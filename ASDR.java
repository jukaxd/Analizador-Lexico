import java.util.Arrays;
import java.util.List;

public class ASDR {

    private int index = 0;
    private boolean hasErrors = false;
    private Token preanalisis;
    private final List<Token> tokens;

    public ASDR(List<Token> tokens) {
        this.tokens = tokens;
        preanalisis = this.tokens.get(index);
    }

    @Override
    public boolean parse() {
        PROGRAM();

        if (preanalisis.tipo == TipoToken.EOF && !hasErrors) {
            System.out.println("Programa sintácticamente correcto.");
        } else {
            System.out.println("Se encontraron errores sintácticos.");
        }
        return false;
    }

    //PROGRAM -> DECLARATION
    private void PROGRAM() {
        if(hasErrors)
        return; 
        DECLARATION();
    }
    //DECLARATION -> FUN_DECL DECLARATION | VAR_DECL DECLARATION | STATEMENT DECLARATION | Ɛ
    private void DECLARATION() {
        if(hasErrors)
        return; 
        if (preanalisis.tipo == TipoToken.FUN) {
            FUN_DECL();
            DECLARATION();
        }else if(preanalisis.tipo == TipoToken.VAR){
            VAR_DECL();
            DECLARATION();
        }else if(preanalisis.tipo == TipoToken.BANG ||
        preanalisis.tipo == TipoToken.MINUS ||
        preanalisis.tipo == TipoToken.TRUE ||
        preanalisis.tipo == TipoToken.FALSE ||
        preanalisis.tipo == TipoToken.NULL ||
        preanalisis.tipo == TipoToken.NUMBER ||
        preanalisis.tipo == TipoToken.STRING ||
        preanalisis.tipo == TipoToken.IDENTIFIER ||
        preanalisis.tipo == TipoToken.LEFT_PAREN ||
        preanalisis.tipo == TipoToken.FOR ||
        preanalisis.tipo == TipoToken.IF ||
        preanalisis.tipo == TipoToken.PRINT ||
        preanalisis.tipo == TipoToken.RETURN ||
        preanalisis.tipo == TipoToken.WHILE ||
        preanalisis.tipo == TipoToken.LEFT_BRACE){
           STATEMENT();
           DECLARATION(); 
        }
    }
    //FUN_DECL -> fun FUNCTION
    private void FUN_DECL() {
        
        if (preanalisis.tipo == TipoToken.FUN) {
            match(TipoToken.FUN);
            FUNCTION();
        }else{
            hasErrors = true;
        }
    }
    //VAR_DECL -> var id VAR_INIT ;
    private void VAR_DECL() {
        
        if (preanalisis.tipo == TipoToken.VAR) {
            match(TipoToken.VAR);
            match(TipoToken.IDENTIFIER);
            VAR_INIT();
            match(TipoToken.SEMICOLON);
        } else {
            hasErrors=true;
        }
    }
    //VAR_INIT -> = EXPRESSION | Ɛ
    private void VAR_INIT() {
        if(hasErrors)
        return;
        if (preanalisis.tipo == TipoToken.EQUAL) {
            match(TipoToken.EQUAL);
            EXPRESSION();
        }
    }
    //STATEMENT -> EXPR_STMT | FOR_STMT | IF_STMT | PRINT_STMT | RETURN_STMT | WHILE_STMT | BLOCK
    private void STATEMENT() {
        if (preanalisis.tipo == TipoToken.BANG ||
            preanalisis.tipo == TipoToken.MINUS ||
            preanalisis.tipo == TipoToken.TRUE ||
            preanalisis.tipo == TipoToken.FALSE ||
            preanalisis.tipo == TipoToken.NULL ||
            preanalisis.tipo == TipoToken.NUMBER ||
            preanalisis.tipo == TipoToken.STRING ||
            preanalisis.tipo == TipoToken.IDENTIFIER ||
            preanalisis.tipo == TipoToken.LEFT_PAREN) {
            EXPR_STMT();
        } else if (preanalisis.tipo == TipoToken.FOR) {
            FOR_STMT();  // Procesa un bucle for
        } else if (preanalisis.tipo == TipoToken.IF) {
            IF_STMT();  // Procesa una instrucción if
        } else if (preanalisis.tipo == TipoToken.PRINT) {
            PRINT_STMT();  // Procesa una instrucción print
        } else if (preanalisis.tipo == TipoToken.RETURN) {
            RETURN_STMT();  // Procesa una instrucción return
        } else if (preanalisis.tipo == TipoToken.WHILE) {
            WHILE_STMT();  // Procesa un bucle while
        } else if (preanalisis.tipo == TipoToken.LEFT_BRACE) {
            BLOCK();  // Procesa un bloque de código
        }else{
            hasErrors=true;
        }
    }
    //EXPR_STMT -> EXPRESSION ;
    private void EXPR_STMT() {
        if(hasErrors)
        return;
        // Conjunto primero de EXPR_STMT: {!, -, true, false, null, number, string, id, (}
        if (preanalisis.tipo == TipoToken.BANG ||
            preanalisis.tipo == TipoToken.MINUS ||
            preanalisis.tipo == TipoToken.TRUE ||
            preanalisis.tipo == TipoToken.FALSE ||
            preanalisis.tipo == TipoToken.NULL ||
            preanalisis.tipo == TipoToken.NUMBER ||
            preanalisis.tipo == TipoToken.STRING ||
            preanalisis.tipo == TipoToken.IDENTIFIER ||
            preanalisis.tipo == TipoToken.LEFT_PAREN) {
    
            EXPRESSION();
            match(TipoToken.SEMICOLON);  // Espera el punto y coma al final
        } else {
            hasErrors=true;
        }
    }
    //FOR_STMT -> for ( FOR_STMT_1 FOR_STMT_2 FOR_STMT_3 ) STATEMENT
    private void FOR_STMT() {
        if(hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.FOR){
            match(TipoToken.FOR);
            match(TipoToken.LEFT_PAREN);
            FOR_STMT_1();
            FOR_STMT_2();
            FOR_STMT_3();
            match(TipoToken.RIGHT_PAREN);  
            STATEMENT();     
        }else{
            hasErrors=true;
        }
    }
    //FOR_STMT_1 -> VAR_DECL | EXPR_STMT | ;
    private void FOR_STMT_1() {
        if(hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.VAR){
            VAR_DECL();    
        }else if(preanalisis.tipo == TipoToken.BANG ||
            preanalisis.tipo == TipoToken.MINUS ||
            preanalisis.tipo == TipoToken.TRUE ||
            preanalisis.tipo == TipoToken.FALSE ||
            preanalisis.tipo == TipoToken.NULL ||
            preanalisis.tipo == TipoToken.NUMBER ||
            preanalisis.tipo == TipoToken.STRING ||
            preanalisis.tipo == TipoToken.IDENTIFIER ||
            preanalisis.tipo == TipoToken.LEFT_PAREN
            ){
        
            EXPR_STMT();
        }else if(preanalisis.tipo == TipoToken.SEMICOLON){
            match(TipoToken.SEMICOLON);
        }else{
            hasErrors=true;
        }
    }
    //FOR_STMT_2 -> EXPRESSION; | ;
    private void FOR_STMT_2() {
        if(hasErrors)
        return;
        if (preanalisis.tipo == TipoToken.BANG ||
            preanalisis.tipo == TipoToken.MINUS ||
            preanalisis.tipo == TipoToken.TRUE ||
            preanalisis.tipo == TipoToken.FALSE ||
            preanalisis.tipo == TipoToken.NULL ||
            preanalisis.tipo == TipoToken.NUMBER ||
            preanalisis.tipo == TipoToken.STRING ||
            preanalisis.tipo == TipoToken.IDENTIFIER ||
            preanalisis.tipo == TipoToken.LEFT_PAREN
            ) {
            EXPRESSION();
            match(TipoToken.SEMICOLON);
        } else if(preanalisis.tipo == TipoToken.SEMICOLON){
            match(TipoToken.SEMICOLON);
        }else{
            hasErrors=true;
        }
    }
    //FOR_STMT_3 -> EXPRESSION | Ɛ
    private void FOR_STMT_3() {
        if(hasErrors)
        return; 
        if (preanalisis.tipo == TipoToken.BANG ||
            preanalisis.tipo == TipoToken.MINUS ||
            preanalisis.tipo == TipoToken.TRUE ||
            preanalisis.tipo == TipoToken.FALSE ||
            preanalisis.tipo == TipoToken.NULL ||
            preanalisis.tipo == TipoToken.NUMBER ||
            preanalisis.tipo == TipoToken.STRING ||
            preanalisis.tipo == TipoToken.IDENTIFIER ||
            preanalisis.tipo == TipoToken.LEFT_PAREN) {
            EXPRESSION();
        }else{
            //epsilon
        }
    }
    //IF_STMT -> if (EXPRESSION) STATEMENT ELSE_STATEMENT
    private void IF_STMT() {
        if(hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.IF){
            match(TipoToken.IF);
            match(TipoToken.LEFT_PAREN);
            EXPRESSION();
            match(TipoToken.RIGHT_PAREN);
            STATEMENT();
            ELSE_STMT();
        }else {
            hasErrors=true;
        }
    }
    //ELSE_STATEMENT -> else STATEMENT | Ɛ
    private void ELSE_STMT() {
        if(hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.ELSE){
            match(TipoToken.ELSE);
            STATEMENT();
        }
    }
    //PRINT_STMT -> print EXPRESSION ;
    private void PRINT_STMT() {
        if(hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.PRINT){
            match(TipoToken.PRINT);
            EXPRESSION();
            match(TipoToken.SEMICOLON);
        }else{
            hasErrors=true;
        }
    }
    //RETURN_STMT -> return RETURN_EXP_OPC ;
    private void RETURN_STMT() {
        if(hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.RETURN){
            match(TipoToken.RETURN);
            RETURN_EXP_OPC();
            match(TipoToken.SEMICOLON);
        }else{
            hasErrors=true;
        }
    }
    //RETURN_EXP_OPC -> EXPRESSION | Ɛ
    private void RETURN_EXP_OPC() {
        if(hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.BANG ||
            preanalisis.tipo == TipoToken.MINUS ||
            preanalisis.tipo == TipoToken.TRUE ||
            preanalisis.tipo == TipoToken.FALSE ||
            preanalisis.tipo == TipoToken.NULL ||
            preanalisis.tipo == TipoToken.NUMBER ||
            preanalisis.tipo == TipoToken.STRING ||
            preanalisis.tipo == TipoToken.IDENTIFIER ||
            preanalisis.tipo == TipoToken.LEFT_PAREN){
                EXPRESSION();
            }else{
                //Epsilon
            }
    }
    //WHILE_STMT -> while ( EXPRESSION ) STATEMENT
    private void WHILE_STMT() {
        if(hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.WHILE){
            match(TipoToken.WHILE);
            match(TipoToken.LEFT_PAREN);
            EXPRESSION();
            match(TipoToken.RIGHT_PAREN);
            STATEMENT();
        }else{
            hasErrors=true;
        }
    }
    //BLOCK -> { DECLARATION }
    private void BLOCK() {
        if (hasErrors)
        return; 
        if (preanalisis.tipo == TipoToken.LEFT_BRACE) {
            match(TipoToken.LEFT_BRACE);
            DECLARATION();
            match(TipoToken.RIGHT_BRACE);
        } else {
            hasErrors=true;
        }
    }
    //EXPRESSION -> ASSIGNMENT
    private void EXPRESSION() {
        if(hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.BANG ||
            preanalisis.tipo == TipoToken.MINUS ||
            preanalisis.tipo == TipoToken.TRUE ||
            preanalisis.tipo == TipoToken.FALSE ||
            preanalisis.tipo == TipoToken.NULL ||
            preanalisis.tipo == TipoToken.NUMBER ||
            preanalisis.tipo == TipoToken.STRING ||
            preanalisis.tipo == TipoToken.IDENTIFIER ||
            preanalisis.tipo == TipoToken.LEFT_PAREN){
            
            ASSIGNMENT();
        }
    }
    //ASSIGNMENT -> LOGIC_OR ASSIGNMENT_OPC
    private void ASSIGNMENT() {
        if(hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.BANG ||
            preanalisis.tipo == TipoToken.MINUS ||
            preanalisis.tipo == TipoToken.TRUE ||
            preanalisis.tipo == TipoToken.FALSE ||
            preanalisis.tipo == TipoToken.NULL ||
            preanalisis.tipo == TipoToken.NUMBER ||
            preanalisis.tipo == TipoToken.STRING ||
            preanalisis.tipo == TipoToken.IDENTIFIER ||
            preanalisis.tipo == TipoToken.LEFT_PAREN){
            LOGIC_OR();
            ASSIGNMENT_OPC();
        }else{
            hasErrors=true;
        }
    }
    // ASSIGNMENT_OPC -> = EXPRESSION | Ɛ
    private void ASSIGNMENT_OPC() {
        if(hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.EQUAL){
            match(TipoToken.EQUAL);
            EXPRESSION();
        }
    }
    //LOGIC_OR -> LOGIC_AND LOGIC_OR_2
    private void LOGIC_OR() {
        if(hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.BANG ||
            preanalisis.tipo == TipoToken.MINUS ||
            preanalisis.tipo == TipoToken.TRUE ||
            preanalisis.tipo == TipoToken.FALSE ||
            preanalisis.tipo == TipoToken.NULL ||
            preanalisis.tipo == TipoToken.NUMBER ||
            preanalisis.tipo == TipoToken.STRING ||
            preanalisis.tipo == TipoToken.IDENTIFIER ||
            preanalisis.tipo == TipoToken.LEFT_PAREN){
                LOGIC_AND();
                LOGIC_OR_2();
            }else{
                hasErrors=true;
            }
    }
    //LOGIC_OR_2 -> or LOGIC_AND LOGIC_OR_2 | Ɛ
    private void LOGIC_OR_2() {
        if(hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.OR){
            match(TipoToken.OR);
            LOGIC_AND();
            LOGIC_OR_2();
        }
    }
    //LOGIC_AND -> EQUALITY LOGIC_AND_2
    private void LOGIC_AND() {
        if(hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.BANG ||
            preanalisis.tipo == TipoToken.MINUS ||
            preanalisis.tipo == TipoToken.TRUE ||
            preanalisis.tipo == TipoToken.FALSE ||
            preanalisis.tipo == TipoToken.NULL ||
            preanalisis.tipo == TipoToken.NUMBER ||
            preanalisis.tipo == TipoToken.STRING ||
            preanalisis.tipo == TipoToken.IDENTIFIER ||
            preanalisis.tipo == TipoToken.LEFT_PAREN){
            
            EQUALITY();
            LOGIC_AND_2();
        }else{
            hasErrors=true;
        }
    }
    //LOGIC_AND_2 -> and EQUALITY LOGIC_AND_2 | Ɛ
    private void LOGIC_AND_2() {
        if(hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.AND){
            match(TipoToken.AND);
            EQUALITY();
            LOGIC_AND_2();
        }
    }
    //EQUALITY -> COMPARISON EQUALITY_2
    private void EQUALITY() {
        if(hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.BANG ||
            preanalisis.tipo == TipoToken.MINUS ||
            preanalisis.tipo == TipoToken.TRUE ||
            preanalisis.tipo == TipoToken.FALSE ||
            preanalisis.tipo == TipoToken.NULL ||
            preanalisis.tipo == TipoToken.NUMBER ||
            preanalisis.tipo == TipoToken.STRING ||
            preanalisis.tipo == TipoToken.IDENTIFIER ||
            preanalisis.tipo == TipoToken.LEFT_PAREN){
            
            COMPARISON();
            EQUALITY_2();
        }else{
            hasErrors=true;
        }
    }
    //EQUALITY_2 -> != COMPARISON EQUALITY_2 | == COMPARISON EQUALITY_2 | Ɛ
    private void EQUALITY_2() {
        if(hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.BANG_EQUAL){
            match(TipoToken.BANG_EQUAL);
            COMPARISON();
            EQUALITY_2();
        }else if(preanalisis.tipo == TipoToken.EQUAL_EQUAL){
            match(TipoToken.EQUAL_EQUAL);
            COMPARISON();
            EQUALITY_2();
        }
    }
        
    //COMPARISON -> TERM COMPARISON_2
    private void COMPARISON() {
        if(hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.BANG ||
            preanalisis.tipo == TipoToken.MINUS ||
            preanalisis.tipo == TipoToken.TRUE ||
            preanalisis.tipo == TipoToken.FALSE ||
            preanalisis.tipo == TipoToken.NULL ||
            preanalisis.tipo == TipoToken.NUMBER ||
            preanalisis.tipo == TipoToken.STRING ||
            preanalisis.tipo == TipoToken.IDENTIFIER ||
            preanalisis.tipo == TipoToken.LEFT_PAREN){
            
            TERM();
            COMPARISON_2();
        }else{
            hasErrors=true;
        }
    }
    //COMPARISON_2 -> > TERM COMPARISON_2 | >= TERM COMPARISON_2 | < TERM COMPARISON_2 | <= TERM COMPARISON_2 | Ɛ
    private void COMPARISON_2() {
        if(hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.GREATER){
            match(TipoToken.GREATER);
            TERM();
            COMPARISON_2();
        }else if(preanalisis.tipo == TipoToken.GREATER_EQUAL){
            match(TipoToken.GREATER_EQUAL);
            TERM();
            COMPARISON_2();
        }else if(preanalisis.tipo == TipoToken.LESS){
            match(TipoToken.LESS);
            TERM();
            COMPARISON_2();
        }else if(preanalisis.tipo == TipoToken.LESS_EQUAL){
            match(TipoToken.LESS_EQUAL);
            TERM();
            COMPARISON_2();
        }else{
            //epsilon
        }
    }
    //TERM -> FACTOR TERM_2
    private void TERM() {
        if(hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.BANG ||
            preanalisis.tipo == TipoToken.MINUS ||
            preanalisis.tipo == TipoToken.TRUE ||
            preanalisis.tipo == TipoToken.FALSE ||
            preanalisis.tipo == TipoToken.NULL ||
            preanalisis.tipo == TipoToken.NUMBER ||
            preanalisis.tipo == TipoToken.STRING ||
            preanalisis.tipo == TipoToken.IDENTIFIER ||
            preanalisis.tipo == TipoToken.LEFT_PAREN){
            
            FACTOR();
            TERM_2();
        }else{
            hasErrors=true;
        }
    }
    //TERM_2 -> - FACTOR TERM_2 | + FACTOR TERM_2 | Ɛ
    private void TERM_2() {
        if(hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.MINUS){
            match(TipoToken.MINUS);
            FACTOR();
            TERM_2();
        }else if(preanalisis.tipo == TipoToken.PLUS){
            match(TipoToken.PLUS);
            FACTOR();
            TERM_2();
        }else{
            //epsilon
        }
    }
    //FACTOR -> UNARY FACTOR_2
    private void FACTOR() {
        if(hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.BANG ||
            preanalisis.tipo == TipoToken.MINUS ||
            preanalisis.tipo == TipoToken.TRUE ||
            preanalisis.tipo == TipoToken.FALSE ||
            preanalisis.tipo == TipoToken.NULL ||
            preanalisis.tipo == TipoToken.NUMBER ||
            preanalisis.tipo == TipoToken.STRING ||
            preanalisis.tipo == TipoToken.IDENTIFIER ||
            preanalisis.tipo == TipoToken.LEFT_PAREN){
            
            UNARY();
            FACTOR_2();
        }else{
            hasErrors=true;
        }
    }
    //FACTOR_2 -> / UNARY FACTOR_2 | * UNARY FACTOR_2 | Ɛ
    private void FACTOR_2() {
        if(hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.SLASH){
            match(TipoToken.SLASH);
            UNARY();
            FACTOR_2();
        }else if(preanalisis.tipo == TipoToken.STAR){
            UNARY();
            FACTOR_2();
        }else{
            //epsilon
        }
    }
    //UNARY -> ! UNARY | - UNARY | CALL
    private void UNARY() {
        if(hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.BANG){
            match(TipoToken.BANG);
            UNARY();
        }else if(preanalisis.tipo == TipoToken.MINUS){
            match(TipoToken.MINUS);
            UNARY();
        }else if(preanalisis.tipo == TipoToken.BANG||
            preanalisis.tipo == TipoToken.MINUS||
            preanalisis.tipo == TipoToken.TRUE||
            preanalisis.tipo == TipoToken.FALSE||
            preanalisis.tipo == TipoToken.NULL||
            preanalisis.tipo == TipoToken.NUMBER||
            preanalisis.tipo == TipoToken.STRING||
            preanalisis.tipo == TipoToken.IDENTIFIER||
            preanalisis.tipo == TipoToken.LEFT_PAREN){
            CALL();
        }else{
            hasErrors=true;
        }
    }
    //CALL -> PRIMARY CALL_2
    private void CALL() {
        if(hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.TRUE ||
            preanalisis.tipo == TipoToken.FALSE ||
            preanalisis.tipo == TipoToken.NULL ||
            preanalisis.tipo == TipoToken.NUMBER ||
            preanalisis.tipo == TipoToken.STRING ||
            preanalisis.tipo == TipoToken.IDENTIFIER ||
            preanalisis.tipo == TipoToken.LEFT_PAREN){
            
            PRIMARY();
            CALL_2();
        }else{
            hasErrors=true;
        }
    }
    //CALL_2 -> ( ARGUMENTS_OPC ) CALL_2 | Ɛ
    private void CALL_2() {
        if(hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.LEFT_PAREN){
            match(TipoToken.LEFT_PAREN);
            ARGUMENTS_OPC();
            match(TipoToken.RIGHT_PAREN);
            CALL_2();
        }else{
            //epsilon
        }
    }
    //PRIMARY -> true | false | null | number | string | id | ( EXPRESSION )
    private void PRIMARY() {
        if(hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.TRUE){
            match(TipoToken.TRUE);
        }else if(preanalisis.tipo == TipoToken.FALSE){
            match(TipoToken.FALSE);
        }else if(preanalisis.tipo == TipoToken.NULL){
            match(TipoToken.NULL);
        }else if(preanalisis.tipo == TipoToken.NUMBER){
            match(TipoToken.NUMBER);
        }else if(preanalisis.tipo == TipoToken.STRING){
            match(TipoToken.STRING);
        }else if(preanalisis.tipo == TipoToken.IDENTIFIER){
            match(TipoToken.IDENTIFIER);
        }else if(preanalisis.tipo == TipoToken.LEFT_PAREN){
            match(TipoToken.LEFT_PAREN);
            EXPRESSION();
            match(TipoToken.RIGHT_PAREN);
        }else{
            hasErrors=true;
        }
    }
    //FUNCTION -> id ( PARAMETERS_OPC ) BLOCK
    private void FUNCTION() {
       if(hasErrors)
       return;
       if(preanalisis.tipo == TipoToken.IDENTIFIER){
        match(TipoToken.IDENTIFIER);
        match(TipoToken.LEFT_PAREN);
        PARAMETERS_OPC();
        match(TipoToken.RIGHT_PAREN);
        BLOCK();
       }else{
            hasErrors=true;
        }
    }
    //FUNCTIONS -> FUN_DECL FUNCTIONS | Ɛ
    private void FUNCTIONS() {
        if(hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.FUN){
            FUN_DECL();
            FUNCTIONS();
        }else{
            //epsilon
        }
    }
    //PARAMETERS_OPC -> PARAMETERS | Ɛ
    private void PARAMETERS_OPC() {
        if(hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.IDENTIFIER){
            PARAMETERS();
        }
    }
    //PARAMETERS -> id PARAMETERS_2
    private void PARAMETERS() {
        if(hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.IDENTIFIER){
            match(TipoToken.IDENTIFIER);
            PARAMETERS_2();
        } else {
            error(TipoToken.IDENTIFIER);
        }
    }
    //PARAMETERS_2 -> , id PARAMETERS_2 | Ɛ
    private void PARAMETERS_2() {
        if (hasErrors)
        return;
        if(preanalisis.tipo==TipoToken.COMMA){
            match(TipoToken.COMMA);
            match(TipoToken.IDENTIFIER);
            PARAMETERS_2();
        }else {
            error(TipoToken.COMMA);
        }
    }
    //ARGUMENTS_OPC -> EXPRESSION ARGUMENTS | Ɛ
    private void ARGUMENTS_OPC() {
        if (hasErrors)
        return;
        if(preanalisis.tipo == TipoToken.BANG ||
            preanalisis.tipo == TipoToken.MINUS ||
            preanalisis.tipo == TipoToken.TRUE ||
            preanalisis.tipo == TipoToken.FALSE ||
            preanalisis.tipo == TipoToken.NULL ||
            preanalisis.tipo == TipoToken.NUMBER ||
            preanalisis.tipo == TipoToken.STRING ||
            preanalisis.tipo == TipoToken.IDENTIFIER ||
            preanalisis.tipo == TipoToken.LEFT_PAREN){
                
            EXPRESSION();
            ARGUMENTS();
        }
    }
    private void ARGUMENTS() {
        if(preanalisis.tipo==TipoToken.COMMA){
            match(TipoToken.COMMA);
            EXPRESSION();
            ARGUMENTS();
        }else{
            //Epsilon
        }
    }

    private void match(TipoToken expectedType) {
        if (preanalisis.tipo == expectedType) {
            index++;
            if (index < tokens.size()) {
                preanalisis = tokens.get(index);
            }
        } else {
            error(expectedType);
        }
    }
    
    private void error(TipoToken... expectedTokens) {
        hasErrors = true;
        System.out.println("Error en la línea " +
                preanalisis.getLine() +
                ". Se esperaba " + Arrays.toString(expectedTokens) +
                " pero se encontró " + preanalisis.tipo +
                " con valor '" + preanalisis.lexema + "'.");
    }
}

