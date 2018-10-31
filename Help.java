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
class Help {

    public Help() {

    }

    public void message() {
        System.err.println(" [<arg1> [<arg2> [<arg3> ...\n       Options, flags and arguments may be in any order");
        System.err.println("usage:  -lf [<list paths | file with paths>] ");
        System.err.println("usage:  -cp [List | -fp File] -rt [<root target paths>] ");
        System.err.println("Options:");
        System.err.println("-lf <ListFiles> Argumets is a list of files, directory and subdirectory, stating from the past path as argument, \n \t e.g: \"C:\\FolderRoot1\\SubFolder1\\SubSubfolder1, D:\\FolderRoot\"");
        System.err.println("\t The option -lf followed by the option -fp <FilePath> The text file that contains a list of directory adress to be listed");
        System.err.println("\t e.g -lf -fp C:\\Users\\boris\\FileWithPaths.txt");
        System.err.println("-cp <CopyFile> Argumets is a list of files, directory and subdirectory, stating from the past path as argument, \n \t e.g: C:\\FolderRoot1\\SubFolder1\\SubSubfolder1, D:\\FolderRoot");
        System.err.println("\t The option -cp followed by the option -fp <FilePath> The text file that contains a list of directory adress to be listed");
        System.err.println("\t e.g -cp -fp C:\\Users\\boris\\FileWithPaths.txt");
        System.err.println("-rt <RootTarget> To be used with the option -cp will set the root destine to the file and directory that will be copied. \n \t e.g -cp [List | -fp File] -rt ");
    }
}
