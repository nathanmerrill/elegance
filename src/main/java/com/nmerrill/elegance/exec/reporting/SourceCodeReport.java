package com.nmerrill.elegance.exec.reporting;

public class SourceCodeReport implements Report{

    private final String message, code;
    private final ReportLevel level;
    private final int line, column;


    public SourceCodeReport(String message, String code, ReportLevel level, int line, int column){
        this.message = message;
        this.code = code;
        this.level = level;
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public ReportLevel getLevel() {
        return level;
    }

    @Override
    public String getOutput() {
        StringBuilder builder = new StringBuilder();
        builder.append("Error on line ").append(line).append(':').append(column).append(':')
                .append(message).append('\n');
        String[] lines = code.split("\\r?\\n");
        int lineNumber = line - lines.length + 1;
        for (String line: lines){
            builder.append(lineNumber).append('\t').append(line).append('\n');
        }
        builder.append('\t');
        for (int i = 0; i < column; i++){
            builder.append(' ');
        }
        builder.append("^\n");
        return builder.toString();
    }
}
