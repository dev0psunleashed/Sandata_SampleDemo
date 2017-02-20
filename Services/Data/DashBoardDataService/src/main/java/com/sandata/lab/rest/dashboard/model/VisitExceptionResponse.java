/**
 * 
 */
package com.sandata.lab.rest.dashboard.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;


/**
 * 
 * Create a Visit exception reponse to caller
 * 
 * @author thanhxle
 *
 * @Description :
 *
 * @date Apr 7, 2016
 * 
 * 
 */
public class VisitExceptionResponse extends BaseObject  {

    private static final long serialVersionUID = 1L;
    
    @SerializedName("BusinessEntityID")
    private String businessEntityId;

    @SerializedName("BusinessEntityLevel")
    private int businessEntityLevel;

    @SerializedName("VisitExceptions")
    private List<VisitException> visitExceptions;
    
    
    /**
     * Getters and setters
     */

    public List<VisitException> getVisitExceptions() {
        return visitExceptions;
    }

    public String getBusinessEntityId() {
        return businessEntityId;
    }

    public void setBusinessEntityId(String businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    public int getBusinessEntityLevel() {
        return businessEntityLevel;
    }

    public void setBusinessEntityLevel(int businessEntityLevel) {
        this.businessEntityLevel = businessEntityLevel;
    }

    public void setVisitExceptions(List<VisitException> visitExceptions) {
        this.visitExceptions = visitExceptions;
    }
    
}
