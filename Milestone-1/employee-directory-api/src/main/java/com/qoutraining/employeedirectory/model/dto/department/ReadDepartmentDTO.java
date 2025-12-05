package com.qoutraining.employeedirectory.model.dto.department;

import com.qoutraining.employeedirectory.model.entity.Department;

public record ReadDepartmentDTO(
        Long id,
        String name,
        String managerName
) {
    public static ReadDepartmentDTO fromEntity(Department department){
        String managerName=(department.getManager() != null)
                ? department.getManager().getFirstName() + " " + department.getManager().getLastName()
                : "No Manager Assigned";

        return new ReadDepartmentDTO(
                department.getId(),
                department.getName(),
                managerName
        );
    }
}
