package com.sandata.lab.eligibility.model.response;

import com.google.gson.annotations.SerializedName;

public class ResponseError
{
	@SerializedName("id")
    public long id;
	
	@SerializedName("ResponseMessageId")
    public long responseMessageId;

	@SerializedName("ResponseMessage")
    public ResponseMessage responseMessage;

    public enum LevelType
    {
        Subelement,
        Element,
        Segment,
        Loop,
        Form;

        public int getValue()
        {
            return this.ordinal();
        }

        public static LevelType forValue(int value)
        {
            return values()[value];
        }
    }
    public enum SeverityType
    {
        Warning,
        Fatal;

        public int getValue()
        {
            return this.ordinal();
        }

        public static SeverityType forValue(int value)
        {
            return values()[value];
        }
    }
    public LevelType level = LevelType.values()[0];
    public SeverityType severity = SeverityType.values()[0];

    public String loop;
    public String segment;
    public int element;
    public int subelement;
    public int repetition;

    public ResponseError()
    {
        loop = "";
        segment = "";
        element = -1;
        subelement = -1;
    }

    public String message;
}