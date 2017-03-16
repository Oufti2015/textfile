package sst.textfile;

import java.io.IOException;
import java.util.List;

public interface InputTextFile {

    public List<String> lines() throws IOException;

}
