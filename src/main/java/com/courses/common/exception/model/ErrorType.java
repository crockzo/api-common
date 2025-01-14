package com.courses.common.exception.model;

import lombok.Getter;

@Getter
public enum ErrorType {
  NOT_FOUND,
  MISSING_MANDATORY_FIELD,
  INVALID_FIELD_VALUE;
}
