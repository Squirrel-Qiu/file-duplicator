package duplicator;

import java.io.*;

public class LocalDuplicator implements DuplicatorImpl {
    public void checkFile(File src, File des) throws IOException {
        if (src.isFile() && des.isFile()) {
            copyFile(src, des);
        }
        if (src.isFile() && des.isDirectory()) {
            copy2(src, des);
        }
        if (src.isDirectory() && des.isDirectory()) {
            copyDirectory(src, des);
        }
        if (src.isDirectory() && des.isFile()) {
            System.out.println("The destination path is not allowed to be a file when the source path is a directory!");
        }
    }

    @Override
    public void copyFile(File srcFile, File desFile) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(srcFile);
        BufferedInputStream in = new BufferedInputStream(fileInputStream);
        FileOutputStream fileOutputStream = new FileOutputStream(desFile);
        BufferedOutputStream out = new BufferedOutputStream(fileOutputStream);
        byte[] buff = new byte[1024];
        int len;
        while ((len = in.read(buff)) > 0) {
            out.write(buff, 0, len);
        }
        out.flush();
        in.close();
        out.close();
    }

    public void copy2(File srcFile, File desFile) throws IOException {
        File desFileName = new File(desFile + File.separator + srcFile.getName());
        copyFile(srcFile, desFileName);
    }

    @Override
    public void copyDirectory(File srcDir, File desDir) throws IOException {
        // new target directory
        File desCopyDir = new File(desDir + File.separator +srcDir.getName());
        desCopyDir.mkdirs();
        File[] files = srcDir.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                File srcFile = files[i];
                File desFile = new File(desCopyDir + File.separator + files[i].getName());
                copyFile(srcFile, desFile);
            }
            if (files[i].isDirectory()) {
                File srcFile = files[i];
                copyDirectory(srcFile, desCopyDir);
            }
        }
    }
}
