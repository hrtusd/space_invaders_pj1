package game;

import graphics.IPaintable;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Entity implements IPaintable {

  private Image image;
  private int x;
  private int y;

  public Entity(String path, int scale_x, int scale_y, int x, int y) {
    try {
      this.image = ImageIO.read(new Resource(path).get());
      this.image = this.image.getScaledInstance(scale_x, scale_y, 0);
      this.x = x;
      this.y = y;
    } catch (IOException e) {
    }
  }

  @Override
  public void paint(Graphics g) {
    g.drawImage(this.image, this.x, this.y, null);
  }

}
