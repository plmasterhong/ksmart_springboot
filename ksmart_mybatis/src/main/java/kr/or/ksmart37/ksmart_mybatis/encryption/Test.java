package kr.or.ksmart37.ksmart_mybatis.encryption;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor(); 
		pbeEnc.setAlgorithm("PBEWithMD5AndDES"); 
		pbeEnc.setPassword("ksmart37"); 
		String url = "jdbc:mysql://localhost:3306/ksmart37db?serverTimezone=UTC&characterEncoding=UTF8"; 
		String username = "root"; 
		String password = "java0000"; 
		System.out.println("기존 URL :: " + url + " | 변경 URL :: " + pbeEnc.encrypt(url)); 
		System.out.println("기존 username :: " + username + " | 변경 username :: " + pbeEnc.encrypt(username)); 
		System.out.println("기존 password :: " + password + " | 변경 password :: " + pbeEnc.encrypt(password));
		System.out.println(pbeEnc.decrypt("KteQ3Eh9PDTmJOS0jFMqrA=="));
		System.out.println(pbeEnc.decrypt("XZeoafDFft+Eur4PGPK5I6pgW3btnp/e"));
	}

}
