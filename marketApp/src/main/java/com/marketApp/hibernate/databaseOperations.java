/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marketApp.hibernate;

import com.marketApp.dataBases.Products;
import com.marketApp.dataBases.Users;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author emirh
 */
public class databaseOperations {

    // kullanıcı için komutlar
    public void addUser(String userName, String password, String name, String sName) {
        Users instance = new Users(userName, password, name, sName);
        Transaction transaction = null;
        try (Session session = util.getSessionFactory().openSession()) {
            if (getUser(userName) == null) {
                transaction = session.beginTransaction();
                session.save(instance);
                transaction.commit();
            } else {
                JOptionPane.showMessageDialog(null, "Böyle bir üye zaten var.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    public Users getUser(String userName) {
        Transaction transaction = null;
        String foo = "FROM Users WHERE userName = '" + userName + "'";
        try (Session session = util.getSessionFactory().openSession()) {
            List<Users> users = session.createQuery(foo).list();
            return users.get(0);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return null;
        }
    }

    public void deleteUser(String username) {
        Transaction transaction = null;
        try (Session session = util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Object Users = session.load(Users.class, username);
            if (Users != null) {
                session.delete(Users);
                transaction.commit();
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

        }
    }

    // ürünler için komutlar
    public void addProduct(String pName, float pPrice, String pOrigin, int pCount) {
        Products instance = new Products(pName, pPrice, pOrigin, pCount);
        Transaction transaction = null;
        if (getProductByID(getIDByProductName(pName)) == null) {
            try (Session session = util.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                session.save(instance);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    public Products getProductByID(int ID) {
        Transaction transaction = null;
        String foo = "FROM  Products WHERE id = " + ID;
        try (Session session = util.getSessionFactory().openSession()) {
            List<Products> product = session.createQuery(foo).list();
            return product.get(0);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return null;
        }
    }

    public void deleteProductById(int ID) {
        Transaction transaction = null;
        try (Session session = util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Object product = session.load(Products.class, ID);
            if (product != null) {
                session.delete(product);
                transaction.commit();
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

        }
    }

    public int getIDByProductName(String pName) {
        Transaction transaction = null;
        String foo = "FROM  Products WHERE product_name = '" + pName + "'";
        try (Session session = util.getSessionFactory().openSession()) {
            List<Products> product = session.createQuery(foo).list();
            return product.get(0).getID();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return 0;
        }
    }
//ürünleri ve kullanıcılar liste olarak alma.
    public List<Products> getProductsAsList() {
        Transaction transaction = null;
        String foo = "FROM  Products ";
        try (Session session = util.getSessionFactory().openSession()) {
            List<Products> product = session.createQuery(foo).list();
            return product;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Users> getUsersAsList(){
        Transaction transaction = null;
        String foo = "FROM Users";
        try (Session session = util.getSessionFactory().openSession()){
            List<Users> user = session.createQuery(foo).list();
            return user;
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }
//count u yeniler.
    public boolean updateProductCountById(int id, String process, int count) {
        Transaction transaction = null;
        switch (process) {
            case "azalt":
                try (Session session = util.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                if (getProductByID(id).getCount() != 0) {
                    Products product = session.load(Products.class, id);
                    product.setCount(product.getCount() - 1);
                    session.update(product);
                    transaction.commit();
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Ürün depoda mevcut değil!");
                    return false;
                }
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
                return false;

            }

            case "arttır":
                try (Session session = util.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();

                Products product = session.load(Products.class, id);
                product.setCount(product.getCount() + 1);
                session.update(product);
                transaction.commit();
                return true;
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
                return false;

            }
            case "coklu arttır":
                try (Session session = util.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                Products product = session.load(Products.class, id);
                product.setCount(product.getCount() + count);
                session.update(product);
                transaction.commit();
                return true;
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
                return false;

            }
            default:
                break;
        }
        return false;
    }

}
