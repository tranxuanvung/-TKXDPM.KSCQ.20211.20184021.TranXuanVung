package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidateRushAddressTest {

	private PlaceRushOrderController placeRushOrderController;
	@BeforeEach
	void setUp() throws Exception {
		placeRushOrderController = new PlaceRushOrderController();
	}

	@Test
	void test() {
		assertEquals(true, placeRushOrderController.validateAddress("Hà Nội"));
		assertEquals(true, placeRushOrderController.validateAddress("Đống Đa, Hà Nội"));
		assertEquals(true, placeRushOrderController.validateAddress("Quận 11, Hồ Chí Minh"));
		assertEquals(true, placeRushOrderController.validateAddress("Nhà số 76, Ngõ 77 Hồ Ba Mẫu, Phương liên, Đống Đa, Hà Nội"));
		assertEquals(false, placeRushOrderController.validateAddress("Mỹ Tiến, Mỹ Lộc, Nam Định"));
	}

}
