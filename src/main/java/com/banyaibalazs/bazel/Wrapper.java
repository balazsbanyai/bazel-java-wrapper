package com.banyaibalazs.bazel;

import com.banyaibalazs.bazel.download.DownloadException;
import com.banyaibalazs.bazel.extract.BazelArchive;
import com.banyaibalazs.bazel.extract.InvalidSignatureException;
import com.banyaibalazs.bazel.packag.NoPackageFoundForOs;
import com.banyaibalazs.bazel.packag.Package;
import com.banyaibalazs.bazel.packag.PackageGuesser;
import com.banyaibalazs.bazel.packag.PropertyGetter;

import java.io.File;

/**
 * Created by bbanyai on 14/01/18.
 */
public class Wrapper {

    private static final Logger logger = new Logger(false);

    public void run(String[] args) {
        Bazel bazel = new Bazel(getFolder());

        try {
            if (!bazel.exists()) {
                PackageGuesser guesser = new PackageGuesser(PropertyGetter.getDefault());
                Package packag = guesser.guessPackage();

                BazelArchive archive = new BazelArchive(getFolder());
                if (!archive.exists()) {
                    archive = packag.download(getFolder());
                }

                if (archive.hasValidSignature()) {
                    logger.log("Expanding com.banyaibalazs.bazel.Bazel.");
                    archive.expand();
                } else {
                    throw new InvalidSignatureException();
                }
            }
        } catch (DownloadException e) {
            logger.log("Download failed due to " + e.getClass().getName());
        } catch (InvalidSignatureException e) {
            logger.log("The downloaded archive is not signed by bazel. Add --allow-untrusted option to force.");
        } catch (NoPackageFoundForOs e) {
            logger.log("Could not find package for this os ("+System.getProperty("os.name")+")");
        }

        if (bazel.exists()) {
            bazel.run(args);
        }
    }

    private File getFolder() {
        return new File("bazel-wrapper"+File.separator);
    }
}
