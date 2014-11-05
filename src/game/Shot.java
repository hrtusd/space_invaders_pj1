package game;

import graphics.ICollidable;
import graphics.IMovable;
import graphics.IPaintable;
import graphics.Rectangle;

import java.awt.Color;
import java.awt.Graphics;

public class Shot implements IPaintable, IMovable, ICollidable {
  private final Rectangle shot;
  private final int speed;

  public Shot(int x, int y) {
    this.shot = new Rectangle(x, y, 5, 25, Color.GREEN);
    this.speed = 20;
  }

  @Override
  public java.awt.Rectangle getRect() {
    // TODO Auto-generated method stub
    return this.shot.getRect();
  }

  @Override
  public boolean collideWith(ICollidable c) {
    // TODO Auto-generated method stub
    this.shot.collideWith(c);
    return false;
  }

  @Override
  public void moveRight(int step) {
    // TODO Auto-generated method stub

  }

  @Override
  public void moveLeft(int step) {
    // TODO Auto-generated method stub

  }

  @Override
  public void moveDown(int step) {
    // TODO Auto-generated method stub
    this.shot.moveDown(step);
  }

  @Override
  public void moveUp(int step) {
    // TODO Auto-generated method stub
    this.shot.moveUp(step);
  }

  @Override
  public void paint(Graphics g) {
    // TODO Auto-generated method stub
    this.shot.paint(g);
  }

  @Override
  public void move() {
    // TODO Auto-generated method stub
    this.shot.move();
  }
}
