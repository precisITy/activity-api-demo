package com.precisity.demo.activity.db;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Component;

import com.precisity.demo.activity.model.Activity;
import com.precisity.demo.activity.model.Activity.StatusEnum;

/**
 * Provides functionality to convert between {@link Activity} objects and {@link ActivityEntity} objects
 */
@Component
public class ActivityConverter {

  public List<Activity> fromEntities(final Iterable<ActivityEntity> entities) {

    return StreamSupport.stream(entities.spliterator(), false).map(this::fromEntity).collect(Collectors.toList());
  }

  public Activity fromEntity(final ActivityEntity entity) {

    if (entity == null) {
      return null;
    }

    final Activity activity = new Activity();

    activity.setId(entity.getId() != null ? entity.getId().toString() : null);
    activity.setAuthor(entity.getAuthor());
    activity.setDateCreation(entity.getDateCreation());
    activity.setHeader(entity.getHeader());
    activity.setName(entity.getName());
    activity.setPrice(entity.getPrice());
    activity.setProcurementChannel(entity.getProcurementChannel());
    activity.setQuantity(entity.getQuantity());
    activity.setShortDescription(entity.getShortDescription());
    activity.setStatus(entity.getStatus() != null ? StatusEnum.fromValue(entity.getStatus()) : null);

    return activity;
  }

  public ActivityEntity toEntity(final Activity activity) {

    if (activity == null) {
      return null;
    }

    final ActivityEntity entity = new ActivityEntity();

    entity.setId(activity.getId() != null ? Long.valueOf(activity.getId()) : null);
    entity.setAuthor(activity.getAuthor());
    entity.setDateCreation(activity.getDateCreation());
    entity.setHeader(activity.getHeader());
    entity.setName(activity.getName());
    entity.setPrice(activity.getPrice());
    entity.setProcurementChannel(activity.getProcurementChannel());
    entity.setQuantity(activity.getQuantity());
    entity.setShortDescription(activity.getShortDescription());
    entity.setStatus(activity.getStatus() != null ? activity.getStatus().toString() : null);

    return entity;
  }

}
