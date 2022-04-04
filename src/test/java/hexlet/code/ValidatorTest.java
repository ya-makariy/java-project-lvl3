package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {
    static final int SIX = 6;
    static final int THREE = 3;

    @Test
    public void testIsValid() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid(null)).isTrue();
    }

    @Test
    public void testRequired() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.required();

        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("not empty or null string")).isTrue();
    }

    @Test
    public void testContains() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.required();

        assertThat(schema.contains("what").isValid("false string")).isFalse();
        assertThat(schema.contains("what").isValid("string that contain what")).isTrue();
        assertThat(schema.isValid("string that contain")).isFalse();
    }

    @Test
    public void testMinLength() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.required();

        assertThat(schema.minLength(SIX).isValid("false")).isFalse();
        assertThat(schema.minLength(THREE).isValid("string that valid")).isTrue();
        assertThat(schema.isValid("st")).isFalse();
    }

}
