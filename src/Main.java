public class Main
{
	public static void main(String[] s) throws Exception{
		try{
			int i = Integer.parseInt(s[0]);
			if(i < 0) throw new NumberFormatException();
			new RecursiveMaster(i).run();
		}
		catch(NumberFormatException e){
			System.out.println("malformed number supplied");
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("calculating 150!");
			new RecursiveMaster(150).run();
		}
	}
}