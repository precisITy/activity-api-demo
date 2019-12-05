package com.precisity.demo.activity.db;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Spring Data JPA repository for accessing activity entities. Provides default methods for retrieving, saving and deleting
 * entities and custom methods for retrieving entities that match certain criteria (filter options).
 */
public interface ActivityRepository extends CrudRepository<ActivityEntity, Long> {

  List<ActivityEntity> findByAuthor(String author);

  List<ActivityEntity> findByHeaderContainingOrNameContainingOrShortDescriptionContainingOrProcurementChannelContainingOrAuthorContaining(String headerKeyword,
                                                                                                                                          String nameKeyword,
                                                                                                                                          String shortDescriptionKeyword,
                                                                                                                                          String procurementChannelKeyword,
                                                                                                                                          String authorKeyword);

  @Query("select a from ActivityEntity a where a.dateCreation >= ?1")
  List<ActivityEntity> findByDateCreationSince(String since);

  @Query("select a from ActivityEntity a where a.dateCreation <= ?1")
  List<ActivityEntity> findByDateCreationUntil(String until);

  @Query("select a from ActivityEntity a where a.dateCreation >= ?1 and a.dateCreation <= ?2")
  List<ActivityEntity> findByDateCreationSinceAndUntil(String since, String until);

}
