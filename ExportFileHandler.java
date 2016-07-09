/*    */ import java.awt.FileDialog;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import javax.swing.JFileChooser;
/*    */ import javax.swing.JFrame;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ExportFileHandler
/*    */ {
/*    */   FileDialog loadSaveWindow;
/*    */   private JFrame window;
/*    */   FileHandler fileHandler;
/*    */   private String csvData;
/*    */   
/*    */   public ExportFileHandler(String data)
/*    */   {
/* 20 */     this.csvData = data;
/* 21 */     saveCSVData();
/*    */   }
/*    */   
/*    */   private void saveCSVData() {
/* 25 */     this.window = new JFrame();
/* 26 */     JFileChooser chooser = new JFileChooser();
/* 27 */     chooser.setCurrentDirectory(new File("."));
/*    */     
/* 29 */     JFileFilter filter = new JFileFilter();
/* 30 */     filter.addType("csv");
/* 31 */     filter.setDescription("CSV Data File");
/* 32 */     chooser.addChoosableFileFilter(filter);
/* 33 */     int option = chooser.showSaveDialog(this.window);
/* 34 */     if (option == 0)
/*    */     {
/* 36 */       if (chooser.getSelectedFile().getName() != null) {
/* 37 */         File outputFile = new File(chooser.getSelectedFile().getAbsolutePath());
/*    */         try {
/* 39 */           FileHandler.setFileContents(outputFile, this.csvData);
/*    */         }
/*    */         catch (IOException e) {}
/*    */       } else {
/* 43 */         System.out.println("File Not Saved.");
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\iain\Desktop\Documents\landisk\programming\Iains Work Samples\simulator\project.jar!\ExportFileHandler.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */