package com.nmerrill.elegance.exec.exceptions;

import com.nmerrill.elegance.exec.reporting.Report;

public class ParseException extends EleganceException{
    public ParseException(Report report){
        super(report);
    }
}
