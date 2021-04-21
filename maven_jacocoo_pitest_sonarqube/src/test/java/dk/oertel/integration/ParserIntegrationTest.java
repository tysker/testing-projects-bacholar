package dk.oertel.integration;

import dk.oertel.JsonObject;
import dk.oertel.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("Integration")
class ParserIntegrationTest {

    private Parser p;
    private JsonObject jo;

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
        jo = new JsonObject();
    }

    @Test
    void testConvertString(){
        String testStr = "ABC";
        String result = (String) p.convertString(testStr);
        assertEquals(testStr, result);
    }

    @Test
    void testConvertStringIfNumber(){
        String testStr = "123";
        int result = (int) p.convertString(testStr);
        assertEquals(Integer.parseInt(testStr), result);
    }

    @Test
    void testParseJsonFile(){
        jo = p.parse();
        Set<String> testSet = new HashSet<>();
        testSet.add("firstName");
        testSet.add("lastName");
        testSet.add("age");
        testSet.add("address");
        testSet.add("phoneNumbers");
        assertEquals(testSet, jo.getJsonBody().keySet());
    }
}
