package org.test;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.payu.sdk.PayU;
import com.payu.sdk.PayUReports;
import com.payu.sdk.exceptions.ConnectionException;
import com.payu.sdk.exceptions.InvalidParametersException;
import com.payu.sdk.exceptions.PayUException;
import com.payu.sdk.model.Language;
import com.payu.sdk.model.TransactionResponse;

/**
 * 
 * @author NICK
 * 
 */
public class QueryByTransactionId {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		payuInit("2abqui9uo35c2r1qt8mugjql2b", "66cf39423a4416c", "507494",
				false);
		Map<String, String> parameters = new HashMap<String, String>();

		parameters.put(PayU.PARAMETERS.TRANSACTION_ID,
				"32100b48-98f5-4b79-b15a-7d644308d14a");
		TransactionResponse response;
		try {
			response = PayUReports.getTransactionResponse(parameters);
			String string = JSONObject.fromObject(response).toString();
			System.out.println(string);
		} catch (PayUException e) {
			e.printStackTrace();
		} catch (ConnectionException e) {
			e.printStackTrace();
		} catch (InvalidParametersException e) {
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
