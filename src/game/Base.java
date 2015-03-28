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
  private Image image1;
  private Image image2;
  private Image image3;
  private Image image4;
  private Image image5;


  public Base(int x, int y) {
    try {
      this.image1 = ImageIO.read(new Resource("base1.png").get());
      this.image2 = ImageIO.read(new Resource("base2.png").get());
      this.image3 = ImageIO.read(new Resource("base3.png").get());
      this.image4 = ImageIO.read(new Resource("base4.png").get());
      this.image5 = ImageIO.read(new Resource("base5.png").get());

      this.image1 = this.image1.getScaledInstance(80, -1, 0);
      this.image2 = this.image2.getScaledInstance(80, -1, 0);
      this.image3 = this.image3.getScaledInstance(80, -1, 0);
      this.image4 = this.image4.getScaledInstance(80, -1, 0);
      this.image5 = this.image5.getScaledInstance(80, -1, 0);
    } catch (IOException e) {
    }

    this.image = this.image1;

    this.posX = x;
    this.posY = y;

    this.lives = 10;
  }

  public void isHit() {
    this.lives--;
    switch (this.lives) {
      case 1:
      case 2:
        this.image = this.image5;
        break;
      case 3:
      case 4:
        this.image = this.image4;
        break;
      case 5:
      case 6:
        this.image = this.image3;
        break;
      case 7:
      case 8:
        this.image = this.image2;
        break;
      case 9:
      case 10:
        this.image = this.image1;
        break;
    }
  }

  public boolean isDestroyed() {
    if (this.lives == 0) {
      Canvas.getInstance().remove(this);
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
