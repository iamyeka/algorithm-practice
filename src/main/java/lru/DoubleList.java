package lru;

import java.util.LinkedList;

/**
 * @author wrj
 * @date 2020/4/29
 */
public class DoubleList {

    private Node first;
    private Node last;
    private int size = 0;

    // 在链表头部添加节点 x，时间 O(1)
    public void addFirst(Node x) {
        final Node f = first;
        x.next = f;
        first = x;

        // 如果是第一个节点
        if (f == null) {
            last = x;
        } else {
            // 不是第一个节点
            f.prev = x;
        }
        size++;
    }

    // 删除链表中的 x 节点(x 一定存在)
    // 由于是双链表且给的是目标 Node 节点，时间 O(1)
    // 因为是LRU，node不会出现重复，直接操作前后节点指针就可以了
    public void remove(Node node) {
        for (Node x = first; x != null; x = x.next) {
            if (node.key == x.key) {
                final Node next = x.next;
                final Node prev = x.prev;

                // 删除的是头节点
                if (prev == null) {
                    first = next;
                } else {
                    // 重新连接前节点的下一点为自己的下一节点
                    prev.next = next;
                    x.prev = null;
                }

                // 删除的是尾节点
                if (next == null) {
                    last = prev;
                } else {
                    // 重新连接后节点的前一点为自己的前一节点
                    next.prev = prev;
                    x.next = null;
                }

                size--;
                return;
            }
        }
    }

    // 删除链表中最后一个节点，并返回该节点，时间 O(1)
    public Node removeLast() {
        final Node _last = last;
        final Node prev = last.prev;
        // 帮助触发gc
        last.prev = null;
        last = prev;
        // 只有一个节点，则将first也置为null
        if (prev == null)
            first = null;
        else {
            prev.next = null;
        }

        size--;
        return _last;
    }

    // 返回链表⻓度，时间 O(1)
    public int size() {
        return size;
    }
}
