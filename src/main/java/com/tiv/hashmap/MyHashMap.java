package com.tiv.hashmap;

public class MyHashMap<K, V> {

    private Node<K, V>[] table = new Node[16];

    public V put(K key, V value) {
        int keyIndex = indexOf(key);
        Node<K, V> head = table[keyIndex];
        if (head == null) {
            table[keyIndex] = new Node<>(key, value);
            return null;
        }
        while (true) {
            if (head.key.equals(key)) {
                V oldValue = head.value;
                head.value = value;
                return oldValue;
            }
            if (head.next == null) {
                head.next = new Node<>(key, value);
                return null;
            }
            head = head.next;
        }
    }

    public V get(K key) {
        int keyIndex = indexOf(key);
        Node<K, V> head = table[keyIndex];
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public V remove(K key) {
        int keyIndex = indexOf(key);
        Node<K, V> head = table[keyIndex];
        if (head == null) {
            return null;
        }
        if (head.key.equals(key)) {
            table[keyIndex] = head.next;
            return head.value;
        }
        Node<K, V> pre = head;
        Node<K, V> cur = head.next;
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
