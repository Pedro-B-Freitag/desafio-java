package com.projeto.desafio_java.entidades;

import com.projeto.desafio_java.enums.TipoProdutoServicoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoServico extends BaseEntity{

    @NotNull
    private String descricao;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoProdutoServicoEnum tipo;

    @NotNull
    private BigDecimal preco;

}
