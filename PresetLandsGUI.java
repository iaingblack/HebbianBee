/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class PresetLandsGUI extends JFrame implements java.awt.event.ActionListener
/*     */ {
/*     */   public final void actionPerformed(ActionEvent av)
/*     */   {
/*  17 */     if (av.getSource() == this.okButton) {
/*  18 */       this.modelParameters.setStartingCycle(this.landList.size());
/*  19 */       this.neuronModel.setInitialWy(this.wyTotal);
/*  20 */       this.neuronModel.setInitialWb(this.wbTotal);
/*  21 */       this.presetsLabel.setText("Presets : Yes, " + this.landList.size());
/*     */       
/*  23 */       this.frame.hide();
/*  24 */       this.frame.dispose();
/*     */     }
/*  26 */     if (av.getSource() == this.cancelButton) {
/*  27 */       this.frame.hide();
/*  28 */       this.frame.dispose();
/*  29 */       this.presetsLabel.setText("Presets : No");
/*     */     }
/*  31 */     if (av.getSource() == this.resetButton) {
/*  32 */       for (int i = 0; i < this.landList.size(); i++) {
/*  33 */         this.selectedPanel.remove((JPanel)this.landList.get(i));
/*     */       }
/*  35 */       resetLabels();
/*  36 */       this.landList.clear();
/*  37 */       this.modelParameters.setStartingCycle(0);
/*  38 */       this.modelParameters.setInitialYellowLands(0);
/*  39 */       this.modelParameters.setInitialBlueLands(0);
/*  40 */       this.wyTotal = 0.0D;
/*  41 */       this.wbTotal = 0.0D;
/*  42 */       this.rewardingLands = 0;
/*  43 */       this.nonRewardingLands = 0;
/*  44 */       this.selectedPanel.repaint();
/*     */     }
/*  46 */     if (av.getSource() == this.yellowRewardButton) {
/*  47 */       this.rewardingLands += 1;
/*  48 */       addChoice(0);
/*     */     }
/*  50 */     if (av.getSource() == this.yellowNoRewardButton) {
/*  51 */       this.nonRewardingLands += 1;
/*  52 */       addChoice(1);
/*     */     }
/*  54 */     if (av.getSource() == this.blueRewardButton) {
/*  55 */       addChoice(2);
/*     */     }
/*     */   }
/*     */   
/*     */   public PresetLandsGUI(ModelParameters mp, ResultList rl, NeuronModel nm, JLabel label) {
/*  60 */     initGUI();
/*  61 */     createFrame();
/*  62 */     this.modelParameters = mp;
/*  63 */     this.resultList = rl;
/*  64 */     this.neuronModel = nm;
/*  65 */     this.presetsLabel = label;
/*     */     
/*  67 */     this.twodec = new DecimalFormat("#0.00");
/*     */     
/*     */ 
/*  70 */     this.modelParameters.setStartingCycle(0);
/*  71 */     this.modelParameters.setInitialYellowLands(0);
/*  72 */     this.modelParameters.setInitialBlueLands(0);
/*  73 */     this.wyTotal = 0.0D;
/*  74 */     this.wbTotal = 0.0D;
/*     */   }
/*     */   
/*     */   private void resetLabels()
/*     */   {
/*  79 */     this.newYellowWeightValueLabel.setText("0.0");
/*  80 */     this.newBlueWeightValueLabel.setText("0.0");
/*  81 */     this.yellowRewardingLandsValueLabel.setText("0");
/*  82 */     this.yellowNoRewardingLandsValueLabel.setText("0");
/*  83 */     this.blueRewardingLandsValueLabel.setText("0");
/*     */   }
/*     */   
/*     */   public void createFrame()
/*     */   {
/*  88 */     this.frame = new JFrame("Preset Initial Landings");
/*  89 */     this.frame.setIconImage(new ImageIcon("images/beeIcon.gif").getImage());
/*  90 */     this.frame.getContentPane().add(this.mainPanel, "Center");
/*  91 */     this.frame.getContentPane().add(this.buttonsPanel, "South");
/*  92 */     this.frame.setSize(new Dimension(450, 500));
/*     */     
/*  94 */     Dimension screenDim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
/*  95 */     Dimension frameDim = this.frame.getSize();
/*  96 */     int x = (screenDim.width - frameDim.width) / 2;
/*  97 */     int y = (screenDim.height - frameDim.height) / 2;
/*  98 */     this.frame.setLocation(x, y);
/*  99 */     this.frame.setVisible(true);
/* 100 */     this.frame.addWindowListener(new java.awt.event.WindowAdapter()
/*     */     {
/*     */       public void windowClosing(java.awt.event.WindowEvent e) {
/* 103 */         PresetLandsGUI.this.frame = null;
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   public void initGUI() {
/* 109 */     this.okButton.setText("OK");
/* 110 */     this.okButton.setMnemonic('O');
/* 111 */     this.okButton.addActionListener(this);
/* 112 */     this.weightValuesLabel.setText("Weights On Start Of Simulations Are - Yellow Weight:  Blue Weight: ");
/*     */     
/* 114 */     this.yellowRewardButton.setText("Add Yellow Rewarding Land");
/* 115 */     this.yellowRewardButton.setBorderPainted(true);
/* 116 */     this.yellowRewardButton.setMnemonic('Y');
/* 117 */     this.yellowRewardButton.addActionListener(this);
/*     */     
/* 119 */     this.yellowNoRewardButton.setText("Add Yellow Non-Rewarding Land");
/* 120 */     this.yellowNoRewardButton.setBorderPainted(true);
/* 121 */     this.yellowNoRewardButton.setPreferredSize(new Dimension(200, 27));
/* 122 */     this.yellowNoRewardButton.setMnemonic('N');
/* 123 */     this.yellowNoRewardButton.addActionListener(this);
/* 124 */     this.mainPanel.setLayout(new GridLayout(3, 1));
/* 125 */     this.mainPanel.add(this.addLandPanel);
/* 126 */     this.mainPanel.add(this.choicesPanel);
/* 127 */     this.mainPanel.add(this.infoPanel);
/* 128 */     this.addLandPanel.setBorder(new javax.swing.border.TitledBorder("Add Lands"));
/* 129 */     this.addLandPanel.setLayout(new GridLayout(3, 1));
/* 130 */     this.addLandPanel.add(this.yellowRewardButton);
/* 131 */     this.addLandPanel.add(this.yellowNoRewardButton);
/* 132 */     this.addLandPanel.add(this.blueRewardButton);
/* 133 */     this.blueRewardButton.setText("Add Blue Land");
/*     */     
/* 135 */     this.blueRewardButton.setMnemonic('B');
/* 136 */     this.blueRewardButton.addActionListener(this);
/* 137 */     this.choicesPanel.setBorder(new javax.swing.border.TitledBorder("Preset Lands Chosen"));
/* 138 */     this.choicesPanel.setLayout(new GridLayout(1, 1));
/* 139 */     this.choicesPanel.add(this.selectedPanel);
/* 140 */     this.infoPanel.setBorder(new javax.swing.border.TitledBorder("Weights Information"));
/* 141 */     this.infoPanel.add(this.newYellowWeightLabel);
/* 142 */     this.infoPanel.add(this.newYellowWeightValueLabel);
/* 143 */     this.infoPanel.add(this.newBlueWeightLabel);
/* 144 */     this.infoPanel.add(this.newBlueWeightValueLabel);
/* 145 */     this.infoPanel.add(this.yellowRewardingLandsLabel);
/* 146 */     this.infoPanel.add(this.yellowRewardingLandsValueLabel);
/* 147 */     this.infoPanel.add(this.yellowNoRewardingLandsLabel);
/* 148 */     this.infoPanel.add(this.yellowNoRewardingLandsValueLabel);
/* 149 */     this.infoPanel.add(this.blueRewardingLandsLabel);
/* 150 */     this.infoPanel.add(this.blueRewardingLandsValueLabel);
/*     */     
/* 152 */     this.buttonsPanel.add(this.okButton);
/* 153 */     this.buttonsPanel.add(this.cancelButton);
/* 154 */     this.buttonsPanel.add(this.resetButton);
/* 155 */     this.cancelButton.setMnemonic('C');
/* 156 */     this.cancelButton.setText("Cancel");
/* 157 */     this.cancelButton.addActionListener(this);
/* 158 */     this.resetButton.setMnemonic('R');
/* 159 */     this.resetButton.setText("Reset");
/* 160 */     this.resetButton.addActionListener(this);
/*     */   }
/*     */   
/*     */   public void addChoice(int type)
/*     */   {
/* 165 */     if (this.landList.size() >= this.resultList.getCycles()) {
/* 166 */       javax.swing.JOptionPane.showMessageDialog(null, "Filled All Cycle Slots", "No More Space", 1, new ImageIcon("images/bee.gif"));
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/* 172 */       int yellowLands = 0;
/* 173 */       int blueLands = 0;
/*     */       
/* 175 */       yellowLands = this.modelParameters.getInitialYellowLands();
/* 176 */       blueLands = this.modelParameters.getInitialBlueLands();
/*     */       
/* 178 */       int choice = type;
/* 179 */       JPanel selection = new JPanel();
/* 180 */       this.landList.add(selection);
/* 181 */       selection.setBorder(new javax.swing.border.EtchedBorder());
/*     */       
/* 183 */       switch (choice) {
/*     */       case 0: 
/* 185 */         yellowLands++;
/* 186 */         selection.setBackground(this.yellowColor);
/* 187 */         this.modelParameters.setInitialYellowLands(yellowLands);
/* 188 */         this.yellowRewardingLandsValueLabel.setForeground(new Color(180, 180, 0));
/* 189 */         this.yellowRewardingLandsValueLabel.setText("" + this.rewardingLands);
/* 190 */         double reward = this.neuronModel.YELLOWREWARD;
/* 191 */         this.wyTotal = this.neuronModel.getNewYellowRewardWeight(this.wyTotal, true);
/*     */         
/* 193 */         this.newYellowWeightValueLabel.setText(this.twodec.format(this.wyTotal));
/* 194 */         for (int trialNum = 1; trialNum < this.modelParameters.getTrials() + 1; trialNum++) {
/* 195 */           this.resultList.add(reward, "Yellow", this.wbTotal, this.wyTotal, trialNum, this.landList.size(), blueLands, yellowLands);
/*     */         }
/* 197 */         break;
/*     */       case 1: 
/* 199 */         yellowLands++;
/* 200 */         selection.setBackground(this.redColor);
/* 201 */         this.modelParameters.setInitialYellowLands(yellowLands);
/* 202 */         this.yellowNoRewardingLandsValueLabel.setForeground(this.redColor);
/* 203 */         this.yellowNoRewardingLandsValueLabel.setText("" + this.nonRewardingLands);
/* 204 */         double reward = this.neuronModel.NOREWARD;
/* 205 */         this.wyTotal = this.neuronModel.getNewYellowRewardWeight(this.wyTotal, false);
/*     */         
/* 207 */         this.newYellowWeightValueLabel.setText(this.twodec.format(this.wyTotal));
/* 208 */         for (int trialNum = 1; trialNum < this.modelParameters.getTrials() + 1; trialNum++) {
/* 209 */           this.resultList.add(reward, "Yellow", this.wbTotal, this.wyTotal, trialNum, this.landList.size(), blueLands, yellowLands);
/*     */         }
/* 211 */         break;
/*     */       case 2: 
/* 213 */         blueLands++;
/* 214 */         selection.setBackground(this.blueColor);
/* 215 */         this.modelParameters.setInitialBlueLands(blueLands);
/* 216 */         this.blueRewardingLandsValueLabel.setForeground(this.blueColor);
/* 217 */         this.blueRewardingLandsValueLabel.setText("" + blueLands);
/* 218 */         double reward = this.neuronModel.BLUEREWARD;
/* 219 */         this.wbTotal = this.neuronModel.getNewBlueRewardWeight(this.wbTotal);
/*     */         
/* 221 */         this.newBlueWeightValueLabel.setText(this.twodec.format(this.wbTotal));
/* 222 */         for (int trialNum = 1; trialNum < this.modelParameters.getTrials() + 1; trialNum++) {
/* 223 */           this.resultList.add(reward, "Blue", this.wbTotal, this.wyTotal, trialNum, this.landList.size(), blueLands, yellowLands);
/*     */         }
/*     */       }
/*     */       
/*     */       
/* 228 */       this.selectedPanel.add((JPanel)this.landList.get(this.landList.size() - 1));
/* 229 */       this.frame.show();
/*     */     }
/*     */   }
/*     */   
/* 233 */   private JPanel mainPanel = new JPanel();
/* 234 */   private JButton yellowRewardButton = new JButton(new ImageIcon("images/picyellowlandbutton.gif"));
/* 235 */   private JButton yellowNoRewardButton = new JButton(new ImageIcon("images/picredlandbutton.gif"));
/* 236 */   private JPanel addLandPanel = new JPanel();
/* 237 */   private JPanel choicesPanel = new JPanel();
/*     */   
/* 239 */   private JPanel infoPanel = new JPanel(new GridLayout(5, 2, 10, 0));
/* 240 */   private JButton blueRewardButton = new JButton(new ImageIcon("images/picbluelandbutton.gif"));
/* 241 */   private JPanel buttonsPanel = new JPanel();
/* 242 */   private JLabel weightValuesLabel = new JLabel("");
/* 243 */   private JPanel selectedPanel = new JPanel();
/* 244 */   private JButton okButton = new JButton(new ImageIcon("images/picstartbutton.gif"));
/* 245 */   private JButton cancelButton = new JButton(new ImageIcon("images/picstopbutton.gif"));
/* 246 */   private JButton resetButton = new JButton(new ImageIcon("images/piceditresetbutton.gif"));
/*     */   private JFrame frame;
/* 248 */   private ArrayList landList = new ArrayList();
/*     */   
/*     */ 
/* 251 */   private JLabel newYellowWeightLabel = new JLabel("New Yellow Weight:");
/* 252 */   private JLabel newBlueWeightLabel = new JLabel("New Blue Weight:");
/* 253 */   private JLabel yellowRewardingLandsLabel = new JLabel("Yellow Successful Lands: ");
/* 254 */   private JLabel yellowNoRewardingLandsLabel = new JLabel("Yellow Unsuccessful Lands: ");
/* 255 */   private JLabel blueRewardingLandsLabel = new JLabel("Blue Successful Lands: ");
/*     */   
/* 257 */   private JLabel newYellowWeightValueLabel = new JLabel("0.0");
/* 258 */   private JLabel newBlueWeightValueLabel = new JLabel("0.0");
/* 259 */   private JLabel yellowRewardingLandsValueLabel = new JLabel("0");
/* 260 */   private JLabel yellowNoRewardingLandsValueLabel = new JLabel("0");
/* 261 */   private JLabel blueRewardingLandsValueLabel = new JLabel("0");
/* 262 */   private JLabel presetsLabel = new JLabel();
/*     */   
/* 264 */   private Color redColor = new Color(255, 0, 0);
/* 265 */   private Color blueColor = new Color(0, 0, 255);
/* 266 */   private Color yellowColor = new Color(255, 255, 0);
/*     */   private ModelParameters modelParameters;
/*     */   private ResultList resultList;
/*     */   private NeuronModel neuronModel;
/*     */   private double wyTotal;
/*     */   private double wbTotal;
/*     */   private final DecimalFormat twodec;
/*     */   private int nonRewardingLands;
/*     */   private int rewardingLands;
/*     */ }


/* Location:              C:\Users\iain\Desktop\Documents\landisk\programming\Iains Work Samples\simulator\project.jar!\PresetLandsGUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */