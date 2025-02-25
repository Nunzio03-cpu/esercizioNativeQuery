package co.develhope.esercizioNativeQuery.Entity;

public enum CategoriaEnum {
    UTENSILE ("utensile"),
    ABITO ("abito"),
    ELETTRODOMESTICO ("elettrodomestico");


    String descrizione;

    CategoriaEnum (String descrizione){
        this.descrizione = descrizione;
    }

}

