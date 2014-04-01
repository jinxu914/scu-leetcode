import java.util.Map;
import java.util.HashMap;

class CashBox {
  private Map<Integer,Integer> store = new HashMap<Integer,Integer>();
  
  public void setValue(int v) {
	  store.put(new Integer(v),new Integer(v));
  }
  public Integer getValue(int v) {
	  return store.remove(new Integer(v));
  }
}
class Setter implements Runnable {
  private CashBox cashBox;
  public Setter(CashBox cb) {
    cashBox = cb;
  }
  public void run() {
    int i=0;
    while(i<10) {
      synchronized(cashBox) {
        cashBox.setValue(i);
        System.out.println("Setter set value: >>>> "+i);
        try
        {
		//Makes this Thread to wait and release the lock associated
		//with this shared object within the synchronized
		//block
          cashBox.wait(); 
        }
        catch (InterruptedException ex)
        {
            ex.printStackTrace();
        }
      }
      i++;
    }
  }
}

class Getter implements Runnable {
	  private CashBox cashBox;
	  public Getter(CashBox cb) {
	    cashBox = cb;
	  }
	  public void run() {
	    int i=0;
	    while(i<10) {
	      synchronized(cashBox) { //tong bu 
	          Integer value = cashBox.getValue(new Integer(i));
	          if(value == null){
	            i--;
	          }
	          else {
	            System.out.println("Getter got value:---- "+value);
				//Notifies thread to wakeup and move to
				//runnable state by invoking notify method of the
				//shared synchronized instance of the CashBox.
	            cashBox.notify();
	          }
	      }
	      i++;
	    }
	  }
	}
public class MultiThreadTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CashBox cb = new CashBox();
	    Thread t1 = new Thread(new Setter(cb));    
	    Thread t2 = new Thread(new Getter(cb));
	    t1.start();
	    t2.start();
	  }
}
