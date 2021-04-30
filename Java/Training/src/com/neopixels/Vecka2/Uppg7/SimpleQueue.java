package V2.Uppg7;

import java.util.*;

 public class SimpleQueue {
   private List<QueueElement> l = new ArrayList<QueueElement>(); 

   public int size() {  // ger antalet element i kön
     return l.size();
   }

   public synchronized void put(QueueElement obj) {
     System.out.println("Putting "+ obj.getText());
     int i;
     l.add(obj);
     l.sort(Comparator.comparing(QueueElement::getPrio));
     notify();
     
   }

   public synchronized QueueElement take() { // tar ut första elementet
     while (l.isEmpty())
       try {
         wait();
       }
       catch (InterruptedException e) { 
         return null;
       }
     QueueElement e = (QueueElement) l.get(0);
     l.remove(0);
     return e;
   }
   
   public void printQueue(){
       l.forEach(o -> System.out.println(o.getText()));
   }
 }

