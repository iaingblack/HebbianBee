/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics2D;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.JComboBox;
/*     */ 
/*     */ public final class GraphDrawer extends javax.swing.JComponent
/*     */ {
/*     */   private Graphics2D g;
/*     */   private Graphics2D g2;
/*     */   private ArrayList trialsToDraw;
/*     */   private final ResultList resultList;
/*     */   private java.awt.image.BufferedImage bufferedImage;
/*     */   private final JComboBox choiceList;
/*     */   private final JComboBox scaleList;
/*     */   private final javax.swing.JCheckBox averageCheckBox;
/*     */   private boolean doDrawGraph;
/*     */   private int xboundary;
/*     */   private int yboundary;
/*     */   private int yAxisLength;
/*     */   private int xAxisLength;
/*     */   private int maxx;
/*     */   private int maxy;
/*     */   private double xRatio;
/*     */   private double yRatio;
/*     */   private int xDivisions;
/*     */   private int yDivisions;
/*     */   
/*     */   public GraphDrawer(ArrayList listoftrials, ResultList resultlist, JComboBox graphtypes, JComboBox scaletodraw, javax.swing.JCheckBox averageresults, Dimension paneldimension)
/*     */   {
/*  31 */     this.trialsToDraw = listoftrials;
/*  32 */     this.resultList = resultlist;
/*  33 */     this.choiceList = graphtypes;
/*  34 */     this.scaleList = scaletodraw;
/*  35 */     this.averageCheckBox = averageresults;
/*  36 */     this.panelSize = paneldimension;
/*  37 */     adjustSize(this.panelSize);
/*  38 */     setDefaults();
/*  39 */     this.divider = 1.0D;
/*  40 */     createOffScreenImage();
/*     */   }
/*     */   
/*     */   private void setDefaults()
/*     */   {
/*  45 */     this.xboundary = 40;
/*  46 */     this.yboundary = 10;
/*  47 */     this.maxx = 1;
/*  48 */     this.maxy = 1;
/*  49 */     setXRatio();
/*  50 */     setYRatio();
/*  51 */     this.xDivisions = 20;
/*  52 */     this.yDivisions = 20;
/*     */   }
/*     */   
/*     */   public final void paint(java.awt.Graphics g)
/*     */   {
/*  57 */     g.drawImage(this.mImage, 0, 0, this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void adjustSize(Dimension newSize)
/*     */   {
/*  66 */     this.panelSize = newSize;
/*  67 */     this.xAxisLength = ((int)this.panelSize.getWidth() - 120);
/*  68 */     this.yAxisLength = ((int)this.panelSize.getHeight() - 100);
/*     */   }
/*     */   
/*     */   private void createOffScreenImage()
/*     */   {
/*  73 */     this.mImage = new java.awt.image.BufferedImage((int)this.panelSize.getWidth(), (int)this.panelSize.getHeight(), 1);
/*  74 */     this.g2 = this.mImage.createGraphics();
/*  75 */     clear();
/*     */   }
/*     */   
/*     */ 
/*     */   private void clear()
/*     */   {
/*  81 */     this.g2.setPaint(this.backgroundColor);
/*  82 */     if (this.g != null) this.g.fillRect(0, 0, this.mImage.getWidth(), this.mImage.getHeight());
/*  83 */     this.g2.fillRect(0, 0, this.mImage.getWidth(), this.mImage.getHeight());
/*  84 */     this.g2.setPaint(Color.black);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void drawGraph(ArrayList trialsToDraw)
/*     */   {
/*  93 */     setSelectedTrials(trialsToDraw);
/*  94 */     resetMaxMin();
/*  95 */     createOffScreenImage();
/*     */     
/*  97 */     drawGridLines();
/*  98 */     drawAxis();
/*     */     
/* 100 */     this.averageList = new ArrayList();
/*     */     
/* 102 */     if (!this.averageCheckBox.isSelected())
/*     */     {
/* 104 */       for (int i = 0; i < trialsToDraw.size(); i++)
/*     */       {
/* 106 */         int choice = this.choiceList.getSelectedIndex();
/* 107 */         switch (choice) {
/* 108 */         case 0:  drawWeights(Integer.parseInt((String)trialsToDraw.get(i))); break;
/* 109 */         case 1:  drawFlowerLands(Integer.parseInt((String)trialsToDraw.get(i))); break;
/* 110 */         case 2:  drawWeightsAndLands(Integer.parseInt((String)trialsToDraw.get(i)));
/*     */         }
/*     */         
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 117 */       for (int i = 0; i < trialsToDraw.size(); i++)
/*     */       {
/* 119 */         for (int j = 0; j < this.resultList.getCycles(); j++)
/*     */         {
/* 121 */           Result avgData = new Result();
/* 122 */           this.averageList.add(avgData);
/*     */         }
/*     */         
/*     */ 
/* 126 */         int trialNum = Integer.parseInt((String)trialsToDraw.get(i));
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 133 */         for (int k = 0; k < this.resultList.getCycles(); k++)
/*     */         {
/* 135 */           Result fetchedData = this.resultList.getArrayResult(trialNum, k);
/*     */           
/* 137 */           double wb = fetchedData.wbWeight();
/* 138 */           double wy = fetchedData.wyWeight();
/* 139 */           int yellowLands = fetchedData.getYellowLands();
/* 140 */           int blueLands = fetchedData.getBlueLands();
/*     */           
/*     */ 
/* 143 */           Result alterResult = (Result)this.averageList.get(k);
/*     */           
/* 145 */           alterResult.setwyWeight(alterResult.wyWeight() + wy);
/* 146 */           alterResult.setwbWeight(alterResult.wbWeight() + wb);
/* 147 */           alterResult.setYellowLands(alterResult.getYellowLands() + yellowLands);
/* 148 */           alterResult.setBlueLands(alterResult.getBlueLands() + blueLands);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 153 */       for (int m = 0; m < this.resultList.getCycles(); m++) {
/* 154 */         Result alterResult = (Result)this.averageList.get(m);
/*     */         
/* 156 */         alterResult.setwyWeight(alterResult.wyWeight() / trialsToDraw.size());
/* 157 */         alterResult.setwbWeight(alterResult.wbWeight() / trialsToDraw.size());
/* 158 */         alterResult.setYellowLands(alterResult.getYellowLands() / trialsToDraw.size());
/* 159 */         alterResult.setBlueLands(alterResult.getBlueLands() / trialsToDraw.size());
/*     */       }
/*     */       
/*     */ 
/* 163 */       int choice = this.choiceList.getSelectedIndex();
/* 164 */       switch (choice) {
/* 165 */       case 0:  drawWeights(-1); break;
/* 166 */       case 1:  drawFlowerLands(-1); break;
/* 167 */       case 2:  drawWeightsAndLands(-1);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void setSelectedTrials(ArrayList trials) {
/* 173 */     this.trialsToDraw = trials;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void drawAxis()
/*     */   {
/* 181 */     java.awt.Font defaultFont = this.g2.getFont();
/* 182 */     java.awt.Font titleFont = new java.awt.Font("Serif", 0, 24);
/* 183 */     this.g2.setFont(titleFont);
/*     */     
/*     */ 
/* 186 */     String selected = "";
/* 187 */     if (this.trialsToDraw.size() == this.resultList.getTrials()) {
/* 188 */       selected = "All";
/*     */     } else {
/* 190 */       for (int i = 0; i < this.trialsToDraw.size(); i++) {
/* 191 */         selected = selected + (i == 0 ? "" : ",") + (Integer.parseInt((String)this.trialsToDraw.get(i)) + 1);
/*     */       }
/*     */     }
/* 194 */     String title = "Trial" + (this.trialsToDraw.size() == 1 ? "" : "s") + " Selected - " + selected;
/*     */     
/*     */ 
/* 197 */     java.text.AttributedString as = new java.text.AttributedString(title);
/* 198 */     as.addAttribute(java.awt.font.TextAttribute.FONT, titleFont);
/* 199 */     as.addAttribute(java.awt.font.TextAttribute.UNDERLINE, java.awt.font.TextAttribute.UNDERLINE_ON);
/* 200 */     this.g2.drawString(as.getIterator(), 50, 33);
/* 201 */     this.g2.setFont(defaultFont);
/*     */     
/* 203 */     this.g2.drawString("V", 1, this.yboundary + this.yAxisLength / 2 - 30);
/* 204 */     this.g2.drawString("A", 1, this.yboundary + this.yAxisLength / 2 - 15);
/* 205 */     this.g2.drawString("L", 1, this.yboundary + this.yAxisLength / 2);
/* 206 */     this.g2.drawString("U", 1, this.yboundary + this.yAxisLength / 2 + 15);
/* 207 */     this.g2.drawString("E", 1, this.yboundary + this.yAxisLength / 2 + 30);
/* 208 */     this.g2.drawString("CYCLE NUMBER", this.xboundary + this.xAxisLength / 2 - 50, this.yboundary + this.yAxisLength + 35);
/*     */   }
/*     */   
/*     */   private void drawGridLines()
/*     */   {
/* 213 */     int choice = this.choiceList.getSelectedIndex();
/* 214 */     setMaxx(this.resultList.getCycles());
/*     */     
/* 216 */     for (int i = 0; i < this.trialsToDraw.size(); i++)
/*     */     {
/* 218 */       if (choice == 0) {
/* 219 */         int tempMax = getMaxWeight();
/* 220 */         setMaxy(tempMax);
/*     */       }
/*     */       
/* 223 */       if (choice == 1) {
/* 224 */         setMaxy(getMaxLand());
/* 225 */       } else if (choice == 2) {
/* 226 */         int tempMax = getMaxWeight();
/* 227 */         if (tempMax > getMaxLand()) {
/* 228 */           setMaxy(tempMax);
/*     */         } else {
/* 230 */           setMaxy(getMaxLand());
/*     */         }
/*     */       }
/*     */     }
/* 234 */     int xValuePerDivision = getMaxx() / this.xDivisions;
/* 235 */     if (getMaxx() % this.xDivisions > 0) xValuePerDivision++;
/* 236 */     int xDivisionPixels = xValuePerDivision * this.xDivisions / this.xDivisions;
/* 237 */     setXRatio();
/*     */     
/*     */ 
/* 240 */     int yValuePerDivision = getMaxy() / this.yDivisions;
/* 241 */     if (getMaxy() % this.yDivisions > 0) yValuePerDivision++;
/* 242 */     setMaxy(yValuePerDivision * this.yDivisions);
/* 243 */     int yDivisionPixels = yValuePerDivision * this.yDivisions / this.yDivisions;
/* 244 */     setYRatio();
/*     */     
/*     */ 
/* 247 */     for (int i = 1; i < this.xDivisions + 1; i++)
/*     */     {
/* 249 */       int xjump = i * xDivisionPixels;
/*     */       
/* 251 */       this.g2.drawLine(relativex(xjump), this.yboundary + this.yAxisLength, relativex(xjump), this.yboundary + this.yAxisLength + 5);
/* 252 */       this.g2.drawString("" + xValuePerDivision * i, relativex(xjump) - 4, this.yboundary + this.yAxisLength + 20);
/*     */       
/* 254 */       if (i % 2 == 0) this.g2.setColor(this.gridGrey); else this.g2.setColor(this.gridLightGrey);
/* 255 */       this.g2.drawLine(relativex(xjump), relativey(0), relativex(xjump), relativey(yDivisionPixels * this.yDivisions));
/* 256 */       this.g2.setColor(this.gridBlack);
/*     */       
/* 258 */       if (i == this.xDivisions) { this.g2.drawLine(relativex(0), relativey(0), relativex(xjump), relativey(0));
/*     */       }
/*     */     }
/*     */     
/* 262 */     for (int i = 1; i < this.yDivisions + 1; i++)
/*     */     {
/* 264 */       int yjump = i * yDivisionPixels;
/*     */       
/* 266 */       this.g2.drawLine(this.xboundary - 5, relativey(yjump), this.xboundary, relativey(yjump));
/* 267 */       this.g2.drawString("" + (int)(yValuePerDivision * i * this.divider), this.xboundary - 26, relativey(yjump) + 4);
/*     */       
/* 269 */       if (i % 2 == 0) this.g2.setColor(this.gridGrey); else this.g2.setColor(this.gridLightGrey);
/* 270 */       this.g2.drawLine(relativex(0), relativey(yjump), relativex(xDivisionPixels * this.xDivisions), relativey(yjump));
/* 271 */       this.g2.setColor(this.gridBlack);
/*     */       
/* 273 */       if (i == this.xDivisions) { this.g2.drawLine(relativex(0), relativey(0), relativex(0), relativey(yjump));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void drawWeights(int trialNum)
/*     */   {
/* 285 */     Result data = getResult(trialNum, 0);
/* 286 */     double wbNew = data.wbWeight();
/* 287 */     double wyNew = data.wyWeight();
/*     */     
/*     */ 
/* 290 */     for (int i = 0; i < this.resultList.getCycles(); i++) {
/* 291 */       data = getResult(trialNum, i);
/*     */       
/* 293 */       double wbOld = wbNew;
/* 294 */       wbNew = data.wbWeight();
/* 295 */       double wyOld = wyNew;
/* 296 */       wyNew = data.wyWeight();
/*     */       
/* 298 */       drawRewardSpike(data, i);
/*     */       
/* 300 */       drawValue(i, wbOld, wbNew, wyOld, wyNew);
/*     */     }
/* 302 */     drawWeightsText(trialNum, wbNew, wyNew);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void drawFlowerLands(int trialNum)
/*     */   {
/* 311 */     int blueFlowersNew = 0;
/*     */     
/* 313 */     int yellowFlowersNew = 0;
/*     */     
/* 315 */     for (int i = 0; i < this.resultList.getCycles(); i++) {
/* 316 */       Result data = getResult(trialNum, i);
/*     */       
/* 318 */       int blueFlowersOld = blueFlowersNew;
/* 319 */       blueFlowersNew = data.getBlueLands();
/* 320 */       int yellowFlowersOld = yellowFlowersNew;
/* 321 */       yellowFlowersNew = data.getYellowLands();
/* 322 */       drawRewardSpike(data, i);
/* 323 */       drawValue(i, blueFlowersOld, blueFlowersNew, yellowFlowersOld, yellowFlowersNew);
/*     */     }
/* 325 */     drawLandsText(trialNum, blueFlowersNew, yellowFlowersNew);
/*     */   }
/*     */   
/*     */   private void drawLandsText(int trial, int bl, int yl) {
/* 329 */     int trialNum = trial;
/* 330 */     int blueLands = bl;
/* 331 */     int yellowLands = yl;
/*     */     
/*     */ 
/* 334 */     if (trialNum == -1) {
/* 335 */       this.g2.setColor(this.gridYellow);
/* 336 */       this.g2.drawString("Lands:AVG", this.xboundary + this.xAxisLength + 5, relativey(yellowLands));
/* 337 */       this.g2.setColor(this.gridBlue);
/* 338 */       this.g2.drawString("Lands:AVG", this.xboundary + this.xAxisLength + 5, relativey(blueLands));
/* 339 */       this.g2.setColor(this.gridBlack);
/*     */     }
/*     */     else {
/* 342 */       this.g2.setColor(this.gridYellow);
/* 343 */       this.g2.drawString("Lands:Trial " + (trialNum + 1), this.xboundary + this.xAxisLength + 5, relativey(yellowLands));
/* 344 */       this.g2.setColor(this.gridBlue);
/* 345 */       this.g2.drawString("Lands:Trial " + (trialNum + 1), this.xboundary + this.xAxisLength + 5, relativey(blueLands));
/* 346 */       this.g2.setColor(this.gridBlack);
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawWeightsText(int trial, double wb, double wy) {
/* 351 */     int trialNum = trial;
/* 352 */     double blueWeight = wb;
/* 353 */     double yellowWeight = wy;
/*     */     
/*     */ 
/* 356 */     if (trialNum == -1) {
/* 357 */       this.g2.setColor(this.gridYellow);
/* 358 */       this.g2.drawString("WY:AVG", this.xboundary + this.xAxisLength + 5, relativey((int)yellowWeight));
/* 359 */       this.g2.setColor(this.gridBlue);
/* 360 */       this.g2.drawString("WB:AVG", this.xboundary + this.xAxisLength + 5, relativey((int)blueWeight));
/* 361 */       this.g2.setColor(this.gridBlack);
/*     */     }
/*     */     else {
/* 364 */       this.g2.setColor(this.gridYellow);
/* 365 */       this.g2.drawString("WY:Trial " + (trialNum + 1), this.xboundary + this.xAxisLength + 5, relativey((int)yellowWeight));
/* 366 */       this.g2.setColor(this.gridBlue);
/* 367 */       this.g2.drawString("WB:Trial " + (trialNum + 1), this.xboundary + this.xAxisLength + 5, relativey((int)blueWeight));
/* 368 */       this.g2.setColor(this.gridBlack);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private Result getResult(int tn, int cn)
/*     */   {
/* 375 */     int trialNum = tn;
/* 376 */     int cycleNum = cn;
/*     */     
/*     */ 
/* 379 */     Result data = trialNum == -1 ? (Result)this.averageList.get(cycleNum) : this.resultList.getArrayResult(trialNum, cycleNum);
/* 380 */     return data;
/*     */   }
/*     */   
/*     */   private void drawValue(int cn, double y1, double y2, double b1, double b2) {
/* 384 */     int cycleNum = cn;
/* 385 */     int y1Pos = (int)y1;
/* 386 */     int y2Pos = (int)y2;
/* 387 */     int b1Pos = (int)b1;
/* 388 */     int b2Pos = (int)b2;
/*     */     
/*     */ 
/* 391 */     this.g2.setColor(this.gridBlue);
/*     */     
/* 393 */     this.g2.drawLine(relativex(cycleNum), relativey(y1Pos), relativex(cycleNum + 1), relativey(y2Pos));
/* 394 */     this.g2.setColor(this.gridYellow);
/*     */     
/* 396 */     this.g2.drawLine(relativex(cycleNum), relativey(b1Pos), relativex(cycleNum + 1), relativey(b2Pos));
/* 397 */     this.g2.setColor(this.gridBlack);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void drawWeightsAndLands(int trialNum)
/*     */   {
/* 404 */     double wbNew = 0.0D;
/*     */     
/* 406 */     double wyNew = 0.0D;
/*     */     
/*     */ 
/* 409 */     int blueFlowersNew = 0;
/*     */     
/* 411 */     int yellowFlowersNew = 0;
/*     */     
/*     */ 
/* 414 */     int tempMax = getMaxWeight();
/* 415 */     if (tempMax > getMaxLand()) {
/* 416 */       setMaxy(tempMax);
/*     */     } else {
/* 418 */       setMaxy(getMaxLand());
/*     */     }
/*     */     
/* 421 */     Result data = getResult(trialNum, 0);
/* 422 */     wbNew = data.wbWeight();
/* 423 */     wyNew = data.wyWeight();
/*     */     
/* 425 */     for (int i = 0; i < this.resultList.getCycles(); i++)
/*     */     {
/* 427 */       data = getResult(trialNum, i);
/*     */       
/*     */ 
/* 430 */       int blueFlowersOld = blueFlowersNew;
/* 431 */       blueFlowersNew = data.getBlueLands();
/* 432 */       int yellowFlowersOld = yellowFlowersNew;
/* 433 */       yellowFlowersNew = data.getYellowLands();
/* 434 */       drawValue(i, blueFlowersOld, blueFlowersNew, yellowFlowersOld, yellowFlowersNew);
/*     */       
/*     */ 
/* 437 */       double wbOld = wbNew;
/* 438 */       wbNew = data.wbWeight();
/* 439 */       double wyOld = wyNew;
/* 440 */       wyNew = data.wyWeight();
/*     */       
/* 442 */       drawValue(i, wbOld, wbNew, wyOld, wyNew);
/*     */       
/*     */ 
/* 445 */       drawRewardSpike(data, i);
/*     */     }
/*     */     
/* 448 */     drawLandsText(trialNum, blueFlowersNew, yellowFlowersNew);
/* 449 */     drawWeightsText(trialNum, wbNew, wyNew);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void drawRewardSpike(Result data, int cycleNum)
/*     */   {
/* 456 */     boolean averaged = this.averageCheckBox.isSelected();
/* 457 */     boolean oneTrialChosen = this.trialsToDraw.size() == 1;
/*     */     
/* 459 */     if (!averaged)
/*     */     {
/* 461 */       if (oneTrialChosen)
/*     */       {
/* 463 */         double reward = data.getReward();
/* 464 */         if (reward == 0.0D) {
/* 465 */           this.g2.setColor(this.gridRed);
/*     */         }
/* 467 */         else if (reward == 0.7D) {
/* 468 */           this.g2.setColor(this.gridBlue);
/*     */         }
/* 470 */         else if (reward == 1.0D) {
/* 471 */           this.g2.setColor(this.gridYellow);
/*     */         }
/* 473 */         this.g2.fill3DRect(relativex(cycleNum), this.yboundary + this.yAxisLength + 5, relativex(cycleNum + 1) - relativex(cycleNum), 3, true);
/* 474 */         drawBoxKey();
/*     */         
/* 476 */         this.g2.setColor(this.gridGrey);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawBoxKey()
/*     */   {
/* 483 */     int edge = this.yboundary + this.yAxisLength;
/* 484 */     this.g2.setColor(this.gridGrey);
/* 485 */     this.g2.draw3DRect(2, edge + 18, 150, 45, true);
/* 486 */     this.g2.setColor(this.gridRed);
/* 487 */     this.g2.fill3DRect(5, edge + 21, 10, 10, true);
/* 488 */     this.g2.drawString("Non-Rewarding Land", 20, edge + 30);
/* 489 */     this.g2.setColor(this.gridYellow);
/* 490 */     this.g2.fill3DRect(5, edge + 36, 10, 10, true);
/* 491 */     this.g2.drawString("Yellow Rewarding Land", 20, edge + 45);
/* 492 */     this.g2.setColor(this.gridBlue);
/* 493 */     this.g2.fill3DRect(5, edge + 51, 10, 10, true);
/* 494 */     this.g2.drawString("Blue Rewarding Land", 20, edge + 60);
/*     */   }
/*     */   
/*     */   private void setMaxx(int max) {
/* 498 */     if (max > this.maxx) this.maxx = max;
/*     */   }
/*     */   
/*     */   private void setMaxy(int max) {
/* 502 */     if (max > this.maxy) this.maxy = max;
/*     */   }
/*     */   
/*     */   private int getMaxx() {
/* 506 */     return this.maxx;
/*     */   }
/*     */   
/*     */   private int getMaxy() {
/* 510 */     return this.maxy;
/*     */   }
/*     */   
/*     */   private int getMaxWeight()
/*     */   {
/* 515 */     int max = 0;
/* 516 */     for (int i = 0; i < this.trialsToDraw.size(); i++)
/*     */     {
/* 518 */       int trialNum = Integer.parseInt((String)this.trialsToDraw.get(i));
/* 519 */       Result data = this.resultList.getArrayResult(trialNum, this.resultList.getCycles() - 1);
/* 520 */       if (max <= (int)data.wbWeight()) max = (int)data.wbWeight();
/* 521 */       if (max <= (int)data.wyWeight()) { max = (int)data.wyWeight();
/*     */       }
/*     */     }
/* 524 */     return max;
/*     */   }
/*     */   
/*     */   private int getMaxLand()
/*     */   {
/* 529 */     int max = 0;
/* 530 */     for (int i = 0; i < this.trialsToDraw.size(); i++) {
/* 531 */       int trialNum = Integer.parseInt((String)this.trialsToDraw.get(i));
/* 532 */       Result data = this.resultList.getArrayResult(trialNum, this.resultList.getCycles() - 1);
/* 533 */       if (max <= data.getBlueLands()) max = data.getBlueLands();
/* 534 */       if (max <= data.getYellowLands()) max = data.getYellowLands();
/*     */     }
/* 536 */     return max;
/*     */   }
/*     */   
/*     */   private int relativex(int x)
/*     */   {
/* 541 */     x = (int)(this.xRatio * x);
/* 542 */     int pos = this.xboundary + x;
/* 543 */     return pos;
/*     */   }
/*     */   
/*     */   private int relativey(int y) {
/* 547 */     y = (int)(this.yRatio * y);
/* 548 */     int y0 = this.yboundary + this.yAxisLength - y;
/* 549 */     return y0;
/*     */   }
/*     */   
/*     */ 
/*     */   private void setXRatio()
/*     */   {
/* 555 */     this.xRatio = (Double.parseDouble("" + this.xAxisLength) / Double.parseDouble("" + getMaxx()));
/*     */   }
/*     */   
/*     */   private void setYRatio() {
/* 559 */     this.yRatio = (Double.parseDouble("" + this.yAxisLength) / Double.parseDouble("" + getMaxy()));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void setGraphPrintingDefaults()
/*     */   {
/* 584 */     this.xAxisLength = 350;
/* 585 */     this.yAxisLength = 300;
/* 586 */     this.xDivisions = 15;
/* 587 */     this.yDivisions = 15;
/* 588 */     this.backgroundColor = Color.white;
/* 589 */     this.gridYellow = new Color(200, 200, 0);
/*     */   }
/*     */   
/*     */   private void resetMaxMin()
/*     */   {
/* 594 */     this.maxx = 0;
/* 595 */     this.maxy = 0;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 617 */   private final Color gridGrey = new Color(192, 192, 192);
/* 618 */   private final Color gridLightGrey = new Color(184, 184, 184);
/* 619 */   private final Color gridBlack = new Color(0, 0, 0);
/* 620 */   private Color gridYellow = new Color(255, 255, 0);
/* 621 */   private final Color gridBlue = new Color(0, 0, 255);
/* 622 */   private final Color gridRed = new Color(255, 0, 0);
/* 623 */   private Color backgroundColor = new Color(200, 200, 200);
/*     */   private double divider;
/*     */   private ArrayList averageList;
/*     */   private java.awt.image.BufferedImage mImage;
/*     */   private Dimension panelSize;
/*     */ }


/* Location:              C:\Users\iain\Desktop\Documents\landisk\programming\Iains Work Samples\simulator\project.jar!\GraphDrawer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */