package org.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.payu.sdk.PayU;
import com.payu.sdk.PayUPayments;
import com.payu.sdk.exceptions.ConnectionException;
import com.payu.sdk.exceptions.InvalidParametersException;
import com.payu.sdk.exceptions.PayUException;
import com.payu.sdk.model.Bank;
import com.payu.sdk.model.Language;
import com.payu.sdk.model.PaymentCountry;
import com.payu.sdk.model.PaymentMethod;

public class PseBanks {
	
	public static void main(String[] args) {
		payuInit("6u39nqhq8ftd0hlvnjfs66eh8c", "11959c415b33d0c", "500238",
				false); // Test

		Map<String, String> parameters = new HashMap<String, String>();

		// Enter the payment method name here
		parameters
				.put(PayU.PARAMETERS.PAYMENT_METHOD, PaymentMethod.PSE.name());

		// Enter the name of the country here.
		parameters.put(PayU.PARAMETERS.COUNTRY, PaymentCountry.CO.name());

		// Obtain the payment receipt URL
		try {
			List<Bank> banks = PayUPayments.getPSEBanks(parameters);
			String string = JSONArray.fromObject(banks).toString();
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
