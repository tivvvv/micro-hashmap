package com.tiv.hashmap;

import java.util.ArrayList;

public class MyHashMap<K, V> {

    ArrayList<Node<K, V>> table = new ArrayList<>();

    public V put(K key, V value) {
        for (Node<K, V> node : table) {
            if (node.key.equals(key)) {
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
        }
        Node<K, V> newNode = new Node<>(key, value);
        table.add(newNode);
        return null;
    }

    public V get(K key) {
        for (Node<K, V> node : table) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    public V remove(K key) {
        for (int i = 0; i < table.size(); i++) {
            Node<K, V> node = table.get(i);
            if (node.key.equals(key)) {
                Node<K, V> removed = table.remove(i);
                return removed.value;
            }
        }
        return null;
    }

    class Node<K, V> {

        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
