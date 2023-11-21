package com.projetjee.projetjee.services.impl;

import com.projetjee.projetjee.entities.TypeSession;
import com.projetjee.projetjee.repository.TypeSessionRepository;
import com.projetjee.projetjee.services.TypeSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeSessionImpl implements TypeSessionService {

    @Autowired
    private TypeSessionRepository typeSessionRepository;

    @Override
    public List<TypeSession> getAll(){
        return typeSessionRepository.findAll();
    }
}
