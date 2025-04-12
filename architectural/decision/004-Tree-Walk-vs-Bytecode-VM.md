# ADR-004: Tree-Walk vs Bytecode VM

## Status
Accepted

## Context
Having chosen to build an interpreter (ADR-003), the next decision is what kind of interpreter to use:

* **Tree-walk interpreter**: Traverse the abstract syntax tree (AST) directly and execute nodes
  * ✅ Simple to implement
  * ✅ Directly reflects the structure of the source
  * ❌ Slower (especially for nested expressions or repeated work)

* **Bytecode Virtual Machine (VM)**: Compile source code to a lower-level instruction set, then execute it in a VM
  * ✅ Faster, especially for loops and reuse
  * ✅ Easier to optimize later (constant folding, inline caching)
  * ❌ More work upfront: need an IR, instruction set, stack model

The language is still in its early stages and not intended for performance-sensitive use. A tree-walk interpreter provides the quickest path to running real programs and reasoning about the semantics of the language.

## Decision
I will start with a **tree-walk interpreter** to get the language up and running.
If performance becomes a bottleneck—or if I want to learn more about VM internals—I may introduce a bytecode layer later.

## Consequences
Simpler control flow, easier to debug, faster to build initially.
Performance will likely be poor, especially for tight loops or recursive calls.
Future migration to bytecode would require rewriting the evaluator, but the parser and AST can likely remain unchanged.

## Alternatives Considered (Optional)
**Bytecode VM**: Too much upfront complexity for the current goal. (Rejected)
**Transpilation to another VM (e.g., Lua, Python)**: Again, too detached from core learning goals. (Rejected)