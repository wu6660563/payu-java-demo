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
import com.payu.sdk.model.DocumentType;
import com.payu.sdk.model.Language;
import com.payu.sdk.model.PaymentCountry;
import com.payu.sdk.model.PaymentMethod;
import com.payu.sdk.model.PersonType;
import com.payu.sdk.model.TransactionResponse;

public class BankTransferPSE {
	
	public static void main(String[] args) {
		payuInit("6u39nqhq8ftd0hlvnjfs66eh8c", "11959c415b33d0c", "500238",
				false); // Test
		
		String reference = "comprame_java_test_00000009";
		String value= "10000";

		Map<String, String> parameters = new HashMap<String, String>();

		// Enter the account¡¯s identifier here.
		parameters.put(PayU.PARAMETERS.ACCOUNT_ID, "500538");
		// Enter the reference code here.
		parameters.put(PayU.PARAMETERS.REFERENCE_CODE, reference);
		// Enter the description here.
		parameters.put(PayU.PARAMETERS.DESCRIPTION, reference);
		// Enter the transaction language here.
		parameters.put(PayU.PARAMETERS.LANGUAGE, Language.es.name());

		// -- Values --
		// Enter the value here.
		parameters.put(PayU.PARAMETERS.VALUE, value);	
		// Enter the currency here.
		parameters.put(PayU.PARAMETERS.CURRENCY, Currency.COP.name());

		//Enter the buyer's email here.
		parameters.put(PayU.PARAMETERS.BUYER_EMAIL, "nick@comprame.com");

		// -- Payer --
		//Enter the payer's name here.
		parameters.put(PayU.PARAMETERS.PAYER_NAME, "nick wu");
		//Enter the payer's email here.
		parameters.put(PayU.PARAMETERS.PAYER_EMAIL, "nick@comprame.com");
		//Enter the payer's contact phone here.
		parameters.put(PayU.PARAMETERS.PAYER_CONTACT_PHONE, "7563126");	

		//-- Mandatory information for PSE ¨C
		// Enter the bank PSE code here.
		parameters.put(PayU.PARAMETERS.PSE_FINANCIAL_INSTITUTION_CODE, "1022");
		// Enter the person type here (Natural or legal).
		parameters.put(PayU.PARAMETERS.PAYER_PERSON_TYPE, PersonType.NATURAL.toString() );	
		//o parameters.put(PayU.PARAMETERS.PAYER_PERSON_TYPE, PersonType.LEGAL.toString() );	
		//Enter the payer's contact document here.
		parameters.put(PayU.PARAMETERS.PAYER_DNI, "123456789");	
		// Enter the payer¡¯s document type here.
		parameters.put(PayU.PARAMETERS.PAYER_DOCUMENT_TYPE, DocumentType.CC.toString());
		// Payer IP
		parameters.put(PayU.PARAMETERS.IP_ADDRESS, "127.0.0.1");

		// Enter the payment method name here. 
		parameters.put(PayU.PARAMETERS.PAYMENT_METHOD, PaymentMethod.PSE.name());

		// Enter the name of the country here.
		parameters.put(PayU.PARAMETERS.COUNTRY, PaymentCountry.CO.name());

		// Cookie of the current session.
		parameters.put(PayU.PARAMETERS.COOKIE, "pt1t38347bs6jc9ruv2ecpv7o2");
		// User agent of the current session.
		parameters.put(PayU.PARAMETERS.USER_AGENT, "Mozilla/5.0 (Windows NT 5.1; rv:18.0) Gecko/20100101 Firefox/18.0");

		//Response page to which the payer will be redirected.
		parameters.put(PayU.PARAMETERS.RESPONSE_URL, "http://www.test.com/response");

		// "Authorization and capture" request
		try {
			TransactionResponse response = PayUPayments.doAuthorizationAndCapture(parameters);
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
