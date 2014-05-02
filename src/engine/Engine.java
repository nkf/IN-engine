package engine;

import engine.util.ListUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Engine {

    private Character[] characters;
    private WorldState worldState;
    private Set<Location> locations;
    private HashMap<Location,Location[]> connections;
    private Action[] actionTemplates;
    private ArrayList<Action> history;
    private int turn = 0;

    public Engine(Character[] characters, HashMap<Location, Location[]> connections, Action[] actionTemplates, WorldState initialState) {
        this.characters = characters;
        worldState = initialState;
        this.connections = connections;
        locations = connections.keySet();
        this.actionTemplates = actionTemplates;
        history = new ArrayList<Action>();
    }

    public ArrayList<Action> turnRecap(Character pov) {
        int TurnIndex = history.size();
        for (; TurnIndex >= 0; TurnIndex--) {
            if(history.get(TurnIndex).character == pov) {
                break;
            }
        }
        ArrayList<Action> recap = new ArrayList<Action>();
        for (; TurnIndex < history.size(); TurnIndex++) {
            Action a = history.get(TurnIndex);
            if(a.location == pov.getLocation()) recap.add(a);
        }
        return recap;
    }

    public void start() {
        while(true) {
            for (Character character : characters) {
                List<Action> actions = getAvailableActions(character);
                actions = checkPreconditions(actions);
                Action selection = character.selectAction(actions);
                selection.postcondition();
                history.add(selection);
            }
            turn++;
        }
    }

    private List<Action> checkPreconditions(List<Action> actions) {
        List<Action> checked = new ArrayList<Action>();
        for (Action action : actions) {
            if(action.precondition()) checked.add(action);
        }
        return checked;
    }

    private List<Action> getAvailableActions(Character character) {
        List<Action> actions = new ArrayList<Action>();
        for (Action template : actionTemplates) {
            Type[] varTypes = template.argumentVariables();
            if(varTypes.length > 0) {
                //create actions with all the different combinations of variables that is specified as arguments.
                for (ArrayList<Object> combination : getVariableCombinations(varTypes)) {
                    actions.add(template.create(character, worldState, combination));
                }
            } else { //no args
                actions.add(template.create(character, worldState, new ArrayList<Object>()));
            }
        }
        return actions;
    }

    private ArrayList<ArrayList<Object>> getVariableCombinations(Type[] varTypes) {
        List<Object>[] varLists = new ArrayList[varTypes.length];
        for (int i = 0; i < varTypes.length; i++) {
            varLists[i] = worldState.getVariablesOfType(varTypes[i]);
        }
        return ListUtil.cartesianProduct(varLists);
    }

}
