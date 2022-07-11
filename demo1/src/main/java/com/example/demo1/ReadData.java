package com.example.demo1;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadData {

    public static void addTable(String nameOfTable,String rows,File data) throws IOException {
        if(findTable(nameOfTable,data)==-1) {
            createTable(nameOfTable,rows,data);
            System.out.println("NeW TABLE!");
        }else{
            System.out.println("Номер элемента массива строки: " + findTable(nameOfTable,data));
            System.out.println("Table name already detected, try rename your table");
        }

    }

    public static void createTable(String nameOfTable,String rows,File data) throws IOException {
        ArrayList<String> dataArray = getData(data);
        ArrayList<String> rowsArray = separateRow(rows);
        FileWriter writer = new FileWriter(data, false);
        int nextTable = rowsArray.size()+dataArray.size()+1;
        String code = "";
        for (String dataCode : dataArray){
            code+=dataCode+"\n";
        }
        code+= nameOfTable + "_" +rowsArray.size() + "_" + nextTable+"\n";
        for (String row : rowsArray){
            code+=row + "\n";
        }

        writer.write(code);
        writer.flush();

    }

    public static ArrayList<String> separateRow(String rows){
        ArrayList<String> result = new ArrayList<>();
        for (String row : rows.split("#")){
            for(String word : row.split(" ")){
                result.add(word);
                break;
            }
        }
        System.out.println(result);
        return result;
    }

    public static ArrayList<String> getData(File data) throws IOException{
        String textFile ="";
        BufferedReader fin = new BufferedReader(new FileReader(data));
        ArrayList<String> arr = new ArrayList<>();
        while((textFile=fin.readLine())!=null){
            arr.add(textFile);
        }
        System.out.println(arr);
        return  arr;
    }

    public static int findTable(String tablename, File data) throws IOException {
        ArrayList<String> dataArray = getData(data);
        System.out.println(dataArray);
        for (int i = 0; i< dataArray.size();){
            String tempname = dataArray.get(i);
            for (String retval : tempname.split("_")) {
                System.out.println(retval + " kzkzkzk");
                if(retval.trim().equals(tablename)) {
                    System.out.println("Find table");
                    return Integer.parseInt(tempname.split("_")[2])-Integer.parseInt(tempname.split("_")[1])-1;
                }
                else {
                    try{
                       i = Integer.parseInt(tempname.split("_")[2]);

                        System.out.println(Integer.parseInt(tempname.split("_")[2]));
                    }catch (IndexOutOfBoundsException e){
                        return -1;
                    }

                }
            }
        }
        return -1;
    }

    public static void insert(String nameOfTable, String rows, File data) throws IOException {
        int rowTable = findTable(nameOfTable,data);
        ArrayList<String> values = new ArrayList<>();
        if(rowTable !=-1){
            for(String s : rows.split("#")){
                values.add(s);
            }
         rowTable++;
            ArrayList<String> dataArray = getData(data);
            FileWriter writer = new FileWriter(data, false);
            System.out.println(dataArray + "   insert");
            for(int i =0; i<values.size(); i++){
                dataArray.set(rowTable, dataArray.get(rowTable) + " " +values.get(i));
                rowTable++;
            }
            String code = "";
            for (String row : dataArray){
                code+=row + "\n";
            }

            writer.write(code);
            writer.flush();


        }else{
            System.out.println("Невозможно вставить данные");
        }

    }

    public static ArrayList<ArrayList<String>> getDataFromTable(String nameOfTable, File data) throws IOException {
        int rowTable = findTable(nameOfTable,data);
        ArrayList<String> dataArray = getData(data);
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        ArrayList<String> row1 = new ArrayList<>();
        row1.addAll(Arrays.asList(dataArray.get(rowTable+1).split(" ")));
        row1.remove(0);
        ArrayList<String> row2 = new ArrayList<>();
        row2.addAll(Arrays.asList(dataArray.get(rowTable+2).split(" ")));
        row2.remove(0);
        ArrayList<String> row3 = new ArrayList<>();
        row3.addAll(Arrays.asList(dataArray.get(rowTable+3).split(" ")));
        row3.remove(0);
        ArrayList<String> row4 = new ArrayList<>();
        row4.addAll(Arrays.asList(dataArray.get(rowTable+4).split(" ")));
        row4.remove(0);
        ArrayList<String> row5 = new ArrayList<>();
        row5.addAll(Arrays.asList(dataArray.get(rowTable+5).split(" ")));
        row5.remove(0);
        ArrayList<String> row6 = new ArrayList<>();
        row6.addAll(Arrays.asList(dataArray.get(rowTable+6).split(" ")));
        row6.remove(0);

        result.add(row1);
        result.add(row2);
        result.add(row3);
        result.add(row4);
        result.add(row5);
        result.add(row6);
        return result;
    }


}
