package org.test;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.payu.sdk.PayU;
import com.payu.sdk.PayUPayments;
import com.payu.sdk.exceptions.ConnectionException;
import com.payu.sdk.exceptions.InvalidParametersException;
import com.payu.sdk.exceptions.PayUException;
import com.payu.sdk.model.Currency;
import com.payu.sdk.model.Language;
import com.payu.sdk.model.PaymentCountry;
import com.payu.sdk.model.PaymentMethod;
import com.payu.sdk.model.TransactionResponse;

public class Card {

	public static void main(String[] args) {
		payuInit("6u39nqhq8ftd0hlvnjfs66eh8c", "11959c415b33d0c", "500238",
				true); // Test

		String reference = "comprame_java_test_00000008";
		String value = "10000";

		Map<String, String> parameters = new HashMap<String, String>();

		// Enter the account¡¯s identifier here.
		parameters.put(PayU.PARAMETERS.ACCOUNT_ID, "500538");
		// Enter the reference code here.
		parameters.put(PayU.PARAMETERS.REFERENCE_CODE, reference);
		// Enter the description here.
		parameters.put(PayU.PARAMETERS.DESCRIPTION, "nick_test_00000008");
		// Enter the transaction language here.
		parameters.put(PayU.PARAMETERS.LANGUAGE, "Language.es");

		// -- Values --
		// Enter the value here.
		parameters.put(PayU.PARAMETERS.VALUE, value);
		// Enter the currency here.
		parameters.put(PayU.PARAMETERS.CURRENCY, Currency.COP.name());

		// -- Buyer --
		// Enter the buyer Id here.
		parameters.put(PayU.PARAMETERS.BUYER_ID, "1");
		// Enter the buyer's name here.
		parameters.put(PayU.PARAMETERS.BUYER_NAME,
				"First name and second buyer  name");
		// Enter the buyer's email here.
		parameters.put(PayU.PARAMETERS.BUYER_EMAIL, "nick@comprame.com");
		// Enter the buyer's contact phone here.
		parameters.put(PayU.PARAMETERS.BUYER_CONTACT_PHONE, "7563126");
		// Enter the buyer's contact document here.
		parameters.put(PayU.PARAMETERS.BUYER_DNI, "5415668464654");
		// Enter the buyer's address here.
		parameters.put(PayU.PARAMETERS.BUYER_STREET, "calle 100");
		parameters.put(PayU.PARAMETERS.BUYER_STREET_2, "5555487");
		parameters.put(PayU.PARAMETERS.BUYER_CITY, "Medellin");
		parameters.put(PayU.PARAMETERS.BUYER_STATE, "Antioquia");
		parameters.put(PayU.PARAMETERS.BUYER_COUNTRY, "CO");
		parameters.put(PayU.PARAMETERS.BUYER_POSTAL_CODE, "000000");
		parameters.put(PayU.PARAMETERS.BUYER_PHONE, "7563126");

		// -- Payer --
		// Enter the payer's ID here.
		parameters.put(PayU.PARAMETERS.PAYER_ID, "1");
		// Enter the payer's name here.
		parameters.put(PayU.PARAMETERS.PAYER_NAME,
				"APPROVED");
		// Enter the payer's email here.
		parameters.put(PayU.PARAMETERS.PAYER_EMAIL, "nick@comprame.com");
		// Enter the payer's contact phone here.
		parameters.put(PayU.PARAMETERS.PAYER_CONTACT_PHONE, "7563126");
		// Enter the payer's contact document here.
		parameters.put(PayU.PARAMETERS.PAYER_DNI, "5415668464654");
		// Enter the payer's address here.
		parameters.put(PayU.PARAMETERS.PAYER_STREET, "calle 93");
		parameters.put(PayU.PARAMETERS.PAYER_STREET_2, "125544");
		parameters.put(PayU.PARAMETERS.PAYER_CITY, "Bogota");
		parameters.put(PayU.PARAMETERS.PAYER_STATE, "Bogota");
		parameters.put(PayU.PARAMETERS.PAYER_COUNTRY, "CO");
		parameters.put(PayU.PARAMETERS.PAYER_POSTAL_CODE, "000000");
		parameters.put(PayU.PARAMETERS.PAYER_PHONE, "7563126");

		// -- Credit card data --
		// Enter the number of the credit card here
		parameters.put(PayU.PARAMETERS.CREDIT_CARD_NUMBER, "4097440000000004");
		// Enter expiration date of the credit card here
		parameters.put(PayU.PARAMETERS.CREDIT_CARD_EXPIRATION_DATE, "2018/12");
		// Enter the security code of the credit card here
		parameters.put(PayU.PARAMETERS.CREDIT_CARD_SECURITY_CODE, "321");
		// Enter the name of the credit card here
		parameters.put(PayU.PARAMETERS.PAYMENT_METHOD, PaymentMethod.VISA
				.name());

		// Enter the number of installments here.
		parameters.put(PayU.PARAMETERS.INSTALLMENTS_NUMBER, "1");
		// Enter the name of the country here.
		parameters.put(PayU.PARAMETERS.COUNTRY, PaymentCountry.CO.name());

		// Session id del device.
		parameters.put(PayU.PARAMETERS.DEVICE_SESSION_ID,
				"vghs6tvkcle931686k1900o6e1");
		// Payer IP
		parameters.put(PayU.PARAMETERS.IP_ADDRESS, "127.0.0.1");
		// Cookie of the current session.
		parameters.put(PayU.PARAMETERS.COOKIE, "pt1t38347bs6jc9ruv2ecpv7o2");
		// User agent of the current session.
		parameters
				.put(PayU.PARAMETERS.USER_AGENT,
						"Mozilla/5.0 (Windows NT 5.1; rv:18.0) Gecko/20100101 Firefox/18.0");

		// "Authorization and capture" request
		try {
			TransactionResponse response = PayUPayments
					.doAuthorizationAndCapture(parameters);
			String string = JSONObject.fromObject(response).toString();
			System.out.println(string);
		} catch (PayUException e) {
			e.printStackTrace();
		} catch (InvalidParametersException e) {
			e.printStackTrace();
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
	}

	public static void payuInit(String apiKey, String apiLogin,
			String merchartId, Boolean isTest) {

		PayU.apiKey = apiKey; // Enter your ApiKey here.
		PayU.apiLogin = apiLogin; // Enter your apiLogin here.
		PayU.language = Language.en; // Enter the language you prefer here
		PayU.isTest = isTest; // Leave it true when
		// testing..
		PayU.merchantId = merchartId;
		if (isTest) {
			PayU.paymentsUrl = "https://stg.api.payulatam.com/payments-api/";
			PayU.reportsUrl = "https://stg.api.payulatam.com/reports-api/";
		} else {
			PayU.paymentsUrl = "https://api.payulatam.com/payments-api/";
			PayU.reportsUrl = "https://api.payulatam.com/reports-api/";
		}
	}

}
