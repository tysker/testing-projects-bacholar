## 1.2 Explain test levels, and what characterizes the individual levels. Then, relate to your own project.

__The goal of testing is to design tests that exercise defects in the system and to reveal problems.__


* unit testing
* integration testing
* system testing
* load testing
* static testing
* acceptance testing

* * *

## test levels

* A level of software testing is a process where every unit or component of a software/system is tested.
* The primary goal of system testing is to evaluate the system's compliance with the specified needs.
* In Software Engineering, four main levels of testing are Unit Testing, Integration Testing, System Testing and Acceptance Testing.

* * *

## [unit testing](http://tryqa.com/?s=unit+testing)

**Why unit tests ?**

- It helps to fix bugs early in development
- It helps devs to understand the code base
- It serves as documentation
	- Reading unit tests gives an understanding of the intensions /expectations
	- Keeping unit tests is like keeping a log of requirements
- It helps with code re-use
- By reading unit tests, you can see how the unit is used
- By writing unit tests, you have to think about use cases, edgecases, fault scenarios

1. The goal is to test each unit in isolation.
2. Smallest possible unit 
3. Scrutinize each unit *(examine or inspect closely and thoroughly)*

**What is a unit test?**

- It’s a piece of code
- Must test a unit of work
- Must be automatic
- Must communicate errors found
- They're fast
- They are fully automated
- They're self-verifing
- They're repeatable and consistent
- They test a single logical concept
- They run in isolation

**Testing conditions**

- No talking to db
- No talking to network
- No touching the file system
- It can't run at the same time as any of the others unit tests
- Must not require special change to your code environment(such as editing configuration files) to run it.

* * *

## [integration testing](http://tryqa.com/what-is-integration-testing/)

- Testing that separately developed modules worked together properly.
- Test that a system of multiple modules worked as expected.


**narrow integration tests**

    
- exercise only that portion of the code in my service that talks to a separate service.
- uses test doubles of those services, either in process or remote.
- thus consist of many narrowly scoped tests, often no larger in scope than a unit test (and usually run with the same test framework that's used for unit tests).

**broad integration tests**

- require live versions of all services, requiring substantial test      environment and network access.
- exercise code paths through all services, not just code responsible    for interactions.

* * *

## [system testing](http://tryqa.com/what-is-system-testing/)

- Tests the system as a whole

- Once all the components are integrated, the application as a whole is
   tested rigorously to see that it meets the specified Quality Standards

- Important because:

	- The application is tested thoroughly to verify that it meets the
	  functional and technical specifications

	- The application is tested in an environment that is very close to the
	  production environment

	- System testing enables us to test, verify, and validate both the
	  business requirements as well as the application architecture

* * *

## [load testing](http://tryqa.com/what-is-load-testing-in-software/)

Load testing is a type of non-functional testing. A load test is type of software testing which is conducted to understand the behavior of the application under a specific expected load. Load testing is performed to determine a system’s behavior under both normal and at peak conditions.

Load testing one among the different kinds of performance testing that determines the performance of the system in real time load conditions. It is basically used to ensure that the application performs satisfactorily when many users try to access or use it at the same time.

**Examples of load testing include**

* Downloading a series of large files from the internet
* Running multiple applications on a computer or server simultaneously
* Assigning many jobs to a printer in a queue
* Subjecting a server to a large amount of traffic
* Writing and reading data to and from a hard disk continuously

* * *

## [static testing](http://tryqa.com/what-is-static-testing/)

Don't confuse static testing with static analysis testing!!!!!!!!

* Static testing is the testing of the software work products manually, or with a set of tools, but they are not executed.
* It starts early in the Life cycle and so it is done during the verification process.
* It does not need computer as the testing of program is done without executing the program. For example:  
    * reviewing, 
    * walk through, 
    * inspection, etc.

**Cost and benefits with static tests**

* It’s expensive to spend time reading documents
* But it’s an investment

**Adoption of static testing**

* It can be difficult to give and take criticism
* Feeling of ownership over code
* Afraid of pointing out errors(especially in a more experienced developer’s work)
* Practice diplomacy

* * *

## [acceptance testing](http://tryqa.com/what-is-acceptance-testing/)

- The most important type of testing, as it is conducted by the customer/
end users

- Tracks whether the application meets the intended specifications and satisfies the client’s requirement

- Acceptance tests are not only intended to point out simple spelling mistakes, cosmetic errors, or interface gaps

- Acceptance tests will also to point out any bugs in the application that will result in system crashes or major errors in the application