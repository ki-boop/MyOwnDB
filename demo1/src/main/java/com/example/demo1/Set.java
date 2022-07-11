package com.example.demo1;

import java.util.ArrayList;

public class Set {
    int id;
    ArrayList<Col> column;
    public static int counter = 0;
    public static int counter2 = 0;
    public static int counter3 = -1;

    public static String a (ArrayList<Col> c){
        counter3++;
        System.out.println( c.get(counter2).getall().get(counter3));
        return c.get(counter2).getall().get(counter3);
    }

    public static String lala (ArrayList<Col> c){
        if(counter2<=2){
            if(counter3<1){
                return a(c);
            }else{
                int temp = counter2;
                counter2++;
                counter3=-1;

            }

        }

        return null;
    }

    public static String getstr(int id, ArrayList<Col> c){
    counter++;
    if(counter>24){
        return lala(c);
    }






      return "a" + counter;
    }

    public String getId() {
        return getstr(this.id,this.column);
    }

    public void setId(int id) {
        this.id = id;
    }

    public Col getColumn() {
        return new Col("a","b");
    }

    public void setColumn(ArrayList<Col> column) {
        this.column = column;
    }
}
