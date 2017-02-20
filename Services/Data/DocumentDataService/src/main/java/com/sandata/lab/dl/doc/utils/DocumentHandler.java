package com.sandata.lab.dl.doc.utils;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.log.SandataLogger;
import com.sandata.lab.dl.doc.utils.log.DocumentDataLogger;

import java.io.*;
import java.util.Arrays;

/**
 * Handler for processing document requests;
 * <p/>
 *
 * @author David Rutgos
 */
public class DocumentHandler {

    public static final String FILE_DATA = "FILE_DATA";

    /**
     * The @targetFile should be wrapped in an application/octet-stream which needs to be removed so we get the original
     * file contents.
     *
     * @param targetFile The full path to the file containing the application/octet-stream.
     * @return Returns the full path of the temp file that contains the original file with the application/octet-stream
     * removed.
     * @throws SandataRuntimeException
     */
    public File removeFormDataHeaders(final File targetFile) throws SandataRuntimeException {

        SandataLogger logger = DocumentDataLogger.CreateLogger();

        InputStream inputStream = null;
        OutputStream outputStream = null;

        if (targetFile == null) {
            throw new SandataRuntimeException(String.format("removeFormDataHeaders: %s: targetFile == null",
                    getClass().getName()));
        }

        if (! targetFile.exists()) {
            throw new SandataRuntimeException(String.format("removeFormDataHeaders: %s: [FILE_PATH=%s]: targetFile does NOT exist!",
                    getClass().getName(), targetFile.getPath()));
        }

        try {

            final int bufferSize = 1024;
            final int lineSize = 255;

            File tempFile = new File(targetFile.getPath() + ".temp");

            inputStream = new FileInputStream(targetFile);
            outputStream = new FileOutputStream(tempFile);
            byte[] buffer = new byte[bufferSize];
            int bytesRead = 0;
            byte val;
            int currentLine = 0;

            // Skip first five lines
            byte[] line = new byte[lineSize];
            String boundaryId = null;
            StringBuilder boundaryBuilder = new StringBuilder();
            while ((val = (byte)inputStream.read()) != -1) {

                // 10 = Line Feed (LF)
                if (val == 10) {
                    if (currentLine == 0) {

                        // Build the boundary string but the line buffer
                        for (int i = 0; i < line.length; i++) {
                            if (line[i] == '\r') {
                                break;
                            }

                            boundaryBuilder.append((char)line[i]);
                        }

                        boundaryId = boundaryBuilder.toString();
                    }

                    currentLine++;
                    bytesRead = 0;

                    // IMPORTANT: Based on a lines 0 - 4 are the application/octet-stream headers
                    if (currentLine == 5) {
                        break;
                    }

                    line = new byte[lineSize];
                }

                line[bytesRead++] = val;
            }

            if (boundaryId == null) {
                throw new SandataRuntimeException("boundaryId == null");
            }

            // Write the rest of the file up-to the boundaryId
            //while ((bytesRead = inputStream.read(buffer)) != -1) {
            int bytesAvailableDelta;
            while (bytesRead != -1) {

                bytesAvailableDelta = inputStream.available() - (bufferSize*2);

                // If the remaining bytes are <= the bufferSize, then get all the remaining bytes
                if (bytesAvailableDelta <= bufferSize) {

                    // Allocate new buffer
                    buffer = new byte[inputStream.available()];

                    // Read data
                    bytesRead = inputStream.read(buffer);
                    int bufferPtr = buffer.length - 1; // Start at the end
                    while (buffer[bufferPtr--] != 10); // Loop until the LF

                    // TEST: Should be pointing to a CR
                    if (buffer[bufferPtr] != 13) {
                        throw new SandataRuntimeException(String.format("buffer[%d] != CR [%d]", bufferPtr, buffer[bufferPtr]));
                    }

                    // The end of a boundary has two dashes (--) which we want to skip

                    // TEST: Should be pointing to a dash
                    bufferPtr--;
                    if (buffer[bufferPtr] != '-') {
                        throw new SandataRuntimeException(String.format("buffer[%d] != Dash(-)(1) [%d]", bufferPtr, buffer[bufferPtr]));
                    }

                    // TEST: Should be pointing to a dash
                    bufferPtr--;
                    if (buffer[bufferPtr] != '-') {
                        throw new SandataRuntimeException(String.format("buffer[%d] != Dash(-)(2) [%d]", bufferPtr, buffer[bufferPtr]));
                    }

                    final int bufferAdjustedSize = (buffer.length - boundaryId.length() - 4);

                    // Adjust bytes minus the boundaryId + two dashes (--)
                    bytesRead -= boundaryId.length() + 4;

                    // Create a new buffer minus the boundaryId
                    buffer = Arrays.copyOfRange(buffer, 0, bufferAdjustedSize);
                    outputStream.write(buffer, 0, bytesRead);

                    bytesRead = -1; // break;
                }
                else {
                    // Read data
                    bytesRead = inputStream.read(buffer);
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            return tempFile;
        }
        catch (Exception e) {

            e.printStackTrace();
            throw new SandataRuntimeException(String.format("saveFile: %s: %s: [ERROR_MSG=%s]",
                    getClass().getName(), e.getClass().getName(), e.getMessage()), e);
        }
        finally {

            if(inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }

            if(outputStream != null) {
                try {
                    outputStream.close();
                }
                catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }

            logger.stop();
        }
    }
}
