package FilesCopy;

/**
 *
 * @author boris
 */
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Walk {
List<File> sourceFileList = new ArrayList<>();
    public Walk() {

    }

    public List walk(String path) {
        File root = new File(path);
        File[] list = root.listFiles();
        if (list == null) {
            return null;
        }
        for (File f : list) {
            long fileDateTime = f.lastModified();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
           // String dateTime = dateFormat.format(fileDateTime);
            if (f.isDirectory()) {
                walk(f.getAbsolutePath());
                sourceFileList.add(f);
            } else {
                sourceFileList.add(f);
            }
        }
        return sourceFileList;
    }

}
