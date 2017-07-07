package com.nmerrill.elegance.exec.exceptions;

import com.nmerrill.elegance.exec.reporting.Report;

public class EleganceException extends RuntimeException{
    private final Report report;
    public EleganceException(Report report){
        super(report.getMessage());
        this.report = report;
    }

    public Report getReport() {
        return report;
    }
}
