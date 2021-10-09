import java.io.File;

public class Mdir {

    public static void mdir(String[] command, String currentDirectory) {
        String absolutePath = "";

        if (command.length > 2) {
            System.out.println("Too Many argumnents provided");
        } else if (command.length == 1) {
            System.out.println("Too few arguments provided");
        } else {
            if (command[1].startsWith("/")) {
                absolutePath = command[1];
            } else if (command[1].startsWith("~/")) {
                String[] fromHomePath = command[1].split("~/", 1);

                absolutePath = String.valueOf(System.getProperty("user.home")) + fromHomePath[1];
            } else {
                absolutePath = currentDirectory + "/" + command[1];
            }
        }

        File requestedFile = new File(absolutePath);
        if (requestedFile.exists() || requestedFile.isDirectory()) {
            System.out.println("A folder by this name already exists");
        } else {
            requestedFile.mkdirs();
        }
    }
}