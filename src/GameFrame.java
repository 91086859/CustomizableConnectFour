import javax.swing.*;
public class GameFrame extends JFrame
{
    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e)
        {
            ;
        }

        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new GameFrame().setVisible(true);
            }
        });
    } // end main method

    public GameFrame()
    {
        String result = (String)JOptionPane.showInputDialog(this, "How big do you want your board to be? (input as \"rowsxcols\", where rows is the rows you want and cols is the columns you want)", "Board Size", JOptionPane.PLAIN_MESSAGE, null, null, "6x6");
        Board board = new Board(Integer.parseInt(result.substring(0, result.indexOf("x"))), Integer.parseInt(result.substring(result.indexOf("x")+1)));
        this.setTitle("Connect Four");
        GamePanel panel = new GamePanel(board);
        add(panel);
        pack();
        setLocationRelativeTo(null);
    } // end constructor
} // end class GameFrame
