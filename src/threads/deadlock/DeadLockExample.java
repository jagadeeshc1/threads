package threads.deadlock;

import java.util.Random;

public class DeadLockExample {

	public static void main(String[] args) {
		Intersection intersection=new Intersection();
		TrainA trainA=new TrainA(intersection);
		TrainB trainB=new TrainB(intersection);
		trainA.start();
		trainB.start();
	}
	public static class TrainA extends Thread{
		Intersection intersection;
		Random random=new Random();
		public TrainA(Intersection intersection){
			this.intersection=intersection;
		}
		@Override
		public void run(){
			while(true){
				try {
					Thread.sleep(random.nextInt(10));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				intersection.takeRoadA();
			}
		}
	}
	
	public static class TrainB extends Thread{
		Intersection intersection;
		Random random=new Random();
		public TrainB(Intersection intersection){
			this.intersection=intersection;
		}
		@Override
		public void run(){
			while(true){
				try {
					Thread.sleep(random.nextInt(10));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				intersection.takeRoadB();
			}
		}
	}
	public static class Intersection{
		public Object roadA=new Object();
		public Object roadB=new Object();
		
		public void takeRoadA(){
			synchronized (roadA) {
				System.out.println(Thread.currentThread().getName()+"obtained lock on roadA");
				synchronized (roadB) {
					System.out.println(Thread.currentThread().getName()+"obatined lock on roadB");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		public void takeRoadB(){
			synchronized (roadB) {
				System.out.println(Thread.currentThread().getName()+"obtained lock on roadB");
				synchronized (roadA) {
					System.out.println(Thread.currentThread().getName()+"obatined lock on roadA");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
