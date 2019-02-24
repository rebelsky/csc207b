---
title: Assignment 5
subtitle: Blockchains
copyright:  Copyright &copy; Peter-Michael Osera and Samuel A. Rebelsky.
copyright_message: |
  This work is licensed under a Creative Commons Attribution-NonCommercial
  4.0 International License.  To view a copy of this license, visit
  <http://creativecommons.org/licenses/by-nc/4.0/>.
javadoc: 
  - "[`java.nio.ByteBuffer`]({{ site.java_api }}/java/nio/ByteBuffer.html)"
  - "[`java.security.MessageDigest`]({{ site.java_api }}/java/security/MessageDigest.html)"
link: true
current: true
---
Sequential structures are pervasive in all of computing.
One of the most recent innovations in sequential structures is the [*blockchain*](https://en.wikipedia.org/wiki/Blockchain_(database)) which is a sequence of records built to be highly resistant to change.
Blockchains were first used in 2008 to record transactions for the cryptocurrency [Bitcoin](https://en.wikipedia.org/wiki/Bitcoin).
In this context, the blockchain is a complete history of all Bitcoin transactions ever made, a *public ledger*, replicated, verified, and evolved by many computers a distributed network of machines connected through the Internet.
Since then, blockchains have been used in many other contexts, *e.g.*, other [cryptocurrencies](https://en.wikipedia.org/wiki/Cryptocurrency), crowd-funding and digital rights management, and supply chain management, anywhere where immutable public records of transactions are necessary.

Blockchains, as a *distributed data structure*, require careful coordination between the participating computers on the network.
As this is not a distributed systems course, we will not address these issues directly.
However, the blockchain itself is simply a list with a bit of extra information to ensures its integrity.
In this homework, we will develop a blockchain data structure that will allow us to understand the essential operations that blockchain-based application perform.

## Background

### Hashes

At its core, a blockchain is simply a linked list augmented with *cryptographic hashes* of their contents.
A hash function is a mapping from values of a data type of arbitrary size to a fixed size bit string.
The output of a hash function is called a *hash* which can be thought of as a summary of that data type.
A [cryptographic hash function](https://en.wikipedia.org/wiki/Cryptographic_hash_function) is a hash function that obeys a number of additional properties, most notably:

1. *Resistance to inversion*: given a hash value, it is computational infeasible to find the original value.
2. *Resistance to collisions*: given a hash, it is computational infeasible to find two values that both produce the given hash.
2. *Sensitivity to change*: small changes to a value should result in significant changes to the resulting hash so that it is infeasible to determine an updated value given its hash coupled with the old value and the old value's hash.

Because of these properties, there is no easy way to find a value that produces a hash that meets some arbitrary property, *e.g.*, the hash begins with three zeroes, other than a brute-force search of all the possibilities.
Blockchains exploit this property of cryptographic hashes to maintain the integrity of its recorded transactions.

### Cryptographic hashes in Java

There are many cryptographic hash functions available that strike balances between size of the outputted hash, computational complexity, likelihood of collisions, and other factors.
It is ill-advised to implement your own cryptographic hash functions as they tend to be complicated to implement and difficult to verify.
Instead, you should use some external library from a source that you trust, ideally, from the standard library of your chosen language.

Luckily, Java includes such functionality through its [`MessageDigest`]({{ site.java_api }}/java/security/MessageDigest.html) class.

A `MessageDigest` is an object that encapsulates the process of creating a hash value from some data.
To create a `MessageDigest`, we first create one through the static `getInstance` method of the `MessageDigest` class:

```java
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public static byte[] calculateHash(String msg) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("sha-256");
    // Remaining implementation below...
} // calculateHash(String)
```

Rather than expose a constructor, `MessageDigest` provides a static
*factory method* that creates instances of the class for you.
`getInstance` takes a single argument which is the hash algorithm
to be employed by the object.  In the above example, we create a
`MessageDigest` object that will use the
[SHA-2](https://en.wikipedia.org/wiki/SHA-2) algorithm to perform
hashing.

Finally note some little details about using the `MessageDigest` class:

* The `getInstance` method potentially throws a `NoSuchAlgorithmException` which is a checked exception.
  Because of this, we must add a `throws` clause to the enclosing method to signify that we are not explicitly dealing with this exception.
* Both `MessageDigest` and `NoSuchAlgorithmException` exist within the `java.security` package, so we must add `import` declarations for each class to reference them.

Once we have created a `MessageDigest` object, we can add elements to it that will be hashed with `update`:

```java
    // Continued from above...
    md.update(msg.getBytes());
```

The `update` method takes the data to be hashed as an array of bytes (*i.e.*, a value of type `byte[]`).
Because of this, we must convert our data to bytes.
The way that we do this is specific to each data type.
For a string, we can simply call its `getBytes` method which produces an appropriate `byte[]` from the string.

We can continue adding data to hash to the `MessageDigest` in this fashion---note that the order matters!
Once we are done, we can ask the `MessageDigest` to compute the hash of all the accumulated data with the `digest` method:

```java
    // Still continued from above...
    byte[] hash = md.digest();
    return hash;
}
```

`digest` produces the hash as an array of bytes.
Finally note that this process "consumes" the data that was added to the `MessageDigest` via `update`.
If we wanted to compute another hash with this `MessageDigest` object, we would need to re-add everything.

### Blockchains as linked lists

A blockchain is essentially a linked list where each node contains a *block* of information.
Each block contains:

1. The number of the node in the list.
2. The data contained in the node.
3. The hash of the previous node's block in the list.
4. A *nonce value* which is an integer (explained shortly).
5. The block's own hash value which is computed from the previous four data described previously.

Let's first consider a blockchain that consists of a single block.
Note that the particulars of the data are not relevant for this discussion (other than the fact that we can hash the data), so we'll simply let the data be a string.

```text
   Block 0
==============
data:     "A"
prevHash: none
nonce:    ??
hash:     ??
```

Because this is the first block in the list, it doesn't have a previous hash to refer to, so we'll ignore it for the purposes of hashing.
However, what do we fill in for the `nonce` value of this block so that we can compute its hash?
If our choice of `nonce` value was arbitrary, then our resulting hash value would also be arbitrary and thus would be of little use for verifying anything.

We therefore put an arbitrary restriction on our hash value which only allows us to accept certain hashes into our blockchain.
For this homework, we place the following restriction on our hashes:

* A hash is *valid* for our block chain if it begins with three bytes that are all zeroes.

Thus, we cannot pick any nonce value; we must pick one such that the resulting hash of the block fulfills our validity criteria.
And thanks to the properties of cryptographic hashing, we are forced to brute-force search all possible nonce values to find one that fulfills our criteria!

This process of finding a nonce that, when combined with the rest of the block, produces a valid hash, is called *mining*.
This is because finding such a nonce is very time consuming.
Cryptographic hashing tells us that the only method of doing so is:

* Loop through all the possible nonce values.
* For each candidate nonce, compute the hash of the block with the nonce.
* If the hash is valid, then complete the block with that data.
  Otherwise, keep iterating.

Bitcoin mining is simply the process of finding nonces for new Bitcoin blocks.
The so-called "difficulty" of the mining operation is the restriction placed on valid hashes which scales as more blocks are created.

Returning back to our example, suppose that we add another block to the chain after adding block 0:

```text
   Block 0                 Block 1
=============           =============
data:     "A"           data:     "B"
prevHash: none    <-->  prevHash: 000f6c
nonce:    41209         nonce:    9810
hash:     000f6c        hash:     000ae1
```

Note that block 1 is tied to block 0 not just by a pointer but also by block 0's hash.
This architecture, coupled with the nature of cryptographic hashes, ensures that the data inside the blockchain cannot be tampered with.

For example, suppose a malicious person tried to change block 0's data:

```text
   Block 0               Block 1
=============         =============
data:     "Q"         data:     "B"
prevHash: none  <-->  prevHash: ???
nonce:    ???         nonce:    9810
hash:     ???         hash:     ???
```

Note that by changing the `data` field, someone can check via hashing block 0 again that the resulting hash is not consistent with the data!
Of course, if someone was this malicious, they could also simply calculate the value of the nonce for block 0.
However, since the hash of block 1 depends on block 0, they would also need to re-calculate the nonce for block 1 as well!

Now imagine that our blockchain has grown significantly:

```text
   Block 0                     Block 1279018
=============                  =============
data:     "Q"                  data:     "ZZZZZZZ"
prevHash: none  <--> ... <-->  prevHash: ???
nonce:    ???                  nonce:    410
hash:     ???                  hash:     ???
```

If someone tried to change block 0 now, they need to recalculate the nonces for the entire chain!
This intra-block dependency ensures that the blockchain is *resilient* to changes.
Once a block is set, it is computationally expensive to try to change it, especially if many blocks follow it in the chain.

However, what happens if an attacker manages re-mine the entire block chain?
This is where the distributed nature of a blockchain comes into play.
Recall that there does not exist one blockchain in isolation but many copies of the blockchain spread out on different machines.
We can determine what the *correct* blockchain is by consensus---do the majority of machines on the network have the same chain?
Furthermore, because of the architecture of the blockchain, it is sufficient to check only the *final block's hash* of all the replicas to determine the majority.

## The BlockChain program

In this homework, we will write a program that simulates the development of a blockchain that records the transactions between two parties, call them Anna and Bob.
Initially, Anna starts with a non-negative amount of cash and no new cash is injected into the system.
Each transaction is represented by a single integer that represents a transfer of money between Anna and Bob.

* A *negative* transaction amount corresponds to transferring money from Anna to Bob.
* A *positive* transaction amount corresponds to transferring money from Bob to Anna.

The first block of the chain stores the amount of money that Anna starts with (think of it as an initial transaction from Bob to Anna).
Subsequent blocks of the chain only store the transactions between Anna and Bob rather than the overall amount in the system.
To tell if a new block is valid, we must not only check that its hashes are correct but also the transaction represents a legal exchange of cash given the history of blocks before it.
For example, the following transaction chain is valid:

```
300 --> -150 --> -100 --> 50 --> 100 --> -150
```

However, the following transaction chain is not valid:

```
300 --> -150 -> -100 -> -100 --> 150
```

Because Anna only has 50 units of money available when she tries to transfer an additional 100 units to Bob in the fourth transaction.
In general, we must traverse the entire blockchain to determine if a new transaction is valid, checking that each transaction legally follows from the previous one.

### A Hash class

While a hash is a byte array, it is convenient to write a *wrapper class* that wraps up a byte array along with some operations we would like to perform on it.
Write a class called `Hash` with the following `public` methods:

* `Hash(byte[] data)`: constructs a new `Hash` object that contains the given hash (as an array of bytes).
* `byte[] getData()`: returns the hash contained in this object.
* `boolean isValid()`: returns true if this hash meets the criteria for validity, *i.e.*, its first three indices contain zeroes.
* `String toString()`: returns the string representation of the hash as a string of hexadecimal digits, 2 digits per byte.
* `boolean equals(Object other)`: returns true if this hash is structurally equal to the argument.

To implement `toString()`, you will find the following static methods useful:

* [`Byte.toUnsignedInt`]({{ site.java_api }}/java/lang/Byte.html#toUnsignedInt-byte-)
* [`String.format`]({{ site.java_api }}/java/lang/String.html#format-java.lang.String-java.lang.Object...-)

The conversion method `Byte.toUnsignedInt` is necessary because all integral values in Java are signed.
We do not want values past 127 to be interpreted as negative numbers, *e.g.*, if we have the bit pattern `11111111`, we want to interpret this as the value `255` not `-128`.
`String.format` works like `sprintf` which acts like `printf` but writes its output to a string.
You should use a format specifier that prints an integer to the screen in hexadecimal using two digits; read the documentation to discover what this format specifier is.

The `equals` method should check to see if:

1. `other` is an instance of `Hash` using the `instanceof` operator.
2. If so, it should *cast* `other` to type `Hash`, *e.g.*, `Hash o = (Hash) other` and then use the [`Arrays.equals` static method]({{ site.java_api }}/java/util/Arrays.html#equals-byte:A-byte:A-) to perform the appropriate equality check on the two `Hash` object's arrays.

### A Block class

Next, you should create a separate class for the data contained in each node of the blockchain.
Recall that a block contains:

* The number of the block in the blockchain.
* The data, *i.e.*, the amount transferred between the two parties represented as a single integer.
* The hash of the previous block in the chain.
* The nonce.
* The hash of this block.

Note that a block itself does not contain links to other blocks in the chain.
The block will be wrapped in a standard `Node` class that will contain the actual links instead.

Write a class called `Block` with the following `public` methods:

* `Block(int num, int amount, Hash prevHash)`: creates a new block from the specified parameters, performing the mining operation to discover the nonce and hash for this block given these parameters.
* `Block(int num, int amount, Hash prevHash, long nonce)`: creates a new block from the specified parameters, using the provided nonce and additional parameters to generate the hash for the block.
  Because the nonce is provided, this constructor does not need to perform the mining operation; it can compute the hash directly.
* `int getNum():` returns the number of this block.
* `int getAmount()`: returns the amount transferred that is recorded in this block.
* `long getNonce():` returns the nonce of this block.
* `Hash getPrevHash()`: returns the hash of the previous block in the blockchain.
* `Hash getHash()`: returns the hash of this block.
* `String toString()`: returns a string representation of the block (see below).

The string representation of a `Block` should be formatted as follows:

```text
Block <num> (Amount: <amt>, Nonce: <nonce>, prevHash: <prevHash>, hash: <hash>)
```

For example,

```text
Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
```

To mine for a given block's nonce, you will need to use the `MessageDigest` class as described in the background section of this homework.
To convert integers and longs into byte arrays, use the [`ByteBuffer` class]({{ site.java_api }}/java/nio/ByteBuffer.html) which allows you to store data types as arrays of bytes.
You will need to use the following methods of the `ByteBuffer` class:

* `ByteBuffer.allocate(int size)`: creates a `ByteBuffer` of the given size in bytes.
* `ByteBuffer putInt(int val)`, `ByteBuffer putLong(long val)`: puts the given value into the `ByteBuffer`.
* `byte[] array()`: extracts the bytes of the given `ByteBuffer` to a byte array.

Note that the `putInt` class returns the `ByteBuffer` that the method was called on.
This is to enable a style of method calls called "method chaining".
For example, here is an example of produce a byte array from an integer `x`:

```java
ByteBuffer.allocate(4).putInt(x).array()
```

To mine a block, you should update a `MessageDigest` object with the following properties of the block:

1. The block's number.
2. The data contained in the block.
3. The hash of the previous node's block in the list if it exists.
4. The discovered nonce value.

Note that when you mine the first block, there will be no previous hash, so you can skip adding such a value to the `MessageDigest` object.

The value of the resulting hash may be different depending on the order in which you hash values.
For consistency's sake with our testing suite, **ensure that you update the `MessageDigest`** object in the order specified above.
For the first block, you should not hash anything for the previous hash value (step 3).
Furthermore, you should also search the space of nonces by searching all possible longs in increasing order starting at zero.

### The BlockChain class

Use `Block` to implement a `BlockChain` class which is a singly-linked structure with a `first` and `last` pointer (*i.e.*, pointer to the first and last elements in the structure, much like in a queue).
`BlockChain` should have a nested `Node` static class in the standard style of linked structure implementation in Java.
`BlockChain` should contain the following methods:

* `BlockChain(int initial)`: creates a `BlockChain` that possess a single block the starts with the given initial amount.
  Note that to create this block, the `prevHash` field should be ignored when calculating the block's own nonce and hash.
* `Block mine(int amount)`: mines a new candidate block to be added to the end of the chain.
  The returned `Block` should be valid to add onto this chain.
* `int getSize()`: returns the size of the blockchain.
  Note that number of the blocks provides a convenient method for quickly determining the size of the chain.
* `void append(Block blk)`: adds this block to the list, throwing an `IllegalArgumentException` if this block cannot be added to the chain (because it is invalid wrt the rest of the blocks).
* `boolean removeLast()`: removes the last block from the chain, returning `true`.  If the chain only contains a single block, then `removeLast` does nothing and returns `false`.
* `Hash getHash()`: returns the hash of the last block in the chain.
* `boolean isValidBlockChain()`: walks the blockchain and ensures that its blocks are consistent and valid.
* `void printBalances()`: prints Alice's and Bob's respective balances in the form `Alice: <amt>, Bob: <amt>` on a single line, *e.g.*, `Alice: 300, Bob: 0`.
* `String toString()`: returns a string representation of the `BlockChain` which is simply the string representation of each of its blocks, earliest to latest, one per line.

Note that our `Block` class only generates hashes from its arguments, so we know by construction that the hash of a block is consistent with its data and that it is valid.
However `isValidBlockChain` must still ensure that the blocks of the chain represents a valid series of transactions by traversing the chain.

### A BlockChainDriver class

Finally we'll create a program, `BlockChainDriver`, which allows us to interact with our `BlockChain`.
`BlockChainDriver` should contain the `main` method of your program.
Your program should take a single command-line argument that is the initial non-negative dollar amount that Anna starts with.
It then allows the user to interactively manipulate the blockchain and mine for additional blocks through a text-driven interface.
The program creates a blockchain with the initial dollar amount and then repeatedly:

1. Prints out the contents of the blockchain.
2. Reads in a command from the user.
3. Executes that command, potentially updating the blockchain and reporting back to the user.

The commands your program should support are:

```text
Valid commands:
    mine: discovers the nonce for a given transaction
    append: appends a new block onto the end of the chain
    remove: removes the last block from the end of the chain
    check: checks that the block chain is valid
    report: reports the balances of Alice and Bob
    help: prints this list of commands
    quit: quits the program
```

The various commands map directly onto `BlockChain` operations that you already implemented.
Note that `mine` is separate from `append`---you must first mine a candidate block with `mine` to discover an appropriate nonce and then `append` that block along with the discovered nonce.
This mimics more closely how Bitcoin works in real-life: miners race to discover blocks may collectively discover several nonces that work.
However, only one such nonce is eventually appended onto the blockchain.

`mine` and `append` take additional arguments---the transaction amount for `mine` and the transaction amount and nonce for `append`.
Your program should prompt for these values individually.

Like previous assignments, your program should mimic the output of the examples below.
In the case of invalid inputs, *e.g.*, invalid commands, your program should report sensible error messages and continue execution.
Note that depending on how you discover your nonce values, the nonce and hash values of your blocks may be different from these examples.

**Sample execution #1**

```text
$> java BlockChainDriver 300
Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Command? help
Valid commands:
    mine: discovers the nonce for a given transaction
    append: appends a new block onto the end of the chain
    remove: removes the last block from the end of the chain
    check: checks that the block chain is valid
    report: reports the balances of Alice and Bob
    help: prints this list of commands
    quit: quits the program

Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Command? mine
Amount transferred? -150
amount = -150, nonce = 2016357

Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Command? append
Amount transferred? -150
Nonce? 2016357

Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Block 1 (Amount: -150, Nonce: 2016357, prevHash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7, hash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26)
Command? mine
Amount transferred? -100
amount = -100, nonce = 9775906

Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Block 1 (Amount: -150, Nonce: 2016357, prevHash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7, hash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26)
Command? append
Amount transferred? -100
Nonce? 9775906

Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Block 1 (Amount: -150, Nonce: 2016357, prevHash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7, hash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26)
Block 2 (Amount: -100, Nonce: 9775906, prevHash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26, hash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639)
Command? mine
Amount transferred? 50
amount = 50, nonce = 18418284

Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Block 1 (Amount: -150, Nonce: 2016357, prevHash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7, hash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26)
Block 2 (Amount: -100, Nonce: 9775906, prevHash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26, hash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639)
Command? append
Amount transferred? 50
Nonce? 18418284

Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Block 1 (Amount: -150, Nonce: 2016357, prevHash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7, hash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26)
Block 2 (Amount: -100, Nonce: 9775906, prevHash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26, hash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639)
Block 3 (Amount: 50, Nonce: 18418284, prevHash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639, hash: 000000b96183e5caab9534bfded11fbf022d03de1cbf2a08bbdbed24882c9d4a)
Command? mine
Amount transferred? 100
amount = 100, nonce = 20197478

Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Block 1 (Amount: -150, Nonce: 2016357, prevHash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7, hash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26)
Block 2 (Amount: -100, Nonce: 9775906, prevHash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26, hash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639)
Block 3 (Amount: 50, Nonce: 18418284, prevHash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639, hash: 000000b96183e5caab9534bfded11fbf022d03de1cbf2a08bbdbed24882c9d4a)
Command? append
Amount transferred? 100
Nonce? 20197478

Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Block 1 (Amount: -150, Nonce: 2016357, prevHash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7, hash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26)
Block 2 (Amount: -100, Nonce: 9775906, prevHash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26, hash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639)
Block 3 (Amount: 50, Nonce: 18418284, prevHash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639, hash: 000000b96183e5caab9534bfded11fbf022d03de1cbf2a08bbdbed24882c9d4a)
Block 4 (Amount: 100, Nonce: 20197478, prevHash: 000000b96183e5caab9534bfded11fbf022d03de1cbf2a08bbdbed24882c9d4a, hash: 0000005092160ebaa679544c3432112e766d4b6369c77c1a23939fc887c6e6e8)
Command? mine
Amount transferred? -150
amount = -150, nonce = 39522567

Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Block 1 (Amount: -150, Nonce: 2016357, prevHash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7, hash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26)
Block 2 (Amount: -100, Nonce: 9775906, prevHash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26, hash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639)
Block 3 (Amount: 50, Nonce: 18418284, prevHash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639, hash: 000000b96183e5caab9534bfded11fbf022d03de1cbf2a08bbdbed24882c9d4a)
Block 4 (Amount: 100, Nonce: 20197478, prevHash: 000000b96183e5caab9534bfded11fbf022d03de1cbf2a08bbdbed24882c9d4a, hash: 0000005092160ebaa679544c3432112e766d4b6369c77c1a23939fc887c6e6e8)
Command? append
Amount transferred? -150
Nonce? 39522567

Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Block 1 (Amount: -150, Nonce: 2016357, prevHash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7, hash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26)
Block 2 (Amount: -100, Nonce: 9775906, prevHash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26, hash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639)
Block 3 (Amount: 50, Nonce: 18418284, prevHash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639, hash: 000000b96183e5caab9534bfded11fbf022d03de1cbf2a08bbdbed24882c9d4a)
Block 4 (Amount: 100, Nonce: 20197478, prevHash: 000000b96183e5caab9534bfded11fbf022d03de1cbf2a08bbdbed24882c9d4a, hash: 0000005092160ebaa679544c3432112e766d4b6369c77c1a23939fc887c6e6e8)
Block 5 (Amount: -150, Nonce: 39522567, prevHash: 0000005092160ebaa679544c3432112e766d4b6369c77c1a23939fc887c6e6e8, hash: 0000008734f8ff6f20a1c49b428d7892d2f8108a103e03d53abeb01ce048ec85)
Command? check
Chain is valid!

Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Block 1 (Amount: -150, Nonce: 2016357, prevHash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7, hash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26)
Block 2 (Amount: -100, Nonce: 9775906, prevHash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26, hash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639)
Block 3 (Amount: 50, Nonce: 18418284, prevHash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639, hash: 000000b96183e5caab9534bfded11fbf022d03de1cbf2a08bbdbed24882c9d4a)
Block 4 (Amount: 100, Nonce: 20197478, prevHash: 000000b96183e5caab9534bfded11fbf022d03de1cbf2a08bbdbed24882c9d4a, hash: 0000005092160ebaa679544c3432112e766d4b6369c77c1a23939fc887c6e6e8)
Block 5 (Amount: -150, Nonce: 39522567, prevHash: 0000005092160ebaa679544c3432112e766d4b6369c77c1a23939fc887c6e6e8, hash: 0000008734f8ff6f20a1c49b428d7892d2f8108a103e03d53abeb01ce048ec85)
Command? quit
```

**Sample execution #2**

```text
$> java BlockChainDriver 300
Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Command? mine
Amount transferred? -150
amount = -150, nonce = 2016357

Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Command? append
Amount transferred? -150
Nonce? 2016357

Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Block 1 (Amount: -150, Nonce: 2016357, prevHash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7, hash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26)
Command? mine
Amount transferred? -100
amount = -100, nonce = 9775906

Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Block 1 (Amount: -150, Nonce: 2016357, prevHash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7, hash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26)
Command? append
Amount transferred? -100
Nonce? 9775906

Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Block 1 (Amount: -150, Nonce: 2016357, prevHash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7, hash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26)
Block 2 (Amount: -100, Nonce: 9775906, prevHash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26, hash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639)
Command? report
Alice: 50, Bob: 250

Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Block 1 (Amount: -150, Nonce: 2016357, prevHash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7, hash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26)
Block 2 (Amount: -100, Nonce: 9775906, prevHash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26, hash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639)
Command? check
Chain is valid!

Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Block 1 (Amount: -150, Nonce: 2016357, prevHash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7, hash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26)
Block 2 (Amount: -100, Nonce: 9775906, prevHash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26, hash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639)
Command? mine
Amount transferred? -100
amount = -100, nonce = 46114774

Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Block 1 (Amount: -150, Nonce: 2016357, prevHash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7, hash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26)
Block 2 (Amount: -100, Nonce: 9775906, prevHash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26, hash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639)
Command? append
Amount transferred? -100
Nonce? 46114774

Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Block 1 (Amount: -150, Nonce: 2016357, prevHash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7, hash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26)
Block 2 (Amount: -100, Nonce: 9775906, prevHash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26, hash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639)
Block 3 (Amount: -100, Nonce: 46114774, prevHash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639, hash: 000000ebdb44ee8f76b3b9c7c4e06e1eb6e6f75a764e91defd986e1955b6c8c1)
Command? report
Alice: -50, Bob: 350

Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Block 1 (Amount: -150, Nonce: 2016357, prevHash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7, hash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26)
Block 2 (Amount: -100, Nonce: 9775906, prevHash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26, hash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639)
Block 3 (Amount: -100, Nonce: 46114774, prevHash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639, hash: 000000ebdb44ee8f76b3b9c7c4e06e1eb6e6f75a764e91defd986e1955b6c8c1)
Command? check
Chain is invalid!

Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Block 1 (Amount: -150, Nonce: 2016357, prevHash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7, hash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26)
Block 2 (Amount: -100, Nonce: 9775906, prevHash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26, hash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639)
Block 3 (Amount: -100, Nonce: 46114774, prevHash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639, hash: 000000ebdb44ee8f76b3b9c7c4e06e1eb6e6f75a764e91defd986e1955b6c8c1)
Command? remove

Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Block 1 (Amount: -150, Nonce: 2016357, prevHash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7, hash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26)
Block 2 (Amount: -100, Nonce: 9775906, prevHash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26, hash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639)
Command? report
Alice: 50, Bob: 250

Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Block 1 (Amount: -150, Nonce: 2016357, prevHash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7, hash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26)
Block 2 (Amount: -100, Nonce: 9775906, prevHash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26, hash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639)
Command? check
Chain is valid!

Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Block 1 (Amount: -150, Nonce: 2016357, prevHash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7, hash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26)
Block 2 (Amount: -100, Nonce: 9775906, prevHash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26, hash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639)
Command? mine
Amount transferred? 150
amount = 150, nonce = 20940595

Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Block 1 (Amount: -150, Nonce: 2016357, prevHash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7, hash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26)
Block 2 (Amount: -100, Nonce: 9775906, prevHash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26, hash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639)
Command? append
Amount transferred? 150
Nonce? 20940595

Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Block 1 (Amount: -150, Nonce: 2016357, prevHash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7, hash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26)
Block 2 (Amount: -100, Nonce: 9775906, prevHash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26, hash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639)
Block 3 (Amount: 150, Nonce: 20940595, prevHash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639, hash: 000000ebae09bbc80b2aba3f924cd6fc7eb7b2662e4ca3c31cc5a373d3669657)
Command? report
Alice: 200, Bob: 100

Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Block 1 (Amount: -150, Nonce: 2016357, prevHash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7, hash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26)
Block 2 (Amount: -100, Nonce: 9775906, prevHash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26, hash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639)
Block 3 (Amount: 150, Nonce: 20940595, prevHash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639, hash: 000000ebae09bbc80b2aba3f924cd6fc7eb7b2662e4ca3c31cc5a373d3669657)
Command? check
Chain is valid!

Block 0 (Amount: 300, Nonce: 9324351, prevHash: null, hash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7)
Block 1 (Amount: -150, Nonce: 2016357, prevHash: 000000201f6c32c24b52b8a5b7d664af23e7db950af8867dbe800eb5c40c30a7, hash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26)
Block 2 (Amount: -100, Nonce: 9775906, prevHash: 000000d744da56bb0f9a87737a7491b557d49f502d0e375678ca160143986c26, hash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639)
Block 3 (Amount: 150, Nonce: 20940595, prevHash: 000000e6a62e93665765ae029cedac2016ac6da4be94b096c625cd1bef24a639, hash: 000000ebae09bbc80b2aba3f924cd6fc7eb7b2662e4ca3c31cc5a373d3669657)
Command? quit
```

## Questions and answers

_A place for Sam to log the questions he gets about this assignment and
the answers he develops._

## Acknowledgements

This assignment comes from materials developed by Peter-Michael Osera
and Anya Vostinar.  Samuel A. Rebelsy made a few minor updates, including
the new Q&A section.

