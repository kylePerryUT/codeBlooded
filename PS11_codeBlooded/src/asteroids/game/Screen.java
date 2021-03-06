package asteroids.game;

import static asteroids.game.Constants.*;
import java.awt.*;
import java.util.Iterator;
import javax.swing.*;

/**
 * The area of the display in which the game takes place.
 */
@SuppressWarnings("serial")
public class Screen extends JPanel
{
    /** Legend that is displayed across the screen */
    private String legend;
    
    /** Leader board */
    private String leaderBoard;
    
    /** level that is displayed in the top right of the screen. */
    private String level;
    
    /** Current score of game. */
    private String score;
    
    /** accuracy */
    private String accuracy;

    /** Game controller */
    private Controller controller;

    /**
     * Creates an empty screen
     */
    public Screen (Controller controller)
    {
        this.controller = controller;
        leaderBoard = "";
        legend = "";
        level = "";
        score = "";
        accuracy = "";
        
        setPreferredSize(new Dimension(SIZE, SIZE));
        setMinimumSize(new Dimension(SIZE, SIZE));
        setBackground(Color.black);
        setForeground(Color.white);
        setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 120));
        setFocusable(true);
    }

    /**
     * Set the legend
     */
    public void setLegend (String legend)
    {
        this.legend = legend;
    }
    
    /**
     * sets the leaderboard
     */
    public void setleaderBoard(String leaderboard)
    {
        this.leaderBoard = leaderboard;
    }
    
    /**
     * Sets the accuracy.
     */
    public void setAccuracy(String Acc)
    {
        accuracy = Acc;
    }
    
    /**
     * Set the level
     */
    public void setLevel (String level)
    {
        this.level = level;
    }
    
    /**
     * Set the score
     */
    public void setScore (String score)
    {
        this.score = score;
        
    }

    /**
     * Paint the participants onto this panel
     */
    @Override
    public void paintComponent (Graphics graphics)
    {
        // Use better resolution
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // Do the default painting
        super.paintComponent(g);

        // Draw each participant in its proper place
        Iterator<Participant> iter = controller.getParticipants();
        while (iter.hasNext())
        {
            iter.next().draw(g);
        }

        // Draw the legend across the middle of the panel
        int size = g.getFontMetrics().stringWidth(legend);
        g.drawString(legend, (SIZE - size) / 2, (SIZE / 2));
        
        // Draws leaderboard at end of game.
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
        int y = 0;
        for (String s: leaderBoard.split("\n"))
        {
            g.drawString(s, (SIZE / 2) - 40, (SIZE / 2) + 50 + y); 
            y += 20;
        }
        
        // Draws accuracy in the top middle of the screen
        g.drawString(accuracy, SIZE / 2 - 30, LABEL_VERTICAL_OFFSET + 30);
        
        // Draw the level in the top right corner
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        g.drawString(level, SIZE - LABEL_HORIZONTAL_OFFSET, LABEL_VERTICAL_OFFSET + 30);
        
        // Draw the score in the top left corner
        g.drawString(score, LABEL_HORIZONTAL_OFFSET, LABEL_VERTICAL_OFFSET + 30);
    }

}
