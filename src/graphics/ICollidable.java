package graphics;

public interface ICollidable {
  public java.awt.Rectangle getRect();

  public boolean collideWith(ICollidable c);
}
