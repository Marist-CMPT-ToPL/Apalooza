//> Functions callable
import java.util.List;

interface AppaCallable {
    //> callable-arity
    int arity();
    //< callable-arity
    Object call(Interpreter interpreter, List<Object> arguments);
}