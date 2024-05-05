package pkg;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class FileHandler {
    private String fileName;

    public FileHandler(String fileName){
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            this.fileName = fileName;
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred. File not found.");

            e.printStackTrace();
        }
    }

    public void SplitFileByLength(){
        try {
            File myObj = new File(this.fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                this.WriteToFile(data);
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred. File not found.");
            e.printStackTrace();
        }
    }

    private void WriteToFile(String data){
        String fileName = "input/split/" + data.length() +  ".txt";

        File myObj= new File(fileName);
    
        try {
            FileWriter myWriter = new FileWriter(myObj, true);
            myWriter.write(data);
            myWriter.write("\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler("dictionary.txt");
        fileHandler.SplitFileByLength();
    }
}
