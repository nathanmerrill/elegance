grammar Elegance;

@header {
package com.nmerrill.elegance.antlr;
}

file: (ele_package)? (file_import)* (ele_class | function | statement | ele_enum)* EOF;

ele_package :
    PACKAGE_DECLARATION module=IDENTIFIER COLON package_path;

package_path :
    IDENTIFIER (PERIOD IDENTIFIER)*;

file_import :
    IMPORT_DECLARATION (module=IDENTIFIER COLON)? package_path;

ele_class :
    CLASS_DECLARATION IDENTIFIER
    type_parameters?
    parameter_list?
    type_alias*
    OPEN_CURLY 
    (function | assignment)*
    CLOSE_CURLY;

function:
    FUNCTION_DECLARATION IMPURE_DECLARATION? IDENTIFIER parameter_list
    COLON type
    (MUTABLE_DECLARATION OPEN_PAREN (variable_reference (COMMA variable_reference)*)? CLOSE_PAREN)?
    (
        OPEN_CURLY (function | statement)* CLOSE_CURLY |
        EQUAL expression SEMICOLON
    );

ele_enum:
    ENUM_DECLARATION name=IDENTIFIER OPEN_CURLY (IDENTIFIER (COMMA IDENTIFIER)*)? CLOSE_CURLY ;

variable_reference:
    variable_reference PERIOD IDENTIFIER |
    variable_reference OPEN_BRACKET expression CLOSE_BRACKET |
    IDENTIFIER;

assignment:
    (VALUE_DECLARATION | VARIABLE_DECLARATION | OPTION_DECLARATION)?
    (MUTABLE_DECLARATION)?
    variable_reference
    (COLON type)?
    (EQUAL expression)?
    SEMICOLON;

statement:
    (assignment | statement_if | ele_for | RETURN_DECLARATION? expression SEMICOLON);

statement_if: IF expression DO statement* (ELSE statement*)? END;
expression_if: IF expression DO expression ELSE expression END;
ele_for: FOR_LOOP IDENTIFIER IN expression DO statement* END;

argument_list:
    OPEN_PAREN 
       (argument
           (COMMA  argument)* 
           (COMMA  named_argument)* 
       | named_argument
           (COMMA  named_argument)* 
       )?
   CLOSE_PAREN;

argument: expression;

named_argument: IDENTIFIER EQUAL expression;

parameter_list:
    OPEN_PAREN 
        (parameter
            (COMMA  parameter)* 
        )?
    CLOSE_PAREN;

parameter:
    MUTABLE_DECLARATION? expression
    (COLON type)?;

type:
    type ASTERISK |  //Iterable type
    type QUESTION_MARK |  //Optional type
    OPEN_PAREN type (COMMA type)* CLOSE_PAREN ARROW type | //Function type
    type ARROW type | //Function type
    type (COMMA type)+ |  //Pair type
    OPEN_PAREN type CLOSE_PAREN |
    IDENTIFIER
        type_parameters?
        (OPEN_BRACKET trait_expression CLOSE_BRACKET)?;


type_parameters:
    (OPEN_ANGLE_BRACKET type (SEMICOLON type)* CLOSE_ANGLE_BRACKET);

trait_expression:
    trait_expression OR trait_expression |
    trait_expression AND trait_expression |
    IDENTIFIER (OPEN_PAREN (literal (COMMA literal)*)? CLOSE_PAREN)?;

type_alias:
    WHERE_DECLARATION IDENTIFIER COLON type;


expression:
        expression argument_list | //Function call
        expression OR expression |
        expression AND expression |
        expression (IS | EQ | NEQ)  expression |
        expression (LEQ | GEQ | LT | GT)  expression |
        expression (PLUS | MINUS)  expression |
        expression (ASTERISK | DIV)  expression |
        expression PERIOD  IDENTIFIER |
        expression_if |
        OPEN_PAREN  expression  CLOSE_PAREN |
        literal |
        type |
        variable_reference;


literal: integer | ele_float | list | tuple | dictionary | set | STRING ;

list: OPEN_BRACKET  (expression (COMMA  expression)* )? CLOSE_BRACKET ;

tuple: TUPLE_START  (expression (COMMA  expression)* )? CLOSE_BRACKET ;

set: SET_START  (expression (COMMA  expression)* )? CLOSE_BRACKET ;

dictionary: DICT_START  (expression COLON expression (COMMA  expression COLON expression)* )? CLOSE_BRACKET ;

integer: (PLUS | MINUS)? INTEGER | ZERO;

ele_float: (PLUS | MINUS)? FLOAT;


INTEGER: [1-9][0-9]* ;

FLOAT: ('0' | [1-9][0-9]* ) '.' [0-9]+;

ZERO: '0' ;

STRING: '"' ('\\'. | ~('"' | '\\'))* '"' |  '\'' ('\\'. | ~('\'' | '\\'))* '\'';

SET_START: 's[';
TUPLE_START: 't[';
DICT_START: 'd[';

PACKAGE_DECLARATION: 'package' ;
CLASS_DECLARATION : 'class' ;
FUNCTION_DECLARATION: 'fun' ;
VALUE_DECLARATION: 'val' ;
VARIABLE_DECLARATION: 'var' ;
OPTION_DECLARATION: 'option' ;
IMPORT_DECLARATION: 'import' ;
WHERE_DECLARATION: 'where' ;
MUTABLE_DECLARATION: 'mut' ;
ENUM_DECLARATION: 'enum' ;
IMPURE_DECLARATION: 'impure' ;
RETURN_DECLARATION: 'return' ;

OPEN_CURLY: '{' ;
CLOSE_CURLY: '}' ;
OPEN_PAREN: '(' ;
CLOSE_PAREN: ')';
OPEN_ANGLE_BRACKET: '<' ;
CLOSE_ANGLE_BRACKET: '>';
OPEN_BRACKET: '[' ;
CLOSE_BRACKET: ']' ;

IF: 'if' ;
ELSE: 'else';
FOR_LOOP: 'for' ;
IN: 'in' ;
DO: 'do' ;
END: 'end';


COLON: ':' ;
COMMA: ',';
PERIOD: '.' ;

EQUAL: '=';

OR: 'or';
AND: 'and';
IS: 'is';
EQ: 'eq';
NEQ: 'neq';
LEQ: 'leq';
GEQ: 'geq';
LT: 'lt';
GT: 'gt';


ASTERISK: '*';
PLUS: '+';
MINUS: '-';
DIV: '/';

SEMICOLON: ';';

IDENTIFIER: [a-zA-Z_][a-zA-Z0-9_]*;

UNDERSCORE: '_';

ARROW : '->';

QUESTION_MARK: '?';

COMMENT: ('//' ~('\r' | '\n')+) -> skip;
WS: ('\n' | '\r' | '\t' | ' ')+ -> skip;
