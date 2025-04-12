# ADR-005: Static vs Dynamic vs Gradual Typing

## Status
Accepted

## Context
Choosing a type system is a foundational decision that impacts how programs are written, validated, and reasoned about. The three main options:

* Static typing (e.g., Kotlin, OCaml)
  * ✅ Type errors are caught early at compile time
  * ✅ Enables better tooling (e.g. autocomplete, refactoring)
  * ✅ Easier to reason about correctness
  * ❌ Requires more design effort up front (type system, checker, error handling)

* Dynamic typing (e.g., Python, JavaScript)
  * ✅ Easier to prototype
  * ❌ Type errors at runtime
  * ❌ Poorer editor support, refactorability

* Gradual typing (e.g., TypeScript, MyPy)
  * ✅ Mix of both: type hints where helpful
  * ❌ Adds complexity (type checker + runtime)
  * ❌ Can lead to inconsistent developer expectations

## Decision
I will implement **static typing** from the start.
Type checking will happen before execution. The interpreter will only evaluate type-safe programs.
I’ll begin with basic types: `int`, `bool`, `string`, and gradually expand to compound types (e.g., functions, lists, records).

## Consequences
Type checking logic must be implemented up front—this slows initial iteration.
The language will be safer, more predictable, and easier to extend with tooling (e.g., LSP integration).
REPL may need to support type inference or type declarations for user convenience.
Some runtime errors will be impossible by design (e.g. adding an Int and a Bool).

## Alternatives Considered (Optional)
* **Dynamic typing**: Easier to build, but sacrifices safety and tooling.
* **Gradual typing**: Would be ideal for a larger language ecosystem, but too complex for the initial scope.