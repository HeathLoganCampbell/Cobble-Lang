package dev.cobblesword;

import java.util.List;

public record Block(List<AstNode> statements) implements AstNode {}
