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

public class BankPayments {

	public static void main(String[] args) {
		payuInit("6u39nqhq8ftd0hlvnjfs66eh8c", "11959c415b33d0c", "500238",
				false); // Test

		String reference = "comprame_java_test_00000007";
		String value = "10000";

		Map<String, String> parameters = new HashMap<String, String>();

		// Enter the account¡¯s identifier here.
		parameters.put(PayU.PARAMETERS.ACCOUNT_ID, "500538");
		// Enter the reference code here.
		parameters.put(PayU.PARAMETERS.REFERENCE_CODE, reference);
		// Enter the description here.
		parameters.put(PayU.PARAMETERS.DESCRIPTION, "payment test");
		// Enter the transaction language here.
		parameters.put(PayU.PARAMETERS.LANGUAGE, Language.es.name());

		// -- Values --
		// Enter the value here.
		parameters.put(PayU.PARAMETERS.VALUE, value);
		// Enter the currency here.
		parameters.put(PayU.PARAMETERS.CURRENCY, Currency.COP.name());

		// Enter the buyer's email here.
		parameters.put(PayU.PARAMETERS.BUYER_EMAIL, "nick@comprame.com");

		// Enter the payer's name here.
		parameters.put(PayU.PARAMETERS.PAYER_NAME, "nick wu");

		// Enter the cash payment method name here.
		parameters.put(PayU.PARAMETERS.PAYMENT_METHOD, "BANK_REFERENCED");
		parameters.put(PayU.PARAMETERS.PAYER_DNI, "1111111111111");

		// Enter the name of the country here.
		parameters.put(PayU.PARAMETERS.COUNTRY, PaymentCountry.CO.name());

		// Enter the expiration date here.
		parameters.put(PayU.PARAMETERS.EXPIRATION_DATE, "2015-12-23T00:00:00");

		// Payer IP
		parameters.put(PayU.PARAMETERS.IP_ADDRESS, "127.0.0.1");
		parameters.put(PayU.PARAMETERS.COOKIE, "pt1t38347bs6jc9ruv2ecpv7o2");

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
		PayU.paymentsUrl = "https://stg.api.payulatam.com/payments-api/";
		PayU.reportsUrl = "https://stg.api.payulatam.com/reports-api/";

		// PayU.paymentsUrl = "https://api.payulatam.com/payments-api/";
		// PayU.reportsUrl = "https://api.payulatam.com/reports-api/";
	}

}
