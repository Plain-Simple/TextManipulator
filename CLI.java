import java.io.*;
import java.util.Scanner;

class CLI {
    public String text;
    public void StartCLI(){
        System.out.println("Welcome to the Plain+Simple text manipulator (CLI mode)\n\n");
        LoadFile();
        /* we need a way of catching errors if LoadFile fails) */
        System.out.println("Please select an option: \n\n");
        System.out.println("    1. Manipulate Text\n");
        System.out.println("    2. Analyze Text\n");
        System.out.println("    3. Modify Settings\n");
        System.out.println("Enter option number: ");
        Scanner scanner = new Scanner(System.in);
        switch(scanner.nextInt()) {
            case 1: ManipulateText();
                break;
            case 2: AnalyzeText();
                break;
            case 3: ModifySettings();
                break;
            /* add default case! */
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
    public void ManipulateText() {

    }
    public void AnalyzeText() {

    }
    public void ModifySettings() {

    }
}
