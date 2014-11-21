package game;

import graphics.ICollidable;
import graphics.IPaintable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class Shot implements IPaintable, ICollidable {
  private final int posX;
  private int posY;
  private final int width;
  private final int height;
  private int speed;
  private final int type;

  public Shot(int type, int x, int y) {

    this.posX = x;
    this.posY = y;
    this.width = 5;
    this.height = 15;
    this.type = type;

    switch (this.type) {
      case 0:
        this.speed = -10;
        break;
      case 1:
        this.speed = 5;
        break;
      case 2:
        this.speed = 10;
        break;
    }
  }

  @Override
  public java.awt.Rectangle getRect() {
    return new java.awt.Rectangle(this.posX, this.posY, this.width, this.height);
  }

  @Override
  public boolean collideWith(ICollidable c) {
    Rectangle2D s = this.getRect();
    Rectangle2D n = c.getRect();
    return s.intersects(n);
  }

  @Override
  public void paint(Graphics g) {
    switch (this.type) {
      case 0:
        g.setColor(Color.GREEN);
        break;
      case 1:
        g.setColor(Color.WHITE);
        break;
      case 2:
        g.setColor(Color.RED);
        break;
    }
    g.fillRect(this.posX, this.posY, this.width, this.height);
  }

  public void move() {
    this.posY += this.speed;
  }
}
