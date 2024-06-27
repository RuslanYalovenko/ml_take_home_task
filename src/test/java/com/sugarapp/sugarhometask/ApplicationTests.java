package com.sugarapp.sugarhometask;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ApplicationTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationTests.class);

	@Test
	void contextLoads() {
	}

//	@Autowired
//	private KafkaTemplate<String, UserLocation> kafkaTemplate;
//
//	@Test
//	public void send() {
//		kafkaTemplate.send("location-weather", new UserLocation(
//				"1",
//				new Location("52.5162731", "13.3571059"),
//				System.currentTimeMillis()
//		));
//	}

}
