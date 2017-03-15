package sst.textfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class InputTextFileImpl implements InputTextFile {

    private final File file;

    public InputTextFileImpl(File file) throws IOException {
	this.file = file;
    }

    @Override
    public Stream<String> lines() throws IOException {
	List<String> result = new ArrayList<>();
	String line;
	try (BufferedReader input = new BufferedReader(new FileReader(this.file))) {
	    while (null != (line = input.readLine())) {
		result.add(line);
	    }
	}
	return result.stream();
    }

}