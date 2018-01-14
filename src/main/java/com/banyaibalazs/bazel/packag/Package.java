package com.banyaibalazs.bazel.packag;

import com.banyaibalazs.bazel.Logger;
import com.banyaibalazs.bazel.download.DownloadException;
import com.banyaibalazs.bazel.extract.BazelArchive;
import org.gradle.wrapper.Download;

import java.io.File;
import java.net.URI;

/**
 * Created by bbanyai on 14/01/18.
 */
public class Package {

    private Logger logger = new Logger(false);

    private static final String BASE_VERSION_ARCH_EXT = "bazel-%s-without-jdk-%s%s";
    private static final String URL_VERSION_ARCHIVE = "https://github.com/bazelbuild/bazel/releases/download/%s/%s";

    private String version;
    private String arch;
    private String extension;

    public Package version(String version) {
        this.version = version;
        return this;
    }

    Package arch(String arch) {
        this.arch = arch;
        return this;
    }

    Package extension(String extension) {
        this.extension = extension;
        return this;
    }

    public String build() {
        return String.format(BASE_VERSION_ARCH_EXT, version, arch, extension);
    }

    public BazelArchive download(File destination) {
        logger.log("Downloading bazel: " + build());
        try {

            Download download = new Download(logger, "wrapper", "1");
            download.download(uri(), new File(destination, build()));
        } catch (Exception e) {
            throw new DownloadException(e);
        }
        return new BazelArchive(destination);
    }

    private URI uri() {
        String url = String.format(URL_VERSION_ARCHIVE, version, build());
        return URI.create(url);
    }
}
