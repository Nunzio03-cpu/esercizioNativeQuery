package co.develhope.esercizioNativeQuery.Controller;

import co.develhope.esercizioNativeQuery.Entity.CategoriaEnum;
import co.develhope.esercizioNativeQuery.Entity.Prodotto;
import co.develhope.esercizioNativeQuery.Service.ProdottoService;
import jakarta.websocket.server.PathParam;
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

    /**
     * Endpoint: GET /cerca-parola-chiave-nome-o-descrizione
     * Descrizione: cerca il prodotto tramite una parola chiave che può
     * essere contenuta o nel nome o nella descrizione;
     *
     * @param nome
     * @param descrizione
     * @return
     */
    @GetMapping("/cerca-parola-chiave-nome-o-descrizione")
    public ResponseEntity<List<Prodotto>> cercaParolaChiaveNomeODescrizione(@RequestParam String nome,
                                                                            @RequestParam String descrizione) {
        List<Prodotto> prodotti = prodottoService.cercaParolaChiaveNomeODescrizione(nome, descrizione);
        return ResponseEntity.ok(prodotti);
    }

    /**
     * Endpoint: GET /prezzo-medio-per-categoria/{categoria}
     * Descrizione: trova il prezzo medio dei prodotti di una categoria
     *
     * @param categoria
     * @return il prezzo medio dei prodotti appartenenti alla categoria cercata
     */
    @GetMapping("/prezzo-medio-per-categoria/{categoria}")
    public ResponseEntity<Double> prezzoMedioPerCategoria(@PathVariable CategoriaEnum categoria) {
        Double prezzoMedio = prodottoService.prezzoMedioPerCategoria(categoria.name());
        return ResponseEntity.ok(prezzoMedio);
    }

    /**
     * Endpoint: PUT /cancellazione-logica/{id}
     * Descrizione: metodo che elimina logicamente un prodotto traminte la setStatus(false)
     *
     * @param id
     * @return prodotto con il proprio campo status = false
     */
    @PutMapping("/cancellazione-logica/{id}")
    public ResponseEntity<Optional<Prodotto>> cancellazioneLogica(@PathVariable Long id) {
        Optional<Prodotto> prodottoOptional = prodottoService.cancellazioneLogica(id);
        if (prodottoOptional.isPresent()) {
            return ResponseEntity.ok(prodottoOptional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint: PUT /attiva-status/{id}
     * Descrizione: metodo che riattiva il prodotto negli oggetti attivi
     *
     * @param id
     * @return prodotto con il proprio campo status = true
     */
    @PutMapping("/attiva-status/{id}")
    public ResponseEntity<Optional<Prodotto>> attivaStatus(@PathVariable Long id) {
        Optional<Prodotto> prodottoOptional = prodottoService.attivaStatus(id);
        if (prodottoOptional.isPresent()) {
            return ResponseEntity.ok(prodottoOptional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint: GET /cerca-ultimi-disponibili
     * Descrizione: metodo che trova i prodotti più recenti inseriti nell'ultima settimana
     *
     * @param quantitaDisponibile
     * @return lista dei prodotti inseriti nell'ultima settimana
     */
    @GetMapping("/cerca-ultimi-disponibili")
    public ResponseEntity<List<Prodotto>> cercaUltimiDisponibili(@RequestParam Integer quantitaDisponibile) {
        List<Prodotto> prodotti = prodottoService.cercaUltimiDisponibili(quantitaDisponibile);
        return ResponseEntity.ok(prodotti);
    }

    /**
     * Endpoint: GET /cerca-per-prezzo-tra
     * Descrizione: metodo che cerca i prodotti che hanno un prezzo che rientra in dato intervallo
     *
     * @param prezzoMinimo
     * @param prezzoMassimo
     * @return lista dei prodotti che hanno un prezzo che rientra nell'intervallo dato
     */
    @GetMapping("/cerca-per-prezzo-tra")
    public ResponseEntity<List<Prodotto>> cercaPerPrezzoTra(@RequestParam Double prezzoMinimo,
                                                            @RequestParam Double prezzoMassimo) {
        List<Prodotto> prodotti = prodottoService.cercaPerPrezzoTra(prezzoMinimo, prezzoMassimo);
        return ResponseEntity.ok(prodotti);
    }

    /**
     * Endpoint: GET /cerca-per-categoria-ordinati-per-prezzo
     * Descrizione: metodo che cerca per categoria i prodotti e poi li ordina per prezzo
     * dal più al meno costoso
     *
     * @param categoria
     * @return lista di prodotti appartenenti alla stessa categoria ordinati per prezzo in ordine decrescente
     */
    @GetMapping("/cerca-per-categoria-ordinati-per-prezzo")
    public ResponseEntity<List<Prodotto>> cercaPerCategoriaOrdinatiPerPrezzo(@RequestParam CategoriaEnum categoria) {
        List<Prodotto> prodotti = prodottoService.cercaPerCategoriaOrdinatiPerPrezzo(categoria);
        return ResponseEntity.ok(prodotti);
    }
}