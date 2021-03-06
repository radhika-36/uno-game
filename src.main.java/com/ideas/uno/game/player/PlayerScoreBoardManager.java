package com.ideas.uno.game.player;

import java.util.Comparator;
import java.util.List;

import com.ideas.uno.game.card.Card;

/**
 * Manage the players score board 
 *
 */
public class PlayerScoreBoardManager {

	private Player currentTopPlayer;

	private boolean isGameOver;

	private PlayerScoreBorad playerScoreBorad;

	public PlayerScoreBoardManager(PlayerScoreBorad playerScoreBorad) {
		this.playerScoreBorad = playerScoreBorad;
	}

	// calculate score
	public void calculateScore() {
		List<Player> players = playerScoreBorad.getPlayers();
		int totalScore = 0;
		for (Player player : players) {
			totalScore = totalScore + player.getCards().stream().mapToInt(Card::getNumber).sum();
			if (player.getCards().size() == 0) {
				this.currentTopPlayer = player;
			}
		}
		currentTopPlayer.addScore(totalScore);
	}

	// check if any player wins the game
	public boolean isGameFinished() {
		Player player = playerScoreBorad.getPlayers().stream().max(Comparator.comparing(Player::getScore)).get();
		if (player.getScore() >= this.playerScoreBorad.getWinningPoints()) {
			return true;
		}
		return false;
	}

	public Player getCurrentTopPlayer() {
		return currentTopPlayer;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}
}
