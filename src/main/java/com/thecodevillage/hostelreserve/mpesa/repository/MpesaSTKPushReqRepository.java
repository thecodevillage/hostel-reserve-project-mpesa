package com.thecodevillage.hostelreserve.mpesa.repository;

import com.thecodevillage.hostelreserve.mpesa.models.MpesaSTKPushReq;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MpesaSTKPushReqRepository extends CrudRepository<MpesaSTKPushReq,Long> {
    MpesaSTKPushReq findByMerchantRequestIDAndCheckoutRequestID(@Param("merchantRequestID") String merchantRequestID, @Param("checkoutRequestID") String checkoutRequestID);

}