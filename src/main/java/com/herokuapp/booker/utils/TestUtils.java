package com.herokuapp.booker.utils;

import com.herokuapp.booker.model.AuthPojo;
import com.herokuapp.booker.model.BookingPojo;

import java.util.Random;

public class TestUtils {

	public static String getRandomValue(){
		Random random = new Random();
		int randomInt = random.nextInt(100000);
		return Integer.toString(randomInt);
	}


	public static String jsonToString(Object objecet) {
		return jsonToString(objecet);

	}
}
