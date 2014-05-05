package engine;

import engine.util.ListUtil;

import java.lang.reflect.Type;
import java.util.*;

public class Engine {

    private Actor[] actors;
    private WorldState worldState;
    private Set<Location> locations;
    private HashMap<Location,Location[]> connections;
    private ActionFactory[] actionTemplates;
    private ArrayList<Action> history;

    private int turn = 0;
    private List<TurnEvent> listeners = new ArrayList<TurnEvent>();

    public Engine(Actor[] actors, HashMap<Location, Location[]> connections, ActionFactory[] actionTemplates, WorldState initialState) {
        this.actors = actors;
        worldState = initialState;
        this.connections = connections;
        locations = connections.keySet();
        this.actionTemplates = actionTemplates;
        history = new ArrayList<Action>();
    }
    /**
     * Illustration of what this method should return.
     * [ c1 , c2 , pov , c4 , c1 , c2 , now ]
     *              |  |-----recap----|
     *           lastTurn
     */
    public List<Action> previousTurnRecap(Actor pov) {
        int turnIndex = history.size()-1;
        while(turnIndex > 0) {
            if(history.get(turnIndex).actor == pov) break;
            turnIndex--;
        }
        List<Action> recap = new ArrayList<Action>();
        if(turnIndex < 0) return recap;
        for (; turnIndex < history.size(); turnIndex++) {
            Action a = history.get(turnIndex);
            if(a.locations.contains(pov.getLocation()) && a.actor != pov) {
                recap.add(a);
            }
        }
        return recap;
    }

    public void addTurnListener(TurnEvent e) {
        listeners.add(e);
    }
    public void removeTurnListener(TurnEvent e) {
        listeners.remove(e);
    }
    private void turnStartedEvent(Actor actor) {
        for (TurnEvent e : listeners) {
            e.TurnStarted(actor);
        }
    }

    public void start() {
        while(!worldState.isGameOver()) {
            for (Actor actor : actors) {
                if(!actor.isActive()) continue;
                turnStartedEvent(actor);
                List<Action> actions = getAvailableActions(actor);
                actions = filterByPrecondition(actions);
                Action selection = actor.selectAction(actions);
                selection.postcondition();
                history.add(selection);
            }
            turn++;
        }
    }

    private List<Action> filterByPrecondition(List<Action> actions) {
        List<Action> checked = new ArrayList<Action>();
        for (Action action : actions) {
            if(action.precondition()) checked.add(action);
        }
        return checked;
    }

    private List<Action> getAvailableActions(Actor actor) {
        List<Action> actions = new ArrayList<Action>();
        for (ActionFactory template : actionTemplates) {
            Type[] varTypes = template.argumentVariables();
            if(varTypes.length > 0) {
                //create actions with all the different combinations of variables that is specified as arguments.
                for (List<Object> combination : getVariableCombinations(varTypes, actor.getLocation())) {
                    actions.add(template.create(actor, worldState, combination));
                }
            } else { //no args
                actions.add(template.create(actor, worldState, new ArrayList<Object>()));
            }
        }
        return actions;
    }

    private List<List<Object>> getVariableCombinations(Type[] varTypes, Location loc) {
        Object[][] varLists = new Object[varTypes.length][];
        for (int i = 0; i < varTypes.length; i++) {
            if     (varTypes[i].equals(Actor.class))    varLists[i] = activeCharacters();
            else if(varTypes[i].equals(Location.class))     varLists[i] = connections.get(loc);
            else                                            varLists[i] = worldState.getVariablesOfType(varTypes[i]);
        }
        if(varLists.length == 1) {
            List<Object> col = Arrays.asList(varLists[0]);
            return wrapObjects(col);
        }
        return ListUtil.cartesianProduct(varLists);
    }

    private List<List<Object>> wrapObjects(List<Object> objects) {
        List<List<Object>> res = new ArrayList<List<Object>>();
        for (Object o : objects) {
            List<Object> wrap = new ArrayList<Object>();
            wrap.add(o);
            res.add(wrap);
        }
        return res;
    }

    private Actor[] activeCharacters() {
        ArrayList<Actor> actives = new ArrayList<Actor>();
        for (int i = 0; i < actors.length; i++) {
            if(actors[i].isActive()) actives.add(actors[i]);
        }
        return actives.toArray(new Actor[actives.size()]);
    }

}
