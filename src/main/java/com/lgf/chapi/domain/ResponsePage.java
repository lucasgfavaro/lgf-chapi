package com.lgf.chapi.domain;

import com.lgf.chapi.domain.Page;

/**
 * Created by lucasfavaro on 3/18/17.
 */
public class ResponsePage {

    String status;
    String status_message;
    Page data;

    public Page getData() {
        return data;
    }

    public void setData(Page data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponsePage{" +
                "status='" + status + '\'' +
                ", status_message='" + status_message + '\'' +
                ", data=" + data +
                '}';
    }
}
