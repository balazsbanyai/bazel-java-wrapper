package com.banyaibalazs.bazel.packag;

/**
 * Created by bbanyai on 14/01/18.
 */
public interface PropertyGetter {
    String getProperty(String s);

    static PropertyGetter getDefault() {
        return System::getProperty;
    }
}
