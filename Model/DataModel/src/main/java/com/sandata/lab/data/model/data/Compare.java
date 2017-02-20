package com.sandata.lab.data.model.data;

import com.sandata.lab.data.model.base.BaseObject;

/**
 * Date: 9/5/16
 * Time: 8:05 PM
 */

public class Compare extends BaseObject {

    private static final long serialVersionUID = 1L;

    private Object original;
    private Object updated;

    public Object getOriginal() {
        return original;
    }

    public void setOriginal(Object original) {
        this.original = original;
    }

    public Object getUpdated() {
        return updated;
    }

    public void setUpdated(Object updated) {
        this.updated = updated;
    }
}
