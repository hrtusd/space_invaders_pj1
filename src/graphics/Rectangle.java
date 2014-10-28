package graphics;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle implements IPaintable {
  private final int posX;
  private final int posY;
  private final int width;
  private final int height;
  private final Color color;

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
}
