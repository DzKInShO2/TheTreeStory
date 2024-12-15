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
    String judulScene; //judul scene
    String isiScene; //berisi cerita 
    String pertanyaanScene; //pertanyaan untuk memilih scene selanjutnya
    String[] pilihanScene; //pilihan scene yang dapat dipilih
    int idScene; //bobot tiap scene pada tree

    public Scene (String judulScene, String isiScene, String pertanyaanScene, String[] pilihanScene, int idScene) {
        this.judulScene = judulScene;
        this.isiScene = isiScene;
        this.pertanyaanScene = pertanyaanScene;
        this.pilihanScene = pilihanScene;
        this.idScene = 0;
    }

}
