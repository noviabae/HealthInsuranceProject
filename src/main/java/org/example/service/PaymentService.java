package org.example.service;

import org.example.entity.PaymentEntity;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    List<PaymentEntity> getAllPayments();

    Optional<PaymentEntity> getPaymentById(Long id);

    List<PaymentEntity> getPaymentsByUserId(Long userId);

    List<PaymentEntity> getPaymentsByPolicyId(Long policyId);

    PaymentEntity createPayment(PaymentEntity payment);

    PaymentEntity updatePayment(PaymentEntity payment);

    void deletePayment(Long id);
}
