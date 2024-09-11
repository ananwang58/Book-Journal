# My Personal Project

## Book Journal

What will the application do?
- This application will allow users to track and rate all of their favorite books as well as put them in different categories to make it easier to find that book when are looking for it in the future

Who will use it?
- This application will be useful to everyone who loves reading as well as academics who need a place to store their thoughts on recent papers they have read

Why is this project of interest to you?
- Reading is one of my favorite hobbies and having an application where I rate track and rate all of my favorite books will be something I think all readers will be interested in 


## User Stories
- As a user, I want to be able to add a book I found funny to a list of books I found funny
- As a user, I want to be able to pull up a list of books that I rated 5 stars
- As a user, I want to be able to categorize books into genres
- As a user, I want to be able to record my thought on a certain book
- As a user, I want to be able to give books half-star ratings
- As a user, I want to be able to save the books I have added to my book journal (if I so choose)
- As a user, I want to be able to be able to load my book journal from file (if I so choose)

### Instructions for Grader
- You can generate the first required action related to the user story by selecting the "View all entries" button
- You can generate the second required action related to the user story by selecting the "List my books" button
- You can locate my visual component when pressing the "List All Books" button
- You can save the state of my application by pressing the "Load" button
- You can reload the state of my application by pressing the "Save" button

### Phase 4: Task 2

Tue Apr 02 23:13:47 PDT 2024
You added Pride and Prejudice to your book journal

Tue Apr 02 23:13:50 PDT 2024
You displayed the most recent book you read which was Pride and Prejuduce

Tue Apr 02 23:13:52 PDT 2024
You displayed all FIVE STAR BOOKS in your book journal

Tue Apr 02 23:13:55 PDT 2024
You displayed all romantic books in your book journal

Tue Apr 02 23:13:59 PDT 2024
You displayed all entries in your book journal

Tue Apr 02 23:14:00 PDT 2024
Book Journal saved successfully to
./data/bookJournal.json

Tue Apr 02 23:14:01 PDT 2024
Book Journal loaded successfully from
./data/bookJournal.json

### Phase 4: Task 3
If I had more time to work on this project one improvement I could make to improve my design would be to
add a class called JsonHandler to encompass JsonReader and JsonWriter so that all Json operations are inside 
one singular class. This refactoring change will not only improve the testability of my code but also improves its 
cohesion as the JasonHandler class will now handle all Json responsibilities. Having JsonReader and JsonWriter both 
defines in a new JsonHandler class will also make it easier for me to maintain and make changes or improvements 
to in the future.

# Book-Journal
