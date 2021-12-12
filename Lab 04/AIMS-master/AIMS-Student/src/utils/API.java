package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;

/**
 * The class provides methods to send requests to the server and receive data back
 * Date: 12/12/2021
 * @author:
 * @version 1.0
 */
public class API {


	/**
	 * Attribute helps to convert date in format
	 */
	public static DateFormat DATE_FORMATER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	/**
	 * properties that pass information to the console
	 */
	private static Logger LOGGER = Utils.getLogger(Utils.class.getName());

	/**
	 * Methods to send to APIs in the form of GET
	 * @param url : server path
	 * @param token : user authentication hash
	 * @return server respone
	 * @throws Exception
	 */
	public static String get(String url, String token) throws Exception {

		//Part 1: setup
		HttpURLConnection conn = setupConnection(url, "GET", token);

		// Part 2: read data respone
		String respone = readRespone(conn);

		return respone;
	}

	int var;

	/**
	 * Methods to send to APIs in the form of POST
	 * @param url : server path
	 * @param data : data sent to the server for processing (JSON)
	 * @return server respone
	 * @throws IOException
	 */
	public static String post(String url, String data
			, String token
	) throws IOException {
		//allow PATCH protocol
		allowMethods("PATCH");

		// Part 1: setup
		HttpURLConnection conn = setupConnection(url, "GET", token);

		// Part 2: send data
		Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		writer.write(data);
		writer.close();

		// Part 3: read data server respone
		String respone = readRespone(conn);

		return respone;
	}


	/**
	 * set connection to server
	 * @param url: server path
	 * @param method: protocol api
	 * @param token: user authentication hash
	 * @return connection
	 * @throws IOException
	 */
	private static HttpURLConnection setupConnection(String url, String method, String token) throws IOException {
		LOGGER.info("Request URL: " + url + "\n");
		URL line_api_url = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) line_api_url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod(method);
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", "Bearer " + token);
		return conn;
	}


	/**
	 * Method to read data server respone
	 * @param conn: connection to server
	 * @return respone
	 * @throws IOException
	 */
	private static String readRespone(HttpURLConnection conn) throws IOException {
		BufferedReader in;
		String inputLine;
		if(conn.getResponseCode() / 100 == 2) {
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder respone = new StringBuilder(); // su dung String Builder cho viec toi uu ve mat bo nho
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		respone.append(inputLine + "\n");
		in.close();
		LOGGER.info("Respone Info: " + respone.substring(0, respone.length() - 1).toString());
		return respone.substring(0, respone.length() - 1).toString();
	}


	/**
	 * The method allows calling different types of PI protocols such as PATCH, PUT, ...
	 * @deprecated only works with Java11
	 * @param methods
	 */
	private static void allowMethods(String... methods) {
		try {
			Field methodsField = HttpURLConnection.class.getDeclaredField("methods");
			methodsField.setAccessible(true);

			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

			String[] oldMethods = (String[]) methodsField.get(null);
			Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
			methodsSet.addAll(Arrays.asList(methods));
			String[] newMethods = methodsSet.toArray(new String[0]);

			methodsField.set(null/* static field */, newMethods);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}
}