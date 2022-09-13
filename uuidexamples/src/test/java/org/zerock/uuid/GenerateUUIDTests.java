package org.zerock.uuid;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.junit.Test;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class GenerateUUIDTests {
	
	
	//================ UUID versions ================//
//	•1 Time-based UUID : 시간을 기반으로 UUID 생성
//	•2 DCE security UUID 
//	•3 Name-based UUID 
//	•4 Randomly generated UUID 
	//===============================================//
	
	
	@Test
	public void testUUIDVersion1() {
		log.debug("testUUIDVersion1() invoked.");
		
		UUID uuid = UUIDGenerator.generateType1UUID();
		
		log.info("\t+ uuid: " + uuid.toString());
		log.info("\t+ version: " + uuid.version());
	} // testUUIDVersion1
	
	// ====================================================================
	
	@Test
	public void testUUIDVersion3() throws UnsupportedEncodingException {	// From namespace + name
		log.debug("testUUIDVersion3() invoked.");
		
		// + 이때 namespace는 마음대로 지정해도 되지만, 길이는 반드시 32자여야 한다.
		String namespace = "12345678901234567890123456789012";
		String name = "myapp";
		
		assert namespace.length() == 32;
		
		UUID uuid = UUIDGenerator.generateType3UUID(namespace, name);
		
		log.info("\t+ uuid: " + uuid.toString());
		log.info("\t+ version: " + uuid.version());
	} // testUUIDVersion3
	
	// ====================================================================
	
	@Test
	public void testUUIDVersion4() {
		log.debug("testUUIDVersion4() invoked.");
		
		UUID uuid = UUIDGenerator.generateType4UUID();
		// + generateType4UUID에서는 UUID uuid = UUID.randomUUID()로 구한다.
		
		log.info("\t+ uuid: " + uuid.toString());
		log.info("\t+ version: " + uuid.version());
	} // testUUIDVersion4
	
	// ====================================================================
	
	@Test
	public void testUUIDversion5() throws UnsupportedEncodingException {
		log.debug("testUUIDversion5() invoked.");
		
		// + 이때 namespace는 마음대로 지정해도 되지만, 길이는 반드시 32자여야 한다.
		String namespace = "12345678901234567890123456789012";
		String name = "myapp";
		
		assert namespace.length() == 32;
		
		UUID uuid = UUIDGenerator.generateType5UUID(namespace, name);
		
		log.info("\t+ uuid: " + uuid.toString());
		log.info("\t+ version: " + uuid.version());
	} // testUUIDversion5
	
	// ====================================================================
	
	@Test
	public void testUUIDAndMessageDigest() // (****)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		log.debug("testUUIDAndMessageDigest() invoked.");
		
		String uuid = UUIDGenerator.generateUniqueKeysWithUUIDAndMessageDigest();
		
		log.info("\t+ uuid: " + uuid);
	} // testUUIDAndMessageDigest
	
	// ====================================================================

} // end class
