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

     public void insertOrderTraversal(Scene scene) {
        Node newNode = new Node(scene);
        if (root == null) {
            root = newNode;
        } else {
            insertOrderTraversal(root, newNode);
        }
    }

    private void insertOrderTraversal(Node current, Node newNode) {
        if (newNode.scene.idScene < current.scene.idScene) {
            if (current.next == null) {
                current.next = newNode;
            } else {
                insertOrderTraversal(current.next, newNode);
            }
        } else {
            if (current.prev == null) {
                current.prev = newNode;
            } else {
                insertOrderTraversal(current.prev, newNode);
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

            if (node.next != null) {
                queue.enqueue(node.next);
            }
            if (node.prev != null) {
                queue.enqueue(node.prev);
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

}


