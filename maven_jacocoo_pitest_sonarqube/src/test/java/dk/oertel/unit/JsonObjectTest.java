package dk.oertel.unit;

import dk.oertel.JsonObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Unit")
class JsonObjectTest {
    private JsonObject jo;

    @BeforeEach
    public void setup() {
        jo = new JsonObject();
    }

    @Test
    void testJsonObjectNotNULL() {
        assertNotNull(jo);
    }

    @Test
    void testAddToJsonBodyMap() {
        String keyMap = "firstName";
        String valueMap = "Test";
        jo.addToJsonBody(keyMap, valueMap);
        assertEquals(1, jo.getJsonBody().size());
    }
}