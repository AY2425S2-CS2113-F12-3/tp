# MindExpander User Guide

## Table of Contents

1. **[Introduction](#introduction)**
2. **[Quick Start](#quick-start)**
3. **[Features](#features)**
    - [Viewing the help sheet: `help`](#viewing-the-help-sheet-help)
    - [Adding a question: `add`](#adding-a-question-add)
    - [Listing questions added: `list`](#listing-questions-added-list)
    - [Finding questions with a specified string: `find`](#finding-questions-with-a-specified-string-find)
    - [Solving questions: `solve`](#solving-questions-solve)
    - [Editing questions: `edit`](#editing-questions-edit)
    - [Deleting a question: `delete`](#deleting-a-question-delete)
    - [Showing the answer to a question: `show`](#showing-the-answer-to-a-specific-question-show)
    - [Clearing all questions: `clear`](#clearing-all-questions-clear)
    - [Undoing and redoing: `undo`/`redo`](#undoredo-a-command-that-modifies-the-question-bank-undoredo)
    - [Exiting the program: `exit`](#exiting-the-program-exit)
4. **[Saving and Loading](#saving-and-loading-of-data)**
5. **[Logged Data](#logged-data)**
6. **[Additional Notes](#additional-notes-for-program-features-and-usage)**
7. **[FAQ](#faq)**
8. **[Command Summary](#command-summary)**

## Introduction

MindExpander is a desktop application designed for Primary School students to practice Mathematics, Science and English
questions. It features a simple Command Line Interface (CLI), allowing users to efficiently add, list, and solve 
questions. The app provides a structured and interactive way for students to reinforce their learning through guided 
commands. With built-in saving and loading capabilities, users can seamlessly continue their practice sessions anytime.

<div style="page-break-after: always;"></div>

## Quick Start

1. Ensure that you have Java 17 or above installed.
2. Down the latest version of `MindExpander` from [here](https://github.com/AY2425S2-CS2113-F12-3/tp/releases/tag/v2.1).
3. Open a command terminal.
4. `cd` into the folder containing the jar file.
5. Use the command `java -jar MindExpander.jar` to run the application. Upon start up, you should see:

![img.png](img.png)
## Features
The list of features and how to use them can be found below.
User input lines will be of the form `[USER_INPUT]`.
For example, an input which requires a question number from the user will be of the form `[QUESTION_NUMBER]`.
For features with multiple separate inputs, the different inputs will be separated by a `|` symbol. For example,
an input with 3 different input steps will be of the form `STEP 1` `|` `STEP 2` `|` `STEP 3`.

"Last shown list" refers to the list that was last shown to the user. This list will be used for commands which
require a specified question index to carry out the command, more details in the commands 
[List](#listing-questions-added-list) and in [Find](#finding-questions-with-a-specified-string-find).


<div style="page-break-after: always;"></div>



### Viewing the help sheet: `help`
Displays a list of the available commands a brief description of what they do. It can also be used to view the
detailed instructions for each command by adding the command name behind.

**To view list of commands**
This prints a list of commands that the program recognises with a short one line description for each one.

Format: `help` 

Example usage:

`help`

**To view instructions for specific command**

Format: `help [COMMAND]`

Example usage:

`help add`

This prints the usage instructions for the command `add`.

<div style="page-break-after: always;"></div>

### Adding a question: `add`
Adds a question to the question bank. Follows a series of steps which require separate inputs each.

Format: `add` `|` `[QUESTION_TYPE]` `|` `[QUESTION_DETAILS]` `|` `[QUESTION_ANSWER]` `|` `[INCORRECT_OPTIONS`]

`[QUESTION_TYPE]`: The type of the question to be added.

`[QUESTION_DETAILS]`: The question itself.

`[QUESTION_ANSWER]`: The answer to the question.

`[INCORRECT_OPTIONS]` The incorrect options (only needed in adding multiple choice questions).

### Supported types:
- `FITB`: Fill in the Blanks
- `MCQ`: Multiple Choice Question
- `TF`: True/False (answer must be either `true` or `false`, case-insensitive)

Example usage for `FITB` questions:
- `add`

- `fitb`

- `what is 2 + 2?`

- `4`

Example usage for `TF` questions:
- `add`

- `tf`

- `is water wet`

- `true`

**Note**
* To maintain a clean and meaningful question bank, duplicate questions are not allowed, even across different question types
  (e.g., FITB and MCQ). Duplicate detection is based solely on the question content, not the question type or format.

  For example, the following two questions would be considered duplicates and cannot coexist:

    - FITB: Which continent is France located?

    - MCQ: Which continent is France located?  
      A. Europe  
      B. Asia  
      C. North America  
      D. Africa

  Even though the formats differ, the core question is identical and storing both is redundant.
  Case-insensitive comparison is used when checking for duplicates. That means:
  test q and Test Q are treated as the same question.
* Fill-in-the-Blank (FITB) questions should be designed to have a single, unambiguous answer.
  Avoid questions that can have multiple correct responses — for example:
  - ✖ What are the roots of (x − 2)(x + 2) = 0? (Answer could be 2 or -2)
  
  Instead, rephrase the question to clearly specify which answer is expected. For example:
  - ✔ What is the positive root of (x − 2)(x + 2) = 0?
* Input is case-insensitive and trimmed. For example, `   TRUE   ` and `false` are both accepted.
* MCQ questions have 4 options including the correct answer. When prompted to enter the incorrect answers, only input the **incorrect**
options one at a time.
* Invalid TF answers will prompt the user to re-enter until `true` or `false` is provided.

<div style="page-break-after: always;"></div>


### Listing questions added: `list`
Lists all the questions currently in the question bank. Running this will change the last shown list to be the full list
of questions in the question bank again. List can be used to show the question list with or without answers.

List can also be run with additional question type parameters, to display questions only from that specific question type.

**To show the list of questions without answers**

Format: `list`

Example usage:

`list`

Example output:

```
==============================
[Command you entered: list]
==============================
==============================
Here are the questions you have currently:
==============================
==============================
1. MCQ: 1 + 1
A. 400
B. 2
C. 49
D. 22123
2. FITB: Where am I?
3. TF: Am I studying? (True/False)
==============================
```

**To show the list of MCQ/FITB/TF questions without answers**

Format `list [mcq/fitb/tf]`

Example usage:

`list mcq`

Example output:

```
==============================
[Command you entered: list mcq]
==============================
==============================
Here are the MCQ questions you have currently:
==============================
==============================
1. MCQ: 1 + 1
A. 2
B. 400
C. 22123
D. 49
==============================
```

**To show the list of questions with answers**

Format: `list answer`

Example usage:

`list answer`

Example output:

```
==============================
[Command you entered: list answer]
==============================
==============================
Here are the questions you have currently:
==============================
==============================
1. MCQ: 1 + 1 [Answer: 2]
2. FITB: Where am I? [Answer: NUS]
3. TF: Am I studying? [Answer: false]
==============================
```

**To show the list of MCQ/FITB/TF questions with answers**

Format `list [mcq/fitb/tf] answer`

Example usage:

`list tf answer`

Example output:

```
==============================
[Command you entered: list tf answer]
==============================
==============================
Here are the TF questions you have currently:
==============================
==============================
1. TF: Am I studying? [Answer: false]
==============================
```

<div style="page-break-after: always;"></div>


### Finding questions with a specified string: `find`
Finds all questions currently in the question bank that contain a specific keyword. Running this will change the last shown list to be the list of
questions which match the user's search query in the question bank. Find can be used to search for all question types,
or specifically for MCQ, FITB and TF questions

**To search for all questions containing `KEYWORD`**

Format: `find [KEYWORD]`

Example usage:

`find MRT`

Example output:

```
==============================
Here are the questions with MRT:
==============================
==============================
1. FITB: What is the closest MRT to NUS?
2. MCQ: What MRT station on EWL is closest to NUS?
A. Clementi MRT
B. Buona Vista MRT
C. Dover MRT
D. Redhill MRT
3. TF: Lakeside MRT is the best.
==============================
```

**To search for all MCQ questions containing `KEYWORD`**

Format: `find mcq [KEYWORD]`

Example usage:

`find mcq MRT`

Example output:

```
==============================
[Command you entered: find MRT]
==============================
==============================
Here are the questions with MRT:
==============================
==============================
1. FITB: What is the closest MRT to NUS?
2. MCQ: What MRT station on EWL is closest to NUS?
A. Buona Vista MRT
B. Redhill MRT
C. Clementi MRT
D. Dover MRT
3. TF: Lakeside MRT is the best. (True/False)
==============================
```

**To search for all FITB questions containing `KEYWORD`**

Format: `find fitb [KEYWORD]`

Example usage:

`find fitb MRT`

Example output:

```
==============================
[Command you entered: find fitb MRT]
==============================
==============================
Here are the FITB questions with MRT:
==============================
==============================
1. FITB: What is the closest MRT to NUS?
==============================
```

**To search for all TF questions containing `KEYWORD`**

Format: `find tf [KEYWORD]`

Example usage:

`find tf MRT`

Example output:

```
==============================
[Command you entered: find tf MRT]
==============================
==============================
Here are the TF questions with MRT:
==============================
==============================
1. TF: Lakeside MRT is the best. (True/False)
==============================
```

<div style="page-break-after: always;"></div>


### Solving questions: `solve`
Solves a question that was previously added to the question bank.
It is recommended to run `list` before `solve` to check the index of the question you intend to solve.
If one runs `find` before `solve`, the list used for the available questions and question indexes will be what is
displayed by the `find` command, i.e. the last shown list.
    
* For example, if `find 1+` has
  > 1. FITB: What is 1+1?
  > 2. FITB: What is 1+2?
* Question Index 1 will be the question "What is 1+1?".

Format: `solve [QUESTION INDEX]` `|` `[QUESTION ANSWER]` `|` `[Y/N]` (only if wrong)  
`[QUESTION_INDEX]`: The question number of the question to be solved, according to the last shown list.  
`[QUESTION_ANSWER]`: The answer to the question.  
`[Y/N]`: Y or N depending on whether you want to keep attempting the question or not.  

Example usage:
These examples are for a FITB question 2, "What are fries made of?" with the correct answer "Potato".

Correct answer example and outputs
1. `solve 2`

```
==============================
Attempting question 2: FITB: What are fries made of? Enter your answer:
==============================
```

2. `Potato`

```
==============================
Correct!
==============================
```

Wrong answer example and outputs
1. `solve 2`

```
==============================
Attempting question 2: FITB: What are fries made of? Enter your answer:
==============================
```

2. `Cheese`

```
==============================
Wrong answer, would you like to try again? [Y/N]
==============================
```

3. `N`

```
==============================
Giving up on question.
==============================
```

OR

4. `Y`

```
==============================
Enter your answer to try again:
==============================
```

These examples are for an MCQ question 2 "What are fries made of?" with the correct answer "Potato" and other options
"Cheese", "Ham" and "Bread". Instead of entering the answer contents, enter the answer's option instead. E.g. for the
option `A. Potato`, enter 'A'.

1. `solve 2`

```
==============================
Attempting question 2: MCQ: What are fries made of? 
A. Bread  
B. Ham  
C. Potato  
D. Cheese  

Enter your answer:
==============================
```

2. `C`

```
==============================
Correct!
==============================
```

These examples are for a TF question 2, "Fries are made of potatoes" with the correct answer "true".

1. `solve 2`

```
==============================
Attempting question 2: TF: Fries are made of potatoes (True/False)
Enter your answer:
==============================
```

2. `true`

```
==============================
Correct!
==============================
```

The wrong answer sequence for the above MCQ and TF questions follows that of the FITB questions.


**Note**:
* Entering a random string or character for MCQ or TF questions will by default be wrong, e.g. entering 'hi' instead of
'A', 'B', 'C' or 'D' for MCQ or 'hi' instead of 'true' or 'false' for TF will be considered incorrect.
* The MCQ question options are randomised each time to aid remembering the right answer contents instead of remembering
the right letter.
* If the question bank is empty (refers to the last shown list as well), the program will ask you to add a question.
* Follow the command format as specified above, incorrect formats will result in errors.
* Ensure that question indexes are within 1 to the number of questions, entering otherwise will result in errors.
* Entering other strings that are neither Y nor N to try again will result in the program continuously asking for Y 
or N until one of them is entered. This is to give the user a chance to actually enter Y or N.

<div style="page-break-after: always;"></div>


### Editing questions: `edit`

Edits a question that was previously added to the question bank.
It is recommended to run `list` before `solve` to check the index of the question you intend to edit.
If one runs `find` before `edit`, the list used for editing questions and question indexes will be what is
displayed by the `find` command, i.e. the last shown list.

Format: `edit [QUESTION_INDEX] [QUESTION_ATTRIBUTES]` `|` `[OPTION_INDEX]` 
(this step is only applicable if `[QUESTION_ATTRIBUTE]` is `o`) `|` `[NEW_QUESTION_ATTRIBUTES]`

`[QUESTION_INDEX]`: The question number of the question to be solved, according to the last shown list.  
`[QUESTION_ATTRIBUTES]`: The specific part of the question to modify:
- q - question content
- a - question answer
- o - incorrect option *(only applicable to multiple-choice questions)*

`[OPTION_INDEX]`: The index of the incorrect option of a multiple choice question to be modified.

`NEW_QUESTION_ATTRIBUTE`: The updated content for the specified attribute.

Example usage:

These examples are for a FITB question 2, "Where is Singapore located?" with a stored answer "North America".

1. `edit 2 a`

```
==============================
Editing FITB: Where is Siingapore located? [Answer: North America] 
 Please enter the new answer:
==============================
```

2. `Asia`

```
==============================
Question successfully edited: FITB: Where is Singapore located? [Answer: Asia]
==============================
```

These examples are for a MCQ question 1, "What are fries made of?" with answer "Potato" and incorrect options "Tomato" "Cheese" and "Apple".

1. `edit 1 o`

```
==============================
Editing MCQ: What are fries made of? [Answer: Potato] 
 Please enter the index of the option you want to edit: 
  1. Tomato
  2. Cheese
  3. Apple
==============================
```

2. `3`

```
==============================
Please enter the new option
==============================
```

3. `Rock`

```
==============================
Question successfully edited: MCQ: What are fries made of?
  A. Tomato
  B. Cheese
  C. Rock
  D. Potato
==============================
```

**Note**
The actual order of options may be different on your console because the options will be shuffled every time
the multiple choice question is displayed.

These examples are for a TF question 1, "Burgers are made of potatoes" with the correct answer "true".

1. `edit 1 a`

```
==============================
Editing TF: Burgers are made of potatoes [Answer: true]
 Please enter the new answer:
==============================
```

2. `false`

```
==============================
Question successfully edited: TF: Burgers are made of potatoes [Answer: false]
==============================
```

**Note**:
- If the input is empty, an error message will prompt the user to enter a valid value.

- When editing multiple-choice options, the system will prompt for three options sequentially.

<div style="page-break-after: always;"></div>


### Deleting a question: `delete`
Removes a question from the question bank. The question index should refer to the index displayed by the most recent `list` or `find` command.

**Format:**  

`delete [QUESTION_INDEX]`

- `[QUESTION_INDEX]`: The number of the question to be deleted, based on the latest shown list (either from `list` or `find`).

---

#### Example usage (after `list`):

```
list
==============================
1. FITB: 1 + 1 = __
2. FITB: The capital of France is __
==============================
delete 1
Deleted question: FITB: 1 + 1 = __ [Answer: 2]
```

You can run `list` again and delete the next question using `delete 1` again:

```
list
1. FITB: The capital of France is __
delete 1
Deleted question: FITB: The capital of France is __ [Answer: Paris]
```

---

#### Example usage (after `find`):

```
find fitb 1 +
==============================
1. FITB: 1 + 1 = __
2. FITB: 1 + 2 = __
==============================
delete 2
Deleted question: FITB: 1 + 2 = __ [Answer: 3]
```

---

### Important Notes

- The question index refers to the **most recently displayed question list**, either from `list` or `find`.
- If you want to delete **multiple questions**, make sure to **refresh the list before each deletion** by re-running the `list` or `find` command.
    - This is because after deleting, the displayed list is outdated — the indices may no longer point to the correct question.
- If you try to delete again **without updating the list**, you may encounter this error:

```
  Unable to find question in main bank to delete.
```

---

### Good Practice:
If you're deleting multiple questions:

**Using `list`:**

```
list → delete 1 → list → delete 1 → list → delete 1
```

**Using `find`:**

```
find fitb math → delete 1 → find fitb math → delete 1 → ...
```

This ensures the list is always updated and the deletion works as expected.


<div style="page-break-after: always;"></div>


### Showing the answer to a specific question: `show`

Shows the answer to a question in the question bank by querying its question index. The question index should refer to the index displayed by the most recent `list` or `find` command.

Format: `show [QUESTION_INDEX]`

- `[QUESTION_INDEX]`: The question number of the question whose answer is to be shown, according to the last shown list

Example usage:

```
show 1
==============================
[Command you entered: show 1]
==============================
==============================
Here is the answer for question 1:
==============================
==============================
1. MCQ: 1 + 1 [Answer: 2]
==============================
```

__Notes:__
- This newly displayed list **does not** update the last shown list, so the user can still refer to the last shown list triggered by `list` or `find` commands to query the question index.


<div style="page-break-after: always;"></div>



### Clearing all questions: `clear`
* Removes all questions from the question bank. This command uses multistep confirmation to prevent accidental loss of data.
* Format: `clear
* When run, it will prompt:

```
  Are you sure you want to clear the entire question bank? (Y/N)
  ```
* Type `Y` to proceed with clearing all questions.

* Type `N` to cancel the operation.

Example usage:

```
clear
==============================
Are you sure you want to clear the entire question bank? (Y/N)
==============================
Y
==============================
All questions have been cleared.
==============================
```


<div style="page-break-after: always;"></div>


### Undo/redo a command that modifies the question bank: `undo`/`redo`

Undo/redo a traceable command. A traceable command is one that modifies the question bank (e.g. `add`, `delete`, `edit`).

Format: `undo`/`redo`

Example usage:

Initial State:
A question, "What is the most abundant gas in air? [Answer: Nitrogen]," 
 is added as the first entry in the question bank.

```
undo
==============================
FITB: What is the most abundant gas in air? [Answer: Nitrogen] successfully deleted.
==============================
==============================
redo
==============================
==============================
FITB: What is the most abundant gas in air? [Answer: Nitrogen] successfully added.
==============================
```

**Note**

The program will store up to 10 traceable commands.

<div style="page-break-after: always;"></div>


### Exiting the program: `exit`
Exits the program.

Format: `exit`

Example usage: 

`exit`

<div style="page-break-after: always;"></div>



## Saving and Loading of Data

### Saving
* Questions are automatically saved every time the question bank is modified (e.g., when you add, delete, or edit a question).

* There is no need to manually save — this is handled behind the scenes.

* The save file uses a custom delimiter (as defined in the program's configuration via `Messages.STORAGE_DELIMITER`) to separate the fields of each question.
* Do NOT at any point enter the `Messages.STORAGE_DELIMITER` string into the input as it is reserved internally by the 
application as a delimiter for saving and loading your questions.
*  Including it will result in the following error message:
   1. While editing or adding a question: "Input cannot contain the reserved delimiter string! Please enter a new question:"

   2. While editing or adding an answer: "Input cannot contain the reserved delimiter string! Please enter a new answer:"

   3. While editing or adding MCQ options: "Input cannot contain the reserved delimiter string! Please enter a new option:"

### File Format
The file follows a structured format to allow for proper parsing:

* `[QUESTION_TYPE]|[QUESTION_TEXT]|[ANSWER]` (for FITB and TF)  
* `[QUESTION_TYPE]|[QUESTION_TEXT]|[OPTION1]|[OPTION2]|[OPTION3]|[OPTION4]` (for MCQ)  

#### Example: 
* Fill-in-the-Blanks questions: FITB`|`What is 2+2?`|`4

* Multiple Choice Questions: MCQ`|`What is 2+2?`|`4`|`3`|`5`|`6

* True/False questions: TF`|`The sky is blue`|`true

Note that `|` in the above examples represent `Messages.STORAGE_DELIMITER`

### Loading
* Upon startup, MindExpander will automatically read and load all questions from the MindExpander.txt file (if it exists).
* If the file is missing or empty, a new question bank will be initialised.
* Only properly formatted lines will be loaded. Malformed entries will be skipped.
* Lines that are incorrectly formatted by the user manually modifying the text file risk being skipped.

<div style="page-break-after: always;"></div>


## Logged data
To improve user experience, MindExpander keeps track of some data throughout the use of the program.
The log files are generated and stored in a logs folder that will be created when the user first does
something that will be logged (see log features below).

**Log features**  

Users may find these logs useful for their specific purposes.

1. Solve attempts: Stored in `solveAttemptLogs.txt`, tracks the time of attempt, the question attempted with its **correct** answer and if the user
got the question correct or wrong in the format `Timestamp|Question [Answer]|Result`. This is useful for seeing, for example, which questions
are constantly attempted and gotten wrong.
2. Errors: Stored in `errorLogs.txt`, tracks the time of input and the error message that was returned. This is useful for referring to when checking what inputs are not accepted.
3. Questions: Whenever a user adds, edits, or deletes a question, the system automatically saves a record of it in `questionLogs.txt`. This is useful for looking back at all the questions you've worked with.

The above log files are for the user to view only.

<div style="page-break-after: always;"></div>


## Additional notes for program features and usage
* This program is designed to take inputs in **Roman Alphabet** (i.e. English characters),
please do not enter characters from other languages, for example Chinese or Arabic characters. Entering them could
result in unspecified behaviour.
* Program is not designed to take in special control characters. Entering them may result in errors or crashing.
  * Ctrl Z + Enter (EOF signal on Windows) or Ctrl C (Interrupt) will end the program. 
* Inputting unrecognised commands will result in an error message.
* Commands are __not__ case-sensitive (i.e. ADD, LIsT are accepted).
* Commands and parameters are trimmed (i.e. leading and trailing whitespaces are accepted).
  * Note: ___ represents extra whitespaces.
  * E.g. `______add_____` is accepted.
  * E.g. `edit_______1____q___` is accepted.
  * E.g. `find hello____` will search for strings with just `hello`.
  * This trimming means it's ok if you accidentally press the space-bar too many times.

<div style="page-break-after: always;"></div>


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Data can be transferred by copying over the `data` folder containing `MindExpander.txt` and pasting it in the
same folder where the .jar file is installed on the new computer.

**Q**: Can I add questions to the question bank while the program is running?

**A**: No, the new questions will not be recorded. Do not edit the save file while the program is running.

**Q**: Will questions I add to the save file before running the program be added to the list?

**A**: Yes, provided that the questions are in the correct format. However, this is highly not recommended due to risk
of getting the format wrong. This will also bypass the checking of duplicate questions since you chose to manually add
the question.

<div style="page-break-after: always;"></div>


## Command Summary

* View help sheet `help`
* View help for a specific command `help [COMMAND]`
* Add question `add` `|` `[QUESTION_TYPE]` `|` `[QUESTION_DETAILS]` `|` `[QUESTION_ANSWER]`
* Edit question `edit` `QUESTION_INDEXT` `q/a/o` `|` `OPTION_INDEX` (`[OPTION_INDEX]` only applicable when editing options) `|` `[NEW_QUESTION_ATTRIBUTES]`
* List question bank `list`
* List MCQ question bank `list mcq`
* List FITB question bank `list fitb`
* List TF question bank `list tf`
* List question bank with answer `list answer`
* List MCQ question bank with answer `list mcq answer`
* List FITB question bank with answer `list fitb answer`
* List TF question bank with answer `list tf answer`
* Find a question in the question bank with a specific keyword `find [KEYWORD]`
* Find a MCQ question in the question bank with a specific keyword `find mcq [KEYWORD]`
* Find a FITB question in the question bank with a specific keyword `find fitb [KEYWORD]`
* Find a TF question in the question bank with a specific keyword `find tf [KEYWORD]`
* Solve question `solve [QUESTION_INDEX]` `|` `[QUESTION_ANSWER]` `|` `[Y/N]` (only if wrong)
* Delete question: `delete [QUESTION_INDEX]`
* Show answer to a question `show [QUESTION_INDEX]`
* Clear all questions: `clear` `|` `[Y/N]`
* Undo the previous command: `undo`
* Redo the previous undid command: `redo`
* Exit program `exit`
