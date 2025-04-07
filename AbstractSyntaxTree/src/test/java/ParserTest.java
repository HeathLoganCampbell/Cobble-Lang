import dev.cobblesword.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParserTest {

    @Test
    public void testSimpleFunctionParsing() {
        String source = """
            func greet(name) {
                return name
            }
        """;

        Tokenizer tokenizer = new Tokenizer(source);
        List<Token> tokens = tokenizer.tokenize();

        Parser parser = new Parser(tokens);
        List<AstNode> ast = parser.parse();

        assertEquals(1, ast.size());
        assertTrue(ast.get(0) instanceof FunctionDecl);

        FunctionDecl fn = (FunctionDecl) ast.get(0);
        assertEquals("greet", fn.name());
        assertEquals(List.of("name"), fn.parameters());

        Block body = fn.body();
        assertEquals(1, body.statements().size());
        assertTrue(body.statements().get(0) instanceof ReturnStmt);

        ReturnStmt ret = (ReturnStmt) body.statements().get(0);
        assertTrue(ret.expression() instanceof Identifier);
        assertEquals("name", ((Identifier) ret.expression()).name());
    }

    @Test
    public void testSimpleFunctionParsingVar() {
        String source = """
            func greet(name)
            {
                var abc = "bacon"
                return abc
            }
        """;

        Tokenizer tokenizer = new Tokenizer(source);
        List<Token> tokens = tokenizer.tokenize();

        Parser parser = new Parser(tokens);
        List<AstNode> ast = parser.parse();

        assertEquals(1, ast.size());
        assertTrue(ast.get(0) instanceof FunctionDecl);

        FunctionDecl fn = (FunctionDecl) ast.get(0);
        assertEquals("greet", fn.name());
        assertEquals(List.of("name"), fn.parameters());

        Block body = fn.body();
        assertEquals(2, body.statements().size());
        assertTrue(body.statements().get(0) instanceof VarDecl);
        assertTrue(body.statements().get(1) instanceof ReturnStmt);

        ReturnStmt ret = (ReturnStmt) body.statements().get(1);
        assertTrue(ret.expression() instanceof Identifier);
        assertEquals("abc", ((Identifier) ret.expression()).name());
    }
}
