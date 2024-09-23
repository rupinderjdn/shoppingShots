package com.shoppingShots.shoppingShots.model;

import com.shoppingShots.shoppingShots.model.Interfaces.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InternalUser extends User {
    private String department;
    private String position;
    private LocalDateTime lastLogin;
}
