package dk.oertel;

import java.util.List;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;


public class Main {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Main.class);
        BasicConfigurator.configure();

        String json =
                "{\n" +
                        "   \"firstName\":\"John\"\n" +
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
                        "           \"number\":\"212 666-1234\"\n" +
                        "       },\n" +
                        "       {\n" +
                        "           \"type\":\"fax\",\n" +
                        "           \"number\":\"212 555-1234\"\n" +
                        "       }\n" +
                        "   ],\n" +
                        "}";
        Parser p = new Parser(json);
        JsonObject jo = p.parse();

        logger.info(jo.getJsonBody().keySet());

        Person person = new Person(
                (String) jo.getJsonBody().get("firstName"),
                (String) jo.getJsonBody().get("lastName"),
                (Integer) jo.getJsonBody().get("age"),
                (Map) jo.getJsonBody().get("address"),
                (List) jo.getJsonBody().get("phoneNumbers")
        );

        logger.info(person);
    }

}
