package controller;
import dto.LoanApplicationRequestDTO;
import dto.LoanApplicationResponseDTO;
import entity.ApplicationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import service.LoanApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanApplicationController {

    private final LoanApplicationService service;

    public LoanApplicationController(LoanApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LoanApplicationResponseDTO> create(@Valid @RequestBody LoanApplicationRequestDTO dto) {
        return ResponseEntity.ok(service.createApplication(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanApplicationResponseDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getApplication(id));
    }

    @GetMapping
    public ResponseEntity<List<LoanApplicationResponseDTO>> list(@RequestParam(required = false) ApplicationStatus status) {
        return ResponseEntity.ok(service.listApplications(status));
    }

    // officer reviews and sets approved amount
    @PostMapping("/{id}/officer-review")
    public ResponseEntity<LoanApplicationResponseDTO> officerReview(@PathVariable Long id,
                                                                    @RequestParam Double approvedAmount,
                                                                    @RequestParam(required = false) String remark) {
        return ResponseEntity.ok(service.officerReview(id, approvedAmount, remark));
    }

    // manager decision approve or reject
    @PostMapping("/{id}/manager-decision")
    public ResponseEntity<LoanApplicationResponseDTO> managerDecision(@PathVariable Long id,
                                                                      @RequestParam boolean approve,
                                                                      @RequestParam(required = false) String remark) {
        return ResponseEntity.ok(service.managerDecision(id, approve, remark));
    }
}

