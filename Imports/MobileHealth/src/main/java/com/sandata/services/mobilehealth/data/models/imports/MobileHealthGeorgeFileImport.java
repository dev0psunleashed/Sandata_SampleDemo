package com.sandata.services.mobilehealth.data.models.imports;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.sandata.up.commons.BaseObject;

@FixedLengthRecord(length = 148)
public class MobileHealthGeorgeFileImport extends BaseObject implements Comparable<MobileHealthGeorgeFileImport> {
    private static final long serialVersionUID = 10101010101L;

    @DataField(pos = 1, length = 3)
    private String vendor;

    @DataField(pos = 4, length = 6)
    private String empSSNum;

    @DataField(pos = 10, length = 6)
    private String physicalDate;

    @DataField(pos = 16, length = 6)
    private String tetanusDate;

    @DataField(pos = 22, length = 6)
    private String ppdDate;

    @DataField(pos = 28, length = 6)
    private String xrayDate;

    @DataField(pos = 34, length = 1)
    private String ppdTest;

    @DataField(pos = 35, length = 6)
    private String hbvDate1;

    @DataField(pos = 41, length = 6)
    private String hbvDate2;

    @DataField(pos = 47, length = 6)
    private String hbvDate3;

    @DataField(pos = 53, length = 6)
    private String hbvTitre1;

    @DataField(pos = 59, length = 1)
    private String hbvPositive;

    @DataField(pos = 60, length = 6)
    private String ppdStep1date;

    @DataField(pos = 66, length = 1)
    private String ppd2Test;

    @DataField(pos = 67, length = 6)
    private String fingerPrintDate;

    @DataField(pos = 73, length = 6)
    private String hbvDate4;

    @DataField(pos = 79, length = 6)
    private String hbvDate5;

    @DataField(pos = 85, length = 6)
    private String hbvDate6;

    @DataField(pos = 91, length = 1)
    private String hbvPositive2;

    @DataField(pos = 92, length = 6)
    private String drugTestDate;

    @DataField(pos = 98, length = 1)
    private String drugTestResult;

    @DataField(pos = 99, length = 6)
    private String rubellaDate;

    @DataField(pos = 105, length = 2)
    private String rubellaResult;

    @DataField(pos = 107, length = 6)
    private String rubellaResultReading;

    @DataField(pos = 113, length = 6)
    private String measlesDate;

    @DataField(pos = 119, length = 2)
    private String measlesResult;

    @DataField(pos = 121, length = 6)
    private String measlesResultReading;

    @DataField(pos = 127, length = 6)
    private String hbvTitre2;

    @DataField(pos = 133, length = 9)
    private String sSSN;

    @DataField(pos = 142, length = 1)
    private String xrayCode;

    @DataField(pos = 143, length = 6)
    private String influenzaDate;
    
    /**
     * @return the vendor
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * @param vendor the vendor to set
     */
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    /**
     * @return the empSSNum
     */
    public String getEmpSSNum() {
        return empSSNum;
    }

    /**
     * @param empSSNum the empSSNum to set
     */
    public void setEmpSSNum(String empSSNum) {
        this.empSSNum = empSSNum;
    }

    /**
     * @return the physicalDate
     */
    public String getPhysicalDate() {
        return physicalDate;
    }

    /**
     * @param physicalDate the physicalDate to set
     */
    public void setPhysicalDate(String physicalDate) {
        this.physicalDate = physicalDate;
    }

    /**
     * @return the tetanusDate
     */
    public String getTetanusDate() {
        return tetanusDate;
    }

    /**
     * @param tetanusDate the tetanusDate to set
     */
    public void setTetanusDate(String tetanusDate) {
        this.tetanusDate = tetanusDate;
    }

    /**
     * @return the ppdDate
     */
    public String getPpdDate() {
        return ppdDate;
    }

    /**
     * @param ppdDate the ppdDate to set
     */
    public void setPpdDate(String ppdDate) {
        this.ppdDate = ppdDate;
    }

    /**
     * @return the xrayDate
     */
    public String getXrayDate() {
        return xrayDate;
    }

    /**
     * @param xrayDate the xrayDate to set
     */
    public void setXrayDate(String xrayDate) {
        this.xrayDate = xrayDate;
    }

    /**
     * @return the ppdTest
     */
    public String getPpdTest() {
        return ppdTest;
    }

    /**
     * @param ppdTest the ppdTest to set
     */
    public void setPpdTest(String ppdTest) {
        this.ppdTest = ppdTest;
    }

    /**
     * @return the hbvDate1
     */
    public String getHbvDate1() {
        return hbvDate1;
    }

    /**
     * @param hbvDate1 the hbvDate1 to set
     */
    public void setHbvDate1(String hbvDate1) {
        this.hbvDate1 = hbvDate1;
    }

    /**
     * @return the hbvDate2
     */
    public String getHbvDate2() {
        return hbvDate2;
    }

    /**
     * @param hbvDate2 the hbvDate2 to set
     */
    public void setHbvDate2(String hbvDate2) {
        this.hbvDate2 = hbvDate2;
    }

    /**
     * @return the hbvDate3
     */
    public String getHbvDate3() {
        return hbvDate3;
    }

    /**
     * @param hbvDate3 the hbvDate3 to set
     */
    public void setHbvDate3(String hbvDate3) {
        this.hbvDate3 = hbvDate3;
    }

    /**
     * @return the hbvTitre1
     */
    public String getHbvTitre1() {
        return hbvTitre1;
    }

    /**
     * @param hbvTitre1 the hbvTitre1 to set
     */
    public void setHbvTitre1(String hbvTitre1) {
        this.hbvTitre1 = hbvTitre1;
    }

    /**
     * @return the hbvPositive
     */
    public String getHbvPositive() {
        return hbvPositive;
    }

    /**
     * @param hbvPositive the hbvPositive to set
     */
    public void setHbvPositive(String hbvPositive) {
        this.hbvPositive = hbvPositive;
    }

    /**
     * @return the ppdStep1date
     */
    public String getPpdStep1date() {
        return ppdStep1date;
    }

    /**
     * @param ppdStep1date the ppdStep1date to set
     */
    public void setPpdStep1date(String ppdStep1date) {
        this.ppdStep1date = ppdStep1date;
    }

    /**
     * @return the ppd2Test
     */
    public String getPpd2Test() {
        return ppd2Test;
    }

    /**
     * @param ppd2Test the ppd2Test to set
     */
    public void setPpd2Test(String ppd2Test) {
        this.ppd2Test = ppd2Test;
    }

    /**
     * @return the fingerPrintDate
     */
    public String getFingerPrintDate() {
        return fingerPrintDate;
    }

    /**
     * @param fingerPrintDate the fingerPrintDate to set
     */
    public void setFingerPrintDate(String fingerPrintDate) {
        this.fingerPrintDate = fingerPrintDate;
    }

    /**
     * @return the hbvDate4
     */
    public String getHbvDate4() {
        return hbvDate4;
    }

    /**
     * @param hbvDate4 the hbvDate4 to set
     */
    public void setHbvDate4(String hbvDate4) {
        this.hbvDate4 = hbvDate4;
    }

    /**
     * @return the hbvDate5
     */
    public String getHbvDate5() {
        return hbvDate5;
    }

    /**
     * @param hbvDate5 the hbvDate5 to set
     */
    public void setHbvDate5(String hbvDate5) {
        this.hbvDate5 = hbvDate5;
    }

    /**
     * @return the hbvDate6
     */
    public String getHbvDate6() {
        return hbvDate6;
    }

    /**
     * @param hbvDate6 the hbvDate6 to set
     */
    public void setHbvDate6(String hbvDate6) {
        this.hbvDate6 = hbvDate6;
    }

    /**
     * @return the hbvPositive2
     */
    public String getHbvPositive2() {
        return hbvPositive2;
    }

    /**
     * @param hbvPositive2 the hbvPositive2 to set
     */
    public void setHbvPositive2(String hbvPositive2) {
        this.hbvPositive2 = hbvPositive2;
    }

    /**
     * @return the drugTestDate
     */
    public String getDrugTestDate() {
        return drugTestDate;
    }

    /**
     * @param drugTestDate the drugTestDate to set
     */
    public void setDrugTestDate(String drugTestDate) {
        this.drugTestDate = drugTestDate;
    }

    /**
     * @return the drugTestResult
     */
    public String getDrugTestResult() {
        return drugTestResult;
    }

    /**
     * @param drugTestResult the drugTestResult to set
     */
    public void setDrugTestResult(String drugTestResult) {
        this.drugTestResult = drugTestResult;
    }

    /**
     * @return the rubellaDate
     */
    public String getRubellaDate() {
        return rubellaDate;
    }

    /**
     * @param rubellaDate the rubellaDate to set
     */
    public void setRubellaDate(String rubellaDate) {
        this.rubellaDate = rubellaDate;
    }

    /**
     * @return the rubellaResult
     */
    public String getRubellaResult() {
        return rubellaResult;
    }

    /**
     * @param rubellaResult the rubellaResult to set
     */
    public void setRubellaResult(String rubellaResult) {
        this.rubellaResult = rubellaResult;
    }

    /**
     * @return the rubellaResultReading
     */
    public String getRubellaResultReading() {
        return rubellaResultReading;
    }

    /**
     * @param rubellaResultReading the rubellaResultReading to set
     */
    public void setRubellaResultReading(String rubellaResultReading) {
        this.rubellaResultReading = rubellaResultReading;
    }

    /**
     * @return the measlesDate
     */
    public String getMeaslesDate() {
        return measlesDate;
    }

    /**
     * @param measlesDate the measlesDate to set
     */
    public void setMeaslesDate(String measlesDate) {
        this.measlesDate = measlesDate;
    }

    /**
     * @return the measlesResult
     */
    public String getMeaslesResult() {
        return measlesResult;
    }

    /**
     * @param measlesResult the measlesResult to set
     */
    public void setMeaslesResult(String measlesResult) {
        this.measlesResult = measlesResult;
    }

    /**
     * @return the measlesResultReading
     */
    public String getMeaslesResultReading() {
        return measlesResultReading;
    }

    /**
     * @param measlesResultReading the measlesResultReading to set
     */
    public void setMeaslesResultReading(String measlesResultReading) {
        this.measlesResultReading = measlesResultReading;
    }

    /**
     * @return the hbvTitre2
     */
    public String getHbvTitre2() {
        return hbvTitre2;
    }

    /**
     * @param hbvTitre2 the hbvTitre2 to set
     */
    public void setHbvTitre2(String hbvTitre2) {
        this.hbvTitre2 = hbvTitre2;
    }

    /**
     * @return the sSSN
     */
    public String getsSSN() {
        return sSSN;
    }

    /**
     * @param sSSN the sSSN to set
     */
    public void setsSSN(String sSSN) {
        this.sSSN = sSSN;
    }

    /**
     * @return the xrayCode
     */
    public String getXrayCode() {
        return xrayCode;
    }

    /**
     * @param xrayCode the xrayCode to set
     */
    public void setXrayCode(String xrayCode) {
        this.xrayCode = xrayCode;
    }

    /**
     * @return the influenzaDate
     */
    public String getInfluenzaDate() {
        return influenzaDate;
    }

    /**
     * @param influenzaDate the influenzaDate to set
     */
    public void setInfluenzaDate(String influenzaDate) {
        this.influenzaDate = influenzaDate;
    }

    @Override
    public int compareTo(MobileHealthGeorgeFileImport model) {
        return this.vendor.compareTo(model.getVendor());
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
