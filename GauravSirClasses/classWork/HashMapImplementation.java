
class HashMapImplementation<Key, Value> {
    private Node<Key,Value>[] st =(Node<Key,Value>[]) new Node[32];
    private int size =0;
    private float lf= 0.75f;

    private int hash(Key Key) {
        return (Key.hashCode() & Integer.MAX_VALUE) % st.length;
    }

    public Value get(Key Key) {
        int n = this.hash(Key);
        Node node = this.st[n];
        while (node != null) {
            if (node.key.equals(Key)) {
                return node.value;
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
	size++;
	if((float)(size /st.length)>lf){
	  rehash();
	}
    }
    private void rehash(){
      Node[] old = st;
      st = (Node<Key,Value>[]) new Node[old.length*2];
      size = 0;
      for(int i = 0; i<old.length; i++){
        for(Node x = old[i]; x!=null; x=x.next){
	  put(x.key,x.value);
	}
      }
    }

    class Node<K,V> {
        K key;
        V value;
        Node<K,V> next;

        Node(Key key, Value value, Node node) {
            this.key = key;
            this.value = value;
            this.next = node;
        }
    }
}
