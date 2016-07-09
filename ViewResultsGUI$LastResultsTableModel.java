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
/*     */ final class ViewResultsGUI$LastResultsTableModel
/*     */   extends AbstractTableModel
/*     */ {
/*     */   private final int rows;
/*     */   private final ViewResultsGUI this$0;
/*     */   
/*     */   public ViewResultsGUI$LastResultsTableModel(ViewResultsGUI this$0)
/*     */   {
/*  92 */     this.this$0 = this$0;
/*  93 */     this.rows = ViewResultsGUI.access$100(this$0).getTrials();
/*     */   }
/*     */   
/*     */   public final Object getValueAt(int row, int column) {
/*  97 */     Result data = ViewResultsGUI.access$100(this.this$0).getArrayResult(row, ViewResultsGUI.access$100(this.this$0).getCycles() - 1);
/*  98 */     String[] details = data.getResultStringArray();
/*  99 */     return details[column];
/*     */   }
/*     */   
/*     */   public final int getRowCount() {
/* 103 */     return this.rows;
/*     */   }
/*     */   
/*     */   public final int getColumnCount() {
/* 107 */     return 8;
/*     */   }
/*     */   
/*     */   public final String getColumnName(int num) {
/* 111 */     String[] columnNames = { "TRIAL", "CYCLE", "REWARD", "FLOWER", "BLUE WEIGHT", "YELLOW WEIGHT", "BLUE LANDS", "YELLOW LANDS" };
/* 112 */     return columnNames[num];
/*     */   }
/*     */ }


/* Location:              C:\Users\iain\Desktop\Documents\landisk\programming\Iains Work Samples\simulator\project.jar!\ViewResultsGUI$LastResultsTableModel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */