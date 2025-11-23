package com.projeto.desafio_java.dtos;

import com.projeto.desafio_java.enums.TipoProdutoServicoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoServicoDto {
    private String descricao;
    private TipoProdutoServicoEnum tipo;
    private BigDecimal preco;
}
