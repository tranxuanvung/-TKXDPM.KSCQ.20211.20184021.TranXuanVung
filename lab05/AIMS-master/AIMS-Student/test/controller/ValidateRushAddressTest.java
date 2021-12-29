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
		ValidationController vali = new ValidationController();
		assertEquals(true, vali.validateAddress("Hà Nội"));
		assertEquals(true, vali.validateAddress("Đống Đa, Hà Nội"));
		assertEquals(true, vali.validateAddress("Quận 11, Hồ Chí Minh"));
		assertEquals(true, vali.validateAddress("Nhà số 76, Ngõ 77 Hồ Ba Mẫu, Phương liên, Đống Đa, Hà Nội"));
		assertEquals(false, vali.validateAddress("Mỹ Tiến, Mỹ Lộc, Nam Định"));
	}

}
