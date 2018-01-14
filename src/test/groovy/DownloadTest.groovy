/*
 * Copyright 2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import com.banyaibalazs.bazel.Logger
import org.gradle.wrapper.Download
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.assertEquals

class DownloadTest {
    Download download

    File downloadFile

    URI remoteFileUri
    File remoteFile

    @Before public void setUp() {
        download = new Download(new Logger(true), "gradlew", "aVersion")

        remoteFile = File.createTempFile("test", "file")
        remoteFile.deleteOnExit()
        remoteFile.write('sometext')

        remoteFileUri = remoteFile.toURI()

        File tempDir = File.createTempDir()
        downloadFile = new File(tempDir, "downloaded.file")
        tempDir.deleteOnExit()
    }

    @Test public void testDownload() {
        assert !downloadFile.exists()
        download.download(remoteFileUri, downloadFile)
        assert downloadFile.exists()
        assertEquals('sometext', downloadFile.text)
    }
}
