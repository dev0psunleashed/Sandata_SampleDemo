package com.sandata.lab.tools.oracle.converter;

/**
 * Date: 8/29/15
 * Time: 7:54 PM
 */

public class CSharpTypeConverter {

    public static String JavaToCSharp(final String type, final boolean required) {

        String csharpType = type.trim().toLowerCase();

        if (csharpType.equals("bigdecimal")) {

            if (required) {

                return "decimal";
            }

            return "decimal?";
        }

        if (csharpType.equals("integer")) {

            if (required) {

                return "int";
            }

            return "int?";
        }

        if (csharpType.equals("biginteger")) {

            if (required) {

                return "long";
            }

            return "long?";
        }

        if (csharpType.equals("boolean")) {

            if (required) {

                return "bool";
            }

            return "bool?";
        }

        if (csharpType.equals("date")) {

            if (required) {

                return "DateTime";
            }

            return "Nullable<DateTime>";
        }

        return type;
    }
}
