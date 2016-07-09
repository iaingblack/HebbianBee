/*    */ import java.awt.Color;
/*    */ import java.awt.Image;
/*    */ import java.awt.Toolkit;
/*    */ import javax.swing.UIManager;
/*    */ 
/*    */ public final class HebbianBeeApplication extends java.applet.Applet
/*    */ {
/*    */   SimulatorGUI gui;
/*    */   
/*    */   public final void init()
/*    */   {
/* 12 */     Image field = getImage(getDocumentBase(), "images/fieldexact3.gif");
/*    */     
/* 14 */     Image bee = getImage(getDocumentBase(), "images/bee.gif");
/*    */     
/*    */ 
/* 17 */     this.gui = new SimulatorGUI(field, bee);
/*    */   }
/*    */   
/*    */   public static void main(String[] args)
/*    */   {
/* 22 */     HebbianBeeApplication app = new HebbianBeeApplication();
/*    */   }
/*    */   
/*    */   public HebbianBeeApplication() {
/* 26 */     Toolkit kit = Toolkit.getDefaultToolkit();
/*    */     
/* 28 */     Image field = kit.getImage("images/fieldexact3.gif");
/*    */     
/* 30 */     System.out.println("FIELD IS " + field);
/*    */     
/* 32 */     Image bee = kit.getImage("images/bee.gif");
/* 33 */     setLook();
/* 34 */     this.gui = new SimulatorGUI(field, bee);
/*    */   }
/*    */   
/*    */   public void setLook()
/*    */   {
/* 39 */     UIManager.put("ToolTip.foreground", Color.blue);
/*    */     
/* 41 */     UIManager.put("ToolTip.background", Color.yellow);
/*    */     
/* 43 */     UIColor.setUIColor(new Color(225, 225, 225));
/*    */   }
/*    */ }


/* Location:              C:\Users\iain\Desktop\Documents\landisk\programming\Iains Work Samples\simulator\project.jar!\HebbianBeeApplication.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */