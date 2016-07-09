/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JSlider;
/*     */ 
/*     */ public final class FieldGenerator extends JPanel
/*     */ {
/*     */   FieldGeneratorGUI fieldGeneratorGUI;
/*     */   private BufferedImage mImage;
/*     */   private BufferedImage image;
/*     */   private final Color blueFlower;
/*     */   private final Color yellowFlower;
/*     */   private Graphics g2x;
/*     */   private int bluePixels;
/*     */   private int yellowPixels;
/*     */   private int fieldWidth;
/*     */   private int fieldHeight;
/*     */   private JPanel slidersPanel;
/*     */   private String baseColor;
/*     */   private int xSize;
/*     */   private int ySize;
/*     */   
/*     */   public FieldGenerator(JSlider rSlider, JLabel yellowPixels, JLabel bluePixels, JLabel ybRatio, javax.swing.JCheckBox update)
/*     */   {
/*  28 */     this.blueFlower = new Color(0, 0, 255);
/*  29 */     this.yellowFlower = new Color(255, 255, 0);
/*     */     
/*     */ 
/*  32 */     this.baseColor = "blue";
/*  33 */     this.xSize = 475;
/*  34 */     this.ySize = 350;
/*  35 */     this.grainSize = 5;
/*  36 */     this.iterations = 5;
/*  37 */     this.doGenerate = false;
/*  38 */     this.doFlip = false;
/*  39 */     this.seed = 0L;
/*  40 */     this.seedGenerated = 0L;
/*  41 */     this.sliderChange = true;
/*     */     
/*  43 */     createDimensionSliders();
/*  44 */     this.ratioSlider = rSlider;
/*  45 */     this.yellowPixelsLabel = yellowPixels;
/*  46 */     this.bluePixelsLabel = bluePixels;
/*  47 */     this.ybRatioLabel = ybRatio;
/*  48 */     this.autoUpdate = update;
/*     */   }
/*     */   
/*     */   public FieldGenerator(JSlider rSlider, JLabel yellowPixels, JLabel bluePixels, JLabel ybRatio, int x, int y)
/*     */   {
/*  53 */     this.blueFlower = new Color(0, 0, 255);
/*  54 */     this.yellowFlower = new Color(255, 255, 0);
/*     */     
/*     */ 
/*  57 */     this.baseColor = "blue";
/*  58 */     this.xSize = x;
/*  59 */     this.ySize = y;
/*  60 */     this.grainSize = 5;
/*  61 */     this.iterations = 5;
/*  62 */     this.doGenerate = false;
/*  63 */     this.doFlip = false;
/*  64 */     this.seed = 0L;
/*  65 */     this.sliderChange = true;
/*     */     
/*  67 */     createDimensionSliders();
/*  68 */     this.ratioSlider = rSlider;
/*  69 */     this.yellowPixelsLabel = yellowPixels;
/*  70 */     this.bluePixelsLabel = bluePixels;
/*  71 */     this.ybRatioLabel = ybRatio;
/*     */   }
/*     */   
/*     */   public FieldGenerator(int x, int y)
/*     */   {
/*  76 */     this.blueFlower = new Color(0, 0, 255);
/*  77 */     this.yellowFlower = new Color(255, 255, 0);
/*  78 */     this.xSize = x;
/*  79 */     this.ySize = y;
/*  80 */     createDimensionSliders();
/*     */   }
/*     */   
/*     */   public final int getFieldHeight()
/*     */   {
/*  85 */     return this.fieldHeight;
/*     */   }
/*     */   
/*     */   public final int getFieldWidth() {
/*  89 */     return this.fieldWidth;
/*     */   }
/*     */   
/*     */   public final void setGenerate(boolean b)
/*     */   {
/*  94 */     this.doGenerate = b;
/*     */   }
/*     */   
/*     */   public final void setFlip(boolean b)
/*     */   {
/*  99 */     this.doFlip = b;
/*     */   }
/*     */   
/*     */   private Color getBaseColor() {
/* 103 */     if (this.baseColor == "blue") {
/* 104 */       return this.blueFlower;
/*     */     }
/* 106 */     return this.yellowFlower;
/*     */   }
/*     */   
/*     */   public final BufferedImage getField()
/*     */   {
/* 111 */     return this.mImage;
/*     */   }
/*     */   
/*     */   private long getSeed() {
/* 115 */     return this.seed;
/*     */   }
/*     */   
/*     */   public final void setParameters(int grain, int it, long s)
/*     */   {
/* 120 */     this.baseColor = "yellow";
/* 121 */     this.grainSize = grain;
/* 122 */     this.iterations = (it / (int)Math.sqrt(grain));
/* 123 */     if (this.iterations == 0) this.iterations = 1;
/* 124 */     this.seed = s;
/*     */   }
/*     */   
/*     */   private double noise1(int x, int y)
/*     */   {
/* 129 */     x += 3;
/* 130 */     y += 3;
/* 131 */     double res = this.randomNoise[x][y].doubleValue();
/* 132 */     return res;
/*     */   }
/*     */   
/*     */   private double smoothedNoise_1(double xt, double yt)
/*     */   {
/* 137 */     int x = (int)xt;
/* 138 */     int y = (int)yt;
/* 139 */     double corners = noise1(x - 1, y - 1) + noise1(x + 1, y - 1) + noise1(x - 1, y + 1) + noise1(x + 1, y + 1);
/* 140 */     double sides = noise1(x - 1, y) + noise1(x + 1, y) + noise1(x, y - 1) + noise1(x, y + 1);
/* 141 */     double center = noise1(x, y) / 4.0D;
/*     */     
/* 143 */     return corners + sides + center;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 149 */   private static double getLinearInterpolateNum(double a, double b, double x) { return a * (1.0D - x) + b * x; }
/*     */   
/*     */   private int grainSize;
/*     */   private int iterations;
/*     */   private boolean doGenerate;
/*     */   
/*     */   private double interpolatedNoise(double x, double y) {
/* 156 */     int intx = (int)x;
/* 157 */     double doublex = x - intx;
/* 158 */     int inty = (int)y;
/* 159 */     double doubley = y - inty;
/*     */     
/* 161 */     double v1 = smoothedNoise_1(intx, inty);
/* 162 */     double v2 = smoothedNoise_1(intx + 1, inty);
/* 163 */     double v3 = smoothedNoise_1(intx, inty + 1);
/* 164 */     double v4 = smoothedNoise_1(intx + 1, inty + 1);
/*     */     
/* 166 */     double i1 = getLinearInterpolateNum(v1, v2, doublex);
/* 167 */     double i2 = getLinearInterpolateNum(v3, v4, doublex);
/*     */     
/* 169 */     return getLinearInterpolateNum(i1, i2, doubley);
/*     */   }
/*     */   
/*     */   private boolean doFlip;
/*     */   private long seed;
/*     */   
/* 175 */   private void generate() { this.random = new java.util.Random(getSeed());
/*     */     
/*     */ 
/*     */ 
/* 179 */     if (this.g2x == null) {
/* 180 */       this.image = new BufferedImage(this.xSize, this.ySize, 1);
/* 181 */       this.g2x = this.image.createGraphics();
/*     */     }
/*     */     
/* 184 */     if ((this.xSize != this.image.getWidth()) || (this.ySize != this.image.getHeight())) {
/* 185 */       this.image = new BufferedImage(this.xSize, this.ySize, 1);
/* 186 */       this.g2x = this.image.createGraphics();
/*     */     }
/*     */     
/* 189 */     this.g2x.setColor(getBaseColor());
/*     */     
/* 191 */     Graphics2D g2 = this.mImage.createGraphics();
/* 192 */     drawPerlin();
/* 193 */     g2.drawImage(this.image, 0, 0, this.xSize, this.ySize, null);
/* 194 */     getColorInfo(); }
/*     */   
/*     */   private long seedGenerated;
/*     */   private JSlider ratioSlider;
/* 198 */   private void drawPerlin() { this.g2x = this.image.createGraphics();
/* 199 */     this.g2x.setColor(getBaseColor());
/*     */     
/* 201 */     if ((this.randomNoise == null) || (this.seedGenerated != this.seed)) {
/* 202 */       this.seedGenerated = getSeed();
/* 203 */       this.randomNoise = new Double[this.image.getWidth() + 6][this.image.getHeight() + 6];
/* 204 */       for (int i = 0; i < this.image.getWidth() + 6; i++) {
/* 205 */         for (int j = 0; j < this.image.getHeight() + 6; j++) {
/* 206 */           double num = this.random.nextFloat();
/* 207 */           num = num * 2.0D - 1.0D;
/* 208 */           Double wrappedDouble = new Double(num);
/* 209 */           this.randomNoise[i][j] = wrappedDouble;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 214 */     Double[][] totals = new Double[this.image.getWidth()][this.image.getHeight()];
/* 215 */     double max = 0.0D;
/* 216 */     double min = 0.0D;
/*     */     
/* 218 */     double persistence = 1.0D;
/* 219 */     int octaves = 1;
/* 220 */     double frequency = -1 * (this.grainSize - 76);
/* 221 */     frequency /= 100.0D;
/* 222 */     double amplitude = 1.0D;
/*     */     
/*     */ 
/*     */ 
/* 226 */     for (int x = 0; x < this.image.getWidth(); x++) {
/* 227 */       for (int y = 0; y < this.image.getHeight(); y++) {
/* 228 */         double total = 0.0D;
/* 229 */         for (int o = 0; o < 1; o++) {
/* 230 */           total += interpolatedNoise(x * frequency, y * frequency) * 1.0D;
/* 231 */           if (total > max) max = total;
/* 232 */           if (total < min) { min = total;
/*     */           }
/* 234 */           Double wrappedDouble = new Double(total);
/* 235 */           totals[x][y] = wrappedDouble;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 240 */     double mid = (max - min) / 2.0D + min;
/* 241 */     int bias = this.ratioSlider.getValue();
/* 242 */     double divider = (max - min) / 2.0D + min;
/* 243 */     if (bias < 50) {
/* 244 */       divider = mid - min / 100.0D * (-1 * (bias - 50));
/*     */     }
/* 246 */     if (bias >= 50) {
/* 247 */       divider = mid - max / 100.0D * (bias - 50);
/*     */     }
/* 249 */     int rgb = 0;
/* 250 */     for (int x = 0; x < this.image.getWidth(); x++) {
/* 251 */       for (int y = 0; y < this.image.getHeight(); y++) {
/* 252 */         if (totals[x][y].doubleValue() > divider)
/*     */         {
/* 254 */           rgb = 65280;
/*     */         }
/*     */         else {
/* 257 */           rgb = -16776961;
/*     */         }
/* 259 */         this.image.setRGB(x, y, rgb);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public final void getColorInfo()
/*     */   {
/* 266 */     this.twodec = new java.text.DecimalFormat("#0.00");
/* 267 */     countColors();
/* 268 */     this.bluePixelsLabel.setText("Blue Pixels - " + this.bluePixels);
/* 269 */     this.yellowPixelsLabel.setText("Yellow Pixels - " + this.yellowPixels);
/* 270 */     double yp = this.yellowPixels;
/* 271 */     double bp = this.bluePixels;
/* 272 */     if (bp > yp)
/*     */     {
/* 274 */       double ratioValue = bp / (bp + yp) * 100.0D;
/* 275 */       int blueShade = (int)((ratioValue - 50.0D) * 20.0D);
/* 276 */       if (blueShade > 255) blueShade = 255;
/* 277 */       Color blueColor = new Color(0, 0, blueShade);
/* 278 */       this.ybRatioLabel.setForeground(blueColor);
/* 279 */       this.ybRatioLabel.setText("Ratio - %" + this.twodec.format(ratioValue) + " Blue");
/* 280 */     } else if (bp <= yp)
/*     */     {
/* 282 */       double ratioValue = yp / (bp + yp) * 100.0D;
/* 283 */       int yellowShade = (int)((ratioValue - 50.0D) * 20.0D);
/* 284 */       if (yellowShade > 255) yellowShade = 255;
/* 285 */       Color yellowColor = new Color(yellowShade, yellowShade, 0);
/* 286 */       this.ybRatioLabel.setForeground(yellowColor);
/* 287 */       this.ybRatioLabel.setText("Ratio - %" + this.twodec.format(ratioValue) + " Yellow");
/*     */     }
/*     */   }
/*     */   
/*     */   public final void flipColor()
/*     */   {
/* 293 */     int blueValue = -16776961;
/* 294 */     int yellowValue = 65280;
/* 295 */     this.bluePixels = 0;
/* 296 */     this.yellowPixels = 0;
/*     */     
/*     */ 
/* 299 */     this.ySize = this.mImage.getHeight();
/* 300 */     this.xSize = this.mImage.getWidth();
/* 301 */     Graphics2D g2 = this.mImage.createGraphics();
/*     */     
/*     */ 
/* 304 */     for (int ytemp = 0; ytemp < this.ySize; ytemp++) {
/* 305 */       for (int xtemp = 0; xtemp < this.xSize; xtemp++) {
/* 306 */         int pixel = this.image.getRGB(xtemp, ytemp);
/* 307 */         if (pixel == -16776961) {
/* 308 */           this.image.setRGB(xtemp, ytemp, 65280);
/*     */         }
/* 310 */         if (pixel == 65280) {
/* 311 */           this.image.setRGB(xtemp, ytemp, -16776961);
/*     */         }
/*     */       }
/*     */     }
/* 315 */     g2.drawImage(this.image, 0, 0, this.xSize, this.ySize, null);
/*     */   }
/*     */   
/*     */   private void saveField()
/*     */   {
/*     */     try {
/* 321 */       java.io.OutputStream out = new java.io.FileOutputStream("savedfield.jpg");
/* 322 */       com.sun.image.codec.jpeg.JPEGImageEncoder encoder = com.sun.image.codec.jpeg.JPEGCodec.createJPEGEncoder(out);
/* 323 */       encoder.encode(this.image);
/* 324 */       out.close();
/*     */     } catch (Exception e) {
/* 326 */       System.out.println(e);
/*     */     }
/*     */   }
/*     */   
/*     */   private void countColors() {
/* 331 */     int blueValue = -16776961;
/* 332 */     int yellowValue = 65280;
/* 333 */     this.bluePixels = 0;
/* 334 */     this.yellowPixels = 0;
/*     */     
/*     */ 
/* 337 */     this.ySize = this.mImage.getHeight();
/* 338 */     this.xSize = this.mImage.getWidth();
/*     */     
/*     */ 
/* 341 */     for (int ytemp = 1; ytemp < this.ySize; ytemp++) {
/* 342 */       for (int xtemp = 1; xtemp < this.xSize; xtemp++) {
/* 343 */         int pixel = this.image.getRGB(xtemp, ytemp);
/* 344 */         if (pixel == -16776961) {
/* 345 */           this.bluePixels += 1;
/*     */         } else {
/* 347 */           this.yellowPixels += 1;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void setXYSize(int x, int y) {
/* 354 */     this.xSize = x;
/* 355 */     this.ySize = y; }
/*     */   
/*     */   private java.util.Random random;
/*     */   private JLabel yellowPixelsLabel;
/*     */   
/* 360 */   private void createOffScreenImage() { this.mImage = new BufferedImage(this.xSize, this.ySize, 1); }
/*     */   
/*     */   private JLabel bluePixelsLabel;
/*     */   private JLabel ybRatioLabel;
/*     */   private java.text.DecimalFormat twodec;
/*     */   
/* 366 */   private void createDimensionSliders() { JSlider widthSlider = new JSlider(0, 175, 550, this.xSize);
/* 367 */     widthSlider.putClientProperty("JSlider.isFilled", Boolean.TRUE);
/* 368 */     JSlider heightSlider = new JSlider(1, 175, 350, this.ySize);
/* 369 */     heightSlider.putClientProperty("JSlider.isFilled", Boolean.TRUE);
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
/* 415 */     javax.swing.Icon icon = new javax.swing.Icon()
/*     */     {
/*     */       private final JSlider val$widthSlider;
/*     */       private final JSlider val$heightSlider;
/*     */       
/*     */       public final int getIconWidth()
/*     */       {
/* 374 */         return this.val$widthSlider.getValue();
/*     */       }
/*     */       
/*     */       public final int getIconHeight() {
/* 378 */         return this.val$heightSlider.getValue();
/*     */       }
/*     */       
/*     */       public void setIconWidth(int width) {
/* 382 */         this.val$widthSlider.setValue(width);
/*     */       }
/*     */       
/*     */       public void setIconHeight(int height) {
/* 386 */         this.val$heightSlider.setValue(height);
/*     */       }
/*     */       
/*     */       public final void paintIcon(java.awt.Component c, Graphics g, int x, int y) {
/* 390 */         FieldGenerator.this.setXYSize(this.val$widthSlider.getValue(), this.val$heightSlider.getValue());
/* 391 */         if (FieldGenerator.this.mImage == null) FieldGenerator.this.createOffScreenImage();
/* 392 */         if (FieldGenerator.this.doGenerate) {
/* 393 */           FieldGenerator.this.createOffScreenImage();
/* 394 */           FieldGenerator.this.generate();
/*     */           
/* 396 */           g.drawImage(FieldGenerator.this.mImage, this.val$widthSlider.getMaximum() / 2 - FieldGenerator.this.mImage.getWidth() / 2 + 16, this.val$heightSlider.getMaximum() / 2 - FieldGenerator.this.mImage.getHeight() / 2 + 16, null);
/* 397 */           FieldGenerator.this.countColors();
/* 398 */           FieldGenerator.this.setGenerate(false);
/* 399 */         } else if (FieldGenerator.this.doFlip) {
/* 400 */           g.drawImage(FieldGenerator.this.mImage, this.val$widthSlider.getMaximum() / 2 - FieldGenerator.this.mImage.getWidth() / 2 + 16, this.val$heightSlider.getMaximum() / 2 - FieldGenerator.this.mImage.getHeight() / 2 + 16, null);
/* 401 */           FieldGenerator.this.setFlip(false);
/* 402 */         } else if (FieldGenerator.this.sliderChange) {
/* 403 */           g.setColor(new Color(105, 105, 145));
/* 404 */           g.fill3DRect(x, y, getIconWidth(), getIconHeight(), true);
/* 405 */           FieldGenerator.this.sliderChange = false;
/*     */         } else {
/* 407 */           g.drawImage(FieldGenerator.this.mImage, this.val$widthSlider.getMaximum() / 2 - FieldGenerator.this.mImage.getWidth() / 2 + 16, this.val$heightSlider.getMaximum() / 2 - FieldGenerator.this.mImage.getHeight() / 2 + 16, null);
/*     */         }
/* 409 */         FieldGenerator.this.fieldWidth = getIconWidth();
/* 410 */         FieldGenerator.this.fieldHeight = getIconHeight();
/*     */ 
/*     */       }
/*     */       
/*     */ 
/* 415 */     };
/* 416 */     JLabel dynamicLabel = new JLabel(icon);
/* 417 */     dynamicLabel.setBorder(new javax.swing.border.TitledBorder("Adjust X/Y Size of Field"));
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
/* 432 */     javax.swing.event.ChangeListener updater = new javax.swing.event.ChangeListener()
/*     */     {
/*     */       private final JLabel val$dynamicLabel;
/*     */       
/*     */       public final void stateChanged(javax.swing.event.ChangeEvent ev)
/*     */       {
/* 421 */         if (FieldGenerator.this.autoUpdate.isSelected()) {
/* 422 */           FieldGenerator.this.doGenerate = true;
/* 423 */           this.val$dynamicLabel.repaint();
/*     */         }
/*     */         else {
/* 426 */           FieldGenerator.this.sliderChange = true;
/* 427 */           this.val$dynamicLabel.repaint();
/*     */         }
/*     */         
/*     */       }
/*     */       
/*     */ 
/* 433 */     };
/* 434 */     widthSlider.addChangeListener(updater);
/* 435 */     heightSlider.addChangeListener(updater);
/* 436 */     this.slidersPanel = new JPanel(new java.awt.BorderLayout());
/* 437 */     setBorder(new javax.swing.border.BevelBorder(1));
/* 438 */     this.slidersPanel.add(widthSlider, "North");
/* 439 */     this.slidersPanel.add(heightSlider, "West");
/* 440 */     this.slidersPanel.add(dynamicLabel, "Center");
/* 441 */     this.slidersPanel.setPreferredSize(new java.awt.Dimension(600, 400));
/* 442 */     add(this.slidersPanel);
/*     */   }
/*     */   
/*     */   private Double[][] randomNoise;
/*     */   private boolean sliderChange;
/*     */   private javax.swing.JCheckBox autoUpdate;
/*     */ }


/* Location:              C:\Users\iain\Desktop\Documents\landisk\programming\Iains Work Samples\simulator\project.jar!\FieldGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */