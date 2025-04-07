package dev.cobblesword;

public class Token {
    public TokenType type;
    public String lexeme;
    public int line, column;

    Token(TokenType type, String lexeme, int line, int column) {
        this.type = type;
        this.lexeme = lexeme;
        this.line = line;
        this.column = column;
    }

    public String toString() {
        return type + "('" + lexeme + "') @ " + line + ":" + column;
    }
}