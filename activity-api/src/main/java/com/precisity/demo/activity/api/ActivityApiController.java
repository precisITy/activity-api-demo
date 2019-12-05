package com.precisity.demo.activity.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.precisity.demo.activity.db.ActivityConverter;
import com.precisity.demo.activity.db.ActivityRepository;
import com.precisity.demo.activity.model.Activity;

import io.swagger.annotations.ApiParam;
import javassist.NotFoundException;

/**
 * Implementation of the Activity REST API
 */
@Controller
public class ActivityApiController implements ActivityApi {

  /**
   * Access to the database (activity repository)
   */
  @Autowired
  private ActivityRepository repo;

  /**
   * Converter utility: ActivityEntity (DB) <-> Activity (API)
   */
  @Autowired
  private ActivityConverter converter;

  /**
   * Adds a new activity
   */
  @Override
  public ResponseEntity<Activity> addActivity(@ApiParam(value = "", required = true) @Valid @RequestBody Activity newActivity) {

    if (newActivity == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    newActivity.id(null); // ID is set by backend

    // save and return newly created activity (which might have been changed by the backend)
    newActivity = converter.fromEntity(repo.save(converter.toEntity(newActivity)));

    return new ResponseEntity<>(newActivity, HttpStatus.CREATED);
  }

  /**
   * Deletes an activity
   */
  @Override
  public ResponseEntity<Void> deleteActivity(@ApiParam(value = "", required = true) @PathVariable("id") String id) {

    try {
      findActivity(id);
    } catch (BadRequestException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } catch (NotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    repo.delete(Long.valueOf(id));

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Gets a single activity
   */
  @Override
  public ResponseEntity<Activity> getActivity(@ApiParam(value = "", required = true) @PathVariable("id") String id) {

    Activity activity;
    try {
      activity = findActivity(id);
    } catch (BadRequestException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } catch (NotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(activity, HttpStatus.OK);
  }

  /**
   * Helper function to find an Activity in the database. If anything is wrong with the given ID or the activity cannot be found,
   * a suitable exception is thrown.
   */
  private Activity findActivity(String id) throws BadRequestException, NotFoundException {

    if (StringUtils.isBlank(id)) {
      throw new BadRequestException("ID is not set");
    }

    Long idNum = toLongOrNull(id);
    if (idNum == null) {
      throw new BadRequestException("ID is not valid");
    }

    final Activity activity = converter.fromEntity(repo.findOne(idNum));
    if (activity == null) {
      throw new NotFoundException("Activity not found");
    }

    return activity;
  }

  /**
   * Helper function for converting a String ID to a Long ID
   */
  private Long toLongOrNull(String id) {

    try {
      return Long.valueOf(id.trim());
    } catch (NumberFormatException e) {
      return null;
    }
  }

  /**
   * Gets a complete or a filtered list of the activities
   */
  @Override
  public ResponseEntity<List<Activity>> getActivityList(@ApiParam(value = "author to search for") @Valid @RequestParam(value = "author", required = false) String author,
                                                        @ApiParam(value = "date (YYYY-MM-DD) for specifying a start date to filter the requested data") @Valid @RequestParam(value = "since", required = false) String since,
                                                        @ApiParam(value = "date (YYYY-MM-DD) for specifying an end date to filter the requested data") @Valid @RequestParam(value = "until", required = false) String until,
                                                        @ApiParam(value = "search keyword") @Valid @RequestParam(value = "keyword", required = false) String keyword) {

    List<Activity> activities = new ArrayList<>();

    if (StringUtils.isBlank(author) && since == null && until == null && StringUtils.isBlank(keyword)) {

      // no query params set
      activities = converter.fromEntities(repo.findAll());

    } else if (!StringUtils.isBlank(author)) {

      // author query param set
      activities = converter.fromEntities(repo.findByAuthor(author));

    } else if (!StringUtils.isBlank(keyword)) {

      // keyword query param set
      activities = converter.fromEntities(repo.findByHeaderContainingOrNameContainingOrShortDescriptionContainingOrProcurementChannelContainingOrAuthorContaining(keyword,
                                                                                                                                                                  keyword,
                                                                                                                                                                  keyword,
                                                                                                                                                                  keyword,
                                                                                                                                                                  keyword));

    } else if (since != null && until == null) {

      // since query param set (but not the until parameter)
      if (isDate(since)) {
        activities = converter.fromEntities(repo.findByDateCreationSince(since));
      } else {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }

    } else if (since == null && until != null) {

      // until query param set (but not the since parameter)
      if (isDate(until)) {
        activities = converter.fromEntities(repo.findByDateCreationUntil(until));
      } else {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }

    } else if (since != null) { // "until != null" is always true here

      // since and until query params set
      if (isDate(since) && isDate(until)) {
        activities = converter.fromEntities(repo.findByDateCreationSinceAndUntil(since, until));
      } else {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }

    }

    return new ResponseEntity<>(activities, HttpStatus.OK);
  }

  /**
   * Helper function for validating a date String (must match the format "YYYY-MM-DD").
   */
  private boolean isDate(String dateString) {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    dateFormat.setLenient(false);
    try {
      dateFormat.parse(dateString);
    } catch (ParseException pe) {
      return false;
    }
    return true;
  }

  /**
   * Updates an activity
   */
  @Override
  public ResponseEntity<Activity> updateActivity(@ApiParam(value = "", required = true) @Valid @RequestBody Activity updatedActivity,
                                                 @ApiParam(value = "", required = true) @PathVariable("id") String id) {

    // check if activity exists
    try {
      findActivity(id);
    } catch (BadRequestException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } catch (NotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // validate new activity object (must be set and ID, if set, must match the given ID)
    if (updatedActivity == null || !StringUtils.isBlank(updatedActivity.getId()) && !updatedActivity.getId().equals(id)) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    updatedActivity.id(id); // overwrite ID value (just in case)

    // save and return updated activity (which might have been changed by the backend)
    updatedActivity = converter.fromEntity(repo.save(converter.toEntity(updatedActivity)));

    return new ResponseEntity<>(updatedActivity, HttpStatus.OK);
  }

}
