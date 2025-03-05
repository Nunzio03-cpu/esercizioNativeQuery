package co.develhope.esercizioNativeQuery;

import ch.qos.logback.core.boolex.Matcher;
import co.develhope.esercizioNativeQuery.Controller.ProdottoController;
import co.develhope.esercizioNativeQuery.Entity.CategoriaEnum;
import co.develhope.esercizioNativeQuery.Entity.Prodotto;
import co.develhope.esercizioNativeQuery.Service.ProdottoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class EsercizioNativeQueryApplicationTests {
    public static final String IDNOTFOUND = "999";
    public static final Double PREZZOMINIMO = 1000.0;
    @Autowired
    private ProdottoController prodottoController;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int port;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private ProdottoService prodottoService;
    private Prodotto prodotto;
    private Prodotto prodottoNotFound;


    @BeforeEach
    public void setUp() {
        prodotto = new Prodotto();
        prodotto.setId(3L);
        prodotto.setNome("Test Prodotto");
        prodotto.setCategoria(CategoriaEnum.ELETTRODOMESTICO);
        prodotto.setPrezzo(100.0);

        prodottoNotFound = new Prodotto();
        prodottoNotFound.setId(Long.valueOf(IDNOTFOUND));
        prodottoNotFound.setNome("Test Prodotto");
        prodottoNotFound.setCategoria(CategoriaEnum.ABITO);
        prodottoNotFound.setPrezzo(10.0);
    }


    @Test
    public void testCreaProdotto() throws Exception {
        // Step 1: prima di testare andiamo a memorizzare un prodotto di test utilizzando direttamente il service
        // per salvare qualsiasi oggetto si utilizza il when con questa chiamata
        when(prodottoService.creaProdotto(any(Prodotto.class))).thenReturn(prodotto);
        // Step 2: tramite MockMvc andiamo a richiamare il controller usando il metodo create e controlliamo che il
        // risultato del controllerCreate sia uguale al risultato dell'oggetto di test che abbiamo memorizzato in precedenza
        mockMvc.perform(post("/prodotto/crea-prodotto")// chiamo l'URL del controller
                        .contentType(MediaType.APPLICATION_JSON) // setto il tipo
                        .content(objectMapper.writeValueAsString(prodotto))) // controlliamo che il Json di ritorno sia uguale
                // al json dell'oggetto che abbiamo creato
                .andDo(print())
                .andExpect(status().isOk())// controlliamo che il codice status sia ok
                .andExpect(jsonPath("$.nome").value("Test Prodotto")); // controllo opzionale

    }

    @Test
    public void testSelezionaOgniProdotto() throws Exception {
        when(prodottoService.selezionaOgniProdotto()).thenReturn(Collections.singletonList(prodotto));

        mockMvc.perform(get("/prodotto/seleziona-ogni-prodotto"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testAggiornaProdottoOk() throws Exception {
        when(prodottoService.aggiornaProdotto(anyLong(), any(Prodotto.class)))
                .thenReturn(Optional.of(prodotto));
        mockMvc.perform(put("/prodotto/aggiorna-prodotto/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prodotto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Test Prodotto"));
    }

    @Test
    public void testAggiornaProdottoNotFound() throws Exception {
        when(prodottoService.aggiornaProdotto(anyLong(), any(Prodotto.class)))
                .thenReturn(Optional.empty());
        mockMvc.perform(put("/prodotto/aggiorna-prodotto/" + IDNOTFOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prodottoNotFound)))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    public void testEliminaProdotto() throws Exception {
        when(prodottoService.eliminaProdotto(any(Prodotto.class)))
                .thenReturn(prodotto);
        mockMvc.perform(delete("/prodotto/elimina-prodotto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prodotto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Test Prodotto"));
    }

    @Test
    public void testCercaPerIdOk() throws Exception {
        when(prodottoService.cercaPerId(anyLong())).thenReturn(Optional.of(prodotto));
        mockMvc.perform(get("/prodotto/cerca-per-id/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prodotto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Test Prodotto"));
    }

    @Test
    public void testCercaPerIdNotFound() throws Exception {
        when(prodottoService.cercaPerId(anyLong())).thenReturn(Optional.empty());
        mockMvc.perform(get("/prodotto/cerca-per-id/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prodotto)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCarcaPerCategoria() throws Exception {
        CategoriaEnum categoria = CategoriaEnum.ABITO;
        when(prodottoService.cercaPerCategoria(categoria)).thenReturn(Collections.singletonList(prodotto));
        mockMvc.perform(get("/prodotto/cerca-per-categoria/ABITO")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prodotto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]nome").value("Test Prodotto"));
    }

    @Test
    public void testCercaPerParolaChiave() throws Exception {
        when(prodottoService.cercaPerParolaChiave("Test Prodotto")).thenReturn(Collections.singletonList(prodotto));
        mockMvc.perform(get("/prodotto/cerca-per-parola-chiave/Test Prodotto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prodotto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]nome").value(Matchers.equalToIgnoringWhiteSpace("Test Prodotto")));
    }

    @Test
    public void testOrdinaPerPrezzo() throws Exception {
        when(prodottoService.ordinaPerPrezzo(100.0)).thenReturn(Collections.singletonList(prodotto));
        mockMvc.perform(get("/prodotto/ordina-per-prezzo-discendente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prodotto))
                        .param("prezzo", "100.0"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testOrdinaPerPrezzoMinimo() throws Exception {
        when(prodottoService.ordinaPerPrezzoMinore(PREZZOMINIMO)).thenReturn(Collections.singletonList(prodotto));
        mockMvc.perform(get("/prodotto/ordina-per-prezzo-minore")
                        .param("prezzo", String.valueOf(PREZZOMINIMO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]prezzo").value(Matchers.lessThan(PREZZOMINIMO)));
    }


}