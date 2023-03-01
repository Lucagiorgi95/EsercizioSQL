package org.example;


import java.sql.*;
import java.util.ArrayList;

public class Student {

    private String name;
    private String surname;

    private Connection connection;
    private PreparedStatement st;
    private ResultSet rs;

    private ArrayList<String> cognomi = new ArrayList<>();
    private ArrayList<Student> italian = new ArrayList<>();
    private ArrayList<Student> german = new ArrayList<>();

    public Student() {}

    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    //Inserimento degli studenti
    public void insertStudent(int id, String name, String surname){
        try {
            connection = DriverManager.getConnection(SQLManager.host,SQLManager.user,SQLManager.password);
            st = connection.prepareStatement("insert into students (student_id, last_name, first_name) values (?,?,?)");
            st.setInt(1,id);
            st.setString(2,name);
            st.setString(3,surname);
            st.execute();
            System.out.println("Insert successfully");
        } catch (SQLException e) {
            System.out.println("Insert failed");
            throw new RuntimeException(e);
        }
    }

    //Stampare i nomi completi e aggiungere i cognomi in una lista
    public void stampaEAggiungi(){
        try {
            connection = DriverManager.getConnection(SQLManager.host,SQLManager.user,SQLManager.password);
            st = connection.prepareStatement("select last_name, first_name from students");
            rs = st.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("first_name") + " " + rs.getString("last_name"));
                cognomi.add(rs.getString("last_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Inserire il paese
    public void aggiungiPaese(String country, int id){
        try {
            connection = DriverManager.getConnection(SQLManager.host,SQLManager.user,SQLManager.password);
            st = connection.prepareStatement("update students set country = ? where (student_id = ?)");
            st.setString(1,country);
            st.setInt(2,id);
            st.execute();
            System.out.println("Insert successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Select della vista per nazionalità ed inserimento nella lista
    public void queryStudent(String nationality){
        try {
            st.executeQuery(String.format("select * from %_student",nationality));
            while(rs.next()){
                Student student = new Student(rs.getString("first_name"),rs.getString("last_name"));
                if(nationality == "italian"){
                    italian.add(student);
                }else{
                    german.add(student);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getCognomi() {
        return cognomi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
