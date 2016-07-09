/*     */ import java.awt.image.BufferedImage;
/*     */ 
/*     */ public final class Field extends javax.swing.JComponent
/*     */ {
/*     */   private java.awt.MediaTracker tracker;
/*     */   private BufferedImage fieldImage;
/*     */   private BufferedImage fieldImageOriginal;
/*     */   private final BufferedImage beeImage;
/*     */   private java.awt.Graphics2D g2;
/*     */   private int viewWidth;
/*     */   private int viewHeight;
/*     */   private final javax.swing.JSlider visionSlider;
/*     */   private int pastEdge;
/*     */   private boolean animationOn;
/*     */   private int bluePixels;
/*     */   private int yellowPixels;
/*     */   private boolean beeSwerving;
/*     */   
/*     */   private void offField(int x, int y)
/*     */   {
/*  21 */     if (x < 0) {
/*  22 */       this.pastEdge = 4;
/*  23 */     } else if (x + this.viewWidth > this.fieldImage.getWidth()) {
/*  24 */       this.pastEdge = 2;
/*  25 */     } else if (y < 0) {
/*  26 */       this.pastEdge = 1;
/*  27 */     } else if (y + this.viewHeight > this.fieldImage.getHeight()) {
/*  28 */       this.pastEdge = 3;
/*     */     } else {
/*  30 */       this.pastEdge = 0;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public final int outOfField()
/*     */   {
/*  38 */     return this.pastEdge;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final java.awt.Dimension getPreferredSize()
/*     */   {
/*  46 */     java.awt.Dimension size = new java.awt.Dimension(this.fieldImage.getWidth(null), this.fieldImage.getHeight(null));
/*  47 */     return size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void drawBee(int x, int y)
/*     */   {
/*  58 */     this.bluePixels = 0;
/*  59 */     this.yellowPixels = 0;
/*     */     
/*     */ 
/*     */ 
/*  63 */     offField(x, y);
/*  64 */     drawField(x, y);
/*  65 */     int[] xy = getView(x, y);
/*  66 */     xy = correctView(xy[0], xy[1]);
/*  67 */     xy = getPixels(xy[0], xy[1]);
/*     */     
/*  69 */     this.bluePixels = xy[0];
/*  70 */     this.yellowPixels = xy[1];
/*     */     
/*     */ 
/*     */ 
/*  74 */     float bluePercent = this.bluePixels / (this.bluePixels + this.yellowPixels) * 100.0F;
/*     */     
/*  76 */     this.visionSlider.setValue((int)bluePercent - 50);
/*     */   }
/*     */   
/*     */ 
/*     */   private BufferedImage getBufferedImage(java.awt.Image image)
/*     */   {
/*  82 */     this.tracker = new java.awt.MediaTracker(this);
/*  83 */     this.tracker.addImage(image, 0);
/*     */     try {
/*  85 */       this.tracker.waitForID(0);
/*     */     }
/*     */     catch (InterruptedException e) {}
/*     */     
/*  89 */     int x = image.getWidth(null);
/*  90 */     int y = image.getHeight(null);
/*     */     
/*  92 */     System.out.println("X: " + x + "Y: " + y);
/*  93 */     BufferedImage bufferedImage = new BufferedImage(x, y, 1);
/*     */     
/*  95 */     java.awt.Graphics2D g2d = bufferedImage.createGraphics();
/*  96 */     g2d.drawImage(image, 0, 0, null);
/*  97 */     return bufferedImage;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void setViewDimensions(int alt)
/*     */   {
/* 106 */     if (alt <= 0) alt = 2;
/* 107 */     this.viewWidth = alt;
/* 108 */     this.viewHeight = alt;
/* 109 */     repaint();
/*     */   }
/*     */   
/*     */   private void drawField(int x, int y) {
/* 113 */     java.awt.Graphics g2temp = getGraphics();
/* 114 */     if ((this.animationOn) && 
/* 115 */       (g2temp != null))
/*     */     {
/* 117 */       java.awt.Graphics imageGraphics = this.fieldImage.getGraphics();
/*     */       
/*     */ 
/* 120 */       imageGraphics.clipRect(x - (this.beeImage.getWidth() + 6), y - (this.beeImage.getHeight() + 6), this.beeImage.getWidth() + this.viewWidth + 12, this.beeImage.getHeight() + this.viewHeight + 12);
/* 121 */       imageGraphics.drawImage(this.fieldImageOriginal, 0, 0, null);
/*     */       
/* 123 */       if (this.beeSwerving) {
/* 124 */         imageGraphics.setColor(java.awt.Color.RED);
/* 125 */         this.beeSwerving = false;
/*     */       }
/* 127 */       imageGraphics.drawRect(x, y, this.viewWidth, this.viewHeight);
/* 128 */       imageGraphics.drawImage(this.beeImage, x - this.beeImage.getWidth(), y - this.beeImage.getHeight(), null);
/*     */       
/* 130 */       imageGraphics.dispose();
/* 131 */       g2temp.dispose();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public final void paint(java.awt.Graphics g)
/*     */   {
/* 138 */     this.g2 = ((java.awt.Graphics2D)g);
/* 139 */     drawImage();
/*     */   }
/*     */   
/*     */   private void drawImage()
/*     */   {
/* 144 */     this.g2.drawImage(this.fieldImage, 0, 0, this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static int[] getView(int x, int y)
/*     */   {
/* 151 */     int[] xy = new int[2];
/*     */     
/*     */ 
/* 154 */     int x1 = x;
/* 155 */     int y1 = y;
/*     */     
/* 157 */     if (x1 < 0) x1 = 0;
/* 158 */     if (y1 < 0) { y1 = 0;
/*     */     }
/*     */     
/* 161 */     xy[0] = x1;
/* 162 */     xy[1] = y1;
/* 163 */     return xy;
/*     */   }
/*     */   
/*     */ 
/*     */   private int[] correctView(int x1init, int y1init)
/*     */   {
/* 169 */     int[] xy = new int[2];
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 174 */     int x1 = x1init;
/* 175 */     int y1 = y1init;
/*     */     
/*     */ 
/* 178 */     if (x1 > this.fieldImage.getWidth() - this.viewWidth) x1 = this.fieldImage.getWidth() - this.viewWidth;
/* 179 */     if (y1 > this.fieldImage.getHeight() - this.viewHeight) { y1 = this.fieldImage.getHeight() - this.viewHeight;
/*     */     }
/*     */     
/* 182 */     xy[0] = x1;
/* 183 */     xy[1] = y1;
/* 184 */     return xy;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private int[] getPixels(int x1, int y1)
/*     */   {
/* 192 */     int blueValue = -16776961;
/* 193 */     int yellowValue = 65280;
/* 194 */     int bluePixels = 0;
/* 195 */     int yellowPixels = 0;
/* 196 */     int[] pixels = new int[2];
/*     */     
/*     */ 
/* 199 */     BufferedImage fieldTile = this.fieldImage.getSubimage(x1, y1, this.viewWidth, this.viewHeight);
/* 200 */     int width = fieldTile.getWidth();
/* 201 */     int height = fieldTile.getHeight();
/*     */     
/*     */ 
/* 204 */     for (int ytemp = 1; ytemp < height; ytemp++) {
/* 205 */       for (int xtemp = 1; xtemp < width; xtemp++) {
/* 206 */         int pixel = fieldTile.getRGB(xtemp, ytemp);
/* 207 */         if (pixel == -16776961) {
/* 208 */           bluePixels++;
/*     */         }
/* 210 */         else if (pixel == 65280) {
/* 211 */           yellowPixels++;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 216 */     pixels[0] = bluePixels;
/* 217 */     pixels[1] = yellowPixels;
/* 218 */     return pixels;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void clear()
/*     */   {
/* 226 */     java.awt.Graphics g2temp = getGraphics();
/* 227 */     if (g2temp != null)
/*     */     {
/* 229 */       java.awt.Graphics imageGraphics = this.fieldImage.getGraphics();
/* 230 */       imageGraphics.drawImage(this.fieldImageOriginal, 0, 0, null);
/* 231 */       imageGraphics.dispose();
/* 232 */       g2temp.dispose();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void beeSwerving()
/*     */   {
/* 240 */     this.beeSwerving = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public final void setAnimation(boolean set)
/*     */   {
/* 247 */     this.animationOn = set;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Field(java.awt.Image fi, java.awt.Image bi, javax.swing.JSlider s)
/*     */   {
/* 267 */     this.fieldImage = getBufferedImage(fi);
/* 268 */     this.fieldImageOriginal = getBufferedImage(fi);
/* 269 */     this.beeImage = getBufferedImage(bi);
/*     */     
/* 271 */     this.visionSlider = s;
/*     */     
/*     */ 
/* 274 */     setSize(this.fieldImage.getWidth(), this.fieldImage.getHeight());
/* 275 */     this.viewWidth = this.fieldImage.getWidth();
/* 276 */     this.viewHeight = this.fieldImage.getHeight();
/*     */     
/*     */ 
/* 279 */     int[] temp = getPixels(0, 0);
/* 280 */     System.out.println("Blue: " + temp[0] + "   Yellow: " + temp[1]);
/* 281 */     this.pastEdge = 0;
/* 282 */     this.animationOn = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final java.awt.Dimension getFieldSize()
/*     */   {
/* 291 */     java.awt.Dimension fieldSize = new java.awt.Dimension(this.fieldImage.getWidth(), this.fieldImage.getHeight());
/* 292 */     return fieldSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void setField(java.awt.Image fi)
/*     */   {
/* 305 */     this.fieldImage = getBufferedImage(fi);
/* 306 */     this.fieldImageOriginal = getBufferedImage(fi);
/*     */     
/*     */ 
/* 309 */     setSize(this.fieldImage.getWidth(), this.fieldImage.getHeight());
/* 310 */     this.pastEdge = 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\iain\Desktop\Documents\landisk\programming\Iains Work Samples\simulator\project.jar!\Field.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */