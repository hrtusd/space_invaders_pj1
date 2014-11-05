package game;

import graphics.Canvas;
import graphics.Rectangle;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game extends JFrame implements KeyListener {
  private final Canvas canvas = Canvas.getCanvas();
  private Player player;

  public Game() {
    this.setTitle("Space Invaders");
    this.setSize(800, 600);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setVisible(true);

    this.add(this.canvas);

    Run();
  }

  public void Run() {
    this.canvas.addKeyListener(this);
    this.canvas.setFocusable(true);

    Rectangle bg = new Rectangle(0, 0, this.canvas.getWidth(), this.canvas.getHeight(), Color.BLACK);
    this.canvas.add(bg);

    this.player = new Player();
    this.canvas.add(this.player);

    int x = 10;
    int y = 10;
    int s = 55;
    int type = 0;

    for (int row = 0; row < 5; row++) {
      switch (row) {
        case 0:
          type = 3;
          break;
        case 1:
        case 2:
          type = 2;
          break;
        case 3:
        case 4:
          type = 1;
          break;
      }
      for (int i = 0; i < 11; i++) {
        this.canvas.add(new Invader(type, x, y, s));
        x += s;
      }
      y += s;
      x = 10;
    }
    this.canvas.repaint();

    // Game loop
    while (true) {
      try {
        this.player.move();

        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      Game.this.player.setSpeed(-2);
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      Game.this.player.setSpeed(2);
    }
    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      Game.this.player.shoot();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
      Game.this.player.setSpeed(0);
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }
}
