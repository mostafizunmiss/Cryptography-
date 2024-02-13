package aesInterface;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import aes.*;
import inverseaes.*;

public class AesInterface extends JFrame {
	
	private final JLabel plainLabel=new JLabel("Enter Your Plaintext: ");
	protected final JTextField plainTextField=new JTextField(20);
	private JLabel cipherLabel;
	private JLabel decipherLabel;
	private final JButton cipherButton;
	private final JButton decipherButton;
	
	
	private String cipherText="";
	private String outputCipher="";
	private String plaintext="";
	private String outputDecipherTextInHex="";
	private int len;
	
	
	public AesInterface()
	{
		super("Abvanced Encryption Standard (AES):");
		
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,300);
		setVisible(true);
		
		plainLabel.setLabelFor(plainTextField);
		plainLabel.setHorizontalAlignment(JLabel.LEFT);
		
		add(plainLabel);
		//plainTextField.setBounds(200,150,100,100);
	
		plainTextField.setVisible(true);
		plainTextField.setEditable(true);
		add(plainTextField);
		
		cipherButton=new JButton("Encrypt");
		cipherLabel=new JLabel();
		decipherButton=new JButton("Decrypt");
		decipherLabel=new JLabel();
		
		add(cipherButton);
		add(cipherLabel);
		add(decipherButton);
		add(decipherLabel);
		
		
		cipherButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					final String plainText=plainTextField.getText();
					System.out.println(plainText);
					String hexPlainText=Convertion.asciiToHex(plainText);
					System.out.println("Hex PlainText: \n"+hexPlainText);
					
					 len=hexPlainText.length()+32-(hexPlainText.length()%32);
					int zeroCount=len-hexPlainText.length()-2;
					String zeroCountStr;
					if(zeroCount<10)
						zeroCountStr="0"+Integer.toString(zeroCount);
					else
						zeroCountStr=Integer.toString(zeroCount);
					StringBuilder sb=new StringBuilder(hexPlainText);
					
					for(int i=hexPlainText.length();i<len-2;i++)
						{
							sb.insert(i, "0");
						}
					
					
					hexPlainText=sb.toString().concat(zeroCountStr);
					System.out.println(hexPlainText);
					
					//substring+forloop
					
					
					for(int i=0; i<len;i=i+32)
					{
						//System.out.println(i);
						String hexSubPlainText=hexPlainText.substring(i, i+32);
						//System.out.println(hexSubPlainText);
						cipherText=Encryption.encrypt(hexSubPlainText);
						outputCipher+=cipherText;
//						System.out.println("Cipher Text: ");
//						System.out.println(cipherText);
						
					}
					
					cipherLabel.setText(outputCipher);//change
				} catch (Exception e2) {
					
					JOptionPane.showMessageDialog(null, "TextArea not get", "Eroor",JOptionPane.ERROR_MESSAGE);
					
				}
				
			}
		});
		
		
		decipherButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					 String encryptedText=outputCipher;
					 
					 for(int i=0;i<len;i+=32)
					 {
						 String subCipherText=encryptedText.substring(i, i+32);
						 String subPlianText=Decryption.decrypt(subCipherText);
						 outputDecipherTextInHex+=subPlianText;
					 }
					 
//					 System.out.println("After decipher:\n"+outputDecipherTextInHex);
//					 System.out.println("length is :"+outputDecipherTextInHex.length());
					 
					
					 String howManyZero=outputDecipherTextInHex.substring(outputDecipherTextInHex.length()-2, outputDecipherTextInHex.length());
					//System.out.println(howManyZero);
					 int numberOfZero=Integer.parseInt(howManyZero);
					// System.out.println(numberOfZero);
					 int index=outputDecipherTextInHex.length()-1-numberOfZero-2;
					// System.out.println(index);
					 String decipherTextInHex=outputDecipherTextInHex.substring(0, index+1);
					 String plainTextFinal=Convertion.hexToASCII(decipherTextInHex);
					// System.out.println("Ascii :\n"+plainTextnotFinal);
					decipherLabel.setText(plainTextFinal);
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "TextArea not get", "Eroor",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	public static void main(String[] args) {
		new AesInterface().setVisible(true);//counter er value non static korte hobe
		
		
//		String plainText="Thats my Kung Fu";
//		System.out.println(plainText);
//		String plainTextInHex=Convertion.asciiToHex(plainText);
//		System.out.println(plainTextInHex);
//		plainText=Convertion.hexToASCII(plainTextInHex);
//		System.out.println(plainText);
//		
//		byte[] testByte=Convertion.hexStringToByteArray(plainTextInHex);
//		for(int i=0;i<testByte.length;i++)
//			System.out.printf("%d ",testByte[i]);
//		plainTextInHex=Convertion.byteArrayToHexString(testByte);
//		System.out.println(plainTextInHex);
//		
//		System.out.println();
//		byte[][] test2DByte=Convertion.hexStringTo2DByteArray(plainTextInHex);
//		for(int i=0; i<4;i++)
//			{for(int j=0;j<4;j++)
//				System.out.printf("%d ",test2DByte[i][j]);
//			System.out.println();
//			}
//		
//		
//		System.out.println();		
//		plainTextInHex=Convertion.twoDbyteArrayToHexString(test2DByte);
//		System.out.println(plainTextInHex);
//		
//		System.out.println();
//		test2DByte=Convertion.hexStringTo2DByteArray(plainTextInHex);
//		for(int i=0; i<4;i++)
//		{for(int j=0;j<4;j++)
//			System.out.printf("%d ",test2DByte[i][j]);
//		System.out.println();
//		}
//	
		
		
//		String cipherTextTest=Encryption.encrypt();
//		System.out.println("Tested CipherText: ");
//		System.out.println(cipherTextTest);
//		
//		String plainTextTest=Decryption.decrypt();
//		System.out.println("Tested DeciphedText: ");
//		System.out.println(plainTextTest);
	}

}
