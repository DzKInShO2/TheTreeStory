public class SceneList {
    private class Node {
        public Node next;
        public Node prev;
        public Scene scene;

        public Node(Scene scene) {
            this.prev = this.next = null;
            this.scene = scene;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public SceneList() {
        this.head = this.tail = null;
        this.size = 0;
    }

    public void append(Scene scene) {
        Node node = new Node(scene);
        if (head == null) {
            this.head = node;
            this.tail = node;
            return;
        }

        node.prev = tail;
        tail.next = node;
        tail = node;
        size++;
    }

    public int size() {
        return size;
    }

    public Scene search(int id) {
        Node curr = head;
        while (curr != null) {
            if (id == curr.scene.id) {
                return curr.scene;
            }
            curr = curr.next;
        }
        return null;
    }

    public Scene get(int location) {
        if (location >= size) {
            return null;
        }

        Node curr = head;
        for (int i = 0; i < location; ++i) {
            curr = curr.next;
        }

        return curr.scene;
    }
}
