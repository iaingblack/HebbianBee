/*    */ import java.io.BufferedReader;
/*    */ import java.io.BufferedWriter;
/*    */ import java.io.File;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.FileReader;
/*    */ import java.io.FileWriter;
/*    */ import java.io.IOException;
/*    */ import java.io.Writer;
/*    */ 
/*    */ 
/*    */ public final class FileHandler
/*    */ {
/*    */   public static String getFileContents(File aFile)
/*    */   {
/* 15 */     StringBuffer contents = new StringBuffer();
/* 16 */     BufferedReader input = null;
/*    */     try
/*    */     {
/* 19 */       input = new BufferedReader(new FileReader(aFile));
/*    */       String line;
/* 21 */       while ((line = input.readLine()) != null) {
/* 22 */         contents.append(line);
/* 23 */         contents.append(System.getProperty("line.separator"));
/*    */       }
/*    */     } catch (FileNotFoundException ex) {
/* 26 */       ex.printStackTrace();
/*    */     } catch (IOException ex) {
/* 28 */       ex.printStackTrace();
/*    */     } finally {
/*    */       try {
/* 31 */         if (input != null)
/*    */         {
/* 33 */           input.close();
/*    */         }
/*    */       } catch (IOException ex) {
/* 36 */         ex.printStackTrace();
/*    */       }
/*    */     }
/* 39 */     return contents.toString();
/*    */   }
/*    */   
/*    */ 
/*    */   public static void setFileContents(File aFile, String aContents)
/*    */     throws FileNotFoundException, IOException
/*    */   {
/* 46 */     Writer output = null;
/*    */     try
/*    */     {
/* 49 */       output = new BufferedWriter(new FileWriter(aFile));
/* 50 */       output.write(aContents);
/*    */     }
/*    */     finally {
/* 53 */       if (output != null) output.close();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\iain\Desktop\Documents\landisk\programming\Iains Work Samples\simulator\project.jar!\FileHandler.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */