package sst.textfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputTextFileImpl implements InputTextFile {

    private final File file;

    public InputTextFileImpl(File file) {
        this.file = file;
    }

    @Override
    public List<String> lines() throws IOException {
        List<String> result = new ArrayList<>();
        String line;
        try (BufferedReader input = new BufferedReader(new FileReader(this.file))) {
            while (null != (line = input.readLine())) {
                result.add(line);
            }
        }
        return result;
    }

    @Override
    public String oneLine() throws IOException {
        return String.join(" ", lines());
    }
}
