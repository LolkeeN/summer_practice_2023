package com.vasyl.summer.practice.database.repository;

import com.vasyl.summer.practice.database.entity.Section;
import org.apache.catalina.webresources.CachedResource;
import org.springframework.data.repository.CrudRepository;

public interface SectionRepository extends CrudRepository<Section, String> {

}
