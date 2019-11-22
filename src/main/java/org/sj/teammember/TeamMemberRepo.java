package org.sj.teammember;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
/**
 * 
 * Extending CrudRepository provides us with CRUD methods, but we also need pagination and sorting methods, so we can extend our repository to PagingAndSortingRepository, which inherits from CrudRepository
 * @author SujeetS
 *
 */
public interface TeamMemberRepo extends PagingAndSortingRepository<TeamMember, UUID> {
	  Optional<TeamMember> findByEmailID(String emailID);
}
