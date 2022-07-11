package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dictionary {


    public static boolean isInDictionary (String key){
        Map<String,String> dictionary = new HashMap<>();
        dictionary.put("Command","CREATE TABLE");
        dictionary.put("tableName","name");
        dictionary.put("row","ROW");

        if(dictionary.containsKey(key)) return true;
        return false;
    }

    /*public static boolean justD0 (String key){
        if(Dictionary.isInDictionary(key)){
            System.out.println(key);
        }
        return true;
    }*/
}
