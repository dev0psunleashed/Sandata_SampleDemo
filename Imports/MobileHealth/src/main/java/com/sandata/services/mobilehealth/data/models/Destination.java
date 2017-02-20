package com.sandata.services.mobilehealth.data.models;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sandata.up.commons.exception.SandataRuntimeException;
import com.sandata.services.mobilehealth.utils.Messaging;

/**
 * Copied from EvvBatch
 * 
 * @author khangle
 */
public class Destination {

    private static final Pattern SFTP_URL_PATTERN = Pattern.compile("(sftp://)([A-z0-9_\\-\\.]*)(:)([0-9]*)(/)(.*)");
    private static final Pattern FILE_URL_PATTERN = Pattern.compile("(file://|file:)(.*)");
    private static final Pattern FTP_URL_PATTERN = Pattern.compile("(ftp://|ftp:)([A-z0-9_\\-\\.]*)(:)([0-9]*)(/)(.*)");

    private String url;

    public Destination(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    /**
     * Get the separator from url of FTP or Filesystem
     * This is to make sure that FTP separator is always '/' and for File system, it depends on
     * platform (means that '/' for Unix/Linux and '\' for Windows)
     *
     * @return separator string
     */
    public String getFileSeparator() {
        if (isFTP()) {
            return "/";
        } else {
            return File.separator;
        }
    }

    /**
     * Build a new destination from the current url and passed-in options
     *
     * @param options to append to url
     * @return new destination with more options
     */

    public Destination appendOptions(final String options) {
        return new Destination(Destination.appendOptions(url, options));
    }

    /**
     * Create a new url by appending `options` to `originalString`.
     * @param originalString the url want to add more option
     * @param options extended option
     * @return new url with more options
     */
    public static String appendOptions(final String originalString, final String options) {
        if (originalString.indexOf("?")>0) {
            return originalString + "&" + options;
        } else {
            return originalString + "?" + options;
        }
    }

    /**
     * Set additional settings for FTP/SFTP Destination
     * 
     * @param dest
     * @param useLocalWorkDir
     * @return
     */
    public static Destination buildDefaultFTPProps(final Destination dest, Boolean useLocalWorkDir) {
        Destination newDest = dest;
        if (newDest.isFTP()) {
            newDest = newDest.appendOptions("stepwise=false");
            if (useLocalWorkDir) {
                newDest = newDest.appendOptions("localWorkDirectory=/tmp");
            }
        }
        return newDest;
    }
    
    /**
     * Get root folder without options from url property
     * Expected:
     *  - sftp://host:port/path/to/folder?option1=value1 -> path/to/folder
     *  - ftp://host:port/path/to/folder?option1=value1  -> ftp://host:port/path/to/folder
     *  - file://path/to/folder?option1=value1           -> file://path/to/folder
     * @return target folder
     */
    public String getRootFolder() {
        String findingRootFolder = url;
        Matcher m = SFTP_URL_PATTERN.matcher(findingRootFolder);
        if (m.find()) {
            findingRootFolder = m.group(6);
        }
        if (findingRootFolder.indexOf("?") >= 0) {
            findingRootFolder = findingRootFolder.split("\\?")[0];
        }
        Matcher fileMatcher = FILE_URL_PATTERN.matcher(findingRootFolder);
        if(fileMatcher.find()){
            findingRootFolder = fileMatcher.group(2);
        }
        Matcher ftpMatcher = FTP_URL_PATTERN.matcher(findingRootFolder);
        if(ftpMatcher.find()){
            findingRootFolder = ftpMatcher.group(6);
        }
        return findingRootFolder;
    }

    public boolean isFTP() {
        if (url.startsWith(Messaging.Names.COMPONENT_TYPE_FTP.toString()) ||
            url.startsWith(Messaging.Names.COMPONENT_TYPE_SFTP.toString())) {
            return true;
        } else if (url.startsWith(Messaging.Names.COMPONENT_TYPE_FILE.toString())) {
            return false;
        } else {
            throw new SandataRuntimeException(String.format("Not supported url %s", url));
        }
    }

}

