package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {
    static final int ONE = 1;
    static final int THREE = 3;
    static final int SIX = 6;
    static final int TEN = 10;
    @Test
    public void testStringSchemaIsValid() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid(null)).isTrue();
    }

    @Test
    public void testStringSchemaRequired() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.required();

        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("not empty or null string")).isTrue();
    }

    @Test
    public void testStringSchemaContains() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.required();

        assertThat(schema.contains("what").isValid("false string")).isFalse();
        assertThat(schema.contains("what").isValid("string that contain what")).isTrue();
        assertThat(schema.isValid("string that contain")).isFalse();
    }

    @Test
    public void testStringSchemaMinLength() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.required();

        assertThat(schema.minLength(SIX).isValid("false")).isFalse();
        assertThat(schema.minLength(THREE).isValid("string that valid")).isTrue();
        assertThat(schema.isValid("st")).isFalse();
    }

    @Test
    public void testNumberSchemaIsValid() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        assertThat(schema.isValid(ONE)).isTrue();
        assertThat(schema.isValid(null)).isTrue();
    }

    @Test
    public void testNumberSchemaRequired() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        schema.required();

        assertThat(schema.isValid("1")).isFalse();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(TEN)).isTrue();
    }

    @Test
    public void testNumberSchemaPositive() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        schema.required();

        assertThat(schema.positive().isValid(-TEN)).isFalse();
        assertThat(schema.isValid(TEN)).isTrue();
    }

    @Test
    public void testNumberSchemaRange() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        schema.required();

        schema.range(THREE, SIX);
        assertThat(schema.isValid(THREE)).isTrue();
        assertThat(schema.isValid(SIX)).isTrue();
        assertThat(schema.isValid(ONE)).isFalse();
        assertThat(schema.isValid(TEN)).isFalse();
    }
}
