import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

/**
 * Created by Jeremy on 3/14/16.
 */
public class SymbolTable<Key extends Comparable<Key>, Value> implements Iterable<Key> {

    private TreeMap<Key, Value> st;

    public SymbolTable() {
        st = new TreeMap<Key, Value>();
    }

    public Value get(Key key) {
        if (key == null)
            throw new NullPointerException("called get() with null key");
        return st.get(key);
    }

    public void put(Key key, Value val) {
        if (key == null)
            throw new NullPointerException("called put() with null key");
        if (val == null)
            st.remove(key);
        else
            st.put(key, val);
    }

    public void delete(Key key) {
        if (key == null) throw new NullPointerException("called delete() with null key");
        st.remove(key);
    }

    public boolean contains(Key key) {
        if (key == null) throw new NullPointerException("called contains() with null key");
        return st.containsKey(key);
    }

    public int size() {
        return st.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Iterable<Key> keys() {
        return st.keySet();
    }

    public Iterator<Key> iterator() {
        return st.keySet().iterator();
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return st.firstKey();
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return st.lastKey();
    }

    public Key ceiling(Key key) {
        if (key == null) throw new NullPointerException("called ceiling() with null key");
        Key k = st.ceilingKey(key);
        if (k == null) throw new NoSuchElementException("all keys are less than " + key);
        return k;
    }

    public Key floor(Key key) {
        if (key == null) throw new NullPointerException("called floor() with null key");
        Key k = st.floorKey(key);
        if (k == null) throw new NoSuchElementException("all keys are greater than " + key);
        return k;
    }

    public static void main(String[] args) {
        SymbolTable<String, Integer> st = new SymbolTable<String, Integer>();
        st.put("S", 0);
        st.put("D", 2);
        st.put("V", 34);
        st.put("W", 43);
        st.put("A", 54);
        st.put("E", 10);
        st.put("L", 65);
        st.put("P", 4);
        for (String s : st.keys())
            System.out.println(s + " " + st.get(s));
    }
}
