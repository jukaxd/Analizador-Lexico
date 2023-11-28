//A.Y

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// public class MiScanner {

//     private static final Map<String, TipoToken> palabrasReservadas;

//     static {
//         palabrasReservadas = new HashMap<>();
//         palabrasReservadas.put("and", TipoToken.AND);
//         palabrasReservadas.put("else", TipoToken.ELSE);
//         palabrasReservadas.put("false", TipoToken.FALSE);
//         palabrasReservadas.put("for", TipoToken.FOR);
//         palabrasReservadas.put("fun", TipoToken.FUN);
//         palabrasReservadas.put("if", TipoToken.IF);
//         palabrasReservadas.put("null", TipoToken.NULL);
//         palabrasReservadas.put("or", TipoToken.OR);
//         palabrasReservadas.put("print", TipoToken.PRINT);
//         palabrasReservadas.put("return", TipoToken.RETURN);
//         palabrasReservadas.put("true", TipoToken.TRUE);
//         palabrasReservadas.put("var", TipoToken.VAR);
//         palabrasReservadas.put("while", TipoToken.WHILE);
//     }

//     private final String source;

//     private final List<Token> tokens = new ArrayList<>();

//     public MiScanner(String source) {
//         this.source = source + " ";
//     }

//     public List<Token> scan() throws Exception {
//         int estado = 0;
//         String lexema = "";
//         char c;

//         for (int i = 0; i < source.length(); i++) {
//             c = source.charAt(i);

//             switch (estado) {
//                 case 0:
//                     if (c == '>') {
//                         estado = 1;
//                         lexema += c;

//                     } else if (c == '<') {
//                         estado = 4;
//                         lexema += c;

//                     } else if (c == '=') {
//                         estado = 7;
//                         lexema += c;

//                     } else if (c == '!') {
//                         estado = 10;
//                         lexema += c;
//                     } else if (Character.isLetter(c)) {
//                         estado = 13;
//                         lexema += c;

//                     } else if (c == '"') {
//                         estado = 24;
//                         lexema += c;

//                     } else if (c == '/') {
//                         estado = 26;
//                         lexema += c;

//                     } else if (Character.isDigit(c)) {
//                         estado = 15;
//                         lexema += c;

//                         /*
//                          * while(Character.isDigit(c)){
//                          * lexema += c;
//                          * i++;
//                          * c = source.charAt(i);
//                          * }
//                          * Token t = new Token(TipoToken.NUMBER, lexema, Integer.valueOf(lexema));
//                          * lexema = "";
//                          * estado = 0;
//                          * tokens.add(t);
//                          */

//                     }
//                     // Tokens de un solo caracter (Diagrama del alumno) DIAGRAMA 6
//                     else if (c == '+') {
//                         Token t = new Token(TipoToken.PLUS, String.valueOf(c));
//                         tokens.add(t);
//                         lexema = "";
                        
//                     } else if (c == '-') {
//                         Token t = new Token(TipoToken.MINUS, String.valueOf(c));
//                         tokens.add(t);
//                         lexema = "";
                        
//                     } else if (c == '.') {
//                         Token t = new Token(TipoToken.DOT, String.valueOf(c));
//                         tokens.add(t);
//                         lexema = "";
                        
//                     } else if (c == ',') {
//                         Token t = new Token(TipoToken.COMMA, String.valueOf(c));
//                         tokens.add(t);
//                         lexema = "";
                        
//                     } else if (c == ')') {
//                         Token t = new Token(TipoToken.RIGHT_PAREN, String.valueOf(c));
//                         tokens.add(t);
//                         lexema = "";
                        
//                     } else if (c == '(') {
//                         Token t = new Token(TipoToken.LEFT_PAREN, String.valueOf(c));
//                         tokens.add(t);
//                         lexema = "";
                        
//                     } else if (c == '}') {
//                         Token t = new Token(TipoToken.RIGHT_BRACE, String.valueOf(c));
//                         tokens.add(t);
//                         lexema = "";
                        
//                     } else if (c == '{') {
//                         Token t = new Token(TipoToken.LEFT_BRACE, String.valueOf(c));
//                         tokens.add(t);
//                         lexema = "";
                        
//                     } else if (c == '*') {
//                         Token t = new Token(TipoToken.STAR, String.valueOf(c));
//                         tokens.add(t);
//                         lexema = "";
                        
//                     } else if (c == ';') {
//                         Token t = new Token(TipoToken.SEMICOLON, String.valueOf(c));
//                         tokens.add(t);
//                         lexema = "";
                        
//                     } else if (c == '['){
//                         System.out.println("Error: caracter '[' no valido");
//                         System.exit(1);
                        
//                     } else if (c == ']'){
//                         System.out.println("Error: caracter ']' no valido");
//                         System.exit(1);
//                     }
//                     break;

//                 case 1: // Diagrama 1
//                     if (c == '=') {
//                         lexema += c;
                        
//                         Token t = new Token(TipoToken.GREATER_EQUAL, lexema);
//                         tokens.add(t);

//                         estado = 0;
//                         lexema = "";
//                     } else {
//                         Token t = new Token(TipoToken.GREATER, lexema);
//                         tokens.add(t);
//                         estado = 0;
//                         lexema = "";
//                         i--;
//                     }
//                     break;

//                 case 4: // Diagrama 1
//                     if (c == '=') {
                    
//                         lexema += c;

//                         Token t = new Token(TipoToken.LESS_EQUAL, lexema);
//                         tokens.add(t);

//                         estado = 0;
//                         lexema = "";
//                     } else {
//                         Token t = new Token(TipoToken.LESS, lexema);
//                         tokens.add(t);

//                         estado = 0;
//                         lexema = "";
//                         i--;
//                     }
//                     break;

//                 case 7: // Diagrama 1
//                     if (c == '=') {

//                         lexema += c;

//                         Token t = new Token(TipoToken.EQUAL_EQUAL, lexema);
//                         tokens.add(t);

//                         estado = 0;
//                         lexema = "";
//                     } else {
//                         Token t = new Token(TipoToken.EQUAL, lexema);
//                         tokens.add(t);

//                         estado = 0;
//                         lexema = "";
//                         i--;
//                     }
//                     break;

//                 case 10: // Diagrama 1
//                     if (c == '=') {

//                         lexema += c;
                        
//                         Token t = new Token(TipoToken.BANG_EQUAL, lexema);
//                         tokens.add(t);

//                         estado = 0;
//                         lexema = "";
//                     } else {
//                         Token t = new Token(TipoToken.BANG, lexema);
//                         tokens.add(t);

//                         estado = 0;
//                         lexema = "";
//                         i--;
//                     }
//                     break;

//                 case 13: //Diagrama 2
//                     if (Character.isLetterOrDigit(c)) {
//                         estado = 13;
//                         lexema += c;
//                     } else {
//                         TipoToken tt = palabrasReservadas.get(lexema);

//                         if (tt == null) {
//                             Token t = new Token(TipoToken.IDENTIFIER, lexema);
//                             tokens.add(t);
//                         } else {
//                             Token t = new Token(tt, lexema);
//                             tokens.add(t);
//                         }

//                         estado = 0;
//                         lexema = "";
//                         i--;

//                     }
//                     break;

//                 case 15: // Diagrama 3
//                     if (Character.isDigit(c)) {
//                         estado = 15;
//                         lexema += c;
//                     } else if (c == '.') {
//                         estado = 16;
//                         lexema += c;

//                     } else if (c == 'E') {
//                         estado = 18;
//                         lexema += c;

//                     } else {
//                         Token t = new Token(TipoToken.NUMBER, lexema, Integer.valueOf(lexema));
//                         tokens.add(t);

//                         estado = 0;
//                         lexema = "";
//                         i--;
//                     }
//                     break;

//                 case 16: // Diagrama 3
//                     if (Character.isDigit(c)) {
//                         estado = 17;
//                         lexema += c;
//                     }
//                     break;

//                 case 17: // Diagrama 3
//                     if (Character.isDigit(c)) {
//                         estado = 17;
//                         lexema += c;

//                     } else if (c == 'E') {
//                         estado = 18;
//                         lexema += c;

//                     } else {
//                         Token t = new Token(TipoToken.NUMBER, lexema, Double.valueOf(lexema));
//                         tokens.add(t);

//                         estado = 0;
//                         lexema = "";
//                         i--;
//                     }
//                     break;

//                 case 18: // Diagrama 3
//                     if (c == '+' || c == '-') {

//                         estado = 19;
//                         lexema += c;
                        
//                     } else if (Character.isDigit(c)) {
//                         estado = 20;
//                         lexema += c;
//                     }
//                     break;

//                 case 19: // Diagrama 3
//                     if (Character.isDigit(c)) {
//                         estado = 20;
//                         lexema += c;
//                     }
//                     break;

//                 case 20: // Diagrama 3
//                     if (Character.isDigit(c)) {
//                         estado = 20;
//                         lexema += c;
//                     } else {
//                         Token t = new Token(TipoToken.NUMBER, lexema, Double.valueOf(lexema));
//                         tokens.add(t);

//                         estado = 0;
//                         lexema = "";
//                         i--;
//                     }
//                     break;

//                  case 24: // Estado para construir una cadena de texto
//                     if (c == '"') {
//                         lexema += c;
//                         Token t = new Token(TipoToken.STRING, lexema, lexema.replace('"', ' ').trim());
//                         tokens.add(t);
//                         estado = 0;
//                         lexema = "";
//                     } else if (Character.isJavaIdentifierPart(c) || c == ' ') {
//                         estado = 24;
//                         lexema += c;
//                     } 
//                     break;

//                 case 26: // Diagrama 5
//                     if (c == '*') {
//                         estado = 27;
//                         lexema += c;
//                     } else if (c == '/') {
//                         estado = 30;
//                         lexema += c;
//                     } else {
//                         Token t = new Token(TipoToken.SLASH, lexema);
//                         tokens.add(t);
//                         // estado32 Slash
//                         estado = 0;
//                         lexema = "";
//                         i--;
//                     }
//                     break;

//                 case 27: // Diagrama 5
//                     if (c == '*') {
//                         estado = 28;
//                         lexema += c;
//                     } else{
//                         estado = 27;
//                         lexema += c;
//                     }
//                     break;

//                 case 28: //Diagrama 5
//                     if ( c == '*'){
//                         estado = 28;
//                         lexema += c;
//                     } else if ( c == '/') {
//                         estado = 0;
//                         lexema ="";
//                     } else if(Character.isJavaIdentifierPart(c)){
//                         estado = 27;
//                         lexema +=c;
//                     } else {
//                         System.out.println("Error");
//                     }
//                     break;
                    
//                 case 30:
//                     if (c == '\n'){
//                         estado = 0;
//                         lexema = "";
//                     }else{ 
//                         estado = 30;
//                         lexema +=c;
//                     }
//                         break;
//             }
//         } 
//         if (estado == 24) {
//             System.out.println("Error: Cadena de texto no cerrada correctamente");
//             System.exit(1);
//     }

//         return tokens;
//     }
// }


//Bueno

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MiScanner {
    private int i;
    private boolean numero;
    private String lexema;
    private int linea=1;
    private boolean error;
    private static final Map<String, TipoToken> palabrasReservadas;
    
    static {
        palabrasReservadas = new HashMap<>();
        palabrasReservadas.put("and",    TipoToken.AND);
        palabrasReservadas.put("else",   TipoToken.ELSE);
        palabrasReservadas.put("false",  TipoToken.FALSE);
        palabrasReservadas.put("for",    TipoToken.FOR);
        palabrasReservadas.put("fun",    TipoToken.FUN);
        palabrasReservadas.put("if",     TipoToken.IF);
        palabrasReservadas.put("null",   TipoToken.NULL);
        palabrasReservadas.put("or",     TipoToken.OR);
        palabrasReservadas.put("print",  TipoToken.PRINT);
        palabrasReservadas.put("return", TipoToken.RETURN);
        palabrasReservadas.put("true",   TipoToken.TRUE);
        palabrasReservadas.put("var",    TipoToken.VAR);
        palabrasReservadas.put("while",    TipoToken.WHILE);
        palabrasReservadas.put("+",  TipoToken.PLUS);
        palabrasReservadas.put("-",  TipoToken.MINUS);
        palabrasReservadas.put("*",  TipoToken.STAR);
        palabrasReservadas.put("/",  TipoToken.SLASH);
        palabrasReservadas.put("(",  TipoToken.LEFT_PAREN);
        palabrasReservadas.put(")",  TipoToken.RIGHT_PAREN);
        palabrasReservadas.put("{",  TipoToken.LEFT_BRACE);
        palabrasReservadas.put("}",  TipoToken.RIGHT_BRACE);
        palabrasReservadas.put(".",  TipoToken.DOT);
        palabrasReservadas.put(",",  TipoToken.COMMA);
        palabrasReservadas.put(";",  TipoToken.SEMICOLON);
        //palabrasReservadas.put("\n",  TipoToken.CR);
        //palabrasReservadas.put("=",  TipoToken.GT);//para cuando se usa get
    }

    private final String source;

    private final List<Token> tokens = new ArrayList<>();
    
    public MiScanner(String source){
        this.source = source+" ";
        //????
    }

    public List<Token> scan() throws Exception {
        lexema = "";
        int estado = 0;
        char c;
        Token t;
        //System.out.println("longitud : "+source.length()+"\n");
        int aux =0;
        
        for(i=0; i<source.length(); i++){
            c = source.charAt(i);
            if(c=='\n')
                linea++;
            //System.out.println(c);
            switch (estado){
                case 0:
                    if(Character.isLetter(c)){
                        estado = 9;
                        lexema += c;
                    }
                    else if(Character.isDigit(c)){
                        numero=funcionTransicionNumeros();//boleano
                        estado = 11;
                        //lexema += c;

                        /*while(Character.isDigit(c)){
                            lexema += c;
                            i++;
                            c = source.charAt(i);
                        }
                        Token t = new Token(TipoToken.NUMBER, lexema);
                        lexema = "";
                        estado = 0;
                        tokens.add(t);
                        */
                    }
                    else if(c=='!'){
                        lexema+=c;
                        estado = 3;
                    }
                    else if (c=='<'){
                        estado = 1;
                        lexema += c;
                    }
                    else if(c=='='){
                        estado = 5;
                        lexema += c;
                    }
                    else if(c=='>'){
                        estado = 6;
                        lexema += c;
                    }
                    else if(c=='"'){
                        //lexema += c;
                        estado =12;
                        
                    }
                    else if(c=='/'){
                        lexema += c;
                        estado = 13;
                        
                    }
                    //TOKENS DE UN CARACTER
                    else if(c=='\n'||c=='\r'||c==' '||c=='\t'){
                        //System.out.println("enter");      
                        break;
                    }
                    else{
                        lexema+=c;  
                        estado=15;
                    }

                    break;
                case 1:
                    if (c=='='){
                        lexema+=c;
                        estado = 2;
                    }
                    else{
                        t = new Token(TipoToken.LESS,lexema);
                        tokens.add(t);
                        lexema="";
                        estado=0;
                        i--;
                        
                    }
                        
                    
                    break;
                case 2:
                    t = new Token(TipoToken.LESS_EQUAL,lexema);
                    tokens.add(t);
                    lexema="";
                    estado=0;
                    i--;
                    break;
                case 3:
                    if(c=='='){
                        lexema+=c;
                        t = new Token(TipoToken.BANG_EQUAL,lexema);
                        tokens.add(t);
                        lexema="";
                        estado=0;
                    }
                    else{
                        t = new Token(TipoToken.BANG,lexema);
                        tokens.add(t);
                        lexema="";
                        estado=0;  
                        i--;
                    }
                    
                    break;
                /*case 4:
                    
                    break;*/
                case 5:
                    if(c=='='){
                        lexema+=c;
                        t= new Token(TipoToken.EQUAL_EQUAL,lexema);
                        tokens.add(t);
                        lexema="";
                        estado=0;
                    }
                    else{
                        t= new Token(TipoToken.EQUAL,lexema);
                        tokens.add(t);
                        lexema="";
                        estado=0;
                        i--;
                    }
                    
                    break;
                case 6:
                    if(c=='='){
                        lexema+=c;
                        estado=7;
                    }
                    else{
                        t= new Token(TipoToken.GREATER,lexema);
                        tokens.add(t);
                        lexema="";
                        estado=0;
                        i--;
                    }
                        
                    break;
                case 7:
                    t= new Token(TipoToken.GREATER_EQUAL,lexema);
                    tokens.add(t);
                    lexema="";
                    estado=0;
                    i--;
                    break;
                /*case 8:
                    
                    break;*/
                case 9:
                    if(Character.isLetter(c) || Character.isDigit(c)){
                        estado = 9;
                        lexema += c;
                    }
                    else{
                        // Vamos a crear el Token de identificador o palabra reservada
                        TipoToken tt = palabrasReservadas.get(lexema);

                        if(tt == null){
                            t = new Token(TipoToken.IDENTIFIER, lexema);
                            tokens.add(t);
                        }
                        else{
                            t = new Token(tt, lexema);
                            tokens.add(t);
                        }

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;
                case 11:
                    /*if(Character.isDigit(c)){
                        estado = 11;
                        lexema += c;
                    }
                    else if(c == '.'){
                        
                    }
                    else if(c == 'E'){

                    }
                    else{*/
                        if(numero){
                            t = new Token(TipoToken.NUMBER, lexema, Double.valueOf(lexema));//?????
                            
                            //\t
                            tokens.add(t);

                            estado = 0;
                            lexema = "";
                            i--;  
                        }
                        else{
                           estado = 0;
                            lexema = "";
                            i--;   
                            Interprete.error(linea, "Numero invalido");
                            error=true;
                            //System.exit(1);
                        }
                        
                    //}
                    break;
                case 12:
                    if(lexema.length()==0)
                    aux=linea;
                    if(c=='"'){
                        //lexema+=c;
                        t = new Token(TipoToken.STRING, lexema, String.valueOf(lexema));
                        tokens.add(t);
                        estado = 0;
                        lexema = "";
                    }
                    else if(c=='\n'||i==source.length()-1){
                        Interprete.error(aux,  "String invalido");//el error fue en la linea anterior
                        error=true;
                        lexema="";
                        estado=0;
                        //System.exit(65);//cadena
                        //linea++;//no se cuenta porque no hay un ciclo
                    }
                        
                    
                    else
                    lexema+=c;
                    //System.out.println(c);
                    break;
                case 13:
                    if(c=='*'){
                        for(;;){   
                            i++;
                            if(i==source.length())
                                return tokens;//comentar todo el codigo no es un error
                                //System.out.println("error en linea: "+linea);
                                //System.exit(64);//se acabo entrada
                            c = source.charAt(i);
                            //System.out.print(c);
                            if (c=='*'){
                                i++;
                                c = source.charAt(i);
                                if(c=='/'){
                                estado = 0;
                                lexema="";
                                break;
                                }
                                if(c=='\n')
                                    linea++;
                                
                            }
                            if(c=='\n')
                            linea++;
                            
                        }

                    }
                    else if(c=='/'){
                        for(;;){   
                            i++;
                            if(i==source.length()){
                                estado=0;
                                break;//coso: cuando solo hay una linea y es un comentario
                            }
                                
                                
                            c = source.charAt(i);
                            //System.out.print(c);
                            if (c=='\n'){
                                linea++;
                                estado = 0;
                                lexema="";
                                break;
                            }
                            
                            
                        }
                    }
                    else{
                        t = new Token(TipoToken.SLASH, lexema);
                        tokens.add(t);
                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;
                case 14:
                    if(c=='*'){
                        
                    }
                    break;
                case 15:
                    
                    /*if(lexema.equals(""))
                        break;*/
                    TipoToken tt=palabrasReservadas.get(lexema);
                    i--;
                    c=source.charAt(i);
                    if(tt==null){
                        //System.out.println(Character.getName(c));
                        
                        estado=0;
                        Interprete.error(linea, "Caracter invalido "+Character.getName(c)+" "+lexema);
                        error=true;
                        lexema="";
                        //System.exit(64);
                    }
                    else{
                        if(tt==TipoToken.CR){
                            t = new Token(tt,"");
                            tokens.add(t);
                            lexema="";
                            estado=0;
                            //i--;
                        }
                        else{                       
                            t = new Token(tt,lexema);
                            tokens.add(t);
                            lexema="";
                            estado=0;
                            //i--;
                        }
                    }
                    break;
            }
        }
        //System.out.println(i);
        t = new Token(TipoToken.EOF, null);
        tokens.add(t);
        return tokens;
    }
    public boolean funcionTransicionNumeros(){
        char c = source.charAt(i);
        int estado=0;
        while(i<source.length()){
            
            if(c=='\r'||c=='\n'||c==' '||(!Character.isDigit(c)&&c!='E'&&c!='e'&&c!='.'&&c!='+'&&c!='-')){
                if(estado==8||estado==2||estado==4||estado==7){
                    i--;
                    return true;
                }
                else{
                    //System.out.println("estado "+estado+" "+c);
                    i--;
                    return false;
                }
                    
            }else lexema += c;
            
            switch (estado){
                case 0:
                    if(c=='-'||c=='+'){
                        estado=1;
                    }
                    else if(Character.isDigit(c)&&c!='0'){
                        estado=2;
                    }
                    else if(c=='0'){
                        estado=8;
                    }
                    else estado=-1;
                    break;
                case 1:
                        if(Character.isDigit(c)&&c!='0'){
                            estado=2;
                        }
                        else estado=-1;
                    break;
                case 2:
                    if(Character.isDigit(c))
                        estado=2;
                    else if(c=='.')
                        estado=3;
                    else if(c=='e'||c=='E')
                        estado=5;
                    else
                        estado=-1;
                        
                    break;
                case 3:
                    if(Character.isDigit(c)){
                        estado=4;
                    }
                    else estado=-1;
                    break;
                case 4:
                    if(Character.isDigit(c)){
                        estado=4;
                    }
                    else if(c=='e'||c=='E'){
                        estado=5;
                    }
                    else estado=-1;
                    break;
                case 5:
                    if(c=='+'||c=='-')
                        estado=6;
                    else if(Character.isDigit(c))
                        estado=7;
                    else estado=-1;
                    break;
                case 6:
                    if(Character.isDigit(c))
                        estado=7;
                    else estado=-1;
                    break;
                case 7:
                    if(Character.isDigit(c))
                        estado=7;
                    else
                        estado=-1;
                    break;
                case 8:
                    if(c=='.'){
                        estado=3;
                    }
                    else estado=-1;
                    break;
            }
            //System.out.println(estado+" "+source.charAt(i));
            i++;
            c = source.charAt(i);
            
        }
        return false;
    }
    
    public boolean getError(){
        return error;
    }
    
}


// 3ra op

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;


// public class MiScanner {

//     private static final Map<String, TipoToken> palabrasReservadas;

//     static {
//         palabrasReservadas = new HashMap<>();
//         palabrasReservadas.put("and",    TipoToken.AND);
//         palabrasReservadas.put("else",   TipoToken.ELSE);
//         palabrasReservadas.put("false",  TipoToken.FALSE);
//         palabrasReservadas.put("for",    TipoToken.FOR);
//         palabrasReservadas.put("fun",    TipoToken.FUN);
//         palabrasReservadas.put("if",     TipoToken.IF);
//         palabrasReservadas.put("null",   TipoToken.NULL);
//         palabrasReservadas.put("or",     TipoToken.OR);
//         palabrasReservadas.put("print",  TipoToken.PRINT);
//         palabrasReservadas.put("return", TipoToken.RETURN);
//         palabrasReservadas.put("true",   TipoToken.TRUE);
//         palabrasReservadas.put("var",    TipoToken.VAR);
//         palabrasReservadas.put("while",  TipoToken.WHILE);
//     }

//     private final String source;

//     private final List<Token> tokens = new ArrayList<>();
    
//     public MiScanner(String source){
//         this.source = source + " ";
//     }

//     public List<Token> scan() throws Exception {
//         int estado = 0;
//         String lexema = "";
//         char c;

//         for(int i=0; i<source.length(); i++){
//             c = source.charAt(i);

//             switch (estado){
//                 case 0:
//                     if(Character.isLetter(c)){
//                         estado = 13;
//                         lexema += c;
//                     }
//                     else if(Character.isDigit(c)){
//                         estado = 15;
//                         lexema += c;

//                         /*while(Character.isDigit(c)){
//                             lexema += c;
//                             i++;
//                             c = source.charAt(i);
//                         }
//                         Token t = new Token(TipoToken.NUMBER, lexema, Integer.valueOf(lexema));
//                         lexema = "";
//                         estado = 0;
//                         tokens.add(t);
//                         */
 
//                     }else if(c == '>'){
//                         estado = 1;
//                         lexema += c; 
//                     }else if(c == '<'){
//                         estado = 4;
//                         lexema += c; 
//                     }else if(c == '='){
//                         estado = 7;
//                         lexema += c; 
//                     }else if(c == '!'){
//                         estado = 10;
//                         lexema += c; 
//                     }else if(c == '"'){
//                         estado = 24;
//                         lexema += c;
//                     }else if(c == '/'){
//                         estado = 26;
//                         lexema += c; 
//                     }else if(c == '+'){
//                         Token t = new Token(TipoToken.PLUS,lexema);
//                         tokens.add(t);
//                     }else if(c == '-'){
//                         Token t = new Token(TipoToken.MINUS,lexema);
//                         tokens.add(t);
//                     }else if(c == '.'){
//                         Token t = new Token(TipoToken.DOT,lexema);
//                         tokens.add(t);
//                     }else if(c == ','){
//                         Token t = new Token(TipoToken.COMMA,lexema);
//                         tokens.add(t);
//                     }else if(c == ')'){
//                         Token t = new Token(TipoToken.RIGHT_PAREN,lexema);
//                         tokens.add(t);
//                     }else if(c == '('){
//                         Token t = new Token(TipoToken.LEFT_PAREN,lexema);
//                         tokens.add(t);
//                     }else if(c == '}'){
//                         Token t = new Token(TipoToken.RIGHT_BRACE,lexema);
//                         tokens.add(t);
//                     }else if(c == '{'){
//                         Token t = new Token(TipoToken.LEFT_BRACE,lexema);
//                         tokens.add(t);
//                     }else if(c == '*'){
//                         Token t = new Token(TipoToken.STAR,lexema);
//                         tokens.add(t);
//                     }else if(c == ';'){
//                         Token t = new Token(TipoToken.SEMICOLON,lexema);
//                         tokens.add(t);
//                     }
//                     break;

//                 case 1:
//                     if(c == '='){
//                         Token t = new Token(TipoToken.GREATER_EQUAL, lexema);
//                         tokens.add(t);
//                     }else{
//                         Token t = new Token(TipoToken.GREATER, lexema);
//                         tokens.add(t);
//                         i--;
//                     }

//                     estado = 0;
//                     lexema = "";
                        
//                     break;
//                 case 4:
//                     if(c == '='){
//                         Token t = new Token(TipoToken.LESS_EQUAL, lexema);
//                         tokens.add(t);
//                     }else{
//                         Token t = new Token(TipoToken.LESS, lexema);
//                         tokens.add(t);
//                         i--;
//                     }
//                         estado = 0;
//                         lexema = "";
//                     break;
//                 case 7:
//                     if(c == '='){
//                         Token t = new Token(TipoToken.EQUAL_EQUAL, lexema);
//                         tokens.add(t);
//                     }else{
//                         Token t = new Token(TipoToken.EQUAL, lexema);
//                         tokens.add(t);
//                         i--;
//                     }

//                         estado = 0;
//                         lexema = "";
//                     break;
//                 case 10:
//                     if(c == '='){
//                         Token t = new Token(TipoToken.BANG_EQUAL, lexema);
//                         tokens.add(t);
//                     }else{
//                         Token t = new Token(TipoToken.BANG, lexema);
//                         tokens.add(t);
//                         i--;
//                     }

//                         estado = 0;
//                         lexema = "";
//                     break;


//                 case 13:
//                     if(Character.isLetterOrDigit(c)){
//                         estado = 13;
//                         lexema += c;
//                     }
//                     else{
//                     TipoToken tt = palabrasReservadas.get(lexema);

//                     if(tt == null){
//                         Token t = new Token(TipoToken.IDENTIFIER, lexema);
//                         tokens.add(t);
//                     }else{
//                         Token t = new Token(tt, lexema);
//                         tokens.add(t);
//                     }

//                         estado = 0;
//                         lexema = "";
//                         i--;
//                     }
//                     break;

//                 case 15:
//                     if(Character.isDigit(c)){
//                         estado = 15;
//                         lexema += c;
//                     }else if(c == '.'){
//                         estado = 16;
//                         lexema += c;
//                     }else if(c == 'E'){
//                         estado = 18;
//                         lexema += c;
//                     }else{
//                         Token t = new Token(TipoToken.NUMBER, lexema, Integer.valueOf(lexema));
//                         tokens.add(t);

//                         estado = 0;
//                         lexema = "";
//                         i--;
//                     }
//                     break;

//                 case 16:
//                     if(Character.isDigit(c)){
//                         estado = 17;
//                         lexema += c;
//                     }

//                     break;

//                     case 17:
//                     if(Character.isDigit(c)){
//                         estado = 17;
//                         lexema += c;
//                     }else if(c == 'E'){
//                         estado = 18;
//                         lexema += c;
//                     }else{
//                         Token t = new Token(TipoToken.NUMBER, lexema, Double.valueOf(lexema));
//                         tokens.add(t);

//                         estado = 0;
//                         lexema = "";
//                         i--;
//                     }

//                     break;

//                 case 18:
//                     if (c == '+' || c == '-') {
//                         //Token t = new Token(TipoToken.PLUS,lexema);
//                         //tokens.add(t);

//                         //Token t = new Token(TipoToken.MINUS,lexema);
//                         //tokens.add(t);
//                         lexema += c;
//                         estado = 19;
//                     }else if(Character.isDigit(c)){
//                         estado = 20;
//                         lexema += c;
//                     }
//                         break;
//                 case 19:
//                     if(Character.isDigit(c)){
//                         estado = 20;
//                         lexema += c;
//                     }
//                     break;

//                 case 20:
//                     if(Character.isDigit(c)){
//                         estado = 20;
//                         lexema += c;
//                     }else{
//                         TipoToken tt = palabrasReservadas.get(lexema);

//                         if(tt == null){
//                             Token t = new Token(TipoToken.NUMBER, lexema);
//                             tokens.add(t);
//                         }else{
//                             Token t = new Token(tt, lexema);
//                             tokens.add(t);
//                         }
//                         estado = 0;
//                         lexema = "";
//                         i--;

//                     }
//                     break;
//                 case 24: 
//                     if(Character.isJavaIdentifierPart(c)){
//                         estado = 24;   
//                         lexema += c;
//                     }else if(c == '"' ){
//                         lexema += c;
//                         estado = 0;
//                         //String valorToken = lexema.substring(1, lexema.length() - 1); // Elimina las comillas al principio y al final
//                         Token t = new Token(TipoToken.STRING, lexema, lexema.replace('\"', ' ').trim());
//                         tokens.add(t);
//                         lexema = "";
//                     }else{
//                         lexema += c;
//                     }
//                     break;
//                 case 26:
//                     if(c == '*'){
//                         estado = 27; 
//                         lexema += c; 
//                     }else if(c == '/'){
//                         estado = 30; 
//                         lexema += c;
//                     }else{
//                             Token t = new Token(TipoToken.SLASH, lexema);
//                             tokens.add(t);
//                             estado = 0;
//                             lexema = "";
//                             i--;        
//                     }
//                     break;
//                 case 27: 
//                     if(c == '*'){
//                         estado = 28;
//                         lexema += c;
//                     }else{
//                         estado = 27;
//                         lexema += c; 
//                     }
//                     break;
//                 case 28:
//                     if(c == '*'){
//                         estado = 28;
//                         lexema += c;
//                     }else if(c == '/'){
//                         estado = 0;
//                         lexema = "";
//                     }else{
//                         estado = 27; 
//                         lexema += c; 
//                     }
//                     break;
//                 case 30:
//                     if(c == '\n'){
//                         estado = 0;
//                         lexema = "";
//                     }else{
//                         estado = 30;
//                         lexema += c;
//                     }
                        
//                     break;
                
//             }
//         }
//         if(estado == 24 || estado == 27){
//             System.out.println("Error");
//         }
//         return tokens;
//     }
// }
