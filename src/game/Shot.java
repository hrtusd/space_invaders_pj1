package game;

import graphics.Canvas;
import graphics.ICollidable;
import graphics.IMovable;
import graphics.IPaintable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class Shot implements IPaintable, IMovable, ICollidable {
  private int posX;
  private int posY;
  private final int width;
  private final int height;
  private final int speed;

  public Shot(int x, int y) {
    this.posX = x;
    this.posY = y;
    this.width = 5;
    this.height = 25;
    this.speed = 10;
  }

  @Override
  public java.awt.Rectangle getRect() {
    // TODO Auto-generated method stub
    return new java.awt.Rectangle(this.posX, this.posY, this.width, this.height);
  }

  @Override
  public boolean collideWith(ICollidable c) {
    // TODO Auto-generated method stub
    Rectangle2D s = this.getRect();
    Rectangle2D n = c.getRect();
    return s.intersects(n);
  }

  @Override
  public void moveRight(int step) {
    this.posX += step;
    Canvas.getCanvas().repaint();
  }

  @Override
  public void moveLeft(int step) {
    moveRight(-step);
  }

  @Override
  public void moveDown(int step) {
    this.posY += step;
    Canvas.getCanvas().repaint();
  }

  @Override
  public void moveUp(int step) {
    moveDown(-step);
  }

  @Override
  public void paint(Graphics g) {
    // TODO Auto-generated method stub
    g.setColor(Color.GREEN);
    g.fillRect(this.posX, this.posY, this.width, this.height);
  }

  @Override
  public void move() {
    // TODO Auto-generated method stub
    this.posY -= this.speed;
    Canvas.getCanvas().repaint();
  }
}
