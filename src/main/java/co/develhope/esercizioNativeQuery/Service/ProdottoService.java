package co.develhope.esercizioNativeQuery.Service;

import co.develhope.esercizioNativeQuery.Entity.CategoriaEnum;
import co.develhope.esercizioNativeQuery.Entity.Prodotto;
import co.develhope.esercizioNativeQuery.Repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdottoService {
    @Autowired
    private ProdottoRepository prodottoRepository;

    public Prodotto creaProdotto(Prodotto prodotto){
        Prodotto prodotto1 = prodottoRepository.save(prodotto);
        return prodotto1;
    }

    public List<Prodotto> selezionaOgniProdotto(){
        List<Prodotto> prodotti = prodottoRepository.findAll();
        return prodotti;
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

    public Prodotto eliminaProdotto(Prodotto prodotto){
        prodottoRepository.delete(prodotto);
        return prodotto;
    }

    public List<Prodotto> cercaPerCategoria(CategoriaEnum categoria){
        List<Prodotto> prodotti = prodottoRepository.findByCategoria(categoria);
        return prodotti;
    }

    public Optional<Prodotto> cercaPerId(Long id){
        Optional<Prodotto> prodottoOptional = prodottoRepository.findById(id);
        if (prodottoOptional.isPresent()){
            return prodottoOptional;
        } else {
            return Optional.empty();
        }
    }

    public List<Prodotto> cercaPerParolaChiave(String nome){
        List<Prodotto> prodotti = prodottoRepository.findByNomeContaining(nome);
        return prodotti;
    }

    public List<Prodotto> ordinaPerPrezzo(Double prezzo){
        List<Prodotto> prodotti = prodottoRepository.findByPrezzoOrderByPrezzoDesc(prezzo);
        return prodotti;
    }

    public List<Prodotto> ordinaPerPrezzoMinore(Double prezzo){
        List<Prodotto> prodotti = prodottoRepository.findByPrezzoLessThan(prezzo);
        return prodotti;
    }

    public List<Prodotto> cercaTuttiAttivi() {
        List<Prodotto> prodotti = prodottoRepository.findByStatusTrue();
        return prodotti;
    }

    public Long selezionaPerCategoriaAbito(CategoriaEnum categoria){
        Long numeroProdotto = prodottoRepository.countByCategoriaAbito(categoria);
        return numeroProdotto;
    }

}