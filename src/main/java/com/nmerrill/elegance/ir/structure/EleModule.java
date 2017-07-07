package com.nmerrill.elegance.ir.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EleModule {
    public String name;
    public String organization;
    public List<EleModule> dependencies;
    public Map<String, EleProgram> startingPoints;
    public Map<String, EleNamespace> namespaces;

    public EleModule(){
        this.name = "";
        this.organization = "";
        this.dependencies = new ArrayList<>();
        this.startingPoints = new HashMap<>();
        this.namespaces = new HashMap<>();
    }

}
