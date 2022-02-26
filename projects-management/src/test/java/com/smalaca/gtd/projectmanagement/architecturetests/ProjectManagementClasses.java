package com.smalaca.gtd.projectmanagement.architecturetests;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;

import static com.tngtech.archunit.core.importer.ImportOption.Predefined.DO_NOT_INCLUDE_JARS;
import static com.tngtech.archunit.core.importer.ImportOption.Predefined.DO_NOT_INCLUDE_TESTS;

class ProjectManagementClasses {
    static JavaClasses projectClasses() {
        return new ClassFileImporter()
                .withImportOption(DO_NOT_INCLUDE_JARS)
                .withImportOption(DO_NOT_INCLUDE_TESTS)
                .importPackages("com.smalaca.gtd.projectmanagement");
    }
}