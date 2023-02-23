package com.upfolio.upfolio;

import com.upfolio.upfolio.services.OTPCodeGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UpfolioApplicationTests {
	@Autowired
	private OTPCodeGenerator otpCodeGenerator;

	@Test
	void contextLoads() {
	}

	@Test
	void testOtpGenerator() {
		String code1 = otpCodeGenerator.generateCode();

		assertEquals(4, code1.length());

		String code2 = otpCodeGenerator.generateCode();
		String code3 = otpCodeGenerator.generateCode();
		System.out.println(code1);
		System.out.println(code2);
		System.out.println(code3);
	}
}
