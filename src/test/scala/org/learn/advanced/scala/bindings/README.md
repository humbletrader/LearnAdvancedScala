
Scala defines the following precedence on bindings (from highest priority):

1. Definitions and declarations that are local, inherited, or made available by a package clause in the same source file where the definition occurs have highest precedence.
2. Explicit imports have next highest precedence.
3. Wildcard imports (import foo._) have next highest precedence.
4. Definitions made available by a package clause not in the source file where the definition occurs have lowest precedence.
