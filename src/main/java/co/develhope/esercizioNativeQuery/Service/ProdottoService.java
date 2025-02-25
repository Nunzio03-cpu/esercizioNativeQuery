package co.develhope.esercizioNativeQuery.Service;

import co.develhope.esercizioNativeQuery.Entity.Prodotto;
import co.develhope.esercizioNativeQuery.Repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdottoService {
    @Autowired
    private ProdottoRepository prodottoRepository;

    public Prodotto creaProdotto(Prodotto prodotto){
        return prodottoRepository.save(prodotto);
    }

    public ArrayList<Prodotto> selezionaOgniProdotto(){
        List<Prodotto> prodotti = prodottoRepository.findAll();
        return new ArrayList<>(prodotti);
    }
}
