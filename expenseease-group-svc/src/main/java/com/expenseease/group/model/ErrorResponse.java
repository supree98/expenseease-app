package com.expenseease.group.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ErrorResponse {
    private Integer status;
    private String message;
    private Long timeStamp;

}
