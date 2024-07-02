package com.example.cafenaporta.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.List;

@Entity (foreignKeys = @ForeignKey(entity = Usuario.class,
        parentColumns = "id",
        childColumns = "userId",
        onDelete = ForeignKey.CASCADE))

public class Pedido {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public Double total;

    public String endereco;

    public String metodo_pagamento;

    public long userId;

    public String status_pedido;

    public long data;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getMetodo_pagamento() {
        return metodo_pagamento;
    }

    public void setMetodo_pagamento(String metodo_pagamento) {
        this.metodo_pagamento = metodo_pagamento;
    }

    public String getStatus_pedido() {
        return status_pedido;
    }

    public void setStatus_pedido(String status_pedido) {
        this.status_pedido = status_pedido;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

}
