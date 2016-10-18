package com.sirding.mybatis.model;

import java.io.Serializable;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table app_role_menu
 *
 * @mbg.generated do_not_delete_during_merge Tue Oct 18 11:55:21 CST 2016
 */
public class AppRoleMenu implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_role_menu.id
     *
     * @mbg.generated Tue Oct 18 11:55:21 CST 2016
     */
    private Integer id;

    /**
     * Database Column Remarks:
     *   角色id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_role_menu.role_id
     *
     * @mbg.generated Tue Oct 18 11:55:21 CST 2016
     */
    private Integer roleId;

    /**
     * Database Column Remarks:
     *   菜单id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_role_menu.menu_id
     *
     * @mbg.generated Tue Oct 18 11:55:21 CST 2016
     */
    private Integer menuId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table app_role_menu
     *
     * @mbg.generated Tue Oct 18 11:55:21 CST 2016
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_role_menu.id
     *
     * @return the value of app_role_menu.id
     *
     * @mbg.generated Tue Oct 18 11:55:21 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_role_menu.id
     *
     * @param id the value for app_role_menu.id
     *
     * @mbg.generated Tue Oct 18 11:55:21 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_role_menu.role_id
     *
     * @return the value of app_role_menu.role_id
     *
     * @mbg.generated Tue Oct 18 11:55:21 CST 2016
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_role_menu.role_id
     *
     * @param roleId the value for app_role_menu.role_id
     *
     * @mbg.generated Tue Oct 18 11:55:21 CST 2016
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_role_menu.menu_id
     *
     * @return the value of app_role_menu.menu_id
     *
     * @mbg.generated Tue Oct 18 11:55:21 CST 2016
     */
    public Integer getMenuId() {
        return menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_role_menu.menu_id
     *
     * @param menuId the value for app_role_menu.menu_id
     *
     * @mbg.generated Tue Oct 18 11:55:21 CST 2016
     */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
}