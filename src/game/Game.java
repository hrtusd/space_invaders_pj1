package game;

import java.awt.Canvas;

import javax.swing.JFrame;

public class Game {
  private final JFrame f;
  private Canvas c;

  public Game() {
    this.f = new JFrame();
    this.f.setTitle("Space Invaders");
    this.f.setSize(800, 600);
    this.f.setLocationRelativeTo(null);
    this.f.setVisible(true);
    this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.f.setResizable(false);
  }
}
