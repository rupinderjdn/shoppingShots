package com.shoppingShots.shoppingShots.Utilities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

// 200 for status Okay.
// 400 for logical error.
// 500 for server error


@AllArgsConstructor
public class OPresponse {
    private final String message;
    private final long code;
}
