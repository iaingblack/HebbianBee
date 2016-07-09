/*     */ import java.text.DecimalFormat;
/*     */ 
/*     */ public final class Result {
/*     */   private final int trialNum;
/*     */   private final int cycleNum;
/*     */   private final double rewardReceived;
/*     */   private final String flowerColor;
/*     */   
/*   9 */   public Result(double reward, String color, double wb, double wy, int trial, int cycle, int blue, int yellow) { this.trialNum = trial;
/*  10 */     this.cycleNum = cycle;
/*  11 */     this.rewardReceived = reward;
/*  12 */     this.flowerColor = color;
/*  13 */     this.wbWeight = wb;
/*  14 */     this.wyWeight = wy;
/*  15 */     this.blueLands = blue;
/*  16 */     this.yellowLands = yellow;
/*     */   }
/*     */   
/*     */   public Result()
/*     */   {
/*  21 */     this.trialNum = 0;
/*  22 */     this.cycleNum = 0;
/*  23 */     this.rewardReceived = 0.0D;
/*  24 */     this.flowerColor = "Yellow/Blue";
/*  25 */     this.wbWeight = 0.0D;
/*  26 */     this.wyWeight = 0.0D;
/*  27 */     this.blueLands = 0;
/*  28 */     this.yellowLands = 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public final String asString()
/*     */   {
/*  34 */     this.twodec = new DecimalFormat("#0.00");
/*     */     
/*  36 */     String line = this.trialNum + ":" + this.cycleNum + ":" + this.rewardReceived + ":" + this.flowerColor + ":" + this.twodec.format(this.wbWeight) + ":" + this.twodec.format(this.wyWeight) + ":" + this.blueLands + ":" + this.yellowLands;
/*  37 */     return line;
/*     */   }
/*     */   
/*     */ 
/*     */   public final String[] getResultStringArray()
/*     */   {
/*  43 */     this.twodec = new DecimalFormat("#0.00");
/*  44 */     String[] details = { "" + getTrialNum(), "" + getCycleNum(), "" + getReward(), flowerColor(), "" + this.twodec.format(wbWeight()), "" + this.twodec.format(wyWeight()), "" + getBlueLands(), "" + getYellowLands() };
/*  45 */     return details;
/*     */   }
/*     */   
/*     */   private double wbWeight;
/*     */   private double wyWeight;
/*     */   private int blueLands;
/*     */   private int yellowLands;
/*     */   private DecimalFormat twodec;
/*  53 */   public String getCSVHeader() { StringBuffer header = new StringBuffer();
/*     */     
/*     */ 
/*  56 */     header.append("Trial,");
/*  57 */     header.append("Cycle,");
/*  58 */     header.append("Reward,");
/*  59 */     header.append("Colour,");
/*  60 */     header.append("Blue Weight,");
/*  61 */     header.append("Yellow Weight,");
/*  62 */     header.append("Blue Lands,");
/*  63 */     header.append("Yellow Lands\n");
/*     */     
/*  65 */     String headerString = header.toString();
/*  66 */     return headerString;
/*     */   }
/*     */   
/*     */ 
/*     */   public final String getFormattedResultString()
/*     */   {
/*  72 */     this.twodec = new DecimalFormat("#0.00");
/*     */     
/*  74 */     String line = "Trial - " + this.trialNum;
/*  75 */     line = line + ".  Cycle - " + this.cycleNum;
/*  76 */     line = line + "*  Reward - " + this.rewardReceived;
/*  77 */     line = line + ".  Flower Colour - " + this.flowerColor;
/*  78 */     line = line + ".  WB Weight - " + this.twodec.format(this.wbWeight);
/*  79 */     line = line + ".  WY Weight - " + this.twodec.format(this.wyWeight);
/*  80 */     line = line + ".  Blue Lands - " + this.blueLands;
/*  81 */     line = line + ".  Yellow Lands - " + this.yellowLands;
/*  82 */     return line;
/*     */   }
/*     */   
/*     */   public int getTrialNum() {
/*  86 */     return this.trialNum;
/*     */   }
/*     */   
/*     */   public int getCycleNum() {
/*  90 */     return this.cycleNum;
/*     */   }
/*     */   
/*     */   public double getReward() {
/*  94 */     return this.rewardReceived;
/*     */   }
/*     */   
/*     */   public String flowerColor() {
/*  98 */     return this.flowerColor;
/*     */   }
/*     */   
/*     */   public final double wbWeight() {
/* 102 */     return this.wbWeight;
/*     */   }
/*     */   
/*     */   public final double wyWeight() {
/* 106 */     return this.wyWeight;
/*     */   }
/*     */   
/*     */   public final int getBlueLands() {
/* 110 */     return this.blueLands;
/*     */   }
/*     */   
/*     */   public final int getYellowLands() {
/* 114 */     return this.yellowLands;
/*     */   }
/*     */   
/*     */   public final void setwbWeight(double num) {
/* 118 */     this.wbWeight = num;
/*     */   }
/*     */   
/*     */   public final void setwyWeight(double num) {
/* 122 */     this.wyWeight = num;
/*     */   }
/*     */   
/*     */   public final void setBlueLands(int num) {
/* 126 */     this.blueLands = num;
/*     */   }
/*     */   
/*     */   public final void setYellowLands(int num) {
/* 130 */     this.yellowLands = num;
/*     */   }
/*     */ }


/* Location:              C:\Users\iain\Desktop\Documents\landisk\programming\Iains Work Samples\simulator\project.jar!\Result.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */