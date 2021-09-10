package com.infosys.coocking.service.measurementunit;

import com.infosys.coocking.model.entity.MeasurementUnitEntity;

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

}
