package threads.synchronize.multiplelocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultipleLocksExample {
	List<Integer> list1=new ArrayList<>();
	List<Integer> list2=new ArrayList<>();
	
	Object lock1=new Object();
	Object lock2=new Object();
	Random random=new Random();
	public void process(){
		for(int i=0;i<1000;i++){
			firstStep();
			secondStep();
		}
	}
	public  void firstStep(){
		synchronized(lock1){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list1.add(random.nextInt(100));
		}
		
		
	}
	public void secondStep(){
		synchronized(lock2){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list2.add(random.nextInt(100));
		}
		
	}
	public static void main(String[] args) {
		MultipleLocksExample m=new MultipleLocksExample();
		Thread thread1=new Thread(()->{
			m.process();
		});
		
		Thread thread2=new Thread(()->{
			m.process();
		});
		
		long start=System.currentTimeMillis();
		thread1.start();
		thread2.start();
		
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long end=System.currentTimeMillis();
		System.out.println("time taken: "+(end-start));
		
		System.out.println("list1 size: "+m.list1.size()+" list2 size: "+m.list2.size());
		
		
	}

}
