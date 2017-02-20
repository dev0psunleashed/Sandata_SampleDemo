package com.sandata.lab.data.model.dl.model.extended.schedule;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;
import org.joda.time.LocalTime;

/**
 * Model to capture user selections from UI when creating/updating a <code>Schedule</code>
 *
 * Created by khangle on 09/12/2016.
 */
public class SelectedDaysOfWeek extends BaseObject {
    private static final long serialVersionUID = 1L;

    @SerializedName("All")
    protected boolean all;

    @SerializedName("Sunday")
    protected boolean sunday;

    @SerializedName("SundayOvernight")
    protected boolean sundayOvernight;

    @SerializedName("SundayStart")
    protected LocalTime sundayStart;

    @SerializedName("SundayEnd")
    protected LocalTime sundayEnd;

    @SerializedName("Monday")
    protected boolean monday;

    @SerializedName("MondayOvernight")
    protected boolean mondayOvernight;

    @SerializedName("MondayStart")
    protected LocalTime mondayStart;

    @SerializedName("MondayEnd")
    protected LocalTime mondayEnd;

    @SerializedName("Tuesday")
    protected boolean tuesday;

    @SerializedName("TuesdayOvernight")
    protected boolean tuesdayOvernight;

    @SerializedName("TuesdayStart")
    protected LocalTime tuesdayStart;

    @SerializedName("TuesdayEnd")
    protected LocalTime tuesdayEnd;

    @SerializedName("Wednesday")
    protected boolean wednesday;

    @SerializedName("WednesdayOvernight")
    protected boolean wednesdayOvernight;

    @SerializedName("WednesdayStart")
    protected LocalTime wednesdayStart;

    @SerializedName("WednesdayEnd")
    protected LocalTime wednesdayEnd;

    @SerializedName("ThursdayOvernight")
    protected boolean thursdayOvernight;

    @SerializedName("Thursday")
    protected boolean thursday;

    @SerializedName("ThursdayStart")
    protected LocalTime thursdayStart;

    @SerializedName("ThursdayEnd")
    protected LocalTime thursdayEnd;

    @SerializedName("Friday")
    protected boolean friday;

    @SerializedName("FridayOvernight")
    protected boolean fridayOvernight;

    @SerializedName("FridayStart")
    protected LocalTime fridayStart;

    @SerializedName("FridayEnd")
    protected LocalTime fridayEnd;

    @SerializedName("Saturday")
    protected boolean saturday;

    @SerializedName("SaturdayOvernight")
    protected boolean saturdayOvernight;

    @SerializedName("SaturdayStart")
    protected LocalTime saturdayStart;

    @SerializedName("SaturdayEnd")
    protected LocalTime saturdayEnd;

    public boolean isAll() {
        return all;
    }

    public void setAll(boolean all) {
        this.all = all;
    }

    public boolean isSunday() {
        return sunday;
    }

    public void setSunday(boolean sunday) {
        this.sunday = sunday;
    }

    public LocalTime getSundayStart() {
        return sundayStart;
    }

    public void setSundayStart(LocalTime sundayStart) {
        this.sundayStart = sundayStart;
    }

    public LocalTime getSundayEnd() {
        return sundayEnd;
    }

    public void setSundayEnd(LocalTime sundayEnd) {
        this.sundayEnd = sundayEnd;
    }

    public boolean isMonday() {
        return monday;
    }

    public void setMonday(boolean monday) {
        this.monday = monday;
    }

    public LocalTime getMondayStart() {
        return mondayStart;
    }

    public void setMondayStart(LocalTime mondayStart) {
        this.mondayStart = mondayStart;
    }

    public LocalTime getMondayEnd() {
        return mondayEnd;
    }

    public void setMondayEnd(LocalTime mondayEnd) {
        this.mondayEnd = mondayEnd;
    }

    public boolean isTuesday() {
        return tuesday;
    }

    public void setTuesday(boolean tuesday) {
        this.tuesday = tuesday;
    }

    public LocalTime getTuesdayStart() {
        return tuesdayStart;
    }

    public void setTuesdayStart(LocalTime tuesdayStart) {
        this.tuesdayStart = tuesdayStart;
    }

    public LocalTime getTuesdayEnd() {
        return tuesdayEnd;
    }

    public void setTuesdayEnd(LocalTime tuesdayEnd) {
        this.tuesdayEnd = tuesdayEnd;
    }

    public boolean isWednesday() {
        return wednesday;
    }

    public void setWednesday(boolean wednesday) {
        this.wednesday = wednesday;
    }

    public LocalTime getWednesdayStart() {
        return wednesdayStart;
    }

    public void setWednesdayStart(LocalTime wednesdayStart) {
        this.wednesdayStart = wednesdayStart;
    }

    public LocalTime getWednesdayEnd() {
        return wednesdayEnd;
    }

    public void setWednesdayEnd(LocalTime wednesdayEnd) {
        this.wednesdayEnd = wednesdayEnd;
    }

    public boolean isThursday() {
        return thursday;
    }

    public void setThursday(boolean thursday) {
        this.thursday = thursday;
    }

    public LocalTime getThursdayStart() {
        return thursdayStart;
    }

    public void setThursdayStart(LocalTime thursdayStart) {
        this.thursdayStart = thursdayStart;
    }

    public LocalTime getThursdayEnd() {
        return thursdayEnd;
    }

    public void setThursdayEnd(LocalTime thursdayEnd) {
        this.thursdayEnd = thursdayEnd;
    }

    public boolean isFriday() {
        return friday;
    }

    public void setFriday(boolean friday) {
        this.friday = friday;
    }

    public LocalTime getFridayStart() {
        return fridayStart;
    }

    public void setFridayStart(LocalTime fridayStart) {
        this.fridayStart = fridayStart;
    }

    public LocalTime getFridayEnd() {
        return fridayEnd;
    }

    public void setFridayEnd(LocalTime fridayEnd) {
        this.fridayEnd = fridayEnd;
    }

    public boolean isSaturday() {
        return saturday;
    }

    public void setSaturday(boolean saturday) {
        this.saturday = saturday;
    }

    public LocalTime getSaturdayStart() {
        return saturdayStart;
    }

    public void setSaturdayStart(LocalTime saturdayStart) {
        this.saturdayStart = saturdayStart;
    }

    public LocalTime getSaturdayEnd() {
        return saturdayEnd;
    }

    public void setSaturdayEnd(LocalTime saturdayEnd) {
        this.saturdayEnd = saturdayEnd;
    }

    public boolean isSundayOvernight() {
        return sundayOvernight;
    }

    public void setSundayOvernight(boolean sundayOvernight) {
        this.sundayOvernight = sundayOvernight;
    }

    public boolean isMondayOvernight() {
        return mondayOvernight;
    }

    public void setMondayOvernight(boolean mondayOvernight) {
        this.mondayOvernight = mondayOvernight;
    }

    public boolean isTuesdayOvernight() {
        return tuesdayOvernight;
    }

    public void setTuesdayOvernight(boolean tuesdayOvernight) {
        this.tuesdayOvernight = tuesdayOvernight;
    }

    public boolean isWednesdayOvernight() {
        return wednesdayOvernight;
    }

    public void setWednesdayOvernight(boolean wednesdayOvernight) {
        this.wednesdayOvernight = wednesdayOvernight;
    }

    public boolean isThursdayOvernight() {
        return thursdayOvernight;
    }

    public void setThursdayOvernight(boolean thursdayOvernight) {
        this.thursdayOvernight = thursdayOvernight;
    }

    public boolean isFridayOvernight() {
        return fridayOvernight;
    }

    public void setFridayOvernight(boolean fridayOvernight) {
        this.fridayOvernight = fridayOvernight;
    }

    public boolean isSaturdayOvernight() {
        return saturdayOvernight;
    }

    public void setSaturdayOvernight(boolean saturdayOvernight) {
        this.saturdayOvernight = saturdayOvernight;
    }
}
