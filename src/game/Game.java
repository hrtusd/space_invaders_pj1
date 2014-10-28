package game;

import graphics.Canvas;
import graphics.Rectangle;

import java.awt.Color;

import javax.swing.JFrame;

public class Game {
  private final JFrame window;
  private final Canvas canvas;

  public Game() {
    this.window = new JFrame();
    this.window.setTitle("Space Invaders");
    this.window.setSize(800, 600);
    this.window.setLocationRelativeTo(null);
    this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.window.setResizable(false);
    this.window.setVisible(true);

    this.canvas = new Canvas();

    this.window.add(this.canvas);

    Run();
  }

  public void Run() {
    this.canvas.add(new Rectangle(110, 120, 200, 40, Color.RED));
    this.canvas.add(new Rectangle(10, 10, 40, 40, Color.BLACK));
    this.canvas.add(new Rectangle(400, 100, 50, 300, Color.PINK));
    this.canvas.add(new Rectangle(500, 500, 50, 40, Color.BLUE));

    this.canvas.repaint();
  }
}
