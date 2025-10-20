class Solution {
    public int finalValueAfterOperations(String[] operations) {
        //If the second index is +, simply add one to our result else reduce one from it.
        return java.util.Arrays.stream(operations).mapToInt(op->op.charAt(1) == '+' ? 1 : -1).sum();
    }
}
