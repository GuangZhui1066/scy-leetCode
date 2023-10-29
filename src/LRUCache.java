import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

class LRUCache {

    private int capacity;
    HashMap<Integer, Integer> cache;
    List<Integer> usedList;


    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        usedList = new LinkedList();
    }

    public int get(Integer key) {
        Integer value = cache.get(key);
        if (value == null) {
            return -1;
        } else {
            moveToEnd(key);
            return value;
        }
    }

    public void put(int key, int value) {
        if (cache.get(key) != null) {
            moveToEnd(key);
        } else if (cache.size() < capacity) {
            usedList.add(key);
        } else {
            Integer expiredKey = usedList.get(0);
            usedList.remove(expiredKey);
            cache.remove(expiredKey);
            usedList.add(key);
        }
        cache.put(key, value);
    }

    void moveToEnd(Integer key) {
        usedList.remove(key);
        usedList.add(key);
    }
}
