package org.example.controller;

import org.example.entity.ClaimEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/claim")
public class ClaimController {
    private List<ClaimEntity> claimList = new ArrayList<>();

    // Mendapatkan semua klaim asuransi
    @GetMapping
    public List<ClaimEntity> getAllClaims() {
        return claimList;
    }

    // Mendapatkan klaim asuransi berdasarkan ID
    @GetMapping("/{id}")
    public ClaimEntity getClaimById(@PathVariable Long id) {
        Optional<ClaimEntity> claim = claimList.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();

        return claim.orElse(null);
    }

    // Mengajukan klaim asuransi baru
    @PostMapping
    public ClaimEntity submitClaim(@RequestBody ClaimEntity claim) {
        // Assign ID baru
        Long nextId = getNextClaimId();
        claim.setId(nextId);

        // Assign tanggal klaim saat ini
        claim.setClaimDate(LocalDate.now());

        claimList.add(claim);
        return claim;
    }

    // Mendapatkan ID berikutnya untuk klaim asuransi
    private Long getNextClaimId() {
        if (claimList.isEmpty()) {
            return 1L;
        } else {
            Long lastClaimId = claimList.get(claimList.size() - 1).getId();
            return lastClaimId + 1;
        }
    }

    // Memperbarui status klaim asuransi berdasarkan ID
    @PutMapping("/{id}")
    public ClaimEntity updateClaimStatus(@PathVariable Long id, @RequestParam String status) {
        Optional<ClaimEntity> claim = claimList.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();

        claim.ifPresent(c -> c.setClaimStatus(status));

        return claim.orElse(null);
    }

    // Menghapus klaim asuransi berdasarkan ID
    @DeleteMapping("/{id}")
    public ClaimEntity deleteClaim(@PathVariable Long id) {
        Optional<ClaimEntity> claim = claimList.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();

        claim.ifPresent(c -> claimList.remove(c));

        return claim.orElse(null);
    }
}
