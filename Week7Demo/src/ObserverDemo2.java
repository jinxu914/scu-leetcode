import java.util.*;
/* Demonstrates the observer pattern - Version 2 */
class Asset {};

/////////////////////////////////////////////////////////////////////////////
class Stock extends Asset 
{
	private String name;
	private double price;	
	private ObservableDelegate delegate; //Important ! 
	
	public Stock(String name, double price)
	{
		this.name = name;
		this.price = price;
		delegate = new ObservableDelegate();//define a delegate
	}
	
	public String toString(){return name;}
	
	public String getName(){return name;}
	public double getPrice(){return price;}
	
	public void setPrice(double price){
		
		this.price = price;
		
		delegate.setChanged();
		delegate.notifyObservers(new Double (price));			
		//only notify the observer about the "price" change
		
	}
	
	
	public Observable getObservable() { return delegate;}     //get the obserable by return the delegate defined above
	
	public void addObserver (Observer ob) { delegate.addObserver(ob);}   //add ob as one observer to delegate
	
			
}
/////////////////////////////////////////////////////////////////////////////
class ObservableDelegate extends java.util.Observable {
	
	/* Open up the access permissions of these methods
	 * Defined as protected methods in super class */
	
	public void clearChanged() { super.clearChanged();}
	
	public void setChanged() { super.setChanged();}
		
}
///////////////////////////////////////////////////////////////////////////////
class StockView implements Observer {
	
	private Stock stockToWatch;
	
	
	public StockView(Stock stock) {
		
		this.stockToWatch = stock;
		
		stock.addObserver(this);
			//add view1 as observer of stock1
	}	
	public void update (Observable ob,Object stockPrice ){ // Question stockPrice is price here, can be name or other things too
		
		String price = ((Double)stockPrice).toString();
		
		System.out.println("Stock price changed: " +price);
		//Q2 when the method "update" is called ?
	}	
	
}
/////////////////////////////////////////////////////////////////////////////
public class ObserverDemo2 {
	
	public static void main(String [] args){
	
		
		Stock stock1 = new Stock ("IBM",75.00);
		
		StockView view1 = new StockView(stock1);
		
		stock1.getObservable().addObserver(view1);				
		
		for (int i = 30; i < 100; i+=10){
			
			stock1.setPrice(i);
			//System.out.println("Stock price changed: ");
			
		}
		//view1.update(stock1.getObservable(),stock1.getPrice());
	}	

}
