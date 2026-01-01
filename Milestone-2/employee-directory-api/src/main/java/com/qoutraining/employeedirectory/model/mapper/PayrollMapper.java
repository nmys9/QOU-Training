package com.qoutraining.employeedirectory.model.mapper;


import com.qoutraining.employeedirectory.model.dto.payroll.PayrollRequestDTO;
import com.qoutraining.employeedirectory.model.dto.payroll.PayrollResponseDTO;
import com.qoutraining.employeedirectory.model.entity.Payroll;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PayrollMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "netPay", ignore = true)
    Payroll toEntity(PayrollRequestDTO dto);

    @Mapping(source = "employee.id", target = "employeeId")
    PayrollResponseDTO toResponseDto(Payroll entity);

    List<PayrollResponseDTO> toResponseListDto(List<Payroll> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "netPay", ignore = true)
    void updateEntityFromDto(PayrollRequestDTO dto,@MappingTarget Payroll entity);
}
