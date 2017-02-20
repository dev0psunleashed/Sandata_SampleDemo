package com.sandata.lab.data.model.dl.model;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import com.sandata.lab.data.model.dl.annotation.Mapping;

/**
 * Model to help map the results of a distinct column operation.
 * <p/>
 *
 * @author David Rutgos
 */
public class DistinctColumn extends BaseObject {

    private static final long serialVersionUID = 1L;

    @SerializedName("Result")
    @Mapping(getter = "getResult", setter = "setResult", type = "String", index = 0)
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
