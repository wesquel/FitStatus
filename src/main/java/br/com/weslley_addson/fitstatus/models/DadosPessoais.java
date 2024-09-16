package br.com.weslley_addson.fitstatus.models;

import jakarta.persistence.*;

@Entity
public class DadosPessoais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long userId;

    private String nomeCompleto;
    private double peso;
    private int altura;
    private double cintura;
    private double quadril;
    private double bracos;
    private double coxas;
    private double dobraCutanea;
    private double pressaoArterial;

    public DadosPessoais() {
    }

    public DadosPessoais(Long userId) {
        this.userId = userId;
    }

    public DadosPessoais(String nomeCompleto, double peso, int altura, double cintura, double quadril, double bracos,
                         double coxas, double dobraCutanea, double pressaoArterial) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long authUserId) {
        this.userId = authUserId;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public double getCintura() {
        return cintura;
    }

    public void setCintura(double cintura) {
        this.cintura = cintura;
    }

    public double getQuadril() {
        return quadril;
    }

    public void setQuadril(double quadril) {
        this.quadril = quadril;
    }

    public double getBracos() {
        return bracos;
    }

    public void setBracos(double bracos) {
        this.bracos = bracos;
    }

    public double getCoxas() {
        return coxas;
    }

    public void setCoxas(double coxas) {
        this.coxas = coxas;
    }

    public double getDobraCutanea() {
        return dobraCutanea;
    }

    public void setDobraCutanea(double dobraCutanea) {
        this.dobraCutanea = dobraCutanea;
    }

    public double getPressaoArterial() {
        return pressaoArterial;
    }

    public void setPressaoArterial(double pressaoArterial) {
        this.pressaoArterial = pressaoArterial;
    }
}
