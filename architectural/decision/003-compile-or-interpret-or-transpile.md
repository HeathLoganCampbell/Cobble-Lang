# ADR-003: Compile Or Interpret Or Transpile

## Status
Accepted

## Context
There are three main approaches to running a custom programming language:

* Compile => Convert to machine code (e.g. C++, Rust)
  * ✅ High performance
  * ❌ High complexity, harder iteration

* Interpret → Parse and execute directly (e.g. Python, JavaScript)
  * ✅ Easier to build and evolve
  * ❌ Slower runtime performance

* Transpile → Convert to another language (e.g. TypeScript → JS, Elm → JS)
  * ✅ Leverages existing tooling, easier to ship
  * ❌ Less educational, not “pure” enough for this exercise

My goals for this project are learning, exploration, and fun—not production-readiness or raw performance. Interpretation strikes a good balance between challenge and feasibility.

## Decision
I will implement the language as a tree-walk interpreter (initially).
This gives me space to focus on parsing, AST structure, and evaluation logic.
Transpiration is ruled out for now because it would shortcut the learning experience too much.

## Consequences
* Unlikely to be performant enough for production use.
* Will take longer than transpiling, but will deepen understanding of language design.
* May eventually switch to transpiling or compiling if project goals change.