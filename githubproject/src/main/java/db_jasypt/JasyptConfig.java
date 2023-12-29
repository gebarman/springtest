package db_jasypt;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@Configuration //xml 대신하는 설정파일
@EnableEncryptableProperties //암호화, 복호화에 관련된 메서드를 갖고있는 메서드 입니다
public class JasyptConfig {
	
	@Autowired
	Environment environment;
	
	@Bean("jasyptEncryptor") //메서드의 결과로 나온 객체를 빈으로 만들기 대신 @Configuration 어노테이션이 있어야함
	public StringEncryptor stringEncryptor() {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(environment.getProperty("jasypt.encryptor.password"));
        //암호화 비번 1234 - 소스상에 기록 -> 다보이니까 외부환경설정변수값으로 읽어오자
        // 자바 클래스 실행 -환경변수 읽어서 실행
        //프로젝트 우클릭 run as => other => arguments => vm arguments에 적어주기 -Djasypt.encryptor.password=1234
        //이걸 갖다 쓸수있게 해주는 스프링 라이브러리(Environment)
        config.setAlgorithm("PBEWithMD5AndDES"); // 알고리즘
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        System.out.println("================================================");
        System.out.println(encryptor.decrypt("TTJGhAY0mofRbh0/CHm6S1/LE2sS3vH3a/NqVLc8zLbCjv0vS+4xxQ=="));
        System.out.println(encryptor.decrypt("FkERAfWcpZjLrOUG0LGdJshb8LPTfWo8AxXX4oUQpp4Yl7z0K89VvLL6VSpTiDaa"));
        System.out.println(encryptor.decrypt("ohqVrq9gmJKiLEkGnThz8w=="));
        System.out.println(encryptor.decrypt("/BIRQW8aqVb6vSeug+Y1OA=="));
        System.out.println("================================================");
        return encryptor;
	}
}
