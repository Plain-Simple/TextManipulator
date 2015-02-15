package TextManipulator;

import java.io.*;
import java.util.Scanner;

class CLI {
    public Scanner scanner = new Scanner(System.in);
    public String text;
    public void StartCLI(){
        System.out.println("Welcome to the Plain+Simple text manipulator (CLI mode)\n\n");
        LoadFile();
        /* we need a way of catching errors if LoadFile fails) */
        System.out.println("Please enter a function or enter \"help\" for a list of possible operations.\n");
        String userInput = scanner.nextLine();
        switch(userInput) { /*
            case "addprefix": run();
                break;
            case "wassup": run();
                break;
            case "wassup": run();
                break;
            case "wassup": run();
                break;
            case "wassup": run();
                break;
            case "wassup": run();
                break;
            case "wassup": run();
                break;
            case "wassup": run();
                break;
            case "wassup": run();
                break;
            case "wassup": run();
                break;
            case "wassup": run();
                break;
            case "wassup": run();
                break;
            case "wassup": run();
                break;
            case "wassup": run();
                break;
            case "wassup": run();
                break;
            case "wassup": run();
                break;
            case "wassup": run();
                break;
            case "wassup": run();
                break;*/
        }
    }
    public void LoadFile() {
        System.out.println("Please enter the file name of the text file you would like to manipulate: ");
        Scanner scanner = new Scanner(System.in);
        String file_path = scanner.nextLine();
        /* import file_path text file into string called text */
        text = new Scanner(file_path).useDelimiter("\\Z").next();
    }
    /* the following functions are to be written! */

    public void ModifySettings() {

    }
}
