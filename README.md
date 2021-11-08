![perfume-factory-19th-century-science-source](https://user-images.githubusercontent.com/2643227/140740537-30c8725f-67f7-46be-9b05-236128976b4f.jpeg)


# Problem
You own a perfume factory. There are N different formulas you can mix, and each formula can be prepared
"spray" or "oil". So, you can make 2N different types of perfumes.

Each of your customers has a set of perfume types they like, and they will be satisfied if you have at
least one of those types prepared. At most one of the types a customer likes will be a "spray".

You want to make N batches of perfumes so that:
There is exactly one batch for each formula, and it is either spray or oil.

For each customer, you make at least one perfume type that they like.

The minimum possible number of batches are spray (since spray is more expensive to make).

Find whether it is possible to satisfy all your customers given these constraints, and if it is, what
perfume types you should make.

If it is possible to satisfy all your customers, there will be only one answer which minimizes the
number of spray batches.

# Input
One line containing an integer C, the number of test cases in the input file.
For each test case, there will be:
One line containing the integer N, the number of perfume formulas.
One line containing the integer M, the number of customers.
M lines, one for each customer, each containing:
An integer T >= 1, the number of perfume types the customer likes, followed by
T pairs of integers "X Y", one for each type the customer likes, where X is the perfume formula between
1 and N inclusive, and Y is either 0 to indicate oil, or 1 to indicated spray. Note that:
No pair will occur more than once for a single customer.
Each customer will have at least one formula that they like (T >= 1).
Each customer will like at most one spray type. (At most one pair for each customer has Y = 1).
All of these numbers are separated by single spaces.

# Output
C lines, one for each test case in the order they occur in the input file, each containing the string
"Case #X: " where X is the number of the test case, starting from 1, followed by:
The string "IMPOSSIBLE", if the customers' preferences cannot be satisfied; OR
N space-separated integers, one for each color from 1 to N, which are 0 if the corresponding formula
should be prepared oil, and 1 if it should be spray.

# Limits
Small dataset  
C = 100  
1 <= N <= 10  
1 <= M <= 100  
Large dataset  
C = 5  
1 <= N <= 2000  
1 <= M <= 2000  
The sum of all the T values for the customers in a test case will not exceed 3000.

# Sample
Input  
2  
5  
3  
1 1 1  
2 1 0 2 0  
1 5 0  
1  
2  
1 1 0  
1 1 1  

Output  
Case #1: 1 0 0 0 0  
Case #2: IMPOSSIBLE  

In the first case, you must make formula #1 spray, to satisfy the first customer. Every other perfume type
can be oil. The second customer is satisfied by getting formula #2 oil, and the third customer
is satisfied by getting formula #5 oil.

In the second case, there is only one formula. One of your customers wants it spray and one wants it
oil. You cannot satisfy them both.
