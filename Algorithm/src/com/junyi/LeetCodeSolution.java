package com.junyi;

import javafx.util.Pair;
import org.junit.platform.commons.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.incrementExact;
import static java.lang.Math.max;
import static java.lang.Math.min;



public class LeetCodeSolution {

    public List<String> fullJustify(String[] words, int maxWidth) {
        ArrayList<String> res = new ArrayList<>();
        int i = 0;
        int n = words.length;
        while (i < n) {
            // j指向下一个单词，cnt累计字符数量，每行至少能够放置一个单词
            int j = i+1;
            int cnt = words[i].length();
            while (j < n && cnt + words[j].length() + 1 <= maxWidth) {
                cnt += words[j++].length() + 1;
            }
            String tmp = resolveLine(words, i, j-1, cnt, maxWidth);
            res.add(tmp);
            i = j;
        }
        return res;
    }

    private String resolveLine(String[] words, int l, int r, int length, int maxWidth) {
        // 单词个数
        int countWord = r - l + 1;
        // 空格数量
        int countSpace = maxWidth - length + countWord - 1;
        // 平均每个单词之间空格的数量
        int avgSpace = countWord == 1 ? 0 : countSpace/(countWord - 1);
        // 空格数量不均匀，前面的 extraSpace 个空格位置需要多放置一个空格
        int extraSpace = countWord == 1 ? 0 : countSpace % (countWord-1);
        StringBuilder sb = new StringBuilder();
        // 处理最后一行或者单行一个单词
        if (r == words.length-1 || l == r) {
            while (l <= r) {
                sb.append(words[l++]).append(" ");
            }
            // 由于上面每个单词后面都跟了一个空字符，如果行末是非空字符，即单词刚刚好，那么我们就多添加了一个空字符，需要删除
            if (sb.length() > maxWidth) {
                sb.deleteCharAt(sb.length()-1);
            }
            sb.append(generateEmpty(maxWidth-sb.length()));
            return sb.toString();
        }
        int start = l;
        while (l < r) {
            int k = (l-start) < extraSpace ? 1 : 0;
            sb.append(words[l++]);
            sb.append(generateEmpty(avgSpace + k));
        }
        sb.append(words[l]);
        return sb.toString();
    }

    /** 生成k个空字符(' ')的字符串 */
    private String generateEmpty(Integer k) {
        char[] chars = new char[k];
        Arrays.fill(chars, ' ');
        return String.valueOf(chars);
    }



    public static void main(String[] argv) {
        LeetCodeSolution lcs = new LeetCodeSolution();
        String[] arr = new String[]{"What","must","be","acknowledgment","shall","be"};
        List<String> res = lcs.fullJustify(arr, 16);
        System.out.println(res);
    }
}



