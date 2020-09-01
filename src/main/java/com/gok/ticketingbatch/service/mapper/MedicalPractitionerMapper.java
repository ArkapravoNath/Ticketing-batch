package com.gok.ticketingbatch.service.mapper;


import com.gok.ticketingbatch.domain.*;
import com.gok.ticketingbatch.service.dto.MedicalPractitionerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MedicalPractitioner} and its DTO {@link MedicalPractitionerDTO}.
 */
@Mapper(componentModel = "spring", uses = {PersonMapper.class})
public interface MedicalPractitionerMapper extends EntityMapper<MedicalPractitionerDTO, MedicalPractitioner> {

    @Mapping(source = "person.id", target = "personId")
    MedicalPractitionerDTO toDto(MedicalPractitioner medicalPractitioner);

    @Mapping(source = "personId", target = "person")
    @Mapping(target = "practitionerSchedules", ignore = true)
    @Mapping(target = "removePractitionerSchedule", ignore = true)
    MedicalPractitioner toEntity(MedicalPractitionerDTO medicalPractitionerDTO);

    default MedicalPractitioner fromId(Long id) {
        if (id == null) {
            return null;
        }
        MedicalPractitioner medicalPractitioner = new MedicalPractitioner();
        medicalPractitioner.setId(id);
        return medicalPractitioner;
    }
}
