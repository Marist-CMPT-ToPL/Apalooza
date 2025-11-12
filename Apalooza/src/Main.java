import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    private static final Interpreter interpreter = new Interpreter();

    //< Evaluating Expressions interpreter-instance
//> had-error
    static boolean hadError = false;
    //< had-error
//> Evaluating Expressions had-runtime-error-field
    static boolean hadRuntimeError = false;

    public static void main(String[] args) {
        try{
            if (args.length == 1) {
                runFile(args[0]);
            } else {
                runFile("/Users/harris/Desktop/apalooza/Apalooza/src/samples/list_and_arrow_test.txt");
            }
        }catch(Exception e){
            System.out.println("Error Running File: " + e);
        }
    }

    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
//> exit-code

        // Indicate an error in the exit code.
        if (hadError) System.exit(65);
//< exit-code
//> Evaluating Expressions check-runtime-error
        if (hadRuntimeError) System.exit(70);
//< Evaluating Expressions check-runtime-error
    }

    private static void run(String source) {
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        /*for (Token token : tokens) {
            System.out.println(token);
        }*/

        Parser parser = new Parser(tokens);

        List<Stmt> statements = parser.parse();

        //for(Stmt statement : statements){
        //    System.out.println(statement);
        //}

        if (hadError) return;

//< Parsing Expressions print-ast
//> Resolving and Binding create-resolver
        Resolver resolver = new Resolver(interpreter);
        resolver.resolve(statements);

        if (hadError) return;

        interpreter.interpret(statements);

        System.out.println();
    }
}