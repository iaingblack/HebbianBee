/*    */ import javax.swing.JFileChooser;
/*    */ 
/*    */ 
/*    */ public final class FieldGeneratorFileHandler
/*    */ {
/*    */   java.awt.FileDialog loadSaveWindow;
/*    */   private javax.swing.JFrame window;
/*    */   ModelParameters modelParameters;
/*    */   FileHandler fileHandler;
/*    */   FieldGenerator fieldGenerator;
/*    */   
/* 12 */   public FieldGeneratorFileHandler(FieldGenerator fg) { this.fieldGenerator = fg; }
/*    */   
/*    */   private int xWidth;
/*    */   private int yWidth;
/*    */   private int iterations;
/*    */   
/*    */   public FieldGeneratorFileHandler() {}
/*    */   
/* 20 */   public final void save(int xw, int yw, int it, int gr, int se, int ra) { this.xWidth = xw;
/* 21 */     this.yWidth = yw;
/* 22 */     this.iterations = it;
/* 23 */     this.graininess = gr;
/* 24 */     this.seed = se;
/* 25 */     this.ratio = ra;
/* 26 */     this.fileHandler = new FileHandler();
/*    */     
/* 28 */     saveField(); }
/*    */   
/*    */   private int graininess;
/*    */   private int seed;
/*    */   private int ratio;
/* 33 */   public final String load() { return loadField(); }
/*    */   
/*    */ 
/*    */   private String getAll()
/*    */   {
/* 38 */     String all = "" + this.xWidth + ":" + this.yWidth + ":" + this.iterations + ":" + this.graininess + ":" + this.seed + ":" + this.ratio + ":";
/* 39 */     return all;
/*    */   }
/*    */   
/*    */   private String loadField()
/*    */   {
/* 44 */     String data = "";
/* 45 */     this.window = new javax.swing.JFrame();
/* 46 */     JFileChooser chooser = new JFileChooser();
/* 47 */     int option = chooser.showOpenDialog(this.window);
/* 48 */     if (option == 0) {
/* 49 */       System.out.println("Opening File: " + chooser.getSelectedFile().getName());
/*    */     } else {
/* 51 */       System.out.println("You Cancelled");
/*    */     }
/* 53 */     if (chooser.getSelectedFile() != null) {
/* 54 */       java.io.File loadModel = chooser.getSelectedFile();
/* 55 */       if (loadModel.canRead()) {
/* 56 */         data = FileHandler.getFileContents(loadModel);
/* 57 */         return data;
/*    */       }
/* 59 */       System.out.println("File Not Valid!");
/*    */     } else {
/* 61 */       System.out.println("File Not Found"); }
/* 62 */     return data;
/*    */   }
/*    */   
/*    */   private void saveField()
/*    */   {
/* 67 */     this.window = new javax.swing.JFrame();
/* 68 */     JFileChooser chooser = new JFileChooser();
/* 69 */     int option = chooser.showSaveDialog(this.window);
/* 70 */     if (option == 0) {
/* 71 */       System.out.println("Saving File: " + (chooser.getSelectedFile() != null ? chooser.getSelectedFile().getName() : "nothing"));
/*    */       
/* 73 */       System.out.println(chooser.getSelectedFile().getAbsolutePath());
/*    */       
/* 75 */       if (chooser.getSelectedFile().getName() != null) {
/* 76 */         java.io.File outputFile = new java.io.File(chooser.getSelectedFile().getAbsolutePath());
/*    */         try {
/* 78 */           FileHandler.setFileContents(outputFile, "FIELD:" + getAll());
/*    */         }
/*    */         catch (java.io.IOException e) {}
/*    */       } else {
/* 82 */         System.out.println("File Not Saved.");
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\iain\Desktop\Documents\landisk\programming\Iains Work Samples\simulator\project.jar!\FieldGeneratorFileHandler.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */