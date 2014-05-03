package engine;

import java.lang.reflect.Type;
import java.util.List;

public interface ActionFactory {
    Action create(Character character, WorldState state, List<Object> args);
    Type[] argumentVariables();
}
