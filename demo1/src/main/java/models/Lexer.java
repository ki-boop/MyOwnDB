package models;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Lexer {

    public static Map<String,ArrayList<String>> lexer (String currFile) throws IOException {
        ArrayList<Map> lexem = new ArrayList<>();
        Map<String,ArrayList<String>> stringObjects = new HashMap<>();
        List<String> code = new ArrayList<>();
        code.addAll(Arrays.asList(currFile.split("\\s")));

        for (int i = 0;i<code.size();i++){
            code.remove("");
            code.get(i).trim();
            if(code.get(i).equalsIgnoreCase("create")
                    && code.get(i+1).equalsIgnoreCase("table") ) {
                ArrayList<String> commandCode = new ArrayList<>();
                commandCode.add(code.get(i)+" "+code.get(i+1));
                stringObjects.put("Command",new ArrayList<String>(commandCode));
                if(code.indexOf(")")!=-1){
                    if (i+2 == code.indexOf("(")) System.out.println("нет закрывающей имени");
                    else {
                        ArrayList<String> copyCode = new ArrayList<>();
                        for (int j = i+2;j<code.indexOf("(");j++){
                            copyCode.add(code.get(j));
                        }
                        stringObjects.put("tableName",new ArrayList<>(copyCode));
                    }

                }

            }



            if (code.get(i).equalsIgnoreCase("(") && code.indexOf(")")!=-1) {
                System.out.println(code.indexOf(")"));
                if (i == code.indexOf(")")-1) System.out.println("Пустые строки");
                else {
                ArrayList<String> copyCode = new ArrayList<>();
                String allstring = "";
                for (int j = i+1;j<code.indexOf(")");j++){
                    if(code.get(j).contains(",")){
                        allstring+= code.get(j).substring(0,code.get(j).length()-1) + "#";
                    }else{
                        allstring+=code.get(j)+" ";
                    }


                }
                copyCode.add(allstring+"#");
                stringObjects.put("row",new ArrayList<>(copyCode));
            }}


        }

        for (int i = 0;i<code.size();i++) {
            code.remove("");
            code.remove(",");
            code.get(i).trim();
            if (code.get(i).equalsIgnoreCase("insert")
                    && code.get(i + 1).equalsIgnoreCase("into")) {
                ArrayList<String> commandCode = new ArrayList<>();
                commandCode.add(code.get(i) + " " + code.get(i + 1));
                stringObjects.put("Command", new ArrayList<String>(commandCode));
                if (code.contains(")")) {
                    if (i + 2 == code.indexOf("(")) System.out.println("нет закрывающей имени");
                    else {
                        ArrayList<String> copyCode = new ArrayList<>();
                        for (int j = i + 2; j < code.indexOf("("); j++) {
                            System.out.println("asad");
                            copyCode.add(code.get(j));
                        }
                        stringObjects.put("tableName", new ArrayList<>(copyCode));
                    }

                }

            }
        }
        lexem.add(stringObjects);
        System.out.println(code);

        for (int i = 0;i<code.size();i++) {
            code.remove("");
            code.remove(",");
            code.get(i).trim();
            if (code.get(i).equalsIgnoreCase("select")) {
                ArrayList<String> commandCode = new ArrayList<>();
                commandCode.add(code.get(i));
                stringObjects.put("Command", new ArrayList<>(commandCode));
                if (code.contains("FROM")) {
                        ArrayList<String> copyCode = new ArrayList<>();
                        copyCode.add(code.get(code.size()-1));
                        stringObjects.put("tableName", new ArrayList<>(copyCode));
                    }
                ArrayList<String> copyCode = new ArrayList<>();
                for(int j = code.indexOf("SELECT")+1; j< code.indexOf("FROM");j++) {
                    copyCode.add(code.get(j));
                }
                stringObjects.put("row", new ArrayList<>(copyCode));
                }else break;






            }


        //System.out.println(lexem);
        return stringObjects;
    }
}
