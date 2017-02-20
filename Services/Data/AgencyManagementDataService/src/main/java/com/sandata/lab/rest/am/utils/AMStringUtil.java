/**
 * 
 */
package com.sandata.lab.rest.am.utils;

import java.util.regex.Pattern;

/**
 * @author thanhxle
 *
 * @Description : Contains all the utils method while working with string
 *
 * @date Jul 6, 2016
 * 
 * 
 */
public class AMStringUtil {
    
    public static boolean isNumber(String source){
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(source).matches();
    }

}
