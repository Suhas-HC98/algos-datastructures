package com.shared.info.utils;

import java.util.Collection;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singleton;
import static java.util.Objects.nonNull;
import static java.util.UUID.randomUUID;

public final class SharedServiceResponseBuilder {

    public static GenericResponse<?> wrapWithGenericResponse(Object data) {
        return GenericResponse.builder().metaData(metaData(data)).errors(errors(data)).data(nonNull(data) ? singleton(data) : emptyList()).build();
    }

    public static GenericResponse<?> wrapWithErrorResponse(Object data) {
        return GenericResponse.builder().metaData(metaData(null)).data(List.of(data.toString())).errors(errors(null)).build();
    }

    private static MetaData metaData(Object data) {
        MetaData success = MetaData.builder()
                .isSuccess(true)
                .responseId(randomUUID().toString())
                .build();
        MetaData failure = MetaData.builder()
                .isSuccess(false)
                .responseId(randomUUID().toString())
                .build();
        if (data instanceof Collection<?>)
            if (nonNull(data) && !((Collection<?>) data).isEmpty()) return success;
            else return failure;
        else if (nonNull(data)) return success;
        else return failure;
    }

    private static Errors errors(Object data) {
        Errors noErrors = Errors.builder().isSuccess(true).build();
        Errors hasErrors = Errors.builder().isSuccess(false).build();
        if (data instanceof Collection<?>)
            if (nonNull(data) && !((Collection<?>) data).isEmpty()) return noErrors;
            else return hasErrors;
        else if (nonNull(data)) return noErrors;
        else return hasErrors;
    }
}
