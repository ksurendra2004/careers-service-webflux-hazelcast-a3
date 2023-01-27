package com.careersservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @NotNull(message = "EmployeeMain.id must be present")
    private int employeeId;
    @NotBlank(message = "EmployeeMain.employeeName must not be null")
    private String employeeName;
    @NotBlank(message = "EmployeeMain.employeeCity must not be null")
    private String employeeCity;
    @NotBlank(message = "EmployeeMain.employeePhone must not be null")
    private String employeePhone;
    @NotNull(message = "EmployeeMain.javaExperience must be present")
    private double javaExperience;
    @NotNull(message = "EmployeeMain.springExperience must be present")
    private double springExperience;
}
