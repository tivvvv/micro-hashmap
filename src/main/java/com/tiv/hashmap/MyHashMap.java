package com.tiv.hashmap;

public class MyHashMap<K, V> {

    private Node<K, V>[] table = new Node[16];

    private int size = 0;

    private double factor = 0.75;

    public V put(K key, V value) {
        int keyIndex = indexOf(key);
        Node<K, V> head = table[keyIndex];
        if (head == null) {
            table[keyIndex] = new Node<>(key, value);
            size++;
            resizeIfNecessary();
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
                size++;
                resizeIfNecessary();
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
            size--;
            return head.value;
        }
        Node<K, V> pre = head;
        Node<K, V> cur = head.next;
        while (cur != null) {
            if (cur.key.equals(key)) {
                pre.next = cur.next;
                size--;
                return cur.value;
            }
            pre = pre.next;
            cur = cur.next;
        }
        return null;
    }

    public int size() {
        return size;
    }

    private void resizeIfNecessary() {
        if (size < table.length * factor) {
            return;
        }
        Node<K, V>[] newTable = new Node[table.length * 2];
        for (Node<K, V> head : table) {
            if (head == null) {
                continue;
            }
            Node<K, V> cur = head;
            while (cur != null) {
                int newIndex = cur.key.hashCode() % newTable.length;
                Node<K, V> next = cur.next;
                if (newTable[newIndex] == null) {
                    cur.next = null;
                    newTable[newIndex] = cur;
                    cur = next;
                } else {
                    cur.next = newTable[newIndex];
                    newTable[newIndex] = cur;
                    cur = next;
                }
            }
        }
        table = newTable;
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
