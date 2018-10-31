/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FilesCopy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author boris
 */
public class Execute {

    public Execute(){

    }

    public void command(Map<String, List<String>> params) throws InterruptedException, IOException {
        if (params.get("cp").get(0).length() > 0) {
            if (params.get("rt").get(0).length() > 0) {
                Copy copy = new Copy() ;
                copy.CopyAll(createList(params.get("cp").toArray()),params.get("rt").get(0),defineDirLog());
            } else {
                System.err.println("Erro de parametro target dir not defined");
            }
        } else if (params.get("lf").get(0).length() > 0) {
            createList(params.get("lf").toArray());
        } else {
            System.err.println("Execute o commando list args");
        }
    }

    public String defineDirLog() {
        String OS = System.getProperty("os.name").toLowerCase();
        String usersHome = System.getProperty("user.home");
        String sep = null;
        if (OS.indexOf("win") >= 0) {
            sep = "\\";
        } else {
            sep = "/";
        }
        File userHomeDir = new File(usersHome + sep + "LogsFileCopy");
        userHomeDir.mkdirs();
        System.out.println("The log file defined is :" + userHomeDir.toString());
        return userHomeDir.toString()+sep;
    }

    private List createList(Object[] params) {
        List<String> fileLines = new ArrayList<String>();
        try {
            if (params.length == 1) {
                File file = new File(params.toString());
                if (file.isFile()) {
                    return readFile(file);
                } else {
                    fileLines.add(params[0].toString());
                    return fileLines;
                }
            } else {
                for (Object param : params) {
                    fileLines.add((String)param.toString());
                }
                return fileLines;
            }
        } catch (Exception e) {
            return null;
        }
    }

    private List readFile(File file) throws IOException {
        ReadFile fileParam = new ReadFile();
        return fileParam.readFile(file);
    }

}
