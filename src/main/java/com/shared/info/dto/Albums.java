package com.shared.info.dto;

import lombok.Builder;

@Builder(toBuilder = true)
public record Albums(Integer userId, Integer id, String title) {
}
