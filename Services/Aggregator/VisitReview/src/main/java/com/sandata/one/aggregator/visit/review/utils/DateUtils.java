package com.sandata.one.aggregator.visit.review.utils;

import com.sandata.one.aggregator.visit.review.model.ReviewVisitStaffResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by thanhxle on 2/7/2017.
 */
public class DateUtils {

    public static Date getLatestHiredDate (List<ReviewVisitStaffResult> source) {
        Date maxHiredDate = source.get(0).getHireDate(); // take first as MaxVal
        for (int i = 0; i < source.size(); i++) {
            //if current is less then MaxVal
            if(source.get(i).getHireDate() != null){
                if (maxHiredDate != null && source.get(i).getHireDate().before(maxHiredDate)) {
                    maxHiredDate = source.get(i).getHireDate();
                }

                if (maxHiredDate == null) {
                    maxHiredDate = source.get(i).getHireDate();
                }
            }
        }
        return maxHiredDate;
    }


    public static Date getLatestTermDate (List<ReviewVisitStaffResult> source) {
        Date maxTermDate = source.get(0).getTermDate(); // take first as MaxVal
        for (int i = 0; i < source.size(); i++) {
            //if current is less then MaxVal
            if(source.get(i).getTermDate() != null){
                if (maxTermDate != null && source.get(i).getTermDate().after(maxTermDate)){
                    maxTermDate = source.get(i).getTermDate();
                }

                if (maxTermDate == null) {
                    maxTermDate = source.get(i).getTermDate();
                }
            }
        }
        return maxTermDate;
    }

    public static Date getEarliestTermDate (List<ReviewVisitStaffResult> source) {
        Date minTermDate = source.get(0).getTermDate(); // take first as MaxVal
        for (int i = 0; i < source.size(); i++) {
            //if current is less then MaxVal
            if(source.get(i).getTermDate() != null && source.get(i).getTermDate().before(minTermDate)){
                minTermDate = source.get(i).getTermDate();
            }
        }
        return minTermDate;
    }


    public static Date sortAndGetHireDateClosestBeforeVisitDate (List<ReviewVisitStaffResult> source) {
        List<ReviewVisitStaffResult> result = new ArrayList<>();
        Date dateToCompare = source.get(0).getSchedEvntDate();
        for (int i = 0; i < source.size(); i++) {
            //if current is less then MaxVal
            if(source.get(i).getTermDate() != null && source.get(i).getTermDate().before(dateToCompare)){
                result.add(source.get(i)) ;
            }
        }

        //then get the max one
        if (!result.isEmpty()) {
            return getLatestHiredDate(result);
        } else {
            return getLatestHiredDate(source);
        }

    }


    public static Date sortAndGetTermDateClosestAfterVisitDate (List<ReviewVisitStaffResult> source) {
        List<ReviewVisitStaffResult> result = new ArrayList<>();
        Date dateToCompare = source.get(0).getSchedEvntDate();
        for (int i = 0; i < source.size(); i++) {
            //if current is less then MaxVal
            if(source.get(i).getTermDate() != null && source.get(i).getTermDate().after(dateToCompare)){
                result.add(source.get(i)) ;
            }
        }

        if (!result.isEmpty()) {
            //then get the max one
            return getEarliestTermDate(result);
        } else {
            return getEarliestTermDate(source);
        }
    }


}
