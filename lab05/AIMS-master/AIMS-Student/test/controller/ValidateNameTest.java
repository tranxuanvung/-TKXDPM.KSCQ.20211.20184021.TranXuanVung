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
		ValidationController vali = new ValidationController();
		assertEquals(false, vali.validateName(""));
		assertEquals(false, vali.validateName("      "));
		assertEquals(true, vali.validateName("Tran Xuan Vung"));
		assertEquals(false, vali.validateName("1Tran Xuan"));
		assertEquals(false, vali.validateName("%Tran"));
	}

}
