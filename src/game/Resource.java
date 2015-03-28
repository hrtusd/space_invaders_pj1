package game;

import java.io.InputStream;

public class Resource {

  private final String path;

  public Resource(String path) {
    this.path = path;
  }

  public InputStream get() {
    InputStream is = Resource.class.getResourceAsStream(this.path);
    if (is == null) {
      is = Resource.class.getResourceAsStream("/" + this.path);
    }
    return is;
  }
}
