public class Stack {
    private Node top;

    public Stack() {
        this.top = null;
    }

    // Method untuk menambahkan Scene ke stack
    public void push(Scene scene) {
        Node node = new Node(scene);
        if (top == null) {
            top = node;
            return;
        }
        node.next = this.top;
        this.top = node;
    }

    // Method untuk menghapus (pop) Scene dari stack
    public Scene pop() {
        if (this.top == null) { // Mengecek apakah stack kosong
            System.out.println("Tidak ada ending terbaru.");
            return null;
        } else {
            Scene pulledScene = this.top.scene; // Simpan elemen teratas
            this.top = this.top.next;       // Hapus elemen teratas
            return pulledScene;
        }
    }

    // Method untuk melihat elemen teratas (top) tanpa menghapusnya
    public Scene peek() {
        if (this.top == null) { // Mengecek apakah stack kosong
            System.out.println("Tidak ada ending terbaru.");
            return null;
        } else {
            return this.top.scene;
        }
    }

    private class Node {
        Scene scene;
        Node next;
        public Node(Scene scene) {
            this.scene = scene;
            this.next = null;
        }
    }
}
