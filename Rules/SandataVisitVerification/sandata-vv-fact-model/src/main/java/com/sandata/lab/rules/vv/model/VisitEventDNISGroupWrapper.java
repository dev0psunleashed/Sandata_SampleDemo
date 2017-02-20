package com.sandata.lab.rules.vv.model;

import org.apache.commons.lang3.SerializationUtils;

import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.model.collection.VisitEventDNISGroup;

/**
 * Created by thanhxle on 11/11/2016.
 */
public class VisitEventDNISGroupWrapper extends BaseObject {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private VisitEventDNISGroup visitEventDNISGroup;

    //this flag is set while running rules
    private boolean isInvalidDNIS;

    public VisitEventDNISGroup getVisitEventDNISGroup() {
        return visitEventDNISGroup;
    }

    public void setVisitEventDNISGroup(VisitEventDNISGroup visitEventDNISGroup) {
        this.visitEventDNISGroup =  SerializationUtils.clone(visitEventDNISGroup);
    }

    public boolean isInvalidDNIS() {
        return isInvalidDNIS;
    }

    public void setInvalidDNIS(boolean invalidDNIS) {
        isInvalidDNIS = invalidDNIS;
    }
}
