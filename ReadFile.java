/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FilesCopy;

/**
 *
 * @author boris
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    public ReadFile() {

    }

    public List readFile(File file) throws IOException {
        BufferedReader b = null;
        List<String> fileLines = null;
        try {
            fileLines = new ArrayList<String>();
            b = new BufferedReader(new FileReader(file));
            String readLine = "";
            System.out.println("Reading file using Buffered Reader");
            while ((readLine = b.readLine()) != null) {
                fileLines.add(readLine);
            }
        } catch (IOException e) {
            System.out.println("File " + file.toString() + " could not be read or was not found!");
            e.printStackTrace();
        }
        return fileLines;
    }

}
