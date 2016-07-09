/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public final class ViewResultsGUI extends JPanel implements java.awt.event.ActionListener, java.awt.event.ItemListener
/*     */ {
/*     */   private javax.swing.JFrame resultsFrame;
/*     */   private javax.swing.JButton closeButton;
/*     */   private javax.swing.JButton exportButton;
/*     */   javax.swing.JTextArea resultsTextArea;
/*     */   javax.swing.JScrollPane resultsScrollPane;
/*     */   private final ResultList resultList;
/*     */   private javax.swing.JCheckBox filterResults;
/*     */   
/*     */   public ViewResultsGUI(ResultList rl) {
/*  14 */     this.resultList = rl;
/*  15 */     System.out.println("ResultList Full? = " + rl.isFull());
/*  16 */     createFrame();
/*  17 */     createGUI();
/*     */   }
/*     */   
/*     */   private void createFrame() {
/*  21 */     this.resultsFrame = new javax.swing.JFrame("Trial Results");
/*  22 */     this.resultsFrame.setIconImage(new javax.swing.ImageIcon("images/beeIcon.gif").getImage());
/*  23 */     this.resultsFrame.setSize(new java.awt.Dimension(700, 500));
/*     */     
/*  25 */     java.awt.Dimension screenDim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
/*  26 */     java.awt.Dimension frameDim = this.resultsFrame.getSize();
/*  27 */     int x = (screenDim.width - frameDim.width) / 2;
/*  28 */     int y = (screenDim.height - frameDim.height) / 2;
/*  29 */     this.resultsFrame.setLocation(x, y);
/*  30 */     this.resultsFrame.setVisible(true);
/*  31 */     this.resultsFrame.setResizable(false);
/*  32 */     this.resultsFrame.addWindowListener(new java.awt.event.WindowAdapter() {
/*     */       public void windowClosing(java.awt.event.WindowEvent e) {
/*  34 */         ViewResultsGUI.this.resultsFrame.dispose();
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   private void createGUI()
/*     */   {
/*  41 */     this.optionPanel = new JPanel();
/*     */     
/*  43 */     this.closeButton = new javax.swing.JButton("Quit", new javax.swing.ImageIcon("images/picquitbutton.gif"));
/*  44 */     this.closeButton.setMnemonic('Q');
/*  45 */     this.closeButton.addActionListener(this);
/*     */     
/*  47 */     this.exportButton = new javax.swing.JButton("Export Results", new javax.swing.ImageIcon("images/picexportbutton.gif"));
/*  48 */     this.exportButton.setMnemonic('E');
/*  49 */     this.exportButton.addActionListener(this);
/*     */     
/*  51 */     this.buttonPanel = new JPanel();
/*  52 */     this.buttonPanel.add(this.exportButton);
/*  53 */     this.buttonPanel.add(this.closeButton);
/*     */     
/*  55 */     JPanel itemsPanel = new JPanel();
/*  56 */     this.optionPanel.add(itemsPanel);
/*     */     
/*  58 */     this.filterResults = new javax.swing.JCheckBox("Show Last Cycles Only", false);
/*  59 */     this.filterResults.setMnemonic('S');
/*  60 */     this.filterResults.addItemListener(this);
/*  61 */     JPanel filterPanel = new JPanel();
/*  62 */     filterPanel.add(this.filterResults);
/*  63 */     filterPanel.setBorder(new javax.swing.border.TitledBorder("Last Result Only"));
/*  64 */     this.optionPanel.add(filterPanel);
/*     */     
/*     */ 
/*  67 */     int numTrials = this.resultList.getTrials() + 1;
/*  68 */     String[] list = new String[numTrials];
/*  69 */     list[0] = "All Trials";
/*  70 */     for (int i = 1; i < numTrials; i++) {
/*  71 */       list[i] = ("Trial " + i);
/*     */     }
/*     */     
/*  74 */     this.selectedTrialList = new javax.swing.JComboBox(list);
/*  75 */     this.selectedTrialList.setToolTipText("Choose Which Trial To View");
/*  76 */     this.selectedTrialList.setSelectedIndex(0);
/*  77 */     this.selectedTrialList.addActionListener(this);
/*  78 */     JPanel trialChoicePanel = new JPanel();
/*  79 */     trialChoicePanel.setBorder(new javax.swing.border.TitledBorder("Trial To View"));
/*  80 */     trialChoicePanel.add(this.selectedTrialList);
/*  81 */     trialChoicePanel.add(new javax.swing.JLabel("Select Trial To View"));
/*  82 */     this.optionPanel.add(trialChoicePanel);
/*  83 */     this.optionPanel.add(this.buttonPanel);
/*     */     
/*  85 */     this.resultsFrame.getContentPane().add(this.optionPanel, "South");
/*     */     
/*  87 */     addTable();
/*     */   }
/*     */   
/*     */   final class LastResultsTableModel extends javax.swing.table.AbstractTableModel {
/*     */     private final int rows;
/*     */     
/*  93 */     public LastResultsTableModel() { this.rows = ViewResultsGUI.this.resultList.getTrials(); }
/*     */     
/*     */     public final Object getValueAt(int row, int column)
/*     */     {
/*  97 */       Result data = ViewResultsGUI.this.resultList.getArrayResult(row, ViewResultsGUI.this.resultList.getCycles() - 1);
/*  98 */       String[] details = data.getResultStringArray();
/*  99 */       return details[column];
/*     */     }
/*     */     
/*     */     public final int getRowCount() {
/* 103 */       return this.rows;
/*     */     }
/*     */     
/*     */     public final int getColumnCount() {
/* 107 */       return 8;
/*     */     }
/*     */     
/*     */     public final String getColumnName(int num) {
/* 111 */       String[] columnNames = { "TRIAL", "CYCLE", "REWARD", "FLOWER", "BLUE WEIGHT", "YELLOW WEIGHT", "BLUE LANDS", "YELLOW LANDS" };
/* 112 */       return columnNames[num];
/*     */     }
/*     */   }
/*     */   
/*     */   final class ResultsTableModel extends javax.swing.table.AbstractTableModel
/*     */   {
/*     */     private final int rows;
/*     */     
/*     */     public ResultsTableModel() {
/* 121 */       this.rows = (ViewResultsGUI.this.resultList.getTrials() * ViewResultsGUI.this.resultList.getCycles());
/*     */     }
/*     */     
/*     */     public final Object getValueAt(int row, int column) {
/* 125 */       int trial = row / ViewResultsGUI.this.resultList.getCycles();
/* 126 */       int cycle = row - trial * ViewResultsGUI.this.resultList.getCycles();
/* 127 */       Result data = ViewResultsGUI.this.resultList.getArrayResult(trial, cycle);
/* 128 */       String[] details = data.getResultStringArray();
/* 129 */       return details[column];
/*     */     }
/*     */     
/*     */     public final int getRowCount() {
/* 133 */       return this.rows;
/*     */     }
/*     */     
/*     */     public final int getColumnCount() {
/* 137 */       return 8;
/*     */     }
/*     */     
/*     */     public final String getColumnName(int num) {
/* 141 */       String[] columnNames = { "TRIAL", "CYCLE", "REWARD", "FLOWER", "BLUE WEIGHT", "YELLOW WEIGHT", "BLUE LANDS", "YELLOW LANDS" };
/* 142 */       return columnNames[num];
/*     */     }
/*     */   }
/*     */   
/*     */   final class TrialResultsTableModel extends javax.swing.table.AbstractTableModel {
/*     */     private final int rows;
/*     */     private int selectedTrial;
/*     */     
/*     */     public TrialResultsTableModel(int st) {
/* 151 */       this.selectedTrial = st;
/* 152 */       this.selectedTrial -= 1;
/* 153 */       this.rows = ViewResultsGUI.this.resultList.getCycles();
/*     */     }
/*     */     
/*     */     public final Object getValueAt(int row, int column) {
/* 157 */       int trial = row / ViewResultsGUI.this.resultList.getCycles();
/* 158 */       int cycle = row - trial * ViewResultsGUI.this.resultList.getCycles();
/* 159 */       Result data = ViewResultsGUI.this.resultList.getArrayResult(this.selectedTrial, cycle);
/* 160 */       String[] details = data.getResultStringArray();
/* 161 */       return details[column];
/*     */     }
/*     */     
/*     */     public final int getRowCount() {
/* 165 */       return this.rows;
/*     */     }
/*     */     
/*     */     public final int getColumnCount() {
/* 169 */       return 8;
/*     */     }
/*     */     
/*     */     public final String getColumnName(int num) {
/* 173 */       String[] columnNames = { "TRIAL", "CYCLE", "REWARD", "FLOWER", "BLUE WEIGHT", "YELLOW WEIGHT", "BLUE LANDS", "YELLOW LANDS" };
/* 174 */       return columnNames[num];
/*     */     }
/*     */   }
/*     */   
/*     */   private JPanel optionPanel;
/*     */   private JPanel buttonPanel;
/*     */   private javax.swing.JTable table;
/*     */   
/* 182 */   private void addTable() { this.scrollPane = null;
/* 183 */     this.table = null;
/*     */     
/* 185 */     createTable();
/*     */     
/*     */ 
/* 188 */     this.scrollPane = new javax.swing.JScrollPane(this.table);
/*     */     
/*     */ 
/* 191 */     this.resultsFrame.getContentPane().add(this.scrollPane, "Center");
/* 192 */     this.resultsFrame.show(); }
/*     */   
/*     */   private javax.swing.JScrollPane scrollPane;
/*     */   private boolean resultFilter;
/*     */   
/* 197 */   private void createTable() { if (this.resultFilter != true) {
/* 198 */       this.model = new ViewResultsGUI.ResultsTableModel();
/*     */     } else {
/* 200 */       this.model = new ViewResultsGUI.LastResultsTableModel();
/*     */     }
/*     */     
/* 203 */     this.table = new javax.swing.JTable(this.model);
/* 204 */     this.table.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 700));
/*     */   }
/*     */   
/*     */   private javax.swing.table.TableModel model;
/*     */   private javax.swing.JComboBox selectedTrialList;
/* 209 */   private void setFilter(boolean set) { this.resultFilter = set; }
/*     */   
/*     */ 
/*     */   public final void actionPerformed(java.awt.event.ActionEvent av)
/*     */   {
/* 214 */     if (av.getSource() == this.closeButton) close();
/* 215 */     if (av.getSource() == this.selectedTrialList) {
/* 216 */       this.filterResults.setSelected(false);
/*     */       
/* 218 */       int selectedItem = this.selectedTrialList.getSelectedIndex();
/* 219 */       changeTableModel(selectedItem);
/*     */     }
/* 221 */     if (av.getSource() == this.exportButton) {
/* 222 */       exportAsCSV();
/*     */     }
/*     */   }
/*     */   
/*     */   public final void itemStateChanged(java.awt.event.ItemEvent ie) {
/* 227 */     if (ie.getSource() == this.filterResults) {
/* 228 */       changeTableModel();
/*     */     }
/*     */   }
/*     */   
/*     */   private void exportAsCSV()
/*     */   {
/* 234 */     String csvData = this.resultList.getResultsAsCSV();
/* 235 */     ExportFileHandler exfh = new ExportFileHandler(csvData);
/*     */   }
/*     */   
/*     */   private void close() {
/* 239 */     this.resultsFrame.dispose();
/*     */   }
/*     */   
/*     */   private void changeTableModel()
/*     */   {
/* 244 */     setFilter(this.filterResults.isSelected());
/* 245 */     if (!this.resultFilter) this.model = new ViewResultsGUI.ResultsTableModel();
/* 246 */     if (this.resultFilter) this.model = new ViewResultsGUI.LastResultsTableModel();
/* 247 */     this.table.setModel(this.model);
/*     */   }
/*     */   
/*     */   private void changeTableModel(int trial) {
/* 251 */     System.out.println("Doing it");
/*     */     
/* 253 */     int selectedTrial = trial;
/*     */     
/*     */ 
/* 256 */     if (selectedTrial == 0) {
/* 257 */       this.model = new ViewResultsGUI.ResultsTableModel();
/*     */     } else {
/* 259 */       this.model = new ViewResultsGUI.TrialResultsTableModel(selectedTrial);
/*     */     }
/*     */     
/* 262 */     this.table.setModel(this.model);
/*     */   }
/*     */ }


/* Location:              C:\Users\iain\Desktop\Documents\landisk\programming\Iains Work Samples\simulator\project.jar!\ViewResultsGUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */