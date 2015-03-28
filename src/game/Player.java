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
  private Image image_s;
  private Image image_d;
  private int speed;
  private int posX;
  private int posY;
  private Shot shot;
  private boolean can_shoot;
  private boolean can_move;

  public Player() {
    try {
      this.image_s = ImageIO.read(new Resource("player.png").get());
      this.image_d = ImageIO.read(new Resource("destroyed.png").get());
    } catch (IOException e) {
    }
    this.image_s = this.image_s.getScaledInstance(40, -1, 0);
    this.image_d = this.image_d.getScaledInstance(40, -1, 0);
    this.image = this.image_s;
    this.speed = 0;
    this.posX = Canvas.getInstance().getWidth() / 2 - this.image.getWidth(null) / 2;
    this.posY = Canvas.getInstance().getHeight() - this.image.getHeight(null) - 40;

    this.can_shoot = true;
    this.can_move = true;
  }

  public boolean canShoot() {
    return this.can_shoot;
  }

  public void shoot() {
    this.shot = new Shot(0, this.posX + this.image.getWidth(null) / 2 - 5 / 2, this.posY - 15);
    Canvas.getInstance().add(this.shot);
    this.can_shoot = false;
  }

  public Shot shot() {
    return this.shot;
  }

  public void shotDestroyed() {
    Canvas.getInstance().remove(this.shot);
    this.can_shoot = true;
  }

  public void move() {
    if (!this.can_move) {
      return;
    }

    this.posX += this.speed;

    if (this.posX < 0) {
      this.posX = 0;
    }

    if (this.posX > Canvas.getInstance().getWidth() - this.image.getWidth(null)) {
      this.posX = Canvas.getInstance().getWidth() - this.image.getWidth(null);
    }
  }

  public void destroy() {
    this.image = this.image_d;
    this.posY = Canvas.getInstance().getHeight() - this.image.getHeight(null) - 40;
    this.can_move = false;
  }

  public void respawn() {
    this.image = this.image_s;
    this.posY = Canvas.getInstance().getHeight() - this.image.getHeight(null) - 40;
    this.can_move = true;
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
