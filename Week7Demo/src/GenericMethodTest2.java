import java.util.*;
class PerishableItem {
	public void show() {
		System.out.println("PerishableItem");
	}
	public String toString() { return "PerishableItem";}
	
}

class Fruit extends PerishableItem {
	
	public void show() {
		System.out.println("Fruit");
	}
	public String toString() { return "Fruit";}
}

class Flower extends PerishableItem {
		public void show() {
		System.out.println("Flower");
	}
	public String toString() { return "Flower";}
	
}
class Some{
	public String toString() { return "Some";}
}

class Basket<T> {
	List <T> itemList = new ArrayList<T>();
	
	public void add(T item) { itemList.add(item);}

	public void show(){
	
		for (T element:itemList ){
			System.out.println(element);
		}
		
	}

}

public class GenericMethodTest2
{

    public static void main( String args[] )
    {
        Basket<PerishableItem> basket = new Basket<PerishableItem>();
        basket.add(new PerishableItem());
        basket.add(new Flower());
        basket.add(new Fruit());
        //basket.add(new Some());
        basket.show();
        
        Basket<Fruit> fruitBasket = new Basket<Fruit>();
        
        //basket = fruitBasket;
        
        
        
        
        
    } 
}



