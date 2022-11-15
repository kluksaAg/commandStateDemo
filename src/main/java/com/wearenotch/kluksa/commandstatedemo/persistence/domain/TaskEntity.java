package com.wearenotch.kluksa.commandstatedemo.persistence.domain;


import com.wearenotch.kluksa.commandstatedemo.domain.state.Task;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class TaskEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Task.Status status;

    @NotBlank
    private String title;

    private Boolean approved;

    public TaskEntity() {
    }

    public Long getId() {
        return id;
    }

    public TaskEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public TaskEntity setTitle(@NotNull final String title) {
        this.title = title;
        return this;
    }

    public Task.Status getStatus() {
        return status;
    }

    public TaskEntity setStatus(Task.Status status) {
        this.status = status;
        return this;
    }

    public Boolean getApproved() {
        return approved;
    }

    public TaskEntity setApproved(Boolean approved) {
        this.approved = approved;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskEntity that)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
