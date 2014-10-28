package game;

import java.awt.Canvas;
import java.awt.Color;

import javax.swing.JFrame;

public class Game {
  private final JFrame f;
  private final Canvas c;

  public Game() {
    this.f = new JFrame();
    this.f.setTitle("Space Invaders");
    this.f.setSize(800, 600);
    this.f.setLocationRelativeTo(null);
    this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.f.setResizable(false);
    this.f.setVisible(true);

    this.c = new Canvas();
    this.c.setSize(800, 600);
    this.c.setBackground(new Color(0, 0, 0));
    this.c.setVisible(true);

    this.f.add(this.c);
  }
}
