/**
* Class: 			CS-3100
* Description: 		Assignment 3. Interactive Shell.
* @author			Stephen Beckstrand
*/

import java.util.Scanner;
import java.nio.file.Path;
import java.io.File;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


public class Assn3Main {

    
    

	/** Main Function. This starts the loop of listning for input and sends the request to the appropriate place. */
	public static void main(String[] args) {
        
        CurrentPath currentDirectory = new CurrentPath();
        currentDirectory.setPath(new File("").getAbsolutePath());

        History history = new History();
        Ptime ptime = new Ptime();
		
        while(true) {
            /** Get current working directory, print it and then request input after */
            Scanner scan = new Scanner(System.in);

            System.out.print(String.format("[%s]: ", currentDirectory.getPath()));

            String commandInput = scan.nextLine();

            /** Input handling */
            history.add(commandInput);

            String[] splitInput = splitCommand(commandInput);
                        
            executor(splitInput, history, currentDirectory, ptime);
        }
	}

    

    private static void executor(String[] command, History history, CurrentPath currentDirectory, Ptime ptime) {

        if (command.length == 0) {
            return;
        }

        else if (command[0].equals("exit")) {
                System.exit(0);
        } 
        
        else if (command[0].equals("history")) {
            history.get();
        } 

        else if (command[0].equals("^")) {
            if (command.length > 2) {
                System.out.println("Too many arguments provided");
            } else if (command.length == 1) {
                executor(history.get(0), history, currentDirectory, ptime);
            } else {
                try {
                    int historyIndex = Integer.parseInt(command[1]);
                    executor(history.get(historyIndex - 1), history, currentDirectory, ptime);
                } catch(Exception e) { 
                    System.out.println("Please provide a valid index for command in history");
                }    
            }
            
        }
        
        else if (command[0].equals("list")) {
            ListCommand.list(currentDirectory.getPath());
        } 
        
        else if (command[0].equals("cd")) {
            Cd cdOperator = new Cd();

            currentDirectory.setPath(cdOperator.cd(command, currentDirectory.getPath()));
        }

        else if (command[0].equals("mdir")) {
            Mdir mdirExecutor = new Mdir();

            mdirExecutor.mdir(command, currentDirectory.getPath());
        }

        else if (command[0].equals("rdir")) {
            Rdir rdirExecutor = new Rdir();

            rdirExecutor.rdir(command, currentDirectory.getPath());
        }

        else if (command[0].equals("ptime")) {
            ptime.getTime();
        }

        else {
            try {
                File currentDirectoryFile = new File(currentDirectory.getPath());

                if (command[command.length -1 ].endsWith("&")){
                    List<String> commandList = new ArrayList<String>(Arrays.asList(command));
                    commandList.remove("&");
                    command = commandList.toArray(new String[0]);
                    ProcessBuilder newProcessBuilder = new ProcessBuilder(command).directory(currentDirectoryFile);
                    Process newProcess = newProcessBuilder.start();
                } else {
                    ProcessBuilder newProcessBuilder = new ProcessBuilder(command).inheritIO().directory(currentDirectoryFile);
                    long startTime = System.currentTimeMillis();
                    Process newProcess = newProcessBuilder.start();
                    int processStatus = newProcess.waitFor();
                    long endTime = System.currentTimeMillis();

                    long difference = endTime - startTime;
                    ptime.updateTime(difference);

                }

            } catch (Exception e) {
                System.out.println("Sorry, that command does not seem to exist in our program or via your System. ");
            }
        }
    }


    /**
    * Split the user command by spaces, but preserving them when inside double-quotes.
    * This was not written by me but instead provided as part of the Assignment description. 
    * Code Adapted from: https://stackoverflow.com/questions/366202/regex-for-splitting-a-string-using-space-when-not-surrounded-by-single-or-double
    */
    public static String[] splitCommand(String command) {
        java.util.List<String> matchList = new java.util.ArrayList<>();

        Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
        Matcher regexMatcher = regex.matcher(command);
        while (regexMatcher.find()) {
            if (regexMatcher.group(1) != null) {
                // Add double-quoted string without the quotes
                matchList.add(regexMatcher.group(1));
            } else if (regexMatcher.group(2) != null) {
                // Add single-quoted string without the quotes
                matchList.add(regexMatcher.group(2));
            } else {
                // Add unquoted word
                matchList.add(regexMatcher.group());
            }
        }

        return matchList.toArray(new String[matchList.size()]);
    }


}