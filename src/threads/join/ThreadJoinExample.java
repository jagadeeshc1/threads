package threads.join;
import java.math.BigInteger;

public class ThreadJoinExample {
	public static void main(String[] args) {
		ThreadJoinExample c=new ThreadJoinExample();
		System.out.println(c.calculateResult(new BigInteger("3"), new BigInteger("4"), new BigInteger("2"), new BigInteger("5")));
		
	}
    public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) {
        BigInteger result;
        /*
            Calculate result = ( base1 ^ power1 ) + (base2 ^ power2).
            Where each calculation in (..) is calculated on a different thread
            since this calculations can take long time..they are run is different threads
        */
        PowerCalculatingThread t1=new PowerCalculatingThread(base1,power1);
        PowerCalculatingThread t2=new PowerCalculatingThread(base2,power2);
        t1.start();
        t2.start();
        
        try {
        	//main thread waiting for t1,t2 to finish the execution
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        //result is collected from both the threads and added for final result
       result=t1.getResult().add(t2.getResult());
        return result;
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;
    
        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }
    
        @Override
        public void run() {
           /*
           Implement the calculation of result = base ^ power
           */
        	for(BigInteger i=BigInteger.ZERO;i.compareTo(power)==-1;i=i.add(BigInteger.ONE)){
        		result=result.multiply(base);
        	}
        	
        }
    
        public BigInteger getResult() { return result; }
    }
}