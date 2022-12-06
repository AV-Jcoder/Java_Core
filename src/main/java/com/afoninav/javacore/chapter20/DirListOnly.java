package com.afoninav.javacore.chapter20;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Пример использования второй формы метода list(FilenameFilter FFObj)
 * который возвращает только те файлы,
 * которые соответствуют образцу инён или фильтру.
 *
 */

public class DirListOnly {
    public static void main(String[] args) {
        String dirName = "/tmp";
        File f1 = new File(dirName);
        FilenameFilter only = new OnlyExt("txt");
        String[] s = f1.list(only);

        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
        }
    }
}

class OnlyExt implements FilenameFilter {
    String ext;

    OnlyExt(String ext) {
        this.ext = "." + ext;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(ext);
    }
}
