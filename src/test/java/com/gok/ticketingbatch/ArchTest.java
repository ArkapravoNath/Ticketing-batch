package com.gok.ticketingbatch;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.gok.ticketingbatch");

        noClasses()
            .that()
                .resideInAnyPackage("com.gok.ticketingbatch.service..")
            .or()
                .resideInAnyPackage("com.gok.ticketingbatch.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.gok.ticketingbatch.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
