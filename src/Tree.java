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
    private Node root;
    public Scene[] traversePreOrder() {
        int size = countNodes(root);
        Scene[] scenes = new Scene[size];
        traversePreOrder(root, scenes, 0);
        return scenes;
    }
    
    private int traversePreOrder(Node node, Scene[] scenes, int index) {
        if (node != null) {
            scenes[index++] = node.scene; 
            index = traversePreOrder(node.next, scenes, index);
            index = traversePreOrder(node.prev, scenes, index);
        }
        return index;
    }
    public Scene[] traverseInOrder() {
        int size = countNodes(root);
        Scene[] scenes = new Scene[size];
        traverseInOrder(root, scenes, 0);
        return scenes;
    }

    private int traverseInOrder(Node node, Scene[] scenes, int index) {
        if (node != null) {
            index = traverseInOrder(node.next, scenes, index);
            scenes[index++] = node.scene; 
            index = traverseInOrder(node.prev, scenes, index);
        }
        return index;
    }

    private int countNodes(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.next) + countNodes(node.prev);
    }
}


