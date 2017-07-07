package com.nmerrill.elegance.ir.structure;

import com.nmerrill.elegance.ir.parts.ModulePath;
import com.nmerrill.elegance.ir.contructs.EleClass;
import com.nmerrill.elegance.ir.contructs.EleEnum;
import com.nmerrill.elegance.ir.contructs.EleFunction;
import com.nmerrill.elegance.ir.contructs.EleInterface;
import com.nmerrill.elegance.ir.statements.EleCodeBlock;

import java.util.ArrayList;
import java.util.List;

public class EleNamespace{
    public ModulePath path;
    public List<EleClass> classes;
    public List<ModulePath> imports;
    public List<EleFunction> functions;
    public List<EleEnum> enums;
    public List<EleInterface> interfaces;
    public EleCodeBlock initializer;

    public EleNamespace(){
        path = new ModulePath();
        this.initializer = new EleCodeBlock();
        this.classes = new ArrayList<>();
        this.functions = new ArrayList<>();
        this.enums = new ArrayList<>();
        this.interfaces = new ArrayList<>();
        this.imports = new ArrayList<>();
    }
}
