/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Student {

    private String name;
    private int id;
    private String password;
    private String email;
    private String address;
    private String city;
    private int contactNo;
    ;

   

    static Student s1[] = new Student[100];
    static int count = 0;

    public Student(String name, int id, String password, String email, String address, String city, int contactNo) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.email = email;
        this.address = address;
        this.city = city;
        this.contactNo = contactNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getContactNo() {
        return contactNo;
    }

    public void setContactNo(int contactNo) {
        this.contactNo = contactNo;
    }

    public static Student[] getS1() {
        return s1;
    }

    public static void setS1(Student[] s1) {
        Student.s1 = s1;
    }
    //method to read students from the file students

    public static void readFile() throws FileNotFoundException {
        File f = new File("students.txt");
        count = 0;
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String name = s.next();
            int id = s.nextInt();
            String pass = s.next();
            String email = s.next();
            String address = s.next();
            String city = s.next();
            int contactNo = s.nextInt();
            s1[count] = new Student(name, id, pass, email, address, city, contactNo);
            count++;

        }
    }
    //method to add new student

    public static void AddStudent(String name, int id, String password, String email, String address, String city, int contactNo) throws FileNotFoundException {
        s1[count] = new Student(name, id, password, email, address, city, contactNo);
        count++;
        saveFile();
    }

  
    //method to save students added in file 

    public static void saveFile() throws FileNotFoundException {
        File f = new File("students.txt");
        try (PrintWriter pw = new PrintWriter(f)) {

            for (int i = 0; i < count; i++) {
                pw.print(s1[i].getName() + " ");
                pw.print(s1[i].getId() + " ");
                pw.print(s1[i].getPassword() + " ");
                pw.print(s1[i].getEmail() + " ");
                pw.print(s1[i].getAddress() + " ");
                pw.print(s1[i].getCity() + " ");
                pw.print(s1[i].getContactNo() + "  \n");

            }
            pw.close();
        }

    }
    public static int StudentId(String st) throws FileNotFoundException, ParseException {
      readFile();
      int id = 0;
        for (int i = 0; i < count; i++) {

            if (s1[i].getName().equals(st)) {
                 id=s1[i].getId();
            }

        }

      return id;
    }
     public static String stName(String name) throws FileNotFoundException, ParseException {
      readFile();
      String n = null;
        for (int i = 0; i < count; i++) {

            if (s1[i].getName().equals(name)) {
                 n=s1[i].getName();
            }

        }

      return n;
    }

  
  public static int FoundStudent(String name, String password) {
        for (int i = 0; i < count; i++) {
            if (s1[i].getName().equals(name) && s1[i].getPassword().equals(password)) {
                return s1[i].getId();
            }

        }
        return -1;

    }

    //method to check if students exists when he is borrowing a book
    public static boolean FoundStudent2(String name, int id) throws FileNotFoundException {
        Student.readFile();
        for (int i = 0; i < count; i++) {
            if (s1[i].getName().equals(name) && s1[i].getId()==(id)) {
                return true;
            }

        }
        return false;

    }
}
