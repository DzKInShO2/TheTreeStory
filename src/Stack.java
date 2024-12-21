public class Stack {
<<<<<<< HEAD
    public Scene top; 
    public Stack next; 

    public Stack() {
        this.top = null;
        this.next = null;
=======
    public Scene[] stack; // Array untuk menyimpan Scene
    public int top;       
    
    public SceneStack(int size) {
        this.stack = new Scene[size]; // Ukuran array ditentukan saat pembuatan stack
        this.top = -1; 
>>>>>>> e42b701 (tambah beberapa opsi di menu skenario)
    }

    // Method untuk menambahkan Scene ke stack
    public void push(Scene scene) {
        Stack newNode = new Stack(); // Membuat node baru
        newNode.top = scene;         // Menyimpan Scene di node baru
        newNode.next = this.next;    // Menghubungkan node baru ke stack lama
        this.next = newNode;         // Node baru menjadi elemen pertama
    }

    // Method untuk menghapus (pop) Scene dari stack
    public Scene pop() {
        if (this.next == null) { // Mengecek apakah stack kosong
            System.out.println("Scene is empty! Cannot pull.");
            return null;
        } else {
            Scene pulledScene = this.next.top; // Simpan elemen teratas
            this.next = this.next.next;       // Hapus elemen teratas
            return pulledScene;
        }
    }

    // Method untuk melihat elemen teratas (top) tanpa menghapusnya
    public Scene peek() {
        if (this.next == null) { // Mengecek apakah stack kosong
            System.out.println("Scene is empty! Cannot peek.");
            return null;
        } else {
            return this.next.top;
        }
    }
}