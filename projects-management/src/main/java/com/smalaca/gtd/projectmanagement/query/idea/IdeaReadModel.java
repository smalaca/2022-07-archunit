package com.smalaca.gtd.projectmanagement.query.idea;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@NoArgsConstructor(access = PRIVATE)
@Table(name = "IDEAS")
public class IdeaReadModel {
    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    @Lob
    private String description;

    IdeaReadModel(String title, String description) {
        this.title = title;
        this.description = description;
    }
}