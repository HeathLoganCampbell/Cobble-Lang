package dev.cobblesword;

public record VarDecl(String name, AstNode value) implements AstNode {}
