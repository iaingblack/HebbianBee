/*     */ import javax.swing.JButton;
/*     */ 
/*     */ public final class SimulatorGUI extends javax.swing.JPanel implements javax.swing.event.ChangeListener, java.awt.event.ActionListener, java.awt.event.ItemListener { private javax.swing.JSlider visionSlider;
/*     */   private javax.swing.JSlider altitudeSlider;
/*     */   private javax.swing.JSlider errorSlider;
/*     */   private javax.swing.JSlider speedSlider;
/*     */   private javax.swing.JLabel yellowValueLabel;
/*     */   private javax.swing.JLabel blueValueLabel;
/*     */   private javax.swing.JLabel oldwyLabel;
/*     */   private javax.swing.JLabel oldwbLabel;
/*     */   private javax.swing.JLabel newwbLabel;
/*     */   private javax.swing.JLabel newwyLabel;
/*     */   private javax.swing.JLabel learningRateLabel;
/*     */   private javax.swing.JLabel errorLabel;
/*     */   private javax.swing.JLabel yellowLandsLabel;
/*     */   private javax.swing.JLabel blueLandsLabel;
/*     */   
/*  18 */   public final void stateChanged(javax.swing.event.ChangeEvent e) { javax.swing.JSlider source = (javax.swing.JSlider)e.getSource();
/*     */     
/*  20 */     if ((!source.getValueIsAdjusting()) && (source == this.visionSlider)) {
/*  21 */       int sliderValue = source.getValue();
/*  22 */       this.blueValueLabel.setText("Blue " + (50 + sliderValue) + "%");
/*  23 */       this.yellowValueLabel.setText("Yellow " + (50 - sliderValue) + "%");
/*     */     }
/*     */     
/*  26 */     if ((!source.getValueIsAdjusting()) && (source == this.altitudeSlider)) {
/*  27 */       this.field.setViewDimensions(source.getValue());
/*     */     }
/*     */     
/*  30 */     if ((!source.getValueIsAdjusting()) && (source == this.errorSlider)) {}
/*     */   }
/*     */   
/*     */ 
/*     */   public final void itemStateChanged(java.awt.event.ItemEvent ie)
/*     */   {
/*  36 */     if (ie.getSource() == this.animationCheckBox) {
/*  37 */       turnAnimationOn(this.animationCheckBox.isSelected());
/*     */     }
/*     */   }
/*     */   
/*     */   public final void actionPerformed(java.awt.event.ActionEvent av)
/*     */   {
/*  43 */     if (av.getSource() == this.fieldButton) {
/*  44 */       UIColor.setUIColor(this.frameYellow);
/*  45 */       createFieldGenerator();
/*     */     }
/*  47 */     if (av.getSource() == this.loadButton) {
/*  48 */       UIColor.setUIColor(this.frameBlue);
/*  49 */       loadResults();
/*     */     }
/*  51 */     if (av.getSource() == this.saveButton) {
/*  52 */       UIColor.setUIColor(this.frameBlue);
/*  53 */       saveResults();
/*     */     }
/*  55 */     if (av.getSource() == this.quitButton) {
/*  56 */       quitSimulator();
/*     */     }
/*  58 */     if (av.getSource() == this.startStopButton) {
/*  59 */       controlSimulator();
/*     */     }
/*  61 */     if (av.getSource() == this.resultsButton) {
/*  62 */       UIColor.setUIColor(this.frameBlue);
/*  63 */       createResultsTable();
/*     */     }
/*  65 */     if (av.getSource() == this.graphButton) {
/*  66 */       UIColor.setUIColor(this.frameBlue);
/*  67 */       createGrapher();
/*     */     }
/*  69 */     if (av.getSource() == this.editButton) {
/*  70 */       UIColor.setUIColor(this.frameYellow);
/*  71 */       createEditModel(); }
/*     */     PresetLandsGUI siwg;
/*  73 */     if (av.getSource() == this.presetButton) {
/*  74 */       UIColor.setUIColor(this.frameYellow);
/*  75 */       siwg = new PresetLandsGUI(this.modelParameters, this.resultList, this.neuronModel, this.presetsLabel);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void createSimulator()
/*     */   {
/*  84 */     this.learningRate = this.modelParameters.getLearningRate();
/*  85 */     this.trials = this.modelParameters.getTrials();
/*  86 */     this.cycles = this.modelParameters.getCycles();
/*     */     
/*     */ 
/*  89 */     Object[] sliders = new Object[4];
/*  90 */     sliders[0] = this.visionSlider;
/*  91 */     sliders[1] = this.altitudeSlider;
/*  92 */     sliders[2] = this.errorSlider;
/*  93 */     sliders[3] = this.speedSlider;
/*     */     
/*  95 */     Object[] labels = new Object[10];
/*  96 */     labels[0] = this.oldwyLabel;
/*  97 */     labels[1] = this.oldwbLabel;
/*  98 */     labels[2] = this.newwyLabel;
/*  99 */     labels[3] = this.newwbLabel;
/* 100 */     labels[4] = this.learningRateLabel;
/* 101 */     labels[5] = this.errorLabel;
/* 102 */     labels[6] = this.yellowLandsLabel;
/* 103 */     labels[7] = this.blueLandsLabel;
/* 104 */     labels[8] = this.trialsLabel;
/* 105 */     labels[9] = this.cyclesLabel;
/*     */     
/* 107 */     Object[] buttons = new Object[7];
/* 108 */     buttons[0] = this.graphButton;
/* 109 */     buttons[1] = this.saveButton;
/* 110 */     buttons[2] = this.resultsButton;
/* 111 */     buttons[3] = this.fieldButton;
/* 112 */     buttons[4] = this.loadButton;
/* 113 */     buttons[5] = this.editButton;
/* 114 */     buttons[6] = this.presetButton;
/* 115 */     this.simulatorManager = new SimulatorManager(this.field, this.neuronModel, this.bee, sliders, labels, buttons, this.resultList, this.modelParameters, this.startStopButton, this.messageTextArea, this.progressBar, this.animationCheckBox);
/*     */   }
/*     */   
/*     */   private void createFieldGenerator() {
/* 119 */     this.fg = new FieldGeneratorGUI(this.field, this.fieldPanel);
/*     */   }
/*     */   
/*     */   private void quitSimulator() {
/* 123 */     this.frame.dispose();
/* 124 */     System.exit(0);
/*     */   }
/*     */   
/*     */   private void createEditModel() {
/* 128 */     this.editModel = new EditModel(this.modelParameters, this.resultList, this.learningRateLabel, this.trialsLabel, this.cyclesLabel, this.presetButton, this.presetsLabel);
/* 129 */     updateGUI();
/*     */   }
/*     */   
/*     */   private void createResultsTable() {
/* 133 */     if (!this.resultList.isEmpty()) {
/* 134 */       this.resultsGUI = new ViewResultsGUI(this.resultList);
/*     */     }
/*     */     else
/*     */     {
/* 138 */       this.messageTextArea.setText("No Results Yet. Run a Simulation First.");
/*     */     }
/*     */   }
/*     */   
/*     */   private void createGrapher() {
/* 143 */     if (!this.resultList.isEmpty()) {
/* 144 */       this.graphGUI = new GraphGUI(this.resultList);
/* 145 */       addComponentListener(this.graphGUI);
/*     */     }
/*     */     else
/*     */     {
/* 149 */       this.messageTextArea.setText("No Results Yet. Run a Simulation First.");
/*     */     }
/*     */   }
/*     */   
/*     */   private void controlSimulator() {
/* 154 */     if (this.startStopButton.getText() == "Run Simulator")
/*     */     {
/*     */ 
/* 157 */       createSimulator();
/* 158 */       this.messageTextArea.setText("");
/*     */       
/* 160 */       this.simulatorManager.startSimulation();
/* 161 */       this.startStopButton.setText("Stop Simulator");
/* 162 */       this.startStopButton.setIcon(new javax.swing.ImageIcon("images/picstopsimbutton.gif"));
/*     */       
/* 164 */       this.saveButton.setEnabled(false);
/* 165 */       this.graphButton.setEnabled(false);
/* 166 */       this.resultsButton.setEnabled(false);
/* 167 */       this.fieldButton.setEnabled(false);
/* 168 */       this.loadButton.setEnabled(false);
/* 169 */       this.editButton.setEnabled(false);
/* 170 */       this.presetButton.setEnabled(false);
/*     */     } else {
/* 172 */       this.simulatorManager.stopSimulation("Simulator Stopped - Please Complete Trials For Analysis Options");
/* 173 */       this.messageTextArea.append(" Simulation Stopped");
/* 174 */       this.startStopButton.setText("Run Simulator");
/* 175 */       this.startStopButton.setIcon(new javax.swing.ImageIcon("images/picstartsimbutton.gif"));
/*     */       
/* 177 */       this.saveButton.setEnabled(false);
/* 178 */       this.graphButton.setEnabled(false);
/* 179 */       this.resultsButton.setEnabled(false);
/* 180 */       this.fieldButton.setEnabled(true);
/* 181 */       this.loadButton.setEnabled(true);
/* 182 */       this.editButton.setEnabled(true);
/*     */       
/*     */ 
/* 185 */       boolean learningOff = this.modelParameters.getLearning();
/* 186 */       if (learningOff) {
/* 187 */         this.presetButton.setEnabled(false);
/*     */       } else
/* 189 */         this.presetButton.setEnabled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   private void loadResults() {
/* 194 */     this.rlfh = new ResultListFileHandler(this.resultList);
/* 195 */     String data = this.rlfh.load();
/* 196 */     if (data != "") {
/* 197 */       boolean loadOK = this.resultList.loadResults(data);
/* 198 */       if (loadOK) {
/* 199 */         this.saveButton.setEnabled(true);
/* 200 */         this.graphButton.setEnabled(true);
/* 201 */         this.resultsButton.setEnabled(true);
/*     */       }
/*     */     }
/*     */     
/* 205 */     System.out.println("Result " + this.resultList.getArrayResult(0, 0));
/*     */   }
/*     */   
/*     */   private void saveResults() {
/* 209 */     this.rlfh = new ResultListFileHandler(this.resultList);
/* 210 */     this.rlfh.save();
/*     */   }
/*     */   
/*     */   private void turnAnimationOn(boolean set) {
/* 214 */     this.field.setAnimation(set);
/* 215 */     if (set) {
/* 216 */       this.visionSlider.setVisible(true);
/* 217 */       this.errorSlider.setVisible(true);
/*     */     } else {
/* 219 */       this.visionSlider.setVisible(false);
/* 220 */       this.errorSlider.setVisible(false);
/*     */     }
/*     */   }
/*     */   
/*     */   public SimulatorGUI(java.awt.Image fi, java.awt.Image bi)
/*     */   {
/* 226 */     super(true);
/*     */     
/* 228 */     createFieldSlider();
/* 229 */     createFieldBeeNeuron(fi, bi);
/* 230 */     buildGUI();
/* 231 */     createFrame();
/*     */   }
/*     */   
/*     */   private void createFrame()
/*     */   {
/* 236 */     this.frame = new javax.swing.JFrame("Hebbian Bee Simulator");
/* 237 */     this.frame.setIconImage(new javax.swing.ImageIcon("images/beeIcon.gif").getImage());
/*     */     
/*     */ 
/* 240 */     this.frame.getContentPane().add(this, "Center");
/*     */     
/* 242 */     this.frame.setSize(new java.awt.Dimension(850, 700));
/*     */     
/* 244 */     java.awt.Dimension screenDim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
/* 245 */     java.awt.Dimension frameDim = this.frame.getSize();
/* 246 */     int x = (screenDim.width - frameDim.width) / 2;
/* 247 */     int y = (screenDim.height - frameDim.height) / 2;
/* 248 */     this.frame.setLocation(x, y);
/*     */     
/* 250 */     this.frame.setVisible(true);
/*     */     
/* 252 */     this.frame.addWindowListener(new java.awt.event.WindowAdapter() {
/*     */       public void windowClosing(java.awt.event.WindowEvent e) {
/* 254 */         System.exit(0);
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   private void buildGUI()
/*     */   {
/* 261 */     createLayout();
/* 262 */     createSliders();
/* 263 */     createLabels();
/* 264 */     createButtons();
/* 265 */     createInfoBox();
/* 266 */     addAll();
/*     */   }
/*     */   
/*     */   private void createLayout() {
/* 270 */     java.awt.BorderLayout layout = new java.awt.BorderLayout();
/* 271 */     setLayout(layout);
/* 272 */     this.fieldPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
/* 273 */     this.fieldScrollPane = new javax.swing.JScrollPane(this.fieldPanel);
/* 274 */     this.fieldScrollPane.setBorder(new javax.swing.border.TitledBorder("Field"));
/* 275 */     this.sliderPanel = new javax.swing.JPanel(new java.awt.FlowLayout());
/* 276 */     this.sliderPanel.setBorder(new javax.swing.border.TitledBorder("Information Sliders"));
/* 277 */     this.speedPanel = new javax.swing.JPanel(new java.awt.FlowLayout());
/* 278 */     this.speedPanel.setBorder(new javax.swing.border.TitledBorder("Simulation Speed"));
/* 279 */     this.buttonPanel = new javax.swing.JPanel(new java.awt.GridLayout(3, 1, 4, 4));
/* 280 */     this.labelPanel = new javax.swing.JPanel(new java.awt.GridLayout(11, 1));
/* 281 */     this.labelPanel.setBorder(new javax.swing.border.TitledBorder("Simulator Information"));
/*     */     
/* 283 */     createMessageBox();
/* 284 */     this.messageScrollPane = new javax.swing.JScrollPane(this.messageTextArea, 22, 32);
/*     */     
/*     */ 
/* 287 */     this.messageScrollPane.setBorder(new javax.swing.border.TitledBorder("Messages"));
/*     */     
/* 289 */     javax.swing.JPanel fieldButtonPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
/* 290 */     fieldButtonPanel.add(this.fieldScrollPane, "Center");
/* 291 */     this.fieldPanel.setBorder(new javax.swing.border.BevelBorder(1));
/* 292 */     fieldButtonPanel.add(this.buttonPanel, "South");
/*     */     
/*     */ 
/*     */ 
/* 296 */     javax.swing.JPanel sliderInfoPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
/* 297 */     sliderInfoPanel.add(this.sliderPanel, "North");
/* 298 */     sliderInfoPanel.add(this.speedPanel, "South");
/*     */     
/* 300 */     sliderInfoPanel.add(this.labelPanel, "Center");
/*     */     
/*     */ 
/*     */ 
/* 304 */     add(fieldButtonPanel, "Center");
/* 305 */     add(sliderInfoPanel, "East");
/* 306 */     add(this.messageScrollPane, "South");
/*     */   }
/*     */   
/*     */   private void createSliders()
/*     */   {
/* 311 */     this.altitudeSlider = new javax.swing.JSlider(1, 0, 100, 100);
/* 312 */     this.altitudeSlider.addChangeListener(this);
/* 313 */     this.altitudeSlider.setMinorTickSpacing(10);
/* 314 */     this.altitudeSlider.setMajorTickSpacing(20);
/* 315 */     this.altitudeSlider.setPaintTicks(true);
/* 316 */     this.altitudeSlider.setPaintLabels(true);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 321 */     this.errorSlider = new javax.swing.JSlider(1, -30, 30, 0);
/* 322 */     this.errorSlider.setEnabled(false);
/* 323 */     this.errorSlider.addChangeListener(this);
/* 324 */     this.errorSlider.setMinorTickSpacing(5);
/* 325 */     this.errorSlider.setMajorTickSpacing(10);
/* 326 */     this.errorSlider.setPaintTicks(true);
/* 327 */     this.errorSlider.setPaintLabels(true);
/* 328 */     java.util.Hashtable errorLabelTable = new java.util.Hashtable();
/* 329 */     errorLabelTable.put(new Integer(this.errorSlider.getMinimum() + 2), new javax.swing.JLabel("Worse"));
/* 330 */     errorLabelTable.put(new Integer(0), new javax.swing.JLabel("Expected "));
/* 331 */     errorLabelTable.put(new Integer(this.errorSlider.getMaximum() - 2), new javax.swing.JLabel("Better"));
/* 332 */     this.errorSlider.setLabelTable(errorLabelTable);
/*     */     
/* 334 */     this.speedSlider = new javax.swing.JSlider(0, 0, 250, 30);
/* 335 */     this.speedSlider.addChangeListener(this);
/* 336 */     this.speedSlider.setMinorTickSpacing(11);
/* 337 */     this.speedSlider.setMajorTickSpacing(33);
/* 338 */     this.speedSlider.setPaintTicks(true);
/* 339 */     this.speedSlider.setPaintLabels(true);
/*     */     
/* 341 */     java.util.Hashtable labelTable = new java.util.Hashtable();
/* 342 */     labelTable.put(new Integer(this.speedSlider.getMinimum() + 5), new javax.swing.JLabel("Fast"));
/* 343 */     labelTable.put(new Integer((this.speedSlider.getMinimum() + this.speedSlider.getMaximum()) / 2), new javax.swing.JLabel("SPEED"));
/* 344 */     labelTable.put(new Integer(this.speedSlider.getMaximum() - 5), new javax.swing.JLabel("Slow"));
/* 345 */     this.speedSlider.setLabelTable(labelTable);
/*     */   }
/*     */   
/*     */   private void createLabels() {
/* 349 */     this.yellowValueLabel = new javax.swing.JLabel("Yellow 50%");
/* 350 */     this.yellowValueLabel.setOpaque(true);
/* 351 */     this.yellowValueLabel.setBackground(java.awt.Color.yellow);
/* 352 */     this.blueValueLabel = new javax.swing.JLabel("Blue 50%");
/* 353 */     this.blueValueLabel.setOpaque(true);
/* 354 */     this.blueValueLabel.setBackground(java.awt.Color.cyan);
/* 355 */     java.util.Hashtable labelTable = new java.util.Hashtable();
/* 356 */     labelTable.put(new Integer(this.visionSlider.getMinimum() + 5), this.yellowValueLabel);
/* 357 */     labelTable.put(new Integer(this.visionSlider.getMaximum() - 5), this.blueValueLabel);
/* 358 */     this.visionSlider.setLabelTable(labelTable);
/*     */   }
/*     */   
/*     */   private void createButtons() {
/* 362 */     this.fieldButton = new JButton("Field Generator", new javax.swing.ImageIcon("images/picgeneratefieldbutton2.gif"));
/* 363 */     this.fieldButton.setToolTipText("Generate a Custom Field For Use In The Simulator");
/* 364 */     this.fieldButton.setMnemonic('F');
/* 365 */     this.fieldButton.setBackground(this.lightYellow);
/* 366 */     this.saveButton = new JButton("Save Results", new javax.swing.ImageIcon("images/picsavebutton.gif"));
/* 367 */     this.saveButton.setMnemonic('S');
/* 368 */     this.saveButton.setToolTipText("Save The Current Results To File");
/* 369 */     this.saveButton.setBackground(this.lightBlue);
/* 370 */     this.startStopButton = new JButton("Run Simulator", new javax.swing.ImageIcon("images/picstartsimbutton.gif"));
/* 371 */     this.startStopButton.setMnemonic('R');
/* 372 */     this.startStopButton.setToolTipText("Start The Simulator Using The Current Parameters");
/* 373 */     this.startStopButton.setBackground(this.lightGreen);
/* 374 */     this.resultsButton = new JButton("View Results", new javax.swing.ImageIcon("images/picviewresults2button.gif"));
/* 375 */     this.resultsButton.setMnemonic('V');
/* 376 */     this.resultsButton.setToolTipText("View The Results In a Table Format");
/* 377 */     this.resultsButton.setBackground(this.lightBlue);
/* 378 */     this.graphButton = new JButton("Graph Results", new javax.swing.ImageIcon("images/picviewgraphbutton2.gif"));
/* 379 */     this.graphButton.setMnemonic('G');
/* 380 */     this.graphButton.setToolTipText("Chart a Graph Of The Results");
/* 381 */     this.graphButton.setBackground(this.lightBlue);
/* 382 */     this.editButton = new JButton("Edit Model", new javax.swing.ImageIcon("images/piceditmodelbutton.gif"));
/* 383 */     this.editButton.setMnemonic('E');
/* 384 */     this.editButton.setToolTipText("Adjust The Current Model Settings - Trials, Cycles etc...");
/* 385 */     this.editButton.setBackground(this.lightYellow);
/* 386 */     this.presetButton = new JButton("Preset Lands", new javax.swing.ImageIcon("images/picpresetbutton.gif"));
/* 387 */     this.presetButton.setMnemonic('P');
/* 388 */     this.presetButton.setToolTipText("Give The Bee Initial Lands To Start From");
/* 389 */     this.presetButton.setBackground(this.lightYellow);
/* 390 */     this.quitButton = new JButton("Quit Simulator", new javax.swing.ImageIcon("images/picquitbutton.gif"));
/* 391 */     this.quitButton.setMnemonic('Q');
/* 392 */     this.quitButton.setToolTipText("Quit The Simulator");
/* 393 */     this.quitButton.setBackground(this.lightRed);
/* 394 */     this.loadButton = new JButton("Load Results", new javax.swing.ImageIcon("images/picloadbutton.gif"));
/* 395 */     this.loadButton.setMnemonic('L');
/* 396 */     this.loadButton.setToolTipText("Load Previously Saved Results");
/* 397 */     this.loadButton.setBackground(this.lightBlue);
/*     */     
/* 399 */     this.saveButton.setEnabled(false);
/* 400 */     this.graphButton.setEnabled(false);
/* 401 */     this.resultsButton.setEnabled(false);
/*     */   }
/*     */   
/*     */   private void createInfoBox()
/*     */   {
/* 406 */     this.oldwyLabel = new javax.swing.JLabel();
/* 407 */     this.oldwbLabel = new javax.swing.JLabel();
/* 408 */     this.newwbLabel = new javax.swing.JLabel();
/* 409 */     this.newwyLabel = new javax.swing.JLabel();
/* 410 */     this.learningRateLabel = new javax.swing.JLabel();
/* 411 */     this.errorLabel = new javax.swing.JLabel();
/* 412 */     this.blueLandsLabel = new javax.swing.JLabel();
/* 413 */     this.yellowLandsLabel = new javax.swing.JLabel();
/* 414 */     this.trialsLabel = new javax.swing.JLabel();
/* 415 */     this.cyclesLabel = new javax.swing.JLabel();
/* 416 */     this.presetsLabel = new javax.swing.JLabel();
/* 417 */     this.animationCheckBox = new javax.swing.JCheckBox("Animation On?");
/* 418 */     this.animationCheckBox.setMnemonic('A');
/* 419 */     this.animationCheckBox.addItemListener(this);
/* 420 */     this.animationCheckBox.setSelected(true);
/* 421 */     this.progressBar = new javax.swing.JProgressBar();
/* 422 */     this.progressBar.setMinimum(0);
/* 423 */     this.progressBar.setMaximum(100);
/* 424 */     this.progressBar.setStringPainted(true);
/*     */     
/*     */ 
/* 427 */     this.oldwyLabel.setText("Old Yellow Weight : " + this.neuronModel.getOldWyWeight());
/* 428 */     this.oldwbLabel.setText("Old Blue Weight : " + this.neuronModel.getOldWbWeight());
/* 429 */     this.newwbLabel.setText("New Yellow Weight : " + this.neuronModel.getWyWeight());
/* 430 */     this.newwyLabel.setText("New Blue Weight : " + this.neuronModel.getWbWeight());
/* 431 */     this.learningRateLabel.setText("Learning Rate : " + this.modelParameters.getLearningRate());
/* 432 */     this.errorLabel.setText("Error Rate : " + this.neuronModel.getError());
/* 433 */     this.blueLandsLabel.setText("Blue Flower Lands : 0");
/* 434 */     this.yellowLandsLabel.setText("Yellow Flower Lands : 0");
/* 435 */     this.trialsLabel.setText("Number of Trials : " + this.modelParameters.getTrials());
/* 436 */     this.cyclesLabel.setText("Number of Cycles : " + this.modelParameters.getCycles());
/* 437 */     this.presetsLabel.setText("Preset Lands : No");
/*     */   }
/*     */   
/*     */   private void createMessageBox() {
/* 441 */     this.messageTextArea = new javax.swing.JTextArea(5, 20);
/* 442 */     this.messageTextArea.setEditable(false);
/*     */   }
/*     */   
/*     */   private void addAll()
/*     */   {
/* 447 */     javax.swing.JPanel allSlidersPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
/* 448 */     allSlidersPanel.add(this.visionSlider, "West");
/*     */     
/* 450 */     this.speedPanel.add(this.speedSlider);
/* 451 */     allSlidersPanel.add(this.errorSlider, "East");
/* 452 */     javax.swing.JPanel sliderLabelPanel = new javax.swing.JPanel(new java.awt.GridLayout(1, 2));
/* 453 */     sliderLabelPanel.add(new javax.swing.JLabel("Vision"));
/*     */     
/* 455 */     sliderLabelPanel.add(new javax.swing.JLabel("       Error"));
/* 456 */     allSlidersPanel.add(sliderLabelPanel, "North");
/* 457 */     this.sliderPanel.add(allSlidersPanel);
/*     */     
/* 459 */     this.buttonPanel.add(this.loadButton);
/* 460 */     this.buttonPanel.add(this.saveButton);
/* 461 */     this.buttonPanel.add(this.startStopButton);
/* 462 */     this.buttonPanel.add(this.resultsButton);
/* 463 */     this.buttonPanel.add(this.graphButton);
/* 464 */     this.buttonPanel.add(this.quitButton);
/* 465 */     this.buttonPanel.add(this.fieldButton);
/* 466 */     this.buttonPanel.add(this.editButton);
/* 467 */     this.buttonPanel.add(this.presetButton);
/*     */     
/*     */ 
/* 470 */     this.labelPanel.add(this.newwyLabel);
/* 471 */     this.labelPanel.add(this.newwbLabel);
/* 472 */     this.labelPanel.add(this.learningRateLabel);
/* 473 */     this.labelPanel.add(this.errorLabel);
/* 474 */     this.labelPanel.add(this.blueLandsLabel);
/* 475 */     this.labelPanel.add(this.yellowLandsLabel);
/* 476 */     this.labelPanel.add(this.trialsLabel);
/* 477 */     this.labelPanel.add(this.cyclesLabel);
/* 478 */     this.labelPanel.add(this.presetsLabel);
/* 479 */     this.labelPanel.add(this.animationCheckBox);
/* 480 */     this.labelPanel.add(this.progressBar);
/* 481 */     this.fieldPanel.setPreferredSize(new java.awt.Dimension(this.field.getWidth(), this.field.getHeight()));
/* 482 */     this.fieldPanel.add(this.field);
/*     */     
/*     */ 
/* 485 */     this.fieldButton.addActionListener(this);
/* 486 */     this.loadButton.addActionListener(this);
/* 487 */     this.saveButton.addActionListener(this);
/* 488 */     this.startStopButton.addActionListener(this);
/* 489 */     this.resultsButton.addActionListener(this);
/* 490 */     this.graphButton.addActionListener(this);
/* 491 */     this.presetButton.addActionListener(this);
/* 492 */     this.editButton.addActionListener(this);
/* 493 */     this.quitButton.addActionListener(this);
/*     */   }
/*     */   
/*     */ 
/*     */   private void createFieldBeeNeuron(java.awt.Image fi, java.awt.Image bi)
/*     */   {
/* 499 */     this.modelParameters = new ModelParameters();
/* 500 */     this.bee = new Bee(this.modelParameters);
/* 501 */     this.neuronModel = new NeuronModel(this.modelParameters, this.bee);
/* 502 */     this.resultList = new ResultList(this.modelParameters.getTrials(), this.modelParameters.getCycles());
/*     */     
/* 504 */     System.out.println("field contains: " + fi);
/* 505 */     this.field = new Field(fi, bi, this.visionSlider);
/*     */     
/* 507 */     this.field.setViewDimensions(this.bee.getAltitude());
/*     */   }
/*     */   
/*     */   private void createFieldSlider() {
/* 511 */     this.visionSlider = new javax.swing.JSlider(1, -50, 50, 0);
/* 512 */     this.visionSlider.setEnabled(false);
/* 513 */     this.visionSlider.addChangeListener(this);
/* 514 */     this.visionSlider.setMinorTickSpacing(5);
/* 515 */     this.visionSlider.setMajorTickSpacing(10);
/* 516 */     this.visionSlider.setPaintTicks(true);
/* 517 */     this.visionSlider.setPaintLabels(true);
/*     */   }
/*     */   
/*     */   private void updateGUI()
/*     */   {
/* 522 */     this.twodec = new java.text.DecimalFormat("#0.00");
/*     */     
/* 524 */     this.learningRateLabel.setText("Learning Rate : " + this.twodec.format(this.modelParameters.getLearningRate()));
/* 525 */     this.trialsLabel.setText("Number of Trials : " + this.modelParameters.getTrials());
/* 526 */     this.cyclesLabel.setText("Number of Cycles : " + this.modelParameters.getCycles());
/*     */   }
/*     */   
/*     */ 
/*     */   private javax.swing.JLabel trialsLabel;
/*     */   
/*     */   private javax.swing.JLabel cyclesLabel;
/*     */   
/*     */   private javax.swing.JLabel presetsLabel;
/*     */   
/*     */   private JButton fieldButton;
/*     */   
/*     */   private JButton saveButton;
/*     */   
/*     */   private JButton resultsButton;
/*     */   
/*     */   private JButton startStopButton;
/*     */   
/*     */   private JButton graphButton;
/*     */   
/*     */   private JButton editButton;
/*     */   
/*     */   private JButton loadButton;
/*     */   
/*     */   private JButton quitButton;
/*     */   
/*     */   private JButton presetButton;
/*     */   
/*     */   private javax.swing.JPanel sliderPanel;
/*     */   
/*     */   private javax.swing.JPanel speedPanel;
/*     */   
/*     */   private javax.swing.JPanel buttonPanel;
/*     */   private javax.swing.JPanel labelPanel;
/*     */   private javax.swing.JPanel fieldPanel;
/*     */   javax.swing.JPanel tempPanel;
/*     */   javax.swing.JPanel messagePanel;
/*     */   private javax.swing.JTextArea messageTextArea;
/*     */   private javax.swing.JScrollPane messageScrollPane;
/*     */   private javax.swing.JProgressBar progressBar;
/*     */   private javax.swing.JCheckBox animationCheckBox;
/*     */   private NeuronModel neuronModel;
/*     */   private Field field;
/*     */   private Bee bee;
/*     */   private SimulatorManager simulatorManager;
/*     */   private ModelParameters modelParameters;
/*     */   java.awt.Dimension preferredSize;
/*     */   private javax.swing.JFrame frame;
/*     */   javax.swing.JFrame graphFrame;
/*     */   private ResultList resultList;
/*     */   EditModel editModel;
/*     */   GraphGUI graphGUI;
/*     */   double learningRate;
/*     */   int trials;
/*     */   int cycles;
/*     */   private java.text.DecimalFormat twodec;
/*     */   private javax.swing.JScrollPane fieldScrollPane;
/*     */   FieldGeneratorGUI fg;
/*     */   private ResultListFileHandler rlfh;
/*     */   ViewResultsGUI resultsGUI;
/* 586 */   java.awt.Color lightYellow = new java.awt.Color(240, 240, 220);
/* 587 */   java.awt.Color frameYellow = new java.awt.Color(230, 230, 225);
/* 588 */   java.awt.Color lightBlue = new java.awt.Color(230, 235, 240);
/* 589 */   java.awt.Color frameBlue = new java.awt.Color(213, 216, 219);
/* 590 */   java.awt.Color lightGreen = new java.awt.Color(220, 240, 220);
/* 591 */   java.awt.Color lightRed = new java.awt.Color(240, 220, 220);
/*     */ }


/* Location:              C:\Users\iain\Desktop\Documents\landisk\programming\Iains Work Samples\simulator\project.jar!\SimulatorGUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */