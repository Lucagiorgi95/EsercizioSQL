package org.example;

import java.sql.*;

public class SQLManager {

    static final String host = "jdbc:mysql://localhost:3306/newdb";
    static final String user = "root";
    static final String password = "password";

    Connection conn;
    Statement st;
    PreparedStatement ps;

    //Stabilire una connessione
    public SQLManager(){
        try {
            conn = DriverManager.getConnection(host,user,password);
            st = conn.createStatement();
            System.out.println("Connected successfully");
        } catch (SQLException e) {
            System.out.println("Connection failed");
            System.out.println(e.getMessage());
        }
    }

    //Creare una tabella
    public void creaTabella(){
        try {
            st.execute("create table if not exists students (" +
                    "student_id int(10) not null auto_increment," +
                    "last_name varchar(30) null," +
                    "first_name varchar(30) null," +
                    "constraint student primary key (student_id));");
            System.out.println("Table is created");
        } catch (SQLException e) {
            System.out.println("Error table");
            System.out.println(e.getMessage());
        }
    }

    //Aggiungere una colonna alla tabella
    public void aggiungiColonna() {
        try {
            st.execute("alter table students add column country varchar(30) after first_name");
            System.out.println("Column is created");
        } catch (SQLException e) {
            System.out.println("The column has not been added");
            throw new RuntimeException(e);
        }
    }

    // Crea una vista
    public void creaVista(String viewName, String query){
        try {
            ps = conn.prepareStatement(String.format("create view \'%s\' as (?);",viewName));
            ps.setString(1,query);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Impossible create a view");
            System.out.println(e.getMessage());
        }
    }

    


}
