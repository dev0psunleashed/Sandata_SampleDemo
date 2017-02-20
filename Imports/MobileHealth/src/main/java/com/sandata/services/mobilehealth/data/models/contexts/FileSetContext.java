package com.sandata.services.mobilehealth.data.models.contexts;


public class FileSetContext {

    private String cc;
    private String extDone;
    private String extError;
    private String extInit;
    private String fileName;
    private String filePattern;
    private String folderInbox;
    private String folderSave;
    private String newFile;
    private Boolean isTest;
    /**
     * @return the cc
     */
    public String getCc() {
        return cc;
    }
    /**
     * @param cc the cc to set
     */
    public void setCc(String cc) {
        this.cc = cc;
    }
    /**
     * @return the extDone
     */
    public String getExtDone() {
        return extDone;
    }
    /**
     * @param extDone the extDone to set
     */
    public void setExtDone(String extDone) {
        this.extDone = extDone;
    }
    /**
     * @return the extError
     */
    public String getExtError() {
        return extError;
    }
    /**
     * @param extError the extError to set
     */
    public void setExtError(String extError) {
        this.extError = extError;
    }
    /**
     * @return the extInit
     */
    public String getExtInit() {
        return extInit;
    }
    /**
     * @param extInit the extInit to set
     */
    public void setExtInit(String extInit) {
        this.extInit = extInit;
    }
    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }
    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    /**
     * @return the filePattern
     */
    public String getFilePattern() {
        return filePattern;
    }
    /**
     * @param filePattern the filePattern to set
     */
    public void setFilePattern(String filePattern) {
        this.filePattern = filePattern;
    }
    /**
     * @return the folderInbox
     */
    public String getFolderInbox() {
        return folderInbox;
    }
    /**
     * @param folderInbox the folderInbox to set
     */
    public void setFolderInbox(String folderInbox) {
        this.folderInbox = folderInbox;
    }
    /**
     * @return the folderSave
     */
    public String getFolderSave() {
        return folderSave;
    }
    /**
     * @param folderSave the folderSave to set
     */
    public void setFolderSave(String folderSave) {
        this.folderSave = folderSave;
    }
    /**
     * @return the newFile
     */
    public String getNewFile() {
        return newFile;
    }
    /**
     * @param newFile the newFile to set
     */
    public void setNewFile(String newFile) {
        this.newFile = newFile;
    }
    /**
     * @return the isTest
     */
    public Boolean getIsTest() {
        return isTest;
    }
    /**
     * @param isTest the isTest to set
     */
    public void setIsTest(Boolean isTest) {
        this.isTest = isTest;
    }
}
