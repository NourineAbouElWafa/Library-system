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
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Librarian {

    //librarian variables
    private String name;
    private String password;
    private String email;
    private String address;
    private String city;
    private int contact;
    static Librarian l[] = new Librarian[100];
    static int c = 0;

    public Librarian(String name, String password, String email, String address, String city, int contact) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.address = address;
        this.city = city;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    //method to read librarian from file books
    public static void readFile() throws FileNotFoundException, ParseException {
        File f = new File("librarian.txt");

        c = 0;
        Scanner s3 = new Scanner(f);
        while (s3.hasNext()) {
            String name = s3.next();
            String password = s3.next();
            String email = s3.next();
            String address = s3.next();
            String city = s3.next();
            int contact = s3.nextInt();

            l[c] = new Librarian(name, password, email, address, city, contact);

            c++;
        }

    }
 //method to add new librarian 
    public static void AddLibrarian(String name, String password, String email, String address, String city, int contact) throws FileNotFoundException {
        l[c] = new Librarian (name, password, email, address, city,contact);
        c++;
        saveFile();
    }
    //method to check if librarian alread exists
    public static boolean FoundLibrarian(String name , String password) throws FileNotFoundException, ParseException{
   Librarian.readFile();
        for ( int i = 0; i < c; i++){
            if (l[i].getName().equals(name)&& l[i].getPassword().equals(password)){
                    return true;
            }
                
        }
        return false;
    
}
    //method to save librarians added in a file
    public static void saveFile() throws FileNotFoundException {
        File f = new File("librarian.txt");
        try (PrintWriter pw = new PrintWriter(f)) {
            

            for (int i = 0; i < c; i++) {
                pw.print(l[i].getName() + " ");
                pw.print(l[i].getPassword() + " ");
                pw.print(l[i].getEmail() + " ");
                pw.print(l[i].getAddress() + " ");
                pw.print(l[i].getCity() + " ");
                pw.print(l[i].getContact() + "  \n");
               
            }
            pw.close();
        }

    }
    //check if librarian is valid to login
     public static boolean isValid(String call, String password) throws FileNotFoundException, ParseException {
           readFile();
        for (int i = 0; i < c; i++) {

            if (l[i].getName().equals(call)&&l[i].getPassword().equals(password)) {
                return true;
            }

        }

        return false;
    }
     //method to delte librarian
     public static void deleteLibrarian(String name,String password) throws FileNotFoundException, ParseException {
        boolean found = false;
        readFile();
        for (int i = 0; i < c; i++) {
            if (l[i].getName().equals(name)&&l[i].getPassword().equals(password)) {
                found = true;
                for (int j = i; j < c- 1; j++) {
                    l[j] = l[j + 1];
                }
                c--;
                break;
            }
        }
        if (found) {
            saveFile();
            
        }
}
}
