# Mandatory 1
For the course [INF102 Fall 2018](https://mitt.uib.no/courses/12780)

Deadline: Wednesday October 17, 2018 at 23:59

## Organizational notes

This assignment is an individual task, however you are encouraged to
to collaborate and discuss solutions *as long as you do not share code*
(see our policy on
[Collaboration and Cheating](https://mitt.uib.no/courses/12780/pages/collaboration-and-cheating)).
Similarily, for tasks which are not answered with code,
you may freely discuss your answers with your peers; but clearly,
blindly transcribing answers from others is not
allowed.

The assignment is graded on a scale from 0 to 100,
and accounts for 10% of your final grade.


#### Forbidden libraries

You may not use any external libraries or classes in
your answers that you did not write yourself.
The only exception to this is Kattio.java
and/or another method for fast input/output in Java; in
these cases, you should clearly mark and attribute
the code correctly. And you are still expected to be able
to explain every single line of what the code does.

You may generally use the Java standard library, but with couple of exceptions:
* Any data structure from the standard library that is used
to provide the functionality of a Collection is forbidden.
This includes (but is not limited to):
  * Set, List, Queue, Stack, Dequeue, Vector, ArrayList, LinkedList, 
 TreeSet, PriorityQueue etc.
* Any data structure from the standard library that is used to
provide the functionality of a Map is forbidden.
This includes (but is not limited to):
  * HashMap, TreeMap, Dictionary, HashTable
* Any algorithm from the standard library which implements a sort
of some kind is forbidden.
This includes (but is not limited to)
  * Arrays.sort()
  
In order to use the funcionalities offered by these data structures and algorithms,
you will need to implement them yourself. This includes any functions used for testing.
You may use your own implementations from manadatory 0.



## [0]&nbsp;&nbsp;Organizational instructions (30 points)

Getting the organizational instructions right can be astonishingly difficult.
Therefore, we award a whopping 30 points to everyone who manage to follow all of them!

The number of points drop rapidly, though; for every mistake in following the handin
instructions, your points for this category is *cut in half*. Thus, if you make a single
mistake, you lose 15 points! If you make two mistakes, you lose 15 + 7.5 = 22.5 points!
So follow these instructions carefully.

#### Getting started

 * To start working with the mandatory assignment, clone or download this repository to
 your local machine. The project is in the maven format, so it should be easy to import
 to popular IDE's such as Eclipse and Intellij.
 
 (Intellij note: Before you download, go to Preferences/Settings -> Build, Execution and Deployment -> Build Tools -> Maven -> Importing and select "Import Maven projects automatically")
 * All code you write should reside in the package no.uib.ii.inf102.f18.mandatory1
 * All code should have correct class names as according to the task specification
 
#### Register for the course at Kattis

You should already be done with this section from mandatory 0, but in case you forgot:

 * You need to register an account at Kattis *whose username is your UiB SEBRA ID*.
 For instance, if your UiB id is *abc123*, you should create a user whose username
 is also *abc123*. If you already have a Kattis user, you can change your username
 by going to settings and change it there (you can change it back after the course
 has concluded and you have received your final grade in the course).
 * At https://uib.kattis.com/courses/INF102/fall18 you should click on 
 *I am a student taking this course and I want to register for it on Kattis*, and 
 enter the keyphrase *inf102kattis*.

#### Non-code answers

 * The answer to non-code questions should be contained in a single pdf named `String.format("%s.pdf", yourid)`
 where *yourid* is your UiB SEBRA account id handle (for example, if your UiB id is *abc123*,
 you should name the file `abc123.pdf`). The file should reside in the main folder of your maven project
 (that's the same folder as this README.md).
 

#### Submission

  * After solving the programming assignments and crafting the pdf with textual solutions,
  make a .zip file containing the main directory of your maven project.
  
    * The main directory of the zip file should be named identically to your UiB SEBRA
    account id (for example, if your UiB ID is *abc123*, then your main folder should
    also be named *abc123*).
  
    * The name of your zip file should be `String.format("%s.zip", yourid)`
 where *yourid* is your UiB SEBRA account id handle (for example, if your UiB id is *abc123*,
 you should name the file `abc123.zip`). 
 
    * To accomplish the two points above, it is easiest to copy the contents of your
    main directory to a new folder named *abc123*, and then zip that folder.
    
  * Submit the .zip file at the assignment on mitt.uib before the deadline
  
  * Late assignments will be accepted for 24 hours, with a 20 point penalty.


## [1]&nbsp;&nbsp;Iterative Quick-sort (15 points)

## [2]&nbsp;&nbsp;Indexed Priority Queues (20 points)

Write a class `IndexMinPQ` which implements the interface `IIndexPQ`.

An *indexed priority queue* is one where your heap consists of *indexed* (named) items; for instance, say we have a priority queue of objects, where what you get when you peek or poll is simply the *id* of the object, and not the actual object itself. In order to know how the indices are sorted, each index (/id) is associated with a comparable *key*. The great benefit of this data structure is that it is possible to change the priority of an object if we know its index.

You may use the partial solution from page 333/334 in the book as a starting point (you might need to rename some funcitons to fit the interface).

Some points to keep in mind:

  * Your constructor should take a single argument, the maximum capacity of your indexed priority queue. You are not required to make the class dynamically resizable.
  * Your `poll()` and `peek()` functions should return the index of the *minimum* element in the priority queue (as opposed to the provided `UnorderedIndexPQ` class, which returns the index of the maximum).
  * Your `add()`, `remove()`, `changeKey()` and `poll()` functions should take logarithmic time at worst (as a function of the number of elements in the priority queue)
  * Your `peek()`, `contains()`, `size()`, and `getKey()` functions should take constant time.


## [3]&nbsp;&nbsp;Iterative Binary Search Tree (20 points)

You have heard that recursion is bad for memory usage and might cause stack overflows, so you want to make

## [4]&nbsp;&nbsp;Balanced Binary Search Trees (15 points)

All answers to this section should be answered in the pdf. Some questions may require drawings of trees; we will not judge you by their artistic quality, as long as they are easy to understand. In particular, the root should be on the top, and all nodes at the same distance from the root should be verically aligned (see example). There is a nice tool for drawing these bad boys at [draw.io](https://www.draw.io).

 a) A left-leaning red-black BST is a particular implementation of 2-3 trees, and there is a bijections between illustrations of red-black BST's and a 2-3 trees. Fill in the table below with the missing pictures.
 
 Task | 2-3 tree | Red-black BST
 ---- | -------- | -------------
   i  | ![2-3 tree (A)](https://github.com/torsteins/inf102f18-mandatory1/blob/master/pics/task4-i-23tree.png) | ![red-black tree (A)](https://github.com/torsteins/inf102f18-mandatory1/blob/master/pics/task4-i-redblack.png)
  ii  |   |  
  iii |   |
  iv  |   |
  v   |   |



 
 ##### Good luck!
 
 
### Appendix: Style Guide
 
These rules applies to all source code that is handed in. These guidelines are loosely based on [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html), though with some minor differences.

##### Most important
  * Make your code easy to read.
    * Comment tricky parts of the code.
    * Use descriptive and logical variable names and function names.
    * Break the code into appropriate functions for readablity and code reuse; no function should ideally be more than 30 lines of code (with the exception of extremely monotone code, such as sanity tests).
    * Write javadoc comments for functions that are not self-explanatory
  * All files should use UTF-8 characeter encoding.
  
##### Also important
  * The only whitespace characters allowed are spaces and newlines (tabs are not allowed).
    * Each indention level increase by 4 spaces.
  * No line may exceed 120 characters - lines exceeding this limit must be line-wrapped.
    * Some exceptions, e.g. very long URL's that don't fit on one line.
  * File name should be the same as the name of the class, written in UpperCamelCase.
  * Function names, parameter names and variable names should be written in lowerCamelCase.
  * Constants should be written in ALL_CAPS.
  * No line breaks before open braces (`{`).
  * Blank lines should be used sparingly, but can be used to separate logic blocks and increase readability
  
