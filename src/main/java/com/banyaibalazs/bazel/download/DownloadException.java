package com.banyaibalazs.bazel.download;

/**
 * Created by bbanyai on 13/01/18.
 */
public class DownloadException extends RuntimeException {
    public DownloadException(Exception e) {
        super(e);
    }
}
