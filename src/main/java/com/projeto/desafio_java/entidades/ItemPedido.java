package com.projeto.desafio_java.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedido extends BaseEntity  {
    @ManyToOne
    private ProdutoServico produto;
    @ManyToOne
    private Pedido pedido;
    @NotNull
    private int quantidade;

}
