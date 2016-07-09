/*     */ import java.io.PrintStream;
/*     */ import java.text.DecimalFormat;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JProgressBar;
/*     */ import javax.swing.JSlider;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.text.Document;
/*     */ 
/*     */ public final class SimulatorManager
/*     */   extends Thread
/*     */ {
/*     */   private boolean isRunning;
/*     */   private final Field field;
/*     */   private final NeuronModel neuronModel;
/*     */   private final Bee bee;
/*     */   private final ResultList resultList;
/*     */   private final ModelParameters modelParameters;
/*     */   private final JSlider visionSlider;
/*     */   private final JSlider altitudeSlider;
/*     */   private final JSlider errorSlider;
/*     */   private final JSlider speedSlider;
/*     */   
/*     */   private void initSimulator()
/*     */   {
/*  12 */     this.isRunning = true;
/*  13 */     this.bee.reset();
/*  14 */     this.bee.setFieldSize(this.field.getFieldSize());
/*     */     
/*  16 */     this.field.clear();
/*     */     
/*     */ 
/*  19 */     this.neuronModel.resetModel();
/*  20 */     this.bee.resetModel();
/*  21 */     updateInfoLabels();
/*     */   }
/*     */   
/*     */   private final JLabel oldwyLabel;
/*     */   private final JLabel oldwbLabel;
/*     */   
/*     */   public final void run()
/*     */   {
/*  25 */     initSimulator();
/*  26 */     while (this.isRunning)
/*     */     {
/*  28 */       if (this.trialNum > this.TRIALSTORUN) { stopSimulation("Finished Trials");
/*     */       }
/*     */       
/*  31 */       if (this.cycleNum < this.CYCLESPERTRIAL)
/*     */       {
/*  33 */         this.field.drawBee(this.bee.getXPosition(), this.bee.getYPosition());
/*     */         
/*     */ 
/*  36 */         int sliderint = this.visionSlider.getValue();
/*  37 */         double sliderValue = sliderint;
/*  38 */         double blue = (50.0D + sliderValue) / 100.0D;
/*  39 */         double yellow = (50.0D - sliderValue) / 100.0D;
/*     */         
/*     */ 
/*  42 */         if (this.bee.getAltitude() < 0)
/*     */         {
/*     */ 
/*     */ 
/*  46 */           if (((yellow == 1.0D) && (blue == 0.0D)) || ((yellow == 0.0D) && (blue == 1.0D)))
/*     */           {
/*  48 */             updateProgressBar();
/*  49 */             this.cycleNum += 1;
/*  50 */             this.neuronModel.updateWeights(yellow, blue);
/*     */             
/*  52 */             if (yellow == 1.0D)
/*     */             {
/*  54 */               this.bee.yellowLand();
/*  55 */               this.flowerColour = "Yellow";
/*  56 */               this.flowerReward = this.neuronModel.getLandingReward();
/*     */             }
/*  58 */             else if (blue == 1.0D)
/*     */             {
/*  60 */               this.bee.blueLand();
/*  61 */               this.flowerColour = "Blue";
/*  62 */               this.flowerReward = this.neuronModel.getLandingReward();
/*     */             } else {
/*  64 */               System.out.println("BUG!!! - YELLOWS - " + this.bee.getYellowLands() + "   BLUES - " + this.bee.getBlueLands()); }
/*  65 */             saveResult(this.flowerReward, this.flowerColour, this.trialNum, this.cycleNum);
/*  66 */             if (this.animationCheckBox.isSelected()) {
/*  67 */               showResult();
/*     */             }
/*     */             
/*  70 */             this.bee.reset();
/*  71 */             this.field.clear();
/*     */ 
/*     */           }
/*     */           
/*     */ 
/*     */         }
/*     */         else
/*     */         {
/*     */ 
/*  80 */           this.neuronModel.step(yellow, blue);
/*     */           
/*  82 */           if (this.neuronModel.isError()) {
/*  83 */             this.bee.swerve();
/*  84 */             this.field.beeSwerving();
/*     */           }
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*  90 */         int randomNum = (int)(this.modelParameters.getAltDescent() * Math.random() + 1.0D);
/*  91 */         this.bee.setAltitude(this.bee.getAltitude() - 1);
/*  92 */         this.field.setViewDimensions(this.bee.getAltitude());
/*     */         
/*     */ 
/*  95 */         this.bounceDirection = this.field.outOfField();
/*  96 */         switch (this.bounceDirection) {
/*     */         case 0: 
/*     */           break;
/*     */         case 1: 
/* 100 */           this.bee.bounce(1);
/* 101 */           break;
/*     */         case 2: 
/* 103 */           this.bee.bounce(2);
/* 104 */           break;
/*     */         case 3: 
/* 106 */           this.bee.bounce(3);
/* 107 */           break;
/*     */         case 4: 
/* 109 */           this.bee.bounce(4);
/*     */         }
/*     */         
/*     */         
/*     */ 
/* 114 */         updateInfoLabels();
/* 115 */         updateSliders();
/*     */         
/*     */ 
/* 118 */         this.bee.calcNextPosition();
/*     */         
/*     */ 
/* 121 */         this.speed = this.speedSlider.getValue();
/* 122 */         if (this.speed != 0) {
/* 123 */           pause(this.speed);
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 130 */         this.trialNum += 1;
/*     */         
/* 132 */         this.cycleNum = this.modelParameters.getStartingCycle();
/* 133 */         this.neuronModel.resetModel();
/* 134 */         this.bee.resetLands();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private final JLabel newwbLabel;
/*     */   private final JLabel newwyLabel;
/*     */   private final JLabel learningRateLabel;
/*     */   private final JLabel errorLabel;
/*     */   private final JLabel blueLandsLabel;
/*     */   
/*     */   public final void startSimulation()
/*     */   {
/* 141 */     this.isRunning = true;
/* 142 */     this.startStopButton.setText("Stop Simulator");
/* 143 */     this.progressBar.setValue(0);
/* 144 */     start();
/*     */   }
/*     */   
/*     */   private final JLabel yellowLandsLabel;
/*     */   
/*     */   public final void stopSimulation(String message)
/*     */   {
/* 148 */     this.isRunning = false;
/* 149 */     JOptionPane.showMessageDialog(null, message, "Simulator Stopped", 1, new ImageIcon("images/bee.gif"));
/*     */     
/* 151 */     updateInfoLabels();
/*     */     
/* 153 */     this.saveButton.setEnabled(true);
/* 154 */     this.graphButton.setEnabled(true);
/* 155 */     this.resultsButton.setEnabled(true);
/*     */     
/* 157 */     if (this.resultList.isFull()) {
/* 158 */       this.fieldButton.setEnabled(true);
/* 159 */       this.loadButton.setEnabled(true);
/* 160 */       this.editButton.setEnabled(true);
/* 161 */       this.progressBar.setValue(100);
/*     */     }
/*     */     
/*     */ 
/* 165 */     boolean learningOff = this.modelParameters.getLearning();
/* 166 */     if (learningOff) {
/* 167 */       this.presetButton.setEnabled(false);
/*     */     } else {
/* 169 */       this.presetButton.setEnabled(true);
/*     */     }
/* 171 */     this.startStopButton.setText("Run Simulator");
/* 172 */     this.startStopButton.setIcon(new ImageIcon("images/picstartsimbutton.gif"));
/*     */     
/* 174 */     this.isRunning = false;
/*     */   }
/*     */   
/*     */   private final JLabel trialsLabel;
/*     */   
/*     */   private void saveResult(double reward, String colour, int trial, int cycle)
/*     */   {
/* 178 */     this.resultList.add(reward, colour, this.neuronModel.getWbWeight(), this.neuronModel.getWyWeight(), trial, cycle, this.bee.getBlueLands(), this.bee.getYellowLands());
/*     */   }
/*     */   
/*     */   private final JLabel cyclesLabel;
/*     */   private void showResult()
/*     */   {
/* 182 */     this.messageTextArea.append(this.resultList.getLastResult() + "\n");
/* 183 */     this.messageTextArea.setCaretPosition(this.messageTextArea.getDocument().getLength());
/*     */   }
/*     */   private final JProgressBar progressBar;
/*     */   private int bounceDirection;
/*     */   private int speed;
/*     */   
/*     */   public final boolean getIsRunning()
/*     */   {
/* 188 */     return this.isRunning;
/*     */   }
/*     */   
/*     */   private final DecimalFormat twodec;
/*     */   private String flowerColour;
/*     */   private double flowerReward;
/*     */   
/*     */   private void updateInfoLabels()
/*     */   {
/* 193 */     this.oldwyLabel.setText("Old Yellow Weight : " + this.twodec.format(this.neuronModel.getOldWyWeight()));
/* 194 */     this.oldwbLabel.setText("Old Blue Weight : " + this.twodec.format(this.neuronModel.getOldWbWeight()));
/* 195 */     this.newwyLabel.setText("New Yellow Weight : " + this.twodec.format(this.neuronModel.getWyWeight()));
/* 196 */     this.newwbLabel.setText("New Blue Weight : " + this.twodec.format(this.neuronModel.getWbWeight()));
/* 197 */     this.learningRateLabel.setText("Learning Rate : " + this.twodec.format(this.modelParameters.getLearningRate()));
/* 198 */     if (this.animationCheckBox.isSelected())
/*     */     {
/* 200 */       this.errorLabel.setText("Error Rate : " + this.twodec.format(this.neuronModel.getError()));
/*     */     }
/* 202 */     this.blueLandsLabel.setText("Blue Flower Lands : " + this.bee.getBlueLands());
/* 203 */     this.yellowLandsLabel.setText("Yellow Flower Lands : " + this.bee.getYellowLands());
/* 204 */     this.trialsLabel.setText("Number of Trials : " + this.modelParameters.getTrials());
/* 205 */     this.cyclesLabel.setText("Number of Cycles : " + this.modelParameters.getCycles());
/*     */   }
/*     */   
/*     */   private int trialNum;
/*     */   private int cycleNum;
/*     */   
/*     */   private void updateSliders()
/*     */   {
/* 211 */     double errorf = this.neuronModel.getError() * 200.0D;
/* 212 */     int error = (int)errorf;
/*     */     
/* 214 */     this.errorSlider.setValue(error);
/*     */     
/* 216 */     this.altitudeSlider.setValue(this.bee.getAltitude());
/*     */   }
/*     */   
/*     */   private final int TRIALSTORUN;
/*     */   private final int CYCLESPERTRIAL;
/*     */   
/*     */   private static void pause(int duration)
/*     */   {
/* 220 */     if (duration == 0) {
/* 220 */       duration = 1;
/*     */     }
/*     */     try
/*     */     {
/* 222 */       Thread.sleep(duration);
/*     */     }
/*     */     catch (Exception e) {}
/*     */   }
/*     */   
/*     */   private final JButton startStopButton;
/*     */   private final JButton graphButton;
/*     */   private final JButton saveButton;
/*     */   
/*     */   public SimulatorManager(Field f, NeuronModel nm, Bee b, Object[] sliders, Object[] labels, Object[] buttons, ResultList rList, ModelParameters modelParams, JButton startStop, JTextArea ta, JProgressBar bar, JCheckBox anim)
/*     */   {
/* 230 */     this.isRunning = false;
/* 231 */     this.field = f;
/* 232 */     this.neuronModel = nm;
/* 233 */     this.bee = b;
/* 234 */     this.resultList = rList;
/* 235 */     this.modelParameters = modelParams;
/*     */     
/* 237 */     this.visionSlider = ((JSlider)sliders[0]);
/* 238 */     this.altitudeSlider = ((JSlider)sliders[1]);
/* 239 */     this.errorSlider = ((JSlider)sliders[2]);
/* 240 */     this.speedSlider = ((JSlider)sliders[3]);
/*     */     
/* 242 */     this.oldwyLabel = ((JLabel)labels[0]);
/* 243 */     this.oldwbLabel = ((JLabel)labels[1]);
/* 244 */     this.newwyLabel = ((JLabel)labels[2]);
/* 245 */     this.newwbLabel = ((JLabel)labels[3]);
/* 246 */     this.learningRateLabel = ((JLabel)labels[4]);
/* 247 */     this.errorLabel = ((JLabel)labels[5]);
/* 248 */     this.yellowLandsLabel = ((JLabel)labels[6]);
/* 249 */     this.blueLandsLabel = ((JLabel)labels[7]);
/* 250 */     this.trialsLabel = ((JLabel)labels[8]);
/* 251 */     this.cyclesLabel = ((JLabel)labels[9]);
/*     */     
/* 253 */     this.graphButton = ((JButton)buttons[0]);
/* 254 */     this.saveButton = ((JButton)buttons[1]);
/* 255 */     this.resultsButton = ((JButton)buttons[2]);
/* 256 */     this.fieldButton = ((JButton)buttons[3]);
/* 257 */     this.loadButton = ((JButton)buttons[4]);
/* 258 */     this.editButton = ((JButton)buttons[5]);
/* 259 */     this.presetButton = ((JButton)buttons[6]);
/*     */     
/* 261 */     this.twodec = new DecimalFormat("#0.00");
/*     */     
/* 263 */     this.TRIALSTORUN = this.modelParameters.getTrials();
/*     */     
/* 265 */     this.trialNum = 1;
/* 266 */     this.CYCLESPERTRIAL = this.modelParameters.getCycles();
/*     */     
/* 268 */     this.cycleNum = this.modelParameters.getStartingCycle();
/* 269 */     this.startStopButton = startStop;
/* 270 */     this.messageTextArea = ta;
/* 271 */     this.progressBar = bar;
/* 272 */     this.animationCheckBox = anim;
/*     */   }
/*     */   
/*     */   private final JButton resultsButton;
/*     */   private final JButton fieldButton;
/*     */   
/*     */   private void updateProgressBar()
/*     */   {
/* 277 */     double total = this.modelParameters.getTrials() * this.modelParameters.getCycles();
/* 278 */     double number = (this.trialNum - 1) * this.modelParameters.getCycles() + (this.cycleNum + 1);
/* 279 */     double percent = number / total * 100.0D;
/* 280 */     int percentInt = (int)percent;
/* 281 */     this.progressBar.setValue(percentInt);
/*     */   }
/*     */   
/*     */   private final JButton loadButton;
/*     */   private final JButton editButton;
/*     */   private final JButton presetButton;
/*     */   private final JTextArea messageTextArea;
/*     */   private final JCheckBox animationCheckBox;
/*     */ }


/* Location:              C:\Users\iain\Desktop\Documents\landisk\programming\Iains Work Samples\simulator\project.jar!\SimulatorManager.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */