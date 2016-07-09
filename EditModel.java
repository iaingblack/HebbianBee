/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.util.Hashtable;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JSlider;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.border.BevelBorder;
/*     */ import javax.swing.border.TitledBorder;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
/*     */ 
/*     */ public final class EditModel
/*     */   extends JPanel
/*     */   implements ChangeListener, ActionListener, ItemListener
/*     */ {
/*     */   private JSlider lrSlider;
/*     */   private JSlider altSlider;
/*     */   private JSlider weightMultSlider;
/*     */   private JSlider ratioSlider;
/*     */   private JLabel lrValueLabel;
/*     */   private JLabel lrNameLabel;
/*     */   private JLabel trialLabel;
/*     */   private JLabel cycleLabel;
/*     */   private JLabel weightMultValueLabel;
/*     */   private JLabel weightMultNameLabel;
/*     */   private JLabel altNameLabel;
/*     */   private JLabel altValueLabel;
/*     */   private JLabel learnYellowLabel;
/*     */   private JLabel learnBlueLabel;
/*     */   JLabel oldwyLabel;
/*     */   JLabel oldwbLabel;
/*     */   JLabel newwbLabel;
/*     */   JLabel newwyLabel;
/*     */   JLabel learningRateLabel;
/*     */   JLabel errorLabel;
/*     */   JLabel yellowLandsLabel;
/*     */   JLabel altLabel;
/*     */   JLabel ratioNameLabel;
/*     */   JLabel ratioValueLabel;
/*     */   JLabel mValueLabel;
/*     */   
/*     */   public final void stateChanged(ChangeEvent e)
/*     */   {
/*  28 */     JSlider source = (JSlider)e.getSource();
/*     */     
/*  30 */     if (source == this.lrSlider) {
/*  31 */       this.lrTextField.setText("" + convertLearningRate(source));
/*     */     }
/*     */     
/*  34 */     if (source == this.altSlider) {
/*  35 */       this.altTextField.setText("" + this.altSlider.getValue());
/*     */     }
/*     */     
/*  38 */     if (source == this.ratioSlider) {
/*  39 */       this.ratioTextField.setText("" + this.ratioSlider.getValue());
/*     */     }
/*     */     
/*  42 */     if (source == this.weightMultSlider) {
/*  43 */       this.weightMultTextField.setText("" + this.weightMultSlider.getValue());
/*     */     }
/*     */   }
/*     */   
/*     */   JLabel bValueLabel;
/*     */   JLabel presetsLabel;
/*     */   private JButton okButton;
/*     */   
/*     */   public final void actionPerformed(ActionEvent av)
/*     */   {
/*  49 */     if (av.getSource() == this.okButton)
/*     */     {
/*  51 */       if (checkUserInput()) {
/*  52 */         updateModel();
/*  53 */         close();
/*     */       }
/*     */     }
/*  56 */     if (av.getSource() == this.cancelButton) {
/*  57 */       close();
/*     */     }
/*  59 */     if (av.getSource() == this.resetButton) {
/*  60 */       loadCurrentParameters();
/*     */     }
/*  62 */     if (av.getSource() == this.loadButton) {
/*  63 */       this.load = new EditModelFileHandler(0, this.modelParameters);
/*  64 */       loadCurrentParameters();
/*     */     }
/*  66 */     if ((av.getSource() == this.saveButton) && 
/*  67 */       (checkUserInput())) {
/*  68 */       updateModel();
/*  69 */       this.load = new EditModelFileHandler(1, this.modelParameters);
/*     */     }
/*     */     ProbabilityGraphGUI probGraph;
/*  72 */     if (av.getSource() == this.probabilityButton) {
/*  73 */       probGraph = new ProbabilityGraphGUI(this.modelParameters, this.mValueLabel, this.bValueLabel);
/*     */     }
/*     */   }
/*     */   
/*     */   private JButton cancelButton;
/*     */   private JButton resetButton;
/*     */   private JButton loadButton;
/*     */   
/*     */   public final void itemStateChanged(ItemEvent ie)
/*     */   {
/*  79 */     if (ie.getSource() == this.learningOffCheckBox) {
/*  80 */       setLearning(this.learningOffCheckBox.isSelected());
/*     */     }
/*     */   }
/*     */   
/*     */   private JButton saveButton;
/*     */   private JButton probabilityButton;
/*     */   private JPanel basicPanel;
/*     */   private JPanel lrPanel;
/*     */   private JPanel trialPanel;
/*     */   private JPanel cyclePanel;
/*     */   
/*     */   private void close()
/*     */   {
/*  85 */     this.editFrame.hide();
/*  86 */     this.editFrame.dispose();
/*     */   }
/*     */   
/*     */   private JPanel altPanel;
/*     */   private JPanel ratioPanel;
/*     */   private JPanel weightMultPanel;
/*     */   
/*     */   private void setLearning(boolean set)
/*     */   {
/*  91 */     if (set)
/*     */     {
/*  92 */       this.learnYellowTextField.setEditable(true);
/*  93 */       this.learnBlueTextField.setEditable(true);
/*     */     }
/*     */     else
/*     */     {
/*  97 */       this.learnYellowTextField.setEditable(false);
/*  98 */       this.learnBlueTextField.setEditable(false);
/*     */     }
/*     */   }
/*     */   
/*     */   private JPanel learningPanel;
/*     */   private JPanel buttonPanel;
/*     */   
/*     */   private static float convertLearningRate(JSlider source)
/*     */   {
/* 104 */     float lrValue = source.getValue();
/* 105 */     lrValue /= 10.0F;
/* 106 */     return lrValue;
/*     */   }
/*     */   
/*     */   private JPanel weightDifferencePanel;
/*     */   private JPanel probabilityPanel;
/*     */   
/*     */   private void createFrame()
/*     */   {
/* 111 */     this.editFrame = new JFrame("Edit Model");
/* 112 */     this.editFrame.setIconImage(new ImageIcon("images/beeIcon.gif").getImage());
/* 113 */     this.editFrame.getContentPane().add(this, "Center");
/* 114 */     this.editFrame.setSize(new Dimension(680, 610));
/* 115 */     this.editFrame.setResizable(false);
/*     */     
/* 117 */     setLayout(new FlowLayout());
/*     */     
/* 119 */     Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
/* 120 */     Dimension frameDim = this.editFrame.getSize();
/* 121 */     int x = (screenDim.width - frameDim.width) / 2;
/* 122 */     int y = (screenDim.height - frameDim.height) / 2;
/* 123 */     this.editFrame.setLocation(x, y);
/* 124 */     this.editFrame.setVisible(true);
/* 125 */     this.editFrame.addWindowListener(new WindowAdapter()
/*     */     {
/*     */       public void windowClosing(WindowEvent e)
/*     */       {
/* 128 */         EditModel.this.editFrame = null;
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   private JTextField lrTextField;
/*     */   private JTextField trialTextField;
/*     */   private JTextField cycleTextField;
/*     */   private JTextField altTextField;
/*     */   private JTextField ratioTextField;
/*     */   private JTextField weightMultTextField;
/*     */   private JTextField learnYellowTextField;
/*     */   
/*     */   public EditModel(ModelParameters modelParams, ResultList rl, JLabel lr, JLabel tr, JLabel cy, JButton pb, JLabel prLabel)
/*     */   {
/* 142 */     super(true);
/* 143 */     this.modelParameters = modelParams;
/* 144 */     this.resultList = rl;
/* 145 */     this.learningRateGUILabel = lr;
/* 146 */     this.trialsLabel = tr;
/* 147 */     this.cyclesLabel = cy;
/* 148 */     createGUI();
/* 149 */     createFrame();
/* 150 */     loadCurrentParameters();
/* 151 */     setBorder(new BevelBorder(0));
/* 152 */     this.presetButton = pb;
/* 153 */     this.presetsLabel = prLabel;
/*     */   }
/*     */   
/*     */   private JTextField learnBlueTextField;
/*     */   private JFrame editFrame;
/*     */   private JCheckBox learningOffCheckBox;
/*     */   
/*     */   private void createGUI()
/*     */   {
/* 160 */     this.basicPanel = new JPanel();
/* 161 */     this.basicPanel.setBorder(new TitledBorder("Basic Parameters"));
/* 162 */     this.basicPanel.setLayout(new BoxLayout(this.basicPanel, 1));
/* 163 */     this.basicPanel.setPreferredSize(new Dimension(580, 275));
/* 164 */     this.lrPanel = new JPanel(new FlowLayout());
/* 165 */     this.trialPanel = new JPanel(new FlowLayout());
/* 166 */     this.cyclePanel = new JPanel(new FlowLayout());
/* 167 */     this.altPanel = new JPanel(new FlowLayout());
/* 168 */     this.ratioPanel = new JPanel(new FlowLayout());
/* 169 */     this.weightMultPanel = new JPanel(new FlowLayout());
/* 170 */     this.weightMultPanel.setBorder(new TitledBorder("Weight Multiplier"));
/* 171 */     this.weightMultPanel.setPreferredSize(new Dimension(580, 80));
/* 172 */     this.learningPanel = new JPanel(new FlowLayout());
/* 173 */     this.learningPanel.setBorder(new TitledBorder("Learning Off?"));
/* 174 */     this.learningPanel.setPreferredSize(new Dimension(580, 70));
/* 175 */     this.weightDifferencePanel = new JPanel(new FlowLayout(0, 55, 5));
/* 176 */     this.weightDifferencePanel.setBorder(new TitledBorder("Use The Weight Differences In Simulator?"));
/* 177 */     this.weightDifferencePanel.setPreferredSize(new Dimension(580, 70));
/* 178 */     this.probabilityPanel = new JPanel(new FlowLayout(0, 55, 5));
/* 179 */     this.probabilityPanel.setBorder(new TitledBorder("Probability Graph"));
/* 180 */     this.probabilityButton = new JButton("Bee Swerving", new ImageIcon("images/picprobbutton.gif"));
/* 181 */     this.probabilityButton.setMnemonic('B');
/* 182 */     this.probabilityButton.setToolTipText("Edit the Chance of The Bee Swerving");
/* 183 */     this.probabilityPanel.add(this.probabilityButton);
/* 184 */     this.mValueLabel = new JLabel("m = " + this.modelParameters.getmValue());
/* 185 */     this.bValueLabel = new JLabel("b = " + this.modelParameters.getbValue() / 10.0D);
/* 186 */     this.probabilityPanel.add(this.mValueLabel);
/* 187 */     this.probabilityPanel.add(this.bValueLabel);
/* 188 */     this.probabilityPanel.setPreferredSize(new Dimension(580, 70));
/* 189 */     this.buttonPanel = new JPanel(new FlowLayout());
/*     */     
/*     */ 
/*     */ 
/* 193 */     this.lrNameLabel = new JLabel("Learning Rate");
/* 194 */     this.lrValueLabel = new JLabel("Value");
/* 195 */     this.lrSlider = new JSlider(0, 0, 10, 9);
/* 196 */     this.lrSlider.addChangeListener(this);
/* 197 */     this.lrSlider.setMinorTickSpacing(1);
/* 198 */     this.lrSlider.setMajorTickSpacing(2);
/* 199 */     this.lrSlider.setPaintTicks(true);
/* 200 */     this.lrSlider.setPaintLabels(true);
/* 201 */     this.lrSlider.putClientProperty("JSlider.isFilled", Boolean.TRUE);
/*     */     
/* 203 */     Hashtable labelTable = new Hashtable();
/* 204 */     labelTable.put(new Integer(this.lrSlider.getMinimum()), new JLabel("0"));
/* 205 */     labelTable.put(new Integer(this.lrSlider.getMinimum() + 2), new JLabel("0.2"));
/* 206 */     labelTable.put(new Integer(this.lrSlider.getMinimum() + 4), new JLabel("0.4"));
/* 207 */     labelTable.put(new Integer(this.lrSlider.getMinimum() + 6), new JLabel("0.6"));
/* 208 */     labelTable.put(new Integer(this.lrSlider.getMinimum() + 8), new JLabel("0.8"));
/* 209 */     labelTable.put(new Integer(this.lrSlider.getMinimum() + 10), new JLabel("1"));
/* 210 */     this.lrSlider.setLabelTable(labelTable);
/* 211 */     this.lrTextField = new JTextField(5);
/* 212 */     this.lrTextField.setText("" + convertLearningRate(this.lrSlider));
/* 213 */     this.lrTextField.setEditable(false);
/* 214 */     this.lrPanel.add(this.lrNameLabel);
/* 215 */     this.lrPanel.add(this.lrSlider);
/* 216 */     this.lrPanel.add(this.lrValueLabel);
/* 217 */     this.lrPanel.add(this.lrTextField);
/*     */     
/* 219 */     this.trialLabel = new JLabel("Number of Trials to Run");
/* 220 */     this.trialTextField = new JTextField(5);
/* 221 */     this.cycleLabel = new JLabel("Number of Cycles Per Trial");
/* 222 */     this.cycleTextField = new JTextField(5);
/* 223 */     this.trialPanel.add(this.trialLabel);
/* 224 */     this.trialPanel.add(this.trialTextField);
/* 225 */     this.cyclePanel.add(this.cycleLabel);
/* 226 */     this.cyclePanel.add(this.cycleTextField);
/*     */     
/* 228 */     this.altNameLabel = new JLabel("Initial Bee Height");
/* 229 */     this.altValueLabel = new JLabel("Value");
/* 230 */     this.altSlider = new JSlider(0, 20, 100, 100);
/* 231 */     this.altSlider.addChangeListener(this);
/* 232 */     this.altSlider.setMinorTickSpacing(5);
/* 233 */     this.altSlider.setMajorTickSpacing(10);
/* 234 */     this.altSlider.setPaintTicks(true);
/* 235 */     this.altSlider.setPaintLabels(true);
/* 236 */     this.altSlider.putClientProperty("JSlider.isFilled", Boolean.TRUE);
/* 237 */     this.altTextField = new JTextField(5);
/* 238 */     this.altTextField.setText("" + this.altSlider.getValue());
/* 239 */     this.altTextField.setEditable(false);
/* 240 */     this.altPanel.add(this.altNameLabel);
/* 241 */     this.altPanel.add(this.altSlider);
/* 242 */     this.altPanel.add(this.altValueLabel);
/* 243 */     this.altPanel.add(this.altTextField);
/*     */     
/* 245 */     this.ratioNameLabel = new JLabel("Rewarding Yellows");
/* 246 */     this.ratioValueLabel = new JLabel("1 in ");
/* 247 */     this.ratioSlider = new JSlider(0, 0, 10, 3);
/* 248 */     this.ratioSlider.addChangeListener(this);
/* 249 */     this.ratioSlider.setMinorTickSpacing(1);
/* 250 */     this.ratioSlider.setMajorTickSpacing(2);
/* 251 */     this.ratioSlider.setPaintTicks(true);
/* 252 */     this.ratioSlider.setPaintLabels(true);
/* 253 */     this.ratioSlider.putClientProperty("JSlider.isFilled", Boolean.TRUE);
/* 254 */     this.ratioTextField = new JTextField(5);
/* 255 */     this.ratioTextField.setText("" + this.ratioSlider.getValue());
/* 256 */     this.ratioTextField.setEditable(false);
/* 257 */     this.ratioPanel.add(this.ratioNameLabel);
/* 258 */     this.ratioPanel.add(this.ratioSlider);
/* 259 */     this.ratioPanel.add(this.ratioValueLabel);
/* 260 */     this.ratioPanel.add(this.ratioTextField);
/*     */     
/* 262 */     this.weightMultNameLabel = new JLabel("Weight Multiplier Value");
/* 263 */     this.weightMultValueLabel = new JLabel("Value");
/* 264 */     this.weightMultSlider = new JSlider(0, 1, 10, 5);
/* 265 */     this.weightMultSlider.addChangeListener(this);
/* 266 */     this.weightMultSlider.setMinorTickSpacing(1);
/* 267 */     this.weightMultSlider.setMajorTickSpacing(9);
/* 268 */     this.weightMultSlider.setPaintTicks(true);
/* 269 */     this.weightMultSlider.setPaintLabels(true);
/* 270 */     this.weightMultSlider.putClientProperty("JSlider.isFilled", Boolean.TRUE);
/* 271 */     this.weightMultTextField = new JTextField(5);
/* 272 */     this.weightMultTextField.setText("" + this.weightMultSlider.getValue());
/* 273 */     this.weightMultTextField.setEditable(false);
/* 274 */     this.weightMultPanel.add(this.weightMultNameLabel);
/* 275 */     this.weightMultPanel.add(this.weightMultSlider);
/* 276 */     this.weightMultPanel.add(this.weightMultValueLabel);
/* 277 */     this.weightMultPanel.add(this.weightMultTextField);
/*     */     
/* 279 */     this.learningOffCheckBox = new JCheckBox("Fix Weights?");
/* 280 */     this.learningOffCheckBox.setMnemonic('F');
/* 281 */     this.learningOffCheckBox.addItemListener(this);
/*     */     
/* 283 */     this.learningPanel.add(this.learningOffCheckBox);
/* 284 */     this.learnYellowLabel = new JLabel("Set Yellow Weight At ");
/* 285 */     this.learnYellowTextField = new JTextField(5);
/* 286 */     this.learnYellowTextField.setEditable(false);
/* 287 */     this.learnYellowTextField.setText("0.0");
/* 288 */     this.learnBlueLabel = new JLabel("Set Blue Weight At ");
/* 289 */     this.learnBlueTextField = new JTextField(5);
/* 290 */     this.learnBlueTextField.setEditable(false);
/* 291 */     this.learnBlueTextField.setText("0.0");
/* 292 */     this.learningPanel.add(this.learnYellowLabel);
/* 293 */     this.learningPanel.add(this.learnYellowTextField);
/* 294 */     this.learningPanel.add(this.learnBlueLabel);
/* 295 */     this.learningPanel.add(this.learnBlueTextField);
/*     */     
/* 297 */     this.weightDifferenceCheckBox = new JCheckBox("Use Weight Differences?");
/* 298 */     this.weightDifferenceCheckBox.setMnemonic('W');
/* 299 */     this.weightDifferenceCheckBox.addItemListener(this);
/* 300 */     this.weightDifferencePanel.add(this.weightDifferenceCheckBox, "West");
/*     */     
/* 302 */     this.okButton = new JButton("OK", new ImageIcon("images/picstartbutton.gif"));
/* 303 */     this.okButton.setMnemonic('O');
/* 304 */     this.okButton.setToolTipText("Accept These Current Parameters");
/* 305 */     this.cancelButton = new JButton("Cancel", new ImageIcon("images/picstopbutton.gif"));
/* 306 */     this.cancelButton.setMnemonic('C');
/* 307 */     this.cancelButton.setToolTipText("Cancel Changes To Model Parameters");
/* 308 */     this.resetButton = new JButton("Reset", new ImageIcon("images/picslideriterations.gif"));
/* 309 */     this.resetButton.setMnemonic('R');
/* 310 */     this.resetButton.setToolTipText("Reset Parameters To Their Original Values");
/* 311 */     this.loadButton = new JButton("Load", new ImageIcon("images/picloadbutton.gif"));
/* 312 */     this.loadButton.setMnemonic('L');
/* 313 */     this.loadButton.setToolTipText("Load A Previously Saved Set Of Parameters");
/* 314 */     this.saveButton = new JButton("Save", new ImageIcon("images/picsavebutton.gif"));
/* 315 */     this.saveButton.setMnemonic('S');
/* 316 */     this.saveButton.setToolTipText("Save The Current Set Of Parameters");
/* 317 */     this.buttonPanel.add(this.okButton);
/* 318 */     this.buttonPanel.add(this.cancelButton);
/* 319 */     this.buttonPanel.add(this.resetButton);
/* 320 */     this.buttonPanel.add(this.loadButton);
/* 321 */     this.buttonPanel.add(this.saveButton);
/*     */     
/* 323 */     this.okButton.addActionListener(this);
/* 324 */     this.cancelButton.addActionListener(this);
/* 325 */     this.resetButton.addActionListener(this);
/* 326 */     this.loadButton.addActionListener(this);
/* 327 */     this.saveButton.addActionListener(this);
/* 328 */     this.probabilityButton.addActionListener(this);
/*     */     
/*     */ 
/* 331 */     this.basicPanel.add(this.lrPanel);
/* 332 */     this.basicPanel.add(this.trialPanel);
/* 333 */     this.basicPanel.add(this.cyclePanel);
/* 334 */     this.basicPanel.add(this.altPanel);
/* 335 */     this.basicPanel.add(this.ratioPanel);
/*     */     
/* 337 */     add(this.basicPanel);
/* 338 */     add(this.weightMultPanel);
/*     */     
/* 340 */     add(this.learningPanel);
/* 341 */     add(this.probabilityPanel);
/* 342 */     add(this.buttonPanel);
/*     */   }
/*     */   
/*     */   private JCheckBox weightDifferenceCheckBox;
/*     */   private double learningRate;
/*     */   
/*     */   private void loadCurrentParameters()
/*     */   {
/* 347 */     this.lrTextField.setText("" + this.modelParameters.getLearningRate());
/* 348 */     this.lrSlider.setValue((int)(this.modelParameters.getLearningRate() * 10.0D));
/* 349 */     this.trialTextField.setText("" + this.modelParameters.getTrials());
/* 350 */     this.cycleTextField.setText("" + this.modelParameters.getCycles());
/* 351 */     this.altTextField.setText("" + this.modelParameters.getAltHeight());
/* 352 */     this.ratioSlider.setValue(this.modelParameters.getYellowRatio());
/* 353 */     this.ratioTextField.setText("" + this.modelParameters.getYellowRatio());
/* 354 */     this.altSlider.setValue(this.modelParameters.getAltHeight());
/* 355 */     this.weightMultTextField.setText("" + this.modelParameters.getWeightMultiplier());
/* 356 */     this.weightMultSlider.setValue(this.modelParameters.getWeightMultiplier());
/* 357 */     this.learningOffCheckBox.setSelected(this.modelParameters.getLearning());
/* 358 */     this.learnYellowTextField.setText("" + this.modelParameters.getYellowWeight());
/* 359 */     this.learnBlueTextField.setText("" + this.modelParameters.getBlueWeight());
/*     */     
/* 361 */     this.mValueLabel.setText("m = " + this.modelParameters.getmValue());
/* 362 */     this.bValueLabel.setText("b = " + this.modelParameters.getbValue());
/*     */   }
/*     */   
/*     */   private double yellowWeight;
/*     */   private double blueWeight;
/*     */   
/*     */   private boolean checkUserInput()
/*     */   {
/* 367 */     boolean isOk = true;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     try
/*     */     {
/* 375 */       int trialInput = Integer.parseInt(this.trialTextField.getText());
/* 376 */       int cycleInput = Integer.parseInt(this.cycleTextField.getText());
/* 377 */       double yellowWeight = Double.parseDouble(this.learnYellowTextField.getText());
/* 378 */       blueWeight = Double.parseDouble(this.learnBlueTextField.getText());
/*     */     } catch (NumberFormatException exc) { double blueWeight;
/* 380 */       JOptionPane.showMessageDialog(null, "Error " + exc.getMessage(), "Please Use Numbers Only", 2);
/* 381 */       isOk = false;
/*     */     }
/* 383 */     return isOk;
/*     */   }
/*     */   
/*     */   private boolean learningOff;
/*     */   private int trials;
/*     */   private int cycles;
/*     */   
/*     */   private void updateModel()
/*     */   {
/* 390 */     this.learningRate = Double.parseDouble(this.lrTextField.getText());
/* 391 */     this.modelParameters.setLearningRate(this.learningRate);
/*     */     
/*     */ 
/*     */ 
/* 395 */     this.trials = Integer.parseInt(this.trialTextField.getText());
/* 396 */     this.modelParameters.setTrials(this.trials);
/*     */     
/*     */ 
/* 399 */     this.cycles = Integer.parseInt(this.cycleTextField.getText());
/* 400 */     this.modelParameters.setCycles(this.cycles);
/* 401 */     this.resultList.clear(this.trials, this.cycles);
/*     */     
/*     */ 
/* 404 */     this.altInt = Integer.parseInt(this.altTextField.getText());
/* 405 */     this.modelParameters.setAltHeight(this.altInt);
/*     */     
/*     */ 
/* 408 */     this.yellowRatio = Integer.parseInt(this.ratioTextField.getText());
/* 409 */     this.modelParameters.setYellowRatio(this.yellowRatio);
/*     */     
/*     */ 
/* 412 */     this.weightMultInt = Integer.parseInt(this.weightMultTextField.getText());
/* 413 */     this.modelParameters.setWeightMultiplier(this.weightMultInt);
/*     */     
/*     */ 
/* 416 */     this.learningOff = this.learningOffCheckBox.isSelected();
/* 417 */     if (this.learningOff) {
/* 418 */       this.presetButton.setEnabled(false);
/*     */     } else
/* 420 */       this.presetButton.setEnabled(true);
/* 421 */     this.modelParameters.setLearning(this.learningOff);
/*     */     
/* 423 */     this.yellowWeight = Double.parseDouble(this.learnYellowTextField.getText());
/* 424 */     this.modelParameters.setYellowWeight(this.yellowWeight);
/* 425 */     this.blueWeight = Double.parseDouble(this.learnBlueTextField.getText());
/* 426 */     this.modelParameters.setBlueWeight(this.blueWeight);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 431 */     this.modelParameters.setStartingCycle(0);
/* 432 */     this.modelParameters.setInitialYellowLands(0);
/* 433 */     this.modelParameters.setInitialBlueLands(0);
/* 434 */     this.presetsLabel.setText("Presets : No");
/*     */     
/*     */ 
/* 437 */     this.learningRateGUILabel.setText("Learning Rate : " + this.learningRate);
/* 438 */     this.trialsLabel.setText("Number of Trials : " + this.trials);
/* 439 */     this.cyclesLabel.setText("Number of Cycles : " + this.cycles);
/*     */   }
/*     */   
/*     */   private int altInt;
/*     */   private int yellowRatio;
/*     */   private int weightMultInt;
/*     */   private int[][] probabilityValue;
/*     */   private final ModelParameters modelParameters;
/*     */   EditModelFileHandler load;
/*     */   private ResultList resultList;
/*     */   private JLabel learningRateGUILabel;
/*     */   private JLabel trialsLabel;
/*     */   private JLabel cyclesLabel;
/*     */   private JButton presetButton;
/*     */ }


/* Location:              C:\Users\iain\Desktop\Documents\landisk\programming\Iains Work Samples\simulator\project.jar!\EditModel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */