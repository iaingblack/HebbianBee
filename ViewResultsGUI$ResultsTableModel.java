/*     */ import javax.swing.table.AbstractTableModel;
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
/*     */ final class ViewResultsGUI$ResultsTableModel
/*     */   extends AbstractTableModel
/*     */ {
/*     */   private final int rows;
/*     */   private final ViewResultsGUI this$0;
/*     */   
/*     */   public ViewResultsGUI$ResultsTableModel(ViewResultsGUI this$0)
/*     */   {
/* 120 */     this.this$0 = this$0;
/* 121 */     this.rows = (ViewResultsGUI.access$100(this$0).getTrials() * ViewResultsGUI.access$100(this$0).getCycles());
/*     */   }
/*     */   
/*     */   public final Object getValueAt(int row, int column) {
/* 125 */     int trial = row / ViewResultsGUI.access$100(this.this$0).getCycles();
/* 126 */     int cycle = row - trial * ViewResultsGUI.access$100(this.this$0).getCycles();
/* 127 */     Result data = ViewResultsGUI.access$100(this.this$0).getArrayResult(trial, cycle);
/* 128 */     String[] details = data.getResultStringArray();
/* 129 */     return details[column];
/*     */   }
/*     */   
/*     */   public final int getRowCount() {
/* 133 */     return this.rows;
/*     */   }
/*     */   
/*     */   public final int getColumnCount() {
/* 137 */     return 8;
/*     */   }
/*     */   
/*     */   public final String getColumnName(int num) {
/* 141 */     String[] columnNames = { "TRIAL", "CYCLE", "REWARD", "FLOWER", "BLUE WEIGHT", "YELLOW WEIGHT", "BLUE LANDS", "YELLOW LANDS" };
/* 142 */     return columnNames[num];
/*     */   }
/*     */ }


/* Location:              C:\Users\iain\Desktop\Documents\landisk\programming\Iains Work Samples\simulator\project.jar!\ViewResultsGUI$ResultsTableModel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */