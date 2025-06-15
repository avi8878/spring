package com.avi.batch;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@Slf4j
class BatchApplicationTests {

	@Test
	void testSayHello() {
      String result = "Avinash";
      log.info("Testing ended sayHello method");
	  assertEquals("Avinash", result);
	  log.info("Testing ended sayHello method");
	}
}


