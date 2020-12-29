package sst.textfile;

import java.io.IOException;

public interface OutputTextFile extends AutoCloseable {

    void saveLine(String line) throws IOException;

    void sort(Boolean sorted);

    void serialize(SerializableToTextFile text) throws IOException;
}
