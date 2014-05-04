package engine;

import java.lang.reflect.Type;
import java.util.*;

public class WorldState {
    private HashMap<String, Object> variables;
    private HashMap<Type, List<Object>> typeList;
    private boolean gameOver = false;

    public <T> void addVariable(String name, T var) {
        if(typeList.containsKey(var.getClass())) {
            typeList.get(var.getClass()).add(var);
        } else {
            List<Object> set = new ArrayList<Object>();
            set.add(var);
            typeList.put(var.getClass(),set);
        }
        variables.put(name, var);
    }
    public Object[] getVariablesOfType(Type t) {
        return typeList.get(t).toArray();
    }
    public <T> void SetVariable(String name, T var) {
        throw new UnsupportedOperationException();
    }

    public boolean isGameOver() { return gameOver; }
    public void setGameOver() { this.gameOver = true; }
}
