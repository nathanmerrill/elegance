package com.nmerrill.elegance.generation;

import com.nmerrill.elegance.antlr.EleganceLexer;
import com.nmerrill.elegance.antlr.EleganceParser;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ANTLRInputStream;

import java.io.IOException;
import java.io.InputStream;

public class EleganceParserFactory {

    public EleganceParser getParser(String code){
        return parse(new ANTLRInputStream(code));
    }

    public EleganceParser getParser(InputStream code) throws IOException{
        return parse(new ANTLRInputStream(code));
    }

    private EleganceParser parse(ANTLRInputStream stream){
        return new EleganceParser(new CommonTokenStream(new EleganceLexer(stream)));
    }
}
