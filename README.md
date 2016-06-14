# testng + selenium poc

http://testng.org


# goals


# requirements


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
