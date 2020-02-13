package am.itspace.clickcommon.repository;

import am.itspace.clickcommon.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address,Long> {

}
