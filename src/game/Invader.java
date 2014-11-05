package game;

import graphics.ICollidable;
import graphics.IPaintable;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Invader implements IPaintable, ICollidable {
  private Image image;
  private int reward;
  private final int step;
  private final int posX;
  private final int posY;

  public Invader(int type, int x, int y, int size) {
    this.step = 5;

    switch (type) {
      case 1:
        try {
          this.image = ImageIO.read(ResourceLoader.load("inv1a.png"));
        } catch (IOException e) {
        }
        this.reward = 10;
        break;
      case 2:
        try {
          this.image = ImageIO.read(ResourceLoader.load("inv2a.png"));
        } catch (IOException e) {
        }
        this.reward = 20;
        break;
      case 3:
        try {
          this.image = ImageIO.read(ResourceLoader.load("inv3a.png"));
        } catch (IOException e) {
        }
        this.reward = 30;
        break;
    }

    this.image = this.image.getScaledInstance(-1, 30, 0);

    this.posX = x + ( size - this.image.getWidth(null) ) / 2;
    this.posY = y + ( size - this.image.getHeight(null) ) / 2;
  }

  @Override
  public void paint(Graphics g) {
    g.drawImage(this.image, this.posX, this.posY, null);
  }

  @Override
  public Rectangle getRect() {
    // TODO Auto-generated method stub
    return new Rectangle(this.posX, this.posY, this.image.getWidth(null), this.image.getHeight(null));
  }

  @Override
  public boolean collideWith(ICollidable c) {
    // TODO Auto-generated method stub
    return false;
  }

}
