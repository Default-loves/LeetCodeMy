package com.junyi;

import javafx.util.Pair;
import org.junit.platform.commons.util.StringUtils;

import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.incrementExact;
import static java.lang.Math.max;
import static java.lang.Math.min;



public class LeetCodeSolution {

    private static final Integer row = 9;


    public String largestNumber(int[] cost, int target) {
        String[][] dp = new String[row][target+1];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= target; i++) {
            if (i % cost[0] == 0) {
                sb.append("1");
                dp[0][i] = sb.toString();
            } else {
                dp[0][i] = "";
            }
        }
        dp[0][0] = "";
        for (int i = 1; i < row; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i-1][j];
                if (j == cost[i]) {
                    dp[i][j] = i+1+"";
                }
                if (j > cost[i] && "".compareTo(dp[i][j - cost[i]]) != 0) {
                    dp[i][j] = max(dp[i-1][j], i + 1 + dp[i][j-cost[i]]);
                }
            }

        }
        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        String res = dp[row - 1][target];
        return "".equals(res)? "0": res;
    }

    /**
     * 计算两个字符串，数字最大的字符串
     */
    private String max(String a, String b) {
        if (a.length() > b.length()) {
            return a;
        } else if (a.length() < b.length()) {
            return b;
        }
        return a.compareTo(b) > 0? a: b;
    }

    public static void main(String[] argv) {
        LeetCodeSolution lcs = new LeetCodeSolution();
        System.out.println(1+2+"34");
    }
}




