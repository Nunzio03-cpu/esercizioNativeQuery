package co.develhope.esercizioNativeQuery.Controller;

import co.develhope.esercizioNativeQuery.Entity.Prodotto;
import co.develhope.esercizioNativeQuery.Service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prodotto")
public class ProdottoController {
    @Autowired
    private ProdottoService prodottoService;

    @PostMapping("/crea-prodotto")
    public ResponseEntity<Prodotto> creaProdotto(@RequestBody Prodotto prodotto){
        return ResponseEntity.ok(prodottoService.creaProdotto(prodotto));
    }
}
