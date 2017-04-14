package Synchronized;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xuch.
 */
public class LRUCache<K, V> {

    private final Map<K, V> cache;
    private final int maxEntries;
    private final ReadWriteLock readWriteLock;
    private final Lock readLock;
    private final Lock writeLock;

    public LRUCache(int maxEntries) {
        this.maxEntries = maxEntries;
        this.cache = new LinkedHashMap<K, V>(this.maxEntries, 0.75F, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > maxEntries;
            }
        };
        this.readWriteLock = new ReentrantReadWriteLock(true);
        this.readLock = readWriteLock.readLock();
        this.writeLock = readWriteLock.writeLock();
    }

    public void put(K key, V value) {
        writeLock.lock();
        try {
            cache.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public V get(K key) {
        readLock.lock();
        try {
            return cache.get(key);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public String toString() {
        return cache.toString();
    }

    public static void main(String[] args) {
        LRUCache<String, String> cache = new LRUCache<>(3);
        cache.put("a", "1");
        cache.put("b", "1");
        cache.put("a", "2");
        cache.put("c", "1");
        cache.put("d", "1");
        System.out.println(cache);
    }
}
