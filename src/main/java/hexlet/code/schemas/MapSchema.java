package hexlet.code.schemas;


import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {
    public MapSchema() {
        super();
    }

    public MapSchema required() {
        Predicate<Object> requiredCheck = map -> ((map != null) && (map instanceof Map));
        addChecks("required ", requiredCheck);
        return this;
    }

    public MapSchema sizeOf(int size) {
        Predicate<Object> sizeOfCheck = map -> ((Map) map).size() == size;
        addChecks("sizeOf", sizeOfCheck);
        return this;
    }

}
