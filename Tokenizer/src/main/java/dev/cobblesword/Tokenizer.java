package dev.cobblesword;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tokenizer {
    private final String input;
    private int pos = 0, line = 1, col = 1;
    private final List<Token> tokens = new ArrayList<>();

    private static final Map<String, TokenType> keywords = Map.of(
        "import", TokenType.IMPORT,
        "func", TokenType.FUNC,
        "module", TokenType.MODULE,
        "type", TokenType.TYPE,
        "value", TokenType.VALUE,
        "handler", TokenType.HANDLER,
        "expose", TokenType.EXPOSE,
        "return", TokenType.RETURN
    );

    public Tokenizer(String input) {
        this.input = input;
    }

    public List<Token> tokenize() {
        while (!isAtEnd()) {
            skipWhitespace();
            int startCol = col;
            char c = advance();
            switch (c) {
                case '{': add(TokenType.LBRACE, "{"); break;
                case '}': add(TokenType.RBRACE, "}"); break;
                case '(': add(TokenType.LPAREN, "("); break;
                case ')': add(TokenType.RPAREN, ")"); break;
                case ':': add(TokenType.COLON, ":"); break;
                case ';': add(TokenType.SEMICOLON, ";"); break;
                case ',': add(TokenType.COMMA, ","); break;
                case '.': add(TokenType.DOT, "."); break;
                case '-':
                    if (match('>')) add(TokenType.ARROW, "->");
                    else throw error("Unexpected character: -");
                    break;
                case '=': add(TokenType.ASSIGN, "="); break;
                case '"': string(); break;
                default:
                    if (Character.isDigit(c)) {
                        number(c);
                    } else if (Character.isLetter(c) || c == '_') {
                        identifier(c);
                    } else {
                        throw error("Unexpected character: " + c);
                    }
            }
        }
        tokens.add(new Token(TokenType.EOF, "", line, col));
        return tokens;
    }

    private void skipWhitespace() {
        while (!isAtEnd()) {
            char c = peek();
            if (c == ' ' || c == '\r' || c == '\t') {
                advance();
            } else if (c == '\n') {
                advance(); line++; col = 1;
            } else break;
        }
    }

    private void identifier(char first) {
        StringBuilder sb = new StringBuilder();
        sb.append(first);
        while (!isAtEnd() && (Character.isLetterOrDigit(peek()) || peek() == '_')) {
            sb.append(advance());
        }
        String text = sb.toString();
        TokenType type = keywords.getOrDefault(text, TokenType.IDENTIFIER);
        add(type, text);
    }

    private void number(char first) {
        StringBuilder sb = new StringBuilder();
        sb.append(first);
        while (!isAtEnd() && Character.isDigit(peek())) {
            sb.append(advance());
        }
        add(TokenType.NUMBER, sb.toString());
    }

    private void string() {
        StringBuilder sb = new StringBuilder();
        while (!isAtEnd() && peek() != '"') {
            sb.append(advance());
        }
        if (isAtEnd()) throw error("Unterminated string literal");
        advance(); // closing quote
        add(TokenType.STRING, sb.toString());
    }

    private char advance() {
        col++;
        return input.charAt(pos++);
    }

    private boolean match(char expected) {
        if (isAtEnd() || input.charAt(pos) != expected) return false;
        pos++; col++;
        return true;
    }

    private char peek() {
        return input.charAt(pos);
    }

    private boolean isAtEnd() {
        return pos >= input.length() - 1;
    }

    private void add(TokenType type, String text) {
        tokens.add(new Token(type, text, line, col));
    }

    private RuntimeException error(String message) {
        return new RuntimeException("Error at " + line + ":" + col + " - " + message);
    }
}
