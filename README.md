# Public-Library

<h2>Project Description</h2>
We are developing a booking system designed for a public library. The system should be able to function as a record keeper of the books owned by the library as well as a service console for both 

**Major Features:**
- Searching and locating books for staff and library users
- Sorting books based on predefined categories
- Checking out books
- Keeping the track of inventory 
- Log-in System
- Admin panel

**NEW Features:**
- Independent Wishlist of books for each user
- Ability to search in browser any wishlisted books

<h2> How The GUI Works</h2>

JavaFX: We utilized the JavaFX public library and its companion program, Scene Builder, to create the visual design of the graphic interface. The library utlizes java classes and XML files to showcase the screens.

Screens: Each screen has a java class which operates its functions and methods and variables. The attached XML file will include the code for how the screen ought to look. JavaFX methods were used to switch between screens and showcase alerts. These classes take care of reading inputs of the user in the form of buttons and text fields.

<h2>How The SQL Datbase is Designed - Relational Model</h2>
<img width="949" alt="Screen Shot 2022-11-08 at 1 13 08 PM" src="https://github.com/Grssmn/Public-Library-Management-System/blob/main/SQL%20Image.png">

<h2>How the Functionalities Operate</h2>
<ul>
  <li><strong>Log In:</strong> many systems may need a simple log in function to have a wall of security behind the system</li>
  <li><strong>Check Out:</strong> whenever objects are assigned to user’s (such as orders, check outs, and buy outs) this function can help assign an object in a database to a user
</li>
  <li><strong>Return:</strong> systems which expan upon the check out and involve a form of return will use this method to return the ‘taken out’ object back into the inventory within the database
</li>
  <li><strong>Search:</strong> the search method is broad and may be utilized by any system that wishes to get result sets from a SQL database
</li>
  <li><strong>Create Users:</strong> any system that has users with distinct data sets has to be able to create new users, thus making use of our method which creates an object and saves its data into an SQL table
</li>
</ul>
<h2>NEW Functionalities</h2>
<ul>
  <li><strong>User Wishlist:</strong> Any user may add any book, with its author and category, to their wishlist. This list will be compiled into the SQL database to save the information. Administrators may access these wishlists and see which users are requesting each book.
</li>
  <li><strong>Search Online:</strong> Administrators will have a button that allows the program to do a google search for any book that may have been wishlisted, in order to view available prices online.
  </li>
</ul>

<h2>How To Compile and Run The Code</h2>
Clone the repository onto your device. Once you have access to the directory, load it on IntelliJ IDEA run the "Login.java" file from the "BookManagementProject" folder. To access a SQL database, the localhost must be hosting a SQL database through phpMyAdmin (for example). Before running, add unto the admin table an admin account directly unto the SQL to be able to log in.
