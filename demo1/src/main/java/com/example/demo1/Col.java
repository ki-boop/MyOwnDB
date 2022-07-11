package com.example.demo1;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Col {
    String col1;
    String col2;
    String col3;
    String col4;

    public Col(String col1) {
        this.col1 = col1;
    }

    public Col(String col1, String col2, String col3) {
        this.col1 = col1;
        this.col2 = col2;
        this.col3 = col3;
    }

    public Col(String col1, String col2) {
        this.col1 = col1;
        this.col2 = col2;
    }

    public Col(String name1, String name2, String name3, String name4) {
        this.col1 = name1;
        this.col2 = name2;
        this.col3 = name3;
        this.col4 = name4;
    }
    public String getCol(int a){

        return getall().get(a);
    }

    public ArrayList<String> getall(){
        ArrayList<String> a = new ArrayList<>();
        a.add(col1);
        a.add(col2);
        a.add(col3);
        a.add(col4);
        return a;

    }

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }

    public String getCol3() {
        return col3;
    }

    public void setCol3(String col3) {
        this.col3 = col3;
    }

    public String getCol4() {
        return col4;
    }

    public void setCol4(String col4) {
        this.col4 = col4;
    }
}
