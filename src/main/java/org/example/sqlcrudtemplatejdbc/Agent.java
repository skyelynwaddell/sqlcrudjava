package org.example.sqlcrudtemplatejdbc;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Agent {

    //Agent Properties
    private SimpleIntegerProperty AgentId;
    private SimpleStringProperty AgtFirstName;
    private SimpleStringProperty AgtMiddleInitial;
    private SimpleStringProperty AgtLastName;
    private SimpleStringProperty AgtBusPhone;
    private SimpleStringProperty AgtEmail;
    private SimpleStringProperty AgtPosition;
    private SimpleIntegerProperty AgencyId;
    private SimpleIntegerProperty AgtIsActive;

    //Agent Constructor
    public Agent(Integer agentId,
                 String agtFirstName,
                 String agtMiddleInitial,
                 String agtLastName,
                 String agtBusPhone,
                 String agtEmail,
                 String agtPosition,
                 Integer agencyId,
                 Integer agtIsActive) {
        AgentId = new SimpleIntegerProperty(agentId);
        AgtFirstName = new SimpleStringProperty(agtFirstName);
        AgtMiddleInitial = new SimpleStringProperty(agtMiddleInitial);
        AgtLastName = new SimpleStringProperty(agtLastName);
        AgtBusPhone = new SimpleStringProperty(agtBusPhone);
        AgtEmail = new SimpleStringProperty(agtEmail);
        AgtPosition = new SimpleStringProperty(agtPosition);
        AgencyId = new SimpleIntegerProperty(agencyId);
        AgtIsActive = new SimpleIntegerProperty(agtIsActive);
    }// end constructor

    public int getAgentId() {
        return AgentId.get();
    }

    public SimpleIntegerProperty agentIdProperty() {
        return AgentId;
    }

    public void setAgentId(int agentId) {
        this.AgentId.set(agentId);
    }

    public String getAgtFirstName() {
        return AgtFirstName.get();
    }

    public SimpleStringProperty agtFirstNameProperty() {
        return AgtFirstName;
    }

    public void setAgtFirstName(String agtFirstName) {
        this.AgtFirstName.set(agtFirstName);
    }

    public String getAgtMiddleInitial() {
        return AgtMiddleInitial.get();
    }

    public SimpleStringProperty agtMiddleInitialProperty() {
        return AgtMiddleInitial;
    }

    public void setAgtMiddleInitial(String agtMiddleInitial) {
        this.AgtMiddleInitial.set(agtMiddleInitial);
    }

    public String getAgtLastName() {
        return AgtLastName.get();
    }

    public SimpleStringProperty agtLastNameProperty() {
        return AgtLastName;
    }

    public void setAgtLastName(String agtLastName) {
        this.AgtLastName.set(agtLastName);
    }

    public String getAgtBusPhone() {
        return AgtBusPhone.get();
    }

    public SimpleStringProperty agtBusPhoneProperty() {
        return AgtBusPhone;
    }

    public void setAgtBusPhone(String agtBusPhone) {
        this.AgtBusPhone.set(agtBusPhone);
    }

    public String getAgtEmail() {
        return AgtEmail.get();
    }

    public SimpleStringProperty agtEmailProperty() {
        return AgtEmail;
    }

    public void setAgtEmail(String agtEmail) {
        this.AgtEmail.set(agtEmail);
    }

    public String getAgtPosition() {
        return AgtPosition.get();
    }

    public SimpleStringProperty agtPositionProperty() {
        return AgtPosition;
    }

    public void setAgtPosition(String agtPosition) {
        this.AgtPosition.set(agtPosition);
    }

    public int getAgencyId() {
        return AgencyId.get();
    }

    public SimpleIntegerProperty agencyIdProperty() {
        return AgencyId;
    }

    public void setAgencyId(int agencyId) {
        this.AgencyId.set(agencyId);
    }

    public int getAgtIsActive() {
        return AgtIsActive.get();
    }

    public SimpleIntegerProperty agtIsActiveProperty() {
        return AgtIsActive;
    }

    public void setAgtIsActive(int agtIsActive) {
        this.AgtIsActive.set(agtIsActive);
    }
} // end class
