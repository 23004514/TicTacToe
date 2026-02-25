TicTacToe
=========

Usage
-----

Interactive:

```ps1
cd "c:/Users/User/OneDrive/Pictures/Documents/GitHub/TicTacToe/TicTacToe"
javac -d build/classes src/*.java
java -cp build/classes TicTacToe
```

Non-interactive (provide moves as arguments — row and column pairs):

```ps1
java -cp build/classes TicTacToe 1 1 1 2 2 1 2 2 3 1
```

Each number is a 1-based row or column. Example above plays a short game where `X` wins the first column.

Test
----

A simple test harness is available as `TicTacToeTest`:

```ps1
javac -d build/classes src/*.java
java -cp build/classes TicTacToeTest
```

GUI
---

Run the Swing GUI:

```ps1
java -cp build/classes TicTacToeGUI
```
