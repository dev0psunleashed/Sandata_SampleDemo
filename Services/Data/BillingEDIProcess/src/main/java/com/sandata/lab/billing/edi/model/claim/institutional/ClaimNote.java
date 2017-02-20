package com.sandata.lab.billing.edi.model.claim.institutional;

import com.sandata.lab.billing.edi.model.enums.NoteCodeType;

public class ClaimNote {
    private long id;
    private long claimId;

    private NoteCodeType type;
    private String note;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClaimId() {
        return claimId;
    }

    public void setClaimId(long claimId) {
        this.claimId = claimId;
    }

    public NoteCodeType getType() {
        return type;
    }

    public void setType(NoteCodeType type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}