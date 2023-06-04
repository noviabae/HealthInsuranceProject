package org.example.controller;

import org.example.entity.PaymentEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private List<PaymentEntity> paymentList = new ArrayList<>();

    // Mendapatkan semua pembayaran premi
    @GetMapping
    public List<PaymentEntity> getAllPayments() {
        return paymentList;
    }

    // Mendapatkan pembayaran premi berdasarkan ID
    @GetMapping("/{id}")
    public PaymentEntity getPaymentById(@PathVariable Long id) {
        Optional<PaymentEntity> payment = paymentList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        return payment.orElse(null);
    }

    // Melakukan pembayaran premi baru
    @PostMapping
    public PaymentEntity makePayment(@RequestBody PaymentEntity payment) {
        // Assign ID baru
        Long nextId = getNextPaymentId();
        payment.setId(nextId);

        // Assign tanggal pembayaran saat ini
        payment.setPaymentDate(LocalDate.now());

        paymentList.add(payment);
        return payment;
    }

    // Mendapatkan ID berikutnya untuk pembayaran premi
    private Long getNextPaymentId() {
        if (paymentList.isEmpty()) {
            return 1L;
        } else {
            Long lastPaymentId = paymentList.get(paymentList.size() - 1).getId();
            return lastPaymentId + 1;
        }
    }

    // Memperbarui pembayaran premi berdasarkan ID
    @PutMapping("/{id}")
    public PaymentEntity updatePayment(@PathVariable Long id, @RequestBody PaymentEntity updatedPayment) {
        Optional<PaymentEntity> payment = paymentList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        payment.ifPresent(p -> {
            p.setUserId(updatedPayment.getUserId());
            p.setPolicyId(updatedPayment.getPolicyId());
            p.setPaymentAmount(updatedPayment.getPaymentAmount());
        });

        return payment.orElse(null);
    }

    // Menghapus pembayaran premi berdasarkan ID
    @DeleteMapping("/{id}")
    public PaymentEntity deletePayment(@PathVariable Long id) {
        Optional<PaymentEntity> payment = paymentList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        payment.ifPresent(p -> paymentList.remove(p));

        return payment.orElse(null);
    }
}
