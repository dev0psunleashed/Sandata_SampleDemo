package com.sandata.lab.data.model.dl.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Date: 10/14/15
 * Time: 2:04 PM
 */

public class FindVisitResultCollection {

    // Keep track of the visits that are combined into one. This will be subtracted from the total count in the result
    private int consolidatedCount = 0;

    private Map<BigInteger, Integer> visitMap;
    List<FindVisitResult> findVisitResults;

    public List<FindVisitResult> getResult() {
        return findVisitResults;
    }

    public int getConsolidatedCount() {
        return this.consolidatedCount;
    }

    public void add(final FindVisitResult findVisitResult) {

        if (visitMap == null) {
            visitMap = new LinkedHashMap<>();
        }

        if (findVisitResults == null) {
            findVisitResults = new ArrayList<>();
        }

        int index = findVisitResults.size();

        Integer visitIndex = visitMap.get(findVisitResult.getVisit().getVisitSK());
        if (visitIndex != null) {

            consolidatedCount++;

            FindVisitResult exitingVisit = findVisitResults.get(visitIndex);

            // Append Visit Exceptions
            if (findVisitResult.getVisit().getVisitException() != null) {

                for (VisitException visitException : findVisitResult.getVisit().getVisitException()) {
                    exitingVisit.getVisit().getVisitException().add(visitException);
                }
            }

            // Calls are sorted by VISIT_EVNT_DTIME ASC and therefore the earliest time would be the callin and the latest will be the callout.
            exitingVisit.setCallOut(findVisitResult.getCallIn());
        }
        else {
            // Visit doesn't exist, add it
            visitMap.put(findVisitResult.getVisit().getVisitSK(), index);
            findVisitResults.add(index, findVisitResult);
        }
    }
}
