package com.sanbro.redis.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserDto implements Serializable {
    @Size(min = 3)
    @NotBlank(message = "Name should be at least 3 character long")
    private String name;

    @NotBlank(message = "Email cannot be empty")
    private String email;

    @Min(value = 1, message = "You should at least be 1 year old")
    @Max(value = 120,message = "You should not exceed 120 years")
    private int age;
}
