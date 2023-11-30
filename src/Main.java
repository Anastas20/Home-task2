import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Создание экземпляра H2DatabaseConnector для подключения к H2 базе данных
        DatabaseConnector connector = new H2DatabaseConnector();

        // Чтение деревьев из базы данных
        List<Tree> trees = connector.readTreesFromDatabase();

        // Подсчет общего количества листьев во всех деревьях
        int totalLeavesCount = 0;
        for (Tree tree : trees) {
            totalLeavesCount += tree.getAllLeaves().size();
        }

        // Запись результата в файл output.csv
        writeResultToCSV("output.csv", totalLeavesCount);
    }

    // Метод для записи результата в CSV файл
    private static void writeResultToCSV(String filename, int leavesCount) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.append(Integer.toString(leavesCount));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
