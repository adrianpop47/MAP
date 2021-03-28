package com.company;

import test.Persoana;
import test.Student;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Student s = new Student("Sorin", (byte) 20, 3);
        s.seDistreaza();
        Persoana stud = new Student("Andu", (byte) 30, 4);
        System.out.println(stud.toString());
    }
}
