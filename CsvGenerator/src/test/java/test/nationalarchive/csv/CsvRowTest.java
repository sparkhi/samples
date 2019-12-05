package test.nationalarchive.csv;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import static org.junit.Assert.assertEquals;

public class CsvRowTest {

    @Rule
    public TemporaryFolder rootFolder = new TemporaryFolder();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldGenerateAStringRowForFileMetadata() throws IOException {
        // [rootFolder]
        //     +---- [FolderOne]
        //                 +------- File1.txt
        //  File1.txt has contents "some data"

        File folderOne = rootFolder.newFolder("FolderOne");
        File file1 = new File(folderOne, "File1.txt");
        FileUtils.write(file1, "some data", Charset.forName("UTF-8"));
        CsvRow row = new CsvRow(rootFolder.getRoot(), file1);
        assertEquals("File1.txt,/FolderOne,9", row.toCommaSeparatedRow());
    }

    @Test
    public void shouldThrowExceptionWhenTheAbsoluteFilePathIsNotAChildOfRootFolder() throws IOException {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("File is not a child of root folder");
        File folderOne = rootFolder.newFolder("FolderOne");
        File file1 = rootFolder.newFile("File1.txt");
        FileUtils.write(file1, "some data", Charset.forName("UTF-8"));
        CsvRow row = new CsvRow(folderOne, file1);
    }

    @Test
    public void shouldGenerateAStringForNestedFolderStructure() throws IOException {
        File folderChild = rootFolder.newFolder("FolderOne", "FolderTwo", "FolderThree");
        File file1 = new File(folderChild, "File1.txt");
        FileUtils.write(file1, "some data", Charset.forName("UTF-8"));
        CsvRow row = new CsvRow(rootFolder.getRoot(), file1);
        assertEquals("File1.txt,/FolderOne/FolderTwo/FolderThree,9", row.toCommaSeparatedRow());
    }
}