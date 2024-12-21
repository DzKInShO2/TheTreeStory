/*
 * Digunakan untuk menyimpan data berkaitan tentang 
 * Scene dimana data tersebut dapat berupa judul,
 * isi, pertanyaan(pertanyaan di atas pilihan) dan dua pilihan
 *
 * Pilihan dalam Scene akan diurutkan pada konstruktor sebelum
 * disimpan.
 */


 // Membuat scene yang berisi judul, isi, pertanyaan, pilihan, dan id
public class Scene {
    public int id; //bobot tiap scene pada tree
    public String title; // judul scene
    public String[] graphics; // Gambar dalam scene
    public String opening; // berisi cerita atau pembukaan scene
    public String question; //pertanyaan untuk memilih scene selanjutnya
    public String[] choices; //pilihan scene yang dapat dipilih

    public Scene (int id, String judulScene, String[] gambarScene, 
                  String isiScene, String pertanyaanScene, String[] pilihanScene) {
        this.id = id;
        this.title = judulScene;
        this.graphics = gambarScene;
        this.opening = isiScene;
        this.question = pertanyaanScene;

        int n = pilihanScene.length;
        if (n > 0 && 
            pilihanScene[0].length() > 0 &&
            pilihanScene[1].length() > 0) {
            for (int i = 0; i < n - 1; ++i) {
                for (int j = 0; j < n - i - 1; ++j) {
                    if (pilihanScene[j].charAt(0) > pilihanScene[j + 1].charAt(0)) {
                        String temp = pilihanScene[j];
                        pilihanScene[j] = pilihanScene[j + 1];
                        pilihanScene[j + 1] = temp;
                    }
                }
            }
        }
        this.choices = pilihanScene;
    }

    public void drawGraphics() {
        System.out.printf("╔════════════════════════════════╗\n");
        for (int i = 0; i < graphics.length; ++i) {
            System.out.printf("║");
            System.out.printf(graphics[i]);
            System.out.printf("║\n");
        }
        System.out.printf("╚════════════════════════════════╝\n");
    }

    public Scene clone() {
        return new Scene(id, title, graphics, opening, question, choices);
    }
}
