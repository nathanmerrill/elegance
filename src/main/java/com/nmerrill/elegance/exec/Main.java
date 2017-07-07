package com.nmerrill.elegance.exec;

import com.beust.jcommander.JCommander;
import com.nmerrill.elegance.antlr.EleganceLexer;
import com.nmerrill.elegance.antlr.EleganceParser;
import com.nmerrill.elegance.generation.EleganceWalker;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Elegance 1.0.0");
        Arguments arguments = new Arguments();
        JCommander commander = new JCommander(arguments);
        commander.parse(args);
        if (arguments.source.isEmpty()) {
            if (arguments.path.isEmpty()) {
                prompt();
            } else {
                openFile(arguments.path);
            }
        } else {
            if (arguments.path.isEmpty()) {
                evaluate(arguments.source);
            } else {
                System.out.println("Invalid parameters:  Either pass in code or a path, not both");
            }
        }
    }


    public static void evaluate(String code) {
        EleganceLexer lexer = new EleganceLexer(new ANTLRInputStream(code));
        TokenStream tokens = new CommonTokenStream(lexer);
        EleganceParser parser = new EleganceParser(tokens);
        //TODO: parser.setErrorHandler(new MyErrorStrategy());
        EleganceWalker walker = new EleganceWalker();
        walker.visit(parser.file());
    }

    public static void prompt() {

    }


    public static void openFile(String path) {

    }

}
