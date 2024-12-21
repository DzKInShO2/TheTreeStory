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

    public SceneList traverseInOrder() {
        SceneList scenes = new SceneList();
        traverseInOrder(root, scenes, 0);
        return scenes;
    }

    private int traverseInOrder(Node node, SceneList scenes, int index) {
        if (node != null) {
            index = traverseInOrder(node.left, scenes, index);
            scenes.append(node.scene);
            index = traverseInOrder(node.right, scenes, index);
        }
        return index;
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

    public SceneList traverseLevelOrder() {
        SceneList scenes = new SceneList();
        if (root == null) {
            return scenes;
        }

        Queue queue = new Queue();
        queue.enqueue(root);
        int index = 0;

        while (!queue.isEmpty()) {
            Node node = queue.dequeue();
            scenes.append(node.scene);

            if (node.left != null) {
                queue.enqueue(node.left);
            }
            if (node.right != null) {
                queue.enqueue(node.right);
            }
        }
        return scenes;
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

    private class Queue {
        private QueueNode front;
        private QueueNode back;

        public Queue() {
            this.front = null;
            this.back = null;
        }

        public boolean isEmpty() {
            return this.front == null;
        }

        public void enqueue(Node node) {
            QueueNode q = new QueueNode(node);
            if (this.front == null) {
                this.front = q;
                this.back = q;
                return;
            }

            back.next = q;
            back = back.next;
        }

        public Node dequeue() {
            if (isEmpty()) {
                throw new IllegalStateException("Queue is empty");
            }

            QueueNode q = front;
            front = front.next;
            return q.node;
        }

        private class QueueNode {
            public QueueNode next;
            public Node node;

            public QueueNode(Node node) {
                this.node = node;
                this.next = null;
            }
        }
    }
}
