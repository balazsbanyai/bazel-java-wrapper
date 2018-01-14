package com.banyaibalazs.bazel;

import com.banyaibalazs.bazel.extract.DeflateFailedException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

/**
 * Created by bbanyai on 13/01/18.
 */
public class Bazel {

    private static final String REAL_EXECUTABLE_NAME = "bazel-real";
    private static final String EXECUTABLE_NAME = "bazel";

    private File folder;

    public Bazel(File folder) {
        this.folder = folder;
    }

    public boolean exists() {
        return Stream.of(executable(), realExecutable()).allMatch(File::exists);
    }

    public void run(String[] args) {

        setExecPermission(executable(), realExecutable());

        File bazelBin = executable();
        try {

            ProcessBuilder builder = new ProcessBuilder();
            List<String> commands = new ArrayList<>();
            commands.add(bazelBin.getAbsolutePath());
            Collections.addAll(commands, args);
            builder.command(commands);
            builder.redirectErrorStream(true);

            Process process = builder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();

            if (exitCode != 0) {
                throw new BazelInvocationException("Could not execute bazel");
            }
        } catch (IOException | InterruptedException e) {
            throw new BazelInvocationException(e);
        }
    }

    private File executable() {
        return new File(folder, EXECUTABLE_NAME);
    }

    private File realExecutable() {
        return new File(folder, REAL_EXECUTABLE_NAME);
    }

    private void setExecPermission(File... executable) {
        for (File file : executable) {
            if (!file.setExecutable(true)) {
                throw new BazelInvocationException("Could not set executable permission on " + file.getAbsolutePath());
            }
        }
    }

}
