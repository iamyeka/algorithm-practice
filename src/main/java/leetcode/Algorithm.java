package leetcode;

import java.util.HashMap;
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

}
