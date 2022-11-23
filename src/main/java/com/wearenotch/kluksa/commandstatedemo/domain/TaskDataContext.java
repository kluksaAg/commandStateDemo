package com.wearenotch.kluksa.commandstatedemo.domain;

import com.wearenotch.kluksa.commandstatedemo.domain.state.Task;
import com.wearenotch.kluksa.commandstatedemo.persistence.domain.TaskEntity;

/**
 * TaskDataContext predstavlja record u kojem se drže svi objekti koji su potrebni Task klasama
 * da izvrše
 */
public record TaskDataContext(TaskEntity entity) {

    public Long getId() {
        return entity.getId();
    }

    public String getTitle() {
        return entity.getTitle();
    }

    // Razni objekti koji su potrebni za izrvršavanje taskova
    // ApplicationEventPublisher, UserRepository...
    //
    public void setTitle(String title) {
        entity.setTitle(title);
    }

    public Task.Status getStatus() {
        return entity.getStatus();
    }

    public void setStatus(Task.Status status) {
        entity.setStatus(status);
    }

    public Boolean getApproved() {
        return entity.getApproved();
    }

    public void setApproved(Boolean approved) {
        entity.setApproved(approved);
    }

}
