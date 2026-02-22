package Algorithms.Greedy;

/*
===========================================================
Greedy – Classic Problems
===========================================================
Topics Covered:
 1) Fractional Knapsack
 2) Huffman Encoding (frequency-based)
 3) Minimum Coins (coin change greedy)
 4) Task Scheduler
 5) Assign Cookies
 6) Hand of Straights
 7) Partition Labels
 8) Candy Distribution
===========================================================
*/

import java.util.*;

public class GreedyClassics {

    // =========================================================================
    // 1️⃣  FRACTIONAL KNAPSACK  –  maximize value with weight capacity
    // =========================================================================

    static class Item { int weight, value; Item(int w, int v){ weight=w; value=v; } }

    public static double fractionalKnapsack(Item[] items, int capacity) {
        Arrays.sort(items, (a, b) -> Double.compare((double)b.value/b.weight, (double)a.value/a.weight));
        double maxValue = 0;
        for(Item it : items) {
            if(capacity >= it.weight) { maxValue += it.value; capacity -= it.weight; }
            else { maxValue += (double) it.value / it.weight * capacity; break; }
        }
        return maxValue;
    }

    // =========================================================================
    // 2️⃣  HUFFMAN ENCODING  –  build optimal prefix-free codes
    // =========================================================================

    static class HuffNode implements Comparable<HuffNode> {
        char ch; int freq; HuffNode left, right;
        HuffNode(char c, int f){ ch=c; freq=f; }
        HuffNode(int f, HuffNode l, HuffNode r){ freq=f; left=l; right=r; }
        public int compareTo(HuffNode o){ return this.freq - o.freq; }
    }

    public static HuffNode buildHuffman(char[] chars, int[] freqs) {
        PriorityQueue<HuffNode> pq = new PriorityQueue<>();
        for(int i = 0; i < chars.length; i++) pq.add(new HuffNode(chars[i], freqs[i]));
        while(pq.size() > 1) {
            HuffNode l = pq.poll(), r = pq.poll();
            pq.add(new HuffNode(l.freq + r.freq, l, r));
        }
        return pq.poll();
    }

    public static void printCodes(HuffNode root, String code) {
        if(root == null) return;
        if(root.left == null && root.right == null) { System.out.println(root.ch + ": " + code); return; }
        printCodes(root.left,  code + "0");
        printCodes(root.right, code + "1");
    }

    // =========================================================================
    // 3️⃣  MINIMUM COINS (Greedy – works for canonical coin systems)
    // =========================================================================

    public static int minCoinsGreedy(int[] coins, int amount) {
        Arrays.sort(coins);  // sort ascending
        int count = 0;
        for(int i = coins.length - 1; i >= 0 && amount > 0; i--) {
            count += amount / coins[i];
            amount %= coins[i];
        }
        return amount == 0 ? count : -1;
    }

    // =========================================================================
    // 4️⃣  TASK SCHEDULER  –  min time to finish all tasks with cooldown n
    // =========================================================================

    public static int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for(char t : tasks) freq[t - 'A']++;
        Arrays.sort(freq);
        int maxFreq = freq[25];
        int idleSlots = (maxFreq - 1) * n;
        for(int i = 24; i >= 0 && freq[i] > 0; i--)
            idleSlots -= Math.min(freq[i], maxFreq - 1);
        return tasks.length + Math.max(0, idleSlots);
    }

    // =========================================================================
    // 5️⃣  ASSIGN COOKIES  –  maximize content children
    // =========================================================================

    public static int findContentChildren(int[] greed, int[] size) {
        Arrays.sort(greed); Arrays.sort(size);
        int child = 0, cookie = 0;
        while(child < greed.length && cookie < size.length) {
            if(size[cookie] >= greed[child]) child++;
            cookie++;
        }
        return child;
    }

    // =========================================================================
    // 6️⃣  HAND OF STRAIGHTS  –  can divide cards into groups of size W?
    // =========================================================================

    public static boolean isNStraightHand(int[] hand, int groupSize) {
        if(hand.length % groupSize != 0) return false;
        TreeMap<Integer, Integer> count = new TreeMap<>();
        for(int h : hand) count.merge(h, 1, Integer::sum);
        while(!count.isEmpty()) {
            int first = count.firstKey();
            for(int i = 0; i < groupSize; i++) {
                if(!count.containsKey(first + i)) return false;
                count.merge(first + i, -1, Integer::sum);
                if(count.get(first + i) == 0) count.remove(first + i);
            }
        }
        return true;
    }

    // =========================================================================
    // 7️⃣  PARTITION LABELS  –  max partitions so each letter in at most one part
    // =========================================================================

    public static List<Integer> partitionLabels(String s) {
        int[] last = new int[26];
        for(int i = 0; i < s.length(); i++) last[s.charAt(i) - 'a'] = i;
        List<Integer> result = new ArrayList<>();
        int start = 0, end = 0;
        for(int i = 0; i < s.length(); i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if(i == end) { result.add(end - start + 1); start = i + 1; }
        }
        return result;
    }

    // =========================================================================
    // 8️⃣  CANDY DISTRIBUTION  –  min candies so each child with higher rating gets more
    // =========================================================================

    public static int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);
        // left to right
        for(int i = 1; i < n; i++)
            if(ratings[i] > ratings[i-1]) candies[i] = candies[i-1] + 1;
        // right to left
        for(int i = n - 2; i >= 0; i--)
            if(ratings[i] > ratings[i+1]) candies[i] = Math.max(candies[i], candies[i+1] + 1);
        int total = 0;
        for(int c : candies) total += c;
        return total;
    }

    // =========================================================================
    // MAIN
    // =========================================================================
    public static void main(String[] args) {
        Item[] items = {new Item(10, 60), new Item(20, 100), new Item(30, 120)};
        System.out.printf("Fractional Knapsack: %.2f%n", fractionalKnapsack(items, 50)); // 240.0

        System.out.println("\n=== Huffman Codes ===");
        char[] chars = {'a','b','c','d','e','f'};
        int[] freqs  = {5, 9, 12, 13, 16, 45};
        HuffNode root = buildHuffman(chars, freqs);
        printCodes(root, "");

        System.out.println("\nMin coins (canonical): " + minCoinsGreedy(new int[]{1,5,10,25}, 36));

        System.out.println("Task Scheduler:        " + leastInterval(new char[]{'A','A','A','B','B','B'}, 2)); // 8

        System.out.println("Assign Cookies:        " + findContentChildren(new int[]{1,2,3}, new int[]{1,1})); // 1

        System.out.println("Hand of Straights:     " + isNStraightHand(new int[]{1,2,3,6,2,3,4,7,8}, 3)); // true

        System.out.println("Partition Labels:      " + partitionLabels("ababcbacadefegdehijhklij"));

        System.out.println("Candy:                 " + candy(new int[]{1,0,2})); // 5
    }
}

/*
===========================================================
Complexity Summary
===========================================================
Operation              | Time        | Space
-----------------------|-------------|------
Fractional Knapsack    | O(n log n)  | O(1)
Huffman Encoding       | O(n log n)  | O(n)
Task Scheduler         | O(n)        | O(1)
Assign Cookies         | O(n log n)  | O(1)
Hand of Straights      | O(n log n)  | O(n)
Partition Labels       | O(n)        | O(1)
Candy Distribution     | O(n)        | O(n)
===========================================================
*/
