package threads.synchronize;

public class RyanAndMonicaJob implements Runnable{
	BankAccount account=new BankAccount();
	public static void main(String[] args) {
		Runnable threadJob=new RyanAndMonicaJob();
		Thread ryan=new Thread(threadJob);
		Thread monica=new Thread(threadJob);
		ryan.setName("ryan");
		monica.setName("monica");
		ryan.start();
		monica.start();
	}

	@Override
	public void run() {
		for(int i=0;i<10;i++){
			makeWithdrawl(10);
			if(account.getBalance()<0){
				System.out.println("-----overdrawn--------");
			}
		}
	}

	private void makeWithdrawl(int amount) {
		if(account.getBalance()>=amount){
			System.out.println(Thread.currentThread().getName()+" is about to withdraw");
			
			try {
				int balance=account.getBalance();
				System.out.println(Thread.currentThread().getName()+" is about to go to sleep");
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+" woke up");
			account.withdraw(amount);
			System.out.println(Thread.currentThread().getName()+"completed withdrawl");
		}
		else{
			System.out.println("sorry not enough balance for "+Thread.currentThread().getName());
		}
		
	}

}
