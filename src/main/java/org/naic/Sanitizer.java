/*
 * Copyright 2018. National Association of Insurance Commissioners.
 */

package org.naic;

public class Sanitizer {

    /**
     * Simple check for sql keywords in a string from user input.
     * Parameterized sql query would be a much better approach
     * to preventing injection.
     * @param value {@link String} of user input
     * @return {@link boolean} whether value is clean
     */
    public static boolean SanitizeInput(String value) {

        return !value.toUpperCase().matches("(ALTER|CREATE|DELETE|DROP|EXEC(UTE){0,1}|INSERT( +INTO){0,1}|MERGE|SELECT|UPDATE|UNION( +ALL){0,1})");
    }
}
