package com.payment.customer.domain.validation;

import com.payment.customer.domain.exceptions.customerExceptions.InvalidParameter;

import java.util.Arrays;
import java.util.List;

public class ValidateSortingType {

    private static final List<String> VALID_SORT_FIELDS = Arrays.asList(
            "name",
            "email",
            "phoneNumber"
    );

    public static void validateSortingType(String sortingType){
        if (!"asc".equalsIgnoreCase(sortingType) && !"desc".equalsIgnoreCase(sortingType)) {
            throw new IllegalArgumentException("El tipo de ordenación debe ser 'asc' o 'desc'.");
        }
    }

    public static void validateSortBy(String sortBy) {
        if (sortBy == null || sortBy.isEmpty()) {
            throw new InvalidParameter("El campo 'sortBy' no puede estar vacío.");
        }

        if (!VALID_SORT_FIELDS.contains(sortBy)) {
            throw new InvalidParameter("Campo de ordenación inválido: " + sortBy +
                    ". Los campos permitidos son: " + VALID_SORT_FIELDS);
        }
    }
}
