package com.wearenotch.kluksa.commandstatedemo.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public record TaskSubmitDto(@NotBlank @Size(max = 255) String title) implements Serializable {
    @JsonCreator
    public TaskSubmitDto(@NotBlank @Size(max = 255) @JsonProperty("title") final String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
