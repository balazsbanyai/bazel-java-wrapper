package com.banyaibalazs.bazel.packag;

/**
 * Created by bbanyai on 14/01/18.
 */
class LinuxPackage extends Package {

    public LinuxPackage() {
        extension(".sh");
        arch("installer-linux-x86_64");
    }

    public static boolean is(String s) {
        return s.contains("linux");
    }
}
