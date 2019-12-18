import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel
{
    private int circles;
    private Board board;
    public int player = 0;
    private boolean settingUp = true;
    public int mouseX;
    public GamePanel(Board board)
    {
        circles = board.getRows() * board.getColumns();
        Dimension dimension = new Dimension((board.getRows()+2)* 50, (board.getColumns()+1)* 50);
        setSize(dimension);
        setPreferredSize(dimension);
        setBackground(Color.ORANGE);
        this.board = board;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e)
            {
                if(board.playerWon(1))
                {
                    Object[] dialogOptions = {"OK"};
                    int n = JOptionPane.showOptionDialog(new JFrame(), "Player 1 won the game!", "Game Over", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, dialogOptions, dialogOptions[0]);
                    if(n == 0)
                    {
                        Runtime.getRuntime().exit(0);
                    }
                }
                else if(board.playerWon(2))
                {
                    Object[] dialogOptions = {"OK"};
                    int n = JOptionPane.showOptionDialog(new JFrame(), "Player 2 won the game!", "Game Over", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, dialogOptions, dialogOptions[0]);
                    if(n == 0)
                    {
                        Runtime.getRuntime().exit(0);
                    }
                }
                else if(board.isFull())
                {
                    Object[] dialogOptions = {"OK"};
                    int n = JOptionPane.showOptionDialog(new JFrame(), "It's a tie! Y'all managed to fill the board.", "Game Over", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, dialogOptions, dialogOptions[0]);
                    if(n == 0)
                    {
                        Runtime.getRuntime().exit(0);
                    }
                }
                else
                {
                    settingUp = false;
                    Point p = new Point(e.getLocationOnScreen());
                    SwingUtilities.convertPointFromScreen(p, e.getComponent());
                    mouseX = p.x;
                    repaint();
                    System.out.println(board);
                }
            }
        });
    } // end constructor

    public void paintComponent(Graphics g)
    {
        if(settingUp)
        {
            g.setColor(Color.YELLOW);
            int x = 0;
            int yFactor = 0;
            for (int i = 0; i < circles; i++) {
                yFactor++;
                if (i % board.getRows() == 0) {
                    x += 50;
                    yFactor = 1;
                }
                g.fillOval(x, 50 * yFactor, 50, 50);
            }
        }
        else
        {
            // check and set position of click
            player++;
            board.checkAndSetPosition(mouseX, player);
            for(int i = 0; i < board.getRows(); i++)
            {
                for(int j = 0; j < board.getColumns(); j++)
                {
                    if(board.getValueAt(i, j) == 0)
                    {
                        g.setColor(Color.YELLOW);
                    }
                    else if(board.getValueAt(i, j) == 1)
                    {
                        g.setColor(Color.RED);
                    }
                    else
                    {
                        g.setColor(Color.BLUE);
                    }
                    g.fillOval((i+1) * 50, (j+1) * 50, 50, 50);
                }
            }
            if(player == 2)
            {
                player = 0;
            }
        }
    } // end method paintComponent
} // end class GamePanel
