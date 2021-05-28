package com.github.concessionaria.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "veiculo")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "placa")
    private String placa;

    @Column(name = "quilometragem")
    private Integer quilometragem;

    @Column(name = "cor")
    private String cor;

    @Column(name = "preco")
    private BigDecimal preco;

    @Column(name = "quantidade_portas")
    private Integer quantidadeDePortas;

    @ManyToOne()
    private Modelo modelo;

    public Veiculo() { }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Integer getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(Integer quilometragem) {
        this.quilometragem = quilometragem;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidadeDePortas() {
        return quantidadeDePortas;
    }

    public void setQuantidadeDePortas(Integer quantidadeDePortas) {
        this.quantidadeDePortas = quantidadeDePortas;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
}
