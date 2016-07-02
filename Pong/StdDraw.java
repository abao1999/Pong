/*      */ package Pong;
/*      */ 
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.FileDialog;
/*      */ import java.awt.Font;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Image;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.Toolkit;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.KeyEvent;
/*      */ import java.awt.event.KeyListener;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.awt.event.MouseListener;
/*      */ import java.awt.event.MouseMotionListener;
/*      */ import java.awt.geom.Arc2D.Double;
/*      */ import java.awt.geom.Ellipse2D.Double;
/*      */ import java.awt.geom.GeneralPath;
/*      */ import java.awt.geom.Line2D.Double;
/*      */ import java.awt.geom.Rectangle2D.Double;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.awt.image.DirectColorModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.io.PrintStream;
/*      */ import java.net.URL;
/*      */ import java.util.LinkedList;
/*      */ import java.util.TreeSet;
/*      */ import javax.imageio.ImageIO;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.swing.JFrame;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JMenu;
/*      */ import javax.swing.JMenuBar;
/*      */ import javax.swing.JMenuItem;
/*      */ import javax.swing.KeyStroke;
/*      */ 
/*      */ public final class StdDraw
/*      */   implements ActionListener, MouseListener, MouseMotionListener, KeyListener
/*      */ {
/*   28 */   public static final Color BLACK = Color.BLACK;
/*   29 */   public static final Color BLUE = Color.BLUE;
/*   30 */   public static final Color CYAN = Color.CYAN;
/*   31 */   public static final Color DARK_GRAY = Color.DARK_GRAY;
/*   32 */   public static final Color GRAY = Color.GRAY;
/*   33 */   public static final Color GREEN = Color.GREEN;
/*   34 */   public static final Color LIGHT_GRAY = Color.LIGHT_GRAY;
/*   35 */   public static final Color MAGENTA = Color.MAGENTA;
/*   36 */   public static final Color ORANGE = Color.ORANGE;
/*   37 */   public static final Color PINK = Color.PINK;
/*   38 */   public static final Color RED = Color.RED;
/*   39 */   public static final Color WHITE = Color.WHITE;
/*   40 */   public static final Color YELLOW = Color.YELLOW;
/*      */ 
/*   46 */   public static final Color BOOK_BLUE = new Color(9, 90, 166);
/*   47 */   public static final Color BOOK_LIGHT_BLUE = new Color(103, 198, 243);
/*      */ 
/*   53 */   public static final Color BOOK_RED = new Color(150, 35, 31);
/*      */ 
/*   56 */   private static final Color DEFAULT_PEN_COLOR = BLACK;
/*   57 */   private static final Color DEFAULT_CLEAR_COLOR = WHITE;
/*      */   private static Color penColor;
/*      */   private static final int DEFAULT_SIZE = 725;
/*   64 */   private static int width = 725;
/*   65 */   private static int height = 725;
/*      */   private static final double DEFAULT_PEN_RADIUS = 0.002D;
/*      */   private static double penRadius;
/*   74 */   private static boolean defer = false;
/*      */   private static final double BORDER = 0.05D;
/*      */   private static final double DEFAULT_XMIN = 0.0D;
/*      */   private static final double DEFAULT_XMAX = 1.0D;
/*      */   private static final double DEFAULT_YMIN = 0.0D;
/*      */   private static final double DEFAULT_YMAX = 1.0D;
/*      */   private static double xmin;
/*      */   private static double ymin;
/*      */   private static double xmax;
/*      */   private static double ymax;
/*   85 */   private static Object mouseLock = new Object();
/*   86 */   private static Object keyLock = new Object();
/*      */ 
/*   89 */   private static final Font DEFAULT_FONT = new Font("SansSerif", 0, 16);
/*      */   private static Font font;
/*      */   private static BufferedImage offscreenImage;
/*      */   private static BufferedImage onscreenImage;
/*      */   private static Graphics2D offscreen;
/*      */   private static Graphics2D onscreen;
/*   99 */   private static StdDraw std = new StdDraw();
/*      */   private static JFrame frame;
/*  105 */   private static boolean mousePressed = false;
/*  106 */   private static double mouseX = 0.0D;
/*  107 */   private static double mouseY = 0.0D;
/*      */ 
/*  110 */   private static LinkedList<Character> keysTyped = new LinkedList();
/*      */ 
/*  113 */   private static TreeSet<Integer> keysDown = new TreeSet();
/*      */ 
/*      */   static
/*      */   {
/*  121 */     init();
/*      */   }
/*      */ 
/*      */   public static void setCanvasSize()
/*      */   {
/*  128 */     setCanvasSize(725, 725);
/*      */   }
/*      */ 
/*      */   public static void setCanvasSize(int w, int h)
/*      */   {
/*  140 */     if ((w < 1) || (h < 1)) throw new IllegalArgumentException("width and height must be positive");
/*  141 */     width = w;
/*  142 */     height = h;
/*  143 */     init();
/*      */   }
/*      */ 
/*      */   private static void init()
/*      */   {
/*  148 */     if (frame != null) frame.setVisible(false);
/*  149 */     frame = new JFrame();
/*  150 */     offscreenImage = new BufferedImage(width, height, 2);
/*  151 */     onscreenImage = new BufferedImage(width, height, 2);
/*  152 */     offscreen = offscreenImage.createGraphics();
/*  153 */     onscreen = onscreenImage.createGraphics();
/*  154 */     setXscale();
/*  155 */     setYscale();
/*  156 */     offscreen.setColor(DEFAULT_CLEAR_COLOR);
/*  157 */     offscreen.fillRect(0, 0, width, height);
/*  158 */     setPenColor();
/*  159 */     setPenRadius();
/*  160 */     setFont();
/*  161 */     clear();
/*      */ 
/*  164 */     RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, 
/*  165 */       RenderingHints.VALUE_ANTIALIAS_ON);
/*  166 */     hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
/*  167 */     offscreen.addRenderingHints(hints);
/*      */ 
/*  170 */     ImageIcon icon = new ImageIcon(onscreenImage);
/*  171 */     JLabel draw = new JLabel(icon);
/*      */ 
/*  173 */     draw.addMouseListener(std);
/*  174 */     draw.addMouseMotionListener(std);
/*      */ 
/*  176 */     frame.setContentPane(draw);
/*  177 */     frame.addKeyListener(std);
/*  178 */     frame.setResizable(false);
/*  179 */     frame.setDefaultCloseOperation(3);
/*      */ 
/*  181 */     frame.setTitle("Standard Draw");
/*  182 */     frame.setJMenuBar(createMenuBar());
/*  183 */     frame.pack();
/*  184 */     frame.requestFocusInWindow();
/*  185 */     frame.setVisible(true);
/*      */   }
/*      */ 
/*      */   private static JMenuBar createMenuBar()
/*      */   {
/*  190 */     JMenuBar menuBar = new JMenuBar();
/*  191 */     JMenu menu = new JMenu("File");
/*  192 */     menuBar.add(menu);
/*  193 */     JMenuItem menuItem1 = new JMenuItem(" Save...   ");
/*  194 */     menuItem1.addActionListener(std);
/*  195 */     menuItem1.setAccelerator(KeyStroke.getKeyStroke(83, 
/*  196 */       Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
/*  197 */     menu.add(menuItem1);
/*  198 */     return menuBar;
/*      */   }
/*      */ 
/*      */   public static void setXscale()
/*      */   {
/*  209 */     setXscale(0.0D, 1.0D);
/*      */   }
/*      */ 
/*      */   public static void setYscale()
/*      */   {
/*  214 */     setYscale(0.0D, 1.0D);
/*      */   }
/*      */ 
/*      */   public static void setXscale(double min, double max)
/*      */   {
/*  222 */     double size = max - min;
/*  223 */     synchronized (mouseLock) {
/*  224 */       xmin = min - 0.05D * size;
/*  225 */       xmax = max + 0.05D * size;
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void setYscale(double min, double max)
/*      */   {
/*  235 */     double size = max - min;
/*  236 */     synchronized (mouseLock) {
/*  237 */       ymin = min - 0.05D * size;
/*  238 */       ymax = max + 0.05D * size;
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void setScale(double min, double max)
/*      */   {
/*  248 */     double size = max - min;
/*  249 */     synchronized (mouseLock) {
/*  250 */       xmin = min - 0.05D * size;
/*  251 */       xmax = max + 0.05D * size;
/*  252 */       ymin = min - 0.05D * size;
/*  253 */       ymax = max + 0.05D * size;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static double scaleX(double x) {
/*  258 */     return width * (x - xmin) / (xmax - xmin); } 
/*  259 */   private static double scaleY(double y) { return height * (ymax - y) / (ymax - ymin); } 
/*  260 */   private static double factorX(double w) { return w * width / Math.abs(xmax - xmin); } 
/*  261 */   private static double factorY(double h) { return h * height / Math.abs(ymax - ymin); } 
/*  262 */   private static double userX(double x) { return xmin + x * (xmax - xmin) / width; } 
/*  263 */   private static double userY(double y) { return ymax - y * (ymax - ymin) / height;
/*      */   }
/*      */ 
/*      */   public static void clear()
/*      */   {
/*  269 */     clear(DEFAULT_CLEAR_COLOR);
/*      */   }
/*      */ 
/*      */   public static void clear(Color color)
/*      */   {
/*  275 */     offscreen.setColor(color);
/*  276 */     offscreen.fillRect(0, 0, width, height);
/*  277 */     offscreen.setColor(penColor);
/*  278 */     draw();
/*      */   }
/*      */ 
/*      */   public static double getPenRadius()
/*      */   {
/*  284 */     return penRadius;
/*      */   }
/*      */ 
/*      */   public static void setPenRadius()
/*      */   {
/*  289 */     setPenRadius(0.002D);
/*      */   }
/*      */ 
/*      */   public static void setPenRadius(double r)
/*      */   {
/*  296 */     if (r < 0.0D) throw new IllegalArgumentException("pen radius must be nonnegative");
/*  297 */     penRadius = r;
/*  298 */     float scaledPenRadius = (float)(r * 725.0D);
/*  299 */     BasicStroke stroke = new BasicStroke(scaledPenRadius, 1, 1);
/*      */ 
/*  301 */     offscreen.setStroke(stroke);
/*      */   }
/*      */ 
/*      */   public static Color getPenColor()
/*      */   {
/*  307 */     return penColor;
/*      */   }
/*      */ 
/*      */   public static void setPenColor()
/*      */   {
/*  312 */     setPenColor(DEFAULT_PEN_COLOR);
/*      */   }
/*      */ 
/*      */   public static void setPenColor(Color color)
/*      */   {
/*  321 */     penColor = color;
/*  322 */     offscreen.setColor(penColor);
/*      */   }
/*      */ 
/*      */   public static void setPenColor(int red, int green, int blue)
/*      */   {
/*  333 */     if ((red < 0) || (red >= 256)) throw new IllegalArgumentException("amount of red must be between 0 and 255");
/*  334 */     if ((green < 0) || (green >= 256)) throw new IllegalArgumentException("amount of red must be between 0 and 255");
/*  335 */     if ((blue < 0) || (blue >= 256)) throw new IllegalArgumentException("amount of red must be between 0 and 255");
/*  336 */     setPenColor(new Color(red, green, blue));
/*      */   }
/*      */ 
/*      */   public static Font getFont()
/*      */   {
/*  342 */     return font;
/*      */   }
/*      */ 
/*      */   public static void setFont()
/*      */   {
/*  347 */     setFont(DEFAULT_FONT);
/*      */   }
/*      */ 
/*      */   public static void setFont(Font f)
/*      */   {
/*  353 */     font = f;
/*      */   }
/*      */ 
/*      */   public static void line(double x0, double y0, double x1, double y1)
/*      */   {
/*  368 */     offscreen.draw(new Line2D.Double(scaleX(x0), scaleY(y0), scaleX(x1), scaleY(y1)));
/*  369 */     draw();
/*      */   }
/*      */ 
/*      */   private static void pixel(double x, double y)
/*      */   {
/*  378 */     offscreen.fillRect((int)Math.round(scaleX(x)), (int)Math.round(scaleY(y)), 1, 1);
/*      */   }
/*      */ 
/*      */   public static void point(double x, double y)
/*      */   {
/*  387 */     double xs = scaleX(x);
/*  388 */     double ys = scaleY(y);
/*  389 */     double r = penRadius;
/*  390 */     float scaledPenRadius = (float)(r * 725.0D);
/*      */ 
/*  395 */     if (scaledPenRadius <= 1.0F) pixel(x, y); else
/*  396 */       offscreen.fill(
/*  397 */         new Ellipse2D.Double(xs - scaledPenRadius / 2.0F, ys - scaledPenRadius / 2.0F, 
/*  397 */         scaledPenRadius, scaledPenRadius));
/*  398 */     draw();
/*      */   }
/*      */ 
/*      */   public static void circle(double x, double y, double r)
/*      */   {
/*  409 */     if (r < 0.0D) throw new IllegalArgumentException("circle radius must be nonnegative");
/*  410 */     double xs = scaleX(x);
/*  411 */     double ys = scaleY(y);
/*  412 */     double ws = factorX(2.0D * r);
/*  413 */     double hs = factorY(2.0D * r);
/*  414 */     if ((ws <= 1.0D) && (hs <= 1.0D)) pixel(x, y); else
/*  415 */       offscreen.draw(new Ellipse2D.Double(xs - ws / 2.0D, ys - hs / 2.0D, ws, hs));
/*  416 */     draw();
/*      */   }
/*      */ 
/*      */   public static void filledCircle(double x, double y, double r)
/*      */   {
/*  427 */     if (r < 0.0D) throw new IllegalArgumentException("circle radius must be nonnegative");
/*  428 */     double xs = scaleX(x);
/*  429 */     double ys = scaleY(y);
/*  430 */     double ws = factorX(2.0D * r);
/*  431 */     double hs = factorY(2.0D * r);
/*  432 */     if ((ws <= 1.0D) && (hs <= 1.0D)) pixel(x, y); else
/*  433 */       offscreen.fill(new Ellipse2D.Double(xs - ws / 2.0D, ys - hs / 2.0D, ws, hs));
/*  434 */     draw();
/*      */   }
/*      */ 
/*      */   public static void ellipse(double x, double y, double semiMajorAxis, double semiMinorAxis)
/*      */   {
/*  447 */     if (semiMajorAxis < 0.0D) throw new IllegalArgumentException("ellipse semimajor axis must be nonnegative");
/*  448 */     if (semiMinorAxis < 0.0D) throw new IllegalArgumentException("ellipse semiminor axis must be nonnegative");
/*  449 */     double xs = scaleX(x);
/*  450 */     double ys = scaleY(y);
/*  451 */     double ws = factorX(2.0D * semiMajorAxis);
/*  452 */     double hs = factorY(2.0D * semiMinorAxis);
/*  453 */     if ((ws <= 1.0D) && (hs <= 1.0D)) pixel(x, y); else
/*  454 */       offscreen.draw(new Ellipse2D.Double(xs - ws / 2.0D, ys - hs / 2.0D, ws, hs));
/*  455 */     draw();
/*      */   }
/*      */ 
/*      */   public static void filledEllipse(double x, double y, double semiMajorAxis, double semiMinorAxis)
/*      */   {
/*  467 */     if (semiMajorAxis < 0.0D) throw new IllegalArgumentException("ellipse semimajor axis must be nonnegative");
/*  468 */     if (semiMinorAxis < 0.0D) throw new IllegalArgumentException("ellipse semiminor axis must be nonnegative");
/*  469 */     double xs = scaleX(x);
/*  470 */     double ys = scaleY(y);
/*  471 */     double ws = factorX(2.0D * semiMajorAxis);
/*  472 */     double hs = factorY(2.0D * semiMinorAxis);
/*  473 */     if ((ws <= 1.0D) && (hs <= 1.0D)) pixel(x, y); else
/*  474 */       offscreen.fill(new Ellipse2D.Double(xs - ws / 2.0D, ys - hs / 2.0D, ws, hs));
/*  475 */     draw();
/*      */   }
/*      */ 
/*      */   public static void arc(double x, double y, double r, double angle1, double angle2)
/*      */   {
/*  490 */     if (r < 0.0D) throw new IllegalArgumentException("arc radius must be nonnegative"); do
/*  491 */       angle2 += 360.0D; while (angle2 < angle1);
/*  492 */     double xs = scaleX(x);
/*  493 */     double ys = scaleY(y);
/*  494 */     double ws = factorX(2.0D * r);
/*  495 */     double hs = factorY(2.0D * r);
/*  496 */     if ((ws <= 1.0D) && (hs <= 1.0D)) pixel(x, y); else
/*  497 */       offscreen.draw(new Arc2D.Double(xs - ws / 2.0D, ys - hs / 2.0D, ws, hs, angle1, angle2 - angle1, 0));
/*  498 */     draw();
/*      */   }
/*      */ 
/*      */   public static void square(double x, double y, double r)
/*      */   {
/*  509 */     if (r < 0.0D) throw new IllegalArgumentException("square side length must be nonnegative");
/*  510 */     double xs = scaleX(x);
/*  511 */     double ys = scaleY(y);
/*  512 */     double ws = factorX(2.0D * r);
/*  513 */     double hs = factorY(2.0D * r);
/*  514 */     if ((ws <= 1.0D) && (hs <= 1.0D)) pixel(x, y); else
/*  515 */       offscreen.draw(new Rectangle2D.Double(xs - ws / 2.0D, ys - hs / 2.0D, ws, hs));
/*  516 */     draw();
/*      */   }
/*      */ 
/*      */   public static void filledSquare(double x, double y, double r)
/*      */   {
/*  527 */     if (r < 0.0D) throw new IllegalArgumentException("square side length must be nonnegative");
/*  528 */     double xs = scaleX(x);
/*  529 */     double ys = scaleY(y);
/*  530 */     double ws = factorX(2.0D * r);
/*  531 */     double hs = factorY(2.0D * r);
/*  532 */     if ((ws <= 1.0D) && (hs <= 1.0D)) pixel(x, y); else
/*  533 */       offscreen.fill(new Rectangle2D.Double(xs - ws / 2.0D, ys - hs / 2.0D, ws, hs));
/*  534 */     draw();
/*      */   }
/*      */ 
/*      */   public static void rectangle(double x, double y, double halfWidth, double halfHeight)
/*      */   {
/*  547 */     if (halfWidth < 0.0D) throw new IllegalArgumentException("half width must be nonnegative");
/*  548 */     if (halfHeight < 0.0D) throw new IllegalArgumentException("half height must be nonnegative");
/*  549 */     double xs = scaleX(x);
/*  550 */     double ys = scaleY(y);
/*  551 */     double ws = factorX(2.0D * halfWidth);
/*  552 */     double hs = factorY(2.0D * halfHeight);
/*  553 */     if ((ws <= 1.0D) && (hs <= 1.0D)) pixel(x, y); else
/*  554 */       offscreen.draw(new Rectangle2D.Double(xs - ws / 2.0D, ys - hs / 2.0D, ws, hs));
/*  555 */     draw();
/*      */   }
/*      */ 
/*      */   public static void filledRectangle(double x, double y, double halfWidth, double halfHeight)
/*      */   {
/*  567 */     if (halfWidth < 0.0D) throw new IllegalArgumentException("half width must be nonnegative");
/*  568 */     if (halfHeight < 0.0D) throw new IllegalArgumentException("half height must be nonnegative");
/*  569 */     double xs = scaleX(x);
/*  570 */     double ys = scaleY(y);
/*  571 */     double ws = factorX(2.0D * halfWidth);
/*  572 */     double hs = factorY(2.0D * halfHeight);
/*  573 */     if ((ws <= 1.0D) && (hs <= 1.0D)) pixel(x, y); else
/*  574 */       offscreen.fill(new Rectangle2D.Double(xs - ws / 2.0D, ys - hs / 2.0D, ws, hs));
/*  575 */     draw();
/*      */   }
/*      */ 
/*      */   public static void polygon(double[] x, double[] y)
/*      */   {
/*  585 */     int N = x.length;
/*  586 */     GeneralPath path = new GeneralPath();
/*  587 */     path.moveTo((float)scaleX(x[0]), (float)scaleY(y[0]));
/*  588 */     for (int i = 0; i < N; i++)
/*  589 */       path.lineTo((float)scaleX(x[i]), (float)scaleY(y[i]));
/*  590 */     path.closePath();
/*  591 */     offscreen.draw(path);
/*  592 */     draw();
/*      */   }
/*      */ 
/*      */   public static void filledPolygon(double[] x, double[] y)
/*      */   {
/*  601 */     int N = x.length;
/*  602 */     GeneralPath path = new GeneralPath();
/*  603 */     path.moveTo((float)scaleX(x[0]), (float)scaleY(y[0]));
/*  604 */     for (int i = 0; i < N; i++)
/*  605 */       path.lineTo((float)scaleX(x[i]), (float)scaleY(y[i]));
/*  606 */     path.closePath();
/*  607 */     offscreen.fill(path);
/*  608 */     draw();
/*      */   }
/*      */ 
/*      */   private static Image getImage(String filename)
/*      */   {
/*  621 */     ImageIcon icon = new ImageIcon(filename);
/*      */ 
/*  624 */     if ((icon == null) || (icon.getImageLoadStatus() != 8))
/*      */       try {
/*  626 */         URL url = new URL(filename);
/*  627 */         icon = new ImageIcon(url);
/*      */       }
/*      */       catch (Exception localException)
/*      */       {
/*      */       }
/*  632 */     if ((icon == null) || (icon.getImageLoadStatus() != 8)) {
/*  633 */       URL url = StdDraw.class.getResource(filename);
/*  634 */       if (url == null) throw new IllegalArgumentException("image " + filename + " not found");
/*  635 */       icon = new ImageIcon(url);
/*      */     }
/*      */ 
/*  638 */     return icon.getImage();
/*      */   }
/*      */ 
/*      */   public static void picture(double x, double y, String s)
/*      */   {
/*  649 */     Image image = getImage(s);
/*  650 */     double xs = scaleX(x);
/*  651 */     double ys = scaleY(y);
/*  652 */     int ws = image.getWidth(null);
/*  653 */     int hs = image.getHeight(null);
/*  654 */     if ((ws < 0) || (hs < 0)) throw new IllegalArgumentException("image " + s + " is corrupt");
/*      */ 
/*  656 */     offscreen.drawImage(image, (int)Math.round(xs - ws / 2.0D), (int)Math.round(ys - hs / 2.0D), null);
/*  657 */     draw();
/*      */   }
/*      */ 
/*      */   public static void picture(double x, double y, String s, double degrees)
/*      */   {
/*  670 */     Image image = getImage(s);
/*  671 */     double xs = scaleX(x);
/*  672 */     double ys = scaleY(y);
/*  673 */     int ws = image.getWidth(null);
/*  674 */     int hs = image.getHeight(null);
/*  675 */     if ((ws < 0) || (hs < 0)) throw new IllegalArgumentException("image " + s + " is corrupt");
/*      */ 
/*  677 */     offscreen.rotate(Math.toRadians(-degrees), xs, ys);
/*  678 */     offscreen.drawImage(image, (int)Math.round(xs - ws / 2.0D), (int)Math.round(ys - hs / 2.0D), null);
/*  679 */     offscreen.rotate(Math.toRadians(degrees), xs, ys);
/*      */ 
/*  681 */     draw();
/*      */   }
/*      */ 
/*      */   public static void picture(double x, double y, String s, double w, double h)
/*      */   {
/*  695 */     Image image = getImage(s);
/*  696 */     double xs = scaleX(x);
/*  697 */     double ys = scaleY(y);
/*  698 */     if (w < 0.0D) throw new IllegalArgumentException("width is negative: " + w);
/*  699 */     if (h < 0.0D) throw new IllegalArgumentException("height is negative: " + h);
/*  700 */     double ws = factorX(w);
/*  701 */     double hs = factorY(h);
/*  702 */     if ((ws < 0.0D) || (hs < 0.0D)) throw new IllegalArgumentException("image " + s + " is corrupt");
/*  703 */     if ((ws <= 1.0D) && (hs <= 1.0D)) pixel(x, y);
/*      */     else {
/*  705 */       offscreen.drawImage(image, (int)Math.round(xs - ws / 2.0D), 
/*  706 */         (int)Math.round(ys - hs / 2.0D), 
/*  707 */         (int)Math.round(ws), 
/*  708 */         (int)Math.round(hs), null);
/*      */     }
/*  710 */     draw();
/*      */   }
/*      */ 
/*      */   public static void picture(double x, double y, String s, double w, double h, double degrees)
/*      */   {
/*  726 */     Image image = getImage(s);
/*  727 */     double xs = scaleX(x);
/*  728 */     double ys = scaleY(y);
/*  729 */     double ws = factorX(w);
/*  730 */     double hs = factorY(h);
/*  731 */     if ((ws < 0.0D) || (hs < 0.0D)) throw new IllegalArgumentException("image " + s + " is corrupt");
/*  732 */     if ((ws <= 1.0D) && (hs <= 1.0D)) pixel(x, y);
/*      */ 
/*  734 */     offscreen.rotate(Math.toRadians(-degrees), xs, ys);
/*  735 */     offscreen.drawImage(image, (int)Math.round(xs - ws / 2.0D), 
/*  736 */       (int)Math.round(ys - hs / 2.0D), 
/*  737 */       (int)Math.round(ws), 
/*  738 */       (int)Math.round(hs), null);
/*  739 */     offscreen.rotate(Math.toRadians(degrees), xs, ys);
/*      */ 
/*  741 */     draw();
/*      */   }
/*      */ 
/*      */   public static void text(double x, double y, String s)
/*      */   {
/*  756 */     offscreen.setFont(font);
/*  757 */     FontMetrics metrics = offscreen.getFontMetrics();
/*  758 */     double xs = scaleX(x);
/*  759 */     double ys = scaleY(y);
/*  760 */     int ws = metrics.stringWidth(s);
/*  761 */     int hs = metrics.getDescent();
/*  762 */     offscreen.drawString(s, (float)(xs - ws / 2.0D), (float)(ys + hs));
/*  763 */     draw();
/*      */   }
/*      */ 
/*      */   public static void text(double x, double y, String s, double degrees)
/*      */   {
/*  775 */     double xs = scaleX(x);
/*  776 */     double ys = scaleY(y);
/*  777 */     offscreen.rotate(Math.toRadians(-degrees), xs, ys);
/*  778 */     text(x, y, s);
/*  779 */     offscreen.rotate(Math.toRadians(degrees), xs, ys);
/*      */   }
/*      */ 
/*      */   public static void textLeft(double x, double y, String s)
/*      */   {
/*  790 */     offscreen.setFont(font);
/*  791 */     FontMetrics metrics = offscreen.getFontMetrics();
/*  792 */     double xs = scaleX(x);
/*  793 */     double ys = scaleY(y);
/*  794 */     int hs = metrics.getDescent();
/*  795 */     offscreen.drawString(s, (float)xs, (float)(ys + hs));
/*  796 */     draw();
/*      */   }
/*      */ 
/*      */   public static void textRight(double x, double y, String s)
/*      */   {
/*  806 */     offscreen.setFont(font);
/*  807 */     FontMetrics metrics = offscreen.getFontMetrics();
/*  808 */     double xs = scaleX(x);
/*  809 */     double ys = scaleY(y);
/*  810 */     int ws = metrics.stringWidth(s);
/*  811 */     int hs = metrics.getDescent();
/*  812 */     offscreen.drawString(s, (float)(xs - ws), (float)(ys + hs));
/*  813 */     draw();
/*      */   }
/*      */ 
/*      */   public static void show(int t)
/*      */   {
/*  831 */     defer = false;
/*  832 */     draw();
/*      */     try { Thread.sleep(t); } catch (InterruptedException e) {
/*  834 */       System.out.println("Error sleeping");
/*  835 */     }defer = true;
/*      */   }
/*      */ 
/*      */   public static void show()
/*      */   {
/*  845 */     defer = false;
/*  846 */     draw();
/*      */   }
/*      */ 
/*      */   private static void draw()
/*      */   {
/*  851 */     if (defer) return;
/*  852 */     onscreen.drawImage(offscreenImage, 0, 0, null);
/*  853 */     frame.repaint();
/*      */   }
/*      */ 
/*      */   public static void save(String filename)
/*      */   {
/*  866 */     File file = new File(filename);
/*  867 */     String suffix = filename.substring(filename.lastIndexOf('.') + 1);
/*      */ 
/*  870 */     if (suffix.toLowerCase().equals("png")) {
/*      */       try { ImageIO.write(onscreenImage, suffix, file); } catch (IOException e) {
/*  872 */         e.printStackTrace();
/*      */       }
/*      */ 
/*      */     }
/*  877 */     else if (suffix.toLowerCase().equals("jpg")) {
/*  878 */       WritableRaster raster = onscreenImage.getRaster();
/*      */ 
/*  880 */       WritableRaster newRaster = raster.createWritableChild(0, 0, width, height, 0, 0, new int[] { 0, 1, 2 });
/*  881 */       DirectColorModel cm = (DirectColorModel)onscreenImage.getColorModel();
/*  882 */       DirectColorModel newCM = new DirectColorModel(cm.getPixelSize(), 
/*  883 */         cm.getRedMask(), 
/*  884 */         cm.getGreenMask(), 
/*  885 */         cm.getBlueMask());
/*  886 */       BufferedImage rgbBuffer = new BufferedImage(newCM, newRaster, false, null);
/*      */       try { ImageIO.write(rgbBuffer, suffix, file); } catch (IOException e) {
/*  888 */         e.printStackTrace();
/*      */       }
/*      */     }
/*      */     else {
/*  892 */       System.out.println("Invalid image file type: " + suffix);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void actionPerformed(ActionEvent e)
/*      */   {
/*  901 */     FileDialog chooser = new FileDialog(frame, "Use a .png or .jpg extension", 1);
/*  902 */     chooser.setVisible(true);
/*  903 */     String filename = chooser.getFile();
/*  904 */     if (filename != null)
/*  905 */       save(chooser.getDirectory() + File.separator + chooser.getFile());
/*      */   }
/*      */ 
/*      */   public static boolean mousePressed()
/*      */   {
/*  919 */     synchronized (mouseLock) {
/*  920 */       return mousePressed;
/*      */     }
/*      */   }
/*      */ 
/*      */   public static double mouseX()
/*      */   {
/*  929 */     synchronized (mouseLock) {
/*  930 */       return mouseX;
/*      */     }
/*      */   }
/*      */ 
/*      */   public static double mouseY()
/*      */   {
/*  939 */     synchronized (mouseLock) {
/*  940 */       return mouseY;
/*      */     }
/*      */   }
/*      */ 
/*      */   public void mouseClicked(MouseEvent e)
/*      */   {
/*      */   }
/*      */ 
/*      */   public void mouseEntered(MouseEvent e)
/*      */   {
/*      */   }
/*      */ 
/*      */   public void mouseExited(MouseEvent e)
/*      */   {
/*      */   }
/*      */ 
/*      */   public void mousePressed(MouseEvent e)
/*      */   {
/*  964 */     synchronized (mouseLock) {
/*  965 */       mouseX = userX(e.getX());
/*  966 */       mouseY = userY(e.getY());
/*  967 */       mousePressed = true;
/*      */     }
/*      */   }
/*      */ 
/*      */   public void mouseReleased(MouseEvent e)
/*      */   {
/*  975 */     synchronized (mouseLock) {
/*  976 */       mousePressed = false;
/*      */     }
/*      */   }
/*      */ 
/*      */   public void mouseDragged(MouseEvent e)
/*      */   {
/*  984 */     synchronized (mouseLock) {
/*  985 */       mouseX = userX(e.getX());
/*  986 */       mouseY = userY(e.getY());
/*      */     }
/*      */   }
/*      */ 
/*      */   public void mouseMoved(MouseEvent e)
/*      */   {
/*  994 */     synchronized (mouseLock) {
/*  995 */       mouseX = userX(e.getX());
/*  996 */       mouseY = userY(e.getY());
/*      */     }
/*      */   }
/*      */ 
/*      */   public static boolean hasNextKeyTyped()
/*      */   {
/* 1010 */     synchronized (keyLock) {
/* 1011 */       return !keysTyped.isEmpty();
/*      */     }
/*      */   }
/*      */ 
/*      */   public static char nextKeyTyped()
/*      */   {
/* 1023 */     synchronized (keyLock) {
/* 1024 */       return ((Character)keysTyped.removeLast()).charValue();
/*      */     }
/*      */   }
/*      */ 
/*      */   public static boolean isKeyPressed(int keycode)
/*      */   {
/* 1037 */     synchronized (keyLock) {
/* 1038 */       return keysDown.contains(Integer.valueOf(keycode));
/*      */     }
/*      */   }
/*      */ 
/*      */   public void keyTyped(KeyEvent e)
/*      */   {
/* 1047 */     synchronized (keyLock) {
/* 1048 */       keysTyped.addFirst(Character.valueOf(e.getKeyChar()));
/*      */     }
/*      */   }
/*      */ 
/*      */   public void keyPressed(KeyEvent e)
/*      */   {
/* 1056 */     synchronized (keyLock) {
/* 1057 */       keysDown.add(Integer.valueOf(e.getKeyCode()));
/*      */     }
/*      */   }
/*      */ 
/*      */   public void keyReleased(KeyEvent e)
/*      */   {
/* 1065 */     synchronized (keyLock) {
/* 1066 */       keysDown.remove(Integer.valueOf(e.getKeyCode()));
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void main(String[] args)
/*      */   {
/* 1077 */     square(0.2D, 0.8D, 0.1D);
/* 1078 */     filledSquare(0.8D, 0.8D, 0.2D);
/* 1079 */     circle(0.8D, 0.2D, 0.2D);
/*      */ 
/* 1081 */     setPenColor(BOOK_RED);
/* 1082 */     setPenRadius(0.02D);
/* 1083 */     arc(0.8D, 0.2D, 0.1D, 200.0D, 45.0D);
/*      */ 
/* 1086 */     setPenRadius();
/* 1087 */     setPenColor(BOOK_BLUE);
/* 1088 */     double[] x = { 0.1D, 0.2D, 0.3D, 0.2D };
/* 1089 */     double[] y = { 0.2D, 0.3D, 0.2D, 0.1D };
/* 1090 */     filledPolygon(x, y);
/*      */ 
/* 1093 */     setPenColor(BLACK);
/* 1094 */     text(0.2D, 0.5D, "black text");
/* 1095 */     setPenColor(WHITE);
/* 1096 */     text(0.8D, 0.8D, "white text");
/*      */   }
/*      */ }

/* Location:           /Users/timcoker/Desktop/Pong v1.0.jar
 * Qualified Name:     Pong.StdDraw
 * JD-Core Version:    0.6.0
 */