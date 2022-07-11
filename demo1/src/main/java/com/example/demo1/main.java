package com.example.demo1;

import models.Lexer;

import java.io.*;

public class main {

    public static void main(String[] args) throws IOException {

        File currFile = new File("C:\\Users\\Kirill\\Desktop\\parserForFufel\\parser.txt");
        Lexer.lexer(currFile.toString());


    }

}
