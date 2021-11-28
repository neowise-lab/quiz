import java.io.*;
import java.util.function.Predicate;

/**
 * This class is thread safe.
 */
public class FileContent implements Content {

  private final File file;

  public FileContent(File file) {
    this.file = file;
  }

  @Override
  public synchronized String getContent() throws IOException {
    return readStreamContent(
            new FileInputStream(file),
            data -> true // append all characters
    );
  }

  @Override
  public synchronized String getContentWithoutUnicode() throws IOException {
    return readStreamContent(
            new FileInputStream(file),
            data -> data < 0x80 // without unicode characters
    );
  }

  private String readStreamContent(InputStream is, Predicate<Integer> filter) throws IOException {
    StringBuilder buffer = new StringBuilder();
    try (is) {
      int data;
      while((data = is.read()) > 0) {
        if (filter.test(data)) {
          buffer.append((char)is.read());
        }
      }
    }
    return buffer.toString();
  }

  @Override
  public synchronized void saveContent(String content) throws IOException {
    try (FileOutputStream fos = new FileOutputStream(file)) {
      fos.write(content.getBytes());
    }
  }
}