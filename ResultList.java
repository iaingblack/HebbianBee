/*     */ import java.util.StringTokenizer;
/*     */ import javax.swing.JOptionPane;
/*     */ 
/*     */ public final class ResultList
/*     */ {
/*     */   private Result data;
/*     */   private Result lastResult;
/*     */   private Object[][] resultList;
/*     */   
/*     */   public ResultList(int trials, int cycles)
/*     */   {
/*  12 */     this.resultList = new Object[trials][cycles];
/*  13 */     this.lastResult = null;
/*     */   }
/*     */   
/*     */   public final void clear(int trials, int cycles)
/*     */   {
/*  18 */     this.resultList = ((Object[][])null);
/*  19 */     this.resultList = new Object[trials][cycles];
/*     */   }
/*     */   
/*     */   public final boolean loadResults(String res)
/*     */   {
/*  24 */     boolean loadOK = false;
/*  25 */     if (res != "")
/*     */     {
/*  27 */       String results = res.substring(0, res.length() - 2);
/*  28 */       StringTokenizer st = new StringTokenizer(results, ":");
/*  29 */       String fileType = st.nextToken();
/*     */       
/*  31 */       if (fileType.equals("RESULTS"))
/*     */       {
/*  33 */         String piece = st.nextToken();
/*  34 */         int numberOfTrials = Integer.parseInt(piece);
/*     */         
/*  36 */         piece = st.nextToken();
/*  37 */         int numberOfCycles = Integer.parseInt(piece);
/*     */         
/*     */ 
/*  40 */         this.resultList = ((Object[][])null);
/*  41 */         this.resultList = new Object[numberOfTrials][numberOfCycles];
/*     */         
/*     */ 
/*     */ 
/*  45 */         while (st.hasMoreTokens())
/*     */         {
/*     */ 
/*  48 */           piece = st.nextToken();
/*     */           
/*  50 */           int trial = Integer.parseInt(piece);
/*     */           
/*  52 */           piece = st.nextToken();
/*     */           
/*  54 */           int cycle = Integer.parseInt(piece);
/*     */           
/*  56 */           piece = st.nextToken();
/*     */           
/*  58 */           double reward = Double.parseDouble(piece);
/*     */           
/*  60 */           piece = st.nextToken();
/*     */           
/*  62 */           String color = piece;
/*     */           
/*  64 */           piece = st.nextToken();
/*     */           
/*  66 */           double wb = Double.parseDouble(piece);
/*     */           
/*  68 */           piece = st.nextToken();
/*     */           
/*  70 */           double wy = Double.parseDouble(piece);
/*     */           
/*  72 */           piece = st.nextToken();
/*     */           
/*  74 */           int blue = Integer.parseInt(piece);
/*     */           
/*  76 */           piece = st.nextToken();
/*     */           
/*  78 */           int yellow = Integer.parseInt(piece);
/*     */           
/*     */ 
/*  81 */           add(reward, color, wb, wy, trial, cycle, blue, yellow);
/*     */         }
/*  83 */         loadOK = true;
/*     */       }
/*  85 */       else if (fileType.equals("FIELD")) {
/*  86 */         JOptionPane.showMessageDialog(null, "This is a file of FIELD settings", "Field File", 1);
/*  87 */       } else if (fileType.equals("EDITMODEL")) {
/*  88 */         JOptionPane.showMessageDialog(null, "This is an EDIT MODEL settings file", "Edit Model File", 1);
/*     */       } else {
/*  90 */         JOptionPane.showMessageDialog(null, "This File is Not For Use With The Simulator", "Invalid File", 1);
/*     */       }
/*     */     }
/*  93 */     return loadOK;
/*     */   }
/*     */   
/*     */   public int getNumberOfResults()
/*     */   {
/*  98 */     Result temp = null;
/*  99 */     int counter = 0;
/* 100 */     for (int i = 0; i < this.resultList.length; i++) {
/* 101 */       for (int j = 0; j < this.resultList[i].length; j++) {
/* 102 */         temp = (Result)this.resultList[i][j];
/* 103 */         if (temp != null) counter++;
/*     */       }
/*     */     }
/* 106 */     return counter;
/*     */   }
/*     */   
/*     */   public final int getTrials() {
/* 110 */     return this.resultList.length;
/*     */   }
/*     */   
/*     */   public final int getCycles() {
/* 114 */     return this.resultList[0].length;
/*     */   }
/*     */   
/*     */   private Result getResult(int tr, int cy)
/*     */   {
/* 119 */     int trials = tr;
/* 120 */     trials--;
/* 121 */     int cycles = cy;
/* 122 */     cycles--;
/*     */     
/* 124 */     if (this.resultList[trials][cycles] != null) {
/* 125 */       this.data = ((Result)this.resultList[trials][cycles]);
/* 126 */       return this.data;
/*     */     }
/* 128 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final String getResultsAsCSV()
/*     */   {
/* 138 */     StringBuffer csvData = new StringBuffer();
/*     */     
/*     */ 
/* 141 */     String csv = "";
/* 142 */     csvData.append("");
/*     */     
/* 144 */     Result data = getArrayResult(0, 0);
/* 145 */     csvData.append(data.getCSVHeader());
/*     */     
/* 147 */     for (int trials = 0; trials < this.resultList.length; trials++) {
/* 148 */       for (int cycles = 0; cycles < this.resultList[trials].length; cycles++) {
/* 149 */         data = getArrayResult(trials, cycles);
/* 150 */         csvData.append(data.getTrialNum());
/* 151 */         csvData.append(",");
/* 152 */         csvData.append(data.getCycleNum());
/* 153 */         csvData.append(",");
/* 154 */         csvData.append(data.getReward());
/* 155 */         csvData.append(",");
/* 156 */         csvData.append(data.flowerColor());
/* 157 */         csvData.append(",");
/* 158 */         csvData.append(data.wbWeight());
/* 159 */         csvData.append(",");
/* 160 */         csvData.append(data.wyWeight());
/* 161 */         csvData.append(",");
/* 162 */         csvData.append(data.getBlueLands());
/* 163 */         csvData.append(",");
/* 164 */         csvData.append(data.getYellowLands());
/* 165 */         csvData.append("\n");
/*     */       }
/*     */     }
/* 168 */     csv = csvData.toString();
/* 169 */     return csv;
/*     */   }
/*     */   
/*     */   public final Result getArrayResult(int tr, int cy)
/*     */   {
/* 174 */     int trials = tr;
/* 175 */     int cycles = cy;
/*     */     
/* 177 */     if (this.resultList[trials][cycles] != null) {
/* 178 */       this.data = ((Result)this.resultList[trials][cycles]);
/* 179 */       return this.data;
/*     */     }
/* 181 */     return null;
/*     */   }
/*     */   
/*     */   public final void add(double reward, String colour, double wb, double wy, int trialNum, int cycleNum, int blueLands, int yellowLands)
/*     */   {
/* 186 */     this.data = new Result(reward, colour, wb, wy, trialNum, cycleNum, blueLands, yellowLands);
/* 187 */     this.resultList[(trialNum - 1)][(cycleNum - 1)] = this.data;
/* 188 */     this.lastResult = this.data;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void showResults()
/*     */   {
/* 195 */     System.out.println(getResults());
/*     */   }
/*     */   
/*     */   public final String getResults()
/*     */   {
/* 200 */     String allResults = "";
/*     */     
/* 202 */     int num = 0;
/*     */     
/* 204 */     for (int i = 0; i < this.resultList.length; i++) {
/* 205 */       for (int j = 0; j < this.resultList[i].length; j++) {
/* 206 */         this.data = ((Result)this.resultList[i][j]);
/* 207 */         if (this.data != null) {
/* 208 */           String line = this.data.asString();
/* 209 */           allResults = allResults + "Result -  " + num++ + " = " + line + "\n";
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 214 */     return allResults;
/*     */   }
/*     */   
/*     */   public boolean isFull()
/*     */   {
/* 219 */     boolean full = true;
/* 220 */     for (int i = 0; i < this.resultList.length; i++) {
/* 221 */       for (int j = 0; j < this.resultList[0].length; j++)
/*     */       {
/* 223 */         if (this.resultList[i][j] == null)
/*     */         {
/* 225 */           full = false;
/*     */         }
/*     */       }
/*     */     }
/* 229 */     return full;
/*     */   }
/*     */   
/*     */   public final boolean isEmpty()
/*     */   {
/* 234 */     if (this.resultList[0][0] == null) {
/* 235 */       return true;
/*     */     }
/* 237 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final String getLastResult()
/*     */   {
/* 251 */     if (this.lastResult != null) {
/* 252 */       return this.lastResult.getFormattedResultString();
/*     */     }
/* 254 */     return "No last result found";
/*     */   }
/*     */ }


/* Location:              C:\Users\iain\Desktop\Documents\landisk\programming\Iains Work Samples\simulator\project.jar!\ResultList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */