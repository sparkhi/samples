package test.nationalarchive.csv;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class is the starting point for the CSV generator application.
 * This class generates the individual lines that could be written to file
 * by an external application.
 */
public class CsvGenerator {
    private String mRootFolder;
    public CsvGenerator(String rootFolder) {
        if (rootFolder == null) {
            throw new RuntimeException("Root folder cannot be null");
        }

        File file = new File(rootFolder);
        if (!file.exists()) {
            throw new RuntimeException("Folder does not exist");
        }

        if (!file.isDirectory()) {
            throw new RuntimeException("The path does not represent a directory");
        }
        mRootFolder = rootFolder;
    }

    /**
     * Main entry point into the generation of comma separated list
     * @return list of comma separated values representing each row in the desired output
     */
    public List<String> getCommaSeparatedLines() {
        List<String> returnList = new ArrayList<>();
        File root = new File(mRootFolder);
        Collection<File> allFiles = FileUtils.listFiles(root, null, true);
        for (File file : allFiles) {
            returnList.add(new CsvRow(root, file).toCommaSeparatedRow());
        }
        return returnList;
    }
}
