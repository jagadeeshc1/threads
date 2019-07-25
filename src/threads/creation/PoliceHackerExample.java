package threads.creation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PoliceHackerExample {
	public static final int MAX_PASSWORD=9999;
	public static void main(String[] args) {
		Random random=new Random();
		Vault vault=new Vault(random.nextInt(1000));
		List<Thread> threads=new ArrayList<>();
		threads.add(new AscendingHackerThread(vault));
		threads.add(new DescendingHackerThread(vault));
		threads.add(new PoliceThread());
		
		threads.forEach(thread->thread.start());
	}
	public static class Vault{
		private int password;
		public Vault(int password){
			this.password=password;
		}
		public boolean isCorrectPassword(int guess){
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return guess==password;
		}
	}
	
	public static abstract class HackerThread extends Thread{
		protected Vault vault;
		public HackerThread(Vault vault){
			this.vault=vault;
			this.setName(this.getClass().getSimpleName());
			this.setPriority(MAX_PRIORITY);
		}
		@Override
		public void start(){
			System.out.println("Starting the thread: "+this.getName());
			super.start();
		}
	}
	public static class AscendingHackerThread extends HackerThread{
		public AscendingHackerThread(Vault vault){
			super(vault);
		}
		@Override
		public void run(){
			for(int guess=0;guess<=MAX_PASSWORD;guess++){
				if(vault.isCorrectPassword(guess)){
					System.out.println(this.getName()+"found the password"+guess);
					System.exit(0);
				}
			}
		}
	}
	public static class DescendingHackerThread extends HackerThread{
		public DescendingHackerThread(Vault vault){
			super(vault);
		}
		@Override
		public void run(){
			for(int guess=MAX_PASSWORD;guess>0;guess--){
				if(vault.isCorrectPassword(guess)){
					System.out.println(this.getName()+"found the password"+guess);
					System.exit(0);
				}
			}
		}
	}
	public static class PoliceThread extends Thread{
		@Override
		public void run(){
			for(int i=10;i>0;i--){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(i);
			}
			System.out.println("you can't break this ...");
			System.exit(0);
		}
	}

}
