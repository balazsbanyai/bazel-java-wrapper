package com.banyaibalazs.bazel.packag;

/**
 * Created by bbanyai on 14/01/18.
 */
class DarwinPackage extends Package {

    public DarwinPackage() {
        extension(".sh");
        arch("installer-darwin-x86_64");
    }

    public static boolean is(String s) {
        return s.contains("mac");
    }
}
