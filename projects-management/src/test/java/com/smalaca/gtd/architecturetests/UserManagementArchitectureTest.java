package com.smalaca.gtd.architecturetests;

import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.Test;

public class UserManagementArchitectureTest {
    private static final String PERSISTENCE = "com.smalaca.gtd.usermanagement.persistence..";
    private static final String JAVA = "java..";
    private static final String SPRING_DATA = "org.springframework.data..";
    private static final String SPRING_DEPENDENCY_INJECTION = "org.springframework.stereotype..";
    private static final String JPA = "javax.persistence..";
    private static final String FINDBUGS = "edu.umd.cs.findbugs.annotations..";

    @Test
    void persistenceShouldBeIndependent() {
        ArchRuleDefinition.classes().that()
                .resideInAPackage(PERSISTENCE)

                .should().onlyDependOnClassesThat()
                .resideInAnyPackage(
                        JAVA, SPRING_DATA, SPRING_DEPENDENCY_INJECTION, JPA, FINDBUGS,
                        PERSISTENCE)

                .as("Persistence layer should be independent")
                .because("0004-user-management-layered-architecture.md")

                .check(CodeBase.userManagementClasses());
    }

    @Test
    void shouldVerifyDependenciesBetweenPackages() {
        Architectures.layeredArchitecture()
                .layer("Persistence").definedBy(PERSISTENCE)
                .layer("Domain").definedBy("com.smalaca.gtd.usermanagement.domain..")
                .layer("Controller").definedBy("com.smalaca.gtd.usermanagement.controller.rest..")

                .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Domain")
                .whereLayer("Domain").mayOnlyBeAccessedByLayers("Controller")

                .check(CodeBase.userManagementClasses());
    }
}
