package engine;

import java.lang.reflect.Type;
import java.util.List;

public interface ActionFactory {
    Action create(Actor actor, WorldState state, List<Object> args);
    Type[] parameterVariables();
}
