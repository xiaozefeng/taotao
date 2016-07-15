package com.taotao.rest.pojo;

import java.util.Date;

public class SysOauthToken {

    /**
     * 临时令牌
     *
     * @mbggenerated
     */
    private String accessToken;

    /**
     * 刷新令牌，当access_token过期时用refresh_token和access_token去换取新access_token
     *
     * @mbggenerated
     */
    private String refreshToken;
    
    /**
     * 平台令牌，从平台鉴权成功后会存储下来，方便后续调用平台接口，后续会放在缓存中
     * 
     * @mbggenerated
     */
    private String platformToken;

    /**
     * 失效时间，默认失效时间为六小时
     *
     * @mbggenerated
     */
    private Date expiresIn;
    
    /**
     * 禁用标记：true：禁用、false：未禁用，默认为false
     */
    private Boolean disabled;

    /**
     * 业务域编号
     *
     * @mbggenerated
     */
    private Integer domainId;
    
    /**
     * 用户编号
     */
    private String uid;

    /**
     * 用户归属单位（集团）编号
     *
     * @mbggenerated
     */
    private Long groupId;
    
    /**
     * 用户归属部门编号
     */
    private Long deptId;

    /**
     * 客户端唯一编号
     *
     * @mbggenerated
     */
    private String clientId;

    /**
     * 修改时间
     *
     * @mbggenerated
     */
    private Date updateDate;
    
    /**
     * 创建时间
     */
    private Date createDate;


    private static final long serialVersionUID = 1L;


	public String getAccessToken() {
		return accessToken;
	}


	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}


	public String getRefreshToken() {
		return refreshToken;
	}


	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}


	public String getPlatformToken() {
		return platformToken;
	}


	public void setPlatformToken(String platformToken) {
		this.platformToken = platformToken;
	}


	public Date getExpiresIn() {
		return expiresIn;
	}


	public void setExpiresIn(Date expiresIn) {
		this.expiresIn = expiresIn;
	}


	public Boolean getDisabled() {
		return disabled;
	}


	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}


	public Integer getDomainId() {
		return domainId;
	}


	public void setDomainId(Integer domainId) {
		this.domainId = domainId;
	}


	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public Long getGroupId() {
		return groupId;
	}


	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}


	public Long getDeptId() {
		return deptId;
	}


	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}


	public String getClientId() {
		return clientId;
	}


	public void setClientId(String clientId) {
		this.clientId = clientId;
	}


	public Date getUpdateDate() {
		return updateDate;
	}


	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 获取deptId,如果deptId为空，则取groupId
	 * @author sbhuang@ewaytec.cn
	 * @date 2016年4月14日 下午8:31:35 
	 * @return
	 */
	public Long getGroupOrDeptId(){
		if(null !=deptId){
			return deptId;
		}else{
			return groupId;
		}
	}

    
}