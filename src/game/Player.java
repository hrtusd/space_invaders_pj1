package game;

import graphics.Canvas;
import graphics.ICollidable;
import graphics.IPaintable;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player implements IPaintable, ICollidable {
  private Image image;
  private Image image_d;
  private int speed;
  private int posX;
  private int posY;
  private int lives;
  private Shot shot;
  private boolean can_shoot;

  public Player() {
    try {
      this.image = ImageIO.read(ResourceLoader.load("player.png"));
      this.image_d = ImageIO.read(ResourceLoader.load("destroyed.png"));
    } catch (IOException e) {
    }
    this.image = this.image.getScaledInstance(50, -1, 0);
    this.image_d = this.image_d.getScaledInstance(50, -1, 0);
    this.speed = 0;
    this.posX = Canvas.getCanvas().getWidth() / 2 - this.image.getWidth(null) / 2;
    this.posY = Canvas.getCanvas().getHeight() - this.image.getHeight(null);

    this.lives = 3;
    this.can_shoot = true;
  }

  public boolean canShoot() {
    return this.can_shoot;
  }

  public void shoot() {
    this.shot = new Shot(0, this.posX + this.image.getWidth(null) / 2 - 5 / 2, this.posY - 15);
    Canvas.getCanvas().add(this.shot);
    this.can_shoot = false;
  }

  public Shot shot() {
    return this.shot;
  }

  public void shotDestroyed() {
    Canvas.getCanvas().remove(this.shot);
    this.can_shoot = true;
  }

  public void move() {
    this.posX += this.speed;

    if (this.posX < 0) {
      this.posX = 0;
    }

    if (this.posX > Canvas.getCanvas().getWidth() - this.image.getWidth(null)) {
      this.posX = Canvas.getCanvas().getWidth() - this.image.getWidth(null);
    }
  }

  public void isHit() {
    this.lives--;
  }

  public void destroy() {
    this.image = this.image_d;
    this.posY = Canvas.getCanvas().getHeight() - this.image.getHeight(null);
    Canvas.getCanvas().repaint();
  }

  public boolean isDestroyed() {
    if (this.lives == 0) {
      return true;
    }
    else {
      return false;
    }
  }

  public void setSpeed(int s) {
    this.speed = s;
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
    // TODO Auto-generated method stub
    return false;
  }
}
