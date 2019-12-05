package test.nationalarchive.csv;

import java.io.File;

/**
 * This class represents a high level object representing contents of a single row
 */
public class CsvRow {
    File mRootFolder;
    File mAbsolutePathToFile;

    public CsvRow(File rootFolder, File absolutePathToFile) {

        if (!absolutePathToFile.getAbsolutePath().startsWith(rootFolder.getAbsolutePath())) {
            throw new RuntimeException("File is not a child of root folder");
        }

        mRootFolder = rootFolder;
        mAbsolutePathToFile = absolutePathToFile;
    }

    public String toCommaSeparatedRow() {
        String fileName = mAbsolutePathToFile.getName();
        String fullPath = mAbsolutePathToFile.getAbsolutePath();
        String relativePath = fullPath.substring(
                mRootFolder.getAbsolutePath().length(),
                fullPath.length() - fileName.length() - 1
        ).replace("\\", "/"); //replace any occurence of back slash to forward sash
        return fileName + "," + relativePath + "," + mAbsolutePathToFile.length();
    }
}
