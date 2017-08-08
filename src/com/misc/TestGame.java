package com.misc;
abstract class Game {
	protected int playersCount;

	abstract void initializeGame();

	abstract void makePlay(int player);

	abstract boolean endOfGame();

	abstract void printWinner();

	/* A template method : */
	final void playOneGame(int playersCount) {
		this.playersCount = playersCount;
		initializeGame();
		int j = 0;
		while (!endOfGame()) {
			makePlay(j);
			j = (j + 1) % playersCount;
		}
		printWinner();
	}
	
	static void foo() {}
}

class FooGame extends Game {

	@Override
	void initializeGame() {
		System.out.println("Inside initialize game");

	}

	@Override
	void makePlay(int player) {
		System.out.println("Inside make play");

	}

	@Override
	boolean endOfGame() {
		System.out.println("Inside end of game");
		return false;
	}

	@Override
	void printWinner() {
		System.out.println("Inside print winner");

	}

}

public class TestGame {
	public static void main(String args[]) {
		Game game=new FooGame();
		Game.foo();
		game.playOneGame(5);
		game.endOfGame();
	}

}