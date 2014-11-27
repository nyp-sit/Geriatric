package entity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


public class TestDigitalSignature {
	
	
	public static void main(String[]args) throws NoSuchAlgorithmException, NoSuchProviderException, IOException, InvalidKeySpecException{
		TestDigitalSignature.generateAndStoreKeys();
		KeyPair loadedKeyPair = TestDigitalSignature.retrieveAndLoadKeys();
		TestDigitalSignature text = new TestDigitalSignature();
		System.out.println("============================================================================");
		text.dumpKeyPair(loadedKeyPair);
	}
	
	
	
	public static void generateAndStoreKeys() throws IOException, NoSuchAlgorithmException, NoSuchProviderException{
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
		keyGen.initialize(1024, random);
		KeyPair pair = keyGen.generateKeyPair();
			
		String path = "C:\\Project\\";
		
		
		PrivateKey privateKey = pair.getPrivate();
		PublicKey publicKey = pair.getPublic();
		System.out.println(privateKey);
		System.out.println(publicKey);
 
		// Store Public Key.
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
		FileOutputStream fos = new FileOutputStream(path + "public.key");
		fos.write(x509EncodedKeySpec.getEncoded());
		//fos.write(publicKey.toString());
		fos.close();
 
		// Store Private Key.
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
		fos = new FileOutputStream(path + "private.key");
		fos.write(pkcs8EncodedKeySpec.getEncoded());
		//fos.write(privateKey.toString());
		fos.close();
	}
	
	public static KeyPair retrieveAndLoadKeys() throws InvalidKeySpecException, IOException, NoSuchAlgorithmException{
		String path = "C:\\Project\\";
		
		// Read Public Key.
				File filePublicKey = new File(path + "public.key");
				FileInputStream fis = new FileInputStream(path + "public.key");
				byte[] encodedPublicKey = new byte[(int) filePublicKey.length()];
				fis.read(encodedPublicKey);
				fis.close();
		 
				// Read Private Key.
				File filePrivateKey = new File(path + "private.key");
				fis = new FileInputStream(path + "private.key");
				byte[] encodedPrivateKey = new byte[(int) filePrivateKey.length()];
				fis.read(encodedPrivateKey);
				fis.close();
		 
				// Generate KeyPair.
				KeyFactory keyFactory = KeyFactory.getInstance("DSA");
				X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(
						encodedPublicKey);
				PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
		 
				PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(
						encodedPrivateKey);
				PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
		 
				return new KeyPair(publicKey, privateKey);
	}
	
	private String getHexString(byte[] b) {
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}
	private void dumpKeyPair(KeyPair keyPair) {
		PublicKey pub = keyPair.getPublic();
		System.out.println("Public Key: " + getHexString(pub.getEncoded()));
 
		PrivateKey priv = keyPair.getPrivate();
		System.out.println("Private Key: " + getHexString(priv.getEncoded()));
	}
	
	
}
