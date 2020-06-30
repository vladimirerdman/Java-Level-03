package iChat;

import java.util.Map;
import java.util.TreeMap;

public class Cens {
    private static final Map<String, String> words = new TreeMap<>();
    {
        words.put("россия", "Россия");
        words.put("росия", "Россия");
        words.put("расия", "Россия");
        words.put("russia", "Russia");
        words.put("rusia", "Russia");
        words.put("bitch", "b***h");
        words.put("дурак", "д***к");
        words.put("мудак", "м***к");
        words.put("punk", "p**k");
        words.put("asshole", "a*****e");
    }

    public Map getCens() {
        return words;
    }
}