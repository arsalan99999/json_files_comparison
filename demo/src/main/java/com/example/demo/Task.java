package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.google.gson.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Map;

public class Task {
    public static void main(String[] args) throws JsonProcessingException, FileNotFoundException {

        //Json Object for DataSet
        JsonObject obj1=new JsonObject();

        obj1.addProperty("id",102);
        obj1.addProperty("name","Shani");
        obj1.addProperty("gender",'M');

        JsonArray array1 = new JsonArray();
        array1.add("BS");
        array1.add("MS");

        obj1.addProperty("degree", String.valueOf(array1));

        //Use ObjectMapper for Comparison

        ObjectMapper mapper = new ObjectMapper();
        JsonParser jsonParser = new JsonParser();

//      Reading TestFile1
        FileReader reader1 = new FileReader("C:\\Users\\Pc\\IdeaProjects\\Tasks\\demo\\src\\main\\java\\com\\example\\demo\\TestFile1.json");
        JsonObject obj2 = (JsonObject) jsonParser.parse(reader1);

//      Reading TestFile2
        FileReader reader2 = new FileReader("C:\\Users\\Pc\\IdeaProjects\\Tasks\\demo\\src\\main\\java\\com\\example\\demo\\TestFile2.json");
        JsonObject obj3 = (JsonObject) jsonParser.parse(reader2);

        Gson g = new Gson();
        Type mapType = new TypeToken<Map<String,Object>>(){}.getType();

        Map<String, Object> firstMap = g.fromJson(obj1,mapType);
        Map<String, Object> secondMap = g.fromJson(obj2,mapType);
        Map<String, Object> thirdMap = g.fromJson(obj3,mapType);

        MapDifference<String, Object> difference1 = Maps.difference(firstMap,secondMap);
        MapDifference<String, Object> difference2 = Maps.difference(firstMap,thirdMap);

//        System.out.println(difference2);

        System.out.println("Comparing Data from file 1:");
        if (mapper.readTree(String.valueOf(obj1)).equals(mapper.readTree(String.valueOf(obj2)))){
            System.out.println("Congratulations Data Matched");
        }
        else{
            System.out.println("Mismatch Data from File1 is:");
            difference1.entriesDiffering().forEach((key,value)-> System.out.println(key + ": "+ value));
        }

        System.out.println("\nComparing Data from file 2:");

        if(mapper.readTree(String.valueOf(obj1)).equals(mapper.readTree(String.valueOf(obj3)))){
            System.out.println("Congratulations Data Matched");
        }
        else{
            System.out.println("\nMismatch Data from File2 is:");
            difference2.entriesDiffering().forEach((key,value)-> System.out.println("\n" + key + ": "+ value));
        }
    }

}
