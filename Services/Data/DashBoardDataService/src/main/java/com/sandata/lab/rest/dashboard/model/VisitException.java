/**
 * 
 */
package com.sandata.lab.rest.dashboard.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

import java.util.Date;


/**
 * 
 * Create a Visit exception
 * 
 * @author thanhxle
 *
 * @Description :
 *
 * @date Apr 7, 2016
 * 
 * 
 */
public class VisitException extends BaseObject  {

    private static final long serialVersionUID = 1L;
    
    @SerializedName("VisitExceptionName")
    private String visitExcpName;

    @SerializedName("VisitExceptionSK")
    private String visitExceptionSK;
    
    //number total of this exception
    @SerializedName("Value")
    private String total;

    @SerializedName("VisitSK")
    private String visitSK;

    @SerializedName("VisitDate")
    private Date visitDate;
    /**
     * Getters and setters
     */
    

    public String getVisitExcpName() {
        return visitExcpName;
    }

    public void setVisitExcpName(String visitExcpName) {
        this.visitExcpName = visitExcpName;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getVisitSK() {
        return visitSK;
    }

    public void setVisitSK(String visitSK) {
        this.visitSK = visitSK;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getVisitExceptionSK() {
        return visitExceptionSK;
    }

    public void setVisitExceptionSK(String visitExceptionSK) {
        this.visitExceptionSK = visitExceptionSK;
    }

}
