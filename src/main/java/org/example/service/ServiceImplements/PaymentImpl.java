package org.example.service.ServiceImplements;

import org.example.entity.PaymentEntity;
import org.example.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentImpl implements PaymentService {
    private List<PaymentEntity> paymentList = new ArrayList<>();

    @Override
    public List<PaymentEntity> getAllPayments() {
        return paymentList;
    }

    @Override
    public Optional<PaymentEntity> getPaymentById(Long id) {
        return paymentList.stream()
                .filter(payment -> payment.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<PaymentEntity> getPaymentsByUserId(Long userId) {
        return paymentList.stream()
                .filter(payment -> payment.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentEntity> getPaymentsByPolicyId(Long policyId) {
        return paymentList.stream()
                .filter(payment -> payment.getPolicyId().equals(policyId))
                .collect(Collectors.toList());
    }

    @Override
    public PaymentEntity createPayment(PaymentEntity payment) {
        Long nextId = getNextPaymentId();
        payment.setId(nextId);
        paymentList.add(payment);
        return payment;
    }

    @Override
    public PaymentEntity updatePayment(PaymentEntity payment) {
        Optional<PaymentEntity> existingPayment = getPaymentById(payment.getId());
        if (existingPayment.isPresent()) {
            PaymentEntity updatedPayment = existingPayment.get();
            updatedPayment.setUserId(payment.getUserId());
            updatedPayment.setPolicyId(payment.getPolicyId());
            updatedPayment.setPaymentAmount(payment.getPaymentAmount());
            updatedPayment.setPaymentDate(payment.getPaymentDate());
            return updatedPayment;
        }
        return null;
    }

    @Override
    public void deletePayment(Long id) {
        paymentList.removeIf(payment -> payment.getId().equals(id));
    }

    private Long getNextPaymentId() {
        if (paymentList.isEmpty()) {
            return 1L;
        } else {
            Long lastPaymentId = paymentList.get(paymentList.size() - 1).getId();
            return lastPaymentId + 1;
        }
    }
}
