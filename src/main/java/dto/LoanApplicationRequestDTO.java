package dto;



import jakarta.validation.constraints.*;

public class LoanApplicationRequestDTO {
    @NotNull
    private Long customerId;

    @NotNull @Positive
    private Double goldWeightInGrams;

    @NotNull @Positive
    private Double goldRatePerGram;

    @NotNull @Positive
    private Double requestedLoanAmount;

    // getters setters
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    public Double getGoldWeightInGrams() { return goldWeightInGrams; }
    public void setGoldWeightInGrams(Double goldWeightInGrams) { this.goldWeightInGrams = goldWeightInGrams; }
    public Double getGoldRatePerGram() { return goldRatePerGram; }
    public void setGoldRatePerGram(Double goldRatePerGram) { this.goldRatePerGram = goldRatePerGram; }
    public Double getRequestedLoanAmount() { return requestedLoanAmount; }
    public void setRequestedLoanAmount(Double requestedLoanAmount) { this.requestedLoanAmount = requestedLoanAmount; }
}
