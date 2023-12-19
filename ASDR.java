// import java.util.List;

// public class ASDR implements Parser{

//     private int i = 0;
//     private int chari = 0;
//     private boolean hasErrors = false;
//     private Token preanalisis;
//     private final List<Token> tokens;
    
//     public ASDR(List<Token> tokens) {
//         this.tokens = tokens;
//         preanalisis = this.tokens.get(i);
//     }

//     //@Override
//     public boolean parse() {
//         PROGRAM();

//         if (preanalisis.tipo != TipoToken.EOF && !hasErrors) {
//             System.out.println("Programa sintácticamente correcto.");
//         } else {
//             System.out.println("Se encontraron errores sintácticos.");
//         }
//         return false;
//     }

//     //PROGRAM -> DECLARATION
//     private void PROGRAM() {
//         if(hasErrors)
//         return; 
//         DECLARATION();
//     }
//     //DECLARATION -> FUN_DECL DECLARATION | VAR_DECL DECLARATION | STATEMENT DECLARATION | Ɛ
//     private void DECLARATION() {
//         if(hasErrors)
//         return; 
//         if (preanalisis.tipo == TipoToken.FUN) {
//             FUN_DECL();
//             DECLARATION();
//         }else if(preanalisis.tipo == TipoToken.VAR){
//             VAR_DECL();
//             DECLARATION();
//         }else if(preanalisis.tipo == TipoToken.BANG ||
//             preanalisis.tipo == TipoToken.MINUS ||
//             preanalisis.tipo == TipoToken.TRUE ||
//             preanalisis.tipo == TipoToken.FALSE ||
//             preanalisis.tipo == TipoToken.NULL ||
//             preanalisis.tipo == TipoToken.NUMBER ||
//             preanalisis.tipo == TipoToken.STRING ||
//             preanalisis.tipo == TipoToken.IDENTIFIER ||
//             preanalisis.tipo == TipoToken.LEFT_PAREN ||
//             preanalisis.tipo == TipoToken.FOR ||
//             preanalisis.tipo == TipoToken.IF ||
//             preanalisis.tipo == TipoToken.PRINT ||
//             preanalisis.tipo == TipoToken.RETURN ||
//             preanalisis.tipo == TipoToken.WHILE ||
//             preanalisis.tipo == TipoToken.LEFT_BRACE){
                
//            STATEMENT();
//            DECLARATION(); 
//         }
// }

//     //FUN_DECL -> fun FUNCTION
//     private void FUN_DECL() {
//         if(hasErrors)
//         return; 
//         if (preanalisis.tipo == TipoToken.FUN) {
//             match(TipoToken.FUN);
//             FUNCTION();
//         }else{
//             hasErrors = true;
//         }
//     }
//     //VAR_DECL -> var id VAR_INIT ;
//     private void VAR_DECL() {
//         if(hasErrors)
//         return; 
//         if (preanalisis.tipo == TipoToken.VAR) {
//             match(TipoToken.VAR);
//             match(TipoToken.IDENTIFIER);
//             VAR_INIT();
//             match(TipoToken.SEMICOLON);
//         } else {
//             hasErrors=true;
//         }
//     }
//     //VAR_INIT -> = EXPRESSION | Ɛ
//     private void VAR_INIT() {
//         if(hasErrors)
//         return;
//         if (preanalisis.tipo == TipoToken.EQUAL) {
//             match(TipoToken.EQUAL);
//             EXPRESSION();
//         }
//     }
//     //STATEMENT -> EXPR_STMT | FOR_STMT | IF_STMT | PRINT_STMT | RETURN_STMT | WHILE_STMT | BLOCK
//     private void STATEMENT() {
//         if(hasErrors)
//         return; 
//         if (preanalisis.tipo == TipoToken.BANG ||
//             preanalisis.tipo == TipoToken.MINUS ||
//             preanalisis.tipo == TipoToken.TRUE ||
//             preanalisis.tipo == TipoToken.FALSE ||
//             preanalisis.tipo == TipoToken.NULL ||
//             preanalisis.tipo == TipoToken.NUMBER ||
//             preanalisis.tipo == TipoToken.STRING ||
//             preanalisis.tipo == TipoToken.IDENTIFIER ||
//             preanalisis.tipo == TipoToken.LEFT_PAREN) {
    
//             EXPR_STMT();
//         } else if (preanalisis.tipo == TipoToken.FOR) {
//            // match(TipoToken.FOR);
//             FOR_STMT();  // Procesa un bucle for
//         } else if (preanalisis.tipo == TipoToken.IF) {
//             //match(TipoToken.IF);
//             IF_STMT();  // Procesa una instrucción if
//         } else if (preanalisis.tipo == TipoToken.PRINT) {
//             //match(TipoToken.PRINT);
//             PRINT_STMT();  // Procesa una instrucción print
//         } else if (preanalisis.tipo == TipoToken.RETURN) {
//             //match(TipoToken.RETURN);
//             RETURN_STMT();  // Procesa una instrucción return
//         } else if (preanalisis.tipo == TipoToken.WHILE) {
//             //match(TipoToken.WHILE);
//             WHILE_STMT();  // Procesa un bucle while
//         } else if (preanalisis.tipo == TipoToken.LEFT_BRACE) {
//             //match(TipoToken.LEFT_BRACE);
//             BLOCK();  // Procesa un bloque de código
//             //match(TipoToken.RIGHT_BRACE);
//         }else{
//             hasErrors=true;
//         }
//     }
//     //EXPR_STMT -> EXPRESSION ;
//     private void EXPR_STMT() {
//         if(hasErrors)
//         return;
//         EXPRESSION();
//         match(TipoToken.SEMICOLON);  // Espera el punto y coma al final
//     }
//     //FOR_STMT -> for ( FOR_STMT_1 FOR_STMT_2 FOR_STMT_3 ) STATEMENT
//     private void FOR_STMT() {
//         if(hasErrors)
//         return;
//         if(preanalisis.tipo == TipoToken.FOR){
//             match(TipoToken.FOR);
//             match(TipoToken.LEFT_PAREN);
//             FOR_STMT_1();
//             FOR_STMT_2();
//             FOR_STMT_3();
//             match(TipoToken.RIGHT_PAREN);  
//             STATEMENT();     
//         }else{
//             hasErrors=true;
//         }
//     }
//     //FOR_STMT_1 -> VAR_DECL | EXPR_STMT | ;
//     private void FOR_STMT_1() {
//         if(hasErrors)
//         return;
//         if(preanalisis.tipo == TipoToken.VAR){
//             VAR_DECL();    
//         }else if(preanalisis.tipo == TipoToken.BANG ||
//             preanalisis.tipo == TipoToken.MINUS ||
//             preanalisis.tipo == TipoToken.TRUE ||
//             preanalisis.tipo == TipoToken.FALSE ||
//             preanalisis.tipo == TipoToken.NULL ||
//             preanalisis.tipo == TipoToken.NUMBER ||
//             preanalisis.tipo == TipoToken.STRING ||
//             preanalisis.tipo == TipoToken.IDENTIFIER ||
//             preanalisis.tipo == TipoToken.LEFT_PAREN
//             ){
        
//             EXPR_STMT();
//         }else if(preanalisis.tipo == TipoToken.SEMICOLON){
//             match(TipoToken.SEMICOLON);
//         }else{
//             hasErrors=true;
//         }
//     }
//     //FOR_STMT_2 -> EXPRESSION; | ;
//     private void FOR_STMT_2() {
//         if(hasErrors)
//         return;
//         if (preanalisis.tipo == TipoToken.BANG ||
//             preanalisis.tipo == TipoToken.MINUS ||
//             preanalisis.tipo == TipoToken.TRUE ||
//             preanalisis.tipo == TipoToken.FALSE ||
//             preanalisis.tipo == TipoToken.NULL ||
//             preanalisis.tipo == TipoToken.NUMBER ||
//             preanalisis.tipo == TipoToken.STRING ||
//             preanalisis.tipo == TipoToken.IDENTIFIER ||
//             preanalisis.tipo == TipoToken.LEFT_PAREN
//             ) {
//             EXPRESSION();
//             match(TipoToken.SEMICOLON);
//         } 
//         else if(preanalisis.tipo == TipoToken.SEMICOLON){
//             match(TipoToken.SEMICOLON);
//         }
//         else{
//             hasErrors=true;
//         }
//     }
//     //FOR_STMT_3 -> EXPRESSION | Ɛ
//     private void FOR_STMT_3() {
//         if(hasErrors)
//         return; 
//         if (preanalisis.tipo == TipoToken.BANG ||
//             preanalisis.tipo == TipoToken.MINUS ||
//             preanalisis.tipo == TipoToken.TRUE ||
//             preanalisis.tipo == TipoToken.FALSE ||
//             preanalisis.tipo == TipoToken.NULL ||
//             preanalisis.tipo == TipoToken.NUMBER ||
//             preanalisis.tipo == TipoToken.STRING ||
//             preanalisis.tipo == TipoToken.IDENTIFIER ||
//             preanalisis.tipo == TipoToken.LEFT_PAREN) {
        
//             EXPRESSION();
//         }
//     }
//     //IF_STMT -> if (EXPRESSION) STATEMENT ELSE_STATEMENT
//     private void IF_STMT() {
//         if(hasErrors)
//         return;
//         if(preanalisis.tipo == TipoToken.IF){
//             match(TipoToken.IF);
//             match(TipoToken.LEFT_PAREN);
//             EXPRESSION();
//             match(TipoToken.RIGHT_PAREN);
//             STATEMENT();
//             ELSE_STATEMENT();
//         }else {
//             hasErrors=true;
//         }
//     }
//     //ELSE_STATEMENT -> else STATEMENT | Ɛ
//     private void ELSE_STATEMENT() {
//         if(hasErrors)
//         return;
//         if(preanalisis.tipo == TipoToken.ELSE){
//             match(TipoToken.ELSE);
//             STATEMENT();
//         }
//     }
//     //PRINT_STMT -> print EXPRESSION ;
//     private void PRINT_STMT() {
//         if(hasErrors)
//         return;
//         if(preanalisis.tipo == TipoToken.PRINT){
//             match(TipoToken.PRINT);
//             EXPRESSION();
//             match(TipoToken.SEMICOLON);
//         }else{
//             hasErrors=true;
//         }
//     }
//     //RETURN_STMT -> return RETURN_EXP_OPC ;
//     private void RETURN_STMT() {
//         if(hasErrors)
//         return;
//         if(preanalisis.tipo == TipoToken.RETURN){
//             match(TipoToken.RETURN);
//             RETURN_EXP_OPC();
//             match(TipoToken.SEMICOLON);
//         }else{
//             hasErrors=true;
//         }
//     }
//     //RETURN_EXP_OPC -> EXPRESSION | Ɛ
//     private void RETURN_EXP_OPC() {
//         if(hasErrors)
//         return;
//         if(preanalisis.tipo == TipoToken.BANG ||
//             preanalisis.tipo == TipoToken.MINUS ||
//             preanalisis.tipo == TipoToken.TRUE ||
//             preanalisis.tipo == TipoToken.FALSE ||
//             preanalisis.tipo == TipoToken.NULL ||
//             preanalisis.tipo == TipoToken.NUMBER ||
//             preanalisis.tipo == TipoToken.STRING ||
//             preanalisis.tipo == TipoToken.IDENTIFIER ||
//             preanalisis.tipo == TipoToken.LEFT_PAREN){
 
//             EXPRESSION();
//         }
//     }
//     //WHILE_STMT -> while ( EXPRESSION ) STATEMENT
//     private void WHILE_STMT() {
//         if(hasErrors)
//         return;
//         if(preanalisis.tipo == TipoToken.WHILE){
//             match(TipoToken.WHILE);
//             match(TipoToken.LEFT_PAREN);
//             EXPRESSION();
//             match(TipoToken.RIGHT_PAREN);
//             STATEMENT();
//         }else{
//             hasErrors=true;
//         }
//     }
//     //BLOCK -> { DECLARATION }
//     private void BLOCK() {
//         if (hasErrors)
//         return; 
//         if (preanalisis.tipo == TipoToken.LEFT_BRACE) {
//             match(TipoToken.LEFT_BRACE);
//             DECLARATION();
//             match(TipoToken.RIGHT_BRACE);
//         } else {
//             hasErrors=true;
//         }
//     }
//     //EXPRESSION -> ASSIGNMENT
//     private void EXPRESSION() {
//         if(hasErrors)
//         return;
//         ASSIGNMENT();
//     }
//     //ASSIGNMENT -> LOGIC_OR ASSIGNMENT_OPC
//     private void ASSIGNMENT() {
//         if(hasErrors)
//         return;
//         LOGIC_OR();
//         ASSIGNMENT_OPC();

//     }
//     // ASSIGNMENT_OPC -> = EXPRESSION | Ɛ
//     private void ASSIGNMENT_OPC() {
//         if(hasErrors)
//         return;
//         if(preanalisis.tipo == TipoToken.EQUAL){
//             match(TipoToken.EQUAL);
//             EXPRESSION();
//         }
//     }
//     //LOGIC_OR -> LOGIC_AND LOGIC_OR_2
//     private void LOGIC_OR() {
//         if(hasErrors)
//         return;
//         LOGIC_AND();
//         LOGIC_OR_2();
//     }
//     //LOGIC_OR_2 -> or LOGIC_AND LOGIC_OR_2 | Ɛ
//     private void LOGIC_OR_2() {
//         if(hasErrors)
//         return;
//         if(preanalisis.tipo == TipoToken.OR){
//             match(TipoToken.OR);
//             LOGIC_AND();
//             LOGIC_OR_2();
//         }
//     }
//     //LOGIC_AND -> EQUALITY LOGIC_AND_2
//     private void LOGIC_AND() {
//         if(hasErrors)
//         return;
//         EQUALITY();
//         LOGIC_AND_2();
//     }
//     //LOGIC_AND_2 -> and EQUALITY LOGIC_AND_2 | Ɛ
//     private void LOGIC_AND_2() {
//         if(hasErrors)
//         return;
//         if(preanalisis.tipo == TipoToken.AND){
//             match(TipoToken.AND);
//             EQUALITY();
//             LOGIC_AND_2();
//         }
//     }
//     //EQUALITY -> COMPARISON EQUALITY_2
//     private void EQUALITY() {
//         if(hasErrors)
//         return;
//         COMPARISON();
//         EQUALITY_2();
//     }
//     //EQUALITY_2 -> != COMPARISON EQUALITY_2 | == COMPARISON EQUALITY_2 | Ɛ
//     private void EQUALITY_2() {
//         if(hasErrors)
//         return;
//         if(preanalisis.tipo == TipoToken.BANG_EQUAL){
//             match(TipoToken.BANG_EQUAL);
//             COMPARISON();
//             EQUALITY_2();
//         }else if(preanalisis.tipo == TipoToken.EQUAL_EQUAL){
//             match(TipoToken.EQUAL_EQUAL);
//             COMPARISON();
//             EQUALITY_2();
//         }
//     }
        
//     //COMPARISON -> TERM COMPARISON_2
//     private void COMPARISON() {
//         if(hasErrors)
//         return;
//         TERM();
//         COMPARISON_2();
//     }
//     //COMPARISON_2 -> > TERM COMPARISON_2 | >= TERM COMPARISON_2 | < TERM COMPARISON_2 | <= TERM COMPARISON_2 | Ɛ
//     private void COMPARISON_2() {
//         if(hasErrors)
//         return;
//         if(preanalisis.tipo == TipoToken.GREATER){
//             match(TipoToken.GREATER);
//             TERM();
//             COMPARISON_2();
//         }else if(preanalisis.tipo == TipoToken.GREATER_EQUAL){
//             match(TipoToken.GREATER_EQUAL);
//             TERM();
//             COMPARISON_2();
//         }else if(preanalisis.tipo == TipoToken.LESS){
//             match(TipoToken.LESS);
//             TERM();
//             COMPARISON_2();
//         }else if(preanalisis.tipo == TipoToken.LESS_EQUAL){
//             match(TipoToken.LESS_EQUAL);
//             TERM();
//             COMPARISON_2();
//         }
//     }
//     //TERM -> FACTOR TERM_2
//     private void TERM() {
//         if(hasErrors)
//         return;
//         FACTOR();
//         TERM_2();
//     }
//     //TERM_2 -> - FACTOR TERM_2 | + FACTOR TERM_2 | Ɛ
//     private void TERM_2() {
//         if(hasErrors)
//         return;
//         if(preanalisis.tipo == TipoToken.MINUS){
//             match(TipoToken.MINUS);
//             FACTOR();
//             TERM_2();
//         }else if(preanalisis.tipo == TipoToken.PLUS){
//             match(TipoToken.PLUS);
//             FACTOR();
//             TERM_2();
//         }
//     }
//     //FACTOR -> UNARY FACTOR_2
//     private void FACTOR() {
//         if(hasErrors)
//         return;
//         UNARY();
//         FACTOR_2();
//     }
//     //FACTOR_2 -> / UNARY FACTOR_2 | * UNARY FACTOR_2 | Ɛ
//     private void FACTOR_2() {
//         if(hasErrors)
//         return;
//         if(preanalisis.tipo == TipoToken.SLASH){
//             match(TipoToken.SLASH);
//             UNARY();
//             FACTOR_2();
//         }else if(preanalisis.tipo == TipoToken.STAR){
//             match(TipoToken.STAR);
//             UNARY();
//             FACTOR_2();
//         }
//     }
//     //UNARY -> ! UNARY | - UNARY | CALL
//     private void UNARY() {
//         if(hasErrors)
//         return;
//         if(preanalisis.tipo == TipoToken.BANG){
//             match(TipoToken.BANG);
//             UNARY();
//         }else if(preanalisis.tipo == TipoToken.MINUS){
//             match(TipoToken.MINUS);
//             UNARY();
//         }else if(preanalisis.tipo == TipoToken.BANG||
//             preanalisis.tipo == TipoToken.MINUS||
//             preanalisis.tipo == TipoToken.TRUE||
//             preanalisis.tipo == TipoToken.FALSE||
//             preanalisis.tipo == TipoToken.NULL||
//             preanalisis.tipo == TipoToken.NUMBER||
//             preanalisis.tipo == TipoToken.STRING||
//             preanalisis.tipo == TipoToken.IDENTIFIER||
//             preanalisis.tipo == TipoToken.LEFT_PAREN){
//             CALL();
//         }else{
//             hasErrors=true;
//         }
//     }
//     //CALL -> PRIMARY CALL_2
//     private void CALL() {
//         if(hasErrors)
//         return;
//             PRIMARY();
//             CALL_2();
//     }
//     //CALL_2 -> ( ARGUMENTS_OPC ) | Ɛ
//     private void CALL_2() {
//         if(hasErrors)
//         return;
//         if(preanalisis.tipo == TipoToken.LEFT_PAREN){
//             match(TipoToken.LEFT_PAREN);
//             ARGUMENTS_OPC();
//             match(TipoToken.RIGHT_PAREN);
//         }
//     }
//     //PRIMARY -> true | false | null | number | string | id | ( EXPRESSION )
//     private void PRIMARY() {
//         if(hasErrors)
//         return;
//         if(preanalisis.tipo == TipoToken.TRUE){
//             match(TipoToken.TRUE);
//         }else if(preanalisis.tipo == TipoToken.FALSE){
//             match(TipoToken.FALSE);
//         }else if(preanalisis.tipo == TipoToken.NULL){
//             match(TipoToken.NULL);
//         }else if(preanalisis.tipo == TipoToken.NUMBER){
//             match(TipoToken.NUMBER);
//         }else if(preanalisis.tipo == TipoToken.STRING){
//             match(TipoToken.STRING);
//         }else if(preanalisis.tipo == TipoToken.IDENTIFIER){
//             match(TipoToken.IDENTIFIER);
//         }else if(preanalisis.tipo == TipoToken.LEFT_PAREN){
//             match(TipoToken.LEFT_PAREN);
//             EXPRESSION();
//             match(TipoToken.RIGHT_PAREN);
//         }else{
//             hasErrors=true;
//         }
//     }
//     //FUNCTION -> id ( PARAMETERS_OPC ) BLOCK
//     private void FUNCTION() {
//        if(hasErrors)
//        return;
//        if(preanalisis.tipo == TipoToken.IDENTIFIER){
//         match(TipoToken.IDENTIFIER);
//         match(TipoToken.LEFT_PAREN);
//         PARAMETERS_OPC();
//         match(TipoToken.RIGHT_PAREN);
//         BLOCK();
//        }else{
//             hasErrors=true;
//         }
//     }
//     //FUNCTIONS -> FUN_DECL FUNCTIONS | Ɛ
//     private void FUNCTIONS() {
//         if(hasErrors)
//         return;
//         if(preanalisis.tipo == TipoToken.FUN){
//             FUN_DECL();
//             FUNCTIONS();
//         }
//     }
//     //PARAMETERS_OPC -> PARAMETERS | Ɛ
//     private void PARAMETERS_OPC() {
//         if(hasErrors)
//         return;
//          if(preanalisis.tipo == TipoToken.IDENTIFIER){
//             PARAMETERS();
//         }
//     }
//     //PARAMETERS -> id PARAMETERS_2
//     private void PARAMETERS() {
//         if(hasErrors)
//         return;
//         if(preanalisis.tipo == TipoToken.IDENTIFIER){
//             match(TipoToken.IDENTIFIER);
//             PARAMETERS_2();
//         } else {
//             hasErrors=true;
//         }
//     }
//     //PARAMETERS_2 -> , id PARAMETERS_2 | Ɛ
//     private void PARAMETERS_2() {
//         if (hasErrors)
//         return;
//         if(preanalisis.tipo==TipoToken.COMMA){
//             match(TipoToken.COMMA);
//             match(TipoToken.IDENTIFIER);
//             PARAMETERS_2();
//         }
//     }
//     //ARGUMENTS_OPC -> EXPRESSION ARGUMENTS | Ɛ
//     private void ARGUMENTS_OPC() {
//         if (hasErrors)
//         return; 
//         if(preanalisis.tipo == TipoToken.BANG||
//             preanalisis.tipo == TipoToken.MINUS||
//             preanalisis.tipo == TipoToken.TRUE||
//             preanalisis.tipo == TipoToken.FALSE||
//             preanalisis.tipo == TipoToken.NULL||
//             preanalisis.tipo == TipoToken.NUMBER||
//             preanalisis.tipo == TipoToken.STRING||
//             preanalisis.tipo == TipoToken.IDENTIFIER||
//             preanalisis.tipo == TipoToken.LEFT_PAREN){  
//             EXPRESSION();
//             ARGUMENTS();
//         }
//     }
//     //ARGUMENTS -> , EXPRESSION ARGUMENTS | Ɛ
//     private void ARGUMENTS() {
//         if (hasErrors)
//         return;
//         if(preanalisis.tipo==TipoToken.COMMA){
//             match(TipoToken.COMMA);
//             EXPRESSION();
//             ARGUMENTS();
//         }
//     }

//     private void match(TipoToken tt) {
//         if (preanalisis.tipo == tt) {
//             chari += preanalisis.lexema.length(); // Incrementa el contador de caracteres
//             i++;
//             if (i < tokens.size()) {
//                 preanalisis = tokens.get(i);
//             }
//         } else {
//             hasErrors = true;
//             System.out.println("Error en el carácter " + chari + ": Se esperaba " + tt + " y se recibió " + preanalisis.tipo);
//         }
//     }
    
    
// }
import java.util.ArrayList;
import java.util.List;



public class ASDR implements Parser{

    private final List<Token> tokens;
    private int i = 0;
    private int chari = 0;
    private boolean hasErrors = false;

    private Token preanalisis;
    
    
    public ASDR(List<Token> tokens) {
        this.tokens = tokens;
        preanalisis = this.tokens.get(i);
    }

    //@Override
    public boolean parse() {
        PROGRAM();

        if (preanalisis.tipo != TipoToken.EOF && !hasErrors) {
            System.out.println("Programa sintácticamente correcto.");
        } else {
            System.out.println("Se encontraron errores sintácticos.");
        }
        return false;
    }

    //PROGRAM -> DECLARATION
    private void PROGRAM() {
        // if(hasErrors)
        // return; 

        List<Statement> statements = new ArrayList<>();

        DECLARATION(statements);
    }
    
    //DECLARATION -> FUN_DECL DECLARATION | VAR_DECL DECLARATION | STATEMENT DECLARATION | Ɛ
private void DECLARATION(List<Statement> statements){
    if(preanalisis.tipo == TipoToken.FUN){
           Statement expr = FUN_DECL();
           statements.add(expr);
           DECLARATION(statements);
    }
    else if(preanalisis.tipo == TipoToken.VAR){
        Statement expr = VAR_DECL();
        statements.add(expr);
           DECLARATION(statements);
          
    }
        else if(preanalisis.tipo == TipoToken.BANG ||
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
        Statement expr = STATEMENT();
        statements.add(expr);
        DECLARATION(statements);

    }

}
    //     private Expression DECLARATION() {
//         if(hasErrors)
//         return; 
//         if (preanalisis.tipo == TipoToken.FUN) {
//             FUN_DECL();
//             DECLARATION();
//         }else if(preanalisis.tipo == TipoToken.VAR){
//             VAR_DECL();
//             DECLARATION();
//         }else if(preanalisis.tipo == TipoToken.BANG ||
//             preanalisis.tipo == TipoToken.MINUS ||
//             preanalisis.tipo == TipoToken.TRUE ||
//             preanalisis.tipo == TipoToken.FALSE ||
//             preanalisis.tipo == TipoToken.NULL ||
//             preanalisis.tipo == TipoToken.NUMBER ||
//             preanalisis.tipo == TipoToken.STRING ||
//             preanalisis.tipo == TipoToken.IDENTIFIER ||
//             preanalisis.tipo == TipoToken.LEFT_PAREN ||
//             preanalisis.tipo == TipoToken.FOR ||
//             preanalisis.tipo == TipoToken.IF ||
//             preanalisis.tipo == TipoToken.PRINT ||
//             preanalisis.tipo == TipoToken.RETURN ||
//             preanalisis.tipo == TipoToken.WHILE ||
//             preanalisis.tipo == TipoToken.LEFT_BRACE){
                
//            STATEMENT();
//            DECLARATION(); 
//         }
// }

    //FUN_DECL -> fun FUNCTION
    private Statement FUN_DECL() {
        //if(hasErrors)
        //return null; 
        Statement expr=null;
          if(preanalisis.tipo==TipoToken.FUN){
            match(TipoToken.FUN);
             expr= FUNCTION(); 
          }
          return expr;
    }

    // private Expression FUN_DECL() {
    //     if(hasErrors)
    //     return; 
    //     if (preanalisis.tipo == TipoToken.FUN) {
    //         match(TipoToken.FUN);
    //         FUNCTION();
    //     }else{
    //         hasErrors = true;
    //     }
    // }


    //VAR_DECL -> var id VAR_INIT ;
    private Statement VAR_DECL() {
        //if(hasErrors)
        //return null; 
        Statement expr=null;
        if (preanalisis.tipo == TipoToken.VAR) {
            match(TipoToken.VAR);
            match(TipoToken.IDENTIFIER);
            expr= VAR_INIT();
            match(TipoToken.SEMICOLON);
            
        } 
        return expr;
    }

    // private Expression VAR_DECL() {
    //     if(hasErrors)
    //     return; 
    //     if (preanalisis.tipo == TipoToken.VAR) {
    //         match(TipoToken.VAR);
    //         match(TipoToken.IDENTIFIER);
    //         VAR_INIT();
    //         match(TipoToken.SEMICOLON);
    //     } else {
    //         hasErrors=true;
    //     }
    // }

    //VAR_INIT -> = EXPRESSION | Ɛ
    private Statement VAR_INIT() {
        //if(hasErrors)
       // return null;
        Statement expr=null;
        if (preanalisis.tipo == TipoToken.EQUAL) {
            match(TipoToken.EQUAL);
            expr= EXPRESSION();
        } 
        return expr;
    }

    // private Expression VAR_INIT() {
    //     if(hasErrors)
    //     return;
    //     if (preanalisis.tipo == TipoToken.EQUAL) {
    //         match(TipoToken.EQUAL);
    //         EXPRESSION();
    //     }
    // }

    //STATEMENT -> EXPR_STMT | FOR_STMT | IF_STMT | PRINT_STMT | RETURN_STMT | WHILE_STMT | BLOCK
    private Statement STATEMENT() {
        //if(hasErrors)
        // null; 
        Statement expr=null;
        if (preanalisis.tipo == TipoToken.BANG ||
            preanalisis.tipo == TipoToken.MINUS ||
            preanalisis.tipo == TipoToken.TRUE ||
            preanalisis.tipo == TipoToken.FALSE ||
            preanalisis.tipo == TipoToken.NULL ||
            preanalisis.tipo == TipoToken.NUMBER ||
            preanalisis.tipo == TipoToken.STRING ||
            preanalisis.tipo == TipoToken.IDENTIFIER ||
            preanalisis.tipo == TipoToken.LEFT_PAREN) {
    
            expr= EXPR_STMT();
            //return expr;

        } else if (preanalisis.tipo == TipoToken.FOR) {
           // match(TipoToken.FOR);
            expr= FOR_STMT();
            //return expr;  // Procesa un bucle for
        } else if (preanalisis.tipo == TipoToken.IF) {
            //match(TipoToken.IF);
            expr= IF_STMT(); 
            //return expr; // Procesa una instrucción if
        } else if (preanalisis.tipo == TipoToken.PRINT) {
            //match(TipoToken.PRINT);
            expr= PRINT_STMT(); 
            //return expr; // Procesa una instrucción print
        } else if (preanalisis.tipo == TipoToken.RETURN) {
            //match(TipoToken.RETURN);
           expr=  RETURN_STMT();  
           //return expr;// Procesa una instrucción return
        } else if (preanalisis.tipo == TipoToken.WHILE) {
            //match(TipoToken.WHILE);
           expr=  WHILE_STMT(); 
           //return expr; // Procesa un bucle while
        } else if (preanalisis.tipo == TipoToken.LEFT_BRACE) {
            //match(TipoToken.LEFT_BRACE);
            expr= BLOCK();  // Procesa un bloque de código
            //return expr;
            //match(TipoToken.RIGHT_BRACE);
        }
        return expr;
    }

    //     private Expression STATEMENT() {
    //     if(hasErrors)
    //     return; 
    //     if (preanalisis.tipo == TipoToken.BANG ||
    //         preanalisis.tipo == TipoToken.MINUS ||
    //         preanalisis.tipo == TipoToken.TRUE ||
    //         preanalisis.tipo == TipoToken.FALSE ||
    //         preanalisis.tipo == TipoToken.NULL ||
    //         preanalisis.tipo == TipoToken.NUMBER ||
    //         preanalisis.tipo == TipoToken.STRING ||
    //         preanalisis.tipo == TipoToken.IDENTIFIER ||
    //         preanalisis.tipo == TipoToken.LEFT_PAREN) {
    
    //         EXPR_STMT();
    //     } else if (preanalisis.tipo == TipoToken.FOR) {
    //        // match(TipoToken.FOR);
    //         FOR_STMT();  // Procesa un bucle for
    //     } else if (preanalisis.tipo == TipoToken.IF) {
    //         //match(TipoToken.IF);
    //         IF_STMT();  // Procesa una instrucción if
    //     } else if (preanalisis.tipo == TipoToken.PRINT) {
    //         //match(TipoToken.PRINT);
    //         PRINT_STMT();  // Procesa una instrucción print
    //     } else if (preanalisis.tipo == TipoToken.RETURN) {
    //         //match(TipoToken.RETURN);
    //         RETURN_STMT();  // Procesa una instrucción return
    //     } else if (preanalisis.tipo == TipoToken.WHILE) {
    //         //match(TipoToken.WHILE);
    //         WHILE_STMT();  // Procesa un bucle while
    //     } else if (preanalisis.tipo == TipoToken.LEFT_BRACE) {
    //         //match(TipoToken.LEFT_BRACE);
    //         BLOCK();  // Procesa un bloque de código
    //         //match(TipoToken.RIGHT_BRACE);
    //     }else{
    //         hasErrors=true;
    //     }
    // }

    //EXPR_STMT -> EXPRESSION ;
    private Statement EXPR_STMT() {           //DUDAAAAAAAAAAAAAAAA como usamos el StmtExpression?
        //if(hasErrors)
        //return null;
        Expression expr= EXPRESSION();
        match(TipoToken.SEMICOLON);  // Espera el punto y coma al final
        return new StmtExpression(expr);
        
    }

    //     private Expression EXPR_STMT() {
    //     if(hasErrors)
    //     return;
    //     EXPRESSION();
    //     match(TipoToken.SEMICOLON);  // Espera el punto y coma al final
    // }


    //FOR_STMT -> for ( FOR_STMT_1 FOR_STMT_2 FOR_STMT_3 ) STATEMENT                 ///DUDAAAAAAAAAAAAAAAAAAAAS ¿Que se regresa aqui?
    private Statement FOR_STMT() {
       // if(hasErrors)
        //return null;
        if(preanalisis.tipo == TipoToken.FOR){
            match(TipoToken.FOR);
            match(TipoToken.LEFT_PAREN);
            Statement expr1= FOR_STMT_1();     
            Expression expr2=FOR_STMT_2();
            Expression expr3=FOR_STMT_3();
            match(TipoToken.RIGHT_PAREN);  
            Statement expr4 =STATEMENT();     
        }
        // else{
        //     hasErrors=true;                                               ///SE QUITA EL HASERROR VEA??
        // }
    }

    //     private Expression FOR_STMT() {
    //     if(hasErrors)
    //     return null;
    //     if(preanalisis.tipo == TipoToken.FOR){
    //         match(TipoToken.FOR);
    //         match(TipoToken.LEFT_PAREN);
    //         FOR_STMT_1();
    //         FOR_STMT_2();
    //         FOR_STMT_3();
    //         match(TipoToken.RIGHT_PAREN);  
    //         STATEMENT();     
    //     }else{
    //         hasErrors=true;
    //     }
    // }

    //FOR_STMT_1 -> VAR_DECL | EXPR_STMT | ;
    private Statement FOR_STMT_1() {
        //if(hasErrors)
        //return null;
        Statement expr=null;
        if(preanalisis.tipo == TipoToken.VAR){
            expr= VAR_DECL();                   //DUDAAAAAAAAA VAR DECL ES STATEMENT PERO SE TIENE QUE REGRESAR UN EXPRESSION
            //return expr;
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
        
           expr= EXPR_STMT();
          //return expr;
        }else if(preanalisis.tipo == TipoToken.SEMICOLON){
            match(TipoToken.SEMICOLON);
        }else{
            hasErrors=true;
        }
        return expr;
    }

    //     private Expression FOR_STMT_1() {
    //     if(hasErrors)
    //     return null;
    //     if(preanalisis.tipo == TipoToken.VAR){
    //         VAR_DECL();    
    //     }else if(preanalisis.tipo == TipoToken.BANG ||
    //         preanalisis.tipo == TipoToken.MINUS ||
    //         preanalisis.tipo == TipoToken.TRUE ||
    //         preanalisis.tipo == TipoToken.FALSE ||
    //         preanalisis.tipo == TipoToken.NULL ||
    //         preanalisis.tipo == TipoToken.NUMBER ||
    //         preanalisis.tipo == TipoToken.STRING ||
    //         preanalisis.tipo == TipoToken.IDENTIFIER ||
    //         preanalisis.tipo == TipoToken.LEFT_PAREN
    //         ){
        
    //         EXPR_STMT();
    //     }else if(preanalisis.tipo == TipoToken.SEMICOLON){
    //         match(TipoToken.SEMICOLON);
    //     }else{
    //         hasErrors=true;
    //     }
    // }

    //FOR_STMT_2 -> EXPRESSION; | ;
    private Expression FOR_STMT_2() {          /// DUDAAAAAAAAAAAAAA ESTA BIEN PUESTO EL EXPRESSION?
        //if(hasErrors)
        //return null;
        Expression expr=null;
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
            expr= EXPRESSION();
            match(TipoToken.SEMICOLON);
            //return expr;
        } 
        else if(preanalisis.tipo == TipoToken.SEMICOLON){
            match(TipoToken.SEMICOLON);
        }
        return expr;
        // else{
        //     hasErrors=true;
        // }
    }

    // private Expression FOR_STMT_2() {
    //     if(hasErrors)
    //     return;
    //     if (preanalisis.tipo == TipoToken.BANG ||
    //         preanalisis.tipo == TipoToken.MINUS ||
    //         preanalisis.tipo == TipoToken.TRUE ||
    //         preanalisis.tipo == TipoToken.FALSE ||
    //         preanalisis.tipo == TipoToken.NULL ||
    //         preanalisis.tipo == TipoToken.NUMBER ||
    //         preanalisis.tipo == TipoToken.STRING ||
    //         preanalisis.tipo == TipoToken.IDENTIFIER ||
    //         preanalisis.tipo == TipoToken.LEFT_PAREN
    //         ) {
    //         EXPRESSION();
    //         match(TipoToken.SEMICOLON);
    //     } 
    //     else if(preanalisis.tipo == TipoToken.SEMICOLON){
    //         match(TipoToken.SEMICOLON);
    //     }
    //     else{
    //         hasErrors=true;
    //     }
    // }

    //FOR_STMT_3 -> EXPRESSION | 
    
    private Expression FOR_STMT_3() {
       // if(hasErrors)
        //return null; 
        Expression expr=null;
        if (preanalisis.tipo == TipoToken.BANG ||
            preanalisis.tipo == TipoToken.MINUS ||
            preanalisis.tipo == TipoToken.TRUE ||
            preanalisis.tipo == TipoToken.FALSE ||
            preanalisis.tipo == TipoToken.NULL ||
            preanalisis.tipo == TipoToken.NUMBER ||
            preanalisis.tipo == TipoToken.STRING ||
            preanalisis.tipo == TipoToken.IDENTIFIER ||
            preanalisis.tipo == TipoToken.LEFT_PAREN) {
        
            expr= EXPRESSION();
            
        }
        return expr;
    }

    // private Expression FOR_STMT_3() {
    //     if(hasErrors)
    //     return; 
    //     if (preanalisis.tipo == TipoToken.BANG ||
    //         preanalisis.tipo == TipoToken.MINUS ||
    //         preanalisis.tipo == TipoToken.TRUE ||
    //         preanalisis.tipo == TipoToken.FALSE ||
    //         preanalisis.tipo == TipoToken.NULL ||
    //         preanalisis.tipo == TipoToken.NUMBER ||
    //         preanalisis.tipo == TipoToken.STRING ||
    //         preanalisis.tipo == TipoToken.IDENTIFIER ||
    //         preanalisis.tipo == TipoToken.LEFT_PAREN) {
        
    //         EXPRESSION();
    //     }
    // }

    //IF_STMT -> if (EXPRESSION) STATEMENT ELSE_STATEMENT
    private Statement IF_STMT() {                                         //DUDAAAAAS IGUAL QUE SE REGRESA AQUI?? 
        //if(hasErrors)
       // return null;
       Expression expr=null;
       Statement expr2=null;
       Statement expr3=null;
        if(preanalisis.tipo == TipoToken.IF){
        match(TipoToken.IF);
        match(TipoToken.LEFT_PAREN);
         expr= EXPRESSION();
            match(TipoToken.RIGHT_PAREN);
            expr2= STATEMENT();
            expr3=ELSE_STATEMENT();
        }
        return new StmtIf(expr, expr2, expr3);
        // else {
        //     hasErrors=true;
        // }
    }

    // private Expression IF_STMT() {
    //     if(hasErrors)
    //     return;
    //     if(preanalisis.tipo == TipoToken.IF){
    //         match(TipoToken.IF);
    //         match(TipoToken.LEFT_PAREN);
    //         EXPRESSION();
    //         match(TipoToken.RIGHT_PAREN);
    //         STATEMENT();
    //         ELSE_STATEMENT();
    //     }else {
    //         hasErrors=true;
    //     }
    // }

    //ELSE_STATEMENT -> else STATEMENT | Ɛ
    private Statement ELSE_STATEMENT() {
        //if(hasErrors)
        //return null;
        if(preanalisis.tipo == TipoToken.ELSE){
            match(TipoToken.ELSE);
           Statement expr= STATEMENT();
           return expr;
        }
        else{
            return null;
        }


    }

    // private Expression ELSE_STATEMENT() {
    //     if(hasErrors)
    //     return;
    //     if(preanalisis.tipo == TipoToken.ELSE){
    //         match(TipoToken.ELSE);
    //         STATEMENT();
    //     }
    // }

    //PRINT_STMT -> print EXPRESSION ;
    private Statement PRINT_STMT() {
        //if(hasErrors)
        //return null;
        Expression expr=null;
        if(preanalisis.tipo == TipoToken.PRINT){
            match(TipoToken.PRINT);
            expr= EXPRESSION();
            match(TipoToken.SEMICOLON);
            
        } 
        // else{
        //     hasErrors=true;
        // }
        return new StmtPrint(expr);
    }

    // private Expression PRINT_STMT() {
    //     if(hasErrors)
    //     return;
    //     if(preanalisis.tipo == TipoToken.PRINT){
    //         match(TipoToken.PRINT);
    //         EXPRESSION();
    //         match(TipoToken.SEMICOLON);
    //     }else{
    //         hasErrors=true;
    //     }
    // }

    //RETURN_STMT -> return RETURN_EXP_OPC ;
    private Expression RETURN_STMT() {
        //if(hasErrors)
        //return null;
        Expression expr=null;
        if(preanalisis.tipo == TipoToken.RETURN){
            match(TipoToken.RETURN);
            expr= RETURN_EXP_OPC();
            match(TipoToken.SEMICOLON);
             
        }
        return expr;
        // else{
        //     hasErrors=true;
        // }
    }

    // private Expression RETURN_STMT() {
    //     if(hasErrors)
    //     return;
    //     if(preanalisis.tipo == TipoToken.RETURN){
    //         match(TipoToken.RETURN);
    //         RETURN_EXP_OPC();
    //         match(TipoToken.SEMICOLON);
    //     }else{
    //         hasErrors=true;
    //     }
    // }

    //RETURN_EXP_OPC -> EXPRESSION | Ɛ
    private Expression RETURN_EXP_OPC() {
        //if(hasErrors)
        //return null;
        Expression expr=null;
        if(preanalisis.tipo == TipoToken.BANG ||
            preanalisis.tipo == TipoToken.MINUS ||
            preanalisis.tipo == TipoToken.TRUE ||
            preanalisis.tipo == TipoToken.FALSE ||
            preanalisis.tipo == TipoToken.NULL ||
            preanalisis.tipo == TipoToken.NUMBER ||
            preanalisis.tipo == TipoToken.STRING ||
            preanalisis.tipo == TipoToken.IDENTIFIER ||
            preanalisis.tipo == TipoToken.LEFT_PAREN){
 
           expr = EXPRESSION();
           
        }
        return expr;
    }

    // private Expression RETURN_EXP_OPC() {
    //     if(hasErrors)
    //     return;
    //     if(preanalisis.tipo == TipoToken.BANG ||
    //         preanalisis.tipo == TipoToken.MINUS ||
    //         preanalisis.tipo == TipoToken.TRUE ||
    //         preanalisis.tipo == TipoToken.FALSE ||
    //         preanalisis.tipo == TipoToken.NULL ||
    //         preanalisis.tipo == TipoToken.NUMBER ||
    //         preanalisis.tipo == TipoToken.STRING ||
    //         preanalisis.tipo == TipoToken.IDENTIFIER ||
    //         preanalisis.tipo == TipoToken.LEFT_PAREN){
 
    //         EXPRESSION();
    //     }
    // }

    //WHILE_STMT -> while ( EXPRESSION ) STATEMENT
    private Statement WHILE_STMT() {
        //if(hasErrors)
        //return null;
        Expression expr=null;
        Statement expr2=null;
        if(preanalisis.tipo == TipoToken.WHILE){
            match(TipoToken.WHILE);
            match(TipoToken.LEFT_PAREN);
            expr= EXPRESSION();
            match(TipoToken.RIGHT_PAREN);
            expr2= STATEMENT();
        }
        return new StmtLoop(expr, expr2);
        // else{
        //     hasErrors=true;
        // }
    }

    // private Expression WHILE_STMT() {
    //     if(hasErrors)
    //     return;
    //     if(preanalisis.tipo == TipoToken.WHILE){
    //         match(TipoToken.WHILE);
    //         match(TipoToken.LEFT_PAREN);
    //         EXPRESSION();
    //         match(TipoToken.RIGHT_PAREN);
    //         STATEMENT();
    //     }else{
    //         hasErrors=true;
    //     }
    // }

    //BLOCK -> { DECLARATION }
    private Statement BLOCK() {                  //DUDAAAAAAAAAAAAAAAAAA si etsa bien declaration?
        //if (hasErrors)
        //return null; 

        List<Statement> statements = new ArrayList<>();
        
        if (preanalisis.tipo == TipoToken.LEFT_BRACE) {
            match(TipoToken.LEFT_BRACE);
            DECLARATION(statements);
            match(TipoToken.RIGHT_BRACE);
            
        } 
        return new StmtBlock(statements);
        // else {
        //     hasErrors=true;
        // }
    }

    // private Expression BLOCK() {
    //     if (hasErrors)
    //     return; 
    //     if (preanalisis.tipo == TipoToken.LEFT_BRACE) {
    //         match(TipoToken.LEFT_BRACE);
    //         DECLARATION();
    //         match(TipoToken.RIGHT_BRACE);
    //     } else {
    //         hasErrors=true;
    //     }
    // }

    //EXPRESSION -> ASSIGNMENT
    private Expression EXPRESSION() {
        //if(hasErrors)
        //return null;
       Expression expr= ASSIGNMENT();
       return expr;
    }

    // private Expression EXPRESSION() {
    //     if(hasErrors)
    //     return;
    //     ASSIGNMENT();
    // }
    
    //ASSIGNMENT -> LOGIC_OR ASSIGNMENT_OPC
    private Expression ASSIGNMENT() {          //DUDAAAAAAAAAAA que onda con assignment
       // if(hasErrors)
       // return null;
        Expression expr=LOGIC_OR();
        return ASSIGNMENT_OPC(expr);
    }

    // private Expression ASSIGNMENT() {
    //     if(hasErrors)
    //     return;
    //     LOGIC_OR();
    //     ASSIGNMENT_OPC();

    // }

    // ASSIGNMENT_OPC -> = EXPRESSION | Ɛ
    private Expression ASSIGNMENT_OPC(Expression expr) {
        //if(hasErrors)
        //return null;

        if(preanalisis.tipo == TipoToken.EQUAL){
            match(TipoToken.EQUAL);
            Expression expr2 = EXPRESSION();

            if(expr instanceof ExprVariable){
                Token t = ((ExprVariable) expr).name;

                 return new ExprAssign(t, expr2);

            }
            else{
                // Error throw new Exception
            }

           


        }
        return expr;
    }

    // private Expression ASSIGNMENT_OPC() {
    //     if(hasErrors)
    //     return;
    //     if(preanalisis.tipo == TipoToken.EQUAL){
    //         match(TipoToken.EQUAL);
    //         EXPRESSION();
    //     }
    // }

    //LOGIC_OR -> LOGIC_AND LOGIC_OR_2
    private Expression LOGIC_OR() {
        //if(hasErrors)
        //return null;
       Expression expr= LOGIC_AND();
       Expression expr2= LOGIC_OR_2(); //aqui hay chamba xd
       return expr;
    }

    // private Expression LOGIC_OR() {
    //     if(hasErrors)
    //     return;
    //     LOGIC_AND();
    //     LOGIC_OR_2();
    // }

    //LOGIC_OR_2 -> or LOGIC_AND LOGIC_OR_2 | Ɛ
    private Expression LOGIC_OR_2() {          //DUDAAAAAAAAAAAAAAAAAAAA QUE ONDA CON LOS LOGIC
        //if(hasErrors)
        //return null;
        Expression expr;
        Expression expr2;
        if(preanalisis.tipo == TipoToken.OR){
            match(TipoToken.OR);
            expr=LOGIC_AND();
            expr2= LOGIC_OR_2();
        }
        
    }

    // private Expression LOGIC_OR_2() {
    //     if(hasErrors)
    //     return;
    //     if(preanalisis.tipo == TipoToken.OR){
    //         match(TipoToken.OR);
    //         LOGIC_AND();
    //         LOGIC_OR_2();
    //     }
    // }

    //LOGIC_AND -> EQUALITY LOGIC_AND_2
    private Expression LOGIC_AND() {
       // if(hasErrors)
        //return null;
        Expression expr=EQUALITY();
        Expression expr2= LOGIC_AND_2();
        return expr;

    }

    // private Expression LOGIC_AND() {
    //     if(hasErrors)
    //     return;
    //     EQUALITY();
    //     LOGIC_AND_2();
    // }

    //LOGIC_AND_2 -> and EQUALITY LOGIC_AND_2 | Ɛ
    private Expression LOGIC_AND_2() {      //DUDAAAAAAAAAAAA QUE ONDA CON LA RECURRENCIA DE LOGIC_AND_2 
        //if(hasErrors)
       // return null;
        if(preanalisis.tipo == TipoToken.AND){
            match(TipoToken.AND);
           Expression expr= EQUALITY();
           Expression expr2= LOGIC_AND_2();
           return expr;
        }
    }

    // private Expression LOGIC_AND_2() {
    //     if(hasErrors)
    //     return;
    //     if(preanalisis.tipo == TipoToken.AND){
    //         match(TipoToken.AND);
    //         EQUALITY();
    //         LOGIC_AND_2();
    //     }
    // }

    //EQUALITY -> COMPARISON EQUALITY_2
    private Expression EQUALITY() {           //DUDAAAAAAAAAAA ASI SE USA EXPRBINARY
        //if(hasErrors)
        //return null;
        Expression expr=COMPARISON();
        Expression expr2 = EQUALITY_2();
        return new ExprBinary(expr, preanalisis, expr2);
    }

    // private Expression EQUALITY() {
    //     if(hasErrors)
    //     return;
    //     COMPARISON();
    //     EQUALITY_2();
    // }

    //EQUALITY_2 -> != COMPARISON EQUALITY_2 | == COMPARISON EQUALITY_2 | Ɛ
    private Expression EQUALITY_2() { 
        //if(hasErrors)
        //return null;
        if(preanalisis.tipo == TipoToken.BANG_EQUAL){
            match(TipoToken.BANG_EQUAL);
            Expression expr= COMPARISON();
            Expression expr2= EQUALITY_2();                             //DUDAAAAAAAAAAA que onda con la recurrencia de EQUALITY2    
            return new ExprBinary(expr, preanalisis, expr2);            //Asi esta bien?? 
        }else if(preanalisis.tipo == TipoToken.EQUAL_EQUAL){
            match(TipoToken.EQUAL_EQUAL);
           Expression expr= COMPARISON();
           Expression expr2= EQUALITY_2();
           return new ExprBinary(expr, preanalisis, expr2);
        }
    }

    // private Expression EQUALITY_2() {
    //     if(hasErrors)
    //     return;
    //     if(preanalisis.tipo == TipoToken.BANG_EQUAL){
    //         match(TipoToken.BANG_EQUAL);
    //         COMPARISON();
    //         EQUALITY_2();
    //     }else if(preanalisis.tipo == TipoToken.EQUAL_EQUAL){
    //         match(TipoToken.EQUAL_EQUAL);
    //         COMPARISON();
    //         EQUALITY_2();
    //     }
    // }

        
    //COMPARISON -> TERM COMPARISON_2
    private Expression COMPARISON() {
        //if(hasErrors)
        //return null;
        Expression expr=TERM();
        return COMPARISON_2(expr);
    }
    
    // private Expression COMPARISON() {
    //     if(hasErrors)
    //     return;
    //     TERM();
    //     COMPARISON_2();
    // }

    //COMPARISON_2 -> > TERM COMPARISON_2 | >= TERM COMPARISON_2 | < TERM COMPARISON_2 | <= TERM COMPARISON_2 | Ɛ
    private Expression COMPARISON_2(Expression expr) {           
       // if(hasErrors)
       // return null;
        if(preanalisis.tipo == TipoToken.GREATER){
            match(TipoToken.GREATER);
            Token t = previous();
            Expression expr2 = TERM();

            Expression expr3 = new ExprBinary(expr, t, expr2);
           COMPARISON_2(expr3);              ///DUDAAAAAAAAAAAA Esta bien implementada la recurrencia??
           
        }else if(preanalisis.tipo == TipoToken.GREATER_EQUAL){
           match(TipoToken.GREATER_EQUAL);
           
           Expression expr= TERM();
           expressions.add(expr);
           COMPARISON_2(expressions);
          
        }else if(preanalisis.tipo == TipoToken.LESS){
            match(TipoToken.LESS);
           Expression expr= TERM();
           expressions.add(expr);
           COMPARISON_2(expressions);
           
        }else if(preanalisis.tipo == TipoToken.LESS_EQUAL){
            match(TipoToken.LESS_EQUAL);
           Expression expr= TERM();
           expressions.add(expr);
           COMPARISON_2(expressions);
          
        }
        return expr;
    }

    // private Expression COMPARISON_2() {
    //     if(hasErrors)
    //     return;
    //     if(preanalisis.tipo == TipoToken.GREATER){
    //         match(TipoToken.GREATER);
    //         TERM();
    //         COMPARISON_2();
    //     }else if(preanalisis.tipo == TipoToken.GREATER_EQUAL){
    //         match(TipoToken.GREATER_EQUAL);
    //         TERM();
    //         COMPARISON_2();
    //     }else if(preanalisis.tipo == TipoToken.LESS){
    //         match(TipoToken.LESS);
    //         TERM();
    //         COMPARISON_2();
    //     }else if(preanalisis.tipo == TipoToken.LESS_EQUAL){
    //         match(TipoToken.LESS_EQUAL);
    //         TERM();
    //         COMPARISON_2();
    //     }
    // }
    
    //TERM -> FACTOR TERM_2
    private Expression TERM() {
        //if(hasErrors)
        //return null;
       Expression expr= FACTOR();
       Expression expr2 = TERM_2();
       return new ExprBinary(expr, preanalisis, expr2);
    }

    // private Expression TERM() {
    //     if(hasErrors)
    //     return;
    //     FACTOR();
    //     TERM_2();
    // }

    //TERM_2 -> - FACTOR TERM_2 | + FACTOR TERM_2 | Ɛ
    private Expression TERM_2() {
        //if(hasErrors)
        //return null;
        Expression expr=null;
        Expression expr2=null;
        Expression expr3=null;
        if(preanalisis.tipo == TipoToken.MINUS){
            match(TipoToken.MINUS);
             expr=FACTOR();
             expr2=TERM_2();
             expr3 = new ExprBinary(expr, preanalisis, expr2);
            
        }else if(preanalisis.tipo == TipoToken.PLUS){
            match(TipoToken.PLUS);
             expr=FACTOR();
             expr2= TERM_2();
             expr3 =  new ExprBinary(expr, preanalisis, expr2);
            
        }
        return expr3;
    }

    // private Expression TERM_2() {
    //     if(hasErrors)
    //     return;
    //     if(preanalisis.tipo == TipoToken.MINUS){
    //         match(TipoToken.MINUS);
    //         FACTOR();
    //         TERM_2();
    //     }else if(preanalisis.tipo == TipoToken.PLUS){
    //         match(TipoToken.PLUS);
    //         FACTOR();
    //         TERM_2();
    //     }
    // }
    
    //FACTOR -> UNARY FACTOR_2
    private Expression FACTOR() {                      ///DUDAAAAAAAAAAAAAA ES EXPBINARY?? O COMO??
        Expression expr = UNARY();
        Expression expr2 = FACTOR_2();
        return new ExprBinary(expr, preanalisis, expr2);
    }

    // private Expression FACTOR() {
    //     Expression expr = UNARY();
    //     expr = FACTOR_2(expr);
    //     return expr;
    // }

    //FACTOR_2 -> / UNARY FACTOR_2 | * UNARY FACTOR_2 | Ɛ
    private Expression FACTOR_2() {           /// DUDAAAAAAAAAAAAAAAAAAAAAAAAAAA EXPLICACION DEL EXPRBINARY Y COMO SE HACEN CASOS COMO FACTOR_2
        //if(hasErrors)
        //return;
        if(preanalisis.tipo == TipoToken.SLASH){
            match(TipoToken.SLASH);
            Token operador = previous();
            Expression expr2 = UNARY();
            ExprBinary expb = new ExprBinary(expr, operador, expr2);
            return FACTOR_2(expb);
        }else if(preanalisis.tipo == TipoToken.STAR){
            match(TipoToken.STAR);
            operador = previous();
            expr2 = UNARY();
            exprb = new ExprBinary(expr, operador, expr2);
            return FACTOR_2(exprb);
        }
        return expr;
    }

    // private Expression FACTOR_2(Expression expr) {
    //     //if(hasErrors)
    //     //return;
    //     if(preanalisis.tipo == TipoToken.SLASH){
    //         match(TipoToken.SLASH);
    //         Token operador = previous();
    //         Expression expr2 = UNARY();
    //         ExprBinary expb = new ExprBinary(expr, operador, expr2);
    //         return FACTOR_2(exprb);
    //     }else if(preanalisis.tipo == TipoToken.STAR){
    //         match(TipoToken.STAR);
    //         operador = previous();
    //         expr2 = UNARY();
    //         exprb = new ExprBinary(expr, operador, expr2);
    //         return FACTOR_2(exprb);
    //     }
    //     return expr;
    // }

    //UNARY -> ! UNARY | - UNARY | CALL
    private Expression UNARY() {
        //if(hasErrors)
       // return null;
       Expression expr=null;
        if(preanalisis.tipo == TipoToken.BANG){
            match(TipoToken.BANG);
             expr=UNARY();
            return expr;
        }else if(preanalisis.tipo == TipoToken.MINUS){
            match(TipoToken.MINUS);
            expr= UNARY();
           return null;
        }else if(preanalisis.tipo == TipoToken.BANG||
            preanalisis.tipo == TipoToken.MINUS||
            preanalisis.tipo == TipoToken.TRUE||
            preanalisis.tipo == TipoToken.FALSE||
            preanalisis.tipo == TipoToken.NULL||
            preanalisis.tipo == TipoToken.NUMBER||
            preanalisis.tipo == TipoToken.STRING||
            preanalisis.tipo == TipoToken.IDENTIFIER||
            preanalisis.tipo == TipoToken.LEFT_PAREN){
            return CALL();
        }
        return expr;
        // else{
        //     hasErrors=true;
        // }
    }

    // private Expression UNARY() {
    //     if(hasErrors)
    //     return;
    //     if(preanalisis.tipo == TipoToken.BANG){
    //         match(TipoToken.BANG);
    //         UNARY();
    //     }else if(preanalisis.tipo == TipoToken.MINUS){
    //         match(TipoToken.MINUS);
    //         UNARY();
    //     }else if(preanalisis.tipo == TipoToken.BANG||
    //         preanalisis.tipo == TipoToken.MINUS||
    //         preanalisis.tipo == TipoToken.TRUE||
    //         preanalisis.tipo == TipoToken.FALSE||
    //         preanalisis.tipo == TipoToken.NULL||
    //         preanalisis.tipo == TipoToken.NUMBER||
    //         preanalisis.tipo == TipoToken.STRING||
    //         preanalisis.tipo == TipoToken.IDENTIFIER||
    //         preanalisis.tipo == TipoToken.LEFT_PAREN){
    //         CALL();
    //     }else{
    //         hasErrors=true;
    //     }
    // }

    //CALL -> PRIMARY CALL_2
    private Expression CALL() {
        //if(hasErrors)
        //return null;
           Expression expr= PRIMARY();
           expr = CALL_2(expr);
           return expr;
    }

    // private Expression CALL() {
    //     if(hasErrors)
    //     return;
    //         PRIMARY();
    //         CALL_2();
    // }

    //CALL_2 -> ( ARGUMENTS_OPC ) | Ɛ      /// DUDAAAAAAAAAAAAAAAAAAAAAAAA QUE HACER DESPUES DE )
    private Expression CALL_2(Expression expr) {
        //if(hasErrors)
        //return null;
        if(preanalisis.tipo == TipoToken.LEFT_PAREN){
            match(TipoToken.LEFT_PAREN);
           List<Expression> expresiones= ARGUMENTS_OPC();
            match(TipoToken.RIGHT_PAREN);
            return new ExprCallFunction(expr, expresiones);
        }
    }

    // private Expression CALL_2() {
    //     if(hasErrors)
    //     return;
    //     if(preanalisis.tipo == TipoToken.LEFT_PAREN){
    //         match(TipoToken.LEFT_PAREN);
    //         ARGUMENTS_OPC();
    //         match(TipoToken.RIGHT_PAREN);
    //     }
    // }

    //PRIMARY -> true | false | null | number | string | id | ( EXPRESSION )        ///DUDAAAAAAAAAAAAA QUE ONDA CON ExprLiteral
    private Expression PRIMARY() {
       // if(hasErrors)
        //return;
        if(preanalisis.tipo == TipoToken.TRUE){
            match(TipoToken.TRUE);
            //return new ExprLiteral(true);
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
           Expression expr= EXPRESSION();
            match(TipoToken.RIGHT_PAREN);
            return expr;
        }
        // else{
        //     hasErrors=true;
        // }
    }

    // private Expression PRIMARY() {
    //     if(hasErrors)
    //     return;
    //     if(preanalisis.tipo == TipoToken.TRUE){
    //         match(TipoToken.TRUE);
    //     }else if(preanalisis.tipo == TipoToken.FALSE){
    //         match(TipoToken.FALSE);
    //     }else if(preanalisis.tipo == TipoToken.NULL){
    //         match(TipoToken.NULL);
    //     }else if(preanalisis.tipo == TipoToken.NUMBER){
    //         match(TipoToken.NUMBER);
    //     }else if(preanalisis.tipo == TipoToken.STRING){
    //         match(TipoToken.STRING);
    //     }else if(preanalisis.tipo == TipoToken.IDENTIFIER){
    //         match(TipoToken.IDENTIFIER);
    //     }else if(preanalisis.tipo == TipoToken.LEFT_PAREN){
    //         match(TipoToken.LEFT_PAREN);
    //         EXPRESSION();
    //         match(TipoToken.RIGHT_PAREN);
    //     }else{
    //         hasErrors=true;
    //     }
    // }

    //FUNCTION -> id ( PARAMETERS_OPC ) BLOCK
    private Statement FUNCTION() {//esother??????????????????????????????
       if(hasErrors)
       return null;
       if(preanalisis.tipo == TipoToken.IDENTIFIER){
        match(TipoToken.IDENTIFIER);
        match(TipoToken.LEFT_PAREN);
        Statement expr=PARAMETERS_OPC();
        match(TipoToken.RIGHT_PAREN);
       Expression expr2= BLOCK();
       }else{
            hasErrors=true;
        }
    }

    // private Expression FUNCTION() {
    //    if(hasErrors)
    //    return;
    //    if(preanalisis.tipo == TipoToken.IDENTIFIER){
    //     match(TipoToken.IDENTIFIER);
    //     match(TipoToken.LEFT_PAREN);
    //     PARAMETERS_OPC();
    //     match(TipoToken.RIGHT_PAREN);
    //     BLOCK();
    //    }else{
    //         hasErrors=true;
    //     }
    // }

    //FUNCTIONS -> FUN_DECL FUNCTIONS | Ɛ
    private Expression FUNCTIONS(Expression expr) {
        if(hasErrors)
        return null;
        if(preanalisis.tipo == TipoToken.FUN){
           Expression expr= FUN_DECL();
           expr = FUNCTIONS(expr);
           return expr;
        }
    }

    // private Expression FUNCTIONS() {
    //     if(hasErrors)
    //     return;
    //     if(preanalisis.tipo == TipoToken.FUN){
    //         FUN_DECL();
    //         FUNCTIONS();
    //     }
    // }

    //PARAMETERS_OPC -> PARAMETERS | Ɛ
    private Statement PARAMETERS_OPC() {
        if(hasErrors)
        return null;
         if(preanalisis.tipo == TipoToken.IDENTIFIER){
           Statement expr= PARAMETERS();
           return expr;
        }
    }

    // private Expression PARAMETERS_OPC() {
    //     if(hasErrors)
    //     return;
    //      if(preanalisis.tipo == TipoToken.IDENTIFIER){
    //         PARAMETERS();
    //     }
    // }

    //PARAMETERS -> id PARAMETERS_2
    private Statement PARAMETERS() {
        if(hasErrors)
        return null;
        if(preanalisis.tipo == TipoToken.IDENTIFIER){
            match(TipoToken.IDENTIFIER);
            Statement expr=PARAMETERS_2();
            return expr;
        } else {
            hasErrors=true;
        }
    }

    //PARAMETERS_2 -> , id PARAMETERS_2 | Ɛ
    private Statement PARAMETERS_2() {
        if (hasErrors)
        return null;
        if(preanalisis.tipo==TipoToken.COMMA){
            match(TipoToken.COMMA);
            match(TipoToken.IDENTIFIER);
            Statement expr=PARAMETERS_2();
            return expr;
        }
    }

    //PARAMETERS_2 -> , id PARAMETERS_2 | Ɛ
    // private Expression PARAMETERS_2() {
    //     if (hasErrors)
    //     return;
    //     if(preanalisis.tipo==TipoToken.COMMA){
    //         match(TipoToken.COMMA);
    //         match(TipoToken.IDENTIFIER);
    //         PARAMETERS_2();
    //     }
    // }



    //ARGUMENTS_OPC -> EXPRESSION ARGUMENTS | Ɛ
    private Statement ARGUMENTS_OPC() {
        if (hasErrors)
        return null; 
        if(preanalisis.tipo == TipoToken.BANG||
            preanalisis.tipo == TipoToken.MINUS||
            preanalisis.tipo == TipoToken.TRUE||
            preanalisis.tipo == TipoToken.FALSE||
            preanalisis.tipo == TipoToken.NULL||
            preanalisis.tipo == TipoToken.NUMBER||
            preanalisis.tipo == TipoToken.STRING||
            preanalisis.tipo == TipoToken.IDENTIFIER||
            preanalisis.tipo == TipoToken.LEFT_PAREN){  
            Statement expr=EXPRESSION();
            expr=ARGUMENTS(expr);
            return expr;
        }
    }



    //ARGUMENTS -> , EXPRESSION ARGUMENTS | Ɛ
    private Expression ARGUMENTS(Expression expr) {
        if (hasErrors)
        return null;
        if(preanalisis.tipo==TipoToken.COMMA){
            match(TipoToken.COMMA);
           Expression expr= EXPRESSION();
           expr = ARGUMENTS(expr);
           return expr;
        }
    }

    // private Expression ARGUMENTS() {
    //     if (hasErrors)
    //     return;
    //     if(preanalisis.tipo==TipoToken.COMMA){
    //         match(TipoToken.COMMA);
    //         EXPRESSION();
    //         ARGUMENTS();
    //     }
    // }


    private void match(TipoToken tt) {
        if (preanalisis.tipo == tt) {
            chari += preanalisis.lexema.length(); // Incrementa el contador de caracteres
            i++;
            if (i < tokens.size()) {
                preanalisis = tokens.get(i);
            }
        } else {
            hasErrors = true;
            System.out.println("Error en el carácter " + chari + ": Se esperaba " + tt + " y se recibió " + preanalisis.tipo);
        }
    }

    private Token previous() {
        return this.tokens.get(i - 1);
    }
    
    
}
