package com.sandata.lab.common.utils.string;

/**
 * Date: 8/24/15
 * Time: 6:04 AM
 */

public class StringUtil {

    public static boolean IsNullOrEmpty(String target) {

        return (target == null) || (target.length() == 0);
    }

    public static String RemovePrefix(String target, String prefix) {

        if (target.toLowerCase().startsWith(prefix.toLowerCase())) {
            return target.substring(prefix.length());
        }

        return target;
    }

    /**
     * Split a string by capital letters appleJack --> Apple_Jack. Assuming '_' is the separator.
     * @param target The string to process.
     * @param separator The string that will separate the words.
     * @return The new string with appropriate separators.
     */
    public static String SplitByCapital(String target, String separator) {

        if (target == null || target.length() == 0) {
            return null;
        }

        if (separator == null || separator.length() == 0) {
            return null;
        }

        return (target.replaceAll("((?<=\\p{Ll})\\p{Lu}|\\p{Lu}(?=\\p{Ll}))", separator+"$1")).substring(1);
    }

    public static String FirstCharToLowercase(String data) {

        if (StringUtil.IsNullOrEmpty(data)) {
            return null;
        }

        char c[] = data.toCharArray();
        c[0] = Character.toLowerCase(c[0]);
        return new String(c);
    }
}
