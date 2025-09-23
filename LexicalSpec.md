### Lexical specs for Apalooza
## IDENTIFIER ::= LETTER (LETTER | DIGIT | '_')*
# To be continuely developed
## Rules for Identifiers
## Rules for literals 
## Reserved Keywords
## Reserved Symbolic Operators 
# To be continuely developed

# Lexical Specification for Apalooza

## Basic Character Classes

1. LETTER  ::= [a-zA-Z]
2. DIGIT.  ::= [0-9]
3. WHITESPACE    ::= ' ' | '\t' | '\r' | '\n'

## Rules for Identifiers

4. IDENTIFIER    ::= LETTER (LETTER | DIGIT | '_')*

- Must start with a letter
- Can contain letters, digits, and underscores
- Case-sensitive
- Cannot be a reserved keyword

## Rules for Literals

### Numbers
5. INTEGER       ::= '-'? DIGIT+
6. FLOAT         ::= '-'? DIGIT+ '.' DIGIT+

### Booleans
7. BOOLEAN       ::= 'true' | 'false'

### Strings
8. STRING        ::= '"' STRING_CHAR* '"'
9. STRING_CHAR   ::= [^"\\\n] | '\\' ESCAPE_CHAR
10. ESCAPE_CHAR  ::= 'n' | 't' | 'r' | '\\' | '"'

## List of Reserved Keywords

11. KEYWORDS  ::= 'let' | 'in' | 'fn' | 'if' | 'then' | 'else' | 'true' | 'false'

## List of Reserved Symbolic Operators

### Arithmetic
12. ARITHMETIC ::= '+' | '-' | '*' | '/' | '%'

### Comparison
13. COMPARISON ::= '==' | '!=' | '<' | '>' | '<=' | '>='

### Logical
14. LOGICAL  ::= '&&' | '||' | '!'

### Function
15. FUNCTION     ::= '=>'

## Additional Token Types for Scanner

### Delimiters
16. DELIMITERS   ::= '(' | ')' | ','

### Comments
17. COMMENT      ::= '//' [^\n]*

### Special
18. EOF          ::= <end of file>