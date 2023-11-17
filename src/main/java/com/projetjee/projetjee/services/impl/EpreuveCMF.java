package com.projetjee.projetjee.services.cmf;
import com.projetjee.projetjee.entities.Discipline;
import com.projetjee.projetjee.entities.Epreuve;
import com.projetjee.projetjee.repository.EpreuveRepository;
import com.projetjee.projetjee.services.EpreuveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
@Service
public class EpreuveCMF implements EpreuveService {

    @Autowired
    private EpreuveRepository epreuveRepository;

    @Override
    public List<Epreuve> findAllEpreuveByDiscipline(Long id){
        return (List<Epreuve>)epreuveRepository.findAllEpreuveByIdDiscipline(id);
    }

}
