package game;

import graphics.Canvas;
import graphics.IPaintable;
import graphics.Rectangle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player implements IPaintable {
  private Image image;
  private int speed;
  private int posX;
  private final int posY;
  private final int lives;
  private int shot_count;
  private Rectangle shot;

  public Player() {
    try {
      this.image = ImageIO.read(new File("src/img/player.png"));
    } catch (IOException e) {
    }
    this.image = this.image.getScaledInstance(50, -1, 0);
    this.speed = 0;
    this.posX = Canvas.getCanvas().getWidth() / 2 - this.image.getWidth(null) / 2;
    this.posY = Canvas.getCanvas().getHeight() - this.image.getHeight(null);

    this.lives = 3;
    this.shot_count = 0;
  }

  // TODO Create Shot class and return new Shot, handle in Game
  public void shoot() {
    if ( this.shot_count > 0 ) {
      return;
    }
    this.shot = new Rectangle(this.posX + this.image.getWidth(null) / 2 - 5 / 2, this.posY - 25, 5, 25, Color.GREEN);
    this.shot_count++;
    Canvas.getCanvas().add(this.shot);
    while (true) {
      try {
        if ( this.shot.getRect().getY() < 0 ) {
          break;
        }
        this.shot.moveUp(5);
        Canvas.getCanvas().repaint();

        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }
  }

  public void move() {
    this.posX += this.speed;
    Canvas.getCanvas().repaint();
  }

  public void setSpeed(int s) {
    this.speed = s;
  }

  @Override
  public void paint(Graphics g) {
    g.drawImage(this.image, this.posX, this.posY, null);
  }
}
