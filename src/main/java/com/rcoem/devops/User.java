package com.rcoem.devops;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class User {
    String userId;
    String name;
    String department;
}
