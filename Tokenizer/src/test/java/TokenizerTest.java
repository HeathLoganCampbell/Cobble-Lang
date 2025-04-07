import dev.cobblesword.Token;
import dev.cobblesword.TokenType;
import dev.cobblesword.Tokenizer;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TokenizerTest {

    @Test
    public void testBasicTokenization() {
        String input = """
            import Console
        
            func greet() {
                var name = "Heath"
                return name
            }
        """;

        Tokenizer tokenizer = new Tokenizer(input);
        List<Token> tokens = tokenizer.tokenize();

        assertEquals(TokenType.IMPORT, tokens.get(0).type);
        assertEquals("import", tokens.get(0).lexeme);

        assertEquals(TokenType.IDENTIFIER, tokens.get(1).type);
        assertEquals("Console", tokens.get(1).lexeme);

        assertTrue(tokens.stream().anyMatch(t -> t.type == TokenType.FUNC));
        assertTrue(tokens.stream().anyMatch(t -> t.lexeme.equals("name")));
        assertTrue(tokens.stream().anyMatch(t -> t.type == TokenType.STRING && t.lexeme.equals("Heath")));
        assertTrue(tokens.stream().anyMatch(t -> t.type == TokenType.RETURN));
        assertEquals(TokenType.EOF, tokens.getLast().type);
    }

    @Test
    public void testArrowAndColon() {
        String input = """
                func getRole(string username) -> string
                {
                    return username
                }
                """;
        Tokenizer tokenizer = new Tokenizer(input);
        List<Token> tokens = tokenizer.tokenize();

        Assert.assertEquals(TokenType.FUNC, tokens.get(0).type);
        Assert.assertEquals(TokenType.IDENTIFIER, tokens.get(1).type);
        Assert.assertEquals("getRole", tokens.get(1).lexeme);
        assertEquals(TokenType.LPAREN, tokens.get(2).type);
        assertEquals(TokenType.IDENTIFIER, tokens.get(3).type);
        assertEquals("string", tokens.get(3).lexeme);
        assertEquals(TokenType.IDENTIFIER, tokens.get(4).type);
        assertEquals("username", tokens.get(4).lexeme);
        assertEquals(TokenType.RPAREN, tokens.get(5).type);
        assertEquals(TokenType.ARROW, tokens.get(6).type);
        assertEquals(TokenType.IDENTIFIER, tokens.get(7).type);
        assertEquals(TokenType.LBRACE, tokens.get(8).type);
        assertEquals(TokenType.RETURN, tokens.get(9).type);
        assertEquals(TokenType.IDENTIFIER, tokens.get(10).type);
        assertEquals(TokenType.RBRACE, tokens.get(11).type);
    }
}
