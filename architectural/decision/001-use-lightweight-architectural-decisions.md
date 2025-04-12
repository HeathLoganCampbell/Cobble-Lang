# ADR-001: use lightweight architectural decisions

## Status
Accepted

## Context
I’m the sole developer on this project, but I want to create a habit of 
documenting architecture decisions in a way that’s fast, useful, and doesn't 
slow me down. In the past, I’ve forgotten why I made certain decisions or had 
to re-evaluate the same options multiple times. Having lightweight records will help 
me reflect and avoid that.

## Decision

* I will adopt the Architecture Decision Record (ADR) pattern for this project.
* ADRs will be stored as individual markdown files in `architectural/decision`
* I will write one whenever a decision has long-term impact, is difficult to reverse, or requires trade-offs.
* Each ADR will follow a simple, structured format focused on context, decision, and consequences. This can be found at `architectural/decision/000-template.md` 
* I’ll keep them short—no more than 1 page.

## Consequences
* Slight increase in up-front effort when making decisions.
* Helps me track why I chose one path over another.
* Supports future refactoring or onboarding by providing context.
* Encourages deliberate thinking even when working solo.

## Alternatives Considered (Optional)
* **No ADRs:** Keep everything in my head or in code comments. Rejected — it’s easy to forget rationale over time.
* **Formal documentation**: Too heavyweight for a solo, iterative project.