package com.projeto.desafio_java.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projeto.desafio_java.enums.StatusPedidoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido extends BaseEntity{

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusPedidoEnum status;

    private BigDecimal percentualDesconto;

    private BigDecimal descontoEfetivo;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ItemPedido> itens = new ArrayList<>();

    private BigDecimal valorTotalItens;

    private BigDecimal valorFinal;

}
