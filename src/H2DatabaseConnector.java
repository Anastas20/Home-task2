import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class H2DatabaseConnector implements DatabaseConnector {
    // Константы для подключения к базе данных H2
    private static final String USER = "userTree";
    private static final String PASSWORD = "pass";
    private static final String URL = "jdbc:h2:~/treeDB";

    // Реализация метода интерфейса для чтения деревьев из базы данных H2
    @Override
    public List<Tree> readTreesFromDatabase() {
        List<Tree> trees = new ArrayList<>();

        try (
                // Установление соединения с базой данных
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM TREES")
        ) {
            // Перебор результатов запроса
            while (resultSet.next()) {
                // Получение значений столбцов id и parent_id
                int nodeId = resultSet.getInt("id");
                int parentId = resultSet.getInt("parent_id");

                // Получение или создание узла с заданным id
                TreeNode node = getNode(trees, nodeId);
                if (node == null) {
                    node = new TreeNode(nodeId);
                    trees.add(new Tree(node));
                }

                // Добавление узла в список дочерних узлов его родителя
                if (parentId != nodeId) {
                    TreeNode parent = getNode(trees, parentId);
                    if (parent == null) {
                        parent = new TreeNode(parentId);
                        trees.add(new Tree(parent));
                    }
                    parent.addChild(node);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trees;
    }

    // Метод для получения узла из списка деревьев по его id
    private static TreeNode getNode(List<Tree> trees, int nodeId) {
        for (Tree tree : trees) {
            TreeNode node = tree.getRoot();
            if (node.getId() == nodeId) {
                return node;
            }
        }
        return null;
    }
}
