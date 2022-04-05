<<<<<<< HEAD
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

    public MapSchema sizeof(int size) {
        Predicate<Object> sizeOfCheck = map -> ((Map) map).size() == size;
        addChecks("sizeOf", sizeOfCheck);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {

        Predicate<Object> shapeChecks = map -> {
            if (shapePreChecks(map, schemas)) {
                return (shapeMajorChecks(map, schemas));
            } else {
                return false;
            }
        };
        addChecks("shape", shapeChecks);
        return this;
    }

    private boolean shapePreChecks(Object map, Map<String, BaseSchema> schemas) {
        if (((Map) map).size() != schemas.size()) {
            return false;
        }
        return schemas.entrySet().stream()
                .allMatch(e -> ((Map) map).containsKey(e.getKey()));
    }

    private boolean shapeMajorChecks(Object map, Map<String, BaseSchema> schemas) {
        for (String key : schemas.keySet()) {
            Object o = ((Map) map).get(key);
            if (!schemas.get(key).isValid(o)) {
                return false;
            }
        }
        return true;
    }

}
=======
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
            if (shapePreChecks(map, schemas)) {
                return (shapeMajorChecks(map, schemas));
            } else {
                return false;
            }
        };
        addChecks("shape", shapeChecks);
        return this;
    }

    private boolean shapePreChecks(Object map, Map<String, BaseSchema> schemas) {
        if (((Map) map).size() != schemas.size()) {
            return false;
        }
        return schemas.entrySet().stream()
                .allMatch(e -> ((Map) map).containsKey(e.getKey()));
    }

    private boolean shapeMajorChecks(Object map, Map<String, BaseSchema> schemas) {
        for (String key : schemas.keySet()) {
            Object o = ((Map) map).get(key);
            if (!schemas.get(key).isValid(o)) {
                return false;
            }
        }
        return true;
    }

}
>>>>>>> fa90895 (ch logic of positive method)
