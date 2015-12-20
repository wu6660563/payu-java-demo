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
import com.payu.sdk.model.Order;

/**
 * 
 * @author NICK
 * 
 */
public class QueryByOrderId {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		payuInit("6u39nqhq8ftd0hlvnjfs66eh8c", "11959c415b33d0c", "500238", true); // Test
		Map<String, String> parameters = new HashMap<String, String>();

		parameters.put(PayU.PARAMETERS.ORDER_ID, "2637540");
		try {
			Order response = PayUReports.getOrderDetail(parameters);
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
