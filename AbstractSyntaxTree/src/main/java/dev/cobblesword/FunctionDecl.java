package dev.cobblesword;

import java.util.List;

public record FunctionDecl(String name, List<String> parameters, Block body) implements AstNode {}
