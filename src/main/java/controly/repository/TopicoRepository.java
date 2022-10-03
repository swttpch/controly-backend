package controly.repository;

import controly.model.entity.TopicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface TopicoRepository extends JpaRepository<TopicoEntity,Long> {

    @Query("SELECT " +
            "tp.id_topico as id_topico," +
            "    tp.quantidades as quantidades," +
            "t.nome " +
            "FROM(SELECT " +
            "id_topico, " +
            "count(id_topico) as quantidades " +
            "from topico_has_seguidores " +
            "GROUP BY id_topico " +
            ") AS TP" +
            "join tb_topico t" +
            "on t.id_topico = tp.id_topico ORDER BY TP.quantidades DESC;")
    public Map<String, Integer> getTopicosMostFollowed();
}
