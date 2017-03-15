package sst.textfile;

import java.io.IOException;

public interface OutputTextFile extends AutoCloseable {

    public void saveLine(String line) throws IOException;

    public void sort(Boolean sorted);

    public void serialize(SerializableToTextFile text) throws IOException;
}
