package com.sirding.mybatis.model;

import java.io.Serializable;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table app_role
 *
 * @mbg.generated do_not_delete_during_merge Tue Oct 18 11:55:17 CST 2016
 */
public class AppRole implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_role.id
     *
     * @mbg.generated Tue Oct 18 11:55:17 CST 2016
     */
    private Integer id;

    /**
     * Database Column Remarks:
     *   角色名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_role.name
     *
     * @mbg.generated Tue Oct 18 11:55:17 CST 2016
     */
    private String name;

    /**
     * Database Column Remarks:
     *   角色启用的状态0：禁用 1：启用
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_role.status
     *
     * @mbg.generated Tue Oct 18 11:55:17 CST 2016
     */
    private Byte status;

    /**
     * Database Column Remarks:
     *   角色的描述信息
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_role.note
     *
     * @mbg.generated Tue Oct 18 11:55:17 CST 2016
     */
    private String note;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table app_role
     *
     * @mbg.generated Tue Oct 18 11:55:17 CST 2016
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_role.id
     *
     * @return the value of app_role.id
     *
     * @mbg.generated Tue Oct 18 11:55:17 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_role.id
     *
     * @param id the value for app_role.id
     *
     * @mbg.generated Tue Oct 18 11:55:17 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_role.name
     *
     * @return the value of app_role.name
     *
     * @mbg.generated Tue Oct 18 11:55:17 CST 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_role.name
     *
     * @param name the value for app_role.name
     *
     * @mbg.generated Tue Oct 18 11:55:17 CST 2016
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_role.status
     *
     * @return the value of app_role.status
     *
     * @mbg.generated Tue Oct 18 11:55:17 CST 2016
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_role.status
     *
     * @param status the value for app_role.status
     *
     * @mbg.generated Tue Oct 18 11:55:17 CST 2016
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_role.note
     *
     * @return the value of app_role.note
     *
     * @mbg.generated Tue Oct 18 11:55:17 CST 2016
     */
    public String getNote() {
        return note;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_role.note
     *
     * @param note the value for app_role.note
     *
     * @mbg.generated Tue Oct 18 11:55:17 CST 2016
     */
    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}