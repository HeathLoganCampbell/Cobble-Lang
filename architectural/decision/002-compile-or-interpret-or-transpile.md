# ADR-NNN: [Title of the decision]

## Status
[ Accepted ]

## Context
We have roughly 3 options we can go with
* Compile: Compiling down to machine code (eg C++).
* Interpret: Parse and Interpret (eg python, javascript).
* Transpile: Convert our language to another language (eg typescript).

Compile is high effort but really efficient
Interpret is not as efficient but is a lot simpler
Transpile leverages existing work very effectively & could probably be put in production, but is too easy. 

## Decision
We will go Interpret, because it offers enough challenge to be fun.
even tho Transpile would be quicker to prototype, and more production ready.

## Consequences
* We probably cannot use it in production ever.
* It'll likely take a bit long