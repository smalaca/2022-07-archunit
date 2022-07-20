package com.smalaca.gtd.architecturetests;

import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;

public class AbstractionTest {
    @Test
    void shouldHaveNoInterfacesNamedAsInterface() {
        ArchRuleDefinition.classes().that()
                .areInterfaces()

                .should().haveSimpleNameNotContaining("Interface")

                .check(CodeBase.allClasses());
    }

    @Test
    void shouldHaveNoClassesNamedWithInvalidEndings() {
        ArchRuleDefinition.classes()

                .should().haveSimpleNameNotContaining("Abstract")
                .andShould().haveSimpleNameNotContaining("Base")
                .andShould().haveSimpleNameNotContaining("Impl")

                .check(CodeBase.allClasses());
    }
}
