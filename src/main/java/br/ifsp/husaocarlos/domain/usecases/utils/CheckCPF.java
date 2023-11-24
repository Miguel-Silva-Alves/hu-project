package br.ifsp.husaocarlos.domain.usecases.utils;

public class CheckCPF {

    public static boolean checkCpf(String cpf){

        if(cpf.length() != 11){
            return false;
        }

        int soma = 0;
        int cont = 10;

        for(int i = 0; i<cpf.length() - 2; i ++){
            char c = cpf.charAt(i);
            if(!Character.isDigit(c)) return false;
            soma += Character.getNumericValue(c) * cont;
            cont -= 1;
        }

        int act = (soma * 10) % 11;

        if (act == 10) act = 0;
        if(act != Character.getNumericValue(cpf.charAt(9))){
            return false;
        }

        soma = 0;
        cont = 11;

        for(int i = 0; i<cpf.length() - 1; i ++){
            char c = cpf.charAt(i);
            if(!Character.isDigit(c)) return false;

            soma += Character.getNumericValue(c) * cont;
            cont -= 1;
        }

        act = (soma * 10) % 11;

        if (act == 10) act = 0;
        if(act != Character.getNumericValue(cpf.charAt(10))){
            return false;
        }

        return true;
    }

}
