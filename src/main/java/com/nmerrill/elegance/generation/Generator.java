package com.nmerrill.elegance.generation;

import com.nmerrill.elegance.ir.IR;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class Generator<T extends ParserRuleContext, U extends IR> {
    public abstract U generate(T context);
}
