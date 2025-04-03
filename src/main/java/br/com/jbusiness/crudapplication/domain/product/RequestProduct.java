package br.com.jbusiness.crudapplication.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestProduct(String id, @NotBlank String name, @NotNull Integer priceInCents) {

}
