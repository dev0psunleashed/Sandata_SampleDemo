package com.sandata.lab.rules.vv.fact;

import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.rules.vv.model.VisitState;

/**
 * @author thanhxle
 */
public class AgencyGPSSetting extends BaseObject {
    private static  final long serialVersionUID = 1L;

    private double distanceThreshold;
    public AgencyGPSSetting(){}

    public double getDistanceThreshold() {
        return distanceThreshold;
    }

    public void setDistanceThreshold(double distanceThreshold) {
        this.distanceThreshold = distanceThreshold;
    }
}
