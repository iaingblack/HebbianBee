/*    */ import javax.swing.JFileChooser;
/*    */ 
/*    */ public final class ResultListFileHandler
/*    */ {
/*    */   java.awt.FileDialog loadSaveWindow;
/*    */   private javax.swing.JFrame window;
/*    */   FileHandler fileHandler;
/*    */   private final ResultList resultList;
/*    */   
/*    */   public ResultListFileHandler(ResultList rl)
/*    */   {
/* 12 */     this.resultList = rl;
/*    */   }
/*    */   
/*    */   public final void save() {
/* 16 */     saveResults();
/*    */   }
/*    */   
/*    */   public final String load() {
/* 20 */     return loadResults();
/*    */   }
/*    */   
/*    */   private String loadResults()
/*    */   {
/* 25 */     String data = "";
/* 26 */     this.window = new javax.swing.JFrame();
/* 27 */     JFileChooser chooser = new JFileChooser();
/* 28 */     int option = chooser.showOpenDialog(this.window);
/* 29 */     if (option == 0) {
/* 30 */       System.out.println("Opening File: " + chooser.getSelectedFile().getName());
/*    */     } else {
/* 32 */       System.out.println("You Cancelled");
/*    */     }
/* 34 */     if (chooser.getSelectedFile() != null) {
/* 35 */       java.io.File loadModel = chooser.getSelectedFile();
/* 36 */       if (loadModel.canRead()) {
/* 37 */         data = FileHandler.getFileContents(loadModel);
/* 38 */         return data;
/*    */       }
/* 40 */       System.out.println("File Not Valid!");
/*    */     } else {
/* 42 */       System.out.println("File Not Found"); }
/* 43 */     return data;
/*    */   }
/*    */   
/*    */   private void saveResults()
/*    */   {
/* 48 */     this.window = new javax.swing.JFrame();
/* 49 */     JFileChooser chooser = new JFileChooser();
/* 50 */     int option = chooser.showSaveDialog(this.window);
/* 51 */     if (option == 0) {
/* 52 */       System.out.println("Saving File: " + (chooser.getSelectedFile() != null ? chooser.getSelectedFile().getName() : "nothing"));
/*    */       
/* 54 */       System.out.println(chooser.getSelectedFile().getAbsolutePath());
/*    */       
/* 56 */       if (chooser.getSelectedFile().getName() != null) {
/* 57 */         java.io.File outputFile = new java.io.File(chooser.getSelectedFile().getAbsolutePath());
/* 58 */         StringBuffer results = new StringBuffer();
/* 59 */         results.append("RESULTS:");
/*    */         
/* 61 */         results.append(this.resultList.getTrials() + ":");
/* 62 */         results.append(this.resultList.getCycles());
/* 63 */         for (int i = 0; i < this.resultList.getTrials(); i++) {
/* 64 */           for (int j = 0; j < this.resultList.getCycles(); j++) {
/* 65 */             Result data = this.resultList.getArrayResult(i, j);
/* 66 */             if (data != null) {
/* 67 */               results.append(":");
/* 68 */               results.append(data.asString());
/*    */             }
/*    */           }
/*    */         }
/*    */         
/*    */ 
/* 74 */         String allResults = results.toString();
/*    */         try {
/* 76 */           FileHandler.setFileContents(outputFile, allResults);
/*    */         }
/*    */         catch (java.io.IOException e) {}
/*    */       } else {
/* 80 */         System.out.println("File Not Saved.");
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\iain\Desktop\Documents\landisk\programming\Iains Work Samples\simulator\project.jar!\ResultListFileHandler.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */