package co.develhope.esercizioNativeQuery.Controller;

import co.develhope.esercizioNativeQuery.Entity.CategoriaEnum;
import co.develhope.esercizioNativeQuery.Entity.Prodotto;
import co.develhope.esercizioNativeQuery.Service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prodotto")
public class ProdottoController {
    @Autowired
    private ProdottoService prodottoService;

    /**
     * Endpoint: POST /crea-prodotto
     * Descrizione: crea un nuovo prodotto
     *
     * @param prodotto
     * @return nuovo oggetto prodotto
     */
    @PostMapping("/crea-prodotto")
    public ResponseEntity<Prodotto> creaProdotto(@RequestBody Prodotto prodotto) {
        return ResponseEntity.ok(prodottoService.creaProdotto(prodotto));
    }

    /**
     * Endpoint: GET /seleziona-ogni-prodotto
     * Descrizione: seleziona tutti i prodotti creati
     *
     * @return un arraylist con tutti i prodotti creati
     */
    @GetMapping("/seleziona-ogni-prodotto")
    public ResponseEntity<List<Prodotto>> selezionaOgniProdotto() {
        List<Prodotto> prodotti = prodottoService.selezionaOgniProdotto();
        return ResponseEntity.ok(prodotti);
    }

    /**
     * Endpoint: PUT /aggiorna-prodotto/{id}
     * Descrizione: aggiorna i field dell'oggetto cambiandone i contenuti
     *
     * @param id
     * @param prodotto
     * @return nuovo oggetto aggiornato
     */
    @PutMapping("/aggiorna-prodotto/{id}")
    public ResponseEntity<Optional<Prodotto>> aggiornaProdotto(@PathVariable Long id, @RequestBody Prodotto prodotto) {
        Optional<Prodotto> prodottoOptional = prodottoService.aggiornaProdotto(id, prodotto);
        if (prodottoOptional.isPresent()) {
            return ResponseEntity.ok(prodottoOptional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint: DELETE /elimina-prodotto
     * Descrizione: Elimina l'oggetto inserito
     *
     * @param prodotto
     * @return l'oggetto eliminato
     */
    @DeleteMapping("/elimina-prodotto")
    public ResponseEntity<Prodotto> eliminaProdotto(@RequestBody Prodotto prodotto) {
        prodottoService.eliminaProdotto(prodotto);
        return ResponseEntity.ok(prodotto);
    }

    /**
     * Endpoint: GET /cerca-per-categoria/{categoria}
     * Descrizione: Cerca i prodotti tramite la loro categoria
     *
     * @param categoria
     * @return lista di prodotti appartenenti alla categoria cercata
     */
    @GetMapping("/cerca-per-categoria/{categoria}")
    public ResponseEntity<List<Prodotto>> cercaPerProdotto(@PathVariable CategoriaEnum categoria) {
        List<Prodotto> prodotti = prodottoService.cercaPerCategoria(categoria);
        return ResponseEntity.ok(prodotti);
    }

    /**
     * Endpoint: GET /cerca-per-id/{id}
     * Descrizione: Cerca un prodotto tramite il suo id univoco
     *
     * @param id
     * @return prodotto con id cercato
     */
    @GetMapping("/cerca-per-id/{id}")
    public ResponseEntity<Optional<Prodotto>> cercaPerId(@PathVariable Long id) {
        Optional<Prodotto> prodottoOptional = prodottoService.cercaPerId(id);
        if (prodottoOptional.isPresent()) {
            return ResponseEntity.ok(prodottoOptional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint: GET /cerca-per-parola-chiave/{nome}
     * Descrizione: Cerca il prodotto tramite una parola chiave
     *
     * @param nome
     * @return lista di prodotti contenenti la parola chiave
     */
    @GetMapping("/cerca-per-parola-chiave/{nome}")
    public ResponseEntity<List<Prodotto>> cercaPerParolaChiave(@PathVariable String nome) {
        List<Prodotto> prodotti = prodottoService.cercaPerParolaChiave(nome);
        return ResponseEntity.ok(prodotti);
    }

    /**
     * Endpoint: GET /ordina-per-prezzo-discendente
     * Descrizione: Cerca i prodotti per prezzo
     *
     * @param prezzo
     * @return lista di prodotti in ordine di prezzo
     */
    @GetMapping("/cerca-per-prezzo")
    public ResponseEntity<List<Prodotto>> cercaPerPrezzo(@RequestParam Double prezzo) {
        List<Prodotto> prodotti = prodottoService.cercaPerPrezzo(prezzo);
        return ResponseEntity.ok(prodotti);
    }

    /**
     * Endpoint: GET /ordina-per-prezzo-minore-di
     * Descrizione: ordina i prodotti dal più al meno costoso
     *
     * @param prezzo
     * @return lista di prodotti con ordine di prezzo discendente
     */
    @GetMapping("/ordina-per-prezzo-minore-di")
    public ResponseEntity<List<Prodotto>> ordinaPerPrezzoMinore(@RequestParam Double prezzo) {
        List<Prodotto> prodotti = prodottoService.ordinaPerPrezzoMinoreDi(prezzo);
        return ResponseEntity.ok(prodotti);
    }

    /**
     * Endpoint: GET /cerca-tutti-attivi
     * Descrizione: cerca tutti i prodotti attivi
     *
     * @return lista di prodotti con status attivo
     */
    @GetMapping("/cerca-tutti-attivi")
    public ResponseEntity<List<Prodotto>> cercaTuttiAttivi() {
        List<Prodotto> prodotti = prodottoService.cercaTuttiAttivi();
        return ResponseEntity.ok(prodotti);
    }

    /**
     * Endpoint: GET /conta-per-categoria
     * Descrizione: conta quanti prodotti appartengono ad una categoria
     *
     * @param categoria
     * @return numero di prodotti con quella categoria
     */
    @GetMapping("/conta-per-categoria")
    public ResponseEntity<Long> contaPerCategoria(@RequestParam CategoriaEnum categoria) {
        Long numeroProdotto = prodottoService.contaPerCategoria(categoria);
        return ResponseEntity.ok(numeroProdotto);
    }

    /**
     * Endpoint: GET /cerca-cinque-prodotti-piu-disponibili
     * Descrizione: cerca i 5 prodotti con quantità più alta
     *
     * @return lista dei cinque prodotti più dispoinibili
     */
    @GetMapping("/cerca-cinque-prodotti-piu-disponibili")
    public ResponseEntity<List<Prodotto>> cercaCinqueProdottiPiuDisponibili() {
        List<Prodotto> prodotti = prodottoService.cercaCinqueProdottiPiuDisponibili();
        return ResponseEntity.ok(prodotti);
    }

    /**
     * Endpoint: GET /cerca-prodotti-piu-recenti
     * Descrizione: cerca i prodotti inseriti nell'ultima settimana
     *
     * @return lista di prodotti in ordine di inserimento da più al meno recente
     */
    @GetMapping("/cerca-prodotti-piu-recenti")
    public ResponseEntity<List<Prodotto>> cercaProdottiPiuRecenti() {
        List<Prodotto> prodotti = prodottoService.cercaProdottiPiuRecenti();
        return ResponseEntity.ok(prodotti);
    }
}