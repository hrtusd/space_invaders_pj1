package game;

import graphics.Canvas;
import graphics.Rectangle;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Game extends JFrame implements KeyListener, ActionListener {
  private final Canvas canvas = Canvas.getCanvas();
  private Player player;
  private final Map<Integer, Invader> invaders = new HashMap<>();
  private final List<Shot> invader_shots = new LinkedList<>();
  private final List<Base> bases = new LinkedList<>();
  private final List<Entity> lives = new LinkedList<>();
  private Timer timer;
  private static Stage stage = Stage.START;
  private static int score = 0;
  private int time;
  private int movetime;
  private Invader remove = null;
  private int pdt;
  private int dt;
  private int di;
  private boolean down = false;
  private int moved = 0;


  public Game() {
    this.setTitle("Space Invaders");
    this.setSize(700, 700);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setVisible(true);

    this.add(this.canvas);
    this.canvas.addKeyListener(this);
  }

  public void Init() {

    this.player = new Player();
    this.canvas.add(this.player);

    this.timer = new Timer(10, this);
    this.time = 0;
    this.movetime = 50;

    int x = 100;
    int y = 500;
    for (int i = 0; i < 3; i++) {
      Base b = new Base(x + 210 * i, y);
      this.bases.add(b);
      this.canvas.add(b);
    }

    x = 20;
    y = 50;
    int s = 50;
    int type = 0;

    for (int row = 0; row < 5; row++) {
      switch (row) {
        case 0:
          type = 3;
          break;
        case 1:
        case 2:
          type = 2;
          break;
        case 3:
        case 4:
          type = 1;
          break;
      }
      for (int i = 0; i < 11; i++) {
        Invader inv = new Invader(type, x, y, s);
        this.invaders.put(i + row * 11, inv);
        this.canvas.add(inv);
        x += s;
      }
      y += s;
      x = 20;
    }

    for (Entry<Integer, Invader> invader : this.invaders.entrySet()) {
      if (invader.getKey() > 43) {
       invader.getValue().setShoot();
      }
    }

    Rectangle line = new Rectangle(0, this.canvas.getHeight() - 40, this.canvas.getWidth(), 5, Color.GREEN);
    this.canvas.add(line);

    x = 130;
    y = this.canvas.getHeight() - 30;
    for (int i = 0; i < 2; i++) {
      Entity l = new Entity("player.png", 40, -1, x + 50 * i, y);
      this.lives.add(l);
      this.canvas.add(l);
    }

    this.canvas.repaint();
    this.timer.start();
  }

  public void Reset() {
    this.lives.clear();
    this.bases.clear();
    this.invaders.clear();
    this.invader_shots.clear();
    this.player = null;
    this.time = 0;
    score = 0;
    this.canvas.clear();
  }

  public void Run() {
    switch (Game.stage) {
      case START:
        break;
      case GAME:
        Init();
        break;
      case END:
        break;
      case WIN:
        break;
    }
  }

  public static Stage getStage() {
    return stage;
  }

  public static int getScore() {
    return score;
  }

  public void moveInvaders() {
    if (this.moved == 1) {
      this.moved = 0;
      for (Entry<Integer, Invader> invader : this.invaders.entrySet()) {
        Invader i = invader.getValue();
        if (!i.canMove()) {
          this.down = true;
          return;
        }
      }

      // Invasion! (collision with base or player)
      for (Entry<Integer, Invader> invader : this.invaders.entrySet()) {
        for (Base base : this.bases) {
          if (invader.getValue().collideWith(base)) {
            this.timer.stop();
            stage = Stage.END;
            Run();
          }
        }
        if (invader.getValue().collideWith(this.player)) {
          this.timer.stop();
          stage = Stage.END;
          Run();
        }
      }
    }

    int diff = this.time % this.movetime;

    if (diff == 1) {
      for (Entry<Integer, Invader> invader : this.invaders.entrySet()) {
        Invader i = invader.getValue();
        int k = invader.getKey();
        if (k > 43 && k < 55) {
          i.move();
        }
      }
    }
    if (diff == 10) {
      for (Entry<Integer, Invader> invader : this.invaders.entrySet()) {
        Invader i = invader.getValue();
        int k = invader.getKey();
        if (k > 32 && k < 44) {
          i.move();
        }
      }
    }
    if (diff == 20) {
      for (Entry<Integer, Invader> invader : this.invaders.entrySet()) {
        Invader i = invader.getValue();
        int k = invader.getKey();
        if (k > 21 && k < 33) {
          i.move();
        }
      }
    }
    if (diff == 30) {
      for (Entry<Integer, Invader> invader : this.invaders.entrySet()) {
        Invader i = invader.getValue();
        int k = invader.getKey();
        if (k > 10 && k < 22) {
          i.move();
        }
      }
    }
    if (diff == 40) {
      for (Entry<Integer, Invader> invader : this.invaders.entrySet()) {
        Invader i = invader.getValue();
        int k = invader.getKey();
        if (k > -1 && k < 11) {
          i.move();
        }
      }
      this.moved = 1;
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (Game.stage == Stage.GAME) {
      if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        Game.this.player.setSpeed(-4);
      }
      if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        Game.this.player.setSpeed(4);
      }
      if (e.getKeyCode() == KeyEvent.VK_SPACE) {
        if (this.player.canShoot()) {
          this.player.shoot();
        }
      }
    }
    if (Game.stage == Stage.START) {
      if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        Game.stage = Stage.GAME;
        Run();
      }
    }
    if (Game.stage == Stage.END || Game.stage == Stage.WIN) {
      if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        this.dispose();
      }
      if (e.getKeyCode() == KeyEvent.VK_SPACE) {
        Game.stage = Stage.GAME;
        Reset();
        Run();
      }
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (Game.stage == Stage.GAME) {
      if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
        Game.this.player.setSpeed(0);
      }
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void actionPerformed(ActionEvent arg0) {

    // Timer increment (1 = 10ms)
    this.time++;

    // Player move
    this.player.move();

    // Player shot move, collision
    if (!this.player.canShoot()) {
      this.player.shot().move();

      // Collision with base
      for (Base base : this.bases) {
        if (this.player.shot().collideWith(base)) {
          base.isHit();
          this.player.shotDestroyed();
          if (base.isDestroyed()) {
            this.bases.remove(base);
            break;
          }
        }
      }

      // Explosion image
      for (Entry<Integer, Invader> invader : this.invaders.entrySet()) {
        if (this.player.shot().collideWith(invader.getValue())) {
          this.remove = invader.getValue();
          score += this.remove.getReward();
          this.remove.destroy();
          this.dt = this.time;
          this.di = invader.getKey();
          this.player.shotDestroyed();
          break;
        }
      }

      // Shot out of canvas
      if (!this.player.canShoot() && this.player.shot().getRect().getY() < -20 ) {
        this.player.shotDestroyed();
      }
    }

    // Completely removing invader 200ms after explosion and setShoot() to invader in upper row
    if (this.remove != null && this.time - this.dt == 20) {
      this.remove.destroyed();
      this.invaders.remove(this.di);
      this.remove = null;

      for (Entry<Integer, Invader> invader : this.invaders.entrySet()) {
        if (invader.getKey() == this.di - 11) {
          invader.getValue().setShoot();
          break;
        }
      }
    }

    // Generate invader shots
    if (this.time % 30 == 0 && this.invader_shots.size() < 3) {
      for (Entry<Integer, Invader> invader : this.invaders.entrySet()) {
        if (invader.getValue().canShoot()) {
          int rand = (int)(Math.random() * 1000 + 1);
          if (rand > 900) {
            invader.getValue().shoot();
            this.invader_shots.add(invader.getValue().shot());
            break;
          }
        }
      }
    }

    // Moving invader shots
    shots:
    for (Shot shot : this.invader_shots) {
      shot.move();

      // Out of canvas
      if (shot.getRect().getY() > this.canvas.getHeight() - 50) {
        this.invader_shots.remove(shot);
        this.canvas.remove(shot);
        break;
      }

      // Player hit
      if (shot.collideWith(this.player)) {
        // Player destroyed
        if (this.lives.isEmpty()) {
          this.player.destroy();
          this.invader_shots.remove(shot);
          this.canvas.remove(shot);
          this.timer.stop();
          stage = Stage.END;
          Run();
        }
        else {
          this.player.destroy();
          this.pdt = this.time;
          this.invader_shots.remove(shot);
          this.canvas.remove(shot);

          this.canvas.remove(this.lives.get(this.lives.size() - 1));
          this.lives.remove(this.lives.size() - 1);
        }
        break;
      }

      // Base hit
      for (Base base : this.bases) {
        if (shot.collideWith(base)) {
          base.isHit();
          if (base.isDestroyed()) {
            this.bases.remove(base);
          }
          this.invader_shots.remove(shot);
          this.canvas.remove(shot);
          break shots;
        }
      }
    }

    // Player respawn
    if (this.pdt != 0 && this.time - this.pdt == 30) {
      this.pdt = 0;
      this.player.respawn();
    }

    // Moving all invaders
    if (this.down) {
      for (Entry<Integer, Invader> invader : this.invaders.entrySet()) {
        invader.getValue().moveDown();
      }
      this.down = false;
    }
    else {
      moveInvaders();
    }

    // Player win
    if (this.invaders.isEmpty()) {
      this.timer.stop();
      stage = Stage.WIN;
      Run();
    }

    // Canvas repaint
    Canvas.getCanvas().repaint();
  }
}
