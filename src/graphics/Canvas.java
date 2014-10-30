package graphics;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Canvas extends JPanel {

  private final List<IPaintable> objects = new ArrayList<IPaintable>();

  private static final Canvas canvas = new Canvas();

  public Canvas() {

  }

  @Override
  public void paint(Graphics g) {
    for (IPaintable obj : this.objects) {
      obj.paint(g);
    }
  }

  public void add(IPaintable obj) {
    this.objects.add(obj);
  }

  public void remove(IPaintable obj) {
    this.objects.remove(obj);
  }

  public static Canvas getCanvas() {
    return canvas;
  }
}
