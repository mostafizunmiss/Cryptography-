package inverseaes;


import aes.Convertion;

public class InverseAddRoundKey {
	
	
	
	public byte[][] addRoundKey(byte[][] unaddedBytes, InverseKeyExpansion key, int round)
	{
		
		
		byte[][] temp=new byte[4][4];
		byte[][] resultAddRound=new byte[4][4];
		
		//key is copying to temp
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
			{
				temp[j][i]=key.word[j][round*4+i];
				//System.out.printf("%d ",temp[i][j]);
			}
		
		System.out.println("Printed Key for round: "+round);
		String testaddkey=Convertion.twoDbyteArrayToHexString(temp);
		System.out.println(testaddkey);
		//key XORing with unaddedBytes
		
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
			{
				resultAddRound[j][i]=(byte) (unaddedBytes[j][i]^temp[j][i]);
			}
		
		
		return resultAddRound;
	}

}
