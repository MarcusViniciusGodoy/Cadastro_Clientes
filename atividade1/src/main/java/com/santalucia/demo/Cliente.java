package com.santalucia.demo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cliente {

    private String nomeCompleto;
    private String email;
    private String telefone;
}
