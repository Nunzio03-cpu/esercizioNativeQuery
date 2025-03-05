package co.develhope.esercizioNativeQuery.Repository;

import co.develhope.esercizioNativeQuery.Entity.CategoriaEnum;
import co.develhope.esercizioNativeQuery.Entity.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
    List<Prodotto> findByCategoria(CategoriaEnum categoria);
    List<Prodotto> findByNomeContaining(String nome);
    List<Prodotto> findByPrezzoOrderByPrezzoDesc(Double prezzo);
    List<Prodotto> findByPrezzoLessThan(Double prezzo);
}
