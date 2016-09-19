package com.sirding.mybatis.model;

import java.io.Serializable;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table user_info
 *
 * @mbg.generated do_not_delete_during_merge Mon Sep 19 11:52:28 CST 2016
 */
public class UserInfo implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.id
     *
     * @mbg.generated Mon Sep 19 11:52:28 CST 2016
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.name
     *
     * @mbg.generated Mon Sep 19 11:52:28 CST 2016
     */
    private String name;

    /**
     * Database Column Remarks:
     *   年龄
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.age
     *
     * @mbg.generated Mon Sep 19 11:52:28 CST 2016
     */
    private Byte age;

    /**
     * Database Column Remarks:
     *   身高
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.height
     *
     * @mbg.generated Mon Sep 19 11:52:28 CST 2016
     */
    private Double height;

    /**
     * Database Column Remarks:
     *   测试a
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.TEST_a
     *
     * @mbg.generated Mon Sep 19 11:52:28 CST 2016
     */
    private String testa;

    /**
     * Database Column Remarks:
     *   测试b
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.TEST_b
     *
     * @mbg.generated Mon Sep 19 11:52:28 CST 2016
     */
    private Double testB;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.CUST_namea
     *
     * @mbg.generated Mon Sep 19 11:52:28 CST 2016
     */
    private String namea;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.CUST_nameb
     *
     * @mbg.generated Mon Sep 19 11:52:28 CST 2016
     */
    private String nameb;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table user_info
     *
     * @mbg.generated Mon Sep 19 11:52:28 CST 2016
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.id
     *
     * @return the value of user_info.id
     *
     * @mbg.generated Mon Sep 19 11:52:28 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.id
     *
     * @param id the value for user_info.id
     *
     * @mbg.generated Mon Sep 19 11:52:28 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.name
     *
     * @return the value of user_info.name
     *
     * @mbg.generated Mon Sep 19 11:52:28 CST 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.name
     *
     * @param name the value for user_info.name
     *
     * @mbg.generated Mon Sep 19 11:52:28 CST 2016
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.age
     *
     * @return the value of user_info.age
     *
     * @mbg.generated Mon Sep 19 11:52:28 CST 2016
     */
    public Byte getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.age
     *
     * @param age the value for user_info.age
     *
     * @mbg.generated Mon Sep 19 11:52:28 CST 2016
     */
    public void setAge(Byte age) {
        this.age = age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.height
     *
     * @return the value of user_info.height
     *
     * @mbg.generated Mon Sep 19 11:52:28 CST 2016
     */
    public Double getHeight() {
        return height;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.height
     *
     * @param height the value for user_info.height
     *
     * @mbg.generated Mon Sep 19 11:52:28 CST 2016
     */
    public void setHeight(Double height) {
        this.height = height;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.TEST_a
     *
     * @return the value of user_info.TEST_a
     *
     * @mbg.generated Mon Sep 19 11:52:28 CST 2016
     */
    public String getTesta() {
        return testa;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.TEST_a
     *
     * @param testa the value for user_info.TEST_a
     *
     * @mbg.generated Mon Sep 19 11:52:28 CST 2016
     */
    public void setTesta(String testa) {
        this.testa = testa == null ? null : testa.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.TEST_b
     *
     * @return the value of user_info.TEST_b
     *
     * @mbg.generated Mon Sep 19 11:52:28 CST 2016
     */
    public Double getTestB() {
        return testB;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.TEST_b
     *
     * @param testB the value for user_info.TEST_b
     *
     * @mbg.generated Mon Sep 19 11:52:28 CST 2016
     */
    public void setTestB(Double testB) {
        this.testB = testB;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.CUST_namea
     *
     * @return the value of user_info.CUST_namea
     *
     * @mbg.generated Mon Sep 19 11:52:28 CST 2016
     */
    public String getNamea() {
        return namea;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.CUST_namea
     *
     * @param namea the value for user_info.CUST_namea
     *
     * @mbg.generated Mon Sep 19 11:52:28 CST 2016
     */
    public void setNamea(String namea) {
        this.namea = namea == null ? null : namea.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.CUST_nameb
     *
     * @return the value of user_info.CUST_nameb
     *
     * @mbg.generated Mon Sep 19 11:52:28 CST 2016
     */
    public String getNameb() {
        return nameb;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.CUST_nameb
     *
     * @param nameb the value for user_info.CUST_nameb
     *
     * @mbg.generated Mon Sep 19 11:52:28 CST 2016
     */
    public void setNameb(String nameb) {
        this.nameb = nameb == null ? null : nameb.trim();
    }
}