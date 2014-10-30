package graphics;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle implements IPaintable, IMovable {
  private int posX;
  private int posY;
  private final int width;
  private final int height;
  private final Color color;
  private static Canvas canvas = Canvas.getCanvas();

  public Rectangle(int x, int y, int width, int height, Color color) {
    this.posX = x;
    this.posY = y;
    this.width = width;
    this.height = height;
    this.color = color;
  }

  @Override
  public void paint(Graphics g) {
    g.setColor(this.color);
    g.fillRect(this.posX, this.posY, this.width, this.height);
  }

  @Override
  public void moveRight(int step) {
    // TODO Auto-generated method stub
    this.posX += step;
    canvas.repaint();
  }

  @Override
  public void moveLeft(int step) {
    // TODO Auto-generated method stub
    moveRight(-step);
  }

  @Override
  public void moveDown(int step) {
    // TODO Auto-generated method stub
    this.posY += step;
    canvas.repaint();
  }

  @Override
  public void moveUp(int step) {
    // TODO Auto-generated method stub
    moveDown(-step);
  }
}
