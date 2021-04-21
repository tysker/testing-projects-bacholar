package dk.oertel.unit;


import dk.oertel.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Tag("unit")
class ParserTest {

    private Parser p;

    private String json =
            "{\n" +
                    "   \"firstName\":\"John\",\n" +
                    "   \"lastName\":\"Smith\",\n" +
                    "   \"address\":{\n" +
                    "       \"streetAddress\":\"21 2nd Street\",\n" +
                    "       \"city\":\"New York\",\n" +
                    "       \"state\":\"NY\",\n" +
                    "       \"postalCode\":10021\n" +
                    "   },\n" +
                    "   \"age\":25,\n" +
                    "   \"phoneNumbers\":[\n" +
                    "       {\n" +
                    "           \"type\":\"home\",\n" +
                    "           \"number\":\"212 555-1234\"\n" +
                    "       },\n" +
                    "       {\n" +
                    "           \"type\":\"fax\",\n" +
                    "           \"number\":\"212 555-1234\"\n" +
                    "       }\n" +
                    "   ],\n" +
                    "}";

    @BeforeEach
    public void setup(){
        p = new Parser(json);
    }

    @Test
    void testStringNotNULL(){
        assertNotNull(json);
    }

    @Test
    void testParserNotNULL(){
        assertNotNull(p);
    }

    @Test
    void testSplitOnNewLinesTag(){
        String[] jsonBodyNewLineArr = p.splitJsonStringOnNewLineTag(json);
        assertTrue(jsonBodyNewLineArr.length > 1);
    }

    @Test
    void testRemoveSpecialCharactersFromJsonString(){
        String jsonAfterRemoveSpecialCharacters = p.removeSpecialCharactersFromJsonString(json);
        boolean jsonStringContainComma = jsonAfterRemoveSpecialCharacters.contains(",");
        assertFalse(jsonStringContainComma);
    }

    @Test
    void testRemoveStringCharactersFromJsonString(){
        String jsonAfterRemoveSpecialCharacters = p.removeStringCharactersFromJsonString(json);
        boolean jsonStringContainStringChar = jsonAfterRemoveSpecialCharacters.contains("\"");
        assertFalse(jsonStringContainStringChar);
    }

    @Test
    void testParsingSingleLine(){
        String testStr = "Test1:Test2";
        String[] jsonBodyColonArray = p.splitJsonStringOnColon(testStr);
        assertEquals(2, jsonBodyColonArray.length);
    }

    @Test
    void testStringIsNumber(){
        String testStr = "123";
        boolean result = p.isNumeric(testStr);
        assertTrue(result);
    }

    @Test
    void testStringIsNotNumber(){
        String testStr = "abc";
        boolean result = p.isNumeric(testStr);
        assertFalse(result);
    }

    @Test
    void testStringIsNotNumberNULL(){
        String testStr = null;
        boolean result = p.isNumeric(testStr);
        assertFalse(result);
    }

    @Test
    void testStringIsObjectOrListWithObject(){
        String testStr = "{";
        boolean result = p.checkIfStringIsObjectOrList(testStr);
        assertTrue(result);
    }

    @Test
    void testStringIsObjectOrListWithList(){
        String testStr = "[";
        boolean result = p.checkIfStringIsObjectOrList(testStr);
        assertTrue(result);
    }

    @Test
    void testStringIsObjectOrListWithCharacter(){
        String testStr = "A";
        boolean result = p.checkIfStringIsObjectOrList(testStr);
        assertFalse(result);
    }

    @Test
    void testStringIsObjectOrListWithNumber(){
        String testStr = "1";
        boolean result = p.checkIfStringIsObjectOrList(testStr);
        assertFalse(result);
    }

    @Test
    void testObjectWithKeysAndValues(){
        Map<String, Object> testMap = new HashMap<>();
        String keyTest = "firstName";
        Object valueTest = "TestName";
        p.createObjectOfKeysAndValues(testMap, keyTest, valueTest);
        assertEquals(1, testMap.size());
    }

    @Test
    void testStringIsObject(){
        String testStr = "{";
        boolean result = p.checkIfStringIsObject(testStr);
        assertTrue(result);
    }

    @Test
    void testStringIsListWithObjects(){
        String testStr = "[";
        boolean result = p.checkIfStringIsList(testStr);
        assertTrue(result);
    }

}
