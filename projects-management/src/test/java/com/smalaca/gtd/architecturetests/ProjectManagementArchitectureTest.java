package com.smalaca.gtd.architecturetests;

import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;

public class ProjectManagementArchitectureTest {
    private static final String DOMAIN = "com.smalaca.gtd.projectmanagement.domain..";
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
}
