package entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "loan_applications")
public class LoanApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    private Double goldWeightInGrams; // weight
    private Double goldRatePerGram; // rate at the time
    private Double requestedLoanAmount;
    private Double approvedLoanAmount;


    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;


    private String officerRemark;
    private String managerRemark;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public Double getGoldWeightInGrams() { return goldWeightInGrams; }
    public void setGoldWeightInGrams(Double goldWeightInGrams) { this.goldWeightInGrams = goldWeightInGrams; }
    public Double getGoldRatePerGram() { return goldRatePerGram; }
    public void setGoldRatePerGram(Double goldRatePerGram) { this.goldRatePerGram = goldRatePerGram; }
    public Double getRequestedLoanAmount() { return requestedLoanAmount; }
    public void setRequestedLoanAmount(Double requestedLoanAmount) { this.requestedLoanAmount = requestedLoanAmount; }
    public Double getApprovedLoanAmount() { return approvedLoanAmount; }
    public void setApprovedLoanAmount(Double approvedLoanAmount) { this.approvedLoanAmount = approvedLoanAmount; }
    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }
    public String getOfficerRemark() { return officerRemark; }
    public void setOfficerRemark(String officerRemark) { this.officerRemark = officerRemark; }
    public String getManagerRemark() { return managerRemark; }
    public void setManagerRemark(String managerRemark) { this.managerRemark = managerRemark; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}