package threads.creation;

public class ThreadCreationExample {

	public static void main(String[] args) {
		//1.using Runnable
		Thread thread1=new Thread(new ThreadWork());
		thread1.start();
		//same as above but using lambda expression
		Thread thread2=new Thread(()->{
			System.out.println("thread run using lambda for : "+Thread.currentThread().getName());
		});
		thread2.start();
		//2.extending Thread class
		SomeThread thread3=new SomeThread();
		thread3.start();
		
	}
	public static class ThreadWork implements Runnable{

		@Override
		public void run() {
			System.out.println("inside run method of ThreadWork running by thread:"+Thread.currentThread().getName());
		}
		
	}
	
	public static class SomeThread extends Thread{
		@Override
		public void run(){
			System.out.println("inside thread run for "+this.getName());
		}
	}

}
