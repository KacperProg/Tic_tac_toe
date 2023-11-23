package com.bnta.Tic_tac_toe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicTacToeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicTacToeApplication.class, args);
	}

}

//TODO
//  README
//		-Should include example payloads for the routes
//		-Need to clarify what position in PATCH/games actually means - is it referring to the cellNumber or the cell id? - specify this when writing up an example payload.
//		-UML diagram missing - please add this in
//		-You only need to write the available routes, you can take out the parts which tell you what each method does because you naming of methods should be a dead giveaway to what the method does and similarly you don’t need to write what models or components are available.
//	RESTFUL ROUTES
//		-GetMapping returns all the cells, if you have 5 games you will have a LOT of cells so maybe JSON ignore property - is it necessary to see all the cells when accessing the game? This will probably also declutter everything that appears when you do a get request to players.
//		-Why do you need a NewGameDTO when you could POST a game using the Game Model and adding in the Difficulty in the constructor?
//	GENERAL CODE
//		-Naming convention could be better eg checkTwoRow, isListWithCell, checkLine
//		-BoardStateGameDTO is technically a GameStateDTO as it includes more about the Game as opposed to just being about the Board - this is just naming convention
//		-Rank Logic was in the Player Model - Should be in the Player Service and Enums could have had associated values then using a for loop to check values may have cut out the lines of code
//		-Unsure about why it’s necessary for each row to be stored as a list - I feel like this was more aesthetic because from a frontend perspective you only need to know what the cellId is
//	ERROR HANDLING
//		-Error code 200 (OK) when you submit the same position twice - incorrect error code, should probably be a 400/405.
//		-Enums must be written the way they are coded ie all in capitals otherwise you cannot start a game - this should be documented

