import java.io.File;

public class Cd {

    public String cd(String[] arguments, String currentPath) {
        String updatePath = "";

        if (arguments.length > 2) {
            System.out.println("Whoa, too many arguments there bub. Try just one, or none (to browse to home directory).");
        } else if (arguments.length == 1 || arguments[1].equals("~") || arguments[1].equals("~/")) {
            updatePath = System.getProperty("user.home");
        } 
        else {
            if (arguments[1].startsWith("~/")) {
                String[] removedHomeRelative = arguments[1].split("~/");
                updatePath = String.valueOf(System.getProperty("user.home")) + removedHomeRelative[0];
            } else if (arguments[1].startsWith("/")) {
                updatePath = arguments[1];
            } else if (arguments[1].equals("..") || arguments[1].equals("../")) {
                File currentFile = new File(currentPath);
                updatePath = currentFile.getParent();
            } else {
                updatePath = currentPath + "/" + arguments[1];
            }
        }

        if (validatePath(updatePath)) {
            return updatePath;
        } else {
            System.out.println("Sorry, but the specified path does not exist.");
            return currentPath;
        }
    }

    public boolean validatePath(String path) {
        File returnedFile = new File(path);

        if (returnedFile.exists()) {
            return true;
        } else {
            return false;
        }
    }
}