import java.util.Scanner;

public class Main {
    private static Scanner scanner;

    private static void clearScreen() {
        System.out.println("\033[2J\033H");
        System.out.flush();
    }

    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        Parser parser = Parser.loadFile("res/scene.str");
        Scene scene = parser.nextScene();

        while (true) {
            clearScreen();
            System.out.println(scene.opening);
            scanner.nextLine();

            int choice = -1;
            while (choice == -1) {
                clearScreen();
                System.out.println(scene.title);
                System.out.printf("+--------------------------------+\n");
                for (int i = 0; i < scene.graphics.length; ++i) {
                    System.out.printf("|");
                    System.out.printf(scene.graphics[i]);
                    System.out.printf("|\n");
                }
                System.out.printf("+--------------------------------+\n");
                System.out.println(scene.question);
                System.out.println("Pilih:");
                for (int i = 0; i < 2; ++i) {
                    System.out.printf("  \033[9%dm%s\033[0m%s\n", i + 1,
                            scene.choices[i].charAt(0), scene.choices[i].substring(1));
                }

                System.out.printf("Pilihan (masukan huruf pertama pilihan) : ");
                String line = scanner.nextLine();
                char c = '\0';
                if (line.length() > 0) c = line.charAt(0);
                for (int i = 0; i < 2; ++i) {
                    if (c == scene.choices[i].charAt(0)) {
                        choice = i;
                    }
                }
            }
        }
    }
}
