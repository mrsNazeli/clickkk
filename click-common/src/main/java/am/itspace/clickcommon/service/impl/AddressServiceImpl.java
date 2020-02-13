package am.itspace.clickcommon.service.impl;

import am.itspace.clickcommon.model.Address;
import am.itspace.clickcommon.repository.AddressRepository;
import am.itspace.clickcommon.service.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void save(Address address) {
        addressRepository.save(address);
    }
}
