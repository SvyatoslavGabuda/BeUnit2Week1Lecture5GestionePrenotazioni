package it.epicode.gp.auth.security;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Configuration;

import jakarta.persistence.AttributeConverter;

@Configuration
public class SecretCodeConverter implements AttributeConverter<String, String> {
	private static final String ALGORITHM = "AES/CFB/PKCS5Padding";
	private static final byte[] KEY = "MySUperSecretKey".getBytes();

	@Override
	public String convertToDatabaseColumn(String attribute) {
		Key key = new SecretKeySpec(KEY, "AES");
		try {
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.ENCRYPT_MODE, key);
			if (attribute == null) {
				return "";
			}
			return Base64.getEncoder().encodeToString((c.doFinal(attribute.getBytes())));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		Key key = new SecretKeySpec(KEY, "AES");
		System.out.println("ciao mondo");
		try {
			System.out.println("inizio decodifica");
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.DECRYPT_MODE, key);
			System.out.print("data" + dbData);
			if (dbData == null) {
				return "";
			}
			
			return new String(c.doFinal(Base64.getDecoder().decode(dbData)));

		} catch (Exception e) {
			System.out.println(dbData);
			System.out.println(e);
			throw new RuntimeException(e);
		}
	}
}
