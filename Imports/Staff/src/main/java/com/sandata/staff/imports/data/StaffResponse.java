package com.sandata.staff.imports.data;

import java.util.ArrayList;

public class StaffResponse
{

  ArrayList<Error> Errors;
  int SucceededCount;

    int FailedCount;
    String Status;

    public int getFailedCount() {
        return FailedCount;
    }

    public void setFailedCount(int failedCount) {
        FailedCount = failedCount;
    }


    public ArrayList<Error> getErrors() {
        return Errors;
    }

    public void setErrors(ArrayList<Error> errors) {
        Errors = errors;
    }

    public int getSucceededCount() {
        return SucceededCount;
    }

    public void setSucceededCount(int succeededCount) {
        SucceededCount = succeededCount;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }


}
