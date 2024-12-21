import java.util.Scanner;

public class Main {
    private static Scanner scanner;
    private static Stack endingStack;

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

        endingStack = new Stack();

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
                            "Ending Terbaru",
                            "Cari Skenario",
                            "In Order Traversal",
                            "Level Order Traversal",
                            "Kembali",
                        });

                        if (traversal == 0) {
                            while (true) {
                                clearDisplay();
                                System.out.println("Ending Terbaru\n");
                                if (endingStack.peek() != null) {
                                    endingStack.peek().drawGraphics();
                                }

                                int opt = choiceFromArray(new String[] {
                                    "Hapus Ending Terbaru",
                                    "Kembali"
                                });

                                if (opt == 0) {
                                    endingStack.pop();
                                } else if (opt == 1) {
                                    break;
                                } else {
                                    invalidInputDisplay();
                                }
                            }
                        } else if (traversal == 1) {
                            Scene[] scenes = tree.traverseLevelOrder();
                            int idx = -1;

                            while (true) {
                                clearDisplay();
                                System.out.println("Skenario");
                                if (idx != -1) {
                                    System.out.printf("Scene No. %d\n", scenes[idx].id);
                                    System.out.printf("Judul : %s\n", scenes[idx].title);
                                    System.out.printf("Pembukaan : %s\n", scenes[idx].opening);
                                    scenes[idx].drawGraphics();
                                }
                                int act = choiceFromArray(new String[] {
                                    "Cari Scene Berdasarkan Id",
                                    "Kembali"
                                });

                                if (act == 0) {
                                    System.out.printf("Masukan Id Scene: ");
                                    String line = scanner.nextLine();
                                    if (line.length() > 0) {
                                        int sceneId = Integer.parseInt(line);

                                        idx = searchSceneById(scenes, sceneId);
                                    }
                                } else if (act == 1) {
                                    break;
                                } else {
                                    invalidInputDisplay();
                                }
                            }
                        } else if (traversal != 1 && traversal != 4) {
                            Scene[] scenes = (traversal == 2) ? 
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
                        } else if (traversal == 4) {
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

    private static int searchSceneById(Scene[] scenes, int sceneId) {
        for (int i = 0; i < scenes.length; ++i) {
            if (scenes[i].id == sceneId) {
                return i;
            }
        }
        return -1;
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
                endingStack.push(scene.clone());
                pauseUntilEnter();
                return 2;
            }
            pauseUntilEnter();
        }
        return choice;
    }
}
