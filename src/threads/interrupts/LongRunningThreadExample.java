package threads.interrupts;

import java.math.BigInteger;

public class LongRunningThreadExample {

	public static void main(String[] args) {
		Thread longComputingThread=new Thread(new LongComputingTask(new BigInteger("2000000"),new BigInteger("5000000")));
		longComputingThread.setDaemon(true);
		longComputingThread.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//longComputingThread.interrupt();
		
	}
	public static class LongComputingTask implements Runnable{
		private BigInteger base;
		private BigInteger power;
		public LongComputingTask(BigInteger base,BigInteger power){
			this.base=base;
			this.power=power;
		}
		@Override
		public void run() {
			System.out.println(base+"^"+power+"="+pow(base,power));
		}
		public BigInteger pow(BigInteger  base,BigInteger power){
			BigInteger result=BigInteger.ONE;
			for(BigInteger i=BigInteger.ZERO;i.compareTo(power)!=0;i=i.add(BigInteger.ONE)){
				if(Thread.currentThread().isInterrupted()){
					System.out.println("longcomputing thread prematurely exiting...");
					return BigInteger.ZERO;
				}
				result=result.multiply(base);
			}
			return result;
		}
	}
}	
