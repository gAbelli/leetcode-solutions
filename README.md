# Strategy

## Memoization

To check:

- Can we lower the time complexity by using dynamic programming instead? This is how I imagine the difference between bottom-up (DP) and top-down (memoization).

## Dynamic programming

To check:

- Did we initialize the dp array properly? We often do something like `val dp = IntArray(n) { 0 }; dp[0] = 1` or `val dp = IntArray(n) { Int.MAX_VALUE }; dp[0] = 0`
- Can we reduce the space complexity by replacing the nD array with an (n-1)D array?
  - This always happens when the solution for row i only depends on the one for row i+1.
  - The easiest way to do this is to use `cur` and `prev` arrays and set `prev[i] = cur[i]` for every i at the end of the loop.
  - Sometimes this is not even needed, but it might be necessary to iterate the array in reverse order.

## Backtracking

The difference between backtracking and memoization/dp is that backtracking is needed when the solution to the remaining subproblems depends on the solution of the current subproblem (imagine the sudoku solver example).

## Bisection

There are mainly two scenarios in which bisection appears:

- We want to find the only element that satisfies a certain condition.
  If we are sure that it exists we can do a `while (true)`. Otherwise we should do `while (left < right)` and then

  ```kotlin
  if (...) left = mid + 1
  else right = mid - 1
  ```

- We want to find the rightmost element that satisfies a certain condition, knowing that all elements on its left will satisfy the same condition (or vice versa).
  Here it's better to use `while (left < right)` so that when we exit the loop we have `left == right`. Then we should either do

  ```kotlin
  val mid = (left + right) / 2
  if (...) left = mid + 1
  else right = mid
  ```

  or

  ```kotlin
  val mid = (left + right + 1) / 2
  if (...) left = mid
  else right = mid - 1
  ```

  to make sure that we never run into an infinite loop.

If `left` is negative, then we should compute the average as `left + (right - left) / 2` (respectively `left + (right - left + 1) / 2`) because integer division rounds towards zero instead of always rounding down.
This is of course also useful to prevent overflows if `left` and `right` are both non-negative.
