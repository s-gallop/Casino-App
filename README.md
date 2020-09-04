# Casino App

## CPSC 210 personal project

My personal project is going to be a casino game app, where the user can play and make bets on certain casino games,
add to their balance or cash out. Of course, when the user looses a game, they will loose whatever they bet on the
game, and if they're out of money they will not be able to continue playing unless they add more balance. 

As a video game, my program would be targeted towards anyone looking for a fun casino experience, without needing to go
to an actual casino.

I'm interested in making this app because I've like casinos and gambling games, but never actually gamble because I
hate loosing. So, this is an app where one can play casino games, and properly bet with a balance, without actually
risking any money.

**User Stories**
- As a user, I want to be able to add money to my balance
- As a user, I want to be able to cash out
- As a user, I want to be able to choose which casino game to play
- As a user, I want to be able to win or loose the casino game
- As a user, I want to be able to bet on a game
- As a user, I want the app to add all of my actions to a log
- As a user, I want to be able to win or loose money based on whether I win or loose the game
- As a user, I want to be able to save the state of my accounts
- As a user, I want to be able to load a set of accounts
- As a user, I want the app to auto save my accounts periodically

**How To Interact With The App**
- Click the button of the option that you want to choose
- If prompted for an input, enter it into the text field then press confirm
- Cancel will bring you back to the main page
- After finishing a game, press ok to return to the mane page
- You win slots if the same three images appear in a row or a diagonal
- You win blackJack if you dont go over 21, and the dealers score is strictly less than yours, or the dealer goes over 21; the dealer wins ties

**Phase 4: Task 2**
- Test and design a class that is robust 
    - BlackJack.isLost()
    - BlackJackTest.testIsLostFalse()
    - BlackJackTest.testIsLostEqual()
    - BlackJackTest.testIsLostTrue()
    
**Phase 4: Task 3**
- Cohesion
    - CasinoApp should be three classes: CasinoApp, AccountManager and LogManager
- Coupling
    - CasinoApp.bjLose() and CasinoApp.panelEight() --> CasinoApp.lose()
    - CasinoApp.bjPass() and CasinoApp.panelEight() --> CasinoApp.win()