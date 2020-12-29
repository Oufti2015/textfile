package sst.textfile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OutputTextFileImpl implements OutputTextFile {

    private final File file;
    private final Integer bufferSize;
    private List<String> buffer;
    private BufferedWriter output;
    private Boolean sorted = false;

    public OutputTextFileImpl(File file) throws IOException {
        this.file = file;
        bufferSize = 1024;
        init();
    }

    public OutputTextFileImpl(File file, Integer bufferSize) throws IOException {
        this.file = file;
        this.bufferSize = bufferSize;
        init();
    }

    @Override
    public void saveLine(String line) throws IOException {
        buffer.add(line);
        flush();
    }

    @Override
    public void close() throws Exception {
        if (output != null) {
            flush(true);
            output.close();
        }
    }

    @Override
    public void sort(Boolean sorted) {
        this.sorted = sorted;
    }

    @Override
    public void serialize(SerializableToTextFile source) throws IOException {
        buffer.addAll(source.text());
        flush();
    }

    private void init() throws IOException {
        buffer = new ArrayList<>(bufferSize);
        output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
    }

    private void flush() throws IOException {
        flush(false);
    }

    private void flush(Boolean forceWrite) throws IOException {
        if ((forceWrite && !buffer.isEmpty()) || (!sorted && buffer.size() >= bufferSize)) {

            if (Boolean.TRUE.equals(sorted)) {
                buffer = buffer.stream()
                        .sorted()
                        .collect(Collectors.toList());
            }
            for (String line : buffer) {
                output.write(line + "\n");
            }
            output.flush();
            buffer = new ArrayList<>(bufferSize);
        }
    }
}
