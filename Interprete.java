import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Interprete {

    static boolean existenErrores = false;

    public static void main(String[] args) throws IOException {
        if(args.length > 1) {
            System.out.println("Uso correcto: interprete [archivo.txt]");

            // Convención defininida en el archivo "system.h" de UNIX
            System.exit(64);
        } else if(args.length == 1){
            ejecutarArchivo(args[0]);
        } else{
            ejecutarPrompt();
            
        }
    }

    private static void ejecutarArchivo(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        
        //System.out.println(new String(bytes, Charset.defaultCharset()));
        
        ejecutar(new String(bytes, Charset.defaultCharset()));
        // Se indica que existe un error
        if(existenErrores) System.exit(65);
    }

    private static void ejecutarPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for(;;){
            System.out.print(">>> ");
            String linea = reader.readLine();
            if(linea == null) break; // Presionar Ctrl + D
            ejecutar(linea);
            existenErrores = false;
        }
    }
    //source es lo que se ingreso
    private static void ejecutar(String source) {
        try{
            MiScanner scanner = new MiScanner(source);
            List<Token> tokens = scanner.scan();
            //if(scanner.getError())//si hay errores no imprime
            //System.out.println("\n");
            for(Token token : tokens){
                System.out.println(token);//hace el toString automaticamente
            }
            Parser parser = new ASDR(tokens);
        parser.parse();
            //System.out.println(tokens);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

    /*
    El método error se puede usar desde las distintas clases
    para reportar los errores:
    Interprete.error(....);
     */
    
    static void error(int linea, String mensaje){
        reportar(linea, "", mensaje);
        
    }

    private static void reportar(int linea, String posicion, String mensaje){
        System.out.println(    
                
                "[linea  " + linea + " ] Error " + posicion + ": " + mensaje
        );
        existenErrores = true;
    }

}
