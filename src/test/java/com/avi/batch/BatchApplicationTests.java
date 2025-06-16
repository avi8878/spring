package com.avi.batch;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.assertEquals;

@RunWith(org.springframework.test.context.junit4.SpringRunner.class)
class BatchApplicationTests {
	private static final Logger log = LoggerFactory.getLogger(BatchApplicationTests.class);
	@Test
	void testSayHello() {
      String result = "Avinash";
      log.info("Testing ended sayHello method");
	  assertEquals("Avinash", result);
	  log.info("Testing ended sayHello method");
	}
}


