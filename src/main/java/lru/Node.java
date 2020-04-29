package lru;

/**
 * @author wrj
 * @date 2020/4/29
 */
public class Node {
    public int key, val;
    public Node next, prev;
    public Node(int k, int v) {
        this.key = k;
        this.val = v;
    }
}
