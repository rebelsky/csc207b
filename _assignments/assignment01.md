---
title: Assignment 1
subtitle: Classical encryption
summary: |
  In this assignment, you'll practice your basic programming-in-the-small
  skills to implement a number of *[classical
  encryption](https://en.wikipedia.org/wiki/Classical_cipher)*
  techniques.
submitting: |
  Please create an assignment repository through the Github Classroom link found on the course schedule.
  When grading your work, we will use the final commit date on your repository as the official turn-in date.
  Please do not commit to your repository after your final submission unless you want to accept a later turn-in date!
copyright:  Copyright &copy; Peter-Michael Osera and Samuel A. Rebelsky.
copyright_message: |
  This work is licensed under a Creative Commons Attribution-NonCommercial
  4.0 International License.  To view a copy of this license, visit
  <http://creativecommons.org/licenses/by-nc/4.0/>.
link: true
---
Classical encryption is based on the principle of substituting letters for other letters.
These may be simple schemes like the ones we'll implement in this homework or more complicated schemes such as they employed in the [Enigma Machine](https://en.wikipedia.org/wiki/Enigma_machine).
The Enigma machine was used by Nazi Germany during World War II and was subsequently broken by the allies, in particular the researchers at the British [Government Code and Cypher School](https://en.wikipedia.org/wiki/Government_Communications_Headquarters#Government_Code_and_Cypher_School_.28GC.26CS.29), notably [Alan Turing](https://en.wikipedia.org/wiki/Alan_Turing) who led development of the [Bombe](https://en.wikipedia.org/wiki/Bombe) machines that were used to break the Enigma machines.

## README.md

Each of your online submissions should include a `README.md` file with the following contents:

1. The name(s) of the members of this group
2. A one sentence description of the content of this submission
3. A list of the resources you used---organic or inorganic---to complete this assignment and how they helped you complete the assignment, one sentence per such resource.
   Examples of resources include tutors, fellow students (within the bounds of the course's policy on collaboration), or online resources such as StackOverflow.

## Encryption Preliminaries

The theory of encryption is a cornerstone of *[cryptography](http://en.wikipedia.org/wiki/Cryptography)* and *[computer security](http://en.wikipedia.org/wiki/Computer_security)* where we make our communication and/or data secure from adversaries.

Before the advent of computers, *classical cryptography* used various *transposition* and *substitution ciphers* to encrypt data.
Here are some basic definitions you should know before proceeding.

* A **cipher** is a pair of algorithms for encrypting and decrypting data.
* **Plaintext** is un-encrypted data.
* **Ciphertext** is encrypted data.
* A **transposition** cipher creates ciphertext from plaintext by re-arranging the letters in the text in some pre-determined fashion.
* A **substitution** cipher creates ciphertext from plaintext by substituting one letter for another letter.

Ciphers in classical cryptography were designed to be executed by hand with analog tools.
For example, the [Solitaire Cipher](http://en.wikipedia.org/wiki/Solitaire_(cipher)) was designed to be hand-executed by secret agents in the field with nothing more than a deck of cards.
More elaborate encryption schemes required the use of mechanical computing devices that became the forefathers of the modern-day digital computers, the Enigma machine described in the introduction being the quintessential example.
If you are curious, here is a [fun simulation](http://www.enigmaco.de/enigma/enigma.html) of an Enigma machine.
However, in the age of digital computers, classical cryptography methods are no longer useful for most practical purposes because a computer can either brute-force through these encryption schemes, or otherwise greatly assist an
educated individual in breaking the code.

To implement these classical cryptographic schemes, we need to understand how to map the mathematical models that underlie them into Java.
Luckily this is relatively straightforward.
For sake of simplicity, let's assume that we're only working with lowercase English letters and that each letter is assigned a number---`'a'` starting at `0` and `'z'` ending with 25---called the *character code* of that letter.
Given a single letter `ch` and a single letter `key` to encrypt the letter with:

* To encrypt `ch` with `key`, "add" `key` to `ch`.
That is, add their corresponding character codes, and the result is the character code of the corresponding encrypted letter.
If you go over 25, wrap around.
For example, If we encrypt `c` with `j`, then we get `l` because the code for `c` is 2 and the code for `j` is 9.
2 + 9 = 11 which is the code for `l`.
If we encrypt `x` with `e`, then we get `b` because the code for `x` is 23 and the code for `e` is 4.
23 + 4 = 27 but since 27 isn't a valid code, we wrap around and get 1 which is the code for `b`.
* To decrypt `ch` with `key`, "subtract" `key` from `ch`.  In the case when the difference is negative, we wrap around like with addition.

By thinking of characters as numbers, we can formalize this style of encryption as well as directly implement it in Java.
With character values, encryption can be thought of the formula:

~~~
E = (ch + key) mod 26.
~~~

Decryption is described by the formula:

~~~
D = (ch - key) mod 26.
~~~

Mod here is *almost* the `%` operator, but not quite.
The problem is that negative integers do not "wrap around" like you expect, *e.g.*, `-2 % 26` is `-2` rather than `24`.
You will need to do a little bit of extra work to obtain the desired behavior in this case.

To get the character value of a single character, we can convert it to an integer by *casting* to the appropriate type:

~~~
java> (int) 'e'
java.lang.Integer res1 = 101
~~~

However, we said that the character code for `e` is `4`.
What is going on here?
It turns out that we assign character codes to not just lowercase letters but to *all possible letters*.
Imagine putting all the possible letters on a line.
The lowercase letters occupy indices 97 through 122 on that line:

~~~
java> (int) 'a'
java.lang.Integer res0 = 97
java> (int) 'z'
java.lang.Integer res1 = 122
~~~

To "re-base" these numbers at index zero, we simply need to subtract the character value of `a`.

~~~
java> int base = (int) 'a'
int base = 97
java> (int) 'a' - base
java.lang.Integer res3 = 0
java> (int) 'z' - base
java.lang.Integer res4 = 25
~~~

When we want to get a letter back given a computed character value in the range 0-25, we simply reverse the process by adding back in `(int) 'a'` and then casting back to `char`.

~~~
java> int result = 22
int result = 22
java> (char) (result + base)
java.lang.Character res6 = w
~~~

## Part 1: Caesar Cipher

With the fundamentals of manipulating characters-as-numbers out of the way, we will now implement a number of classic ciphers based off these cryptographic principles.
First, we will implement the [Caesar Cipher](http://en.wikipedia.org/wiki/Caesar_cipher), so named after Julius Caesar who used this encryption for his own private correspondence.

In terms of the formulae described above, the `key` we use to add and subtract to each character is constant.
That is, for any message, we pick a particular value `n` and encrypt a message with:

~~~
E = (ch + n) mod 26.
~~~

And we decrypt a message with

~~~
D = (ch - n) mod 26.
~~~

For example, say we want to encrypt the message `hello`, we would pick a key `n`, say `n = 11`.
Then, to encode the message, we calculate:

~~~
'h' + 11 = 7 + 11 = 18 = 's'
'e' + 11 = 4 + 11 = 15 = 'p'
'l' + 11 = 11 + 11 = 22 = 'w'
'l' + 11 = 11 + 11 = 22 = 'w'
'o' + 11 = 14 + 11 = 25 = 'z'
~~~

To decrypt the message, we subtract the key rather than add it:

~~~
's' - 11 = 18 - 11 = 7
'p' - 11 = 15 - 11 = 4
'w' - 11 = 22 - 11 = 11
'w' - 11 = 22 - 11 = 11
'z' - 11 = 25 - 11 = 14
~~~

Your task is to write a program in a class called **CaesarCipher** that encodes and decodes messages using the Caesar cipher as described above.
Because there are only 26 letters in the English alphabet, rather than shifting according to a user-defined value, we can simply show the user the result of applying all 26 possible shifts!

Here are some example executions of the program you should create.

~~~
$ java CaeserCipher encode "helloworld"
n = 0: helloworld
n = 1: ifmmpxpsme
n = 2: jgnnqyqtnf
n = 3: khoorzruog
n = 4: lippsasvph
n = 5: mjqqtbtwqi
n = 6: nkrrucuxrj
n = 7: olssvdvysk
n = 8: pmttwewztl
n = 9: qnuuxfxaum
n = 10: rovvygybvn
n = 11: spwwzhzcwo
n = 12: tqxxaiadxp
n = 13: uryybjbeyq
n = 14: vszzckcfzr
n = 15: wtaadldgas
n = 16: xubbemehbt
n = 17: yvccfnficu
n = 18: zwddgogjdv
n = 19: axeehphkew
n = 20: byffiqilfx
n = 21: czggjrjmgy
n = 22: dahhksknhz
n = 23: ebiiltloia
n = 24: fcjjmumpjb
n = 25: gdkknvnqkc

$ java CaeserCipher decode dahhksknhz
n = 0: dahhksknhz
n = 1: czggjrjmgy
n = 2: byffiqilfx
n = 3: axeehphkew
n = 4: zwddgogjdv
n = 5: yvccfnficu
n = 6: xubbemehbt
n = 7: wtaadldgas
n = 8: vszzckcfzr
n = 9: uryybjbeyq
n = 10: tqxxaiadxp
n = 11: spwwzhzcwo
n = 12: rovvygybvn
n = 13: qnuuxfxaum
n = 14: pmttwewztl
n = 15: olssvdvysk
n = 16: nkrrucuxrj
n = 17: mjqqtbtwqi
n = 18: lippsasvph
n = 19: khoorzruog
n = 20: jgnnqyqtnf
n = 21: ifmmpxpsme
n = 22: helloworld
n = 23: gdkknvnqkc
n = 24: fcjjmumpjb
n = 25: ebiiltloia

$ java CaesarCipher booboo helloworld
Valid options are "encode" or "decode"

$ java CaesarCipher encode
Incorrect number of parameters

$ java CaesarCipher booboo
Incorrect number of parameters

$ java CaesarCipher
Incorrect number of parameters

$ java CaesarCipher encode a b
Incorrect number of parameters
~~~

Note how the program operates:

1. The `CaeserCipher` program typically takes two parameters on the command line.  An instruction, contained in `args[0]`, should be either `encode` or `decode`.  The parameter, contained in `args[1]`, should be the string to encode or decode.
2. If the user provides an invalid instruction, the program states that the input was invalid (using `stderr`) and exits with a code of 1 (using `System.exit`).
3. If the user provides an incorrect number of parameters, the program states that the input was invalid (using `stderr`) and exits with a code of 2.
4. Otherwise, the program displays all 26 possible ways of encoding or decoding that string.

Your program must follow this format *exactly* and producible *identical* output to the sample execution above.

Here are some assumptions you can make about the user input to simplify the problem:

* The user must enter *exactly* `encode` or `decode` as the first parameter.
  Any other strings can be considered invalid input.
* The string the user provides to be encoded or decoded must consist only of lowercase characters.
  In particular, the string should not contain any whitespace.
  If this input is invalid, your program should report the valid options as described above.

For this program, you will need to use a handful of [String methods and constructors](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html):

* `s.charAt(n)` retrieves the character at the `n`th index of string `s`.
* `s.toCharArray()` creates a character array (*i.e.,* a value of type `char[]`) containing the characters of string `s`.
* `s.length()` returns the length of the string `s`.
* `new String(arr)` creates a new string from the given character array `arr`.

## Part 2: Vigenère Cipher

The Vigenère Cipher is a substitution cipher like the Caesar Cipher.
However, unlike the Caesar Cipher that has a fixed key, the Vigenère Cipher uses a *keyword* to shift the text.

As a concrete example, let's consider the plaintext `helloworld` along with the keyword `cap`.
First, we replicate the keyword to match the length of the plaintext.
Then we use the character value of the first letter of the keyword as the shift value of the first letter of the plaintext, the second keyword letter with the second plaintext letter, and so forth.
In other words, we calculate the resulting ciphertext as follows:

~~~
  h e l l o w o r l d
+ c a p c a p c a p c
---------------------
  j e a n o l q r a f
~~~

So the final ciphertext is `jeanolqraf`.
To decrypt this ciphertext, we simply reverse the process with subtraction:

~~~
  j e a n o l q r a f
- c a p c a p c a p c
---------------------
  h e l l o w o r l d
~~~

As in Part 1, create a program `VigenereCipher` that allows a user to either encode or decode strings, except using the Vigenere Cipher instead of the Caesar Cipher.
THis time, your program will take three parameters: The instruction (`encode` or `decode`), the string to encode or decode, and the keyword.

~~~
$ java VigenereCipher encode helloworld cap
jeanolqraf

$ java VigenereCipher decode jeanolqraf cap
helloworld

$ java VigenereCipher nipnap helloworld cap
Valid options are "encode" or "decode"

$ java VignereCipher encode helloworld ""
helloworld

$ java VigenereCipher encode jeanolqraf yal
helloworld
~~~

You may make the same assumptions about the user input to simplify the problem:

* The user must enter *exactly* `encode` or `decode` for the first parameter.
  Any other strings can be considered invalid input.
* The plaintext/ciphertext string and key the user provides must consist only of lowercase characters.

## Program Design and Decomposition

In this homework (and all work that you do), you should keep an eye out for appropriate *decomposition* of the program into smaller pieces.
In particular, your two programs---CaeserCipher and VigenereCipher---should not do all of their work in `main`!
Each program should utilize at least **two helper methods each** that break up the problem into smaller chunks.


## Grading

When grading homework, we will grade based off of two criteria:

* Internal correctness: does your program function correctly?
* External correctness: does your program follow good design practices?

In particular, the internal correctness portion depends on the following criteria:

1. Whether you used Git appropriately.
   You should have at least *three meaningful commits* in your repository corresponding to saving your intermediate work.
2. Whether you followed the [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html).  
3. Your actual program design: do you have a monolithic main function (bad), or did you appropriately use helper functions to decompose the problem (good)?
4. General program style: does your program have appropriate variable names, indentation, and spacing?

The two components are weighed roughly equally on every homework with some deviations based on the homework content.
Here, we will emphasize *internal correctness* more to encourage you to create good code!

## Credit

Peter-Michael Osera designed the original version of this assignment.
Samuel A. Rebelsky made a variety of changes for spring 2019.
