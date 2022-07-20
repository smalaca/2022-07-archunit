package com.smalaca.gtd.architecturetests;

import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;

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
}
