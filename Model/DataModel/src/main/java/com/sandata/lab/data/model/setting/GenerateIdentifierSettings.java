package com.sandata.lab.data.model.setting;

import com.google.gson.annotations.SerializedName;
import com.sandata.lab.data.model.base.BaseObject;

public class GenerateIdentifierSettings extends BaseObject {
    
	private static final long serialVersionUID = 1L;

	@SerializedName("MaxLength")
	protected int maxLength;

	@SerializedName("MinLength")
	protected int minLength;

	@SerializedName("Type")
	protected String type = "NUMERIC";   // We will only support NUMERIC IDs for this version.

	@SerializedName("PadWithLeadingZeros")
	protected boolean padWithLeadingZeros;	  

	@SerializedName("FixedLength")
	protected int fixedLength;
	
	@SerializedName("FixedLengthStartDigit")
	protected int fixedLengthStartDigit;
	
	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public int getMinLength() {
		return minLength;
	}

	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isPadWithLeadingZeros() {
		return padWithLeadingZeros;
	}

	public void setPadWithLeadingZeros(boolean padWithLeadingZeros) {
		this.padWithLeadingZeros = padWithLeadingZeros;
	}

    /**
     * @return the fixedLength
     */
    public int getFixedLength() {
        return fixedLength;
    }

    /**
     * @param fixedLength the fixedLength to set
     */
    public void setFixedLength(int fixedLength) {
        this.fixedLength = fixedLength;
    }

    /**
     * @return the fixedLengthStartDigit
     */
    public int getFixedLengthStartDigit() {
        return fixedLengthStartDigit;
    }

    /**
     * @param fixedLengthStartDigit the fixedLengthStartDigit to set
     */
    public void setFixedLengthStartDigit(int fixedLengthStartDigit) {
        this.fixedLengthStartDigit = fixedLengthStartDigit;
    }
}
