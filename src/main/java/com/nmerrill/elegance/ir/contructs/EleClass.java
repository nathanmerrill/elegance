package com.nmerrill.elegance.ir.contructs;

import com.nmerrill.elegance.ir.parts.EleTypeParameter;
import com.nmerrill.elegance.ir.statements.EleAssignment;

import java.util.List;

public class EleClass {
    public List<EleFunction> functions;
    public List<EleAssignment> assignments;
    public List<EleConstructor> constructors;
    public List<EleInitializer> initializers;
    public List<EleTypeParameter> typeParameters;

}