# Elegance

Elegance is a language that believes that the best language is the one you write the fewest amount of bugs in.  It primary features include:

1. Refinement typing.  Elegance can enforce that certain functions only accept certain values.  For example, the code 1/0 is a compile time error.
2. Optional impurity.  Pure functions are the default, but allows impure functions for cases where mutation is typical (such as file IO or random number generation)
3. Descriptive types.  Instead of having a `List<Pair<Int, Int>>`, you have a `(Int, Int)*`

This language is currently in development.  The first iteration of the grammar is complete, and progress is being made on an IR.
