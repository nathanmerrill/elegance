package com.nmerrill.elegance.exec;


import com.beust.jcommander.Parameter;

public class Arguments {
    @Parameter(names = {"-s", "--source"}, description = "Source code to execute")
    public String source = "";

    @Parameter(names = {"-p","--path"}, description = "Path to source code")
    public String path = "";

}
