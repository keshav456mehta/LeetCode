class Solution {
    static class Node {
        char firstChar, lastChar;
        int maxLen, prefixLen, suffixLen;
        int length;

        public Node(char c) {
            this.firstChar = c;
            this.lastChar = c;
            this.maxLen = 1;
            this.prefixLen = 1;
            this.suffixLen = 1;
            this.length = 1;
        }

        public Node() {}
    }

    private Node[] tree;
    private char[] chars;

    private Node merge(Node left, Node right) {
        Node res = new Node();
        res.length = left.length + right.length;
        res.firstChar = left.firstChar;
        res.lastChar = right.lastChar;

        // Merge prefix length
        res.prefixLen = left.prefixLen;
        if (left.prefixLen == left.length && left.lastChar == right.firstChar) {
            res.prefixLen = left.length + right.prefixLen;
        }

        // Merge suffix length
        res.suffixLen = right.suffixLen;
        if (right.suffixLen == right.length && left.lastChar == right.firstChar) {
            res.suffixLen = right.length + left.suffixLen;
        }

        // Merge max length
        res.maxLen = Math.max(left.maxLen, right.maxLen);
        if (left.lastChar == right.firstChar) {
            res.maxLen = Math.max(res.maxLen, left.suffixLen + right.prefixLen);
        }

        return res;
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(chars[start]);
            return;
        }
        int mid = start + (end - start) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int start, int end, int idx, char c) {
        if (start == end) {
            chars[idx] = c;
            tree[node] = new Node(c);
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, c);
        } else {
            update(2 * node + 1, mid + 1, end, idx, c);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int k = queryIndices.length;
        chars = s.toCharArray();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            update(1, 0, n - 1, queryIndices[i], queryCharacters.charAt(i));
            ans[i] = tree[1].maxLen;
        }

        return ans;
    }
}