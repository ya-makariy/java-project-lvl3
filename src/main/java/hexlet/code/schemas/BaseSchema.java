package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private Map<String, Predicate<Object>> checks = new HashMap<>();

    protected BaseSchema() {

    }

    public final boolean isValid(Object o) {
        for (String key: checks.keySet()) {
            if (!checks.get(key).test(o)) {
                return false;
            }
        }
        return true;
    }

    protected final void addChecks(String checkName, Predicate<Object> check) {
        checks.put(checkName, check);
    }
}
