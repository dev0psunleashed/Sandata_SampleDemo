package com.sandata.lab.rest.visit.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class VisitRelatedSK {

    private final String bsnEntID;
    private final Long visitSK;

    public VisitRelatedSK(String bsnEntID, Long visitSK) {
        this.bsnEntID = bsnEntID;
        this.visitSK = visitSK;
    }

    private List<Long> visitExceptions;
    private List<Long> visitActivities;
    private List<Long> visitAuth;
    private List<Long> visitDocXwalk;
    private List<Long> visitEvents;
    private List<Long> visitHistory;
    private List<Long> visitNotes;
    private List<Long> visitTasks;
    private List<Long> visitVerification;

    public Object[] getParams() {

        List objects = new ArrayList();

        objects.add(String.format("%s|%d", bsnEntID, visitSK));

        if (visitExceptions != null) {
            for (Long skValue : visitExceptions) {
                objects.add(String.format("%s|%d", bsnEntID, skValue));
            }
        }

        if (visitActivities != null) {
            for (Long skValue : visitActivities) {
                objects.add(String.format("%s|%d", bsnEntID, skValue));
            }
        }

        if (visitAuth != null) {
            for (Long skValue : visitAuth) {
                objects.add(String.format("%s|%d", bsnEntID, skValue));
            }
        }

        if (visitDocXwalk != null) {
            for (Long skValue : visitDocXwalk) {
                objects.add(String.format("%s|%d", bsnEntID, skValue));
            }
        }

        if (visitEvents != null) {
            for (Long skValue : visitEvents) {
                objects.add(String.format("%s|%d", bsnEntID, skValue));
            }
        }

        if (visitHistory != null) {
            for (Long skValue : visitHistory) {
                objects.add(String.format("%s|%d", bsnEntID, skValue));
            }
        }

        if (visitNotes != null) {
            for (Long skValue : visitNotes) {
                objects.add(String.format("%s|%d", bsnEntID, skValue));
            }
        }

        if (visitTasks != null) {
            for (Long skValue : visitTasks) {
                objects.add(String.format("%s|%d", bsnEntID, skValue));
            }
        }

        if (visitVerification != null) {
            for (Long skValue : visitVerification) {
                objects.add(String.format("%s|%d", bsnEntID, skValue));
            }
        }

        return objects.toArray();
    }

    public void addVisitException(BigDecimal value) {
        if (value != null) {

            if (visitExceptions == null) {
                visitExceptions = new ArrayList<>();
            }

            visitExceptions.add(value.longValue());
        }
    }

    public void addVisitActivities(BigDecimal value) {
        if (value != null) {

            if (visitActivities == null) {
                visitActivities = new ArrayList<>();
            }

            visitActivities.add(value.longValue());
        }
    }

    public void addVisitAuth(BigDecimal value) {
        if (value != null) {

            if (visitAuth == null) {
                visitAuth = new ArrayList<>();
            }

            visitAuth.add(value.longValue());
        }
    }

    public void addVisitDocXwalk(BigDecimal value) {
        if (value != null) {

            if (visitDocXwalk == null) {
                visitDocXwalk = new ArrayList<>();
            }

            visitDocXwalk.add(value.longValue());
        }
    }

    public void addVisitEvents(BigDecimal value) {
        if (value != null) {

            if (visitEvents == null) {
                visitEvents = new ArrayList<>();
            }

            visitEvents.add(value.longValue());
        }
    }

    public void addVisitHistory(BigDecimal value) {
        if (value != null) {

            if (visitHistory == null) {
                visitHistory = new ArrayList<>();
            }

            visitHistory.add(value.longValue());
        }
    }

    public void addVisitNotes(BigDecimal value) {
        if (value != null) {

            if (visitNotes == null) {
                visitNotes = new ArrayList<>();
            }

            visitNotes.add(value.longValue());
        }
    }

    public void addVisitTasks(BigDecimal value) {
        if (value != null) {

            if (visitTasks == null) {
                visitTasks = new ArrayList<>();
            }

            visitTasks.add(value.longValue());
        }
    }

    public void addVisitVerification(BigDecimal value) {
        if (value != null) {

            if (visitVerification == null) {
                visitVerification = new ArrayList<>();
            }

            visitVerification.add(value.longValue());
        }
    }
}
