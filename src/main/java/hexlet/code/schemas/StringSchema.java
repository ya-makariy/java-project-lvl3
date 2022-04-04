package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class StringSchema {
    private final Map<String, Object> checks = new HashMap<>();
    public StringSchema() {

    }


    public boolean isValid(String str) {
        if (this.checks.containsKey("required")) {
            if (str == null) {
                return false;
            } else if (str.isEmpty()) {
                return false;
            }
        }

        if (this.checks.containsKey("minLength")) {
            if (str.length() < (int) checks.get("minLength")) {
                return false;
            }
        }

        if (this.checks.containsKey("contains")) {
            String sub = (String) this.checks.get("contains");
            if (!str.contains(sub)) {
                return false;
            }
        }

        return true;
    }

    public void required() {
        this.checks.put("required", "0");
    }

    public StringSchema minLength(int strLength) {
        this.checks.put("minLength", strLength);
        return this;
    }

    public StringSchema contains(String subString) {
        this.checks.put("contains", subString);
        return this;
    }
}
