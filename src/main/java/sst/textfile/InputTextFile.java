package sst.textfile;

import java.io.IOException;
import java.util.List;

public interface InputTextFile {

    List<String> lines() throws IOException;

    String oneLine() throws IOException;
}
