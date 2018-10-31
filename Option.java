package FilesCopy;

import java.io.IOException;
import java.util.*;

/**
 *
 * @author boris
 */
public class Option {

    //String flag, opt;
    static Map<String, List<String>> params;

    public Option() {

    }

//    public Option(String flag, String opt) {
//        this.flag = flag;
//        this.opt = opt;
//    }

    public static void main(String[] args) throws InterruptedException, IOException {

       // String[] argsTest = {"-cp", "E:\\Shell_Scripts\\Scripts_Adm", "E:\\Shell_Scripts\\Boris_Adm", "E:\\Shell_Scripts\\ScriptsDatabaseXMETA","E:\\PyCharmSpark", "-rt", "C:\\Users\\boris\\Downloads"};
        Execute exec = new Execute();
        exec.command(getOpt(args));
        //fw.walk("E:\\PoolInformationServer\\IA");
    }

    public static Map getOpt(String[] args) {

        params = new HashMap<>();
        List<String> options = null;
        for (int i = 0; i < args.length; i++) {
            final String option = args[i];

            if (option.charAt(0) == '-') {
                if (option.length() < 2) {
                    System.err.println("Error at argument " + option);
                    return null;
                }
                if (args[i].charAt(1) == '-') {
                    if (args[i].length() < 3) {
                        throw new IllegalArgumentException("Not a valid argument: " + args[i]);
                    }
                }
                if (args.length - 1 == i) {
                    throw new IllegalArgumentException("Expected arg after: " + args[i]);
                }
                if (checkOption(option)) {
                    options = new ArrayList<>();
                    params.put(option.substring(1).trim(), options);
                } else {
                    throw new IllegalArgumentException("Expected arg after: " + args[i]);
                }
            } else if (options != null) {
                options.add(option.trim());
            } else {
                System.err.println("Illegal parameter usage");
                Help help = new Help();
                help.message();
                return null;
            }
        }
        return params;
    }

    private static boolean checkOption(String opt) {
        if (opt == "-cp" || opt == "-rt" || opt == "-lf") {
            return true;
        } else {
            return false;
        }
    }
}

