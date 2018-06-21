package teste.gamesranking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teste.gamesranking.model.Jogador;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long> {
}
