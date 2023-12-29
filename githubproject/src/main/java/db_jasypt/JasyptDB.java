package db_jasypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class JasyptDB {
	public static void main(String[] args) {
		StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
		jasypt.setPassword("1234");
		String driver = jasypt.encrypt("com.mysql.cj.jdbc.Driver");
		String url = jasypt.encrypt("jdbc:mysql://localhost:3306/backenddb");
		String username = jasypt.encrypt("backend");
		String password = jasypt.encrypt("backend");
		System.out.println("com.mysql.cj.jdbc.Driver: " + driver);
		System.out.println(url);
		System.out.println(username);
		System.out.println(password);
/*		
		ENC(com.mysql.cj.jdbc.Driver: TTJGhAY0mofRbh0/CHm6S1/LE2sS3vH3a/NqVLc8zLbCjv0vS+4xxQ==)
		ENC(FkERAfWcpZjLrOUG0LGdJshb8LPTfWo8AxXX4oUQpp4Yl7z0K89VvLL6VSpTiDaa)
		ENC(ohqVrq9gmJKiLEkGnThz8w==)
		ENC(/BIRQW8aqVb6vSeug+Y1OA==)
*/
	}
}
/*
 * 1. pom.xml 라이브러리 추가
 * 2. driver, url, username, password 암호화
 * 3. application.properties 파일
 * 예)spring.datasource.driver-class-name = ENC(TTJGhAY0mofRbh0/CHm6S1/LE2sS3vH3a/NqVLc8zLbCjv0vS+4xxQ==)
 */
