package com.nmerrill.elegance.exec.reporting;

public interface Report {
    String getMessage();
    ReportLevel getLevel();
    String getOutput();
}
