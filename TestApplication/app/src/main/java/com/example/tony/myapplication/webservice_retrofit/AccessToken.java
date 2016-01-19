
package com.example.tony.myapplication.webservice_retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccessToken {

    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("expires_in")
    @Expose
    private Integer expiresIn;
    @SerializedName("token_type")
    @Expose
    private String tokenType;
    @SerializedName("scope")
    @Expose
    private Object scope;
    @SerializedName("refresh_token")
    @Expose
    private String refreshToken;

    /**
     * 
     * @return
     *     The accessToken
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * 
     * @param accessToken
     *     The access_token
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * 
     * @return
     *     The expiresIn
     */
    public Integer getExpiresIn() {
        return expiresIn;
    }

    /**
     * 
     * @param expiresIn
     *     The expires_in
     */
    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    /**
     * 
     * @return
     *     The tokenType
     */
    public String getTokenType() {
        return tokenType;
    }

    /**
     * 
     * @param tokenType
     *     The token_type
     */
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    /**
     * 
     * @return
     *     The scope
     */
    public Object getScope() {
        return scope;
    }

    /**
     * 
     * @param scope
     *     The scope
     */
    public void setScope(Object scope) {
        this.scope = scope;
    }

    /**
     * 
     * @return
     *     The refreshToken
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * 
     * @param refreshToken
     *     The refresh_token
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
