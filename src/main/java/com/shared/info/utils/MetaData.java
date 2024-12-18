package com.shared.info.utils;

import lombok.Builder;

@Builder
public record MetaData(boolean isSuccess, String responseId) {
}
