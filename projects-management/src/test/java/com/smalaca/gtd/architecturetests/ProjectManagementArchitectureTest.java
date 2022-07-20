package com.smalaca.gtd.architecturetests;

import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.Test;

public class ProjectManagementArchitectureTest {
    private static final String DOMAIN = "com.smalaca.gtd.projectmanagement.domain..";
    private static final String APPLICATION = "com.smalaca.gtd.projectmanagement.application..";
    private static final String INFRASTRUCTURE = "com.smalaca.gtd.projectmanagement.infrastructure..";
    private static final String JAVA = "java..";
    private static final String JPA = "javax.persistence..";
    private static final String APACHE_COMMONS = "org.apache.commons.lang3..";
    private static final String FINDBUGS = "edu.umd.cs.findbugs.annotations..";

    @Test
    void domainShouldBeIndependent() {
        ArchRuleDefinition.classes().that()
                .resideInAPackage(DOMAIN)

                .should().onlyDependOnClassesThat()
                .resideInAnyPackage(
                        JAVA, JPA, APACHE_COMMONS, FINDBUGS,
                        DOMAIN)

                .as("Domain should be independent")
                .because("0003-project-management-hexagonal-architecture.md")

                .check(CodeBase.projectManagementClasses());
    }

    @Test
    void shouldVerifyDependenciesBetweenPackages() {
        Architectures.layeredArchitecture()
                .layer("Domain").definedBy(DOMAIN)
                .layer("Application").definedBy(APPLICATION)
                .layer("Infrastructure").definedBy(INFRASTRUCTURE)

                .whereLayer("Domain").mayOnlyBeAccessedByLayers("Application", "Infrastructure")
                .whereLayer("Application").mayOnlyBeAccessedByLayers("Infrastructure")

                .check(CodeBase.projectManagementClasses());
    }

    @Test
    void shouldVerifyIsThatPortAndAdaptersArchitecture() {
        Architectures.onionArchitecture()
                .domainModels(APPLICATION)
                .domainServices(APPLICATION)
                .applicationServices(DOMAIN)
                .adapter("REST-API", "com.smalaca.gtd.projectmanagement.infrastructure.api.web.rest..")
                .adapter("REPOSITORY", "com.smalaca.gtd.projectmanagement.infrastructure.repository.jpa..")

                .as("Hexagonal Architecture should be followed")
                .because("0003-project-management-hexagonal-architecture.md")

                .check(CodeBase.projectManagementClasses());
    }
}
