package game;

import graphics.Canvas;
import graphics.IPaintable;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player implements IPaintable {
  private Image image;
  private int speed;
  private int posX;
  private final int posY;
  private final int lives;
  private int shot_count;
  private Shot shot;

  public Player() {
    try {
      //this.image = ImageIO.read(new File("src/img/player.png"));
      this.image = ImageIO.read(ResourceLoader.load("player.png"));
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
  public Shot shoot() {
    if ( this.shot_count > 0 ) {
      return this.shot;
    }
    this.shot = new Shot(this.posX + this.image.getWidth(null) / 2 - 5 / 2, this.posY - 25);
    this.shot_count++;
    return this.shot;
  }

  public void shotDestroyed() {
    this.shot_count = 0;
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
