package json;

import java.util.HashMap;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private HashMap<String, Json> pairsMap = new HashMap<>();

    public JsonObject(JsonPair... jsonPairs) {
        for (JsonPair pair: jsonPairs) {
            pairsMap.put(pair.key, pair.value);
        }
    }

    @Override
    public String toJson() {
        int count = 0;
        StringBuilder res = new StringBuilder();
        res.append("{");

        for (String key: pairsMap.keySet()) {
            res.append("'");
            res.append(key);
            res.append("': ");
            res.append(pairsMap.get(key).toJson());
            count++;
            if (count != pairsMap.size()) {
                res.append(", ");
            }
        }

        res.append("}");
        return res.toString();
    }

    public void add(JsonPair jsonPair) {
        pairsMap.put(jsonPair.key, jsonPair.value);
    }

    public Json find(String name) {
        return pairsMap.get(name);
    }

    public JsonObject projection(String... names) {
        JsonObject jsonObj = new JsonObject();
        for (String name: names) {
            if (find(name) != null) {
                jsonObj.add(new JsonPair(name, find(name)));
            }
        }
        return jsonObj;
    }
}
