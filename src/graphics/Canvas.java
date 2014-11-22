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
  private Font font_small;
  private final String STR_START = "PRESS ENTER TO START";
  private final String STR_END = "GAME OVER";
  private final String STR_WIN1 = "CONGRATULATIONS";
  private final String STR_WIN2 = "YOU WON";
  private final String STR_LIVES = "LIVES";
  private final String STR_SCORE = "SCORE";
  private final String STR_RETRY = "SPACE * RETRY";
  private final String STR_EXIT = "ENTER * EXIT";
  private String STR_POINTS = "0";

  private Canvas() {

    try {
      this.font = Font.createFont(Font.TRUETYPE_FONT, ResourceLoader.load("8-BIT WONDER.TTF"));
      this.font = this.font.deriveFont(24.0f);
      this.font_small = this.font.deriveFont(20.0f);
    } catch (FontFormatException e) {
    } catch (IOException e) {
    }

    setSize(700, 700);
    setVisible(true);
    setFocusable(true);
  }

  @Override
  public void paintComponent(Graphics g) {
    Stage stage = Game.getStage();

    Rectangle bg = new Rectangle(0, 0, this.getWidth(), this.getHeight(), Color.BLACK);
    g.setFont(this.font);
    FontMetrics fm = g.getFontMetrics(this.font);
    FontMetrics fm2 = g.getFontMetrics(this.font_small);
    this.STR_POINTS = String.format("%04d", Game.getScore());

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
        g.drawString(this.STR_START, (this.getWidth() - fm.stringWidth(this.STR_START)) / 2, this.getHeight() / 2 + 100);
        break;
      case GAME:
        bg.paint(g);
        g.setColor(Color.WHITE);
        g.setFont(this.font_small);
        g.drawString(this.STR_LIVES, 10, this.getHeight() - 8);
        g.drawString(this.STR_SCORE, 470, this.getHeight() - 8);
        g.drawString(this.STR_POINTS, 600, this.getHeight() - 8);
        for (IPaintable obj : this.objects) {
          obj.paint(g);
        }
        break;
      case END:
        for (IPaintable obj : this.objects) {
          obj.paint(g);
        }
        g.setColor(Color.BLACK);
        g.fillRect(200, 275, 300, 200);
        g.setColor(Color.WHITE);
        g.drawString(this.STR_END, (this.getWidth() - fm.stringWidth(this.STR_END)) / 2, this.getHeight() / 2);
        g.setFont(this.font_small);
        g.drawString(this.STR_RETRY, (this.getWidth() - fm2.stringWidth(this.STR_RETRY)) / 2, this.getHeight() /2  + 70);
        g.drawString(this.STR_EXIT, (this.getWidth() - fm2.stringWidth(this.STR_EXIT)) / 2, this.getHeight() / 2 + 100);
        break;
      case WIN:
        bg.paint(g);
        g.setColor(Color.WHITE);
        g.drawString(this.STR_WIN1, (this.getWidth() - fm.stringWidth(this.STR_WIN1)) / 2, this.getHeight() / 2 -20);
        g.drawString(this.STR_WIN2, (this.getWidth() - fm.stringWidth(this.STR_WIN2)) / 2, this.getHeight() / 2 + 20);
        g.setFont(this.font_small);
        g.drawString(this.STR_RETRY, 10, this.getHeight() - 8);
        g.drawString(this.STR_EXIT, 470, this.getHeight() - 8);
        break;
    }

  }

  public void add(IPaintable obj) {
    this.objects.add(obj);
  }

  public void remove(IPaintable obj) {
    this.objects.remove(obj);
  }

  public void clear() {
    this.objects.clear();
  }

  public static Canvas getCanvas() {
    return canvas;
  }
}
