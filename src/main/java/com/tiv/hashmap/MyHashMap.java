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
        int keyIndex = indexOf(key);
        Node<K, V> node = table[keyIndex];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public V remove(K key) {
        int keyIndex = indexOf(key);
        Node<K, V> node = table[keyIndex];
        if (node == null) {
            return null;
        }
        if (node.key.equals(key)) {
            table[keyIndex] = node.next;
            return node.value;
        }
        Node<K, V> pre = node;
        Node<K, V> cur = node.next;
        while (cur != null) {
            if (cur.key.equals(key)) {
                pre.next = cur.next;
                return cur.value;
            }
            pre = pre.next;
            cur = cur.next;
        }
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
        Node<K, V> pre;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
