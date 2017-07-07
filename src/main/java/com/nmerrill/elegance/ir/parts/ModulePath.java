package com.nmerrill.elegance.ir.parts;

import java.util.ArrayList;
import java.util.List;

public class ModulePath {
    public String module;
    public List<String> path;
    public ModulePath(){
        module = "";
        path = new ArrayList<>();
    }

    public boolean isEmpty(){
        return module.isEmpty() && path.isEmpty();
    }

    public boolean isCurrentModule(){
        return module.isEmpty();
    }
}
