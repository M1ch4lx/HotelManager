package pl.edu.agh.utils;

import java.util.*;
import java.util.stream.Collectors;

public class MyMap<K, V> implements Map<K, V> {

    private ArrayList<LinkedList<Entry<K, V>>> entries;

    private final float RESIZE_THRESHOLD = 0.75f;

    private final int INITIAL_CAPACITY = 8;

    private int entriesCount;

    private int capacity;

    public MyMap() {
        clear();
    }

    private ArrayList<LinkedList<Entry<K, V>>> buildHashTable(int tableCapacity) {
        var hashTable = new ArrayList<LinkedList<Entry<K, V>>>();
        for(var i = 0; i < tableCapacity; i++) {
            hashTable.add(new LinkedList<>());
        }
        return hashTable;
    }

    private void resize(int newCapacity) {
        var entriesToPut = entrySet();

        entries = buildHashTable(newCapacity);

        entriesToPut.forEach(
                entry -> put(entry.getKey(), entry.getValue()));

        capacity = newCapacity;
    }

    private void resizeIfThresholdReached() {
        if((int)(capacity * RESIZE_THRESHOLD) <= entriesCount) {
            resize(capacity * 2);
        }
    }

    private int findKeyIndex(Object key) {
        return key.hashCode() % entries.size();
    }

    private LinkedList<Entry<K, V>> findListForKey(Object key) {
        return entries.get(findKeyIndex(key));
    }

    private Optional<Entry<K, V>> findEntry(Object key, LinkedList<Entry<K, V>> list) {
        for(var entry : list) {
            if(entry.getKey().equals(key)) {
                return Optional.of(entry);
            }
        }
        return Optional.empty();
    }

    private Optional<Entry<K, V>> findEntry(Object key) {
        return findEntry(key, findListForKey(key));
    }

    @Override
    public int size() {
        return entriesCount;
    }

    @Override
    public boolean isEmpty() {
        return entriesCount == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return findEntry(key).isPresent();
    }

    @Override
    public boolean containsValue(Object value) {
        return entries.stream()
                .flatMap(Collection::stream)
                .anyMatch(entry -> entry.getValue().equals(value));
    }

    @Override
    public V get(Object key) {
        return findEntry(key).map(Entry::getValue).orElse(null);
    }

    @Override
    public V put(K key, V value) {
        var list = findListForKey(key);

        findEntry(key, list).ifPresentOrElse(entry -> {
            entry.setValue(value);
        }, () -> {
            list.add(Map.entry(key, value));
            entriesCount++;
            resizeIfThresholdReached();
        });

        return value;
    }

    @Override
    public V remove(Object key) {
        var list = findListForKey(key);
        var entry = findEntry(key, list);
        if(entry.isPresent()) {
            list.remove(entry.get());
            entriesCount--;
            return entry.get().getValue();
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for(var entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        entriesCount = 0;
        capacity = INITIAL_CAPACITY;
        entries = buildHashTable(INITIAL_CAPACITY);
    }

    @Override
    public Set<K> keySet() {
        return entries.stream()
                .flatMap(Collection::stream)
                .map(Entry::getKey)
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<V> values() {
        return entries.stream()
                .flatMap(Collection::stream)
                .map(Entry::getValue)
                .distinct()
                .toList();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return entries.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }
}
