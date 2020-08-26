package com.thecodevillage.hostelreserve.mpesa.service;

import com.thecodevillage.hostelreserve.mpesa.models.MpesaSTKPushReq;
import com.thecodevillage.hostelreserve.mpesa.repository.MpesaSTKPushReqRepository;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class MpesaServiceImpl implements MpesaService {
    private static final Logger log = LoggerFactory.getLogger(MpesaServiceImpl.class);
    String stk_app_key = "you app key";
    String stk_app_secret = "your app secret";
    private MpesaSTKPushReqRepository mpesaSTKPushReqRepository;


    @Autowired
    public MpesaServiceImpl(MpesaSTKPushReqRepository mpesaSTKPushReqRepository) {
        this.mpesaSTKPushReqRepository = mpesaSTKPushReqRepository;
    }

    private String authenticate(String appKey, String appSecret) {
        try{
            String appKeySecret = appKey + ":" + appSecret;
            byte[] bytes = appKeySecret.getBytes("ISO-8859-1");
            String encoded = Base64.getEncoder().encodeToString(bytes);

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials")
                    .get()
                    .addHeader("authorization", "Basic "+encoded)
                    .addHeader("cache-control", "no-cache")
                    .build();
            Response response = client.newCall(request).execute();
            //log.info("Response##"+response.body().string());
            String res = response.body().string();
            JSONObject jsonObject=new JSONObject(res);
            log.info(jsonObject.getString("access_token"));
            return jsonObject.getString("access_token");
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public String STKPushSimulation(String businessShortCode,
                                    String password,
                                    String timestamp,
                                    String transactionType,
                                    String amount,
                                    String phoneNumber,
                                    String partyA,
                                    String partyB,
                                    String callBackURL,
                                    String queueTimeOutURL,
                                    String accountReference,
                                    String transactionDesc) {
        try{
            JSONArray jsonArray=new JSONArray();
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("BusinessShortCode", businessShortCode);
            jsonObject.put("Password", password);
            jsonObject.put("Timestamp", timestamp);
            jsonObject.put("TransactionType", transactionType);
            jsonObject.put("Amount",amount);
            jsonObject.put("PhoneNumber", phoneNumber);
            jsonObject.put("PartyA", partyA);
            jsonObject.put("PartyB", partyB);
            jsonObject.put("CallBackURL", callBackURL);
            jsonObject.put("AccountReference", accountReference);
            jsonObject.put("QueueTimeOutURL", queueTimeOutURL);
            jsonObject.put("TransactionDesc", transactionDesc);
            jsonArray.put(jsonObject);
            String requestJson=jsonArray.toString().replaceAll("[\\[\\]]","");
            OkHttpClient client = new OkHttpClient();
            String url="https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest";
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, requestJson);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .addHeader("content-type", "application/json")
                    .addHeader("authorization", "Bearer "+authenticate(stk_app_key,stk_app_secret))
                    .addHeader("cache-control", "no-cache")
                    .build();
            Response response = client.newCall(request).execute();
            String responseString=response.body().string();
            log.info("STK Push Response#\n"+responseString);
            return responseString;
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void processSTKPushConfirmation(String message) {
        log.info("Process STK Push Confirmation Result ##");
        log.info(message);
        JSONObject jsonBodyResponse = null;
        String merchantRequestId = "";
        String checkoutRequestId = "";
        int resultCode = 0;
        String resultDesc = "";
        double transAmount = 0;
        String mpesaReceiptNumber = "";
        double orgAccountBalance = 0;
        String msisdn = "";
        String transactionDate = "";
        try {
            jsonBodyResponse = new JSONObject(message);
            JSONObject body = jsonBodyResponse.getJSONObject("Body");
            JSONObject stkCallback = body.getJSONObject("stkCallback");
            resultCode = stkCallback.getInt("ResultCode");
            checkoutRequestId = stkCallback.getString("CheckoutRequestID");
            merchantRequestId = stkCallback.getString("MerchantRequestID");
            MpesaSTKPushReq stkPushRequest = mpesaSTKPushReqRepository.findByMerchantRequestIDAndCheckoutRequestID(merchantRequestId, checkoutRequestId);
            if (stkPushRequest != null) {
                if (resultCode == 0) {
                    JSONObject callMeta = stkCallback.getJSONObject("CallbackMetadata");
                    JSONArray item = callMeta.getJSONArray("Item");
                    for (int i = 0; i < item.length(); i++) {
                        JSONObject jsonObject1 = item.getJSONObject(i);
                        System.out.println(jsonObject1.toString());
                        String key = jsonObject1.getString("Name");
                        if (StringUtils.equalsIgnoreCase(key, "Amount")) {
                            transAmount = jsonObject1.optDouble("Value", 0);
                        }

                        if (StringUtils.equalsIgnoreCase(key, "MpesaReceiptNumber")) {
                            mpesaReceiptNumber = jsonObject1.optString("Value");
                        }

                        if (StringUtils.equalsIgnoreCase(key, "TransactionDate")) {
                            transactionDate = jsonObject1.optString("Value");
                        }

                        if (StringUtils.equalsIgnoreCase(key, "PhoneNumber")) {
                            msisdn = jsonObject1.optString("Value");
                        }
                    }
                    log.info("Find and update the stk push Request## " + merchantRequestId + "\t" + checkoutRequestId);
                    stkPushRequest.setBillRefNumber(mpesaReceiptNumber);
                    stkPushRequest.setStkPushResponseCode(resultCode + "");
                    stkPushRequest.setStkPushResponseDescription(resultDesc);
                    stkPushRequest.setTransactionStatus("COMPLETE");
                    mpesaSTKPushReqRepository.save(stkPushRequest);
                    log.info("stk call back Request Logged##");
                } else {
                    log.info("STK Push Failed ## Just notify customer");
                    stkPushRequest.setBillRefNumber(mpesaReceiptNumber);
                    stkPushRequest.setStkPushResponseCode(resultCode + "");
                    stkPushRequest.setStkPushResponseDescription(resultDesc);
                    stkPushRequest.setTransactionStatus("FAILED");

                    mpesaSTKPushReqRepository.save(stkPushRequest);
                }
            } else {
                log.info("Transaction Details NOT Found");

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            ex.printStackTrace();
        }
    }

    @Override
    public void logStkPushOutboundRequest(MpesaSTKPushReq mpesaOutboundRequest) {
        mpesaSTKPushReqRepository.save(mpesaOutboundRequest);
    }
}
