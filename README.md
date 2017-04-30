# LPOO1617_T3G02_P2
Project 2 for LPOO class

* **Team members**:
   * [Ana Claudia Fonseca Santos](https://github.com/anaezes) 
      * up200700742 (up200700742@fe.up.pt)
   * [Cristiana Maria Monteiro Ribeiro](https://github.com/311-311) 
      * up201305188 (up201305188@fe.up.pt)
      
      
# Final Project Intermediate Check-Point:

* **Architecture Design:**
  * UML

  <p align="center"> <img src="prj_images/uml.png"> </p>
  
  * Sequences and state machines
      * Interaction sheme
      
       <p align="center"> <img src="prj_images/InteractionScheme.png"> </p>
       
       * Menu state
      
       <p align="center"> <img src="prj_images/MenuState.png"> </p>
       
       * Game state
       
       <p align="center"> <img src="prj_images/GameState.png"> </p>
       
       * Level state
       
       <p align="center"> <img src="prj_images/LevelState.png"> </p>
  
  * Expected Design Patterns:
    * Model-View-Controller (MVC)
    * Singleton
    * Factory
    * Fluent Builder
    * ...


* **GUI Design:**
  * Listing of the main functionalities, present on the GUI, and game description. 
    * Choose menu.
    * Choose the level
      - Each level has a different theme and to play them, with the exception of the first, you need to unlock them.
      - The weight of the chicken increases with the passing of the levels (Physics).
    * Play the game:
      - Touching the screen to make the chicken fly.
      - Rotating the screen to make the chicken to avoid obstacles or catch awards. 
    * Display game state.
    * Change game options.
    * Customize the chicken:
      - Choose another colors and items. 
  
  * GUI mock-up
  
    * Mock-up general:
    
    <p align="center"> <img src="prj_images/final.png"> </p>
    
    * Design of main screen:
    
    <p align="center"> <img src="prj_images/init.png"> </p>
    
    * Design of main menu:
    
    <p align="center"> <img src="prj_images/mainMenu.png"> </p>
    
    * Design of play menu:
    
    <p align="center"> <img src="prj_images/gameMenu.png"> </p>
    
    * Design of Game (level 1):
    
    <p align="center"> <img src="prj_images/game.png"> </p>
    
    * Design of the instructions:
    
    <p align="center"> <img src="prj_images/instructions.png"> </p>
    
    * Design of the scores:
    
    <p align="center"> <img src="prj_images/scores.png"> </p>
    
    * Design of the options:
    
    <p align="center"> <img src="prj_images/options.png"> </p> 
 
  
* **Test Design:**

  * Listing of the expected final test cases:
  
      * testMoveBird() - Bird moves on user input;
      
      * testScore() - Score change when bird moves;
      
      * testCollisionStone() - Collision with a stone object;
      
      * testCollisionBranch() - Collision with a branch object;
      
      * testWaterRising() - Tests if water is rising;
      
      * testGetStar() - Bird get a star: the score changes (+ 100 pts);
      
      * testGetApple() - Bird get a apple: the score changes (+ 50 pts);
      
      * testPassLevel() - Pass for next level (case the score is more than a defined value);
      
      * testWinGame() - Tests if the user win the game;
      
      * testLoseGame() - Tests if the user lose the game;
