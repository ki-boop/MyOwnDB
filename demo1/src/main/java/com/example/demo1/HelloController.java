package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Dictionary;
import models.Lexer;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TreeView<File> TheTree;
    @FXML
    private TextArea codeBlock;



    public ObservableList<Student> getStudentList(ArrayList<ArrayList<String>> res) {
        ObservableList<Student> list = FXCollections.observableArrayList();
        for(int i =0; i< res.get(0).size();i++){
            list.add(new Student(res.get(0).get(i),res.get(1).get(i),res.get(2).get(i),res.get(3).get(i),res.get(4).get(i),res.get(5).get(i)));
        }

        return list;
    }

    public void createNewDataBase(ObservableList<Student> list) throws InstantiationException, IllegalAccessException {
        TableView tableView = new TableView<>();
        Scene scene= new Scene( tableView, 800,400);
       /* Col c1 = new Col("1","Vasya");
        Col c2 = new Col("2", "Kate");
        Col c3 = new Col("3", "Ivan");
        ArrayList<Col> objects = new ArrayList<>();
        objects.add(c1);
        objects.add(c2);
        objects.add(c3);
        double count=0;
        for (Col c : objects){
            if (c.getCol1()!=null) count++;
            if (c.getCol2()!=null) count++;
            if (c.getCol3()!=null) count++;
            if (c.getCol4()!=null) count++;
        }

        ObservableList<Set> list1 = FXCollections.observableArrayList();
        ArrayList<Set> arr = new ArrayList<>();
        for(int i =0;i<objects.size();i++){
            list1.add(new Set());
            list1.get(i).setId(i);
            list1.get(i).setColumn(objects);
        }



        ArrayList<TableColumn> result = new ArrayList<>();
        for(int i =1; i<count/3+1;i++){
            result.add(new TableColumn<>("col"+i));
        }


        for(TableColumn c : result){
            c.setCellValueFactory(new PropertyValueFactory<>("id"));
        }

        tableView.setItems(list1);
        tableView.getColumns().addAll(result);

*/


        TableColumn<String, String> idCol
                = new TableColumn<String, String>("Id");

        TableColumn<String, String> nameCol
                = new TableColumn<String, String>("User Name");

        TableColumn<String, String> gradeCol//
                = new TableColumn<String, String>("Grade");


        TableColumn<String, String> firstNameCol//
                = new TableColumn<String, String>("First Name");

        TableColumn<String, String> lastNameCol //
                = new TableColumn<String, String>("Last Name");

        TableColumn<String, String> activeCol//
                = new TableColumn<String, String>("Active");

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        activeCol.setCellValueFactory(new PropertyValueFactory<>("active"));
        gradeCol.setCellValueFactory(new PropertyValueFactory<>("grades"));

        // Set Sort type for userName column
        nameCol.setSortType(TableColumn.SortType.DESCENDING);
        lastNameCol.setSortable(false);

        tableView.setItems(list);
        tableView.getColumns().addAll(idCol, nameCol, firstNameCol,lastNameCol, activeCol,gradeCol);


        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Second Stage");
        newWindow.setScene(scene);

        // Set position of second window, related to primary window.


        newWindow.show();

        /*
        String fileSeparator = System.getProperty("file.separator");
        String absoluteFilePath =  "C:"+ fileSeparator+"Users" + fileSeparator + "Kirill" + fileSeparator + "Desktop" +fileSeparator + "temp" + fileSeparator+ "file.txt";
        File file = new File(absoluteFilePath);
        System.out.println(absoluteFilePath);
        if(file.createNewFile()){
            System.out.println(absoluteFilePath + " Файл создан");
        } else {
            System.out.println("Файл " + absoluteFilePath + " уже существует");
        }
        file = new File("file.txt");
        if(file.createNewFile()){
            System.out.println("file.txt файл создан в корневой директории проекта");
        }else System.out.println("file.txt файл уже существует в корневой директории проекта");
*/
    }


    public void SaveCode() throws IOException {
        File currFile = new File(TheTree.getSelectionModel().getSelectedItems().get(0).getValue().toString());
        FileWriter writer = new FileWriter(currFile,false);
        String code = codeBlock.getText();
        writer.write(code);
        writer.flush();
    }



    public void CreateTable(String name, String row) throws IOException {
        String code ="ALTER TABLE " + name + "(\n";
        File fileRoot = new File(TheTree.getRoot().getValue().toString());
        TreeItem<File> rootItem = new TreeItem<>(fileRoot);
        rootItem.setExpanded(true);
        File newFile = new File(fileRoot+"/"+name);
        File dataFile = new File(fileRoot + "/" + "DATA");
        if(!dataFile.canExecute()){
            dataFile.createNewFile();
        }
        System.out.println(row);
        ReadData.addTable(name,row,dataFile);
        newFile.createNewFile();
        FileWriter writer = new FileWriter(newFile, false);

        for (String retval : row.split("#")) {
            code+= retval + ",\n";
        }

        writer.write(code+")");
        writer.flush();

        File[] files = fileRoot.listFiles();
        for (int i=0;i<files.length;i++){
            System.out.println(files[i].toString());
            rootItem.getChildren().add(new TreeItem<File>(files[i]));
        }
        TheTree.setRoot(rootItem);
    }
    public void select(String name) throws IOException, InstantiationException, IllegalAccessException {
        File fileRoot = new File(TheTree.getRoot().getValue().toString());
        File dataFile = new File(fileRoot + "/" + "DATA");

        createNewDataBase(getStudentList(ReadData.getDataFromTable(name,dataFile)));
    }

    public void insert (String name, String row) throws IOException {
        File fileRoot = new File(TheTree.getRoot().getValue().toString());
        File dataFile = new File(fileRoot + "/" + "DATA");
        System.out.println(dataFile);
        ReadData.insert(name,row,dataFile);

    }

    public void RunCode() throws IOException, InstantiationException, IllegalAccessException {

        String code = codeBlock.getText();
        Map<String, ArrayList<String>> doing = new HashMap<>(Lexer.lexer(code));
        System.out.println(doing + "   HashMap");
        Commands.Execute(doing);
        switch (doing.get("Command").get(0)){
            case ("CREATE TABLE"):
                welcomeText.setText("CREATE TABLE " + doing.get("tableName").get(0));
                CreateTable(doing.get("tableName").get(0),doing.get("row").get(0));
                break;
            case ("INSERT INTO"):
                insert(doing.get("tableName").get(0),doing.get("row").get(0));
                welcomeText.setText("INSERT INTO " + doing.get("tableName").get(0));
                break;
            case ("SELECT"):
                select(doing.get("tableName").get(0));
                welcomeText.setText("SELECT FROM " + doing.get("tableName").get(0));
                break;

        }
    }
    public void openFile () throws IOException {
        final FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(HelloApplication.stage);
        TheTree.setRoot(new TreeItem<File>(selectedFile));
    }

    public void openFolder()throws IOException{
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(HelloApplication.stage);
        System.out.println(selectedDirectory.getName());
        TreeItem<File> rootItem = new TreeItem<>(selectedDirectory);
        rootItem.setExpanded(true);
        File[] files = selectedDirectory.listFiles();
        for (int i=0;i<files.length;i++){
            System.out.println(files[i].toString());
            rootItem.getChildren().add(new TreeItem<File>(files[i]));
        }
        TheTree.setRoot(rootItem);
    }

    public void click() throws IOException {
        String textFile ="";
        System.out.println("click");
        File currFile = new File(TheTree.getSelectionModel().getSelectedItems().get(0).getValue().toString());
        BufferedReader fin = new BufferedReader(new FileReader(currFile));
        ArrayList<String> arr = new ArrayList<String>();
        while((textFile=fin.readLine())!=null){
            arr.add(textFile);
        }
        System.out.println(arr);
        textFile="";
        for (int i =0; i<arr.size();i++){
            textFile+=arr.get(i)+"\n";
        }
        codeBlock.setText(textFile);
    }
}