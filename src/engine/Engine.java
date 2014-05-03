package engine;

import engine.util.ListUtil;

import java.lang.reflect.Type;
import java.util.*;

public class Engine {

    private Character[] characters;
    private WorldState worldState;
    private Set<Location> locations;
    private HashMap<Location,Location[]> connections;
    private ActionFactory[] actionTemplates;
    private ArrayList<Action> history;

    private int turn = 0;
    private List<TurnEvent> listeners = new ArrayList<TurnEvent>();

    public Engine(Character[] characters, HashMap<Location, Location[]> connections, ActionFactory[] actionTemplates, WorldState initialState) {
        this.characters = characters;
        worldState = initialState;
        this.connections = connections;
        locations = connections.keySet();
        this.actionTemplates = actionTemplates;
        history = new ArrayList<Action>();
    }

    public List<Action> turnRecap(Character pov) {
        int turnIndex = history.size()-1;
        while(turnIndex > 0) {
            if(history.get(turnIndex).character == pov && turnIndex != history.size()-1)
                break;
            turnIndex--;
        }
        List<Action> recap = new ArrayList<Action>();
        for (; turnIndex < history.size(); turnIndex++) {
            Action a = history.get(turnIndex);
            if(a.location == pov.getLocation()) recap.add(a);
        }
        return recap;
    }

    public void addTurnListener(TurnEvent e) {
        listeners.add(e);
    }
    public void removeTurnListener(TurnEvent e) {
        listeners.remove(e);
    }
    private void turnPassedEvent() {
        for (TurnEvent e : listeners) {
            e.TurnPassed();
        }
    }

    public void start() {
        while(!worldState.isGameOver()) {
            for (Character character : characters) {
                List<Action> actions = getAvailableActions(character);
                actions = filterByPrecondition(actions);
                Action selection = character.selectAction(actions);
                selection.postcondition();
                history.add(selection);
            }
            turn++;
            turnPassedEvent();
            if(turn > 10) worldState.setGameOver();
        }
    }

    private List<Action> filterByPrecondition(List<Action> actions) {
        List<Action> checked = new ArrayList<Action>();
        for (Action action : actions) {
            if(action.precondition()) checked.add(action);
        }
        return checked;
    }

    private List<Action> getAvailableActions(Character character) {
        List<Action> actions = new ArrayList<Action>();
        for (ActionFactory template : actionTemplates) {
            Type[] varTypes = template.argumentVariables();
            if(varTypes.length > 0) {
                //create actions with all the different combinations of variables that is specified as arguments.
                for (List<Object> combination : getVariableCombinations(varTypes, character.getLocation())) {
                    actions.add(template.create(character, worldState, combination));
                }
            } else { //no args
                actions.add(template.create(character, worldState, new ArrayList<Object>()));
            }
        }
        return actions;
    }

    private List<List<Object>> getVariableCombinations(Type[] varTypes, Location loc) {
        Object[][] varLists = new Object[varTypes.length][];
        for (int i = 0; i < varTypes.length; i++) {
            if     (varTypes[i].equals(Character.class))    varLists[i] = characters;
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

}
