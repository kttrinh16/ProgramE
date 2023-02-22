import static java.util.Objects.hash;

//from https://math.hws.edu/eck/cs124/javanotes4/c12/ex-12-3-answer.html used under fair academic guidelines
/**************************************************
 *Hash table class implements a hash table
 * which maps keys to values. Any non-null
 * object can be used as a key or as a value.
 * To successfully store and retrieve objects
 * from a hash table, the objects used as keys
 * must implement the hasCode method and the equals
 * method
 *
 *
 *
 **************************************************/
public class HashTable {

    static private class ListNode {
        Object key;
        Object value;
        ListNode next;

    }

    private ListNode[] table;
    private int count;

    public HashTable() {
        table = new ListNode[64];
    }

    public HashTable(int initialSize) {
        table = new ListNode[initialSize];
    }


    /**
     * assigns the unique key and the value to the element in the hash table
     * @param key
     * @param value
     */
    public void put(Object key, Object value) {
        int bucket = hash(key);
        ListNode list = table[bucket];

        while (list != null) {
            if (list.key.equals(key))
                break;
            list = list.next;
        }

        if (list != null) {
            list.value = value;
        } else {
            if (count >= 0.75 * table.length) {
                resize();
            }
            ListNode newNode = new ListNode();
            newNode.key = key;
            newNode.value = value;
            newNode.next = table[bucket];
            table[bucket] = newNode;
            count++;
        }
    }

    /**
     * getter for the key in the hash table
     * @param key
     * @return value
     */
    public Object get(Object key) {
        int bucket = hash(key);
        ListNode list = table[bucket];
        while (list != null) {
            if (list.key.equals(key))
                return list.value;
        }
        return null;
    }

    /**
     * remove from the hash table
     * @param key
     */
    public void remove(Object key) {
        int bucket = hash(key);
        if (table[bucket] == null) {
            return;
        }
        if (table[bucket].key.equals(key)) {
            table[bucket] = table[bucket].next;
            count--;
            return;
        }

        ListNode prev = table[bucket];

        ListNode curr = prev.next;

        while (curr != null && !curr.key.equals(key)) {
            curr = curr.next;
            prev = curr;
        }

        if (curr != null) {
            prev.next = curr.next;
            count--;
        }
    }

    /**
     * checks to see if there is a unique key
     * @param key
     * @return boolean
     */
    public boolean containsKey(Object key) {
        int bucket = hash(key);
        ListNode list = table[bucket];
        while (list != null) {
            if (list.key.equals(key))
                return true;
            list = list.next;
        }
        return false;
    }

    /**
     * gets the size of the hash table
     * @return size
     */
    public int size() {
        return count;
    }

    /**
     * retrieves the hash table key
     * @param key
     * @return
     */
    private int hash(Object key) {
        return (Math.abs(key.hashCode())) % table.length;
    }

    /**
     * changes the size of the hash table
     */
    private void resize() {
        ListNode[] newtable = new ListNode[table.length * 2];
        for (int i = 0; i < table.length; i++) {
            ListNode list = table[i];
            while (list != null) {
                ListNode next = list.next;
                int hash = (Math.abs(list.key.hashCode())) % newtable.length;
                list.next = newtable[hash];
                newtable[hash] = list;
                list = next;
            }
        }
        table = newtable;
    }
}
