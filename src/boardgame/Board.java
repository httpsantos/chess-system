package boardgame;

import boardgame.exception.BoardException;

/*
 * @author Carlos Henrique
 * @github github.com/httpsantos
 */
public class Board {
	private int rows;
	private int columns;
	private Piece[][] pieces;

	public Board() {
	}

	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

// uma vez criado um tabuleiro, a quantidade de linhas nao pode ser modificada	
//	public void setRows(int rows) {
//		this.rows = rows;
//	}

	public int getColumns() {
		return columns;
	}

//	uma vez criado um tabuleiro, a quantidade de colunas nao pode ser modificada
//	public void setColumns(int columns) {
//		this.columns = columns;
//	}

	public Piece piece(int rows, int column) {
		if (!positionExists(rows, column)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[rows][column];
	}

	public Piece piece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return piece(position.getRow(), position.getColumn());
	}

	public Piece removePiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}

		if (piece(position) == null) {
			return null;
		}

		Piece aux = piece(position);
		aux.position = null;
		pieces[position.getRow()][position.getColumn()] = null;
		return aux;
	}

	public void placePiece(Piece piece, Position position) {
		if (thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position: " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}

	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}

	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}

	public boolean thereIsAPiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return piece(position) != null;
	}
}