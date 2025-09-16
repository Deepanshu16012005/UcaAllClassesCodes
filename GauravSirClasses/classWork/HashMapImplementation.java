/*
 * Decompiled with CFR 0.152.
 */
class HashMapImplementation<Key, Value> {
    private int M = 32;
    private Node[] st = new Node[this.M];

    HashMapImplementation() {
    }

    private int hash(Key Key) {
        return (Key.hashCode() & Integer.MAX_VALUE) % 32;
    }

    public Value get(Key Key) {
        int n = this.hash(Key);
        Node node = this.st[n];
        while (node != null) {
            if (node.key.equals(Key)) {
                return (Value)node.value;
            }
            node = node.next;
        }
        return null;
    }

    public void put(Key Key, Value Value) {
        int n = this.hash(Key);
        Node node = this.st[n];
        while (node != null) {
            if (node.key.equals(Key)) {
                node.value = Value;
            }
            node = node.next;
        }
        this.st[n] = new Node(Key, Value, this.st[n]);
    }

    static class Node {
        private Object key;
        private Object value;
        private Node next;

        Node(Object object, Object object2, Node node) {
            this.key = object;
            this.value = object2;
            this.next = node;
        }
    }
}
