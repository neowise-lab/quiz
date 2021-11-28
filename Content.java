import java.io.IOException;

public interface Content {

    String getContent() throws IOException;

    String  getContentWithoutUnicode() throws IOException;

    void saveContent(String content) throws IOException;

}