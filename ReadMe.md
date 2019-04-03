This package contains the following files:
├── README.md
├── run.sh
├── src
│   └── com
│       └── lingjun
│           └── insight
│               └── DepartmentStats.java
│               └── DepartmentStatsTest.java 
│               └── InputOutputUtils.java
│               └── ParsingUtils.java
│               └── ParsingUtilsTest.java
│               └── PurchaseAnalyzer.java
├── input
│   └── products.csv
|   └── order_products.csv
├── output
|   └── report.csv
├── insight_testsuite
    └── run_tests.sh
    └── tests
        └── test_1
        |   ├── input
        |   │   └── products.csv
        |   │   └── order_products.csv
        |   |__ output
        |   │   └── report.csv
        └── test_2
        |   ├── input
        |   │   └── products.csv
        |   │   └── order_products.csv
        |   |__ output
        |   │   └── report.csv
        ├── test_3
            ├── input
            │   └── products.csv
            |   └── order_products.csv
            |── output
                └── report.csv

——————————————————————————————————————————————————————————
How to run the code:
java -jar src/InsightDataChallenge.jar  [Path to order_products.csv] [Path to products.csv] [Path to report.csv]

——————————————————————————————————————————————————————————
Assumptions:

There are several assumptions that I made after reading through the input files:
1. Assumes department_ids are integers. As required to sort output by department_id, I use Integer instead of String to represent department_id. Department_id is usually within the range of 100, so no need to use Long to represent them.
2. Assumes one product_id only belongs to one department_id. It is possible that the same product belongs to multiple departments. But in the products.csv data set, all product_id has a single department_id.

———————————————————————————————————————————————————————————
My algorithm mainly contains three steps:

1. Generates the mapping form product_id to department_id
2. Generates the mapping from department_id to DepartmentStats (i.e., number of orders, number of first orders, first order percentage).
3. Writes the result map from Step 2 to output CSV file.

————————————————————————————————————————————————————————————

