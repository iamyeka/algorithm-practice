package lru;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author wrj
 * @date 2020/4/29
 *
 * 查询和插入操作必须是O(1)，需要用到map这种结构
 * 需要保证顺序，空间不够时需要删除节点，需要O(1)内完成，需要用到链表这种结构，JDK：LinkedList基本满足这种数据结构
 */
public class LRUCache {
    private Map<Integer, Node> map;
    private DoubleList cache;
    private List<Node> linkedList = new LinkedList<>();
    // 最大容量
    private int cap;

    public LRUCache(int capacity) {
        this.cap = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    public int get(int key) {
        /*if (key 不存在) {
            return -1; } else {
            将数据 (key, val) 提到开头;
            return val; }*/

        if (!map.containsKey(key)) {
            return -1;
        }

        int val = map.get(key).val;
        // 利用 put 方法把该数据提前
        put(key, val);
        return val;
    }

    public void put(int key, int val) {
        /*if (key 已存在) {
            把旧的数据删除;
            将新节点 x 插入到开头; } else {
            if (cache 已满) {
                删除链表的最后一个数据腾位置;
                删除 map 中映射到该数据的键; }
            将新节点 x 插入到开头;
            map 中新建 key 对新节点 x 的映射; }*/
        Node x = new Node(key, val);
        if (map.containsKey(key)) {
            // 删除旧节点
            cache.remove(x);
            // 将新节点提到最前
            cache.addFirst(x);
            // 添加新节点到map中
            map.put(key, x);
            return;
        }

        // 不是重复节点，则插入到头，但要判断容量
        if (cap == cache.size()) {
            // 删除链表最后一个数据
            Node last = cache.removeLast();
            map.remove(last.key);
        }

        // 直接添加到头部
        cache.addFirst(x);
        // 保存map的映射
        map.put(key, x);
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        // 初始化缓存
        // 当前缓存结构【{1, 1}, {2, 2}】
        cache.put(1, 1);
        cache.put(2, 2);

        // 缓存{3, 3}，则将最近最少使用的删掉，删掉1
        // 当前缓存结构【{3, 3}, {2, 2}】
        cache.put(3, 3);
        // i应该等于-1，1作为尾节点需要被删除
        int i = cache.get(1);
        assert i == -1;

        // 再次访问2，将{2, 2}提到开头
        // 当前缓存结构【{2, 2}, {3, 3}】
        cache.get(2);

        // 缓存{4, 4}，将最近最少使用的删掉，删掉3
        // i应该等于-1，1作为尾节点需要被删除
        cache.put(4, 4);
        int i1 = cache.get(3);
        assert i1 == -1;
    }

}
