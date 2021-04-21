## 1.5 Testing is related to ensuring higher code quality. Elaborate on what characterizes high code quality, and what makes code testable.

* Testable code
* Names of tests
* “sufficient” tests of a method or class
* Assertions, defensive programming
* Dependency injection

**What is good testable code ?**

* pure functions
* low coupled
* simplicity
* separation between logic and presentation

**Names of tests**

naming of the test should reflect what the test actually does. It should be clear without having to read a line a of the code what the test is all about. Should have the word test included in the name.

**What is sufficient testing of a method or class ?**

Depends on which part of testing we talking about. It various what developer think is enough testing. Acceptance or verification testing for example should always be at 100% code coverage to ensure a functional software product according to a specification. 

**What is defensive programming ?**

- Check preconditions, trust nobody
- Check postconditions, doubt yourself
- Check invariants (An invariant (never changing) means some conditions that must be true at some point in time or even always while your program is executing.)

_The precondition statement indicates what must be true before the function is called._

_The postcondition statement indicates what will be true when the function finishes its work._

**Dependency injection ?**

Dependency injection is a technique in which an object receives other objects that it depends on. These other objects are called dependencies.

Keep the classes as independently as possible. So there are easier to test. 
https://www.vogella.com/tutorials/DependencyInjection/article.html


**What is good code?**
- logical names for functions
- functions only returns one thing (pure functions)
- no duplicated code
- documentation



**What is Code quality?**

[List of system quality attributes](https://en.wikipedia.org/wiki/List_of_system_quality_attributes)

[Non-functional requirement](https://en.wikipedia.org/wiki/Non-functional_requirement)

- customer is satisfied
- developer can confidently change the code.
- even new developer can contribute to the code base
- maintainability
	- testability
	- stabel
- usability
	- attractivness
	- operability
	- understandability
	- learn-ability
- efficently
	- time behavior
	- ressource utiliatzion
- portability
	- adaptbility
	- replacebility
	- installability
	- co-existence
- funcionality
	- security
	- regularity
	- accuarcy
- reliability
	- fault tolerance
	- recoverbility
	- maturity