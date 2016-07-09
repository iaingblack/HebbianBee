/*    */ import java.awt.Color;
/*    */ import javax.swing.UIManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UIColor
/*    */ {
/*    */   public static void UIColor() {}
/*    */   
/*    */   public static void setUIColor(Color newColor)
/*    */   {
/* 19 */     Color backgroundColor = newColor;
/* 20 */     UIManager.put("Panel.background", backgroundColor);
/* 21 */     UIManager.put("Slider.background", backgroundColor);
/* 22 */     UIManager.put("CheckBox.background", backgroundColor);
/* 23 */     UIManager.put("Label.background", backgroundColor);
/* 24 */     UIManager.put("ComboBox.background", backgroundColor);
/* 25 */     UIManager.put("ScrollPane.background", backgroundColor);
/* 26 */     UIManager.put("TextArea.background", backgroundColor);
/*    */   }
/*    */ }


/* Location:              C:\Users\iain\Desktop\Documents\landisk\programming\Iains Work Samples\simulator\project.jar!\UIColor.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */