package com.shoppingShots.shoppingShots.model;

import com.shoppingShots.shoppingShots.model.Interfaces.UserDO;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class InternalUser extends UserDO {
    private String department;
    private String position;
    private LocalDateTime lastLogin;
    private List<String> role;

}
