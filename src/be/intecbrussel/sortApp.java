package be.intecbrussel;
import java.io.IOException;
import java.nio.file.*;
public class sortApp {


    public static void main(String[] args) {

        Path unsorted_folder = Paths.get("unsorted");
        Path sorted_folder = Paths.get("sorted_folder");
        Path summary = sorted_folder.resolve("summary");
        Path summaryFile = summary.resolve("summary.txt");


        try {
            sortUtil.creation(sorted_folder, summary, summaryFile);
            Object[] paths = Files.walk(unsorted_folder).filter(e -> !Files.isDirectory(e)).toArray();
            sortUtil.sortAndCopy(paths, sorted_folder);
            Object []newPaths = Files.walk(sorted_folder).filter(e -> Files.isDirectory(e)).toArray();
            sortUtil.writingSummary(newPaths, summaryFile);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
