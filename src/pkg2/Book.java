/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import static pkg2.IssuedBook.b1;
import static pkg2.IssuedBook.count1;

/**
 *
 * @author User
 */
public class Book {

    //  book variables
    private String call;
    private String name;
    private String author;
    private String publisher;
    private int quantity;
    private int noIssued;
    private Date dateRegistered;
    static Book b[] = new Book[100];
    //int issue = 0;
    static int count = 0;

    /**
     *
     */
    // issued book variables
    public Book(String call, String name, String author, String publisher, int quantity, int noIssued) {
        this.call = call;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.quantity = quantity;
        this.noIssued = noIssued;

        this.dateRegistered = new Date(System.currentTimeMillis());
    }

    public void setNoIssued(int noIssued) {
        this.noIssued = noIssued;
    }

    public int getNoIssued() {
        return noIssued;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCall(String call) {
        this.call = call;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getName() {
        return name;
    }

    public String getCall() {
        return call;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    //method to read books from file books
    public static void readFile() throws FileNotFoundException, ParseException {
        File f = new File("books.txt");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        count = 0;
        Scanner s1 = new Scanner(f);
        while (s1.hasNext()) {
            String call = s1.next();
            String name = s1.next();
            String author = s1.next();
            String publisher = s1.next();
            int quantity = s1.nextInt();
            int noIssued = s1.nextInt();

            String dateStr = s1.next();
            Date d = sdf.parse(dateStr);
            b[count] = new Book(call, name, author, publisher, quantity, noIssued);
            b[count].setDateRegistered(d);
            count++;
        }

    }

    //method to add new book 
    public static void AddBook(String call, String name, String author, String publisher, int quantity, int noIssued) throws FileNotFoundException {
        b[count] = new Book(call, name, author, publisher, quantity, noIssued);
        count++;
        saveFile();
    }

    //method to save books added in a file
    public static void saveFile() throws FileNotFoundException {
        File f = new File("books.txt");
        try (PrintWriter pw = new PrintWriter(f)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            for (int i = 0; i < count; i++) {
                pw.print(b[i].getCall() + " ");
                pw.print(b[i].getName() + " ");
                pw.print(b[i].getAuthor() + " ");
                pw.print(b[i].getPublisher() + " ");
                pw.print(b[i].getQuantity() + " ");
                pw.print(b[i].getNoIssued() + " ");
                pw.println(sdf.format(b[i].getDateRegistered()));
            }
            pw.close();
        }

    }

    //check if book exists
    public static boolean isFound(String call) throws FileNotFoundException, ParseException {
      readFile();

        for (int i = 0; i < count; i++) {

            if (b[i].getCall().equals(call)) {
                return true;
            }

        }

        return false;
    }

    //editbook when issued to increment no of issued books 
    public static void editBook(String call) throws FileNotFoundException, ParseException {
        readFile();

        for (int i = 0; i < count; i++) {

            if (b[i].getCall().equals(call)) {
                b[i].noIssued++;
            }

        }
        saveFile();

    } 
    /// methos to decrement no of issued books when returned 
     public static void editRBook(String call) throws FileNotFoundException, ParseException {
        readFile();

        for (int i = 0; i < count; i++) {

            if (b[i].getCall().equals(call)) {
                b[i].noIssued--;
            }

        }
        saveFile();

    }
     //method to increment quantity of book when same book added
     public static void editQuantity(String call,int quantity) throws FileNotFoundException, ParseException {
        readFile();

        for (int i = 0; i < count; i++) {

            if (b[i].getCall().equals(call)) {
                b[i].quantity += quantity;
            }

        }
        saveFile();

    }
     //method to return book name
      public static String bookName(String call) throws FileNotFoundException, ParseException {
      readFile();
      String name = null;
        for (int i = 0; i < count; i++) {

            if (b[i].getCall().equals(call)) {
                 name=b[i].getName();
            }

        }

      return name;
    }

}
