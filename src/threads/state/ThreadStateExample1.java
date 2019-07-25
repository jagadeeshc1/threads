package threads.state;

public class ThreadStateExample1 {

	public static void main(String[] args) throws InterruptedException {
		Runnable runnable=new SomeWork();
		Thread t1=new Thread(runnable);
		System.out.println(t1.getState());
		
		t1.start();
		System.out.println(t1.getState());
		Thread.sleep(1000);
		System.out.println(t1.getState());
		t1.join();
		System.out.println(t1.getState());
	
		
	}
	public static class SomeWork implements Runnable{

		@Override
		public void run() {
			for(int i=0;i<10;i++){
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
		
	}

}
