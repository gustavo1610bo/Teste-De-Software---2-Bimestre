package br.edu.exemplo;

public class ContaBancaria {
    private double saldo;

    public ContaBancaria() {
        this.saldo = 0;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public void sacar(double valor) {
        // Saque maior que o saldo é ignorado: o saldo não muda.
        if (valor <= saldo) {
            saldo -= valor;
        }
    }
}
