package com.banyaibalazs.bazel;

import java.io.IOException;

/**
 * Created by bbanyai on 13/01/18.
 */
public class BazelInvocationException extends RuntimeException {
    public BazelInvocationException(Exception e) {
        super(e);
    }

    public BazelInvocationException(String message) {
        super(message);
    }
}
