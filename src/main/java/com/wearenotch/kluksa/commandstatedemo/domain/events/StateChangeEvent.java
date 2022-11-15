package com.wearenotch.kluksa.commandstatedemo.domain.events;

public record StateChangeEvent(String stateName) {

  public String getStateName() {
    return stateName;
  }
}
