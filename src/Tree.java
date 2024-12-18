/*
 * Kelas <Tree> ini merupakan implementasi tipe data
 * Tree
 */
public class Tree {
    private Node root;
    private Node pivot;

    public Tree() {
        root = null;
        pivot = null;
    }

    public boolean isPivotOnRoot() {
        return pivot.equals(root);
    }

    public Scene getPivot() {
        return pivot.scene;
    }

    public void surface() {
        pivot = root;
    }

    public void diveRight() {
        if (pivot.right != null)
            pivot = pivot.right;
    }

    public void diveLeft() {
        if (pivot.left != null)
            pivot = pivot.left;
    }

    public Scene[] traversePreOrder() {
        int size = countNodes(root);
        Scene[] scenes = new Scene[size];
        traversePreOrder(root, scenes, 0);
        return scenes;
    }
    
    private int traversePreOrder(Node node, Scene[] scenes, int index) {
        if (node != null) {
            scenes[index++] = node.scene; 
            index = traversePreOrder(node.left, scenes, index);
            index = traversePreOrder(node.right, scenes, index);
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
            index = traverseInOrder(node.left, scenes, index);
            scenes[index++] = node.scene; 
            index = traverseInOrder(node.right, scenes, index);
        }
        return index;
    }

    private int countNodes(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

     public void insert(Scene scene) {
        Node newNode = new Node(scene);
        if (root == null) {
            root = newNode;
            pivot = root;
        } else {
            insert(root, newNode);
        }
    }

    private void insert(Node current, Node newNode) {
        if (newNode.scene.id < current.scene.id) {
            if (current.left == null) {
                current.left = newNode;
            } else {
                insert(current.left, newNode);
            }
        } else {
            if (current.right == null) {
                current.right = newNode;
            } else {
                insert(current.right, newNode);
            }
        }
    }

    public Scene[] traverseLevelOrder() {
        int size = countNodes(root);
        Scene[] scenes = new Scene[size];
        if (root == null) {
            return scenes;
        }

        Queue queue = new Queue(size);
        queue.enqueue(root);
        int index = 0;

        while (!queue.isEmpty()) {
            Node node = queue.dequeue();
            scenes[index++] = node.scene;

            if (node.left != null) {
                queue.enqueue(node.left);
            }
            if (node.right != null) {
                queue.enqueue(node.right);
            }
        }
        return scenes;
    }

    private static class Queue {
        private Node[] elements;
        private int front;
        private int rear;
        private int size;
        private int capacity;

        public Queue(int capacity) {
            this.capacity = capacity;
            elements = new Node[capacity];
            front = 0;
            rear = -1;
            size = 0;
        }

        public void enqueue(Node node) {
            if (size == capacity) {
                throw new IllegalStateException("Queue is full");
            }
            rear = (rear + 1) % capacity;
            elements[rear] = node;
            size++;
        }

        public Node dequeue() {
            if (isEmpty()) {
                throw new IllegalStateException("Queue is empty");
            }
            Node node = elements[front];
            front = (front + 1) % capacity;
            size--;
            return node;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    /*
     * Kelas <Node> ini digunakan sebagai node
     * yang akan digunakan oleh kelas Tree
     */
    private class Node {
        Node left, right;
        Scene scene;

        public Node(Scene scene) {
            this.left = null;
            this.right = null;
            this.scene = scene;
        }
    }
}
