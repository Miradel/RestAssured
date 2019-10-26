package Homework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class readInputs {

    public static void main(String[] args) {

        File file = new File("src/test/java/Homework/fileToRead.txt");

        try( FileInputStream fileInputStream = new FileInputStream(file)) {
            System.out.println("Total size in read in the file is "+ fileInputStream.available());

            int content;
            while( (content = fileInputStream.read() )!= -1 ){
                System.out.print((char)content);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
