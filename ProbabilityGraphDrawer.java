/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JSlider;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProbabilityGraphDrawer
/*     */   extends JPanel
/*     */ {
/*     */   Graphics2D g2;
/*     */   JSlider mSlider;
/*     */   JSlider bSlider;
/*     */   
/*     */   public ProbabilityGraphDrawer(JSlider m, JSlider b)
/*     */   {
/*  20 */     this.mSlider = m;
/*  21 */     this.bSlider = b;
/*  22 */     this.g2 = ((Graphics2D)getGraphics());
/*  23 */     setPreferredSize(new Dimension(600, 400));
/*     */   }
/*     */   
/*     */   public void paintComponent(Graphics g) {
/*  27 */     this.g2 = ((Graphics2D)g);
/*     */     
/*  29 */     this.g2.setColor(new Color(250, 250, 250));
/*  30 */     this.g2.fill3DRect(0, 0, getWidth(), getHeight(), true);
/*  31 */     this.g2.setColor(Color.BLACK);
/*     */     
/*  33 */     drawGraph(this.mSlider.getValue(), this.bSlider.getValue() / 10.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   private void drawGraph(double m, double b)
/*     */   {
/*  39 */     int xBoundary = 50;
/*  40 */     int yBoundary = 50;
/*  41 */     boolean leftGuide = false;
/*  42 */     boolean rightGuide = false;
/*     */     
/*     */ 
/*  45 */     Dimension size = getPreferredSize();
/*     */     
/*     */ 
/*  48 */     double xRes = size.getWidth() / 2.0D - xBoundary / 2;
/*  49 */     xRes = 1.0D / xRes;
/*  50 */     double yRes = size.getHeight() - yBoundary;
/*  51 */     yRes = 1.0D / yRes;
/*     */     
/*  53 */     drawXAxis(xBoundary, yBoundary);
/*  54 */     drawYAxis(xBoundary, yBoundary);
/*  55 */     for (int x = 0; x < size.getWidth() - xBoundary; x++) {
/*  56 */       int xPos = 0;
/*  57 */       int yPos = 0;
/*  58 */       double position = 0.0D;
/*     */       
/*  60 */       xPos = x + xBoundary;
/*  61 */       position = 1.0D / (1.0D + Math.exp(m * (x * xRes - 1.0D) + b));
/*  62 */       yPos = (int)(position * (size.getHeight() - yBoundary));
/*  63 */       yPos = (int)size.getHeight() - yBoundary / 2 - yPos;
/*  64 */       this.g2.setColor(Color.BLACK);
/*  65 */       this.g2.drawLine(xPos, yPos - 1, xPos, yPos - 1);
/*     */       
/*     */ 
/*  68 */       if ((position < 0.997D) && 
/*  69 */         (!leftGuide)) {
/*  70 */         this.g2.setColor(Color.LIGHT_GRAY);
/*  71 */         this.g2.drawLine(xPos, yBoundary / 2, xPos, (int)size.getHeight() - yBoundary / 2);
/*  72 */         this.g2.setColor(Color.BLACK);
/*  73 */         leftGuide = true;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*  78 */       if ((!rightGuide) && (position < 0.003D)) {
/*  79 */         this.g2.setColor(Color.LIGHT_GRAY);
/*  80 */         this.g2.drawLine(xPos, yBoundary / 2, xPos, (int)size.getHeight() - yBoundary / 2);
/*  81 */         this.g2.setColor(Color.BLACK);
/*  82 */         rightGuide = true;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void drawYAxis(int xBoundary, int yBoundary)
/*     */   {
/*  90 */     Dimension size = getPreferredSize();
/*  91 */     int gap = ((int)size.getHeight() - yBoundary) / 5;
/*     */     
/*  93 */     this.g2.drawLine(xBoundary, yBoundary / 2, xBoundary, (int)size.getHeight() - yBoundary / 2);
/*  94 */     String probability = "PROBABILITY";
/*  95 */     for (int i = 0; i < probability.length(); i++) {
/*  96 */       this.g2.drawString("" + probability.charAt(i), 10, 100 + 13 * (i + 1));
/*     */     }
/*     */     
/*  99 */     String[] yLabel = { "1.0", "0.8", "0.6", "0.4", "0.2", "0.0" };
/* 100 */     for (int i = 0; i < 6; i++) {
/* 101 */       this.g2.drawString("" + yLabel[i], xBoundary - 25, yBoundary / 2 + gap * i + 5);
/* 102 */       this.g2.drawLine(xBoundary - 5, yBoundary / 2 + gap * i, xBoundary, yBoundary / 2 + gap * i);
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawXAxis(int xBoundary, int yBoundary) {
/* 107 */     Dimension size = getPreferredSize();
/* 108 */     int gap = ((int)size.getWidth() - xBoundary) / 8;
/*     */     
/* 110 */     this.g2.drawLine(xBoundary, (int)size.getHeight() - yBoundary / 2, (int)size.getWidth(), (int)size.getHeight() - yBoundary / 2);
/* 111 */     String error = "ERROR";
/* 112 */     for (int i = 0; i < error.length(); i++) {
/* 113 */       this.g2.drawString("" + error.charAt(i), (int)size.getWidth() / 2 + 10 * i, (int)size.getHeight() - yBoundary / 2 + 30);
/*     */     }
/*     */     
/* 116 */     int yHeight = (int)size.getHeight() - yBoundary / 2;
/* 117 */     String[] xLabel = { "-1.0", "-0.75", "-0.5", "-0.25", "0", "0.25", "0.5", "0.75", "1.0" };
/* 118 */     for (int i = 0; i < 9; i++)
/*     */     {
/* 120 */       this.g2.drawLine(xBoundary + gap * i, yHeight, xBoundary + gap * i, yHeight + 5);
/*     */       
/* 122 */       if (i == 4) {
/* 123 */         this.g2.setColor(Color.LIGHT_GRAY);
/* 124 */         this.g2.drawLine(xBoundary + gap * i, yBoundary / 2, xBoundary + gap * i, yHeight);
/* 125 */         this.g2.setColor(Color.BLACK);
/*     */       }
/* 127 */       this.g2.drawString("" + xLabel[i], xBoundary + gap * i - 9, yHeight + 17);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\iain\Desktop\Documents\landisk\programming\Iains Work Samples\simulator\project.jar!\ProbabilityGraphDrawer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */