package com.shoppingShots.shoppingShots.Utilities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 200 for status Okay.
// 400 for logical error.
// 500 for server error

@Getter
@AllArgsConstructor
public class OpResponse {
    private final String message;
    private final long code;

    public String toString(){
        return this.message + " :- "+this.code;
    }
}
