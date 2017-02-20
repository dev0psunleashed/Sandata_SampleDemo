package com.sandata.lab.data.model.dl.model.extended;


import com.sandata.lab.data.model.dl.model.VisitTaskList;

import java.io.Serializable;
import java.util.List;

public class VisitEventExt extends com.sandata.lab.data.model.dl.model.VisitEvent {

    private static final long serialVersionUID = 1L;
    public List<VisitTaskList> getVisitTaskLists() {
        return visitTaskLists;
    }

    public void setVisitTaskLists(List<VisitTaskList> visitTaskLists) {
        this.visitTaskLists = visitTaskLists;
    }

    private List<VisitTaskList> visitTaskLists;

    private String sid;
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}


}
