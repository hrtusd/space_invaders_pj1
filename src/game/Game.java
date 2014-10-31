package game;

import graphics.Canvas;
import graphics.Rectangle;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Game {
  private final JFrame window;
  private final Canvas canvas = Canvas.getCanvas();
  private final int step = 5;
  private Rectangle player;

  public class SI_KeyListener implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {
      int key = e.getKeyCode();
      if (key == KeyEvent.VK_LEFT) {
        Game.this.player.moveLeft(Game.this.step);
      }

      if (key == KeyEvent.VK_RIGHT) {
        Game.this.player.moveRight(Game.this.step);
      }

      if (key == KeyEvent.VK_UP) {
        Game.this.player.moveUp(Game.this.step);
      }

      if (key == KeyEvent.VK_DOWN) {
        Game.this.player.moveDown(Game.this.step);
      }
    }

    @Override
    public void keyReleased(KeyEvent e) {
      // TODO Auto-generated method stub
    }

    @Override
    public void keyTyped(KeyEvent e) {
      // TODO Auto-generated method stub

    }

  }

  public Game() {
    this.window = new JFrame();
    this.window.setTitle("Space Invaders");
    this.window.setSize(800, 600);
    this.window.setLocationRelativeTo(null);
    this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.window.setResizable(false);
    this.window.setVisible(true);

    this.window.add(this.canvas);

    Run();
  }

  public void Run() {
    this.canvas.addKeyListener(new SI_KeyListener());
    this.canvas.setFocusable(true);

    this.canvas.add(new Rectangle(0, 0, 800, 600, Color.BLACK));

    Rectangle rect = new Rectangle(110, 120, 200, 40, Color.RED);
    this.canvas.add(rect);
    this.canvas.add(new Rectangle(400, 100, 50, 300, Color.PINK));
    this.canvas.add(new Rectangle(500, 500, 50, 40, Color.BLUE));

    rect.moveRight(100);
    rect.moveDown(200);

    this.player = new Rectangle(375, 550, 50, 20, Color.GREEN);
    this.canvas.add(this.player);

    Rectangle s = new Rectangle(395, 520, 10, 30, Color.WHITE);
    this.canvas.add(s);

    int sy = 2;

    while (true) {
      try {
        if (s.collideWith(rect)) {
          sy = 0;
          this.canvas.remove(rect);
        }
        s.moveUp(sy);
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }
}
