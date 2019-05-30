package com.company;

public class Registro {
    private String password;
    private Integer nivel;

    public Registro(String password) {
        this.password = password;
        this.nivel = 0;
    }

    public Registro(String password, Integer nivel) {
        this.password = password;
        this.nivel = nivel;
    }

    public Registro() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Boolean contieneOtraPassword(Registro registro) {
        return this.password.contains(registro.getPassword());
    }

    public void agregarNivel() {
        this.nivel++;
    }

    @Override
    public String toString() {
        return this.nivel + " "+ this.password;
    }

    public Boolean coincidePrimerLetra(Registro registro) {
        return this.getPassword().charAt(0) == registro.getPassword().charAt(0);
    }
}
