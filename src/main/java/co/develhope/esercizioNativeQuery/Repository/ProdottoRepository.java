package co.develhope.esercizioNativeQuery.Repository;

import co.develhope.esercizioNativeQuery.Entity.CategoriaEnum;
import co.develhope.esercizioNativeQuery.Entity.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
    List<Prodotto> findByCategoria(CategoriaEnum categoria);

    List<Prodotto> findByNomeContaining(String nome);

    List<Prodotto> findByPrezzoOrderByPrezzoDesc(Double prezzo);

    List<Prodotto> findByPrezzoLessThan(Double prezzo);

    List<Prodotto> findByStatusTrue();

    @Query(value = "select count(*) from native.prodotto p where p.categoria = ?1",
            nativeQuery = true)
    Long countByCategoria(CategoriaEnum categoria);

    @Query(value = "select * from native.prodotto p order by p.quantita_disponibile desc limit 5",
            nativeQuery = true)
    List<Prodotto> findTopFiveMostAvailableProdotto();

    @Query(value = "select * from native.prodotto p where p.data_creazione >= now() - interval 7 day",
            nativeQuery = true)
    List<Prodotto> findRecentlyProdotto();
}
