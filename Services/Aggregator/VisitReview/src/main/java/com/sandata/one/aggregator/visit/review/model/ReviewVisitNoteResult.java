package com.sandata.one.aggregator.visit.review.model;

import java.util.Date;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

public class ReviewVisitNoteResult extends BaseObject {

    private static final long serialVersionUID = -6650002700921398850L;

    @SerializedName("NoteType")
    private String noteType;

    @SerializedName("Note")
    private String note;

    @SerializedName("DateCreated")
    private Date dateCreated;

    /**
     * @return the noteType
     */
    public String getNoteType() {
        return noteType;
    }

    /**
     * @param noteType the noteType to set
     */
    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }

    /**
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return the dateCreated
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @SerializedName("CreatedBy")
    private String createdBy;
}
