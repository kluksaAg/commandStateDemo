package com.wearenotch.kluksa.commandstatedemo.domain;

import com.wearenotch.kluksa.commandstatedemo.domain.state.Task;
import com.wearenotch.kluksa.commandstatedemo.persistence.domain.TaskEntity;

public record TaskDataContext(TaskEntity entity) {

    public TaskEntity setTitle(String title) {
        return entity.setTitle(title);
    }

    public void setStatus(Task.Status status) {
        entity.setStatus(status);
    }

    public void setApproved(Boolean approved) {
        entity.setApproved(approved);
    }

    public Long getId() {
        return entity.getId();
    }

    public String getTitle() {
        return entity.getTitle();
    }

    public Task.Status getStatus() {
        return entity.getStatus();
    }

    public Boolean getApproved() {
        return entity.getApproved();
    }

}
