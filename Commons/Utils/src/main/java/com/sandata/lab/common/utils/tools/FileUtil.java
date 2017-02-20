package com.sandata.lab.common.utils.tools;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 8/28/15
 * Time: 6:00 PM
 */

public class FileUtil {

    private final File file;
    private final BufferedReader bufferedReader;
    private String currentLine;

    public FileUtil(File file) throws FileNotFoundException {
        this.file = file;
        this.bufferedReader = new BufferedReader(new FileReader(this.file.getPath()));
    }

    public static void CopyFile(final File from, final File to) throws IOException {
        Files.copy(from.toPath(), to.toPath());
    }

    public static void RenameTempFile(final String tmpFilePath, final String targetFilePath) throws SandataRuntimeException {

        File targetFile = new File(targetFilePath);

        try {
            // Try to delete the targetFile if it still exists
            DeleteFile(targetFilePath);
        }
        catch (Exception e) {

            e.printStackTrace();
            throw new SandataRuntimeException(String.format("FileUtil: RenameFile: %s: [%s]",
                    e.getClass().getName(), e.getMessage()));
        }

        // Rename
        File tmpFile = new File(tmpFilePath);
        tmpFile.renameTo(targetFile);

    }

    public static void DeleteFile(final String fileToDeletePath) {

        // Delete Original File
        File fileToDelete = new File(fileToDeletePath);
        fileToDelete.delete();

        try {
            // wait after delete
            Thread.sleep(25);
        }
        catch (Exception e) {
            // Do nothing
        }
    }

    public static String ReadFile(final File file) throws SandataRuntimeException {

        final String filePath = file.getPath();

        BufferedReader reader = null;

        StringBuilder builder = new StringBuilder();

        try {
            reader = new BufferedReader(new FileReader(filePath));

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("FileUtil: ReadFile: %s: [%s]",
                    e.getClass().getName(), e.getMessage()));
        }
        finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        return builder.toString();
    }

    public static void Write(final String data, final String path, final String fileName) throws IOException {

        File target = new File(path);

        if (!target.exists()) {
            target.mkdirs();
        }

        //byte[] byteArray = data.getBytes("UTF-8");

        InputStream is = new ByteArrayInputStream(data.getBytes("UTF-8"));

        FileOutputStream outputStream = new FileOutputStream(String.format("%s/%s", path, fileName));
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");

        int c;
        while ((c = is.read()) != -1) {
            writer.write(c);
        }

        writer.flush();
        writer.close();
        is.close();
        //outputStream.write(byteArray);

        outputStream.close();
    }

    public static String ReadResource(final String resourceFile) throws IOException {

        InputStream inputStream = null;
        try {
            inputStream = FileUtil.class.getClassLoader().getResourceAsStream(resourceFile);

            if (inputStream == null) {
                throw new SandataRuntimeException("InputStream is NULL! File not found!");
            }

            InputStreamReader is = new InputStreamReader(inputStream, "UTF-8");
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(is);
            String read = br.readLine();

            while(read != null) {
                sb.append(read);
                read = br.readLine();
            }

            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            throw new SandataRuntimeException(String.format("FileUtil: ReadResource: [%s]: [%s]: %s",
                    e.getClass().getName(), resourceFile, e.getMessage()));
        }
        finally {
            if (inputStream != null)
                inputStream.close();
        }
    }

    public static List<String> ListFiles(File folder) throws Exception {

        List<String> files = new ArrayList<>();

        if (folder == null || folder.listFiles() == null) {
            return files;
        }

        for (final File fileEntry : folder.listFiles()) {

            if (fileEntry.isDirectory()) {
                // WARNING: Recursive!
                ListFiles(fileEntry);

            } else {

                files.add(fileEntry.getPath());
            }
        }

        return files;
    }

    public static String RemoveFileExtension(File file, String extention) {
       return file.getName().substring(0, file.getName().length() - extention.length());
    }

    public String getCurrentLine() {
        return currentLine;
    }

    public String readLine() throws IOException {
        currentLine = bufferedReader.readLine();
        if (currentLine == null) {
            close();
        }
        return currentLine;
    }

    public void close() throws IOException {

        if (bufferedReader != null) {
            bufferedReader.close();
        }
    }
}
