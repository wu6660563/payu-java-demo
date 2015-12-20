package org.test;

import java.util.Calendar;

import com.payu.sdk.PayU;
import com.payu.sdk.PayUPayments;
import com.payu.sdk.exceptions.ConnectionException;
import com.payu.sdk.exceptions.PayUException;
import com.payu.sdk.model.Language;

/**
 * 查看是否能够PING通Payu服务器
 * 
 * @author NICK
 * @version 1.0
 * 
 */
public class Ping {

	public static void main(String[] args) {
		Long startTime = Calendar.getInstance().getTime().getTime();
		try {
			PingPayu("2abqui9uo35c2r1qt8mugjql2b", "66cf39423a4416c", "507494",
					false);// 正式

			PingPayu("6u39nqhq8ftd0hlvnjfs66eh8c", "11959c415b33d0c", "500238",
					true); // Test

			Long endTime = Calendar.getInstance().getTime().getTime();
			System.out.println(endTime - startTime);
		} catch (PayUException e) {
			e.printStackTrace();
		} catch (ConnectionException e) {
			e.printStackTrace();
		}

	}

	public static Boolean PingPayu(String apiKey, String apiLogin,
			String merchartId, Boolean isTest) throws PayUException,
			ConnectionException {
		Boolean flag = false;

		PayU.apiKey = apiKey; // Enter your ApiKey here.
		PayU.apiLogin = apiLogin; // Enter your apiLogin here.
		PayU.language = Language.en; // Enter the language you prefer here
		PayU.isTest = isTest; // Leave it true when
		// testing..
		PayU.merchantId = merchartId;
		// Include it only if you want to see all the trace of the log; if you
		// want to see the response only, you can delete it.
		// Include it only if you want to test a specific payment server, and
		// display the route of the same.
		if (isTest) {
			PayU.paymentsUrl = "https://stg.api.payulatam.com/payments-api/";
			// Include it only if you want to test it in a specific query
			// server,
			// and indicate the path of the same.
			PayU.reportsUrl = "https://stg.api.payulatam.com/reports-api/";
		} else {
			PayU.paymentsUrl = "https://api.payulatam.com/payments-api/";
			// Include it only if you want to test it in a specific query
			// server,
			// and indicate the path of the same.
			PayU.reportsUrl = "https://api.payulatam.com/reports-api/";
		}

		flag = PayUPayments.doPing();
		System.out.println(flag);
		return flag;
	}

}
