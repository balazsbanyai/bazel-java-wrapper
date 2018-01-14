package com.banyaibalazs.bazel.extract;

import com.banyaibalazs.bazel.Logger;

import java.io.File;

/**
 * Created by bbanyai on 14/01/18.
 */
public class BazelArchive {

    private final File archive;

    private Logger logger = new Logger(false);

    public BazelArchive(File folder) {
        this.archive = new File(folder, "installer.zip");

    }

    public boolean exists() {
        return archive.exists();
    }

    public boolean hasValidSignature() {
        return true;
    }

    public void expand() {
        logger.log("Extracting...");
        new Deflator().deflate(archive.getAbsolutePath(), archive.getParent()+File.separator);
    }


}
