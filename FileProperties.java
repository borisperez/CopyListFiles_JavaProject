package FilesCopy;

/**
 *
 * @author boris
 */
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class FileProperties {

    List<String> paths;
    String logFile;
    //return f.getPath().substring(0,f.getPath().lastIndexOf("\\"))+"|"+f.getAbsolutePath()+"|"+gp.getExtension(f)+"|"+gp.getMime(f)+"|"+f.getName()+"|"+dateTime+"| Size: "+gp.getSize(f)+" KB, Owner:"+gp.getOwner(f);

    public FileProperties(List paths, String logDir) {
        paths = paths;
        logFile = logDir + "Copy.log";
    }

    public void getAllFiles() {
        WriteLog writeFile = new WriteLog();
        Walk walkAhead = new Walk();
        for (String path : paths) {
            List<File> sourceFiles = walkAhead.walk(path);
            for (File sourceFile : sourceFiles) {
                writeFile.write(logFile, sourceFile.getAbsolutePath() + "" + sourceFile.length() + "" + sourceFile.getName());
            }
        }
    }

    public String getMime(File f) {
        Path pathNio = f.toPath();
        String mime;
        try {
            mime = Files.probeContentType(pathNio);
        } catch (IOException e) {
            mime = "";
            e.printStackTrace();
        }
        return mime;
    }

    public Long getSize(File f) {
        Path pathNio = f.toPath();
        BasicFileAttributes attr = null;
        try {
            attr = Files.readAttributes(pathNio, BasicFileAttributes.class);
        } catch (IOException e) {
            attr = null;
            e.printStackTrace();
        }
        return attr.size() / 1024;
    }

    public String getOwner(File f) {
        Path pathNio = f.toPath();
        String attr = null;
        try {
            attr = Files.getOwner(pathNio, NOFOLLOW_LINKS).toString();
        } catch (IOException e) {
            attr = null;
            e.printStackTrace();
        }
        return attr.substring(attr.lastIndexOf("\\") + 1);
    }

    public String getExtension(File f) {
        String extension;
        try {
            extension = f.getName().substring(f.getName().lastIndexOf("."));
        } catch (Exception e) {
            extension = "";
        }
        return extension;
    }
}
