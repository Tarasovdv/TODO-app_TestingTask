package ru.buttonone.todoApp.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private BigInteger id;
    private String text;
    private Boolean completed;
}