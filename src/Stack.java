public class Stack {
    public Scene[] stack; // Array untuk menyimpan Scene
    public int top;       

    
    public SceneStack(int size) {
        this.stack = new Scene[size]; // Ukuran array ditentukan saat pembuatan stack
        this.top = -1; 
    }

    // Method untuk menambahkan Scene ke stack
    public void push(Scene scene) {
        if (top == stack.length - 1) {
            System.out.println("Stack is full! Cannot push.");
        } else {
            stack[++top] = scene;
        }
    }

    // Method untuk menghapus (pop) Scene dari stack
    public Scene pull() {
        if (top == -1) { // Mengecek apakah stack kosong
            System.out.println("Stack is empty! Cannot pull.");
            return null;
        } else {
            return stack[top--];
        }
    }

    // Method untuk mendapatkan jumlah elemen dalam stack
    public int size() {
        return top + 1;
    }
}
