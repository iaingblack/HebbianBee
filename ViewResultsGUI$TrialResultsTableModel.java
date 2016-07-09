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
/*     */ final class ViewResultsGUI$TrialResultsTableModel
/*     */   extends AbstractTableModel
/*     */ {
/*     */   private final int rows;
/*     */   private int selectedTrial;
/*     */   private final ViewResultsGUI this$0;
/*     */   
/*     */   public ViewResultsGUI$TrialResultsTableModel(ViewResultsGUI this$0, int st)
/*     */   {
/* 150 */     this.this$0 = this$0;
/* 151 */     this.selectedTrial = st;
/* 152 */     this.selectedTrial -= 1;
/* 153 */     this.rows = ViewResultsGUI.access$100(this$0).getCycles();
/*     */   }
/*     */   
/*     */   public final Object getValueAt(int row, int column) {
/* 157 */     int trial = row / ViewResultsGUI.access$100(this.this$0).getCycles();
/* 158 */     int cycle = row - trial * ViewResultsGUI.access$100(this.this$0).getCycles();
/* 159 */     Result data = ViewResultsGUI.access$100(this.this$0).getArrayResult(this.selectedTrial, cycle);
/* 160 */     String[] details = data.getResultStringArray();
/* 161 */     return details[column];
/*     */   }
/*     */   
/*     */   public final int getRowCount() {
/* 165 */     return this.rows;
/*     */   }
/*     */   
/*     */   public final int getColumnCount() {
/* 169 */     return 8;
/*     */   }
/*     */   
/*     */   public final String getColumnName(int num) {
/* 173 */     String[] columnNames = { "TRIAL", "CYCLE", "REWARD", "FLOWER", "BLUE WEIGHT", "YELLOW WEIGHT", "BLUE LANDS", "YELLOW LANDS" };
/* 174 */     return columnNames[num];
/*     */   }
/*     */ }


/* Location:              C:\Users\iain\Desktop\Documents\landisk\programming\Iains Work Samples\simulator\project.jar!\ViewResultsGUI$TrialResultsTableModel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */