package com.vehicleinsurance.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "insurances")
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String policyNumber;
    private String insuranceCompany;
    private String startDate;
    private String endDate;
    private Double premiumAmount;
    
    @ManyToOne
    @JoinColumn(name = "policy_id")
    private Policy policy;


    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPolicyNumber() { return policyNumber; }
    public void setPolicyNumber(String policyNumber) { this.policyNumber = policyNumber; }

    public String getInsuranceCompany() { return insuranceCompany; }
    public void setInsuranceCompany(String insuranceCompany) { this.insuranceCompany = insuranceCompany; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

    public Double getPremiumAmount() { return premiumAmount; }
    public void setPremiumAmount(Double premiumAmount) { this.premiumAmount = premiumAmount; }

    public Vehicle getVehicle() { return vehicle; }
    public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }
    
    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }
}
