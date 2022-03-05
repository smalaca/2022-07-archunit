package com.smalaca.gtd.projectmanagement.domain.idea;

import com.smalaca.gtd.projectmanagement.domain.owner.OwnerId;
import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.Assertions;

public class IdeaAssertion {
    private final Idea actual;

    private IdeaAssertion(Idea actual) {
        this.actual = actual;
    }

    public static IdeaAssertion assertThat(Idea actual) {
        return new IdeaAssertion(actual);
    }

    public IdeaAssertion hasNoTitle() {
        return this;
    }

    public IdeaAssertion hasTitle(String expected) {
        title().isEqualTo(expected);
        return this;
    }

    private AbstractObjectAssert<?, ?> title() {
        return Assertions.assertThat(actual).extracting("title");
    }

    public IdeaAssertion hasNoDescription() {
        description().isNull();
        return this;
    }

    public IdeaAssertion hasDescription(String expected) {
        description().isEqualTo(expected);
        return this;
    }

    private AbstractObjectAssert<?, ?> description() {
        return Assertions.assertThat(actual).extracting("description");
    }

    public IdeaAssertion hasOwnerId(OwnerId expected) {
        Assertions.assertThat(actual).extracting("ownerId").isEqualTo(expected);
        return this;
    }
}
