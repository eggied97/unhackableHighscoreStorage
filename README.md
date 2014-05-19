unhackableHighscoreStorage
==========================

With this code you can store the highscore of a game and you cant hack it.

For starters, you need to set the package, and a database name. after that 
you can start using it. 

Usage
---------------------------------------


```java
DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());

int highscore = dbHelper.getScore();

// Do all of you code

if (newHighscore > highscore){
  dbHelper.updateScore(newHighscore);
  highscore = newHighscore; 
}
```
