# ADR-002: Why make a programming language

## Status
Accepted

## Context
After reading Domain-Driven Design, I noticed a mismatch between the way I want to model domains and the constraints of general-purpose languages. I’ve also been inspired by features from a range of languages (e.g. OCaml, Kotlin, F#) and wanted to explore what it would look like to combine them.
This project serves multiple goals:
* Practice TDD in a creative, low-pressure context
* Practice DDD by modeling the language itself as a domain
* Experiment with language and tool design, purely for learning and fun
* No expectation of production use

## Decision
I will design and build a small programming language as a solo project.
The focus will be on clarity, domain modeling, and iteration rather than performance or completeness

## Consequences
I may need to build supporting tools:
* A language processor (tokenizer → parser → interpreter)
* Minimal syntax highlighting (e.g., for VS Code)
* Basic REPL or CLI
* I’ll need to make trade-offs between expressiveness and simplicity
* Most value will come from the process, not the outcome

## Alternatives Considered (Optional)
- Use another language - rejected because it's not as fun
  - OCaml
  - Kotlin
  - C# with framework
    - EventFlow
    - DDD Toolkit