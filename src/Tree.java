import java.util.ArrayList;
import java.util.List;


class Tree {
    private TreeNode root;

    public Tree(TreeNode root) {
        this.root = root;
    }

    public TreeNode getRoot() {
        return root;
    }

    // Возвращает список всех узлов дерева
    public List<TreeNode> getAllNodes() {
        List<TreeNode> allNodes = new ArrayList<>();
        traverseTree(root, allNodes);
        return allNodes;
    }

    // Возвращает список всех листов дерева
    public List<TreeNode> getAllLeaves() {
        List<TreeNode> leaves = new ArrayList<>();
        findLeaves(root, leaves);
        return leaves;
    }

    private void traverseTree(TreeNode node, List<TreeNode> nodeList) {
        // Рекурсивно добавляет все узлы в список
        nodeList.add(node);
        for (TreeNode child : node.getChildren()) {
            traverseTree(child, nodeList);
        }
    }

    private void findLeaves(TreeNode node, List<TreeNode> leaves) {
        // Рекурсивно находит все листья в дереве
        if (node.isLeaf()) {
            leaves.add(node);
        } else {
            for (TreeNode child : node.getChildren()) {
                findLeaves(child, leaves);
            }
        }
    }
}