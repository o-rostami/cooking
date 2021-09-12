package com.infosys.coocking.service.measurementunit;

import com.infosys.coocking.model.dto.PagingRequest;
import com.infosys.coocking.model.dto.PagingResponse;
import com.infosys.coocking.model.entity.MeasurementUnitEntity;

import java.util.List;

/**
 * A <i>MeasurementUnitService</i>. This interface has responsibility to fetch Measurement Unit
 * on the Ingredient <p>
 * The <tt>MeasurementUnitService</tt> interface provides some methods for completing its task
 * the role service implemented by <tt>MeasurementUnitServiceImpl</tt> class.<p>
 *
 * @author Omid Rostami
 */

public interface MeasurementUnitService {

    /**
     * Returns the details of MeasurementUnit fetched
     *
     * @param measurementUnitId the id of the MeasurementUnit needs to be fetched
     * @return the MeasurementUnitEntity entity fetched.
     */

    MeasurementUnitEntity getMeasurementUnitById(Long measurementUnitId);


    /**
     * Delete the specified MeasurementUnit
     *
     * @param measurementUnitId the id of the MeasurementUnit needs to be deleted
     */

    void deleteMeasurementUnitById(Long measurementUnitId);

    /**
     * Create the MeasurementUnit and return the id of created one
     *
     * @param measurementUnitEntity contains detail of Ingredient which is going to be created
     * @return the MeasurementUnit's id just created.
     */

    Long createMeasurementUnit(MeasurementUnitEntity measurementUnitEntity);

    /**
     * Modify and return the updated  MeasurementUnit.
     *
     * @param measurementUnitEntity contains detail of  MeasurementUnit is going to be updated
     * @return the MeasurementUnit just updated.
     */

    MeasurementUnitEntity updateMeasurementUnit(MeasurementUnitEntity measurementUnitEntity);

    /**
     * Returns the all  MeasurementUnit for admin
     *
     * @return the  MeasurementUnit list.
     */
    List<MeasurementUnitEntity> getAllIngredient();


    /**
     * Returns the details of MeasurementUnitEntity based on the filed searched
     *
     * @param pagingRequest contains the search criteria and filters
     * @return the PagingResponse fetched.
     */

    PagingResponse<MeasurementUnitEntity> searchIngredient(PagingRequest pagingRequest);

}
