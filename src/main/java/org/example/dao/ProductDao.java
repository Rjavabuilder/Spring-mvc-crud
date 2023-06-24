package org.example.dao;

import lombok.RequiredArgsConstructor;
import org.example.dto.ProductDto;
import org.example.entity.ProductEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class ProductDao {

    private final SessionFactory sessionFactory;
    public List<ProductEntity> list(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ProductEntity> productEntities =
                session.createQuery("from ProductEntity ").list();
        session.getTransaction().commit();
        session.close();
        return productEntities;
    }
    public boolean add(ProductDto productDto) {
        if(productDto.getId()!=null){
            ProductEntity product = ProductEntity.builder()
                    .id(productDto.getId())
                    .name(productDto.getName())
                    .category(productDto.getCategory())
                    .build();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
            session.close();
            return true;
        }
        ProductEntity product = ProductEntity.builder()
                .name(productDto.getName())
                .category(productDto.getCategory())
                .build();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(product);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    public boolean delete(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ProductEntity productEntity = finalById(id);
        if(productEntity!=null){
            session.remove(productEntity);
            session.getTransaction().commit();
            return true;
        }
        session.close();
        return false;
    }

    public ProductEntity finalById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ProductEntity product = session.get(ProductEntity.class, id);
        session.getTransaction().commit();
        session.close();
        return product;
    }

}
