import dev.cobblesword.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class InterpreterTest {

    @Test
    public void testRunFunctionReturnsString() {
        String source = """
            func run() {
                return "Hello world"
            }
        """;

        Tokenizer tokenizer = new Tokenizer(source);
        List<Token> tokens = tokenizer.tokenize();

        Parser parser = new Parser(tokens);
        List<AstNode> ast = parser.parse();

        Interpreter interpreter = new Interpreter();
        interpreter.execute(ast);
        Object result = interpreter.callFunction("run", List.of());

        assertEquals("Hello world", result);
    }
}
