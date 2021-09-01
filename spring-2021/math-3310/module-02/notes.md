## 1.15 Mathematical Induction

Mathematical Induction is a tool used in proofs throughout mathematics. 

**Problem 1.15.1**

Consider an island populated only with two types of people. Those who can tell the truth and those who can lie. One person says "This is not the first time I hae said what I am now saying." . Is that person a liar or a truthteller? 

**Theorem 1.15.2**: If a property holds for the number zero, and whenever it holds for any number n it also holds for n + 1, then it holds for all nautral numbers. 

P(0) = T AND (P(k) = T -> P(K + 1) = T) -> P(n) = T for all n in nautral nubmers. 

**Strong Induction**

There is a variant on induction called Strong Induction and the only difference on the surface is that instead of assuming P9)), we assume that the property holds for all numbers from zero up to n. Basically that something is true for all numbers before k. 

**Least Number Principle**

For every non-empty set of nautral numbers containing at least one element


## 1.16 Using the Principle of Mathematical Induction (PMI)

For PMI to apply, we have to meet two cases: 

1) Have a base case. 
∂
Prove 1 exists in T (The set of all n where P(n) is true). Basically, show that P(1) is true.

2) Have an inductive step

Prove that if k exists in T, then k + 1 exists in T. Basically, show that if P(k) is true, then p(k+1 is true as well). 

### Theorem 1.16.1. Prove 4 divides (5^n -1). For all n that exists in natural numbers, let P(n) be "4 divides 5^n -1". 

Example check (base case): 

n = 1 -> 5^1 - 1 = 4. 4/4 = 1. 

n = 2 -> 5^2 - 1 = 24. 24/4 = 6. 

Assuming this is always true. 4a = 5^n - 1 -> 4a + 1 = 5^n