package com.wildpulse.commons.models.errors;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class WPErrorResponse {
    List<WPErrorDetails> errors;
}
