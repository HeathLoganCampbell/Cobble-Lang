package dev.cobblesword;

import java.util.List;

sealed public interface AstNode permits FunctionDecl, ReturnStmt, VarDecl, Block, Identifier, StringLiteral {
}

