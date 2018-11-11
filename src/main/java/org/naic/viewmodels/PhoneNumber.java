/*
 * Copyright 2018. National Association of Insurance Commissioners.
 */

package org.naic.viewmodels;

public class PhoneNumber {

    private String number;


    public PhoneNumber(String number) {
        //Add logic and stuff
        this.number = number.replace("(", "").replace(")","").replace(" ", "").replace("-", "");
    }

    @Override
    public String toString() {
        //format
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        builder.append(number.substring(0, 3));
        builder.append(") ");
        builder.append(number.substring(3, 6));
        builder.append("-");
        builder.append(number.substring(6, 10));
        return builder.toString();
    }

}
