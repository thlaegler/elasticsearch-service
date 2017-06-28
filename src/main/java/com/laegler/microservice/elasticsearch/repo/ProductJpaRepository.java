package com.laegler.microservice.elasticsearch.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.laegler.microservice.elasticsearch.model.Product;

@Repository
public interface ProductJpaRepository extends PagingAndSortingRepository<Product, Long> {

  // @Query("SELECT p FROM Product p")
  // public Page<Product> findAll(Pageable pageable);

}
