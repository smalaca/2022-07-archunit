package com.smalaca.gtd.architecturetests;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;

class CodeBase {

    private static final String GTD = "com.smalaca.gtd";

    static JavaClasses allClasses() {
        return allIn(GTD);
    }

    static JavaClasses projectManagementClasses() {
        return allIn(GTD + ".projectmanagement");
    }

    private static JavaClasses allIn(String packageName) {
        return new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_JARS)
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages(packageName);
    }
}
