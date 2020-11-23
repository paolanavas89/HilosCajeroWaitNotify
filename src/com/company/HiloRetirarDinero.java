package com.company;

public class HiloRetirarDinero extends Thread {

    private final int retirarDinero;
    FakeDB miCuentaBancaria= new FakeDB();
    public HiloRetirarDinero(int retirarDinero){
        this.retirarDinero = retirarDinero;
    }

    @Override
    public void run() {

        try {

            System.out.println("Simulamos la lectura del saldo del cliente");
            // Ponemos un delay para simular el tiempo que tarda el saldo en llegar desde el servidor al movil
            long sleepingTime = (long) (Math.random()*1000);
            sleep(sleepingTime);
            retirarDineroCuenta(this.retirarDinero);
            // Vovlemos a poner un delay para simular el tiempo que tarda la transacción en realizarse
            sleep(sleepingTime);
            System.out.println("Simulamos la escritura del saldo del cliente");
            if(miCuentaBancaria.getCash()<=0){
                System.out.println("Su cuenta esta en negativo");

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private synchronized void retirarDineroCuenta(int valorRetiro){

        if(miCuentaBancaria.getCash()>=valorRetiro){
            notifyAll();
            System.out.println("SaldoActual= "+miCuentaBancaria.getCash());
            System.out.println("El hilo esta realizando retiro ");
            miCuentaBancaria.retiroDeCuenta(this.retirarDinero);
            System.out.println("El resultado guardado en la base de datos es " + FakeDB.cash);

        }
        else{
            System.out.println("No hay saldo suficiente para realizar la transacción");
        }
    }
}

