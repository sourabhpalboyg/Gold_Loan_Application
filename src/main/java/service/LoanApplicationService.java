package service;



import dto.LoanApplicationRequestDTO;
import dto.LoanApplicationResponseDTO;
import entity.ApplicationStatus;

import java.util.List;

public interface LoanApplicationService {
    LoanApplicationResponseDTO createApplication(LoanApplicationRequestDTO dto);
    LoanApplicationResponseDTO getApplication(Long id);
    List<LoanApplicationResponseDTO> listApplications(ApplicationStatus status);
    LoanApplicationResponseDTO officerReview(Long id, Double approvedAmount, String remark);
    LoanApplicationResponseDTO managerDecision(Long id, boolean approve, String remark);
}
