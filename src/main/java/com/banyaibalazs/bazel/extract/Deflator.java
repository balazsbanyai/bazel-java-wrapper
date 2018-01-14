package com.banyaibalazs.bazel.extract; /**
 * Created by bbanyai on 13/01/18.
 */
import net.lingala.zip4j.core.ZipFile;

import java.io.File;

public class Deflator {

    public void deflate(String zipFile, String outputFolder) {
        try {

            File folder = new File(outputFolder);
            if (!folder.exists()) {
                folder.mkdir();
            }

            ZipFile zip = new ZipFile(zipFile);
            zip.extractAll(outputFolder);

        } catch (Exception ex) {
            throw new DeflateFailedException(ex);
        }
    }
}
