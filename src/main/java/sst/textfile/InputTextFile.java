package sst.textfile;

import java.io.IOException;
import java.util.stream.Stream;

public interface InputTextFile {

    public Stream<String> lines() throws IOException;

}
