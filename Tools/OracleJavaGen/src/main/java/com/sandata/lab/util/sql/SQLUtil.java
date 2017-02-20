package com.sandata.lab.util.sql;

import com.sandata.lab.common.utils.exception.SandataRuntimeException;
import com.sandata.lab.common.utils.string.StringUtil;

import java.io.File;
import java.util.Date;

public class SQLUtil {

    public static String createReferenceScript(String scriptRootPath) throws SandataRuntimeException {

        if (StringUtil.IsNullOrEmpty(scriptRootPath)) {
            return null;
        }

        StringBuilder java = new StringBuilder();
        StringBuilder types = new StringBuilder();
        StringBuilder pkgs = new StringBuilder();
        StringBuilder global = new StringBuilder();

        File directory = new File(scriptRootPath);
        File[] folders = directory.listFiles();

        if (folders == null) {
            throw new SandataRuntimeException("SQLUtil: createReferenceScript: folders == null");
        }

        // Expecting the path to be gen/types and gen/java
        for (File folder : folders) {

            if (folder.getName().equals("install.coredata.sql")) {
                continue;
            }

            if (folder.getName().equals("install.metadata.sql")) {
                continue;
            }

            if (!folder.isDirectory()) {
                throw new SandataRuntimeException("SQLUtil: createReferenceScript: Directory structure does not appear to be valid!");
            }

            if (folder.getName().equals("java")) {

                File javaScripts = new File(folder.getPath());

                File[] javaScriptFiles = javaScripts.listFiles();
                if (javaScriptFiles == null) {
                    throw new SandataRuntimeException("SQLUtil: createReferenceScript: javaScriptFiles == null");
                }

                for(File javaScript : javaScriptFiles) {
                    java.append(String.format("%s\n", SQLUtil.formatReferenceScript(folder.getName(), javaScript.getName())));
                }

            } else if (folder.getName().equals("types")) {

                File typeScripts = new File(folder.getPath());

                File[] typeScriptFiles = typeScripts.listFiles();
                if (typeScriptFiles == null) {
                    throw new SandataRuntimeException("SQLUtil: createReferenceScript: typeScriptFiles == null");
                }

                for(File typeScript : typeScriptFiles) {
                    types.append(String.format("%s\n", SQLUtil.formatReferenceScript(folder.getName(), typeScript.getName())));
                }

            } else if (folder.getName().equals("pkg")) {

                File pkgScripts = new File(folder.getPath());

                File[] pkgScriptFiles = pkgScripts.listFiles();
                if (pkgScriptFiles == null) {
                    throw new SandataRuntimeException("SQLUtil: createReferenceScript: pkgScriptFiles == null");
                }

                for(File pkgScript : pkgScriptFiles) {
                    pkgs.append(String.format("%s\n", SQLUtil.formatReferenceScript(folder.getName(), pkgScript.getName())));
                }

            } else if (folder.getName().equals("global")) {

                File globalScripts = new File(folder.getPath());

                File[] globalScriptFiles = globalScripts.listFiles();
                if (globalScriptFiles == null) {
                    throw new SandataRuntimeException("SQLUtil: createReferenceScript: globalScriptFiles == null");
                }

                for(File globalScript : globalScriptFiles) {
                    global.append(String.format("%s\n", SQLUtil.formatReferenceScript(folder.getName(), globalScript.getName())));
                }

            } else {
                throw new SandataRuntimeException("SQLUtil: createReferenceScript: Unknown folder name: [" + folder.getName() + "]");
            }
        }

        StringBuilder result = new StringBuilder("-- File Generated: " + new Date().toString() + "\n\n");
        result.append(global.toString());
        result.append(types.toString());
        result.append(java.toString());
        result.append(pkgs.toString());
        return result.toString();
    }

    public static String formatReferenceScript(String directoryPath, String fileName) {

        if (StringUtil.IsNullOrEmpty(directoryPath) || StringUtil.IsNullOrEmpty(fileName)) {
            return null;
        }

        return String.format("@@\"./%s/%s\";", directoryPath, fileName);
    }
}
