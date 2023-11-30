import java.util.ArrayList;
import java.util.List;


class TreeNode {
    private int id;
    private List<TreeNode> children;
    private TreeNode parent;

    public TreeNode(int id) {
        this.id = id;
        this.children = new ArrayList<>();
        this.parent = null;
    }

    // Получить id
    public int getId() {
        return id;
    }

    // Получить родительский узел
    public TreeNode getParent() {
        return parent;
    }

    // Получить список всех дочерних узлов
    public List<TreeNode> getChildren() {
        return children;
    }

    //  Является ли узел листом
    public boolean isLeaf() {
        return children.isEmpty();
    }

    // Является ли узел корнем
    public boolean isRoot() {
        return parent == null;
    }

    // Добавить узел
    public void addChild(TreeNode child) {
        children.add(child);
        child.parent = this;
    }
}