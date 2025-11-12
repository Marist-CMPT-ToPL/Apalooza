//> Functions lox-function
import java.util.List;

class AppaFunction implements AppaCallable {
    private final Stmt.Function declaration;
    private final Expr.ArrowFunction arrowFunction;
    //> closure-field
    private final Environment closure;

    //< closure-field
/* Functions lox-function < Functions closure-constructor
  LoxFunction(Stmt.Function declaration) {
*/
/* Functions closure-constructor < Classes is-initializer-field
  LoxFunction(Stmt.Function declaration, Environment closure) {
*/
//> Classes is-initializer-field
    private final boolean isInitializer;

    // Constructor for regular functions
    AppaFunction(Stmt.Function declaration, Environment closure,
                 boolean isInitializer) {
        this.isInitializer = isInitializer;
//< Classes is-initializer-field
//> closure-constructor
        this.closure = closure;
//< closure-constructor
        this.declaration = declaration;
        this.arrowFunction = null;
    }

    // Constructor for arrow functions
    AppaFunction(Expr.ArrowFunction arrowFunction, Environment closure) {
        this.isInitializer = false;
        this.closure = closure;
        this.declaration = null;
        this.arrowFunction = arrowFunction;
    }
    //> Classes bind-instance
    AppaFunction bind(AppaInstance instance) {
        Environment environment = new Environment(closure);
        environment.define("this", instance);
/* Classes bind-instance < Classes lox-function-bind-with-initializer
    return new LoxFunction(declaration, environment);
*/
//> lox-function-bind-with-initializer
        return new AppaFunction(declaration, environment,
                isInitializer);
//< lox-function-bind-with-initializer
    }
    //< Classes bind-instance
//> function-to-string
    @Override
    public String toString() {
        if (arrowFunction != null) {
            return "<arrow fn>";
        }
        return "<fn " + declaration.name.lexeme + ">";
    }
    //< function-to-string
//> function-arity
    @Override
    public int arity() {
        if (arrowFunction != null) {
            return arrowFunction.params.size();
        }
        return declaration.params.size();
    }
    //< function-arity
//> function-call
    @Override
    public Object call(Interpreter interpreter,
                       List<Object> arguments) {
        // Handle arrow functions
        if (arrowFunction != null) {
            Environment environment = new Environment(closure);

            // Bind parameters
            for (int i = 0; i < arrowFunction.params.size(); i++) {
                environment.define(arrowFunction.params.get(i).lexeme,
                        arguments.get(i));
            }

            // Execute arrow function
            if (arrowFunction.isExpression) {
                // Single expression arrow function: (x) => x * 2
                Environment previous = interpreter.environment;
                try {
                    interpreter.environment = environment;
                    return interpreter.evaluate(arrowFunction.expressionBody);
                } finally {
                    interpreter.environment = previous;
                }
            } else {
                // Block arrow function: (x) => { return x * 2; }
                try {
                    interpreter.executeBlock(arrowFunction.body, environment);
                } catch (Return returnValue) {
                    return returnValue.value;
                }
                return null;
            }
        }

        // Handle regular functions
/* Functions function-call < Functions call-closure
    Environment environment = new Environment(interpreter.globals);
*/
//> call-closure
        Environment environment = new Environment(closure);
//< call-closure
        for (int i = 0; i < declaration.params.size(); i++) {
            environment.define(declaration.params.get(i).lexeme,
                    arguments.get(i));
        }

/* Functions function-call < Functions catch-return
    interpreter.executeBlock(declaration.body, environment);
*/
//> catch-return
        try {
            interpreter.executeBlock(declaration.body, environment);
        } catch (Return returnValue) {
//> Classes early-return-this
            if (isInitializer) return closure.getAt(0, "this");

//< Classes early-return-this
            return returnValue.value;
        }
//< catch-return
//> Classes return-this

        if (isInitializer) return closure.getAt(0, "this");
//< Classes return-this
        return null;
    }
//< function-call
}