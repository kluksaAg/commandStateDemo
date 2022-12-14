package com.wearenotch.kluksa.commandstatedemo.service.dto;

import com.wearenotch.kluksa.commandstatedemo.domain.state.Task;

public record TaskDetailsDto(Long id, String title, Boolean approved,
                             Task.Status status) {

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Boolean isApproved() {
        return approved;
    }

    public Task.Status getStatus() {
        return status;
    }
}
