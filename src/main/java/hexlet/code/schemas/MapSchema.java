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

    public MapSchema shape(Map<String, BaseSchema> schemas) {

        Predicate<Object> shapeChecks = map -> {
            if (((Map) map).size() != schemas.size()) {
                return false;
            }

            if (!schemas.entrySet().stream()
                    .allMatch(e -> ((Map) map).containsKey(e.getKey()))) {
                return false;
            }
            for (String key: schemas.keySet()) {
                Object o = ((Map) map).get(key);
                if (!schemas.get(key).isValid(o)) {
                    return false;
                }
            }
            return true;
        };
        addChecks("shape", shapeChecks);
        return this;
    }

}
