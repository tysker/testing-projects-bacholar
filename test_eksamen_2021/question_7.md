## 1.7 Explain unit testing, and what characterizes it in contrast to other types of test.

* What and why
* Unit Under Test System Under Test
* Unit test lifecycle(BeforeAll, AfterAll, SetUp, TearDown)
* Test doubles (mock, fake, stub, spy)
* Test Driven Development
* Dependency Injection
* Equivalence classes, boundary value analysis, equivalence partitions

* * * 

### Unit test to the resque! The goal is to test each unit in isolation.


**What and Why?**

**What**
* It’s a piece of code
* must test a unit 
* must be automated
* must communicate errors found

**Why**
* It helps to fix bugs early in development
* It helps devs to understand the code base
    - By reading unit tests, you can see how the unit is used
    - By writing unit tests, you have to think about use cases, edgecases, fault scenarios
* It serves as documentation
	- Reading unit tests gives an understanding of the intensions /expectations
	- Keeping unit tests is like keeping a log of requirements
It helps with code re-use

**What are the common test levels?**
* Acceptance test
* System test
* Integration test
* Unit test


**Important for Unit tests**
* isolated
* reentrant
* fast
* no talking to database
* no talking to a network
* no touching the file system
* must be able to run at the same time as other tests

* * *

**System under Test**
You will test the system for ->
* functionality
* performance
* scalability
* stress
* reliability
* reusability

* * * 

**Unit test lifecycle(BeforeAll, AfterAll, SetUp, TearDown)**
* The tearDown() class method to perform final cleanup after all test methods complete.
* The setUp() instance method to set initial state before each test method is run.

* * * 

**Test doubles (mock, fake, stub, spy)**

* **Dummy** objects are passed around but never actually used. Usually they are just used to fill parameter lists.
* **Fake** objects actually have working implementations, but usually take some shortcut which makes them not suitable for production(an InMemoryTestDatabase is a good example).
* **Stubs** provide canned answers to calls made during the test,usually not responding at all to anything outside what's programmed in for the test.
* **Spies** are stubs that also record some information based on how they were called. One form of this might be an email service that records how many messages it was sent.
* **Mocks** are pre-programmed with expectations which form a specification of the calls they are expected to receive. They can throw an exception if they receive a call they don't expect and are checked during verification to ensure they got all the calls they were expecting.


* * * 

**Test Driven Development**

* write test before write production code

1. Add a test.
2. Run all tests and see if any new test fails.
3. Write some code.
4. Run tests and Refactor code.
5. Repeat.

**Equivalence classes, boundary value analysis, equivalence partitions**

**Boundary Value Analysis and Equivalence Partitioning Testing** -> https://www.guru99.com/equivalence-partitioning-boundary-value-analysis.html
* * *

**What to do about complexity?** -> Divide and Conquer

**What does Murphy's law state?** -> If it can go wrong, it will go wrong

**What is the meaning of regression?** -> reappearance of a defect

**Dependency injection** -> is a technique in which an object receives other objects that it depends on. These other objects are called dependencies.

**Pure-functions** -> are easier to test. A pure function:
* Returns the same value when given the same arguments
* Has no side effects

It makes the function deterministic. That means it is easier to test. It acts more like a mathematical function.

**Naming of tests** -> Naming of testsTest names should be as descriptive and consistent.