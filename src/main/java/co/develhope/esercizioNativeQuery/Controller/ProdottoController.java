package co.develhope.esercizioNativeQuery.Controller;

import co.develhope.esercizioNativeQuery.Entity.CategoriaEnum;
import co.develhope.esercizioNativeQuery.Entity.Prodotto;
import co.develhope.esercizioNativeQuery.Service.ProdottoService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<Prodotto>> selezionaOgniProdotto(){
        List<Prodotto> prodotti = prodottoService.selezionaOgniProdotto();
        return ResponseEntity.ok(prodotti);
    }

    /**
     *  Endpoint: PUT /aggiorna-prodotto/{id}
     *  Descrizione: aggiorna i field dell'oggetto cambiandone i contenuti
     *  @param id
     *  @param prodotto
     *  @return nuovo oggetto aggiornato
     */
    @PutMapping("/aggiorna-prodotto/{id}")
    public ResponseEntity<Optional<Prodotto>> aggiornaProdotto(@PathVariable Long id, @RequestBody Prodotto prodotto){
        Optional<Prodotto> prodottoOptional = prodottoService.aggiornaProdotto(id, prodotto);
        if (prodottoOptional.isPresent()){
            return ResponseEntity.ok(prodottoOptional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     *  Endpoint: DELETE /elimina-prodotto
     *  Descrizione: Elimina l'oggetto inserito
     *  @param prodotto
     *  @return l'oggetto eliminato
     */
    @DeleteMapping("/elimina-prodotto")
    public ResponseEntity<Prodotto> eliminaProdotto(@RequestBody Prodotto prodotto){
        prodottoService.eliminaProdotto(prodotto);
        return ResponseEntity.ok(prodotto);
    }

    @GetMapping("/cerca-per-categoria/{categoria}")
    public ResponseEntity<List<Prodotto>> cercaPerProdotto(@PathVariable CategoriaEnum categoria){
        List<Prodotto> prodotti = prodottoService.cercaPerCategoria(categoria);
        return ResponseEntity.ok(prodotti);
    }

//    @GetMapping("/saluto")
//    public String saluto(){
//        return "Hello World!";
//    }

    @GetMapping("/cerca-per-id/{id}")
    public ResponseEntity<Optional<Prodotto>> cercaPerId(@PathVariable Long id){
        Optional<Prodotto> prodottoOptional = prodottoService.cercaPerId(id);
        if (prodottoOptional.isPresent()){
            return ResponseEntity.ok(prodottoOptional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cerca-per-parola-chiave/{nome}")
    public ResponseEntity<List<Prodotto>> cercaPerParolaChiave(@PathVariable String nome){
        List<Prodotto> prodotti = prodottoService.cercaPerParolaChiave(nome);
        return ResponseEntity.ok(prodotti);
    }

    @GetMapping("/ordina-per-prezzo-discendente")
    public ResponseEntity<List<Prodotto>> ordinaPerPrezzoDisc(@RequestParam Double prezzo){
        List<Prodotto> prodotti = prodottoService.ordinaPerPrezzoDisc(prezzo);
        return ResponseEntity.ok(prodotti);
    }

    @GetMapping("/ordina-per-prezzo-minore")
    public ResponseEntity<List<Prodotto>> ordinaPerPrezzoMinore(@RequestParam Double prezzo){
        List<Prodotto> prodotti = prodottoService.ordinaPerPrezzoMinore(prezzo);
        return ResponseEntity.ok(prodotti);
    }
}
