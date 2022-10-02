Competition was on 9/30/22

I only attempted one problem, of which I received TLE on pretest 4.
I compared my algo to some successfully submitted java algos, and mine was the same.

So at a high level, my algo was correct. I think there were some smaller optimizations that could have been made so that
the problem would be accepted. I'll look into this again soon.

Similar solution to mine: https://codeforces.com/contest/1738/status/page/6?order=BY_JUDGED_DESC
vivek1401

Update 10/1/22: I was able to get a successful submission. The issue was that I was first accepting the input as string,
then converting it to integers. This meant that when the input size was 200k, I had to convert 200k strings to ints,
which was taking a majority of the processing time and causing the timeouts.
Once I fixed the solution to read the input as an integer from the start (and thus avoid the unnecessary conversion),
the tests passed.
