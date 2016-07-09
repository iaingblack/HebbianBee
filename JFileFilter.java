/*    */ import java.io.File;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import javax.swing.filechooser.FileFilter;
/*    */ 
/*    */ public class JFileFilter
/*    */   extends FileFilter
/*    */ {
/*    */   protected String description;
/* 10 */   protected ArrayList exts = new ArrayList();
/*    */   
/*    */   public void addType(String s) {
/* 13 */     this.exts.add(s);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean accept(File f)
/*    */   {
/* 20 */     if (f.isDirectory()) {
/* 21 */       return true;
/*    */     }
/* 23 */     if (f.isFile()) {
/* 24 */       Iterator it = this.exts.iterator();
/* 25 */       while (it.hasNext()) {
/* 26 */         if (f.getName().endsWith((String)it.next())) {
/* 27 */           return true;
/*    */         }
/*    */       }
/*    */     }
/*    */     
/* 32 */     return false;
/*    */   }
/*    */   
/*    */   public void setDescription(String s)
/*    */   {
/* 37 */     this.description = s;
/*    */   }
/*    */   
/*    */   public String getDescription() {
/* 41 */     return this.description;
/*    */   }
/*    */ }


/* Location:              C:\Users\iain\Desktop\Documents\landisk\programming\Iains Work Samples\simulator\project.jar!\JFileFilter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */