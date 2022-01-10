package domain;

import json.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    List<Tuple<String,Integer>> exams;
    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = Arrays.asList(exams);
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject jsonObj = super.toJsonObject();
        JsonObject[] arr = new JsonObject[exams.size()];
        for(int i = 0; i < exams.size(); i++) {
            arr[i] = new JsonObject();
            arr[i].add((new JsonPair("course", new JsonString(exams.get(i).key))));
            arr[i].add(new JsonPair("mark", new JsonNumber(exams.get(i).value)));
            arr[i].add(new JsonPair("passed", new JsonBoolean(exams.get(i).value >= 3 )));
        }
        jsonObj.add(new JsonPair("exams", new JsonArray(arr)));
        return jsonObj;
    }
}