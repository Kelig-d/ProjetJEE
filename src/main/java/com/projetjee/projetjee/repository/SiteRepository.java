package com.projetjee.projetjee.repository;

import com.projetjee.projetjee.entities.Site;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SiteRepository extends JpaRepository<Site, Long> {
    List<Site.SiteCategory> findAllProjectedBy();
}
