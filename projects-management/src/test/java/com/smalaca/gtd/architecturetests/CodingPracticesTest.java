package com.smalaca.gtd.architecturetests;

import com.tngtech.archunit.library.GeneralCodingRules;
import org.junit.jupiter.api.Test;

public class CodingPracticesTest {
    @Test
    void shouldNotUseFieldInjection() {
        GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION
                .check(CodeBase.allClasses());
    }
}
