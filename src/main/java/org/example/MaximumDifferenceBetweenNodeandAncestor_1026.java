package org.example;

public class MaximumDifferenceBetweenNodeandAncestor_1026 {
    private static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
    int maxDifference = Integer.MIN_VALUE;


    public int maxAncestorDiff(TreeNode root) {
        recursiveInOrderTree(root, root.val, root.val);
        return this.maxDifference;
    }

    void recursiveInOrderTree(TreeNode root, int maxParent, int minParent) {


        // Ask for absolute difference and compare it.
        if (root != null && root.left != null) {
            int absoluteMaxLeft = Math.abs(maxParent - root.left.val);
            int absoluteMinLeft = Math.abs(minParent - root.left.val);
            int absolute = Math.max(absoluteMinLeft, absoluteMaxLeft);

            maxDifference = Math.max(this.maxDifference, absolute);

            int possibleMaxAncestor = Math.max(maxParent, root.left.val);
            int possibleMinAncestor = Math.min(minParent, root.left.val);
            recursiveInOrderTree(root.left, possibleMaxAncestor, possibleMinAncestor);
        }

        if (root != null && root.right != null) {
            int absoluteMaxLeft = Math.abs(maxParent - root.right.val);
            int absoluteMinLeft = Math.abs(minParent - root.right.val);
            int absolute = Math.max(absoluteMinLeft, absoluteMaxLeft);

            maxDifference = Math.max(this.maxDifference, absolute);

            int possibleMaxAncestor = Math.max(maxParent, root.right.val);
            int possibleMinAncestor = Math.min(minParent, root.right.val);
            recursiveInOrderTree(root.right, possibleMaxAncestor, possibleMinAncestor);
        }

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(0);
        root.right.right.left = new TreeNode(3);

        MaximumDifferenceBetweenNodeandAncestor_1026 sol = new MaximumDifferenceBetweenNodeandAncestor_1026();
        System.out.println(sol.maxAncestorDiff(root));


    }
}
