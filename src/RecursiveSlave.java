import java.math.BigInteger;

class RecursiveSlave implements Runnable{

	private BigInteger b;
	private int fst, lst;
	private RecursiveMaster master;

	public RecursiveSlave(int f, int l, RecursiveMaster m){
		fst = f;
		lst = l;
		b = new BigInteger(Integer.toString(fst));
		master = m;
	}

	public void run(){
		int i = fst+1;
		BigInteger one = new BigInteger("1");
		BigInteger bi = new BigInteger(Integer.toString(i));
		while(i <= lst){
			b = b.multiply(bi);
			i++;
			bi = bi.add(one);
		}
		reportToMaster();
	}

	public void reportToMaster(){
		master.report(b);
	}
}