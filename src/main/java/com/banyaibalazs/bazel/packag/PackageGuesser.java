package com.banyaibalazs.bazel.packag;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by bbanyai on 14/01/18.
 */
public class PackageGuesser {

    private final PropertyGetter getter;

    public PackageGuesser(PropertyGetter getter) {
        this.getter = getter;
        mapping.put(WinPackage::is, WinPackage::new);
        mapping.put(DarwinPackage::is, DarwinPackage::new);
        mapping.put(LinuxPackage::is, LinuxPackage::new);
    }

    public com.banyaibalazs.bazel.packag.Package guessPackage() {
        String os = getter.getProperty("os.name").toLowerCase();

        Optional<Supplier<com.banyaibalazs.bazel.packag.Package>> result = mapping.entrySet()
                .stream()
                .filter(functionSupplierEntry -> functionSupplierEntry.getKey().apply(os))
                .map(Map.Entry::getValue)
                .findFirst();

        Supplier<com.banyaibalazs.bazel.packag.Package> packageSupplier = result.orElseThrow(NoPackageFoundForOs::new);
        return packageSupplier.get();
    }

    Map<Function<String, Boolean>, Supplier<com.banyaibalazs.bazel.packag.Package>> mapping = new HashMap<>();


}
