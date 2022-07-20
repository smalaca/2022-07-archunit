package com.smalaca.gtd.architecturetests;

import com.smalaca.gtd.projectmanagement.domain.idea.IdeaRepository;
import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.conditions.ArchConditions;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.conditions.ArchConditions.bePackagePrivate;
import static com.tngtech.archunit.lang.conditions.ArchConditions.bePublic;

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

    @Test
    void shouldVerifyRuntimeExceptionExtensions() {
        ArchRuleDefinition.classes().that()
                .areAssignableTo(RuntimeException.class)

                .should().haveSimpleNameEndingWith("Exception")
                .andShould(bePublic().or(bePackagePrivate()))

                .check(CodeBase.allClasses());
    }

    @Test
    void shouldVerifyThatRepositoriesImplementationArePublic() {
        ArchRuleDefinition.classes().that()
                .implement(new RepositoryInterfacePredicate())

                .should().bePublic()
                .andShould().beAnnotatedWith(Repository.class)
                .andShould().haveSimpleNameEndingWith("Repository")

                .check(CodeBase.allClasses());
    }

    public static class RepositoryInterfacePredicate extends DescribedPredicate<JavaClass> {
        public RepositoryInterfacePredicate() {
            super("All interfaces that has name ending with Repository");
        }

        @Override
        public boolean apply(JavaClass javaClass) {
            return javaClass.getSimpleName().contains("Repository");
        }
    }
}
