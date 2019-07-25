package threads.synchronize;

public class VolatileUseCase {

	public static void main(String[] args) {
		SharedClass sharedClass=new SharedClass();
		Thread incrementingThread=new Thread(()->{
			for(int i=0;i<Integer.MAX_VALUE;i++){
				sharedClass.increment();
			}
		});
		
		Thread raceConditionCheckThread=new Thread(()->{
			for(int i=0;i<Integer.MAX_VALUE;i++)
				sharedClass.checkRaceCondition();
		});
		incrementingThread.start();
		raceConditionCheckThread.start();
	}
	public static class SharedClass{
		private volatile int x=0;
		private volatile int y=0;
		public void increment(){
			x++;
			y++;
		}
		public void checkRaceCondition(){
			if(y>x){
				System.out.println("y> x..race condition happened");
			}
		}
	}

}
