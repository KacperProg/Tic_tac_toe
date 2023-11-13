# Tic-tac-toe Game Brief

## MVP

Build Spring Boot API with **Web**, **DevTools**, **PostgreSQL** and **JPA** dependencies.

1. Create Game class - **id**, **player**, **board** (as a 2D Array?), **isComplete**, **result** (W/L/D - Enum)
2. Create Player Class - **id**, **playerName**
3. Create **Controllers**, **Service Layer**, **Data Access Layer**
4. Game Service Layer - **startGame()**, **processTurn()**, **checkWinner()**, **isCellFull()**, **isBoardFull()**, **getBoardState()**
5. RESTful Routes - Game 
	- **@GetMapping** - Index (Show all the Games stored)
	- **@GetMapping** - Show specific Game by ID
	- **@PostMapping** - Start New Game
	- **@DeleteMapping** - Delete a Game
	- **@PatchMapping** - Player has a turn
6. RESTful Routes - Player
	- **@GetMapping** - Index (Show all Players)
	- **@GetMapping** - Show Specific Player by ID
	- **@PostMapping** - Create a Player
	- **@DeleteMapping** - Delete a player
	- **@PatchMapping** - Update player details 

	
## 	Extension
1. Create a 2-player game - Change DB relationships
2. Create a Leaderboard
3. Achievements - Messages to say you've won this many games etc.
4.  