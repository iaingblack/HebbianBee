/*    */ import java.awt.FileDialog;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import java.util.StringTokenizer;
/*    */ import javax.swing.JFileChooser;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JOptionPane;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class EditModelFileHandler
/*    */ {
/*    */   FileDialog loadSaveWindow;
/*    */   private JFrame window;
/*    */   private final ModelParameters modelParameters;
/*    */   FileHandler fileHandler;
/*    */   
/*    */   public EditModelFileHandler(int mode, ModelParameters modelparameters)
/*    */   {
/* 22 */     this.modelParameters = modelparameters;
/* 23 */     this.fileHandler = new FileHandler();
/*    */     
/* 25 */     if (mode == 0) {
/* 26 */       loadModel();
/*    */ 
/*    */     }
/* 29 */     else if (mode == 1) {
/* 30 */       saveModel();
/*    */     }
/*    */   }
/*    */   
/*    */   private void loadModel()
/*    */   {
/* 36 */     this.window = new JFrame();
/* 37 */     JFileChooser chooser = new JFileChooser();
/* 38 */     int option = chooser.showOpenDialog(this.window);
/* 39 */     if (option == 0) {
/* 40 */       System.out.println("Opening File: " + chooser.getSelectedFile().getName());
/*    */     } else {
/* 42 */       System.out.println("You Cancelled");
/*    */     }
/* 44 */     if (chooser.getSelectedFile() != null) {
/* 45 */       File loadModel = chooser.getSelectedFile();
/* 46 */       if (loadModel.canRead()) {
/* 47 */         String w = "";
/* 48 */         String data = FileHandler.getFileContents(loadModel);
/* 49 */         StringTokenizer st = new StringTokenizer(data, ":");
/* 50 */         String fileType = st.nextToken();
/* 51 */         if (fileType.equals("EDITMODEL")) {
/* 52 */           while (st.hasMoreTokens()) {
/* 53 */             w = w + st.nextToken();
/*    */           }
/* 55 */           System.out.println(w);
/* 56 */           this.modelParameters.stringUpdate(data);
/*    */         }
/* 58 */         else if (fileType.equals("RESULTS")) {
/* 59 */           JOptionPane.showMessageDialog(null, "This is a file of RESULTS", "Results File", 1);
/* 60 */         } else if (fileType.equals("FIELD")) {
/* 61 */           JOptionPane.showMessageDialog(null, "This is a FIELD settings file", "Field File", 1);
/*    */         } else {
/* 63 */           JOptionPane.showMessageDialog(null, "This File is Not For Use With The Simulator", "Invalid File", 1);
/*    */         }
/*    */       } else {
/* 66 */         System.out.println("File Not Valid!");
/*    */       }
/* 68 */     } else { System.out.println("File Not Found");
/*    */     }
/*    */   }
/*    */   
/* 72 */   private void saveModel() { this.window = new JFrame();
/* 73 */     JFileChooser chooser = new JFileChooser();
/* 74 */     int option = chooser.showSaveDialog(this.window);
/* 75 */     if (option == 0) {
/* 76 */       System.out.println("Saving File: " + (chooser.getSelectedFile() != null ? chooser.getSelectedFile().getName() : "nothing"));
/*    */       
/* 78 */       System.out.println(chooser.getSelectedFile().getAbsolutePath());
/*    */       
/* 80 */       if (chooser.getSelectedFile().getName() != null) {
/* 81 */         File outputFile = new File(chooser.getSelectedFile().getAbsolutePath());
/*    */         try {
/* 83 */           FileHandler.setFileContents(outputFile, "EDITMODEL:" + this.modelParameters.getAll());
/*    */         }
/*    */         catch (IOException e) {}
/*    */       } else {
/* 87 */         System.out.println("File Not Saved.");
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\iain\Desktop\Documents\landisk\programming\Iains Work Samples\simulator\project.jar!\EditModelFileHandler.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */