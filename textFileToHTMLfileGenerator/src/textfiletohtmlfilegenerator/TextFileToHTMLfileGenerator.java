/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textfiletohtmlfilegenerator;

import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.file.Paths;

/**
 *
 * @author 4800590195
 */
public class TextFileToHTMLfileGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner stdIn = new Scanner(System.in);
        String filenameIn;      // original file's name
        int dotIndex;           //position of dot in filename
        String filenameOut;     //HTML file's name
        String line;            //a line from the input file
        System.out.println("Enter file's name: ");
        filenameIn = stdIn.nextLine();
        //compose the new filename
        dotIndex = filenameIn.lastIndexOf(".");
        if (dotIndex == -1) // no dot found
        {
            filenameOut = filenameIn + ".html";
        } else {
            filenameOut = filenameIn.substring(0, dotIndex) + ".html";
        }
        try (
                Scanner fileIn = new Scanner(Paths.get(filenameIn));
                PrintWriter fileOut = new PrintWriter(filenameOut)) {
            // first line used for title and header elements
            line = fileIn.nextLine();
            if (line == null) {
                System.out.println(filenameIn + " is empyt.");
            } else {
                // write the top of the html page.
                fileOut.println("<!DOCTYPE.html>");
                fileOut.println("<html>");
                fileOut.println("<head>");
                fileOut.println("<title>" + line + "</title>");
                fileOut.println("</head>");
                fileOut.println("<body>");
                fileOut.println("<h1>" + line + "</h1>");

                while (fileIn.hasNextLine()) {
                    line = fileIn.nextLine();
                    //blank lines generate p tags.
                    if (line.isEmpty()) {
                        fileOut.println("<p>");
                    } else {
                        fileOut.println(line);
                    }
                }// end while
                // write ending html code
                fileOut.println("</body>");
                fileOut.println("</html>");
            }// end else
        }// end try, and close fileOut and fileIn automatically 
        catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        } // end catch
        System.out.println("check file for new file created");
    } // end main

} // end class HTMLgenerator
