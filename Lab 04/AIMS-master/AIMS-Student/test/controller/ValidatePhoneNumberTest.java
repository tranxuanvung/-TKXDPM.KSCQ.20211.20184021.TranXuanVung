package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import controller.PlaceOrderController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidatePhoneNumberTest {

	private PlaceOrderController placeOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController();
	}

	@Test
	public void test() {
		//boolean isValided = placeOrderController.validatePhoneNumber("1123456789");
		assertEquals(false, placeOrderController.validatePhoneNumber("1123456789"));
		assertEquals(true, placeOrderController.validatePhoneNumber("0123456789"));
		assertEquals(false, placeOrderController.validatePhoneNumber("11234567"));
		assertEquals(false, placeOrderController.validatePhoneNumber("112345678967867"));
	}
}
