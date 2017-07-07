package com.nmerrill.elegance.ir.statements;

import java.util.ArrayList;
import java.util.List;

public class EleCodeBlock extends EleStatement{
    
    public List<EleStatement> statements;
    
    public EleCodeBlock(){
        this.statements = new ArrayList<>();    
    }

}
