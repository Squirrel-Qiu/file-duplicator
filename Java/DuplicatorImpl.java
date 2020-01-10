package duplicator;

import java.io.File;
import java.io.IOException;

public interface DuplicatorImpl {
    void copyFile(File srcFile, File desFile) throws IOException;
    void copyDirectory(File srcDir, File desDir) throws IOException;
}
