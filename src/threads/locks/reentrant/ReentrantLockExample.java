package threads.locks.reentrant;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

	public static void main(String[] args) throws InterruptedException {
		SharedClass sharedObject=new SharedClass();
		
		Thread incrementingThread=new Thread(()->{
			for(int i=0;i<100000;i++)
				sharedObject.increment();
				
		});
		Thread decrementingThread=new Thread(()->{
			for(int i=0;i<100000;i++)
				sharedObject.decrement();
		});
		incrementingThread.start();
		decrementingThread.start();
		
		incrementingThread.join();
		decrementingThread.join();
		
		System.out.println(sharedObject.getCount());

	}
	public static class SharedClass{
		private int count=0;
		private ReentrantLock lock=new ReentrantLock();
		public void increment(){
			lock.lock();
			count++;
			if(count==50){
				System.out.println("decrementing thread you're screwed..LOL");
				return ;
			}
			lock.unlock();
		}
		public void decrement(){
			lock.lock();
			count--;
			lock.unlock();
		}
		public int  getCount(){
			return count;
		}
	}
}
