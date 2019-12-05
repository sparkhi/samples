package test.nationalarchive.csv;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class CsvGeneratorTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Rule
    public TemporaryFolder rootFolder = new TemporaryFolder();

    @Test
    public void shouldThrowExceptionWhenRootFolderIsNull() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Root folder cannot be null");
        CsvGenerator generator = new CsvGenerator(null);
    }

    @Test
    public void shouldThrowExceptionWhenRootFolderDoesNotExist() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Folder does not exist");
        CsvGenerator generator = new CsvGenerator(rootFolder.getRoot().getAbsolutePath() + "\\non-existent");
    }

    @Test
    public void shouldThrowExceptionWhenRootFolderIsNotAFolder() throws IOException {
        File file = rootFolder.newFile("SomeFile");
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("The path does not represent a directory");
        CsvGenerator generator = new CsvGenerator(file.getAbsolutePath());
    }

    @Test
    public void shouldReturnCollectionOfCommaSeparatedRowsFromRootFolder() throws IOException {
        File folder2001 = rootFolder.newFolder("collection", "2001");
        File folderJuly = rootFolder.newFolder("collection", "2001", "July");
        File folderAugust = rootFolder.newFolder("collection", "2001", "August");

        File julyFile1 = new File(folderJuly, "file1.txt");
        FileUtils.write(julyFile1, "123 characters to be written for testing that the length is correctly " +
                "calculated by the csv generator that we have with us.", Charset.forName("UTF-8"));

        File julyFile2 = new File(folderJuly, "file2.txt");
        FileUtils.write(julyFile2, "8 digits", Charset.forName("UTF-8"));

        File file2001 = new File(folder2001, "file4.txt");
        FileUtils.write(file2001, "some data", Charset.forName("UTF-8"));

        File augustFile1 = new File(folderAugust, "file1.txt");
        FileUtils.write(augustFile1, "13 characters", Charset.forName("UTF-8"));

        File augustFile2 = new File(folderAugust, "file2.txt");
        FileUtils.write(augustFile2, "", Charset.forName("UTF-8"));

        List<String> commaSeparatedString = new CsvGenerator(rootFolder.getRoot().getAbsolutePath()).getCommaSeparatedLines();

        assertEquals(5, commaSeparatedString.size());
        assertTrue(commaSeparatedString.contains("file1.txt,/collection/2001/August,13"));
        assertTrue(commaSeparatedString.contains("file2.txt,/collection/2001/August,0"));
        assertTrue(commaSeparatedString.contains("file4.txt,/collection/2001,9"));
        assertTrue(commaSeparatedString.contains("file1.txt,/collection/2001/July,123"));
        assertTrue(commaSeparatedString.contains("file2.txt,/collection/2001/July,8"));
    }
}