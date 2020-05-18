package prime;

import java.util.Arrays;

/**
 * 计算(1, n]之间有多少个素数
 *
 * @author wrj
 * @date 2020/5/18
 */
public class Prime {
    public static int primeCount(int n) {
        // 用一个boolean数组保存结果
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);

        // 2,3是素数，则2,3的倍数不会是素数，同理5、7类似
        for (int i = 2; i * i < n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < n; j = j + i) {
                    isPrime[j] = false;
                }
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(primeCount(100));
    }
}
