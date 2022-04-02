import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solutions {

    /* Problem 117. Populating Next Right Pointers in Each Node II  #Medium#
     * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
     *  Problem description:
     *  Given a binary tree.
     *  Populate each next pointer to point to its next right node.
     *  If there is no next right node, the next pointer should be set to NULL.
     *  Initially, all next pointers are set to NULL.
     */
    public Node connect(Node root) {
        if (root == null) return root;
        Queue<Node> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            int levelSize = que.size();
            for (int i = 0; i < levelSize; i++) {
                Node currentNode = que.poll();
                Node nextNode = null;
                if (i < levelSize - 1) {
                    nextNode = que.peek();
                }
                currentNode.next = nextNode;
                if (currentNode.left != null) que.offer(currentNode.left);
                if (currentNode.right != null) que.offer(currentNode.right);
            }
        }
        return root;
    }

    /* Problem 1382. Balance a Binary Search Tree  #Medium#
     * https://leetcode.com/problems/balance-a-binary-search-tree/
     *  Problem description:
     *  Given the root of a binary search tree, return a balanced binary search tree with the same node values.
     *  If there is more than one answer, return any of them.
     *  A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.
     */

    public TreeNode balanceBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }
        List<Integer> values = new ArrayList<>();
        getValuesInorder(root, values);
        TreeNode balancedTreeRoot = buildBalancedTree(values, 0, values.size() - 1);
        return balancedTreeRoot;
    }

    private List<Integer> getValuesInorder(TreeNode root, List<Integer> values) {
        if (root == null) return values;
        getValuesInorder(root.left, values);
        values.add(root.val);
        getValuesInorder(root.right, values);
        return values;
    }

    private TreeNode buildBalancedTree(List<Integer> values, int lowIndex, int highIndex) {
        if (lowIndex > highIndex) return null;
        int midIndex = (lowIndex + highIndex) / 2;
        TreeNode root = new TreeNode(values.get(midIndex));
        root.left = buildBalancedTree(values, lowIndex, midIndex - 1);
        root.right = buildBalancedTree(values, midIndex + 1, highIndex);
        return root;
    }
}
