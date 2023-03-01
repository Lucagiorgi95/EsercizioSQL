package org.example;

public class Main {
    public static void main(String[] args) {


        SQLManager manager = new SQLManager();
        manager.creaTabella();

        /*Student student = new Student();
        student.insertStudent(1,"luca", "giorgi");
        student.insertStudent(2,"giulio","verdi");
        student.insertStudent(3,"paolo","rossi");
        student.insertStudent(4,"elena","blu");

        student.stampaEAggiungi();
        System.out.println(student.getCognomi());

        manager.aggiungiColonna();

        student.aggiungiPaese("Italy",1);
        student.aggiungiPaese("Italy",2);
        student.aggiungiPaese("Germany",3);
        student.aggiungiPaese("Germany",4);*/

        manager.creaVista("italian_Students","select * from students where country = \"Italy\"");
        manager.creaVista("german_Students","select * from students where country = \"Germany\"");



    }
}