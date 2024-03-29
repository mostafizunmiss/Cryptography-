package inverseaes;

public class InverseShiftRows {
	
	private byte[][] shiftedBytes=new byte[4][4];
		
	
	public byte[][] invShiftRows(byte[][] unShiftedBytes)
	{
		int nb=4;
		
		//copy
		
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
				this.shiftedBytes[i][j]=unShiftedBytes[i][j];
		
		//shifting
		for(int row=1;row<4;row++)
			for(int col=0;col<nb;col++)
			{
				this.shiftedBytes[row][(col+shift(row,nb))%nb]=unShiftedBytes[row][col];
				
			}	
		
		
		return this.shiftedBytes;
		
	}//ShiftRows
	
	private int shift(int r, int n)
	{
		return r;
	}
	

}
