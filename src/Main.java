import java.util.Scanner;

public class Main {
    private static Scanner scanner;

    private static void clearScreen() {
        System.out.println("\033[2J\033[H");
        System.out.flush();
    }

    private static void onHoldScreen() {
        System.out.printf("Tekan [Enter] untuk lanjut...");
        scanner.nextLine();
    }

    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        Tree tree = new Tree();
        Parser parser = Parser.loadFile("res/scene.str");
        {
            Scene scene;
            while ((scene = parser.nextScene()) != null) {
                tree.insert(scene);
            }
        }
        
        while (true) {
            Scene scene = tree.getPivot();

            clearScreen();
            System.out.println(scene.opening);
            onHoldScreen();

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
                if (!scene.question.equals("END")) {
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
                            if (tree.isPivotOnRoot()) {
                                if (i == 1) tree.diveLeft();
                                else if (i == 0) tree.diveRight();
                            } else {
                                if (i == 0) tree.diveLeft();
                                else if (i == 1) tree.diveRight();
                            }
                            break;
                        }
                    } if (choice != -1) break;
                    System.out.println("Masukan yang anda masukan tidak valid.");
                }
                onHoldScreen();
            }
        }
    }
}
