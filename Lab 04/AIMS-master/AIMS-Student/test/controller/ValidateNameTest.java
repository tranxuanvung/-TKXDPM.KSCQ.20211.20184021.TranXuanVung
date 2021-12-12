package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidateNameTest {

	private PlaceOrderController placeOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController();
	}

	@Test
	void test() {
		assertEquals(false, placeOrderController.validateName(""));
		assertEquals(false, placeOrderController.validateName("      "));
		assertEquals(true, placeOrderController.validateName("Tran Xuan Vung"));
		assertEquals(false, placeOrderController.validateName("1Tran Xuan"));
		assertEquals(false, placeOrderController.validateName("%Tran"));
	}

}
