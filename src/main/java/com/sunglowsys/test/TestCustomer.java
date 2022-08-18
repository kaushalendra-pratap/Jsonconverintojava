package com.sunglowsys.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunglowsys.domain.Customer;
import org.apache.commons.io.FileUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class TestCustomer {
        public static void main(String[] args) throws Exception{

            // read  Json file
            String jsonData =   FileUtils.readFileToString(new File("E:/jsonAndJavaConversion/customer.json"), StandardCharsets.UTF_8);
            System.out.println(jsonData);

            // convert json to java object
            ObjectMapper objectMapper = new ObjectMapper();
            Customer customer = objectMapper.readValue(jsonData, Customer.class);
            System.out.println(customer);

            // save into database
            SessionFactory factory = new Configuration().configure().buildSessionFactory();
            Session session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
            session.close();
            factory.close();


        }
    }

