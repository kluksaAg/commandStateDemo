package com.wearenotch.kluksa.commandstatedemo.service.dto;

import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public record TaskSubmitDto(@NotBlank @Size(max = 255) String title) implements Serializable {
    @NotNull
    public String getTitle() {
        return title;
    }
}
