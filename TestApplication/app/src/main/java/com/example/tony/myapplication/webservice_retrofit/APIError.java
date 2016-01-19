
package com.example.tony.myapplication.webservice_retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class APIError {

    @SerializedName("error_id")
    @Expose
    private Integer errorId;
    @SerializedName("error_message")
    @Expose
    private String errorMessage;
    @SerializedName("error_name")
    @Expose
    private String errorName;

    /**
     * 
     * @return
     *     The errorId
     */
    public Integer getErrorId() {
        return errorId;
    }

    /**
     * 
     * @param errorId
     *     The error_id
     */
    public void setErrorId(Integer errorId) {
        this.errorId = errorId;
    }

    /**
     * 
     * @return
     *     The errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * 
     * @param errorMessage
     *     The error_message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * 
     * @return
     *     The errorName
     */
    public String getErrorName() {
        return errorName;
    }

    /**
     * 
     * @param errorName
     *     The error_name
     */
    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

}
