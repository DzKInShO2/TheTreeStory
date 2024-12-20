import java.util.Scanner;

public class Main {
    private static Scanner scanner;

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
            clearDisplay();
            System.out.println("""
 _|_|_|_|_|  _|                      _|_|_|_|_|                                      _|_|_|    _|                                    
     _|      _|_|_|      _|_|            _|      _|  _|_|    _|_|      _|_|        _|        _|_|_|_|    _|_|    _|  _|_|  _|    _|  
     _|      _|    _|  _|_|_|_|          _|      _|_|      _|_|_|_|  _|_|_|_|        _|_|      _|      _|    _|  _|_|      _|    _|  
     _|      _|    _|  _|                _|      _|        _|        _|                  _|    _|      _|    _|  _|        _|    _|  
     _|      _|    _|    _|_|_|          _|      _|          _|_|_|    _|_|_|      _|_|_|        _|_|    _|_|    _|          _|_|_|  
                                                                                                                                 _|  
                                                                                                                             _|_|                                                                                                                                                           
                    """);

            int option = choiceFromArray(new String[]{ "Main", "Skenario", "Keluar" });
            switch (option) {
                case 0:
                    tree.surface();
                    while (true) {
                        int choice = sceneDisplay(tree.getPivot());

                        if (choice == 0) {
                            tree.diveLeft();
                        } else if (choice == 1) {
                            tree.diveRight();
                        } else {
                            break;
                        }
                    }
                    break;
                case 1:
                    while (true) {
                        clearDisplay();
                        System.out.println("Tampilkan Semua Skenario");
                        int traversal = choiceFromArray(new String[] { 
                            "In Order Traversal",
                            "Level Order Traversal",
                            "Kembali",
                        });
                        if (traversal != -1 && traversal != 2) {
                            Scene[] scenes = (traversal == 1) ? 
                                tree.traverseLevelOrder() : tree.traverseInOrder();
                            int index = 0;

                            while (true) {
                                clearDisplay();
                                System.out.printf("Scene No. %d\n", scenes[index].id);
                                System.out.println(scenes[index].title);
                                scenes[index].drawGraphics();
                                int action = choiceFromArray(new String[] {
                                    "Berikutnya",
                                    "Sebelumnya",
                                    "Kembali"
                                });

                                if (action == 0) {
                                    index++;
                                    if (index >= scenes.length) {
                                        index = 0;
                                    } 
                                } else if (action == 1) {
                                    index--;
                                    if (index < 0) {
                                        index = scenes.length - 1;
                                    } 
                                } else if (action == 2) {
                                    break;
                                } else {
                                    invalidInputDisplay();
                                }
                            }
                        } else if (traversal == 2) {
                            break;
                        } else {
                            invalidInputDisplay();
                        }
                    }
                    break;
                case 2:
                    return;
                default:
                    invalidInputDisplay();
                    break;
            }
        }
    }

    private static void invalidInputDisplay() {
        System.out.println("Masukan yang anda masukan tidak valid.");
        pauseUntilEnter();
    }

    private static void clearDisplay() {
        System.out.println("\033[2J\033[H");
        System.out.flush();
    }

    private static void pauseUntilEnter() {
        System.out.printf("Tekan [Enter] untuk lanjut...");
        scanner.nextLine();
    }

    private static int choiceFromArray(String[] choices) {
        int n = choices.length;

        System.out.println("Pilih:");
        for (int i = 0; i < n; ++i) {
            System.out.printf("  \033[9%dm%s\033[0m%s\n", i + 1,
                    choices[i].charAt(0), choices[i].substring(1));
        }

        System.out.printf("Pilihan [");
        for (int i = 0; i < n; ++i) {
            System.out.printf("%c%s", choices[i].charAt(0),
                    (i != n - 1) ? "," : "] : ");
        }

        String line = scanner.nextLine().toUpperCase();
        if (line.length() > 0) {
            for (int i = 0; i < n; ++i) {
                if (choices[i].toUpperCase().charAt(0) == line.charAt(0)) {
                    return i;
                }
            }
        }

        return -1;
    }

    private static int sceneDisplay(Scene scene) {
        clearDisplay();
        System.out.println(scene.opening);
        pauseUntilEnter();

        int choice = -1;
        while (choice == -1) {
            clearDisplay();
            System.out.println(scene.title);
            scene.drawGraphics();
            if (!scene.question.equals("END")) {
                System.out.println(scene.question);
                choice = choiceFromArray(scene.choices);
                if (choice != -1) break;
                System.out.println("Masukan yang anda masukan tidak valid.");
            } else {
                System.out.println("Selamat anda telah mendapatkan sebuah ending");
                pauseUntilEnter();
                return 2;
            }
            pauseUntilEnter();
        }
        return choice;
    }
}
