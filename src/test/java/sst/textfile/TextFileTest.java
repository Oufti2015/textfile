package sst.textfile;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

public class TextFileTest {

    @Test
    public void testTextFile() {

	File file = new File("Test.txt");
	file.delete();
	Assert.assertFalse(file.exists());
	try (OutputTextFile textFile = new OutputTextFileImpl(file, 3)) {
	    textFile.saveLine("un");
	    textFile.saveLine("deux");
	    textFile.saveLine("trois");
	    textFile.saveLine("quatre");
	    textFile.saveLine("cinq");
	    textFile.saveLine("six");
	    textFile.saveLine("sept");
	    textFile.saveLine("huit");
	    textFile.saveLine("neuf");
	    textFile.saveLine("dix");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	Assert.assertTrue(file.exists());
    }

    @Test
    public void testReadFile() {
	File file = new File("Test.txt");
	Assert.assertTrue(file.exists());
	try {
	    InputTextFile textFile = new InputTextFileImpl(file);

	    textFile.lines()
		    .forEach(l -> System.out.println(" -- " + l));

	    Long count = textFile.lines().count();
	    Assert.assertEquals(new Long(10L), count);
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    @Test
    public void testTextFileSorted() {

	File file = new File("Test.txt");
	file.delete();
	Assert.assertFalse(file.exists());
	try (OutputTextFile textFile = new OutputTextFileImpl(file, 3)) {
	    textFile.sort(true);

	    textFile.saveLine("un");
	    textFile.saveLine("deux");
	    textFile.saveLine("trois");
	    textFile.saveLine("quatre");
	    textFile.saveLine("cinq");
	    textFile.saveLine("six");
	    textFile.saveLine("sept");
	    textFile.saveLine("huit");
	    textFile.saveLine("neuf");
	    textFile.saveLine("dix");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	Assert.assertTrue(file.exists());
    }

    @Test
    public void testReadFileSorted() {
	File file = new File("Test.txt");
	Assert.assertTrue(file.exists());
	try {
	    InputTextFile textFile = new InputTextFileImpl(file);

	    textFile.lines()
		    .forEach(l -> System.out.println(" -- " + l));

	    Long count = textFile.lines().count();
	    Assert.assertEquals(new Long(10L), count);
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }
}
