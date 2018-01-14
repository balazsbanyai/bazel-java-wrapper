package com.banyaibalazs.bazel.extract;

/**
 * Created by bbanyai on 14/01/18.
 */
public class DeflateFailedException extends RuntimeException {
    public DeflateFailedException(Exception e) {
        super(e);
    }

    public DeflateFailedException(String s) {
        super(s);
    }
}
