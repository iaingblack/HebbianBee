/*     */ import java.awt.Dimension;
/*     */ 
/*     */ public final class Bee
/*     */ {
/*     */   private int xPosition;
/*     */   private int yPosition;
/*     */   private int headingNum;
/*     */   private int altitude;
/*     */   private int moveX;
/*     */   private int moveY;
/*     */   private int yellowLands;
/*     */   private int blueLands;
/*     */   private int fieldWidth;
/*     */   private int fieldHeight;
/*     */   private final ModelParameters modelParameters;
/*     */   
/*     */   public Bee(ModelParameters modelparameters) {
/*  18 */     this.modelParameters = modelparameters;
/*  19 */     randomPosition();
/*  20 */     this.xPosition = getXPosition();
/*  21 */     this.yPosition = getYPosition();
/*  22 */     randomHeading();
/*  23 */     this.headingNum = getHeading();
/*  24 */     this.altitude = this.modelParameters.getAltHeight();
/*  25 */     this.fieldWidth = 480;
/*  26 */     this.fieldHeight = 360;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void reset()
/*     */   {
/*  35 */     randomPosition();
/*  36 */     initAltitude();
/*  37 */     randomHeading();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public final void resetLands()
/*     */   {
/*  44 */     resetYellowLands();
/*  45 */     resetBlueLands();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void setFieldSize(Dimension newFieldSize)
/*     */   {
/*  53 */     this.fieldWidth = ((int)newFieldSize.getWidth());
/*  54 */     this.fieldHeight = ((int)newFieldSize.getHeight());
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
/*     */   public final void bounce(int wallHit)
/*     */   {
/*  68 */     int heading = getHeading();
/*     */     
/*  70 */     if (wallHit != 0)
/*     */     {
/*  72 */       if (wallHit == 1) {
/*  73 */         switch (heading)
/*     */         {
/*     */         case 13: 
/*  76 */           setHeading(11);
/*  77 */           break;
/*     */         case 14: 
/*  79 */           setHeading(10);
/*  80 */           break;
/*     */         case 15: 
/*  82 */           setHeading(9);
/*  83 */           break;
/*     */         case 0: 
/*  85 */           setHeading(8);
/*  86 */           break;
/*     */         case 1: 
/*  88 */           setHeading(7);
/*  89 */           break;
/*     */         case 2: 
/*  91 */           setHeading(6);
/*  92 */           break;
/*     */         case 3: 
/*  94 */           setHeading(5);
/*     */         
/*     */ 
/*     */         }
/*     */         
/*  99 */       } else if (wallHit == 2) {
/* 100 */         switch (heading) {
/*     */         case 1: 
/* 102 */           setHeading(15);
/* 103 */           break;
/*     */         case 2: 
/* 105 */           setHeading(14);
/* 106 */           break;
/*     */         case 3: 
/* 108 */           setHeading(13);
/* 109 */           break;
/*     */         case 4: 
/* 111 */           setHeading(12);
/* 112 */           break;
/*     */         case 5: 
/* 114 */           setHeading(11);
/* 115 */           break;
/*     */         case 6: 
/* 117 */           setHeading(10);
/* 118 */           break;
/*     */         case 7: 
/* 120 */           setHeading(9);
/*     */         
/*     */ 
/*     */         }
/*     */         
/* 125 */       } else if (wallHit == 3) {
/* 126 */         switch (heading) {
/*     */         case 11: 
/* 128 */           setHeading(13);
/* 129 */           break;
/*     */         case 10: 
/* 131 */           setHeading(14);
/* 132 */           break;
/*     */         case 9: 
/* 134 */           setHeading(15);
/* 135 */           break;
/*     */         case 8: 
/* 137 */           setHeading(0);
/* 138 */           break;
/*     */         case 7: 
/* 140 */           setHeading(1);
/* 141 */           break;
/*     */         case 6: 
/* 143 */           setHeading(2);
/* 144 */           break;
/*     */         case 5: 
/* 146 */           setHeading(3);
/*     */         
/*     */ 
/*     */         }
/*     */         
/* 151 */       } else if (wallHit == 4) {
/* 152 */         switch (heading) {
/*     */         case 15: 
/* 154 */           setHeading(1);
/* 155 */           break;
/*     */         case 14: 
/* 157 */           setHeading(2);
/* 158 */           break;
/*     */         case 13: 
/* 160 */           setHeading(3);
/* 161 */           break;
/*     */         case 12: 
/* 163 */           setHeading(4);
/* 164 */           break;
/*     */         case 11: 
/* 166 */           setHeading(5);
/* 167 */           break;
/*     */         case 10: 
/* 169 */           setHeading(6);
/* 170 */           break;
/*     */         case 9: 
/* 172 */           setHeading(7);
/*     */         }
/*     */         
/*     */       }
/*     */       
/*     */ 
/* 178 */       updateMovement(getHeading());
/*     */     }
/*     */   }
/*     */   
/*     */   private void randomPosition() {
/* 183 */     this.xPosition = ((int)(this.fieldWidth * Math.random()) + 1);
/* 184 */     this.yPosition = ((int)(this.fieldHeight * Math.random()) + 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void calcNextPosition()
/*     */   {
/* 192 */     setXPosition(getXPosition() + getMoveX());
/* 193 */     setYPosition(getYPosition() + getMoveY());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void randomHeading()
/*     */   {
/* 200 */     this.headingNum = ((int)(16.0D * Math.random()));
/* 201 */     updateMovement(this.headingNum);
/*     */   }
/*     */   
/*     */   private void updateMovement(int heading)
/*     */   {
/* 206 */     switch (heading) {
/*     */     case 0: 
/* 208 */       this.moveX = 0;
/* 209 */       this.moveY = -4;
/* 210 */       break;
/*     */     case 1: 
/* 212 */       this.moveX = 1;
/* 213 */       this.moveY = -3;
/* 214 */       break;
/*     */     case 2: 
/* 216 */       this.moveX = 2;
/* 217 */       this.moveY = -2;
/* 218 */       break;
/*     */     case 3: 
/* 220 */       this.moveX = 3;
/* 221 */       this.moveY = -1;
/* 222 */       break;
/*     */     case 4: 
/* 224 */       this.moveX = 4;
/* 225 */       this.moveY = 0;
/* 226 */       break;
/*     */     case 5: 
/* 228 */       this.moveX = 3;
/* 229 */       this.moveY = 1;
/* 230 */       break;
/*     */     case 6: 
/* 232 */       this.moveX = 2;
/* 233 */       this.moveY = 2;
/* 234 */       break;
/*     */     case 7: 
/* 236 */       this.moveX = 1;
/* 237 */       this.moveY = 3;
/* 238 */       break;
/*     */     case 8: 
/* 240 */       this.moveX = 0;
/* 241 */       this.moveY = 4;
/* 242 */       break;
/*     */     case 9: 
/* 244 */       this.moveX = -1;
/* 245 */       this.moveY = 3;
/* 246 */       break;
/*     */     case 10: 
/* 248 */       this.moveX = -2;
/* 249 */       this.moveY = 2;
/* 250 */       break;
/*     */     case 11: 
/* 252 */       this.moveX = -3;
/* 253 */       this.moveY = 1;
/* 254 */       break;
/*     */     case 12: 
/* 256 */       this.moveX = -4;
/* 257 */       this.moveY = 0;
/* 258 */       break;
/*     */     case 13: 
/* 260 */       this.moveX = -3;
/* 261 */       this.moveY = -1;
/* 262 */       break;
/*     */     case 14: 
/* 264 */       this.moveX = -2;
/* 265 */       this.moveY = -2;
/* 266 */       break;
/*     */     case 15: 
/* 268 */       this.moveX = -1;
/* 269 */       this.moveY = -3;
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void swerve()
/*     */   {
/* 280 */     int randomNum = (int)(9.0D * Math.random() - 4.0D);
/* 281 */     this.headingNum += randomNum;
/*     */     
/* 283 */     if (this.headingNum > 15) this.headingNum -= 16;
/* 284 */     if (this.headingNum < 0) { this.headingNum += 16;
/*     */     }
/* 286 */     updateMovement(this.headingNum);
/*     */   }
/*     */   
/*     */   private int getHeading() {
/* 290 */     return this.headingNum;
/*     */   }
/*     */   
/*     */   private void setHeading(int h) {
/* 294 */     this.headingNum = h;
/*     */   }
/*     */   
/*     */   private int getMoveX() {
/* 298 */     return this.moveX;
/*     */   }
/*     */   
/*     */   private int getMoveY() {
/* 302 */     return this.moveY;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final int getAltitude()
/*     */   {
/* 310 */     return this.altitude;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void setAltitude(int newAltitude)
/*     */   {
/* 319 */     this.altitude = newAltitude;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final int getXPosition()
/*     */   {
/* 327 */     return this.xPosition;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final int getYPosition()
/*     */   {
/* 335 */     return this.yPosition;
/*     */   }
/*     */   
/*     */   private void setXPosition(int x) {
/* 339 */     this.xPosition = x;
/*     */   }
/*     */   
/*     */   private void setYPosition(int y) {
/* 343 */     this.yPosition = y;
/*     */   }
/*     */   
/*     */   private void initAltitude()
/*     */   {
/* 348 */     this.altitude = this.modelParameters.getAltHeight();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public final void yellowLand()
/*     */   {
/* 355 */     this.yellowLands += 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public final void blueLand()
/*     */   {
/* 362 */     this.blueLands += 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final int getYellowLands()
/*     */   {
/* 370 */     return this.yellowLands;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final int getBlueLands()
/*     */   {
/* 378 */     return this.blueLands;
/*     */   }
/*     */   
/*     */   private void resetYellowLands() {
/* 382 */     this.yellowLands = this.modelParameters.getInitialYellowLands();
/*     */   }
/*     */   
/*     */   private void resetBlueLands() {
/* 386 */     this.blueLands = this.modelParameters.getInitialBlueLands();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void resetModel()
/*     */   {
/* 394 */     resetLands();
/* 395 */     reset();
/*     */   }
/*     */ }


/* Location:              C:\Users\iain\Desktop\Documents\landisk\programming\Iains Work Samples\simulator\project.jar!\Bee.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */