package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void testMapSchemaIsValid() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        assertThat(schema.isValid(new HashMap())).isTrue();
        assertThat(schema.isValid(null)).isTrue();
    }

    @Test
    public void testMapSchemaRequired() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        schema.required();

        assertThat(schema.isValid("1")).isFalse();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(new HashMap())).isTrue();
    }


    @Test
    public void testMapSchemaSizeOf() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        schema.required();

        Map data = new HashMap();
        data.put("key", "value");
        assertThat(schema.sizeOf(ONE).isValid(data)).isTrue();
        data.put(ONE, TEN);
        assertThat(schema.isValid(data)).isFalse();
        data.put(THREE, "3");
        assertThat(schema.sizeOf(THREE).isValid(data)).isTrue();
    }

    @Test
    public void testMapSchemaShape() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", TEN);
        assertThat(schema.isValid(human1)).isTrue();
        human1.put("wrong", "wrong");
        assertThat(schema.isValid(human1)).isFalse();

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertThat(schema.isValid(human2)).isFalse();

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertThat(schema.isValid(human3)).isFalse();

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -TEN);
        assertThat(schema.isValid(human4)).isFalse();
    }
}
