package threads.synchronize;


public class RaceConditionExample {

	public static void main(String[] args) throws InterruptedException {
		InventoryCounter inventoryCounter=new InventoryCounter();
		IncrementingThread incrementingThread=new IncrementingThread(inventoryCounter);
		DecrementingThread decrementingThread=new DecrementingThread(inventoryCounter);
		incrementingThread.start();		
		decrementingThread.start();
		incrementingThread.join();
		decrementingThread.join();
		System.out.println("number of items in the inventory: "+inventoryCounter.getItems());
	}
	public static class IncrementingThread extends Thread{
		private InventoryCounter inventoryCounter;
		public IncrementingThread(InventoryCounter inventoryCounter){
			this.inventoryCounter=inventoryCounter;
		}
		@Override
		public void run(){
			for(int i=0;i<1000;i++){
				inventoryCounter.increment();
			}
		}
	}
	public static class DecrementingThread extends Thread{
		private InventoryCounter inventoryCounter;
		public DecrementingThread(InventoryCounter inventoryCounter){
			this.inventoryCounter=inventoryCounter;
		}
		@Override
		public void run(){
			for(int i=0;i<1000;i++){
				inventoryCounter.decrement();
			}
		}
	}
	public static class InventoryCounter{
		private int itemCount=0;
		public synchronized void increment(){
			int ic=itemCount;
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			itemCount=ic+1;
			//itemCount++;
		}
		public synchronized void decrement(){
			int ic=itemCount;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			itemCount=ic-1;
		}
		public int getItems(){
			return itemCount;
		}
	}
}
