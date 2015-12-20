package org.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

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
public class QueryByReferenceCode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		payuInit("6u39nqhq8ftd0hlvnjfs66eh8c", "11959c415b33d0c", "500238",
//				true); // Test
		
		payuInit("2abqui9uo35c2r1qt8mugjql2b", "66cf39423a4416c", "507494",
				false);
		
		Map<String, String> parameters = new HashMap<String, String>();

		parameters.put(PayU.PARAMETERS.REFERENCE_CODE, "production_100037098");
		try {
			List<Order> orderList  = PayUReports.getOrderDetailByReferenceCode(parameters);
			String string = JSONArray.fromObject(orderList).toString();
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
