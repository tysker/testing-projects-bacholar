package dk.oertel;

import java.util.HashMap;
import java.util.Map;


public class JsonObject {
    private Map<String, Object> jsonBody;

    public JsonObject() {
        this.jsonBody = new HashMap<>();
    }

    public Map<String, Object> getJsonBody() {
        return jsonBody;
    }

    public void addToJsonBody(String keyMap, Object valueMap){
        jsonBody.put(keyMap, valueMap);
    }
}
