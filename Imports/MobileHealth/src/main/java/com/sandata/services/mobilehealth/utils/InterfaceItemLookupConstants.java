/**
 * 
 */
package com.sandata.services.mobilehealth.utils;

import java.util.Arrays;
import java.util.List;

/**
 * @author huyvo
 *
 */
public class InterfaceItemLookupConstants {
    private InterfaceItemLookupConstants() {
        // Empty and Private
    }
    
    public static final List<String> XRAY_INTERFACE_NAMES = Arrays.asList("XRAY_DATE", "XRAY_RESULT_CODE", "XRAY_TEST_INDICATOR", "PPD_CHEST_XRAY_CODE");
    public static final List<String> FINGERPRINT_INTERFACE_NAMES = Arrays.asList("FINGERPRINT_DATE");
    public static final List<String> DRUG_INTERFACE_NAMES = Arrays.asList("DRUG_TEST_DATE", "DRUG_TEST_RESULT_CODE");
    public static final List<String> RUBELLA_INTERFACE_NAMES = Arrays.asList("RUBELLA_DATE", "RUBELLA_RESULT_CODE", "RUBELLA_RESULT_READING");
    public static final List<String> MEASLES_INTERFACE_NAMES = Arrays.asList("MEASLES_DATE", "MEASLES_RESULT_CODE", "MEASLES_RESULT_READING");
    public static final List<String> INFLUENZA_INTERFACE_NAMES = Arrays.asList("INFLUENZA_DATE");
    public static final List<String> PPD_INTERFACE_NAMES = Arrays.asList("PPD_DATE", "PPD_CODE", "PPD_TEST");
    public static final List<String> PPD_2_INTERFACE_NAMES = Arrays.asList("PPD_2_DATE", "PPD_2_CODE", "PPD_2_TEST");
    public static final List<String> PHYSICAL_INTERFACE_NAMES = Arrays.asList("PHYSICAL_DATE");
    public static final List<String> TETANUS_INTERFACE_NAMES = Arrays.asList("TETANUS_DATE");
    public static final List<String> HBV_1_INTERFACE_NAMES = Arrays.asList("HBV_1_DATE");
    public static final List<String> HBV_2_INTERFACE_NAMES = Arrays.asList("HBV_2_DATE");
    public static final List<String> HBV_3_INTERFACE_NAMES = Arrays.asList("HBV_3_DATE");
    public static final List<String> HBV_TEST_INTERFACE_NAMES = Arrays.asList("HBV_1_TITRE", "HBV_POSITIVE_INDICATOR");
    public static final List<String> HBV_4_INTERFACE_NAMES = Arrays.asList("HBV_4_DATE");
    public static final List<String> HBV_5_INTERFACE_NAMES = Arrays.asList("HBV_5_DATE");
    public static final List<String> HBV_6_INTERFACE_NAMES = Arrays.asList("HBV_6_DATE");
    public static final List<String> HBV_TEST_2_INTERFACE_NAMES = Arrays.asList("HBV_2_TITRE", "HBV_POSITIVE_2_INDICATOR");
}
