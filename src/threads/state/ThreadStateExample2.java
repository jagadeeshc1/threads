package threads.state;

public class ThreadStateExample2 {
	int counter=0;
	public static void main(String[] args) throws InterruptedException {
		SharedClass sharedObject=new SharedClass();
		
		
		WorkerThread thread1=new WorkerThread(sharedObject);
		WorkerThread thread2=new WorkerThread(sharedObject);
		
		StateThread st1=new StateThread(thread1);
		StateThread st2=new StateThread(thread2);
		
		st1.start();
		st2.start();
		
		thread1.start();
		thread2.start();
		

		thread1.join();
		thread2.join();

		
		System.out.println(sharedObject.getCounter());
		
		
	}
	public static class SharedClass{
		int counter=0;
		public synchronized void increment(){
			for(int i=0;i<1000;i++){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				counter++;
			}
		}
		public int getCounter(){
			return counter;
		}
	}
	public static class WorkerThread extends Thread{
		SharedClass sharedObject;
		public WorkerThread(SharedClass sharedObject){
			this.sharedObject=sharedObject;
		}
		@Override
		public void run(){
			sharedObject.increment();
		}
	}
	public static class StateThread extends Thread{
		Thread thread;
		public StateThread(Thread thread){
			this.thread=thread;
		}
		@Override
		public void run(){
			while(thread.isAlive()){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(thread.getName()+" status -> "+thread.getState());
			}
		}
	}

}
