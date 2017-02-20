package com.sandata.lab.rest.visit.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

public class AuditVisitNote extends BaseObject {

    private static final long serialVersionUID = -6739469077328999143L;

    @SerializedName("VisitNoteTypeName")
    private String visitNoteTypeName;

    @SerializedName("VisitNote")
    private String visitNote;

    /**
     * @return the visitNoteTypeName
     */
    public String getVisitNoteTypeName() {
        return visitNoteTypeName;
    }

    /**
     * @param visitNoteTypeName the visitNoteTypeName to set
     */
    public void setVisitNoteTypeName(String visitNoteTypeName) {
        this.visitNoteTypeName = visitNoteTypeName;
    }

    /**
     * @return the visitNote
     */
    public String getVisitNote() {
        return visitNote;
    }

    /**
     * @param visitNote the visitNote to set
     */
    public void setVisitNote(String visitNote) {
        this.visitNote = visitNote;
    }
}
