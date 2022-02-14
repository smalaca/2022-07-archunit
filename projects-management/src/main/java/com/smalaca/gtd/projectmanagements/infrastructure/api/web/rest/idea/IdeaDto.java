package com.smalaca.gtd.projectmanagements.infrastructure.api.web.rest.idea;

import com.smalaca.gtd.projectmanagements.domain.idea.CreateIdeaCommand;
import com.smalaca.gtd.projectmanagements.infrastructure.validation.constraints.AtLeastOneNotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@AtLeastOneNotEmpty(
        fields = {"title", "description"},
        message = "Title or description cannot be empty."
)
@Getter
class IdeaDto {
    private final String title;
    private final String description;

    CreateIdeaCommand asCreateIdeaCommand() {
        return new CreateIdeaCommand(title, description);
    }
}