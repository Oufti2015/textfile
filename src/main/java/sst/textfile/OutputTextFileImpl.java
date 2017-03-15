package sst.textfile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
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

    private void init() throws IOException {
	buffer = new ArrayList<>(bufferSize);
	output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
    }

    @Override
    public void saveLine(String line) throws IOException {
	buffer.add(line);
	flush();
    }

    private void flush() throws IOException {
	flush(false);
    }

    private void flush(Boolean forceWrite) throws IOException {
	Runnable runnable = () -> {
	    final String name = Thread.currentThread().getName();
	    try {
		System.out.println(name + " - Flushing " + buffer.size() + " lines...");
		for (String line : buffer) {
		    output.write(line + "\n");
		}
		output.flush();
		buffer = new ArrayList<>(bufferSize);
		System.out.println(name + " - ...done.");
	    } catch (IOException e) {
		System.err.println("Cannot write to " + file + " : " + e);
		e.printStackTrace();
	    }
	};

	if ((forceWrite && buffer.size() > 0) || (!sorted && buffer.size() >= bufferSize)) {

	    if (sorted) {
		buffer = buffer.stream()
			.sorted()
			.collect(Collectors.toList());
	    }
	    runnable.run();
	}
    }

    @Override
    public void close() throws Exception {
	final String name = Thread.currentThread().getName();
	System.out.println(name + " - Closing...");

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
}
