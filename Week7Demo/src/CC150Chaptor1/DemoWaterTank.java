package CC150Chaptor1;
import java.util.*;

public class DemoWaterTank {
  public static void main(String [] args) {
        Tank tank = new Tank(10);
        LevelMonitor monitor = new LevelMonitor(5,tank);
        for (int i = 0; i < 10; ++i){
        	tank.flowOut(); 
        }  
  }
}
//////////////////////////////////////////////////
class LevelMonitor implements Observer {    

    int criticalLevel = 0;
    Tank tank;
    public LevelMonitor (int level,Tank t){
        this.criticalLevel = level;
        tank = t;
        tank.addObserver(this);
    }
    public LevelMonitor(){}

    public void shutOffTank(){
        tank.stop();
    }    
    public void update (Observable ob, Object ob1) {
        int currentlevel = tank.getLevel();      
        if (currentlevel <= criticalLevel) {
            System.out.println("Critical level reached, shutting off tank; "+currentlevel);
            shutOffTank();
        }
    }
}
///////////////////////////////////////////////////////
class Tank extends Observable {
    int currentLevel;
    boolean shutOff = false;

    Tank(int c){currentLevel = c;}
    Tank(){}

    public void flowOut(){
    	 if (!shutOff){
         --currentLevel;
         setChanged();
         notifyObservers();
      }
    }
    public int getLevel() { return currentLevel;}
    public void stop(){
        shutOff = true;
    }
}

