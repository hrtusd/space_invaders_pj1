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
      // TODO Auto-generated method stub
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

    Rectangle rect = new Rectangle(110, 120, 200, 40, Color.RED);
    this.canvas.add(rect);
    this.canvas.add(new Rectangle(10, 10, 40, 40, Color.BLACK));
    this.canvas.add(new Rectangle(400, 100, 50, 300, Color.PINK));
    this.canvas.add(new Rectangle(500, 500, 50, 40, Color.BLUE));

    rect.moveRight(100);
    rect.moveDown(200);

    this.player = new Rectangle(375, 550, 50, 20, Color.GREEN);
    this.canvas.add(this.player);

    while (true) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

  }
}
