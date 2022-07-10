package com.example.sambo_tablo.create_file;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteIntoFile {
    public void writeIntoFile(String info){
        try {
            Date date = new Date() ;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh") ;

            FileWriter myWriter = new FileWriter(dateFormat.format(date) + ".txt");
            myWriter.write(info);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
