package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {

    public NumberSchema() {
        super();
    }

    public NumberSchema required() {
        Predicate<Object> requiredCheck = num -> ((num != null) && (num instanceof Integer));
        addChecks("required", requiredCheck);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Object> positiveCheck = num -> (num == null)
                || ((num instanceof Integer)
                && (((int) num) > 0));
        addChecks("positive", positiveCheck);
        return this;
    }

    public NumberSchema range(int bottom, int top) {
        Predicate<Object> rangeCheck = num -> ((num instanceof Integer)
                && (((int) num) > bottom - 1 && ((int) num) < top + 1));
        addChecks("range", rangeCheck);
        return this;
    }
}
