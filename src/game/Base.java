package game;

import graphics.Canvas;
import graphics.ICollidable;
import graphics.IPaintable;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Base implements IPaintable, ICollidable {

  private final int posX;
  private final int posY;
  private int lives;
  private Image image;


  public Base(int x, int y) {
    try {
      this.image = ImageIO.read(ResourceLoader.load("base.png"));
    } catch (IOException e) {
    }
    this.image = this.image.getScaledInstance(100, -1, 0);

    this.posX = x;
    this.posY = y;

    this.lives = 10;
  }

  public void isHit() {
    this.lives--;
  }

  public boolean isDestroyed() {
    if (this.lives == 0) {
      Canvas.getCanvas().remove(this);
      return true;
    }
    return false;
  }

  @Override
  public Rectangle getRect() {
    return new java.awt.Rectangle(this.posX, this.posY, this.image.getWidth(null), this.image.getHeight(null));
  }

  @Override
  public boolean collideWith(ICollidable c) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void paint(Graphics g) {
    g.drawImage(this.image, this.posX, this.posY, null);
  }

}
