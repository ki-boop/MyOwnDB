package com.example.demo1;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

public class Commands {



    public static String Execute (Map<String, ArrayList<String>> code) {
        String command="";
        for (String key : code.keySet()) {
            switch (key) {
                case ("Command"):
                    command = code.get(key).get(0);
                    break;
            }

        }

        return command;
    }


}


