/**
 * Created by Jeremy on 3/15/16.
 */
public class FrequencyCounter {
    public static void main(String[] args) {

        int distinct = 0, words = 0;
        int minlen = Integer.parseInt(args[0]);
        SymbolTable<String, Integer> st = new SymbolTable<>();

        // compute frequency counts
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            if (key.length() < minlen) continue;
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key)+1);
            }
            else {
                st.put(key, 1);
                distinct++;
            }
        }

        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max))
                max = word;
        }

        System.out.println(max + " " + st.get(max));
        System.out.println("distinct = " + distinct);
        System.out.println("words    = " + words);
    }
}
