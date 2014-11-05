package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class Rectangle implements IPaintable, IMovable {
  private int posX;
  private int posY;
  private final int width;
  private final int height;
  private final Color color;
  private int speed;
  private static Canvas canvas = Canvas.getCanvas();

  public Rectangle(int x, int y, int width, int height, Color color) {
    this.posX = x;
    this.posY = y;
    this.width = width;
    this.height = height;
    this.color = color;
    this.speed = 0;
  }

  public java.awt.Rectangle getRect() {
    return new java.awt.Rectangle(this.posX, this.posY, this.width, this.height);
  }

  public boolean collideWith(ICollidable c) {
    Rectangle2D s = this.getRect();
    Rectangle2D n = c.getRect();
    return s.intersects(n);
  }

  @Override
  public void move() {
    this.posX += this.speed;
  }

  public void setSpeed(int s) {
    this.speed = s;
  }

  @Override
  public void paint(Graphics g) {
    g.setColor(this.color);
    g.fillRect(this.posX, this.posY, this.width, this.height);
  }

  @Override
  public void moveRight(int step) {
    this.posX += step;
    canvas.repaint();
  }

  @Override
  public void moveLeft(int step) {
    moveRight(-step);
  }

  @Override
  public void moveDown(int step) {
    this.posY += step;
    canvas.repaint();
  }

  @Override
  public void moveUp(int step) {
    moveDown(-step);
  }
}
