# testng + selenium poc

http://testng.org

# goals

* provide a codebase for running TestNG based tests across multiple clients
* strive for a no-code-redundancy model
  * for low maintenance (editing one file updates all tests)
* determine if TestNG is suitable for visual tests / screenshot comparisson, or if 3rd party tool is better.
* deliver something that compares/competes with nodejs+nightmare test runner.


# requirements

to complete the POC, we'd like to provide a basic set of TestNG and explore
scaling this to multiple clients.  

## tests

these are ordered from basic to advanced, and should be delivered in this
order.

* **functional tests** should execute under _both_ iOS and Chrome
* **visual tests** should execute execute under mobile + desktop resolutions

---

1. homepage check (**smoke test**)
  * checks for 200 status code
  * checks that response body contains a string
1. filtration check (**functional test**)
  * verifies category page returns specified # results/page
  * verifies sorting orders
1. concurrent searches (**performance test**)
  * random keywords
  * measure response time
  * verify results page
  * demonstrate how to determine max searches/period (e.g. before 500/errors are returned)
1. checkout funnel (**functional test**)
  * demonstrate a maintainable code base. e.g. support "onepage", "native", and "per-client" overrides in test configuration.
1. _optional_ header comparisson (**visual test**)
  * ensure mobile layout is working (e.g. test for mobile mega-menu)


## inheritance model

* Use "base" configuration in YAML to feed tests (e.g. selectors, strings, urls)

* "base" tests should be general enough to be used by most client codebases without need to change/override.

* per-client changes can:
  * override base tests
  * provide _additional_ tests (in addition to base tests)
  * provide configuration (overrides base configuration)


# notes

* for authoritative documentation, see: http://testng.org/doc/documentation-main.html

* for YAML example, see: https://github.com/cbeust/testng/blob/master/src/test/resources/testng.yaml


* testng supports running multiple test configuration files
  ```
  java org.testng.TestNG testng1.xml [testng2.xml testng3.xml ...]
  ```

* testng supports passing a package instead of specific classes
  ```yaml
  # In this example, TestNG will look at all the classes in the package test.regressions and will retain only classes that have TestNG annotations.
  - name: regressions
    packages:
       - test.regressions
  ```

* testng allows for inclusion/exclusion of groups (groups are annotated in the java classes)
  ```yaml
  - name: regressions
    excludedGroups:
      - mobile
    packages:
       - test.regressions
  ```

  ```java
  public class Test1 {
    @Test(groups = { "regression", "mobile" })
    public void testMethod1() {
    }

    @Test(groups = {"regression", "desktop"} )
    public void testMethod2() {
    }
  }
  ```

* regex is supported in group matching
  ```yaml
  - name: regressions
    includedGroups:http://testng.org
      - "mobile.*"
  ```

* configuration allows "metagroup" definitions (e.g. group of groups)
  ```yaml
  - name: regressions
    metaGroups:
      all:
        - mobile
        - desktop
    includedGroups:
      - all
  ```

* supports test teardown and preparation logic via @Before\* and @After\* annotations. (see docs)
