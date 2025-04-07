package dev.cobblesword;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final List<Token> tokens;
    private int current = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public List<AstNode> parse() {
        List<AstNode> program = new ArrayList<>();
        while (!isAtEnd()) {
            if (match(TokenType.FUNC)) {
                program.add(parseFunction());
            } else if (match(TokenType.IDENTIFIER) && peek().type == TokenType.ASSIGN) {
                // Not real var decl logic yet
                throw error(peek(), "Top-level assignment not supported yet.");
            } else {
                advance(); // skip
            }
        }
        return program;
    }

    private FunctionDecl parseFunction() {
        String name = consume(TokenType.IDENTIFIER, "Expected function name").lexeme;
        consume(TokenType.LPAREN, "Expected '('");
        List<String> params = new ArrayList<>();
        if (!check(TokenType.RPAREN)) {
            do {
                params.add(consume(TokenType.IDENTIFIER, "Expected parameter name").lexeme);
            } while (match(TokenType.COMMA));
        }
        consume(TokenType.RPAREN, "Expected ')'");
        Block body = parseBlock();
        return new FunctionDecl(name, params, body);
    }

    private Block parseBlock() {
        consume(TokenType.LBRACE, "Expected '{'");
        List<AstNode> statements = new ArrayList<>();
        while (!check(TokenType.RBRACE) && !isAtEnd()) {
            if (match(TokenType.RETURN)) {
                statements.add(parseReturn());
            } else {
                advance(); // skip
            }
        }
        consume(TokenType.RBRACE, "Expected '}'");
        return new Block(statements);
    }

    private ReturnStmt parseReturn() {
        AstNode value;
        if (check(TokenType.STRING)) {
            value = new StringLiteral(advance().lexeme);
        } else if (check(TokenType.IDENTIFIER)) {
            value = new Identifier(advance().lexeme);
        } else {
            throw error(peek(), "Expected expression after return");
        }
        return new ReturnStmt(value);
    }

    // Helpers

    private boolean match(TokenType type) {
        if (check(type)) {
            advance(); return true;
        }
        return false;
    }

    private boolean check(TokenType type) {
        if (isAtEnd()) return false;
        return peek().type == type;
    }

    private Token advance() {
        if (!isAtEnd()) current++;
        return previous();
    }

    private Token consume(TokenType type, String message) {
        if (check(type)) return advance();
        throw error(peek(), message);
    }

    private Token peek() { return tokens.get(current); }
    private Token previous() { return tokens.get(current - 1); }
    private boolean isAtEnd() { return peek().type == TokenType.EOF; }

    private RuntimeException error(Token token, String message) {
        return new RuntimeException("Parse error at line " + token.line + ": " + message);
    }
}
