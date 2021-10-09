import java.util.ArrayList;
import java.io.File;
import java.util.*;
import java.text.*;


public class ListCommand {

    public static void list(String path) {
        ArrayList<String> fileList = new ArrayList<String>();

        File fileInstance = new File(path);
        File[] files = fileInstance.listFiles();
        Arrays.sort(files);



        for (File file : files) {
            String size = String.format("%10s", file.length());

            Long lastModified = file.lastModified();
            Date lastModifiedDate = new Date(lastModified);
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm");
            String lastModifiedDateFormatted = dateFormat.format(lastModifiedDate);

            String fileString = "";
            

            if (file.isDirectory()) {
                fileString += "d";
            } else {
                fileString = fileString + "-";
            }

            fileString += getPermissions(file);

            


            System.out.println(fileString + " " + size + " " + lastModifiedDateFormatted + " " + file.getName());
        }

    }

    private static String getPermissions(File file) {
        String permissionString = "";

        if (file.canRead()) {
            permissionString += "r";
        } else {
            permissionString +=  "-";
        }

        if (file.canWrite()) {
            permissionString += "w";
        } else {
            permissionString += "-";
        }

        if (file.canExecute()) {
            permissionString += "e";
        } else {
            permissionString += "-";
        }

        return permissionString;
    }

}