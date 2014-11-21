package graphics;

import game.Game;
import game.ResourceLoader;
import game.Stage;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Canvas extends JPanel {

  private final List<IPaintable> objects = new LinkedList<IPaintable>();

  private static final Canvas canvas = new Canvas();
  private Font font;
  private final String start;
  private final String end;
  private final String won;
  private final String won2;

  private Canvas() {

    try {
      this.font = Font.createFont(Font.TRUETYPE_FONT, ResourceLoader.load("8-BIT WONDER.TTF"));
      this.font = this.font.deriveFont(24.0f);
    } catch (FontFormatException e) {
    } catch (IOException e) {
    }
    this.start = "PRESS ENTER TO START";
    this.end = "GAME OVER";
    this.won = "CONGRATULATIONS";
    this.won2 = "YOU WON";

    setSize(800, 800);
    setVisible(true);
    setFocusable(true);
  }

  @Override
  public void paintComponent(Graphics g) {
    Stage stage = Game.getStage();

    Rectangle bg = new Rectangle(0, 0, this.getWidth(), this.getHeight(), Color.BLACK);
    g.setFont(this.font);
    FontMetrics fm = g.getFontMetrics(this.font);

    switch (stage) {
      case START:
        bg.paint(g);
        Image logo = null;
        try {
          logo = ImageIO.read(ResourceLoader.load("logo.png"));
        } catch (IOException e1) {
        }
        g.drawImage(logo, (this.getWidth() - logo.getWidth(null)) / 2, 50, null);

        g.setColor(Color.WHITE);
        g.drawString(this.start, (this.getWidth() - fm.stringWidth(this.start)) / 2, this.getHeight() / 2 + 100);
        break;
      case GAME:
        bg.paint(g);
        for (IPaintable obj : this.objects) {
          obj.paint(g);
        }
        break;
      case END:
        for (IPaintable obj : this.objects) {
          obj.paint(g);
        }
        g.setColor(Color.BLACK);
        g.fillRect(250, 325, 300, 100);
        g.setColor(Color.WHITE);
        g.drawString(this.end, (this.getWidth() - fm.stringWidth(this.end)) / 2, this.getHeight() / 2);
        break;
      case WIN:
        bg.paint(g);
        g.setColor(Color.WHITE);
        g.drawString(this.won, (this.getWidth() - fm.stringWidth(this.won)) / 2, this.getHeight() / 2 -20);
        g.drawString(this.won2, (this.getWidth() - fm.stringWidth(this.won2)) / 2, this.getHeight() / 2 + 20);
        break;
    }

  }

  public void add(IPaintable obj) {
    this.objects.add(obj);
  }

  public void remove(IPaintable obj) {
    this.objects.remove(obj);
  }

  public static Canvas getCanvas() {
    return canvas;
  }
}
