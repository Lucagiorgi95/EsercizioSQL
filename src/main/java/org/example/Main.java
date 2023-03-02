package org.example;

public class Main {
    public static void main(String[] args) {


        SQLManager manager = new SQLManager();
        manager.creaTabella();

        Student student = new Student();
        student.insertStudent("luca", "giorgi");
        student.insertStudent("giulio","verdi");
        student.insertStudent("paolo","rossi");
        student.insertStudent("elena","blu");

        student.stampaEAggiungi();
        System.out.println(student.getCognomi());

        manager.aggiungiColonna();

        student.aggiungiPaese("Italy",1);
        student.aggiungiPaese("Italy",2);
        student.aggiungiPaese("Germany",3);
        student.aggiungiPaese("Germany",4);

        manager.creaVista("italianstudents","select * from students where country = \"Italy\"");
        manager.creaVista("germanstudents","select * from students where country = \"Germany\"");

        student.queryStudent("italian");
        student.queryStudent("german");

        System.out.println(student.getItalian());
        System.out.println(student.getItalian());



    }
}