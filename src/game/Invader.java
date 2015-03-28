package game;

import graphics.Canvas;
import graphics.ICollidable;
import graphics.IPaintable;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Invader implements IPaintable, ICollidable {
  private Image image;
  private Image image2;
  private Image image_d;
  private int reward;
  private int stepX;
  private final int stepY;
  private int posX;
  private int posY;
  private boolean can_shoot;
  private Shot shot;
  private boolean move_down;

  public Invader(int type, int x, int y, int size) {
    this.stepX = 9;
    this.stepY = 40;
    this.can_shoot = false;
    this.move_down = false;

    switch (type) {
      case 1:
        try {
          this.image = ImageIO.read(new Resource("inv1a.png").get());
          this.image2 = ImageIO.read(new Resource("inv1b.png").get());
        } catch (IOException e) {
        }
        this.reward = 10;
        break;
      case 2:
        try {
          this.image = ImageIO.read(new Resource("inv2a.png").get());
          this.image2 = ImageIO.read(new Resource("inv2b.png").get());
        } catch (IOException e) {
        }
        this.reward = 20;
        break;
      case 3:
        try {
          this.image = ImageIO.read(new Resource("inv3a.png").get());
          this.image2 = ImageIO.read(new Resource("inv3b.png").get());
        } catch (IOException e) {
        }
        this.reward = 30;
        break;
    }

    try {
      this.image_d = ImageIO.read(new Resource("explosion.png").get());
    } catch (IOException e) {
    }

    this.image = this.image.getScaledInstance(-1, 25, 0);
    this.image2 = this.image2.getScaledInstance(-1, 25, 0);
    this.image_d = this.image_d.getScaledInstance(-1, 20, 0);

    this.posX = x + ( size - this.image.getWidth(null) ) / 2;
    this.posY = y + ( size - this.image.getHeight(null) ) / 2;
  }

  public void destroy() {
    this.posX += ( this.image.getWidth(null) - this.image_d.getWidth(null) ) / 2;
    this.posY += ( this.image.getHeight(null) - this.image_d.getHeight(null) ) / 2;

    this.image = this.image_d;
    this.image2 = this.image_d;
  }

  public void destroyed() {
    Canvas.getInstance().remove(this);
  }

  public void swap() {
    Image tmp = this.image;
    this.image = this.image2;
    this.image2 = tmp;
  }

  public boolean canMove() {
    if (this.posX + this.stepX > Canvas.getInstance().getWidth() - this.image.getWidth(null) || this.posX + this.stepX < 0) {
      this.move_down = true;
      return false;
    }
    return true;
  }

  public void move() {
    this.posX += this.stepX;
    swap();
  }

  public void moveDown() {
    this.posY += this.stepY;
    this.stepX *= -1;
    this.move_down = false;
    move();
  }

  public int getReward() {
    return this.reward;
  }

  public boolean canShoot() {
    return this.can_shoot;
  }

  public void setShoot() {
    this.can_shoot = true;
  }

  public void shoot() {
    this.shot = new Shot(1, this.posX + this.image.getWidth(null) / 2 - 5 / 2, this.posY + this.image.getHeight(null));
    Canvas.getInstance().add(this.shot);
  }

  public Shot shot() {
    return this.shot;
  }

  public boolean down() {
    return this.move_down;
  }

  @Override
  public void paint(Graphics g) {
    g.drawImage(this.image, this.posX, this.posY, null);
  }

  @Override
  public Rectangle getRect() {
    return new Rectangle(this.posX, this.posY, this.image.getWidth(null), this.image.getHeight(null));
  }

  @Override
  public boolean collideWith(ICollidable c) {
    Rectangle2D s = this.getRect();
    Rectangle2D n = c.getRect();
    return s.intersects(n);
  }

}
