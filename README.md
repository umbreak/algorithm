# Algorithms from coursera

Scala implementation of algorithms explained on the Coursera Algorithms course.

[Course link](https://www.coursera.org/learn/algorithms-part1)
[Code material (Java version)](https://algs4.cs.princeton.edu/code/)

## Benchmarks
```
jmh:run

```

```
[info] Benchmark                                     Mode  Cnt   Score    Error  Units
[info] SortBenchmarks.immutableInsertionSort10000   thrpt    5   5.877 ±  0,077  ops/s
[info] SortBenchmarks.mutableInsertionSort10000     thrpt    5  14.628 ±  0,046  ops/s
[info] SortBenchmarks.immutableInsertionSort100000  thrpt    5   0.058 ±  0,001  ops/s
[info] SortBenchmarks.mutableInsertionSort100000    thrpt    5   0.146 ±  0,001  ops/s

[info] SortBenchmarks.immutableSelectionSort10000   thrpt    5   2.541 ±  0,007  ops/s
[info] SortBenchmarks.mutableSelectionSort10000     thrpt    5  14.644 ±  0,081  ops/s
[info] SortBenchmarks.immutableSelectionSort100000  thrpt    5   0.024 ±  0,001  ops/s
[info] SortBenchmarks.mutableSelectionSort100000    thrpt    5   0,158 ±  0,001  ops/s
```

Mutable insertion sort is ~ 2.5 times faster than the immutable implementation.

Mutable selection sort is ~ 5.7 times faster than the immutable implementation.