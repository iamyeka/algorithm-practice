package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wrj
 * @date 2020/9/24
 */
public class Algorithm {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     * 示例:
     * <p>
     * 给定 nums = [2, 7, 11, 15], target = 9
     * <p>
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int tmp = target - num;
            if (map.containsKey(tmp) && map.get(tmp) != i) {
                return new int[]{map.get(tmp), i};
            }

            map.put(num, i);
        }

        throw new RuntimeException("not available");

    }

    /**
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }


    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n < 1) {
            throw new RuntimeException("error");
        }
        ListNode dummy = new ListNode();
        ListNode pre = dummy;

        dummy.next = head;
        ListNode fast = head;
        ListNode slow = head;

        // 快指针先走N步，然后慢指针开始走，当快指针遍历到最后一个节点，慢指针停在倒数第N个节点的前一个节点，方便做删除操作
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            pre = pre.next;
            fast = fast.next;
            slow = slow.next;
        }

        pre.next = slow.next;

        return dummy.next;

    }

    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     *
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     *
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        List<Integer> num1List = new ArrayList<>();
        List<Integer> num2List = new ArrayList<>();

        while (l1 != null) {
            num1List.add(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            num2List.add(l2.val);
            l2 = l2.next;
        }

        int size = Math.max(num1List.size(), num2List.size());
        List<Integer> list = new ArrayList<>();

        boolean addOne = false;
        for (int i = 0; i < size; i++) {
            int val1 = i < num1List.size() ? num1List.get(i) : 0;
            int val2 = i < num2List.size() ? num2List.get(i) : 0;

            int total = addOne ? (val1 + val2 + 1) : (val1 + val2);

            if (total >= 10) {
                total -= 10;
                addOne = true;
            } else {
                addOne = false;
            }

            list.add(total);
        }

        if (addOne) {
            list.add(1);
        }

        ListNode head = null;
        for (int i = list.size() - 1; i >= 0; i--) {
            ListNode tmp = new ListNode(list.get(i));
            tmp.next = head;
            head = tmp;
        }

        return head;

    }

    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     *
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     *
     * 注意：给定 n 是一个正整数。
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n < 3) {
            return n;
        }
        int[] dp = new int[n + 1];

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];

    }

    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm();

        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(4);
        ListNode n3 = new ListNode(3);

        n1.next = n2;
        n2.next = n3;

        ListNode n4 = new ListNode(5);
        ListNode n5 = new ListNode(6);
        ListNode n6 = new ListNode(4);

        n4.next = n5;
        n5.next = n6;

        ListNode listNode = algorithm.addTwoNumbers(n1, n4);

        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

}
