package com.sirding.mybatis.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table oauth_client_details
 *
 * @mbg.generated do_not_delete_during_merge Mon Nov 28 21:58:01 CST 2016
 */
public class OauthClientDetails implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_details.client_id
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    private String clientId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_details.resource_ids
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    private String resourceIds;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_details.client_secret
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    private String clientSecret;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_details.scope
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    private String scope;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_details.authorized_grant_types
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    private String authorizedGrantTypes;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_details.web_server_redirect_uri
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    private String webServerRedirectUri;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_details.authorities
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    private String authorities;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_details.access_token_validity
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    private Integer accessTokenValidity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_details.refresh_token_validity
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    private Integer refreshTokenValidity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_details.create_time
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_details.archived
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    private Boolean archived;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_details.trusted
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    private Boolean trusted;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_details.autoapprove
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    private String autoapprove;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth_client_details.additional_information
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    private String additionalInformation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table oauth_client_details
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_details.client_id
     *
     * @return the value of oauth_client_details.client_id
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_details.client_id
     *
     * @param clientId the value for oauth_client_details.client_id
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_details.resource_ids
     *
     * @return the value of oauth_client_details.resource_ids
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    public String getResourceIds() {
        return resourceIds;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_details.resource_ids
     *
     * @param resourceIds the value for oauth_client_details.resource_ids
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds == null ? null : resourceIds.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_details.client_secret
     *
     * @return the value of oauth_client_details.client_secret
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_details.client_secret
     *
     * @param clientSecret the value for oauth_client_details.client_secret
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret == null ? null : clientSecret.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_details.scope
     *
     * @return the value of oauth_client_details.scope
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    public String getScope() {
        return scope;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_details.scope
     *
     * @param scope the value for oauth_client_details.scope
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    public void setScope(String scope) {
        this.scope = scope == null ? null : scope.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_details.authorized_grant_types
     *
     * @return the value of oauth_client_details.authorized_grant_types
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_details.authorized_grant_types
     *
     * @param authorizedGrantTypes the value for oauth_client_details.authorized_grant_types
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes == null ? null : authorizedGrantTypes.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_details.web_server_redirect_uri
     *
     * @return the value of oauth_client_details.web_server_redirect_uri
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    public String getWebServerRedirectUri() {
        return webServerRedirectUri;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_details.web_server_redirect_uri
     *
     * @param webServerRedirectUri the value for oauth_client_details.web_server_redirect_uri
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    public void setWebServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri == null ? null : webServerRedirectUri.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_details.authorities
     *
     * @return the value of oauth_client_details.authorities
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    public String getAuthorities() {
        return authorities;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_details.authorities
     *
     * @param authorities the value for oauth_client_details.authorities
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    public void setAuthorities(String authorities) {
        this.authorities = authorities == null ? null : authorities.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_details.access_token_validity
     *
     * @return the value of oauth_client_details.access_token_validity
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_details.access_token_validity
     *
     * @param accessTokenValidity the value for oauth_client_details.access_token_validity
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_details.refresh_token_validity
     *
     * @return the value of oauth_client_details.refresh_token_validity
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    public Integer getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_details.refresh_token_validity
     *
     * @param refreshTokenValidity the value for oauth_client_details.refresh_token_validity
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_details.create_time
     *
     * @return the value of oauth_client_details.create_time
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_details.create_time
     *
     * @param createTime the value for oauth_client_details.create_time
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_details.archived
     *
     * @return the value of oauth_client_details.archived
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    public Boolean getArchived() {
        return archived;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_details.archived
     *
     * @param archived the value for oauth_client_details.archived
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_details.trusted
     *
     * @return the value of oauth_client_details.trusted
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    public Boolean getTrusted() {
        return trusted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_details.trusted
     *
     * @param trusted the value for oauth_client_details.trusted
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    public void setTrusted(Boolean trusted) {
        this.trusted = trusted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_details.autoapprove
     *
     * @return the value of oauth_client_details.autoapprove
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    public String getAutoapprove() {
        return autoapprove;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_details.autoapprove
     *
     * @param autoapprove the value for oauth_client_details.autoapprove
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    public void setAutoapprove(String autoapprove) {
        this.autoapprove = autoapprove == null ? null : autoapprove.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth_client_details.additional_information
     *
     * @return the value of oauth_client_details.additional_information
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    public String getAdditionalInformation() {
        return additionalInformation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth_client_details.additional_information
     *
     * @param additionalInformation the value for oauth_client_details.additional_information
     *
     * @mbg.generated Mon Nov 28 21:58:01 CST 2016
     */
    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation == null ? null : additionalInformation.trim();
    }
}