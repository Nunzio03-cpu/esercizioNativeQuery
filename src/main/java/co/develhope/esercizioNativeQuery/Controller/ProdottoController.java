package co.develhope.esercizioNativeQuery.Controller;

import co.develhope.esercizioNativeQuery.Entity.Prodotto;
import co.develhope.esercizioNativeQuery.Service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/prodotto")
public class ProdottoController {
    @Autowired
    private ProdottoService prodottoService;

    /**
     *  Endpoint: POST /crea-prodotto
     *  Descrizione: crea un nuovo prodotto
     *  @param prodotto
     *  @return nuovo oggetto prodotto
     */
    @PostMapping("/crea-prodotto")
    public ResponseEntity<Prodotto> creaProdotto(@RequestBody Prodotto prodotto){
        return ResponseEntity.ok(prodottoService.creaProdotto(prodotto));
    }

    /**
     *  Endpoint: GET /seleziona-ogni-prodotto
     *  Descrizione: seleziona tutti i prodotti creati
     *  @return un arraylist con tutti i prodotti creati
     */
    @GetMapping("/seleziona-ogni-prodotto")
    public ResponseEntity<ArrayList<Prodotto>> selezionaOgniProdotto(){
        List<Prodotto> prodotti = prodottoService.selezionaOgniProdotto();
        return ResponseEntity.ok(new ArrayList<>(prodotti));
    }
}
