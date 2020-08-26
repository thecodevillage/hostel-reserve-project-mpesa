package com.thecodevillage.hostelreserve.mpesa.service;

import com.thecodevillage.hostelreserve.mpesa.models.MpesaSTKPushReq;

public interface MpesaService {

    String STKPushSimulation(String businessShortCode, String password, String timestamp,String transactionType,
                             String amount, String phoneNumber, String partyA, String partyB,
                             String callBackURL, String queueTimeOutURL,
                             String accountReference, String transactionDesc);

    void processSTKPushConfirmation(String message);

    void logStkPushOutboundRequest(MpesaSTKPushReq mpesaSTKPushReq);
}
