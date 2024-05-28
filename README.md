# Alternative-Language-Project

![1](/Kotlin%20Full%20Color%20Logo%20on%20Black%20RGB.png)

**Which programming language and version did you pick?**

I picked the programming language Kotlin, using the most recent version as of this report (223-1.8.0-release-345-IJ8617.56).

**Why did you pick this programming language?**

Kotlin is an object-oriented programming language based on Java, it even compiles to bytecode using the JDK. It aims to tackle common issues with Java, such as boilerplate code, verbosity, and strong typing. In a way, it cna be seen as an alternate interface to using Java, one that focusing on simplifying and speeding up development. Its a popular choice in Android development, with mobile development being an appealing career path thanks to the high compensation mobile developers make. The language acts as a good jumping-off point since it offers a new take on object-oriented programming and on Java itself, which is currently my preferred language to develop in. It's similar to Java under the hood, but the syntax has a distinct focus on simplicity and ease of development, solving a lot of pain points I've had when working with Java. I'm interested in seeing whether this becomes my new goto language.

**How your programming language chosen handles: object-oriented programming, file ingestion, conditional statements, assignment statements, loops, subprograms (functions/methods), unit testing and exception handling. If one or more of these are not supported by your programming language, indicate it as so.**

Object-oriented programming in Kotlin uses a system of classes to create objects, which can contain variables, methods, and constructors. Inheritance and polymorphism are possible through interfaces, implementation, and overrides, allowing developers to reduce redundancy be reusing and altering existing code. File ingestion can be handled in a multitude of ways, such as file input streams, buffered readers, and more. Generally, the choice comes in how to process the data in a file, whether that be the entire file at once, each line of the file, or each token in the file based on a delimiter. Conditional statements are written with a header composed of a keyword, a condition in parentheses, and the body of the condition, denoted with curly braces. Of note, unlike in Java, newlines indicate the end of lines in Kotlin, making semicolons unnecessary for denoting them (but they can optionally be used). Assignment statements differ from their Java equivalents. Assignments statements start with a either `var` or `val`, indicating whether a piece of data is mutable or not respectively. Access modifiers can optionally be used at the beginning to denote the scope of the data. The data can then be initialized either by choosing a type or assigning a value, both can be done at once but it is redundant. `private val count: Int` Loops follow the same structure as conditional statements. There condition has multiple formats available, double periods can denote a range between an inclusive and exclusive value `for (0..5) { ... }`, `for each` loops start with a variable, the keyword in, and the object to iterate through. `for (book in library) { ... }` Subprograms also follow a similar structure, they begin with the keyword `fun`, a name for the function, the parameters in parentheses, and any return values after a colon, with a body denoted by curly braces. For exception handling, when using JUnit, besides the aforementioned syntax, tests are conducted the same way, using assert methods to test for equality, null values, boolean values, and so on `assertEquals(expected, actual)`. Test methods are written the same as production methods, but are denoted with `@Test` the line before. Exception handling is also very similar to Java, exceptions can be handled in a variety of ways, most commonly through a `try { ... } catch () { ... }` block. There can be zero ore more `catch` blocks, and a `finally` block can also be used, at least one of the two must be present. [2]

**List out 3 libraries you used from your programming language (if applicable) and explain what they are, why you chose them and what you used them for.**

`java.io.FileInputStream`
Reads a file in a file system, returning a stream of bytes. Most commonly used for reading raw bytes such as reading an image but can be used for other file types. [3]

`kotlin.collections.ArrayList`
Implementation of the List interface. Includes Array operations but with additional features such as automatic resizing and new list operations.

`kotlin.collections.HashMap`
Implementation of the Map interface. Includes all Map operations and allows for null keys and values. Similar to the Hashtable class, but it is synchronized and allows using nulls. The order of of the map may not be consistent. [2]

**Answer the following questions (and provide a corresponding screen showing output answering them):**

![screen output](/screenshots/Screenshot%20(360).png)

* **What company (oem) has the highest average weight of the phone body?**

HP

* **Was there any phones that were announced in one year and released in another? What are they? Give me the oem and models.**

OEM: Motorola, Model: One Hyper

OEM: Motorola, Model: Razr 2019

OEM: Xiaomi, Model: Redmi K30 5G

* **How many phones have only one feature sensor?**

419

* **What year had the most phones launched in any year later than 1999?**

2019

**References:**

[1] “Kotlin brand assets | Kotlin,” Kotlin Help.

[2] “Kotlin docs | Kotlin,” Kotlin Help.

[3] Oracle, “Java Documentation,” Oracle Help Center.