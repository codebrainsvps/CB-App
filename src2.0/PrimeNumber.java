import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;


public class PrimeNumber {
	public static void main(String ...arg)
	{
		ArrayList<BigInteger> prime1=new ArrayList<BigInteger>();
		ArrayList<BigInteger> xorval=new ArrayList<BigInteger>();
		
	    Random random=new Random();
		String msg="hi this is testing process";
		for(int i=0;i<msg.length();i++)
		{
			
			BigInteger prime=BigInteger.probablePrime(8,random);
			System.out.println("prime:"+prime);
			prime1.add(prime);
			
		}
		for(int j=0;j<msg.length();j++)
		{
			char ch=msg.charAt(j);
			int val=(int)ch;
			BigInteger bigIntValue = new BigInteger(Integer.toString(val));
			BigInteger primeval=prime1.get(j);
			BigInteger xor=bigIntValue.xor(primeval);
			xorval.add(xor);
			//System.out.println(xor);
			
		}
		String levelone="";
		for(int k=0;k<xorval.size();k++)
		{
			BigInteger bi=xorval.get(k);
			int ascii=bi.intValue();
			String binary=Integer.toBinaryString(ascii);
			System.out.println("binary:"+binary);
			for(int l=binary.length();l<8;l++)
			{
				binary='0'+binary;
				
			}
			for (int l = 0; l <binary.length()/2; l++)
			{

		        String a = binary.substring(2*l,(l+1)*2);
		        if(a.equalsIgnoreCase("01"))
		        {
		        	levelone+='a';
		        }
		        else if(a.equalsIgnoreCase("00"))
		        {
		        	levelone+='b';
		        }
		        else if(a.equalsIgnoreCase("10"))
		        {
		        	levelone+='c';
		        }
		        else 
		        {
		        	levelone+='d';
		        }
		        
		        System.out.println("levelone:"+levelone);
		    }
			String cipher="";
			for (int l = 0; l <levelone.length()/2; l++)
			{

		        String a = levelone.substring(2*l,(l+1)*2);
		        if(a.equalsIgnoreCase("aa"))
		        {
		        	cipher+='e';
		        }
		        else if(a.equalsIgnoreCase("ab"))
		        {
		        	cipher+='f';
		        }
		        else if(a.equalsIgnoreCase("ac"))
		        {
		        	cipher+='g';
		        }
		        else if(a.equalsIgnoreCase("ad"))
		        {
		        	cipher+='h';
		        }
		        else if(a.equalsIgnoreCase("bb"))
		        {
		        	cipher+='i';
		        }
		        else if(a.equalsIgnoreCase("bc"))
		        {
		        	cipher+='j';
		        }
		        else if(a.equalsIgnoreCase("bd"))
		        {
		        	cipher+='k';
		        }
		        else if(a.equalsIgnoreCase("cc"))
		        {
		        	cipher+='l';
		        }
		        else if(a.equalsIgnoreCase("cd"))
		        {
		        	cipher+='m';
		        }
		        else if(a.equalsIgnoreCase("dd"))
		        {
		        	cipher+='n';
		        }
		        else if(a.equalsIgnoreCase("ba"))
		        {
		        	cipher+='o';
		        }
		        else if(a.equalsIgnoreCase("ca"))
		        {
		        	cipher+='p';
		        }
		        else if(a.equalsIgnoreCase("da"))
		        {
		        	cipher+='q';
		        }
		        else if(a.equalsIgnoreCase("cb"))
		        {
		        	cipher+='r';
		        }
		        else if(a.equalsIgnoreCase("db"))
		        {
		        	cipher+='s';
		        }
		       
		       
		        else 
		        {
		        	cipher+='t';
		        }
		        
		        
		        
		        
		    }
			System.out.println("level two:"+cipher);
			
			
			
			
		}
		
	}

}
