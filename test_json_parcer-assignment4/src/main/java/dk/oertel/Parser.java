package dk.oertel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    private String json;
    private JsonObject jo;

    public Parser(String json) {
        this.json = json;
        jo = new JsonObject();
    }

    public JsonObject parse() {
        String jsonBodyWithoutComma = removeSpecialCharactersFromJsonString(json);
        jsonBodyWithoutComma = removeStringCharactersFromJsonString(jsonBodyWithoutComma);
        String[] arrOfSplitNewLine = splitJsonStringOnNewLineTag(jsonBodyWithoutComma);

        for (int i = 0; i < arrOfSplitNewLine.length - 1; i++) {
            if(arrOfSplitNewLine[i].contains(":")){
                String[] arrSplitOnColon = splitJsonStringOnColon(arrOfSplitNewLine[i]);

                if(!checkIfStringIsObjectOrList(arrSplitOnColon[1])){
                    String mapKey = arrSplitOnColon[0].trim();
                    Object mapValue = convertString(arrSplitOnColon[1].trim());
                    jo.addToJsonBody(mapKey, mapValue);
                }
                if(checkIfStringIsObject(arrSplitOnColon[1])){
                    String keyMap = arrSplitOnColon[0].trim();
                    Map<String, Object> mapKeyValue = new HashMap<>();
                    int[] contentIndexArr = calculateLengthForObject(i, arrOfSplitNewLine, "}");
                    objectData(contentIndexArr, arrOfSplitNewLine, mapKeyValue);
                    i = contentIndexArr[1];
                    jo.addToJsonBody(keyMap, mapKeyValue);
                }
                if(checkIfStringIsList(arrSplitOnColon[1])){
                    String keyMap = arrSplitOnColon[0].trim();
                    List<Map<String, Object>> listMapKeyValue = new ArrayList<>();
                    int[] contentIndexArrList = calculateLengthForObject(i, arrOfSplitNewLine, "]");
                    listData(contentIndexArrList, arrOfSplitNewLine, listMapKeyValue);
                    i = contentIndexArrList[1];
                    jo.addToJsonBody(keyMap, listMapKeyValue);
                }
            }
        }
        return jo;
    }

    public int[] calculateLengthForObject(int index, String[] arrOfSplitNewLine, String character){
        int contentLength = 0;
        int[] arrContentLength = new int[2];
        arrContentLength[0] = index + 1;
        for (int j = index + 1; j < arrOfSplitNewLine.length - 1; j++) {
            if(!splitJsonStringOnColon(arrOfSplitNewLine[j])[0].contains(character)){
                contentLength += 1;
            } else {
                break;
            }
        }
        arrContentLength[1] =  index + contentLength;
        return arrContentLength;
    }

    public void objectData(int[] indexArr, String[] arrOfSplitNewLine, Map<String, Object> mapKeyValue){
        for (int j = indexArr[0]; j <= indexArr[1]; j++) {
            if (!splitJsonStringOnColon(arrOfSplitNewLine[j])[0].contains("}")) {
                String[] arrSplitOnColonObject = splitJsonStringOnColon(arrOfSplitNewLine[j]);
                createObjectOfKeysAndValues(mapKeyValue, arrSplitOnColonObject[0].trim(), convertString(arrSplitOnColonObject[1].trim()));
            }
        }
    }

    public void listData(int[] indexArr, String[] arrOfSplitNewLine, List<Map<String, Object>> listMapKeyValue){
        for (int k = indexArr[0]; k < indexArr[1]; k++) {
            if(!splitJsonStringOnColon(arrOfSplitNewLine[k])[0].contains("]")){
                String[] arrSplitOnColonObject2 = splitJsonStringOnColon(arrOfSplitNewLine[k]);
                if(checkIfStringIsObject(arrSplitOnColonObject2[0])){
                    Map<String, Object> mapKeyValue2 = new HashMap<>();
                    int[] contentIndexArr = calculateLengthForObject(k, arrOfSplitNewLine, "}");
                    objectData(contentIndexArr, arrOfSplitNewLine, mapKeyValue2);
                    k = contentIndexArr[1];
                    listMapKeyValue.add(mapKeyValue2);
                }
            } else {
                break;
            }
        }
    }

    public String[] splitJsonStringOnNewLineTag(String json) {
        return json.split("\\\n");
    }

    public String removeSpecialCharactersFromJsonString(String json) {
        return json.replace(",", "");
    }

    public String removeStringCharactersFromJsonString(String json) {
        return json.replace("\"", "");
    }

    public String[] splitJsonStringOnColon(String jsonContent) {
        return jsonContent.split(":");
    }

    public Object convertString(String dataRow){
        if(isNumeric(dataRow)){
            return Integer.parseInt(dataRow);
        }
        return dataRow;
    }

    //Source Link: https://www.baeldung.com/java-check-string-number
    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean checkIfStringIsObjectOrList(String jsonContent) {
        return jsonContent.contains("{") || jsonContent.contains("[");
    }

    public void createObjectOfKeysAndValues(Map<String, Object> pairMap, String keyMap, Object valueMap){
        pairMap.put(keyMap, valueMap);
    }

    public boolean checkIfStringIsObject(String jsonContent) {
        return jsonContent.contains("{");
    }

    public boolean checkIfStringIsList(String jsonContent) {
        return jsonContent.contains("[");
    }
}
