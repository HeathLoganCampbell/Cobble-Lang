package dev.cobblesword;

import java.util.*;

public class Interpreter {
    private final Map<String, FunctionDecl> functions = new HashMap<>();
    private final Map<String, Object> variables = new HashMap<>();

    public void execute(List<AstNode> program) {
        for (AstNode node : program) {
            if (node instanceof FunctionDecl fn) {
                functions.put(fn.name(), fn);
            }
        }

        // call run()
        callFunction("run", List.of());
    }

    public Object callFunction(String name, List<Object> args) {
        FunctionDecl fn = functions.get(name);
        if (fn == null) throw new RuntimeException("Function not found: " + name);

        // push params to scope
        for (int i = 0; i < fn.parameters().size(); i++) {
            variables.put(fn.parameters().get(i), args.get(i));
        }

        return executeBlock(fn.body());
    }

    private Object executeBlock(Block block) {
        for (AstNode stmt : block.statements()) {
            if (stmt instanceof ReturnStmt ret) {
                return evaluate(ret.expression());
            } else if (stmt instanceof VarDecl decl) {
                variables.put(decl.name(), evaluate(decl.value()));
            } else {
                throw new RuntimeException("Unhandled statement: " + stmt);
            }
        }
        return null;
    }

    private Object evaluate(AstNode expr) {
        if (expr instanceof Identifier id) {
            if (!variables.containsKey(id.name()))
                throw new RuntimeException("Undefined variable: " + id.name());
            return variables.get(id.name());
        } else if (expr instanceof StringLiteral str) {
            return str.value();
        }
        throw new RuntimeException("Unknown expression: " + expr);
    }
}
