import java.util.Scanner;

public class TicTacToe {
	static char[][] board = {
		{' ', ' ', ' '},
		{' ', ' ', ' '},
		{' ', ' ', ' '}
	};

	static char currentPlayer = 'X';

	public static void main(String[] args) {
		Scanner scanner   = new Scanner(System.in);
		boolean gameEnded = false;

		while (!gameEnded) {
			printBoard();
			System.out.println(
							"Player " + currentPlayer + ", enter your move (row [1-3] and column [1-3]): ");
			int row = scanner.nextInt() - 1;
			int col = scanner.nextInt() - 1;

			if (isValidMove(row, col)) {
				board[row][col] = currentPlayer;

				if (checkWin(currentPlayer)) {
					printBoard();
					System.out.println("Congratulations! Player " + currentPlayer + " wins!");
					gameEnded = true;
				} else if (isBoardFull()) {
					printBoard();
					System.out.println("It's a tie! Well played!");
					gameEnded = true;
				} else {
					currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
				}
			} else {
				System.out.println("Invalid move. Please try again.");
			}
		}

		scanner.close();
	}

	static void printBoard() {
		System.out.println("  1   2   3");
		System.out.println("-------------");
		for (int i = 0; i < 3; i++) {
			System.out.print((i + 1) + " | ");
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println("\n-------------");
		}
	}

	static boolean isValidMove(int row, int col) {
		return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
	}

	static boolean checkWin(char player) {
		for (int i = 0; i < 3; i++)
			if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;

		for (int j = 0; j < 3; j++)
			if (board[0][j] == player && board[1][j] == player && board[2][j] == player) return true;

		if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true;

		if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true;

		return false;
	}

	static boolean isBoardFull() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (board[i][j] == ' ') return false;
		return true;
	}
}
