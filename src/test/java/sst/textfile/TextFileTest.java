package sst.textfile;

import org.junit.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class TextFileTest {

    private static final String TEST_TXT = "Test.txt";

    @BeforeClass
    public static void beforeClass() {
        System.out.println("@BeforeClass");
        deleteFile();
    }

    @Before
    public void before() {
        System.out.println("@Before -----------------------------------");
    }

    @Test
    public void testTextFile() {
        System.out.println("@Test");
        File file = new File(TEST_TXT);
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
        System.out.println("@Test");
        File file = new File(TEST_TXT);
        Assert.assertTrue(file.exists());
        try {
            InputTextFile textFile = new InputTextFileImpl(file);

            textFile.lines().forEach(l -> System.out.println(" -- " + l));

            Integer count = textFile.lines().size();
            Assert.assertEquals(new Integer(10), count);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testTextFileSorted() {
        System.out.println("@Test");

        File file = new File(TEST_TXT);
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
        System.out.println("@Test");
        File file = new File(TEST_TXT);
        Assert.assertTrue(file.exists());
        try {
            InputTextFile textFile = new InputTextFileImpl(file);

            textFile.lines()
                    .forEach(l -> System.out.println(" -- " + l));

            Integer count = textFile.lines().size();
            Assert.assertEquals(new Integer(10), count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSerialize() {

        File file = new File(TEST_TXT);

        try (OutputTextFile textFile = new OutputTextFileImpl(file)) {
            textFile.sort(true);

            textFile.serialize(new SerializableToTextFile() {
                List<String> source = Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight",
                        "nine", "ten");

                @Override
                public List<String> text() {
                    return source;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertTrue(file.exists());

        try {
            InputTextFile textFile = new InputTextFileImpl(file);

            textFile.lines()
                    .forEach(l -> System.out.println(" -- " + l));

            Integer count = textFile.lines().size();
            Assert.assertEquals(new Integer(10), count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void after() {
        System.out.println("@After -----------------------------------");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("@AfterClass");
        deleteFile();
    }

    private static void deleteFile() {
        File file = new File(TEST_TXT);
        file.delete();
        Assert.assertFalse(file.exists());
    }
}
