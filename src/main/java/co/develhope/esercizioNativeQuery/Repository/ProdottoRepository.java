package co.develhope.esercizioNativeQuery.Repository;

import co.develhope.esercizioNativeQuery.Entity.CategoriaEnum;
import co.develhope.esercizioNativeQuery.Entity.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
    List<Prodotto> findByCategoria(CategoriaEnum categoria);
    List<Prodotto> findByNomeContaining(String nome);
    List<Prodotto> findByPrezzoOrderByPrezzoDesc(Double prezzo);
    List<Prodotto> findByPrezzoLessThan(Double prezzo);
    List<Prodotto> findByStatusTrue();
   @Query(value = "select count(*) from native.prodotto p where p.categoria = ?1",
                    nativeQuery = true)
    Long countByCategoriaAbito(CategoriaEnum categoria);
    //@Query(value = "SELECT * FROM book b WHERE b.status = ?1",
    //        nativeQuery = true)
    //List<BookEntity> findByStatus(BookStatus status);
}
