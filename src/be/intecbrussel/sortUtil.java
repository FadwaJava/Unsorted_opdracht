package be.intecbrussel;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class sortUtil {
    public static void creation(Path sorted_folder, Path summary, Path summaryFile) {
        try {

            if (Files.notExists(sorted_folder) && Files.notExists(summary)) {
                Files.createDirectories(summary);
                Files.createFile(summaryFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public static void sortAndCopy (Object[]paths,Path sorted_folder) throws IOException {

        for (Object p:paths) {

            String filePath = p.toString();
            String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
            String extension = filePath.substring(filePath.lastIndexOf(".") + 1);
            Path newPath = sorted_folder.resolve(extension);


            if (Files.isHidden(Paths.get(filePath))) {
                newPath = sorted_folder.resolve("hidden");
            }
            if (Files.notExists(newPath)) {
                Files.createDirectory(newPath);
            }
            while((Files.notExists((Path)p))){
            Files.copy(Paths.get(filePath), newPath.resolve(fileName), REPLACE_EXISTING);
        }
        }
    }
        public static void writingSummary(Object[]newPaths, Path summaryFile) {

            try (FileWriter outputStream = new FileWriter(summaryFile.toString())) {

                outputStream.append ("-----------------------------SUMMARY--------------------------\n");
                outputStream.append ("File ------------------------|Readable----------------|Writable \n");

                for (Object n : newPaths) {
                    String newDirPath = n.toString();
                    if (newDirPath.contains("\\")) {
                        String dirName = newDirPath.substring(newDirPath.lastIndexOf("\\") + 1);
                        outputStream.append("\n" + dirName + " : \n--------- \n");
                        Object[] newFilesPaths = Files.walk((Path) n).toArray();
                        for (Object newf : newFilesPaths) {
                            String newFilePath = newf.toString();
                            if (newFilePath.contains(".")) {
                                String newFileName = newFilePath.substring(newFilePath.lastIndexOf("\\") + 1);
                                outputStream.write(newFileName);
                                if (Files.isHidden((Path)newf))
                                    outputStream.write("\t\t|X \t\t|/ \n");
                                    else outputStream.write("\t\t|X \t\t|X \n");
                                }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}



