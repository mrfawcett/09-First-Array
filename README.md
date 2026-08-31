# First Array: Horse Barn and Mountain

**Unit 3 — Arrays** · Pairs with lectures 3.1 Arrays and 3.1.1 Array Algorithms · CED 4.3–4.5

Two small classes, four methods, and every one of them is a single pass over a
one-dimensional array. `HorseBarn` holds an array of `Horse` objects with
`null` marking an empty stall; you search it and compact it in place.
`Mountain` looks at an `int[]` and decides whether it has a peak and whether it
is shaped like a mountain. `HorseBarn` is adapted from the 2012 AP Computer
Science A free-response question *HorseBarn* (Question 3); `Mountain` is
adapted from the College Board's 2014 AP CSA Practice Exam free-response
section. These are the array patterns the exam asks for every year: linear
search, "next free slot" compaction, and comparing an element with its
neighbors without running off either end.

## What you are given
| File | Status | Purpose |
|---|---|---|
| `src/main/java/HorseBarn.java` | **you complete this** | `findHorseSpace`, `consolidate` |
| `src/main/java/Mountain.java` | **you complete this** | `getPeakIndex`, `isMountain` (`isIncreasing`, `isDecreasing` provided) |
| `src/main/java/Horse.java` | provided | a name and a weight; do not change |
| `src/test/java/*Test.java` | provided | the autograder's tests — read them |

## What to write

| Method | Points | What it does |
|---|---|---|
| `HorseBarn.findHorseSpace(String name)` | 20 | index of the horse with that name, or `-1` |
| `HorseBarn.consolidate()` | 30 | slide every horse toward index 0, keep the order, `null` the rest |
| `Mountain.getPeakIndex(int[] array)` | 25 | index of the first element bigger than both neighbors, or `-1` |
| `Mountain.isMountain(int[] array)` | 25 | `true` if the array rises strictly to one peak and falls strictly after it |

### `findHorseSpace(String name)`
Walk the array from index 0. Return the index of the first space whose horse
has exactly the given name. Return `-1` if no horse matches. Two rules:
- Empty spaces are `null`. Test for `null` **before** you call `getName()`, or
  you will get a `NullPointerException`.
- Compare names with `.equals`, never `==`. The match is case-sensitive:
  `"patches"` does not find `"Patches"`.

The search must not change the barn.

### `consolidate()`
Move every horse so the horses occupy adjacent spaces starting at index 0, in
the **same order** they were in, and every space after the last horse is
`null`.

```
before: [Trigger, null, Silver, null, null, Patches, Duke]
after:  [Trigger, Silver, Patches, Duke, null, null, null]
```

Do it in one pass with a "next free slot" counter: start `next = 0`; for each
index `i`, if `spaces[i]` is a horse, put it at `spaces[next]` and add one to
`next`. When the loop ends, set every space from `next` to the end to `null`.

The trap: copying horses forward but forgetting the clearing step. The array
`[null, Lady, null, Duke]` becomes `[Lady, Duke, null, Duke]` — Duke appears
twice. The tests check that every trailing space is `null`. Move the
references; do not build new `Horse` objects.

### `getPeakIndex(int[] array)`
A **peak** is an element that is strictly greater than the element right
before it and strictly greater than the element right after it. The first and
last elements are never peaks because they have only one neighbor. Return the
index of the **first** peak; return `-1` if there is none.

Loop `i` from `1` to `array.length - 2` inclusive and compare `array[i]` with
`array[i - 1]` and `array[i + 1]`. That bound is the whole exercise: start at
`0` or run to `array.length - 1` and you read outside the array. Arrays of
length 1 or 2 have no candidates and must return `-1` without crashing.

Use `>` not `>=`: `{1, 3, 8, 8, 2}` has a plateau, not a peak, so the answer
is `-1`.

### `isMountain(int[] array)`
Return `true` only when all three hold:
1. `getPeakIndex(array)` is not `-1`;
2. `isIncreasing(array, peak)` — everything before the peak strictly rises;
3. `isDecreasing(array, peak)` — everything after the peak strictly falls.

Call the helpers; do not re-write them. If there is no peak, return `false`
right away — passing `-1` to the helpers is what the `stop < 0` guards are
for, but a mountain check should never get there.

## Examples

`HorseBarn` (index : horse):
```
0 Trigger   1 null   2 Silver   3 Lady   4 null   5 Patches   6 Duke
```
| Call | Result |
|---|---|
| `findHorseSpace("Trigger")` | `0` |
| `findHorseSpace("Silver")` | `2` |
| `findHorseSpace("Duke")` | `6` |
| `findHorseSpace("Coco")` | `-1` |
| `findHorseSpace("patches")` | `-1` (case matters) |
| `consolidate()` | `[Trigger, Silver, Lady, Patches, Duke, null, null]` |

`Mountain`:
| `arr` | `getPeakIndex(arr)` | `isMountain(arr)` |
|---|---|---|
| `{1, 2, 3, 2, 1}` | `2` | `true` |
| `{1, 2, 1, 2, 1}` | `1` | `false` — rises again after the peak |
| `{1, 2, 3, 5, 7}` | `-1` | `false` |
| `{9, 3, 5, 7, 5}` | `3` | `false` — falls before the peak |
| `{1, 4, 2, 1, 0}` | `1` | `true` |
| `{3, 2, 1}` | `-1` | `false` |
| `{1, 3, 8, 8, 2}` | `-1` | `false` — plateau |

## Running the tests
`mvn test` runs everything; `mvn test -Dtest=<ClassName>` runs one rubric line.

| Test class | Rubric line | Points |
|---|---|---|
| `FindHorseSpaceTest` | `HorseBarn.findHorseSpace` | 20 |
| `ConsolidateTest` | `HorseBarn.consolidate` | 30 |
| `GetPeakIndexTest` | `Mountain.getPeakIndex` | 25 |
| `IsMountainTest` | `Mountain.isMountain` | 25 |

The autograder awards a rubric line only when every test in that class passes.

## Suggested order
1. `findHorseSpace` — the simplest linear search. Get the `null` check right
   here and you will reuse it in `consolidate`. Run `FindHorseSpaceTest`.
2. `consolidate` — trace the "next free slot" idea on paper with
   `[null, Lady, null, Duke]` before you type. Run `ConsolidateTest`; if only
   the "trailing spaces must become null" test fails, you skipped the clearing
   loop.
3. `getPeakIndex` — write the loop bounds first, then the comparison. Run
   `GetPeakIndexTest`; failures on `{5}` or `{1, 2}` mean your bounds are off.
4. `isMountain` — three helper calls and two `if`s. Run `IsMountainTest`.

## Rules of the road
- AP Java subset only: loops, `if`, arrays, `String.equals`. No `Arrays.*`,
  no `System.arraycopy`, no `ArrayList`, no streams.
- Do not change method headers or provided code (`Horse`, the constructor,
  `getSpaces`, `isIncreasing`, `isDecreasing`).
- Do not touch `src/test`, `pom.xml`, `grading.json`, or `.github`. The
  autograder checks that they are byte-identical to the template before it
  runs a single test; if they differ it stops and awards nothing, and the
  change shows up in the roster.
- Write `consolidate` **in place** on the existing array — moving references
  around inside one array is the skill this assignment teaches. Do not create
  a second array.
