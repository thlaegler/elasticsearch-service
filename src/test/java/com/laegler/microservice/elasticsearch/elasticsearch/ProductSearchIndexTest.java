package com.laegler.microservice.elasticsearch.elasticsearch;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.laegler.microservice.elasticsearch.model.Product;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class ProductSearchIndexTest {

  private static final Logger LOG = LoggerFactory.getLogger(ProductSearchIndexTest.class);

  // @Autowired
  // private ProductSearchService service;

  @Autowired
  private ElasticsearchTemplate esTemplate;

  // @Autowired
  // private ProductSearchRepository esRepository;

  @Before
  public void before() {
    // esTemplate.deleteIndex(ProductSearchIndex.class);
    esTemplate.createIndex(Product.class);
    // esTemplate.putMapping(ProductSearchIndex.class);
    esTemplate.refresh(Product.class);
  }

  @Test
  public void test_addProuctSearch() {
    // ProductSearchIndex productSearch = new ProductSearchIndex();
    // productSearch.setDescription("1001");
    // productSearch.setItemCode("12345");
    // productSearch.setMetaTitle("Elasticsearch Basics");
    // ProductSearchIndex testProductSearchIndex = service.addProductSearchIndex(productSearch);
    //
    // assertNotNull(testProductSearchIndex.getId());
    // assertEquals(testProductSearchIndex.getDescription(), productSearch.getDescription());
    // assertEquals(testProductSearchIndex.getItemCode(), productSearch.getItemCode());
    // assertEquals(testProductSearchIndex.getMetaTitle(), productSearch.getMetaTitle());
  }

  @Test
  public void test_findProductSearchIndex() {
    // ProductSearchIndex productSearch = new ProductSearchIndex();
    // productSearch.setDescription("1001");
    // productSearch.setItemCode("12345");
    // productSearch.setMetaTitle("Elasticsearch Basics");
    // service.addProductSearchIndex(productSearch);
    //
    // ProductSearchIndex testProductSearchIndex =
    // service.getProductSearchIndex(productSearch.getId());
    //
    // assertNotNull(testProductSearchIndex.getId());
    // assertEquals(testProductSearchIndex.getDescription(), productSearch.getDescription());
    // assertEquals(testProductSearchIndex.getItemCode(), productSearch.getItemCode());
    // assertEquals(testProductSearchIndex.getMetaTitle(), productSearch.getMetaTitle());
  }

  @Test
  public void test_getByItemCode() {
    // ProductSearchIndex productSearch = new ProductSearchIndex();
    // productSearch.setDescription("1001");
    // productSearch.setItemCode("12345");
    // productSearch.setMetaTitle("Elasticsearch Basics");
    // service.addProductSearchIndex(productSearch);
    //
    // List<ProductSearchIndex> byItemCode = service.getByItemCode(productSearch.getItemCode());
    // assertThat(byItemCode.size(), is(1));
  }

  @Test
  public void test_reindex() {
    // LOG.info("Products in index: " + service.getAllProducts().getNumberOfElements());
    //
    // ProductSearchIndex productSearch = new ProductSearchIndex();
    // productSearch.setDescription("1001");
    // productSearch.setItemCode("12345");
    // productSearch.setMetaTitle("Elasticsearch Basics");
    // service.addProductSearchIndex(productSearch);
    //
    // Product product1 = new Product();
    // product1.setDescription("Beschreibung");
    // product1.setItemCode("123");
    // product1.setIsInStock(true);
    // product1.setIsInActive(false);
    // productRepository.save(product1);
    //
    // indexService.reindex();
    //
    // LOG.info("Products in index: " + service.getAllProducts().getNumberOfElements());
  }

  // @Test
  // public void testFindByAuthor() {
  // List<ProductSearchIndex> productSearchList = new ArrayList<>();
  // productSearchList
  // .add(new ProductSearchIndex("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017"));
  // productSearchList
  // .add(new ProductSearchIndex("1002", "Apache Lucene Basics", "Rambabu Posa", "13-MAR-2017"));
  // productSearchList
  // .add(new ProductSearchIndex("1003", "Apache Solr Basics", "Rambabu Posa", "21-MAR-2017"));
  // productSearchList.add(
  // new ProductSearchIndex("1007", "Spring Data + ElasticSearch", "Rambabu Posa", "01-APR-2017"));
  // bookList.add(new ProductSearchIndex("1008", "Spring Boot + MongoDB", "Mkyong", "25-FEB-2017"));
  //
  // for (ProductSearchIndex book : bookList) {
  // bookService.save(book);
  // }
  //
  // Page<ProductSearchIndex> byAuthor = bookService.findByAuthor("Rambabu Posa", new PageRequest(0,
  // 10));
  // assertThat(byAuthor.getTotalElements(), is(4L));
  //
  // Page<ProductSearchIndex> byAuthor2 = bookService.findByAuthor("Mkyong", new PageRequest(0,
  // 10));
  // assertThat(byAuthor2.getTotalElements(), is(1L));
  // }

  @Test
  public void test_delete() {
    // ProductSearchIndex productSearch = new ProductSearchIndex();
    // productSearch.setDescription("1001");
    // productSearch.setItemCode("12345");
    // productSearch.setMetaTitle("Elasticsearch Basics");
    // service.addProductSearchIndex(productSearch);
    //
    // service.remove(productSearch);
    // ProductSearchIndex testBook = service.getById(productSearch.getId());
    // assertNull(testBook);
  }

}
