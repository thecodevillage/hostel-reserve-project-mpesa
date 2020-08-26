package com.thecodevillage.hostelreserve.hostel.repository;

import com.thecodevillage.hostelreserve.hostel.models.PaymentTransaction;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PaymentRepository extends PagingAndSortingRepository<PaymentTransaction,Long> {
}
