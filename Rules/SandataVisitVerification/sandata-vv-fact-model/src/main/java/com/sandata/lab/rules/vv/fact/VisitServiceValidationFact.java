package com.sandata.lab.rules.vv.fact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sandata.lab.data.model.base.BaseObject;

public class VisitServiceValidationFact extends BaseObject {

	private static final long serialVersionUID = 1L;
	
	private List<String> servicesList;

	
    public List<String> getServicesList() {
		return servicesList;
	}


	public void setServicesList(List<String> servicesList) {
		this.servicesList = servicesList;
	}


	public VisitServiceValidationFact(){}


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof VisitServiceValidationFact)) {
            return false;
        }
        VisitServiceValidationFact that = (VisitServiceValidationFact ) obj;

        return equalLists(servicesList, that.getServicesList()) ;
    }
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (servicesList != null ? servicesList.hashCode() : 0);
        return result;
    }
    
    public  boolean equalLists(List<String> one, List<String> two){     
        if (one == null && two == null){
            return true;
        }

        if((one == null && two != null) 
          || one != null && two == null
          || one.size() != two.size()){
            return false;
        }

        //to avoid messing the order of the lists we will use a copy
        //as noted in comments by A. R. S.
        one = new ArrayList<String>(one); 
        two = new ArrayList<String>(two);   

        Collections.sort(one);
        Collections.sort(two);      
        return one.equals(two);
    }
	
}
