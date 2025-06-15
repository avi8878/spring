package com.avi.batch;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.assertEquals;

@SpringBootTest
class BatchApplicationTests {

	@Test
	void testSayHello() {
		String result = "Avinash";
		assertEquals("Avinash", result);
	}
}


