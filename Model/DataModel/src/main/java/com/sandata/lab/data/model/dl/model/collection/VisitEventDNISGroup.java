package com.sandata.lab.data.model.dl.model.collection;

import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.extended.VisitEventExt;

import java.io.Serializable;
import java.util.List;

public class VisitEventDNISGroup extends BaseObject {

    private static final long serialVersionUID = 1L;

    private String dnis;
    private List<VisitEventExt> visitEvents;

    public String getDnis() {
        return dnis;
    }

    public void setDnis(String dnis) {
        this.dnis = dnis;
    }

    public List<VisitEventExt> getVisitEvents() {
        return visitEvents;
    }

    public void setVisitEvents(List<VisitEventExt> visitEvents) {
        this.visitEvents = visitEvents;
    }
}
