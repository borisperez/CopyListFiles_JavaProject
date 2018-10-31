package FilesCopy;

/**
 *
 * @author boris
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;

public class Copy {

    private static long targetFileSize;
    WriteLog writeFile = new WriteLog();
    String logFile;

    public Copy() {

    }

    public void CopyAll(List<String> paths, String target, String logDir) throws InterruptedException, IOException {
        logFile = logDir + "Copy.log";
        Walk walkAhead = new Walk();
        for (String path : paths) {
            List<File> sourceFiles = walkAhead.walk(path);
            for (File sourceFile : sourceFiles) {
                copy(sourceFile.getPath().substring(0, sourceFile.getPath().lastIndexOf("\\")), sourceFile.length(), target, sourceFile.getName());
            }
        }
    }

    public static String createDir(String dirTarget) {
        File f = new File(dirTarget);
        if (!f.exists()) {
            f.mkdirs();
        }
        return dirTarget;
    }

    public static String defineTargetDir(String dirSource, String driveTarget) {
        String cutOffDrive = dirSource.split(":")[1];
        return driveTarget + cutOffDrive;
    }

    public void copy(String dirSource, long sizeSource, String dirTarget, String file) throws InterruptedException, IOException {

        File source = new File(dirSource + "\\" + file);
        File target = new File(createDir(defineTargetDir(dirSource, dirTarget)) + "\\" + file);

        if (!target.exists() && !target.isDirectory()) {
            cp(source, target);
            writeFile.write(logFile, "File copied ," + target.getName() + "," + targetFileSize + "," + source + "," + sizeSource);
        } else {
            writeFile.write(logFile, "File :" + target + " already exist");
        }
    }

    private boolean cp(File source, File target) throws IOException {
        boolean erro = false;
        FileChannel sourceChannel = null;
        FileChannel targetChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            targetChannel = new FileOutputStream(target).getChannel();
            targetChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            targetFileSize = targetChannel.size();
        } catch (IOException e) {
            System.err.println("Stream source: " + source.getAbsolutePath() +" Error :" + e);
            writeFile.write(logFile,"Stream source: " + source.getAbsolutePath() +" Error :" + e);
            erro = true;
            return false;
        } finally {
            if (!erro) {
                sourceChannel.close();
                targetChannel.close();
            }
        }

        return true;
    }

// public static void main(String[] args) {
//     createDir("C:\\Users\\boris\\test\\foo");
//  }
}
