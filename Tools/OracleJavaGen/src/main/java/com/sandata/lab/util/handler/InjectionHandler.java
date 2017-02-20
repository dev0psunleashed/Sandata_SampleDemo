package com.sandata.lab.util.handler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Date: 8/29/15
 * Time: 12:26 PM
 */

public abstract class InjectionHandler {

    protected String line;
    protected String injectionPoint;
    protected String fileName;
    protected Map excludedFile;
    protected BufferedWriter writer;
    protected String[] strings;

    public abstract void write() throws IOException;

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public BufferedWriter getWriter() {
        return writer;
    }

    public void setWriter(BufferedWriter writer) {
        this.writer = writer;
    }

    public String[] getStrings() {
        return strings;
    }

    public void setStrings(String[] strings) {
        this.strings = strings;
    }

    public String getInjectionPoint() {
        return injectionPoint;
    }

    public void setInjectionPoint(String injectionPoint) {
        this.injectionPoint = injectionPoint;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Map getExcludedFile() {
        return excludedFile;
    }

    public void setExcludedFile(Map excludedFile) {
        this.excludedFile = excludedFile;
    }

    protected boolean excludedFile() {
        return !(fileName == null || fileName.length() == 0 || excludedFile == null) && (excludedFile.get(fileName) != null);
    }
}
