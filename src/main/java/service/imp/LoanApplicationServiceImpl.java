package service.imp;

import dto.CustomerDTO;
import dto.LoanApplicationRequestDTO;
import dto.LoanApplicationResponseDTO;
import entity.ApplicationStatus;
import entity.Customer;
import entity.LoanApplication;
import repository.CustomerRepository;
import repository.LoanApplicationRepository;
import service.LoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {

    @Autowired
    private LoanApplicationRepository loanRepo;

    @Autowired
    private CustomerRepository customerRepo;

    //  Create New Loan Application
    @Override
    public LoanApplicationResponseDTO createApplication(LoanApplicationRequestDTO dto) {
        Customer customer = customerRepo.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + dto.getCustomerId()));

        LoanApplication application = new LoanApplication();
        application.setCustomer(customer);
        application.setGoldWeightInGrams(dto.getGoldWeightInGrams());
        application.setGoldRatePerGram(dto.getGoldRatePerGram());
        application.setRequestedLoanAmount(dto.getRequestedLoanAmount());
        application.setStatus(ApplicationStatus.SUBMITTED);     //  changed here
        application.setCreatedAt(LocalDateTime.now());
        application.setUpdatedAt(LocalDateTime.now());

        LoanApplication saved = loanRepo.save(application);
        return convertToResponseDTO(saved);
    }

    //  Get Application by ID
    @Override
    public LoanApplicationResponseDTO getApplication(Long id) {
        LoanApplication app = loanRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan application not found with ID: " + id));
        return convertToResponseDTO(app);
    }

    //  List all applications (optional filter by status)
    @Override
    public List<LoanApplicationResponseDTO> listApplications(ApplicationStatus status) {
        List<LoanApplication> list;
        if (status != null) {
            list = loanRepo.findByStatus(status);
        } else {
            list = loanRepo.findAll();
        }
        return list.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    //  Officer Review
    @Override
    public LoanApplicationResponseDTO officerReview(Long id, Double approvedAmount, String remark) {
        LoanApplication app = loanRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        app.setApprovedLoanAmount(approvedAmount);
        app.setOfficerRemark(remark);
        app.setStatus(ApplicationStatus.IN_REVIEW);  //  changed here
        app.setUpdatedAt(LocalDateTime.now());

        LoanApplication updated = loanRepo.save(app);
        return convertToResponseDTO(updated);
    }

    //  Manager Decision
    @Override
    public LoanApplicationResponseDTO managerDecision(Long id, boolean approve, String remark) {
        LoanApplication app = loanRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        app.setManagerRemark(remark);
        app.setStatus(approve ? ApplicationStatus.APPROVED : ApplicationStatus.REJECTED);
        app.setUpdatedAt(LocalDateTime.now());

        LoanApplication updated = loanRepo.save(app);
        return convertToResponseDTO(updated);
    }

    //  Helper: Convert Entity → Response DTO
    private LoanApplicationResponseDTO convertToResponseDTO(LoanApplication app) {
        LoanApplicationResponseDTO dto = new LoanApplicationResponseDTO();
        dto.setId(app.getId());

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(app.getCustomer().getId());
        customerDTO.setName(app.getCustomer().getName());
        customerDTO.setEmail(app.getCustomer().getEmail());
        customerDTO.setPhone(app.getCustomer().getPhone());
        customerDTO.setDob(app.getCustomer().getDob());

        dto.setCustomer(customerDTO);
        dto.setGoldWeightInGrams(app.getGoldWeightInGrams());
        dto.setGoldRatePerGram(app.getGoldRatePerGram());
        dto.setRequestedLoanAmount(app.getRequestedLoanAmount());
        dto.setApprovedLoanAmount(app.getApprovedLoanAmount());
        dto.setStatus(app.getStatus());
        dto.setOfficerRemark(app.getOfficerRemark());
        dto.setManagerRemark(app.getManagerRemark());
        dto.setCreatedAt(app.getCreatedAt());
        dto.setUpdatedAt(app.getUpdatedAt());

        return dto;
    }
}
