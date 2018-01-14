package com.banyaibalazs.bazel.packag;

/**
 * Created by bbanyai on 14/01/18.
 */
class WinPackage extends Package {

    public WinPackage() {
        extension(".exe");
        arch("windows-x86_64");
    }

    public static boolean is(String s) {
        return s.contains("win");
    }
}
