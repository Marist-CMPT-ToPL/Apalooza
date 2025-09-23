// Sample Program 4: Conditional Expressions
let age = 25
let min_age = 18
let max_score = 100
let current_score = 85

let is_adult = age >= min_age
let is_passing = current_score > 60
let is_perfect = current_score == max_score
let needs_improvement = current_score != max_score
let is_young = age < 30

let status = if is_adult then "Adult" else "Minor"
let grade = if current_score >= 90 then "A" else if current_score >= 80 then "B" else "C"

// This program showcases:
// - Comparison operators: >=, >, ==, !=, <
// - Keywords: if, then, else
// - Nested conditional expressions
// - Boolean expressions using comparisons
// - Identifiers with meaningful names
// - Mixed integer and string operations
