package duplicator;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Duplicator {
    public static void main(String[] args) throws IOException {
        Duplicator duplicator = new Duplicator();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入源路径:");
        String src = sc.nextLine();
        System.out.println("请输入目标路径:");
        String des = sc.nextLine();
        System.out.println(duplicator.check(src, des));
    }

    public String check(String src, String des) throws IOException {
        File srcFile = new File(src);
        File desFile = new File(des);
        if (!srcFile.exists() | !desFile.exists()) return "The sourceFile or destinationFile don't exist!";
        LocalDuplicator localDuplicator = new LocalDuplicator();
        localDuplicator.checkFile(srcFile, desFile);
        return "true";
    }
}
