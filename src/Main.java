import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scan = new Scanner(System.in);

        String path = "C:/temp";

        File theDir = new File(path);
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
        System.out.print("write the name for the file: ");
        path += String.format("/%s.csv",scan.nextLine());
        System.out.println();

        boolean overwrite = false;
        if (new File(path).exists()) {
            System.out.print("The file already exists, do you want to overwrite it (y/n)? ");
            overwrite = scan.nextLine().toLowerCase().charAt(0) == 'y';
        }
        System.out.println();

        try (BufferedWriter br = new BufferedWriter(new FileWriter(path, !overwrite))) {

            boolean conti = false;
            do{
                System.out.print("Value: ");
                String task = scan.nextLine();

                br.write(String.format("%s;Not complete",task));
                br.newLine();

                System.out.print("Enter y for exit program: ");
                conti = !(scan.nextLine().toLowerCase().charAt(0) == 'y');
                System.out.println();
            }while (conti);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Your document is ready!");
        System.out.println("path for archive: " + path);
        System.out.print("want to open the file? (y/n)");
        boolean open = scan.nextLine().toLowerCase().charAt(0) == 'y';
        if(open){
            try {
                Desktop.getDesktop().open(new File(path));
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}