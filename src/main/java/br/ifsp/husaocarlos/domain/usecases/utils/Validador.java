package br.ifsp.husaocarlos.domain.usecases.utils;

public abstract class Validador<T> {
    public static boolean nullOrEmpty(String string){
        return string == null || string.isEmpty();
    }
}
