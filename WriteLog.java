/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FilesCopy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author boris
 */
public class WriteLog {

    FileWriter fw = null;
    BufferedWriter bw = null;
    PrintWriter out = null;
    
    public WriteLog(){

    }

    public void write(String logFileName, String line) {
        try {
            fw = new FileWriter(logFileName, true);
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);
            out.println(line);
            out.close();
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        } finally {
            if (out != null) {
                out.close();
            } //exception handling left as an exercise for the reader
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                //exception handling left as an exercise for the reader
            }
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                //exception handling left as an exercise for the reader
            }
        }
    }

}
