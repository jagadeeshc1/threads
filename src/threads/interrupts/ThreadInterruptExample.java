package threads.interrupts;

public class ThreadInterruptExample {

	public static void main(String[] args) throws InterruptedException {
		WorkerThread workerThread=new WorkerThread();
		//this will run as daemon thread..means this thread won't be dead just because application is dead	
		//only way to kill is forcefully kill this daemon thread
		//workerThread.setDaemon(true);
		workerThread.start();
		Thread.sleep(1000);
		//this will interrupt WorkerThread and code inside catch block is executed..
		workerThread.interrupt();
		
		AnotherWorkerThread anotherWorkerThread=new AnotherWorkerThread();
		anotherWorkerThread.start();
		//this won't do anything since there is no operation to interrupt
		anotherWorkerThread.interrupt();
	
	}
	public static class WorkerThread extends Thread{
		@Override
		public void run(){
			try {
				Thread.sleep(1000000);
			} catch (InterruptedException e) {
				//this code should be used to release any locks or gracefully exit the thread
				System.out.println(this.getName()+" is interrupted");
				return;
			}
		}
	}
	
	public static class AnotherWorkerThread extends Thread{
		@Override
		public void run(){
			//this will keep on running forever ..till application is forcefully terminated
			while(true){
				System.out.println("inside anotherworker thread");
			}
		}
	}

}
