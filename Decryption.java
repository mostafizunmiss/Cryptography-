package inverseaes;

import aes.Convertion;
import aes.KeyExpands;

public class Decryption {
	
	private static byte[][] invAddedRoundKey=new byte[4][4];
	private static byte[][] invShiftedByteArray=new byte[4][4];
	private static byte[][] invSubstitutedArray=new byte[4][4];
	private static byte[][] beforeInvMixArray=new byte[4][4];
	private static byte[][] beforeShiftedArray=new byte[4][4];
	private static byte[][] beforeSubbedArray=new byte[4][4];
	

	public static String decrypt(String d) {
		
		String inputKey="2b7e151628aed2a6abf7158809cf4f3c";
		byte[] inputByte=Convertion.hexStringToByteArray(inputKey);
		InverseKeyExpansion invKey=new InverseKeyExpansion(inputByte);
		
		
		String ciperTextinHex=d;//"1a97b1923491ea41638ddf559435ab2e";
		
		byte[][] byteToDecrypt=Convertion.hexStringTo2DByteArray(ciperTextinHex);
		
		String testByte=Convertion.twoDbyteArrayToHexString(byteToDecrypt);
		System.out.println(testByte);
		
		InverseMixColumns inverseMixcolumn=new InverseMixColumns();
		
		
	//Inverse IP
		
		//Inverse AddRoundKey
		
		
		
		InverseAddRoundKey invAddRoundKey=new InverseAddRoundKey();
		
		 invAddedRoundKey=invAddRoundKey.addRoundKey(byteToDecrypt, invKey, 10);
		
		String outputAddedRoundKey=Convertion.twoDbyteArrayToHexString(invAddedRoundKey);
		System.out.println("Decrypt After initial added key i.e: the last key: ");
		System.out.println(outputAddedRoundKey);
		
		
		//Inverse ShiftRows
		
		InverseShiftRows invShift=new InverseShiftRows();
		
		invShiftedByteArray=invShift.invShiftRows(invAddedRoundKey);
		
		String outputInvShited=Convertion.twoDbyteArrayToHexString(invShiftedByteArray);
		System.out.println("Decrypt After inverse shiftRows ");
		System.out.println(outputInvShited);
		
		//Inverse Substitution
		
		InverseSubBytes isb=new InverseSubBytes();
		invSubstitutedArray=isb.invSubByteArray(invShiftedByteArray);
		
		String outputInvSubbed=Convertion.twoDbyteArrayToHexString(invSubstitutedArray);
		System.out.println("Decrypt After inverse substituted ");
		System.out.println(outputInvSubbed);
		
		
		
		
		
		int cycle=9;
		
		do {
		
		invAddedRoundKey=invAddRoundKey.addRoundKey(invSubstitutedArray, invKey, cycle);
		
//		String outputAddedRoundKey1=Convertion.twoDbyteArrayToHexString(invAddedRoundKey);
//		System.out.println("Decrypt After 10th round added key i.e: the last key: ");
//		System.out.println(outputAddedRoundKey1);
		
		//inverse Mixcolumns test
		
		
		beforeInvMixArray=inverseMixcolumn.invMixColumns(invAddedRoundKey);
		
//		String outputInvMix=Convertion.twoDbyteArrayToHexString(beforeInvMixArray);
//		System.out.println("Decryption: Before InvMixColumns ByteArray was");
//		System.out.println(outputInvMix);
//		
		
		beforeShiftedArray=invShift.invShiftRows(beforeInvMixArray);
		
		beforeSubbedArray=isb.invSubByteArray(beforeShiftedArray);
		
		invSubstitutedArray=beforeSubbedArray;
		
		cycle=cycle-1;
		
		}while(cycle>=1);
		
		
		invAddedRoundKey=invAddRoundKey.addRoundKey(invSubstitutedArray, invKey, 0);
		
		String output=Convertion.twoDbyteArrayToHexString(invAddedRoundKey);
		System.out.println("Final Decrypted: hexaMessage: ");
		System.out.println(output);
		
		return output;
		

	}

}
