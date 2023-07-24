package com.accounts.provider.record;

import java.time.LocalDate;

public record Cliente(
        Long id,
        String nombres,
        LocalDate fechaNacimiento
) {

}
