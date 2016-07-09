/*     */ public final class NeuronModel {
/*     */   private double currentWb;
/*     */   private double currentWy;
/*     */   private double oldWb;
/*     */   private double oldWy;
/*     */   
/*     */   public final void step(double currentYellowPercentage, double currentBluePercentage) {
/*   8 */     this.olderYellowPercentage = this.oldYellowPercentage;
/*   9 */     this.olderBluePercentage = this.oldBluePercentage;
/*     */     
/*  11 */     this.oldYellowPercentage = this.newYellowPercentage;
/*  12 */     this.oldBluePercentage = this.newBluePercentage;
/*     */     
/*  14 */     this.newYellowPercentage = currentYellowPercentage;
/*  15 */     this.newBluePercentage = currentBluePercentage;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  20 */     this.error = (0.0D + getVisionAbsolute(this.currentWb, this.currentWy, this.newBluePercentage, this.newYellowPercentage) - getVisionAbsolute(this.currentWb, this.currentWy, this.oldBluePercentage, this.oldYellowPercentage));
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
/*  32 */     if (this.error > 0.99D) this.error = 1.0D;
/*  33 */     if (this.error < -0.99D) { this.error = -1.0D;
/*     */     }
/*  35 */     willBeeReorientate(this.error);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double getVisionChange(double Wb, double Wy, double bluePercentage, double yellowPercentage, double oldBluePercentage, double oldYellowPercentage)
/*     */   {
/*  44 */     double bluePercentageChange = calculatePercentageChange(bluePercentage, oldBluePercentage);
/*  45 */     double yellowPercentageChange = calculatePercentageChange(yellowPercentage, oldYellowPercentage);
/*     */     
/*  47 */     double vision = Wb * bluePercentageChange + Wy * yellowPercentageChange;
/*  48 */     return vision;
/*     */   }
/*     */   
/*  51 */   public double getVisionAbsolute(double Wb, double Wy, double bluePercentage, double yellowPercentage) { return Wb * bluePercentage + Wy * yellowPercentage; }
/*     */   
/*     */ 
/*     */ 
/*     */   private double calculatePercentageChange(double newPercentage, double oldPercentage)
/*     */   {
/*  57 */     double change = newPercentage / oldPercentage - 1.0D;
/*  58 */     return change;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double getReward(double blue, double yellow, int altitude)
/*     */   {
/*  67 */     double reward = 0.0D;
/*  68 */     double bluePercentage = blue;
/*  69 */     double yellowPercentage = yellow;
/*  70 */     int beesAltitude = altitude;
/*     */     
/*     */ 
/*     */ 
/*  74 */     reward = bluePercentage * this.BLUEREWARD * beesAltitude;
/*  75 */     reward += yellowPercentage * this.YELLOWREWARD * beesAltitude / 3.0D;
/*     */     
/*  77 */     return reward;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private double yellowFlowerLand()
/*     */   {
/*  84 */     int chanceOfReward = this.modelParameters.getYellowRatio();
/*  85 */     this.landingReward = 0.0D;
/*     */     
/*  87 */     int num = (int)(Math.random() * chanceOfReward);
/*  88 */     if (num == chanceOfReward - 1) {
/*  89 */       this.landingReward = this.YELLOWREWARD;
/*     */     }
/*     */     else {
/*  92 */       this.landingReward = this.NOREWARD;
/*     */     }
/*     */     
/*  95 */     return this.landingReward;
/*     */   }
/*     */   
/*     */   private double blueFlowerLand()
/*     */   {
/* 100 */     this.landingReward = 0.0D;
/* 101 */     this.landingReward = this.BLUEREWARD;
/* 102 */     return this.landingReward;
/*     */   }
/*     */   
/*     */ 
/*     */   public final void updateWeights(double yellow, double blue)
/*     */   {
/* 108 */     this.oldWb = this.currentWb;
/* 109 */     this.oldWy = this.currentWy;
/*     */     
/*     */ 
/* 112 */     if (yellow == 1.0D) {
/* 113 */       this.reward = yellowFlowerLand();
/* 114 */       this.currentWy += changeWeight(this.reward);
/* 115 */     } else if (blue == 1.0D) {
/* 116 */       this.reward = blueFlowerLand();
/* 117 */       this.currentWb += changeWeight(this.reward);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double changeWeight(double reward)
/*     */   {
/* 125 */     double changeValue = 0.0D;
/*     */     
/*     */ 
/* 128 */     if ((reward == 1.0D) || (reward == 0.0D))
/*     */     {
/* 130 */       changeValue = this.learningRate * 0.5D * (reward * this.modelParameters.getWeightMultiplier() - this.currentWy * 1.0D);
/*     */     }
/*     */     else {
/* 133 */       changeValue = this.learningRate * 0.5D * (reward * this.modelParameters.getWeightMultiplier() - this.currentWb * 1.0D);
/*     */     }
/*     */     
/* 136 */     return changeValue;
/*     */   }
/*     */   
/*     */ 
/*     */   private double learningRate;
/*     */   
/*     */   private double error;
/*     */   
/*     */   private double newBluePercentage;
/*     */   private double newYellowPercentage;
/*     */   private double oldYellowPercentage;
/*     */   public double getNewYellowRewardWeight(double oldWeight, boolean rewardingLand)
/*     */   {
/* 149 */     double oldWy = oldWeight;
/* 150 */     double landReward; double landReward; if (rewardingLand) landReward = this.YELLOWREWARD; else
/* 151 */       landReward = this.NOREWARD;
/* 152 */     double newWy = oldWy + this.learningRate * 0.5D * (landReward * this.modelParameters.getWeightMultiplier() - oldWeight * 1.0D);
/* 153 */     return newWy;
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
/*     */   public double getNewBlueRewardWeight(double oldWeight)
/*     */   {
/* 166 */     double oldWb = oldWeight;
/* 167 */     double landReward = this.BLUEREWARD;
/* 168 */     double newWb = oldWb + this.learningRate * 0.5D * (landReward * this.modelParameters.getWeightMultiplier() - oldWeight * 1.0D);
/* 169 */     return newWb;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void willBeeReorientate(double err)
/*     */   {
/* 176 */     double probability = getProbablility(err);
/*     */     
/*     */ 
/* 179 */     if (probability >= 0.99D) {
/* 180 */       this.isError = true;
/*     */     } else {
/* 182 */       this.isError = false;
/*     */     }
/*     */   }
/*     */   
/*     */   private double getProbablility(double err) {
/* 187 */     double probability = 0.0D;
/* 188 */     double m = this.modelParameters.getmValue();
/* 189 */     double b = this.modelParameters.getbValue();
/*     */     
/* 191 */     probability = 1.0D + Math.exp(m * err + b);
/* 192 */     probability = 1.0D / probability;
/* 193 */     return probability;
/*     */   }
/*     */   
/*     */   public final boolean isError()
/*     */   {
/* 198 */     return this.isError;
/*     */   }
/*     */   
/*     */ 
/*     */   public final void resetModel()
/*     */   {
/* 204 */     if (this.modelParameters.getLearning()) {
/* 205 */       this.currentWy = this.modelParameters.getYellowWeight();
/* 206 */       this.currentWb = this.modelParameters.getBlueWeight();
/* 207 */       this.oldWy = this.modelParameters.getBlueWeight();
/* 208 */       this.oldWb = this.modelParameters.getYellowWeight();
/*     */       
/* 210 */       setLearningRate(0.0D);
/*     */     }
/*     */     else
/*     */     {
/* 214 */       this.currentWb = this.initialWb;
/* 215 */       this.currentWy = this.initialWy;
/* 216 */       this.oldWb = this.initialWb;
/* 217 */       this.oldWy = this.initialWy;
/* 218 */       setLearningRate(this.modelParameters.getLearningRate());
/*     */     }
/*     */     
/* 221 */     this.error = 0.0D;
/*     */     
/* 223 */     this.newBluePercentage = 0.5D;
/* 224 */     this.newYellowPercentage = 0.5D;
/* 225 */     this.oldBluePercentage = 0.5D;
/* 226 */     this.oldYellowPercentage = 0.5D;
/* 227 */     this.olderBluePercentage = 0.5D;
/* 228 */     this.olderYellowPercentage = 0.5D;
/* 229 */     this.isError = false;
/* 230 */     this.landingReward = 0.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setWeights(double yellow, double blue)
/*     */   {
/* 239 */     this.currentWy = yellow;
/* 240 */     this.oldWy = yellow;
/* 241 */     this.currentWb = blue;
/* 242 */     this.oldWb = blue;
/*     */   }
/*     */   
/*     */ 
/*     */   public final double getOldWbWeight()
/*     */   {
/* 248 */     return this.oldWb;
/*     */   }
/*     */   
/*     */   public final double getOldWyWeight() {
/* 252 */     return this.oldWy;
/*     */   }
/*     */   
/*     */   public final double getWbWeight() {
/* 256 */     return this.currentWb;
/*     */   }
/*     */   
/*     */   public final double getWyWeight() {
/* 260 */     return this.currentWy;
/*     */   }
/*     */   
/*     */   public final double getError() {
/* 264 */     return this.error;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void setLearningRate(double newlr)
/*     */   {
/* 271 */     this.learningRate = newlr;
/*     */   }
/*     */   
/*     */   public final double getLandingReward() {
/* 275 */     return this.landingReward;
/*     */   }
/*     */   
/*     */   public void setInitialWb(double value) {
/* 279 */     this.initialWb = value;
/*     */   }
/*     */   
/* 282 */   public void setInitialWy(double value) { this.initialWy = value; }
/*     */   
/*     */ 
/*     */ 
/*     */   public NeuronModel(ModelParameters mp, Bee b)
/*     */   {
/* 288 */     this.bee = b;
/* 289 */     this.modelParameters = mp;
/* 290 */     resetModel();
/* 291 */     this.YELLOWREWARD = 1.0D;
/* 292 */     this.BLUEREWARD = 0.7D;
/* 293 */     this.NOREWARD = 0.0D;
/* 294 */     this.learningRate = this.modelParameters.getLearningRate();
/*     */     
/* 296 */     if (this.modelParameters.getLearning()) {
/* 297 */       setWeights(this.modelParameters.getYellowWeight(), this.modelParameters.getBlueWeight());
/* 298 */       this.learningRate = 0.0D;
/*     */     }
/* 300 */     this.beesAltitude = 0;
/*     */   }
/*     */   
/*     */ 
/*     */   private double oldBluePercentage;
/*     */   
/*     */   private double olderBluePercentage;
/*     */   
/*     */   private double olderYellowPercentage;
/*     */   
/*     */   private boolean isError;
/*     */   private double reward;
/*     */   public final double YELLOWREWARD;
/*     */   public final double BLUEREWARD;
/*     */   public final double NOREWARD;
/*     */   private double landingReward;
/* 316 */   private double initialWb = 0.0D;
/* 317 */   private double initialWy = 0.0D;
/*     */   private int beesAltitude;
/*     */   private Bee bee;
/*     */   private final ModelParameters modelParameters;
/*     */ }


/* Location:              C:\Users\iain\Desktop\Documents\landisk\programming\Iains Work Samples\simulator\project.jar!\NeuronModel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */