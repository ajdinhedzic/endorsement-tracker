package com.hedzic.ajdin.endorsementtracker.repository;

import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FakeSendGrid extends SendGrid {

    private List<Request> requestList;

    public FakeSendGrid(String apiKey) {
        super(apiKey);
        requestList = new ArrayList<>();
    }

    @Override
    public Response api(Request request) throws IOException {
        requestList.add(request);
        return null;
    }


    public List<Request> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<Request> requestList) {
        this.requestList = requestList;
    }
}
