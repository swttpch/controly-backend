package controly.model.service;

import controly.model.entity.TopicoEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicoService {
    List<TopicoEntity> topicoEntityList = new ArrayList<>();

    public ResponseEntity<List<TopicoEntity>> getTopicos(){

        if(true){
            return ResponseEntity.status(200).body(topicoEntityList);
        } else {
            return ResponseEntity.status(404).body(null);
        }

    }
}
