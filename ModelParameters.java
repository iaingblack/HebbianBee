/*     */ import java.util.StringTokenizer;
/*     */ 
/*     */ public final class ModelParameters {
/*     */   private double learningRate;
/*     */   private int trials;
/*     */   private int cycles;
/*     */   private int altHeight;
/*     */   private int altDescent;
/*     */   
/*  10 */   public ModelParameters() { this.learningRate = 0.9D;
/*  11 */     this.trials = 2;
/*  12 */     this.cycles = 10;
/*  13 */     this.altHeight = 50;
/*  14 */     this.yellowRatio = 3;
/*  15 */     this.altDescent = 1;
/*  16 */     this.learningOff = false;
/*  17 */     this.yellowWeight = 0.0D;
/*  18 */     this.blueWeight = 0.0D;
/*  19 */     this.initialBlueLands = 0;
/*  20 */     this.initialYellowLands = 0;
/*  21 */     this.weightMultiplier = 1;
/*  22 */     this.mValue = 30;
/*  23 */     this.bValue = 0.1D;
/*     */   }
/*     */   
/*     */   public ModelParameters(double lr, int tr, int cy)
/*     */   {
/*  28 */     this.learningRate = lr;
/*  29 */     this.trials = tr;
/*  30 */     this.cycles = cy;
/*  31 */     this.startingCycle = 0;
/*     */   }
/*     */   
/*     */   public final void stringUpdate(String loadedParameters) {
/*  35 */     StringTokenizer st = new StringTokenizer(loadedParameters, ":");
/*     */     
/*     */ 
/*  38 */     st.nextToken();
/*  39 */     String piece = st.nextToken();
/*  40 */     System.out.println(piece);
/*  41 */     double lr = Double.parseDouble(piece);
/*  42 */     setLearningRate(lr);
/*     */     
/*  44 */     piece = st.nextToken();
/*  45 */     System.out.println(piece);
/*  46 */     int tri = Integer.parseInt(piece);
/*  47 */     setTrials(tri);
/*     */     
/*  49 */     piece = st.nextToken();
/*  50 */     System.out.println(piece);
/*  51 */     int cyc = Integer.parseInt(piece);
/*  52 */     setCycles(cyc);
/*     */     
/*  54 */     piece = st.nextToken();
/*  55 */     System.out.println(piece);
/*  56 */     int height = Integer.parseInt(piece);
/*  57 */     setAltHeight(height);
/*     */     
/*  59 */     piece = st.nextToken();
/*  60 */     System.out.println(piece);
/*  61 */     int ratio = Integer.parseInt(piece);
/*  62 */     setYellowRatio(ratio);
/*     */     
/*  64 */     piece = st.nextToken();
/*  65 */     System.out.println(piece);
/*  66 */     System.out.println("ALTDESCENT IS - " + piece);
/*     */     
/*  68 */     setAltDescent(1);
/*     */     
/*  70 */     piece = st.nextToken();
/*  71 */     System.out.println(piece);
/*  72 */     if (piece.equals("false")) setLearning(false);
/*  73 */     if (piece.equals("true")) { setLearning(true);
/*     */     }
/*  75 */     piece = st.nextToken();
/*  76 */     System.out.println(piece);
/*  77 */     setYellowWeight(Double.parseDouble(piece));
/*     */     
/*  79 */     piece = st.nextToken();
/*  80 */     System.out.println(piece);
/*  81 */     setBlueWeight(Double.parseDouble(piece));
/*     */     
/*  83 */     piece = st.nextToken();
/*  84 */     System.out.println(piece);
/*  85 */     setWeightMultiplier(Integer.parseInt(piece));
/*     */     
/*  87 */     piece = st.nextToken();
/*  88 */     setmValue(Integer.parseInt(piece));
/*  89 */     System.out.println(piece);
/*     */     
/*  91 */     piece = st.nextToken();
/*  92 */     piece = piece.substring(0, piece.length() - 2);
/*  93 */     setbValue(Double.parseDouble(piece));
/*  94 */     System.out.println(piece);
/*  95 */     System.out.println("MODEL VARIABLES - " + getAll());
/*     */   }
/*     */   
/*     */ 
/*     */   public final String getAll()
/*     */   {
/* 101 */     String all = "" + getLearningRate() + ":" + getTrials() + ":" + getCycles() + ":" + getAltHeight() + ":" + getYellowRatio() + ":" + getAltDescent() + ":" + getLearning() + ":" + getYellowWeight() + ":" + getBlueWeight() + ":" + getWeightMultiplier() + ":" + getmValue() + ":" + getbValue();
/* 102 */     return all;
/*     */   }
/*     */   
/*     */   public String getAllFormatted()
/*     */   {
/* 107 */     String all = "";
/* 108 */     all = "Learning Rate \t- " + getLearningRate() + "\n";
/* 109 */     all = all + "Trials\t\t- " + getTrials() + "\n";
/* 110 */     all = all + "Cycles\t\t- " + getCycles() + "\n";
/* 111 */     all = all + "Init Altitude\t- " + getAltHeight() + "\n";
/* 112 */     all = all + "Yellow Ratio\t- " + getYellowRatio() + "\n";
/* 113 */     all = all + "Desc Altitude\t- " + getAltDescent() + "\n";
/* 114 */     all = all + "Learning off\t- " + getLearning() + "\n";
/* 115 */     all = all + "Yellow Weight\t- " + getYellowWeight() + "\n";
/* 116 */     all = all + "Blue Weight\t- " + getBlueWeight() + "\n";
/* 117 */     all = all + "Weight Multiplier  - " + getWeightMultiplier() + "\n";
/* 118 */     all = all + "Probability m Value- " + getmValue() + "\n";
/* 119 */     all = all + "Probability b Value- " + getbValue() + "\n";
/* 120 */     return all;
/*     */   }
/*     */   
/*     */   public final double getLearningRate()
/*     */   {
/* 125 */     return this.learningRate;
/*     */   }
/*     */   
/*     */   public final void setLearningRate(double newlr) {
/* 129 */     this.learningRate = newlr;
/*     */   }
/*     */   
/*     */   public final int getTrials()
/*     */   {
/* 134 */     return this.trials;
/*     */   }
/*     */   
/*     */   public final void setTrials(int newTrials) {
/* 138 */     this.trials = newTrials;
/*     */   }
/*     */   
/*     */   public final int getCycles()
/*     */   {
/* 143 */     return this.cycles;
/*     */   }
/*     */   
/*     */   public final void setCycles(int newCycles) {
/* 147 */     this.cycles = newCycles;
/*     */   }
/*     */   
/*     */   public final void setAltHeight(int height)
/*     */   {
/* 152 */     this.altHeight = height;
/*     */   }
/*     */   
/*     */   public final void setAltDescent(int rate) {
/* 156 */     this.altDescent = rate;
/*     */   }
/*     */   
/*     */   public final int getAltHeight() {
/* 160 */     return this.altHeight;
/*     */   }
/*     */   
/*     */   public final int getAltDescent() {
/* 164 */     return this.altDescent;
/*     */   }
/*     */   
/*     */   public final void setYellowRatio(int ratio)
/*     */   {
/* 169 */     this.yellowRatio = ratio;
/*     */   }
/*     */   
/*     */   public int getYellowRatio() {
/* 173 */     return this.yellowRatio;
/*     */   }
/*     */   
/*     */   public final void setLearning(boolean set)
/*     */   {
/* 178 */     this.learningOff = set;
/*     */   }
/*     */   
/*     */   public final boolean getLearning() {
/* 182 */     return this.learningOff;
/*     */   }
/*     */   
/*     */   public final void setYellowWeight(double yellow)
/*     */   {
/* 187 */     this.yellowWeight = yellow;
/*     */   }
/*     */   
/*     */   public final double getYellowWeight() {
/* 191 */     return this.yellowWeight;
/*     */   }
/*     */   
/*     */   public final void setBlueWeight(double blue) {
/* 195 */     this.blueWeight = blue;
/*     */   }
/*     */   
/*     */   public final double getBlueWeight() {
/* 199 */     return this.blueWeight;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStartingCycle(int startNum)
/*     */   {
/* 208 */     this.startingCycle = startNum;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getStartingCycle()
/*     */   {
/* 215 */     return this.startingCycle;
/*     */   }
/*     */   
/*     */   public void setInitialYellowLands(int value) {
/* 219 */     this.initialYellowLands = value;
/*     */   }
/*     */   
/* 222 */   public void setInitialBlueLands(int value) { this.initialBlueLands = value; }
/*     */   
/*     */   public int getInitialYellowLands() {
/* 225 */     return this.initialYellowLands;
/*     */   }
/*     */   
/* 228 */   public int getInitialBlueLands() { return this.initialBlueLands; }
/*     */   
/*     */   public void setmValue(int m)
/*     */   {
/* 232 */     this.mValue = m;
/*     */   }
/*     */   
/* 235 */   public int getmValue() { return this.mValue; }
/*     */   
/*     */   public void setbValue(double b)
/*     */   {
/* 239 */     this.bValue = b;
/*     */   }
/*     */   
/* 242 */   public double getbValue() { return this.bValue; }
/*     */   
/*     */   public int getWeightMultiplier()
/*     */   {
/* 246 */     return this.weightMultiplier;
/*     */   }
/*     */   
/* 249 */   public void setWeightMultiplier(int multiplier) { this.weightMultiplier = multiplier; }
/*     */   
/*     */ 
/*     */   private int yellowRatio;
/*     */   
/*     */   private boolean learningOff;
/*     */   
/*     */   private double yellowWeight;
/*     */   private double blueWeight;
/*     */   private int startingCycle;
/* 259 */   private int initialYellowLands = 0;
/* 260 */   private int initialBlueLands = 0;
/*     */   private int mValue;
/*     */   private double bValue;
/*     */   private int weightMultiplier;
/*     */ }


/* Location:              C:\Users\iain\Desktop\Documents\landisk\programming\Iains Work Samples\simulator\project.jar!\ModelParameters.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */