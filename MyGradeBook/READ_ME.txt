READ ME FIRST!


Welcome to Andrew's, Tyler's, Adam's, and Daniel's GRADEBOOK SYSTEM!


How To Run:


From command line: 
To compile the code:                \\working directory> javac GUIFrame.java
To run the program:                \\working directory> java GUIFrame


From an IDE (like Eclipse):
Set run configurations to main method for GUIFrame in the gui package. From there the GUI should start up.




Beginning:
When you start up our MyGradeBook, you're given an empty gradebook. There's a menu bar with File, Edit, and Stats, and the main panel is a table. The table is resizable and always fits the data you have entered.


To create a new GB:
        - Empty GradeBook:
                Go to File ? New empty Gradebook
        
        -From a file:
                Go to File ? Open ? <Select File> ? OK


        -From a string (text):
                Go to File ? New Gradebook from string ? <Enter String> ? OK


Saving:
Gradebooks can be saved (output) to a .txt file. Note that the GradeBook does not automatically save, so it is wise to save your work periodically.


To save, go to
        File ? Save as... ? <Enter Name/Directory> ? OK




Adding to your GradeBook:
There are three ways to add to your GradeBook: adding a student, adding an assignment, and adding to your GradeBook from a properly formatted text file.


        -Adding a Student:
                Go to Edit ? Add Student ? <fill out each field> ? Create
        
        -Adding an Assignment:
                Go to Edit ? Add Assignment ? <fill out each field> ? Create


Adding from a File:
                Go to Edit ? Add file to Gradebook ? <select directories/file> ? OK


Editing your GradeBook:
        Manipulating existing data is extremely easy within the GradeBook! Each cell in the main table is editable (except for usernames, which cannot be edited once created). So if you accidentally mistype a student's score�or if you want to add extra credit, e.g.--just double click on a cell to make the text field editable. Once you have finished updating the data, either press Enter or click away from the cell to confirm the change.


Statistics:
        Do your students ever bother you about finding out the mean, median, min, and max for an assignment in your class? It must get really annoying after the 1000th time, so we added a feature to ease your struggle. No more needing to reach for that 4th glass of wine every night!


        -View Statistics:
                Go to Stats ? Calculate Statistics ? <check boxes you want> ? OK


In the Calculate Statistics box, there are two main categories: assignments and statistics. For each assignment you check, you will receive for that a report of the statistics that you also checked off. Therefore, it's extremely easy to see either 1 stat for 1 assignment, or even all stats for all assignments!


NOTE: It's much clearer to read if you limit the number of assignments to get statistics from to 1 or 2 at a time.


Viewing Data:
        Every row within the GradeBook represents a student, and every column is a category. By default, each student has 6 categories (columns): username, first name, last name, advisor, year, and current overall grade. Each new assignment added creates a new column.


        You may resize each and every column by placing your cursor on a column header's border and clicking and dragging to the side. Furthermore, you may drag columns around to arrange them in any order you wish.


Exiting:
        If at any point you want to exit, press the red X located on the top-right corner of the main GUI screen. Remember, your work is not automatically saved! It's best to save before you quit.






IMPORTANT: Below is the guidelines for inputting text files into the GradeBook program.
There are six (6) different headers you may use to signify data types:
- ASSIGNMENT
- STUDENT_GRADES
- CURRENT_GRADES
- STUDENT
- ASSIGNMENT_GRADES
- GRADES_FOR_ASSIGNMENT


Input can either be from a file or as a string. This is how the inputs should be formatted in the text files:


<HEADER>
<info1>
<info2>
.
.
<info-n>


Here�s how each is specifically formatted:
ASSIGNMENT:


ASSIGNMENT
<Name of assignment 1>
<Points out of for assignment 1>
<Percent of total grade for assignment 1>
ASSIGNMENT
<Name of assignment 2>
<Points out of for assignment 2>
<Percent of total grade for assignment 2>
.
.
ASSIGNMENT
<Name of assignment n>
<Points out of for assignment n>
<Percent of total grade for assignment n>
// Here it�s just shown for clarity that a file could contain many assignments one after the //other


STUDENT_GRADES:


STUDENT_GRADES
<Student 1>
<Student 2>
.
.
<Student n>
<Current Year (?)>
----
<Name of Assignment> <Points out of>
<Name of Assignment> <Points out of>
.
.
----
CURRENT GRADE <Average (weighted?) grade of those listed students?>


CURRENT_GRADES:


CURRENT_GRADES
<Student 1> <Student 1�s average weighted grade>
<Student 2> <Student 2�s average weighted grade>
.
.
<Student n> <Student n�s average weighted grade>


STUDENT:


STUDENT
<Student 1�s id>
<Student 1�s first name>
<Student 1�s last name>
<Student 1�s adviser>
<Student 1�s year of graduation>
STUDENT
<Student 2�s id>
<Student 2�s first name>
<Student 2�s last name>
<Student 2�s adviser>
<Student 2�s year of graduation>
.
.
.
STUDENT
<Student n�s id>
<Student n�s first name>
<Student n�s last name>
<Student n�s adviser>
<Student n�s year of graduation>


ASSIGNMENT_GRADES:


ASSIGNMENT_GRADES
<Assignment 1 name>
<Total points possible assignment 1>
<Assignment 1�s percentage of grade>
----
<Student 1> <Student 1�s grade>
<Student 2> <Student 2�s grade>
.
.
<Student n> <Student n�s grade>
----
STATS
Average <average>
Median <median>
Max <max>
Min <min>


GRADES_FOR_ASSIGNMENT:


GRADES_FOR_ASSIGNMENT
<Assignment name>
<Student 1>
<Student 1 grade>
<Student 2>
<Student 2 grade>
.
.
<Student n>
<Student n grade>