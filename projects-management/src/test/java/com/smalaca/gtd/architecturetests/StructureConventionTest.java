package com.smalaca.gtd.architecturetests;

import com.tngtech.archunit.lang.conditions.ArchConditions;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RestController;

public class StructureConventionTest {
    @Test
    void shouldHaveDtosAndCommandsWithAllFieldsFinal() {
        ArchRuleDefinition.classes().that()
                .haveSimpleNameEndingWith("Dto")
                .or()
                .haveSimpleNameEndingWith("Command")

                .should().haveOnlyFinalFields()

                .check(CodeBase.allClasses());
    }

    @Test
    void shouldHaveAllRestControllerInAppropriatePackages() {
        ArchRuleDefinition.classes().that()
                .areAnnotatedWith(RestController.class)

                .should().resideInAnyPackage("..api.web.rest..", "..controller.rest..")
                .andShould(ArchConditions.haveSimpleNameEndingWith("Controller"))

                .check(CodeBase.allClasses());
    }
}
