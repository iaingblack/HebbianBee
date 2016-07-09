/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.image.BufferedImage;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JSlider;
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
/*     */ final class FieldGenerator$1DynamicIcon
/*     */   implements Icon
/*     */ {
/*     */   private final JSlider val$widthSlider;
/*     */   private final JSlider val$heightSlider;
/*     */   private final FieldGenerator this$0;
/*     */   
/*     */   FieldGenerator$1DynamicIcon(FieldGenerator this$0, JSlider val$widthSlider, JSlider val$heightSlider)
/*     */   {
/* 372 */     this.this$0 = this$0;this.val$widthSlider = val$widthSlider;this.val$heightSlider = val$heightSlider; }
/*     */   
/* 374 */   public final int getIconWidth() { return this.val$widthSlider.getValue(); }
/*     */   
/*     */   public final int getIconHeight()
/*     */   {
/* 378 */     return this.val$heightSlider.getValue();
/*     */   }
/*     */   
/*     */   public void setIconWidth(int width) {
/* 382 */     this.val$widthSlider.setValue(width);
/*     */   }
/*     */   
/*     */   public void setIconHeight(int height) {
/* 386 */     this.val$heightSlider.setValue(height);
/*     */   }
/*     */   
/*     */   public final void paintIcon(Component c, Graphics g, int x, int y) {
/* 390 */     FieldGenerator.access$000(this.this$0, this.val$widthSlider.getValue(), this.val$heightSlider.getValue());
/* 391 */     if (FieldGenerator.access$100(this.this$0) == null) FieldGenerator.access$200(this.this$0);
/* 392 */     if (FieldGenerator.access$300(this.this$0)) {
/* 393 */       FieldGenerator.access$200(this.this$0);
/* 394 */       FieldGenerator.access$400(this.this$0);
/*     */       
/* 396 */       g.drawImage(FieldGenerator.access$100(this.this$0), this.val$widthSlider.getMaximum() / 2 - FieldGenerator.access$100(this.this$0).getWidth() / 2 + 16, this.val$heightSlider.getMaximum() / 2 - FieldGenerator.access$100(this.this$0).getHeight() / 2 + 16, null);
/* 397 */       FieldGenerator.access$500(this.this$0);
/* 398 */       this.this$0.setGenerate(false);
/* 399 */     } else if (FieldGenerator.access$600(this.this$0)) {
/* 400 */       g.drawImage(FieldGenerator.access$100(this.this$0), this.val$widthSlider.getMaximum() / 2 - FieldGenerator.access$100(this.this$0).getWidth() / 2 + 16, this.val$heightSlider.getMaximum() / 2 - FieldGenerator.access$100(this.this$0).getHeight() / 2 + 16, null);
/* 401 */       this.this$0.setFlip(false);
/* 402 */     } else if (FieldGenerator.access$700(this.this$0)) {
/* 403 */       g.setColor(new Color(105, 105, 145));
/* 404 */       g.fill3DRect(x, y, getIconWidth(), getIconHeight(), true);
/* 405 */       FieldGenerator.access$702(this.this$0, false);
/*     */     } else {
/* 407 */       g.drawImage(FieldGenerator.access$100(this.this$0), this.val$widthSlider.getMaximum() / 2 - FieldGenerator.access$100(this.this$0).getWidth() / 2 + 16, this.val$heightSlider.getMaximum() / 2 - FieldGenerator.access$100(this.this$0).getHeight() / 2 + 16, null);
/*     */     }
/* 409 */     FieldGenerator.access$802(this.this$0, getIconWidth());
/* 410 */     FieldGenerator.access$902(this.this$0, getIconHeight());
/*     */   }
/*     */ }


/* Location:              C:\Users\iain\Desktop\Documents\landisk\programming\Iains Work Samples\simulator\project.jar!\FieldGenerator$1DynamicIcon.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */