package com.infosys.coocking.service.role;

import com.infosys.coocking.model.entity.RoleEntity;

/**
 * A <i>RoleService</i>. This interface has responsibility to fetch
 * role<p>
 * The <tt>RoleService</tt> interface provides one method for fetching the role
 * the role service implemented by <tt>RoleServiceImpl</tt> class.<p>
 *
 * @author Omid Rostami
 */

public interface RoleService {

    /**
     * Returns the RoleEntity
     *
     * @param roleName
     * @return the Role entity just created.
     */

    RoleEntity getRoleByName(String roleName);


}
