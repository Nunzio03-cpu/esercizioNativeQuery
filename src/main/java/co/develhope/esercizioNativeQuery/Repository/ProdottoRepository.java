package co.develhope.esercizioNativeQuery.Repository;

import co.develhope.esercizioNativeQuery.Entity.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
}
