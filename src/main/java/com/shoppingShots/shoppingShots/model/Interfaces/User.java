package com.shoppingShots.shoppingShots.model.Interfaces;

import com.shoppingShots.shoppingShots.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class User {
    private Integer id;
    private String name;
    private String mobileNumber;
    private String email;
    private String password;
    private String profileImage;
    private List<String> role;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
