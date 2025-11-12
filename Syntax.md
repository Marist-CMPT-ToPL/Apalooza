# Formal Syntax Grammar for Apalooza

This document defines the formal syntax grammar for the Apalooza programming language using EBNF notation.

## Grammar Productions

### Program Structure
1. **program** → declaration* EOF

### Declarations
2. **declaration** → classDecl | funDecl | varDecl | statement
3. **classDecl** → "class" IDENTIFIER ( "<" IDENTIFIER )? "{" function* "}"
4. **funDecl** → "fn" function
5. **varDecl** → "let" IDENTIFIER ( "=" expression )? ";"

### Functions
6. **function** → IDENTIFIER "(" parameters? ")" block
7. **parameters** → IDENTIFIER ( "," IDENTIFIER )*

### Statements
8. **statement** → exprStmt | forStmt | ifStmt | printStmt | returnStmt | whileStmt | block
9. **exprStmt** → expression ";"
10. **forStmt** → "for" "(" ( varDecl | exprStmt | ";" ) expression? ";" expression? ")" statement
11. **ifStmt** → "if" "(" expression ")" statement ( "else" statement )?
12. **printStmt** → "print" expression ";"
13. **returnStmt** → "return" expression? ";"
14. **whileStmt** → "while" "(" expression ")" statement
15. **block** → "{" declaration* "}"

### Expressions
16. **expression** → assignment
17. **assignment** → ( call "." )? IDENTIFIER "=" assignment | logic_or
18. **logic_or** → logic_and ( "or" logic_and )*
19. **logic_and** → equality ( "and" equality )*
20. **equality** → comparison ( ( "!=" | "==" ) comparison )*
21. **comparison** → term ( ( ">" | ">=" | "<" | "<=" ) term )*
22. **term** → factor ( ( "-" | "+" ) factor )*
23. **factor** → unary ( ( "/" | "*" ) unary )*
24. **unary** → ( "!" | "-" ) unary | call
25. **call** → primary ( "(" arguments? ")" | "." IDENTIFIER )*
26. **primary** → "true" | "false" | "nil" | "this" | NUMBER | STRING | IDENTIFIER | "(" expression ")" | "super" "." IDENTIFIER

### Utility Rules
27. **arguments** → expression ( "," expression )*

### Lexical Rules
28. **NUMBER** → DIGIT+ ( "." DIGIT+ )?
29. **STRING** → '"' ( [^"\\] | "\\" ['"\\nrt] )* '"'
30. **IDENTIFIER** → ALPHA ( ALPHA | DIGIT )*
31. **ALPHA** → [a-zA-Z_]
32. **DIGIT** → [0-9]

## Keywords
Reserved words that cannot be used as identifiers:
- `and`, `class`, `else`, `false`, `fn`, `for`, `if`, `nil`, `or`
- `print`, `return`, `super`, `this`, `true`, `let`, `while`

## Operators
### Arithmetic
- `+` (addition), `-` (subtraction/negation), `*` (multiplication), `/` (division)

### Comparison
- `>` (greater than), `>=` (greater or equal), `<` (less than), `<=` (less or equal)
- `==` (equality), `!=` (inequality)

### Logical
- `and` (logical AND), `or` (logical OR), `!` (logical NOT)

### Assignment
- `=` (assignment)

### Delimiters
- `(`, `)` (parentheses), `{`, `}` (braces), `,` (comma), `.` (dot), `;` (semicolon)

## Comments
Single-line comments start with `//` and continue to the end of the line.

---

## Abstract Syntax Trees for Sample Programs

### Sample 1: Basic Arithmetic and Variables (Actual Implementation)

```
let x = 10;
let y = 20;
let sum = x + y;
let difference = y - x;
let product = x * y;
let quotient = y / x;
let remainder = y % x;
```

**AST:**
```

```

### Sample 2: String Handling and Boolean Logic

```
let greeting = "Hello, World!";
let name = "Apalooza";
let escaped_string = "Line 1\nLine 2\tTabbed\\"Quote\\"";

let is_ready = true;
let is_complete = false;
let both_true = is_ready && is_complete;
let either_true = is_ready || is_complete;
let not_ready = !is_ready;
```

**AST:**
```

```

### Sample 3: Function Definition and Application (Corrected for Actual Syntax)

**Note**: The current implementation only supports function declarations, not function expressions or arrow functions.
**Sample Program:**
```
fn add(a, b) {
    return a + b;
}

fn multiply(x, y) {
    return x * y;
}

fn greet(person) {
    return "Hello, " + person;
}

let result1 = add(5, 3);
let result2 = multiply(4, 7);
let message = greet("Alice");
```

**AST:**
```

```

### Sample 4: Conditional Statements (Corrected for Actual Syntax)

**Note**: The current implementation only supports `if` statements, not conditional expressions.
**Sample Program:**
```
let age = 25;
let min_age = 18;
let max_score = 100;
let current_score = 85;

let is_adult = age >= min_age;
let is_passing = current_score > 60;
let is_perfect = current_score == max_score;
let needs_improvement = current_score != max_score;
let is_young = age < 30;

let status;
if (is_adult) {
    status = "Adult";
} else {
    status = "Minor";
}

let grade;
if (current_score >= 90) {
    grade = "A";
} else if (current_score >= 80) {
    grade = "B";
} else {
    grade = "C";
}
```

**AST:**
```

```

### Sample 5: Float Numbers and Advanced Comparisons

```
// Sample Program 5: Float Numbers and Advanced Comparisons
let pi = 3.14159;
let e = 2.71828;
let temperature = -15.5;
let zero = 0.0;

let sum_constants = pi + e;
let scaled_temp = temperature * 2.0;
let ratio = pi / e;

// Comprehensive comparison demonstrations
let pi_greater_e = pi > e;
let temp_less_zero = temperature < zero;
let pi_equals_pi = pi == pi;
let e_not_equals_pi = e != pi;
let temp_less_equal_zero = temperature <= zero;
let pi_greater_equal_e = pi >= e;

// Classification using if statements (not conditional expressions)
let classification;
if (temperature <= -10.0) {
    classification = "Very Cold";
} else if (temperature <= 0.0) {
    classification = "Cold";
} else {
    classification = "Warm";
}
```

**AST:**
```

```
