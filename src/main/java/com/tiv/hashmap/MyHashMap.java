package com.tiv.hashmap;

public class MyHashMap<K, V> {

    private Node<K, V>[] table = new Node[16];

    public V put(K key, V value) {
        int keyIndex = indexOf(key);
        Node<K, V> node = table[keyIndex];
        if (node == null) {
            table[keyIndex] = new Node<>(key, value);
            return null;
        }
        while (true) {
            if (node.key.equals(key)) {
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
            if (node.next == null) {
                node.next = new Node<>(key, value);
                return null;
            }
            node = node.next;
        }
    }

    public V get(K key) {

        return null;
    }

    public V remove(K key) {

        return null;
    }

    public int size() {
        return 0;
    }

    private int indexOf(Object key) {
        return key.hashCode() % table.length;
    }

    class Node<K, V> {

        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
