/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Admin {
    private String name;
    private String password;

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

    public static Admin[] getA() {
        return a;
    }

    public static void setA(Admin[] a) {
        Admin.a = a;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Admin.count = count;
    }

    public Admin(String name, String password) {
        this.name = name;
        this.password = password;
    }
    static Admin a[] = new Admin[100];
    static int count = 0;
    public static void readFile() throws FileNotFoundException{
    File f = new File("admins.txt");
    Scanner s = new Scanner(f);
    while (s.hasNext()){
        String name = s.next();
        String password = s.next();

        a[count] = new Admin ( name , password);
        count++;
          
    }
}
    public static boolean FoundAdmin(String name , String password) throws FileNotFoundException{
    Admin.readFile();
        for ( int i = 0; i < count; i++){
            if (a[i].getName().equals(name)&& a[i].getPassword().equals(password)){
                    return true;
            }
                
        }
        return false;
    
}
}
