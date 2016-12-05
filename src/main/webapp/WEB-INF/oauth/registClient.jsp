<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fun" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>注册client</title>
    <jsp:include page="/static/layout/inc.jsp" />
</head>
<body>
<div style="width: 76%;margin-left: 12%">
<!-- 头部信息 -->
<a href="${pageContext.request.contextPath}/toHome">Home</a>

<h2 align="center">注册client</h2>

<div>
    <div>
        <form id="clientForm" class="form-horizontal" action="registClient" method="post">
        	<input type="hidden" name="resubmitToken" value="${resubmitToken}" >
            <div class="form-group">
                <label for="clientId" class="col-sm-2 control-label">client_id<em class="text-danger">*</em></label>

                <div class="col-sm-10">
                    <input name="clientId" class="form-control" id="clientId" placeholder="client_id" />
                    <p class="help-block">
                    	client_id必须输入,且必须唯一,长度至少5位; 在实际应用中的另一个名称叫appKey,与client_id是同一个概念.
                    </p>
                </div>
            </div>
            <div class="form-group">
                <label for="clientSecret" class="col-sm-2 control-label">client_secret<em class="text-danger">*</em></label>
                <div class="col-sm-10">
                    <input name="clientSecret" class="form-control" id="clientSecret" placeholder="client_secret" />
                    <p class="help-block">
                    	client_secret必须输入,且长度至少8位; 在实际应用中的另一个名称叫appSecret,与client_secret是同一个概念.
                    </p>
                </div>
            </div>
            <div class="form-group">
                <label for="resourceIds" class="col-sm-2 control-label">resource_ids<em class="text-danger">*</em></label>
                <div class="col-sm-10">
                    <select name="resourceIds" class="form-control" id="resourceIds">
                        <option value="unity-resource">unity-resource</option>
                        <option value="mobile-resource">mobile-resource</option>
                        <option value="unity-resource,mobile-resource">unity-resource,mobile-resource</option>
                    </select>
                    <p class="help-block">
                    	resourceIds必须选择; 可选值必须来源于与<code>security.xml</code>中标签<code>&lsaquo;oauth2:resource-server</code>的属性<code>resource-id</code>值
                    </p>
                </div>
            </div>

            <div class="form-group">
                <label for="scope" class="col-sm-2 control-label">scope<em class="text-danger">*</em></label>

                <div class="col-sm-10">
                    <select name="scope" id="scope" class="form-control">
                        <option value="read">read</option>
                        <option value="write">write</option>
                        <option value="read,write">read write</option>
                    </select>
                    <p class="help-block">scope必须选择</p>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label">grant_type(s)<em class="text-danger">*</em></label>

                <div class="col-sm-10">
                    <label class="checkbox-inline">
                        <input type="checkbox" name="authorizedGrantTypes" value="authorization_code" ${fun:containsIgnoreCase(formDto.authorizedGrantTypes, 'authorization_code') ?'checked':''} />
                        authorization_code
                    </label>
                    <label class="checkbox-inline">
                        <input type="checkbox" name="authorizedGrantTypes" value="password" ${fun:containsIgnoreCase(formDto.authorizedGrantTypes, 'authorization_code') ?'checked':''} />
                        password
                    </label>
                    <label class="checkbox-inline">
                        <input type="checkbox" name="authorizedGrantTypes" value="implicit" ${fun:containsIgnoreCase(formDto.authorizedGrantTypes, 'implicit') ?'checked':''} />
                        implicit
                    </label>
                    <label class="checkbox-inline">
                        <input type="checkbox" name="authorizedGrantTypes" value="client_credentials" ${fun:containsIgnoreCase(formDto.authorizedGrantTypes, 'client_credentials') ?'checked':''} />
                        client_credentials
                    </label>
                    <label class="checkbox-inline">
                        <input type="checkbox" name="authorizedGrantTypes"
                               value="refresh_token" ${fun:containsIgnoreCase(formDto.authorizedGrantTypes, 'refresh_token') ?'checked':''} />
                        refresh_token
                    </label>

                    <p class="help-block">
                    	至少勾选一项grant_type(s), 且不能只单独勾选<code>refresh_token</code>
                    </p>
                </div>
            </div>

            <div class="form-group">
                <label for="webServerRedirectUri" class="col-sm-2 control-label">redirect_uri</label>

                <div class="col-sm-10">
                    <input name="webServerRedirectUri" id="webServerRedirectUri" placeholder="redirect_uri" class="form-control"/>
                    <p class="help-block">
                    	若<code>grant_type</code>包括<em>authorization_code</em>或<em>implicit</em>,则必须输入redirect_uri
                    </p>
                </div>
            </div>

            <div class="form-group">
                <label for="authorities" class="col-sm-2 control-label">authorities</label>
                <div class="col-sm-10">
                    <select name="authorities" id="authorities" class="form-control">
                        <option value="">无</option>
                        <option value="ROLE_USER,ROLE_UNITY">ROLE_UNITY</option>
                        <option value="ROLE_USER,ROLE_MOBILE">ROLE_MOBILE</option>
                        <option value="ROLE_USER,ROLE_UNITY,ROLE_MOBILE">ROLE_UNITY,ROLE_MOBILE</option>
                    </select>

                    <p class="help-block">
                    	指定客户端所拥有的Spring Security的权限值,可选;若<code>grant_type</code>为<em>implicit</em>或<em>client_credentials</em>则必须选择authorities,因为服务端将根据该字段值的权限来判断是否有权限访问对应的API
                    </p>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-2"></div>
                <div class="col-sm-10">
                    <a href="javascript:void(0);" onclick="showMore()" id="_more_detail_a">更多选项</a>
                </div>
            </div>

            <div id="_more_spread" style="display:none">
                <div class="form-group">
                    <label for="accessTokenValidity" class="col-sm-2 control-label">access_token_validity</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" name="accessTokenValidity" id="accessTokenValidity" placeholder="access_token_validity"/>
                        <p class="help-block">
                        	设定客户端的access_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 12, 12小时);若设定则必须是大于0的整数值
                        </p>
                    </div>
                </div>

                <div class="form-group">
                    <label for="refreshTokenValidity" class="col-sm-2 control-label">refresh_token_validity</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" name="refreshTokenValidity" id="refreshTokenValidity" placeholder="refresh_token_validity"/>
                        <p class="help-block">
                        	设定客户端的refresh_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 24 * 30, 30天);若设定则必须是大于0的整数值
                        </p>
                    </div>
                </div>

                <div class="form-group">
                    <label for="additionalInformation" class="col-sm-2 control-label">additional_information</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="additionalInformation" id="additionalInformation" placeholder="additional_information"/>
                        <p class="help-block">
                            	这是一个预留的字段,在Oauth的流程中没有实际的使用,可选,但若设置值,必须是JSON格式的数据,如:
                            <code>{"country":"CN","country_code":"086"}</code>
                        </p>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">trusted</label>
                    <div class="col-sm-10">
                        <label class="radio-inline">
                            <input type="radio" name="trusted" value="true"/> Yes
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="trusted" value="false" checked="checked"/> No
                        </label>
                        <p class="help-block">
                        	该属性是扩展的,只适用于grant_type(s)包括<code>authorization_code</code>的情况,当用户登录成功后,
                        	若选No,则会跳转到让用户Approve的页面让用户同意授权, 若选Yes,则在登录后不需要再让用户Approve同意授权(因为是受信任的)
                        </p>
                    </div>
                </div>
            </div>

            <div class="form-group" align="center">
                    <button type="submit" class="btn btn-success" style="width: 150px">注&nbsp;&nbsp;&nbsp;&nbsp;册</button>
            </div>
        </form>
    </div>
</div>
</div>
</body>
<script>
	var moreSpreadFlag = true;
    function showMore(){
    	if(moreSpreadFlag){
	    	$("#_more_spread").show();
    	}else{
	    	$("#_more_spread").hide();
    	}
    	moreSpreadFlag = !moreSpreadFlag;
    }
    
    //添加表单校验
    $(document).ready(function() {
	    $('#clientForm').bootstrapValidator({
	//      live: 'disabled',
	      message: 'This value is not valid',
	      feedbackIcons: {
	          valid: 'glyphicon glyphicon-ok',
	          invalid: 'glyphicon glyphicon-remove',
	          validating: 'glyphicon glyphicon-refresh'
	      },
	      fields: {
	          clientId: {
	              message: 'The username is not valid',
	              validators: {
	                  notEmpty: {
	                      message: ''
	                  },
	                  remote:{
	                	  message: 'clientId已经存在',
	                	  url: "${pageContext.request.contextPath}/oauthClient/checkClientId"
	                  }
	              }
	          },
	          authorizedGrantTypes: {
	              validators: {
	                  notEmpty: {
	                      message: 'Please specify at least one language you can speak'
	                  }
	              }
	          }
	      }
	  });
    })
</script>
</html>