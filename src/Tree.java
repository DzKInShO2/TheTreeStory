/*
 * Kelas <Node> ini digunakan sebagai node
 * yang akan digunakan oleh kelas Tree
 */
class Node {
    Node next, prev;
    Scene scene;

    public Node(Scene scene) {
        this.next = null;
        this.prev = null;
        this.scene = scene;
    }
}

/*
 * Kelas <Tree> ini merupakan implementasi tipe data
 * Tree
 */
public class Tree {

}
