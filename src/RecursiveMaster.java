import java.math.BigInteger;

class RecursiveMaster{

	private BigInteger b;
	private int jobs;
	private int count;
	private int number;
	private long start;

	public RecursiveMaster(int i){
		b = new BigInteger("1");
		jobs = Runtime.getRuntime().availableProcessors();
		count = 0;
		number = i;
		start = 0;
	}

	public void run(){
		start = System.currentTimeMillis();
		//enshures a minimal work for a job
		if(jobs*10 > number) jobs = number/10;
		if(jobs < 1) jobs = 1;
		int[] steps = steps();
		for(int i = 0; i < jobs; i++){
			if(i == 0) createSlave(1,steps[i]); 
			else createSlave(steps[i-1]+1, steps[i]);
		}
	}

	public synchronized void report(BigInteger i){
		b = b.multiply(i);
		count++;
		try{
			Thread.sleep(1);
		}
		catch(InterruptedException e){}
		if(count >= jobs){
			done();
		}
	}

	public void done(){
		long end = System.currentTimeMillis();
		//System.out.println(b);
		//System.out.println(b.toString().length());
		System.out.println("used " + jobs + " threads");
		System.out.println(end-start + " ms elapsed during calculation");
	}

	public int[] steps(){
		int[] s = new int[jobs];
		int step = number/jobs;
		for(int i = 1; i <= jobs; i++){
			if(i == jobs) s[i-1] = number;
			else s[i-1] = i*step; 
		}
		return s;
	}

	public void createSlave(int f, int l){
		Thread t = new Thread(new RecursiveSlave(f, l, this));
		t.start();
	}
}