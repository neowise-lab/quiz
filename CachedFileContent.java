import java.io.IOException;

/***
 * Decorator class for any Content objects
 */
public class CachedFileContent implements Content {

    private final Content otherContent;

    private String cachedContent;
    private String cachedContentWithoutUnicode;

    public CachedFileContent(Content content) {
        this.otherContent = content;
    }

    @Override
    public String getContent() throws IOException {
        if (cachedContent == null) {
            cachedContent = otherContent.getContent();
        }
        return cachedContent;
    }

    @Override
    public String getContentWithoutUnicode() throws IOException {
        if (cachedContentWithoutUnicode == null) {
            cachedContentWithoutUnicode = otherContent.getContentWithoutUnicode();
        }
        return cachedContentWithoutUnicode;
    }

    @Override
    public void saveContent(String content) throws IOException {
        otherContent.saveContent(content);
    }
}