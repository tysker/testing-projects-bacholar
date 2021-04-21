## 1.6 Explain the concept of maintainable code, and how it’s related to test. Explain how to find out if a code base is maintainable.


* Maintainability
* Product quality
* Temporal coupling
* Continuous Integration
* Static Analysis
* Dependency injection, inversion of control
* Low coupling, high cohesion
* Cyclomatic code complexity

**What is good code?**
- logical names for functions
- functions only returns one thing
- not duplicated code
- documentation

**Maintainability**

Maintainable code simply means “code that is easy to modify or extend”.

* carefully constructed code that is easy to read
* code that is easy to dissect (take apart)
* easy to modify without the risk that everything else crashes
* keep code simple
* creating effective documentation

Maintainability can also be archived by tools or techniques as for example by using MVC pattern or TDD.

**Readability and Maintainability**

* Do the names actually reflect the thing they represent?
* Can I understand what the code does by reading it?
* Can I understand what the tests do?
* Do the tests cover a good subset of cases?
* Are the exception error messages understandable?
* Are confusing sections of code either documented,commented, or covered by understandable tests?

**[Temporal coupling](https://enterprisecraftsmanship.com/posts/temporal-coupling-and-immutability/)**

Temporal coupling is coupling that occurs when there are two or more members of a class that need to be invoked in a particular order.

**Continuous Integration**

integrate code into a shared repository frequently, preferable several times a day.

**Static Analysis**

Static code analysis, is a method of computer program debugging that is done by examining the code without executing the program.

* Programming errors
* Coding standard violations
* Undefined values
* Syntax violations
* Security vulnerabilities

**Dependency injection, inversion of control**

**Dependency injection** is a technique in which an object receives other objects that it depends on. These other objects are called dependencies.

The **Inversion of Control** (IoC) and Dependency Injection (DI) patterns are all about removing dependencies from your code.

https://stackoverflow.com/questions/3058/what-is-inversion-of-control

**Low coupling, high cohesion**

https://www.coursera.org/lecture/object-oriented-design/1-3-1-coupling-and-cohesion-q8wGt

_you want to create your system like lego and not like a puzzle. Lego bricks can be placed on every other brick. Not so with a puzzle piece. One puzzle piece only fits on one ore maybe two other puzzles._

(cohesion = the act or state of sticking together tightly)

If the module performs one task and nothing else or has a clear purpose, the module has high cohesion.

Cohesion is the degree to which the elements of a certain module belong together.

Coupling focuses on complexity between a module and other modules.

Cohesion focuses on complexity within a module.

Loose or low coupling is like playing with Lego.
High coupling is playing with puzzles.

**Cyclomatic code complexity**

Cyclomatic Complexity in Software Testing is a testing metric used for measuring the complexity of a software program. It is a quantitative measure of independent paths in the source code of a software program. 

Cyclomatic complexity can be calculated by using control flow graphs or with respect to functions, modules, methods or classes within a software program.

https://datsoftlyngby.github.io/soft2020fall/resources/ef9d8d43-static-analysis.pdf