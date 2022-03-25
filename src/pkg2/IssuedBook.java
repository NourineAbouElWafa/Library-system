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

/**
 *
 * @author User
 */
public class IssuedBook {

    private String stName;
    private String stCall;
    private int stId;
    private int stContact;
    private Date dateIssued;
    private Date dateReturn;
   public static IssuedBook b1[] = new IssuedBook[100];
   public static int count1 = 0;

    public String getStName() {
        return stName;
    }

    public String getStCall() {
        return stCall;
    }

    public int getStId() {
        return stId;
    }

    public int getStContact() {
        return stContact;
    }

    public Date getDateIssued() {
        return dateIssued;
    }

    public Date getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(Date dateReturn) {
        this.dateReturn = dateReturn;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public void setStCall(String stCall) {
        this.stCall = stCall;
    }

    public void setStId(int stId) {
        this.stId = stId;
    }

    public void setStContact(int stContact) {
        this.stContact = stContact;
    }

    public void setDateIssued(Date dateIssued) {
        this.dateIssued = dateIssued;
    }
    //constructor

    public IssuedBook(String stCall, String stName, int stId, int stContact, Date dateReturn) {
        this.stName = stName;
        this.stCall = stCall;
        this.stId = stId;
        this.stContact = stContact;
        this.dateReturn = dateReturn;
        this.dateIssued = new Date(System.currentTimeMillis());
    }

    public static void readFile1() throws FileNotFoundException, ParseException {
        File f = new File("issuedBooks.txt");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        count1 = 0;
        Scanner s2 = new Scanner(f);
        while (s2.hasNext()) {
            String stCall = s2.next();
            String stname = s2.next();
            int stId = s2.nextInt();
            int stcontact = s2.nextInt();

            String dateStr = s2.next();

            Date d = sdf.parse(dateStr);
            Date dateReturn = sdf.parse(s2.next());
            b1[count1] = new IssuedBook(stCall, stname, stId, stcontact, dateReturn);
            b1[count1].setDateIssued(d);

            count1++;
        }

    }
    //method to add new issued book 

    public static void AddIssuedBook(String stCall, String stName, int stId, int stContact, Date dateReturn) throws FileNotFoundException {
        b1[count1] = new IssuedBook(stCall, stName, stId, stContact, dateReturn);

        count1++;
        saveFile1();
    }

    public static boolean FoundBook(String call, int id) throws FileNotFoundException, ParseException {

        readFile1();
        for (int i = 0; i < count1; i++) {
            if (b1[i].getStCall().equals(call) && b1[i].getStId() == id) {
//                if()
                return true;
            }

        }

        return false;
    }

    //method to return book and to delete it from issued books
    public static void ReturnBook(String call, int id) throws FileNotFoundException, ParseException {
        readFile1();
        boolean found = false;
        for (int i = 0; i < count1; i++) {
            if (b1[i].getStCall().equals(call) && b1[i].getStId() == id) {
                found = true;
                for (int j = i; j < count1 - 1; j++) {
                    b1[j] = b1[j + 1];
                }
                count1--;
                break;
            }
        }
        if (found) {
            saveFile1();

        }

    }
    //method to check if student returned book late

    public static boolean isLate(String call) throws ParseException, FileNotFoundException {
        readFile1();
        SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
        Date current = f1.parse(f1.format(new Date()));

        for (int i = 0; i < count1; i++) {
            if(b1[i].getStCall().equals(call)){
            if (b1[i].getDateReturn().before(current)) {
                return true;
            }
            }
        }
        return false;
    }
    //method to check if student is allowed to borrow another book

    public static boolean checkIssue3(String id) throws FileNotFoundException, ParseException {
        readFile1();
        int n = 0;
        int ID = Integer.parseInt(id);
        for (int i = 0; i < count1; i++) {
            if (b1[i].getStId() == ID) {
                n++;
            }

            if (n == 3) {
                return true;
            }
        }
        return false;
    }

    //method to save issued books added in a file
    public static void saveFile1() throws FileNotFoundException {
        File f = new File("issuedBooks.txt");
        try (PrintWriter pw1 = new PrintWriter(f)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            for (int i = 0; i < count1; i++) {
                pw1.print(b1[i].getStCall() + " ");
                pw1.print(b1[i].getStName() + " ");
                pw1.print(b1[i].getStId() + " ");
                pw1.print(b1[i].getStContact() + " ");
                pw1.print(sdf.format(b1[i].getDateIssued())+" ");
                pw1.println(sdf.format(b1[i].getDateReturn()));
            }
            pw1.close();
        }

    }
     public static Date bookDate(String call) throws FileNotFoundException, ParseException {
      readFile1();
      Date d = null;
        for (int i = 0; i < count1; i++) {

            if (b1[i].getStCall().equals(call)) {
                 d=b1[i].getDateIssued();
            }

        }

      return d;
    }
     

}
