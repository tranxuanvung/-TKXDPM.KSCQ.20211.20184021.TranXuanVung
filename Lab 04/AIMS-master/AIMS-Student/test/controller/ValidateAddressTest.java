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
		assertEquals(false, placeOrderController.validateAddress(""));
		assertEquals(false, placeOrderController.validateAddress("      "));
		assertEquals(true, placeOrderController.validateAddress("Thon La Cho"));
		assertEquals(true, placeOrderController.validateAddress("Thon La Cho, Xa My Tien"));
		assertEquals(false, placeOrderController.validateAddress("@#Thon La Cho"));
	}

}
