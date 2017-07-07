package com.nmerrill.elegance.generation;


import com.nmerrill.elegance.antlr.EleganceBaseVisitor;
import com.nmerrill.elegance.antlr.EleganceParser;
import com.nmerrill.elegance.ir.statements.EleAssignment;
import com.nmerrill.elegance.ir.structure.EleNamespace;
import com.nmerrill.elegance.ir.statements.EleStatement;
import com.nmerrill.elegance.ir.parts.ModulePath;
import com.nmerrill.elegance.ir.contructs.EleClass;
import com.nmerrill.elegance.ir.contructs.EleEnum;
import com.nmerrill.elegance.ir.contructs.EleFunction;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class EleganceWalker extends EleganceBaseVisitor<Object>
{

    @Override
    public EleNamespace visitFile(EleganceParser.FileContext ctx) {
        EleNamespace namespace = new EleNamespace();
        if (ctx.ele_package() != null) {
            namespace.path = visitEle_package(ctx.ele_package());
        }
        namespace.imports = map(ctx.file_import(), this::visitFile_import);
        namespace.classes = map(ctx.ele_class(), this::visitEle_class);
        namespace.functions = map(ctx.function(), this::visitFunction);
        namespace.enums = map(ctx.ele_enum(), this::visitEle_enum);
        namespace.initializer.statements = map(ctx.statement(), this::visitStatement);
        return namespace;
    }

    @Override
    public ModulePath visitFile_import(EleganceParser.File_importContext ctx) {
        ModulePath modulePath = new ModulePath();
        modulePath.module = ctx.module.toString();
        modulePath.path = visitPackage_path(ctx.package_path());
        return modulePath;
    }

    @Override
    public EleEnum visitEle_enum(EleganceParser.Ele_enumContext ctx) {
        EleEnum eleEnum = new EleEnum();
        eleEnum.name = ctx.name.getText();
        eleEnum.constants = toString(ctx.IDENTIFIER().subList(1, ctx.IDENTIFIER().size()));
        return eleEnum;
    }

    @Override
    public ModulePath visitEle_package(EleganceParser.Ele_packageContext ctx) {
        ModulePath modulePath = new ModulePath();
        modulePath.module = ctx.module.toString();
        modulePath.path = visitPackage_path(ctx.package_path());
        return modulePath;
    }

    @Override
    public List<String> visitPackage_path(EleganceParser.Package_pathContext ctx) {
        return toString(ctx.IDENTIFIER());
    }

    @Override
    public EleClass visitEle_class(EleganceParser.Ele_classContext ctx) {
        EleClass eleClass = new EleClass();
        eleClass.functions = map(ctx.function(), this::visitFunction);
        eleClass.assignments = map(ctx.assignment(), this::visitAssignment);
        return eleClass;
    }

    @Override
    public EleStatement visitStatement(EleganceParser.StatementContext ctx) {
        return (EleStatement) super.visitStatement(ctx);
    }

    @Override
    protected Object aggregateResult(Object aggregate, Object nextResult) {
        if (aggregate == null){
            return nextResult;
        }
        return super.aggregateResult(aggregate, nextResult);
    }

    @Override
    public EleAssignment visitAssignment(EleganceParser.AssignmentContext ctx) {
        //TODO
        return new EleAssignment();
    }

    @Override
    public EleFunction visitFunction(EleganceParser.FunctionContext ctx) {
        //TODO
        return new EleFunction();
    }

    private List<String> toString(List<TerminalNode> nodes){
        ArrayList<String> strings = new ArrayList<>(nodes.size());
        for (TerminalNode node: nodes){
            strings.add(node.getText());
        }
        return strings;
    }

    private <T, U extends ParserRuleContext> List<T> map(List<U> list, Function<U, T> mapper){
        ArrayList<T> ir = new ArrayList<>(list.size());
        for (U context: list){
            ir.add(mapper.apply(context));
        }
        return ir;
    }
}
