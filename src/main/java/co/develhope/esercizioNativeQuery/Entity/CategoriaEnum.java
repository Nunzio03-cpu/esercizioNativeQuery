package co.develhope.esercizioNativeQuery.Entity;

public enum CategoriaEnum {
    UTENSILE ("utensile"),
    ABITO ("abito"),
    ELETTRODOMESTICO ("elettrodomestico");


    private final String descrizione;

    CategoriaEnum (String descrizione){
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }
}

