package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {
    public StringSchema() {
        super();
    }

    public StringSchema required() {
        Predicate<Object> requiredCheck = str -> ((str != null) && !((String) str).isEmpty());
        addChecks("required", requiredCheck);
        return this;
    }

    public StringSchema minLength(int strLength) {
        Predicate<Object> minLengthCheck = str -> !(((String) str).length() < strLength);
        addChecks("minLength", minLengthCheck);
        return this;
    }

    public StringSchema contains(String subString) {
        Predicate<Object> containsCheck = str -> ((String) str).contains(subString);
        addChecks("contains", containsCheck);
        return this;
    }
}
