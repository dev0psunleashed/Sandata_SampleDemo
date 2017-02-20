package com.sandata.lab.rest.am.model;

import com.sandata.lab.data.model.base.BaseObject;

import java.util.List;

/**
 * Models the request to add/update the List Item records;
 * <p/>
 *
 * @author David Rutgos
 */
public class PayerListItemRequest extends BaseObject {

    private static final long serialVersionUID = 1L;

    private List<String> listItems;
    private String payerId;
    private String businessEntityId;

    public List<String> getListItems() {
        return listItems;
    }

    public void setListItems(List<String> listItems) {
        this.listItems = listItems;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getBusinessEntityId() {
        return businessEntityId;
    }

    public void setBusinessEntityId(String businessEntityId) {
        this.businessEntityId = businessEntityId;
    }
}
