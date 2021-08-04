package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest; // library used for object
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}
}

@RestController
class ServerController {
	private static String byteArray2Hex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		return formatter.toString();
	}

	@RequestMapping("/hash")
	public String myHash() throws NoSuchAlgorithmException { //
		String data = "Hello World Check Sum!";  // hello , that gets encrypted

		MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");

		//this encrypts the message with hash using sha 512
		data = messageDigest.digest(data.getBytes()).toString(); //  method  then parsed toString to upload it


		return "<p>data:" + data + "</p><br><p>Name of Cipher Algorithm used: SHA-512 CheckSum Value::" + byteArray2Hex(messageDigest.digest(data.getBytes())) + "</p>";
	}
}
//FIXME: Add route to enable check sum return of static data example:  String data = "Hello World Check Sum!";