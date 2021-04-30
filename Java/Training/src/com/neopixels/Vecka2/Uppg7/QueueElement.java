package V2.Uppg7;

public class  QueueElement {
   protected String text;
   protected int prio;

   QueueElement(String o,int prio) {
     this.text = o;
     this.prio = prio;

   }
   
   public String getText(){
       return text;
   }

   public int getPrio(){return prio;}
 }
