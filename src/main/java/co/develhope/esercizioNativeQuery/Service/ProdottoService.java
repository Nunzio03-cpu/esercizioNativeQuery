package co.develhope.esercizioNativeQuery.Service;

import co.develhope.esercizioNativeQuery.Entity.Prodotto;
import co.develhope.esercizioNativeQuery.Repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<Prodotto> aggiornaProdotto(Long id, Prodotto prodottoAggiornato){
        Optional<Prodotto> prodottoOptional = prodottoRepository.findById(id);
        if (prodottoOptional.isPresent()){
            prodottoOptional.get().setNome(prodottoAggiornato.getNome());
            prodottoOptional.get().setDescrizione(prodottoAggiornato.getDescrizione());
            prodottoOptional.get().setPrezzo(prodottoAggiornato.getPrezzo());
            prodottoOptional.get().setCategoria(prodottoAggiornato.getCategoria());
            prodottoOptional.get().setQuantitaDisponibile(prodottoAggiornato.getQuantitaDisponibile());
            prodottoOptional.get().setDataCreazione(prodottoAggiornato.getDataCreazione());
            Prodotto prodotto = prodottoRepository.save(prodottoOptional.get());
            return Optional.of(prodotto);
        } else {
            return Optional.empty();
        }
    }
}