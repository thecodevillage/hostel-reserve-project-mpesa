package com.thecodevillage.hostelreserve.mpesa.models;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="stk_push_req")
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class MpesaSTKPushReq implements Serializable{
    public enum TransactionType{
        DISBURSEMENT, WITHDRAWAL, DEPOSIT
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="transaction_type")
    private TransactionType transactType;

    @Column(name="mobile_number")
    private String mobileNumber;

    @Column(name="account_number")
    private String accountNumber;

    private String requestId;

    private String shortCode;

    private String billRefNumber;

    private String commandID;

    private String transactionID;

    private String amount;

    private String partyA;

    private String partyB;

    private String identifierType;

    private String remarks;

    private String queueTimeOutURL;

    private String resultURL;

    private String occassion;


    private String originatorConversationID;

    private String conversationID;

    private String responseCode;

    private String responseDescription;

    private String merchantRequestID;

    private String checkoutRequestID;

    private String stkPushResponseCode;

    private String stkPushResponseDescription;

    private String failureDescription;

    @Column(name="transaction_status")
    private String transactionStatus;

    @Column(name="transaction_date_time")
    private Date transactionDateTime=new Date();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getTransactType() {
        return transactType;
    }

    public void setTransactType(TransactionType transactType) {
        this.transactType = transactType;
    }


    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getBillRefNumber() {
        return billRefNumber;
    }

    public void setBillRefNumber(String billRefNumber) {
        this.billRefNumber = billRefNumber;
    }

    public String getCommandID() {
        return commandID;
    }

    public void setCommandID(String commandID) {
        this.commandID = commandID;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPartyA() {
        return partyA;
    }

    public void setPartyA(String partyA) {
        this.partyA = partyA;
    }

    public String getPartyB() {
        return partyB;
    }

    public void setPartyB(String partyB) {
        this.partyB = partyB;
    }

    public String getIdentifierType() {
        return identifierType;
    }

    public void setIdentifierType(String identifierType) {
        this.identifierType = identifierType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getQueueTimeOutURL() {
        return queueTimeOutURL;
    }

    public void setQueueTimeOutURL(String queueTimeOutURL) {
        this.queueTimeOutURL = queueTimeOutURL;
    }

    public String getResultURL() {
        return resultURL;
    }

    public void setResultURL(String resultURL) {
        this.resultURL = resultURL;
    }

    public String getOccassion() {
        return occassion;
    }

    public void setOccassion(String occassion) {
        this.occassion = occassion;
    }


    public String getOriginatorConversationID() {
        return originatorConversationID;
    }

    public void setOriginatorConversationID(String originatorConversationID) {
        this.originatorConversationID = originatorConversationID;
    }

    public String getConversationID() {
        return conversationID;
    }

    public void setConversationID(String conversationID) {
        this.conversationID = conversationID;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public Date getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(Date transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public String getMerchantRequestID() {
        return merchantRequestID;
    }

    public void setMerchantRequestID(String merchantRequestID) {
        this.merchantRequestID = merchantRequestID;
    }

    public String getCheckoutRequestID() {
        return checkoutRequestID;
    }

    public void setCheckoutRequestID(String checkoutRequestID) {
        this.checkoutRequestID = checkoutRequestID;
    }

    public String getStkPushResponseCode() {
        return stkPushResponseCode;
    }

    public void setStkPushResponseCode(String stkPushResponseCode) {
        this.stkPushResponseCode = stkPushResponseCode;
    }

    public String getStkPushResponseDescription() {
        return stkPushResponseDescription;
    }

    public String getFailureDescription() {
        return failureDescription;
    }

    public void setFailureDescription(String failureDescription) {
        this.failureDescription = failureDescription;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public void setStkPushResponseDescription(String stkPushResponseDescription) {
        this.stkPushResponseDescription = stkPushResponseDescription;
    }

    public MpesaSTKPushReq(TransactionType transactType, String mobileNumber, String accountNumber, String requestId,
                               String commandID, String amount,
                               String partyA, String partyB,
                               String checkoutRequestID,String merchantRequestID,String responseCode,String responseDescription,String transactionStatus) {
        this.transactType = transactType;
        this.mobileNumber = mobileNumber;
        this.accountNumber = accountNumber;
        this.requestId = requestId;
        this.commandID = commandID;
        this.amount = amount;
        this.partyA = partyA;
        this.partyB = partyB;
        this.checkoutRequestID =checkoutRequestID;
        this.merchantRequestID = merchantRequestID;
        this.responseCode = responseCode;
        this.responseDescription = responseDescription;
        this.transactionStatus = transactionStatus;
    }

}
