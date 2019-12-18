import java.util.Arrays;
public class Board
{
    private Piece[][] board;
    public Board(int rows, int cols)
    {
        board = new Piece[rows][cols];
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[0].length; j++)
            {
                board[i][j] = new Piece(0);
            }
        }
    } // end constructor

    public int getRows()
    {
        return board.length;
    } // end int method getRows

    public int getColumns()
    {
        return board[0].length;
    } // end int method getColumns

    public int getValueAt(int row, int col)
    {
        return board[row][col].getPieceValue();
    } // end int method getValueAt

    public boolean isFull()
    {
        int count = 0;
        for(int i = 0; i < getRows(); i++)
        {
            for(int j = 0; j < getRows(); j++)
            {
                if(board[i][j].getPieceValue() == 1 || board[i][j].getPieceValue() == 2)
                {
                    count++;
                }
            }
        }
        if(count == getRows() * getColumns())
        {
            return true;
        }
        return false;
    } // end boolean method isFull

    public boolean playerWon(int player)
    {
        int piecesInARow = 0;

        // 4 IN A ROW: VERTICAL
        for(int j = 0; j < getRows(); j++)
        {
            for(int i = 0; i < getColumns(); i++)
            {
                // if the piece at board[i][j] happens to be the input player, add 1 to pieces in a row
                if(board[i][j].getPieceValue() == player)
                {
                    piecesInARow++;
                    if(piecesInARow >= 4)
                    {
                        System.out.println("Win! Vertical");
                        return true;
                    }
                }
                // in the event of there being a break in the pieces in a row, reset count
                else
                {
                    piecesInARow = 0;
                }
            }
        }
        piecesInARow = 0;

        // 4 IN A ROW: HORIZONTAL
        for(int i = 0; i < getColumns(); i++)
        {
            for(int j = 0; j < getRows(); j++)
            {
                if(board[i][j].getPieceValue() == player)
                {
                    piecesInARow++;
                    if(piecesInARow >= 4)
                    {
                        System.out.println("Win! Horizontal");
                        return true;
                    }
                }
                else
                {
                    piecesInARow = 0;
                }
            }
        }
        piecesInARow = 0;

        // 4 IN A ROW: RIGHT DIAGONAL (A)
        int y = getRows()-1;
        int x;
        for(int i = 0; y >= 0; i++)
        {
            x = 0;
            int y2 = y;

            while(x <= getColumns()-1 && y2 <= getRows()-1) // diagonally right, downwards
            {
                if(board[x][y2].getPieceValue() == player)
                {
                    piecesInARow++;
                    if(piecesInARow >= 4)
                    {
                        System.out.println("Win! Right Diagonal A");
                        return true;
                    }
                }
                else
                {
                    piecesInARow = 0;
                }
                x++;
                y2++;
                y = getRows()-1; // to prevent skipping row
            }
            y -= i;
        }
        piecesInARow = 0;

        // 4 IN A ROW: RIGHT DIAGONAL (B)
        x = getColumns()-1;
        for(int i = 0; x >= 0; i++)
        {
            y = 0;
            int x2 = x;
            while(y <= getRows()-1 && x2 <= getColumns()-1) // diagonally right, downwards
            {
                if(board[x2][y].getPieceValue() == player)
                {
                    piecesInARow++;
                    if(piecesInARow >= 4)
                    {
                        System.out.println("Win! Right Diagonal B");
                        return true;
                    }
                }
                else
                {
                    piecesInARow = 0;
                }
                y++;
                x2++;
                x = getColumns() - 1;
            }
            x -= i;
        }
        piecesInARow = 0;

        // 4 IN A ROW: LEFT DIAGONAL (A)
        y = getRows() - 1;
        for(int i = 0; y >= 0; i++)
        {
            x = getColumns() - 1;
            int y2 = y;

            while(x >= 0 && y2 <= getRows()-1) // diagonally left, downwards
            {
                if(board[x][y2].getPieceValue() == player)
                {
                    piecesInARow++;
                    if(piecesInARow >= 4)
                    {
                        System.out.println("Win! Left Diagonal A");
                        return true;
                    }
                }
                else
                {
                    piecesInARow = 0;
                }
                x--;
                y2++;
                y = getRows()-1;
            }
            y -= i;
        }
        piecesInARow = 0;

        // 4 IN A ROW: LEFT DIAGONAL (B)
        x = getColumns() - 1;
        for(int i = 0; x >= 0; i++)
        {
            y = 0;
            int x2 = x;

            while(y <= getRows()-1 && x2 >= 0) // diagonally left, downwards
            {
                if(board[x2][y].getPieceValue() == player)
                {
                    piecesInARow++;
                    if(piecesInARow >= 4)
                    {
                        System.out.println("Win! Left Diagonal B");
                        return true;
                    }
                }
                else
                {
                    piecesInARow = 0;
                }
                y++;
                x2--;
                x = getColumns() - 1;
            }
            x -= i;
        }
        return false;
    } // end boolean method playerWon

    public boolean checkAndSetPosition(int mouseX, int player)
    {
        // get row that the piece would be in
        int row = 0;
        boolean setPos = false;
        for(int i = 1; i <= board.length; i++)
        {
            if(mouseX >= (50*i)+50 && mouseX <= (50*(i+1))+50)
            {
                if(i > board.length-1)
                {
                    row = board.length-1;
                }
                else
                {
                    row = i;
                }
            }
        }
        // iterate through row, check if and where the piece would fit, place piece
        for(int i = board[0].length-1; i >=  0; i--)
        {
            if(board[row][i].getPieceValue() == 0)
            {
                board[row][i].setPlaced(player);
                setPos = true;
                break;
            }
        }
        return setPos;
    } // end boolean method checkAndSetPosition

    public String toString()
    {
        int[][] boardReturn = new int[board.length][board[0].length];
        for(int i = 0 ; i < board.length; i++)
        {
            for(int j = 0; j < board[0].length; j++)
            {
                boardReturn[i][j] = board[i][j].getPieceValue();
            }
        }
        return Arrays.deepToString(boardReturn);
    } // end method toString

    public Piece[][] to2DPieceArray()
    {
        return board;
    } // end method to2DPieceArray
} // end class Board
