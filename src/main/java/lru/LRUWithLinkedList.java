package lru;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

/**
 * @author wrj
 * @date 2020/9/25
 */
public class LRUWithLinkedList {

    private int capacity;
    private Map<Integer, Node> map = new HashMap<>();
    private LinkedList<Node> linkedList = new LinkedList<>();

    public LRUWithLinkedList(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        int val = map.get(key).val;
        // 提到开头
        put(key, val);
        return val;
    }

    public void put(int key, int value) {
        // 节点已存在，将节点提到开头，最后更新map
        Node node = new Node(key, value);
        if (map.containsKey(key)) {
            linkedList.remove(node);
            linkedList.addFirst(node);
            return;
        }

        // 节点不存在，但是空间已经满了，删除最后一个节点
        if (linkedList.size() == capacity) {
            Node last = linkedList.removeLast();
            map.remove(last.key);
        }

        // 将节点加到开头，并更新map
        linkedList.addFirst(node);
        map.put(key, node);
    }

    class Node {
        public int key, val;
        public Node(int k, int v) {
            this.key = k;
            this.val = v;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return key == node.key;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }

    public static void main(String[] args) {
        LRUWithLinkedList LRUWithLinkedList = new LRUWithLinkedList(2);
        LRUWithLinkedList.put(1, 1);
        LRUWithLinkedList.put(2, 2);
        System.out.println(LRUWithLinkedList.get(1));
        LRUWithLinkedList.put(3, 3);
        System.out.println(LRUWithLinkedList.get(2));
        LRUWithLinkedList.put(4, 4);
        System.out.println(LRUWithLinkedList.get(1));
        System.out.println(LRUWithLinkedList.get(3));
        System.out.println(LRUWithLinkedList.get(4));
    }
}
