package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidateAddressTest {

	private PlaceOrderController placeOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController();
	}

	@Test
	void test() {
		ValidationController vali = new ValidationController();
		assertEquals(false, vali.validateAddress(""));
		assertEquals(false, vali.validateAddress("      "));
		assertEquals(true, vali.validateAddress("Thon La Cho"));
		assertEquals(true, vali.validateAddress("Thon La Cho, Xa My Tien"));
		assertEquals(false, vali.validateAddress("@#Thon La Cho"));
	}

}
