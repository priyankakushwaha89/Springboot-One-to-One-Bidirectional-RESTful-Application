package com.example.springboot_OneToOneMastery.Repository;

import com.example.springboot_OneToOneMastery.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
}
