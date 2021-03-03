package com.rhplus.rhplus;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.rhplus.rhplus");

        noClasses()
            .that()
            .resideInAnyPackage("com.rhplus.rhplus.service..")
            .or()
            .resideInAnyPackage("com.rhplus.rhplus.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.rhplus.rhplus.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
