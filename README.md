# Tic-tac-toe Game Project

	![alt text] (Tic_tac_toe_documentation/game_logic.png)

	Create an interactive game of tic-tac-toe which can be played through postman. A player can be created with a name, and id. A game is made with a set of 9 blank cells, which can be filled through patch requests to the game. After a move is made, there is a computer response where a the computer takes a turn based on the player's move.

	Game logic is implemented to process every possible outcome of the game and return the state of the game after each guess or after the game is complete.

	Packages - (components, controllers, models, repositories, services) (+ application runner)

	components - data loader 
	data loader - on start up, a player(Zsolt) and a new game associated with that player are created

	Contollers - (GameController, PlayerController)
	GameController - (all object returned are response entities containing relevent information)
	- **@GetMapping** - getAllGames - return all the Games stored
	- **@GetMapping** - getGameById - Show specific Game by ID
	- **@GetMapping** - getGameState - Show specific Game information without excess unncessary information
	- **@PostMapping** - startNewGame - creates a new blank game
	- **@DeleteMapping** - deleteGameById - deletes a game by id
	- **@PatchMapping** - updateGame - player has a turn, and then the computer makes a move if possible

	PlayerController
	- **@GetMapping** - getAllPlayers - show all Players
	- **@GetMapping** - getPlayerById - show Specific Player by ID
	- **@PostMapping** - addPlayer - create a new Player
	- **@DeleteMapping** - deletePlayerById - delete a player
	- **@PatchMapping** - updatePlayerName - Update player's name but keeps player id

	## models - (BoardStateGameDTO, Cell, Game, GameDTO, Player, PlayerDTO, ReplyDTO, Result, Value) ## 

	BoardStateDTO (long id, Player player, boolean isComplete, Result result, List<List<Value>> board)
	
	Cell (long id, Game game, Value value, int cellNumber)
	
	Game (long id, Player player, boolean isComplete, Result result)
	
	GameDTO (int position)
	
	Player (long id, String playerName, List<Game> games)
	
	ReplyDTO (String message, List<List<Value>> board, boolean isValidMove, Result result)
	
	enum Result (WIN, DRAW, LOSS)
	
	enum Value (EMPTY, X, O)

	repositories - (CellRepository, GameRepository, PlayerRepository)

	## Services - (GameService, PlayerService) ##

	Gameservice

	- getAllGames(return list of all games)
	- getGameById(return an optional game)
	- makeCells(when a new game is made, a set of 9 cells is created and saved to that game)
	- startNewGame(return a new blank game with 9 cells associated to it)
	- deleteGameById(remove game and associated cells from database)
	- isCellFull(returns true if cell has and 'X' or 'O' in it)
	- isBoardFull(returns true if every cell has and 'X' or 'O' in it)
	- makePlayerMove(puts an 'X' in cell chosen by player)
	- makeComputerMove(puts an 'O' in a random unoccupied cell)
	- checkTwo()
	- reactiveMoveComputer()
	- makeComputerMoveHard()
	- isListWithCell()
	- getValidCells()
	- getCellCombinations(return a List of the lists of all possible cell combinations that would give a 3 in a row)
	- checkLine(return true if there is a line of 'X's or 'O's)
	- checkWinner(return true if any possible checkLine is true)
	- getGameState(returns a list of all the game's cells as a 2D array as it would appear in a 3x3 grid)
	- processTurn(implements all the game logic for processing a turn, making processing computer turn and checking for winners, then returns a replyDTO (See flowchart for logic))

	PlayerService

	- getAllPlayers(return a list of all players)
	- getPlayerById(return Optional<Player> by id)
	- addPlayer(return new player added to database)
	- deleteById(remove player from database)
	- updatePlayerName(returns updated player and updates them in database)
