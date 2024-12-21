public class StringList {
    private class Node {
        public Node next;
        public String string;

        public Node(String string) {
            this.string = string;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public StringList() {
        head = null;
        size = 0;
    }

    public void append(String string) {
        size++;
        Node node = new Node(string);

        if (head == null) {
            head = node;
            return;
        }

        Node curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = node;
    }

    public String[] toArray() {
        String[] strings = new String[size];
        Node curr = head;
        for (int i = 0; curr != null; ++i) {
            strings[i] = curr.string;
            curr = curr.next;
        }
        return strings;
    }

    public void sort() {
        if (head == null || head.next == null) {
            return;
        }

        boolean swapped;
        Node current;
        Node last = null;

        do {
            swapped = false;
            current = head;

            while (current.next != last) {
                if (current.string.length() > 0 && current.next.string.length() > 0) {
                    if (current.string.charAt(0) > current.next.string.charAt(0)) {
                        String temp = current.string;
                        current.string = current.next.string;
                        current.next.string = temp;

                        swapped = true;
                    }
                }
                current = current.next;
            }
            last = current; 
        } while (swapped);
    }
}
