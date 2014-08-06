StringRex
=========

A GUI program which generates all strings that match a given regular expression. Useful to create a wordlist to recover a partially known password. It's based on the [Generex](https://github.com/mifmif/Generex) library.

![StringRex main window](/../gh-pages/images/screenshot.png?raw=true)

Building
========

You can use the bundled gradle distribution to build.

Unix based:

```
./gradlew build
```

Windows:

```
gradlew.bat build
```

Use the `fatjar` target, instead of `build` to create an executable jar which is started through `java -jar StringRex-0.1.jar` (or doubleclicking on most systems). 

Usage
=====

Enter the regular expression and press "Generate" to create the list of matches strings. Use the "Save to file"-Button to write a word list.

Limitations
===========

The used RegEx syntax is described in the [javadoc for the RegExp class](http://www.brics.dk/automaton/doc/index.html?dk/brics/automaton/RegExp.html). Note that it may differ from the syntax you are used to.

**Warning** There is no limitation on the complexity of your expression or the size of the result. Be careful when designing your RegEx, generating all matched strings may require a lot of memory/time.
