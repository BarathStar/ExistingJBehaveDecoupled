package util

/**
 */
class TicketType {

    public TicketType(){
        super();
    }
    private String carrierCode
    private String departingStation
    private String outboundConnectionCarrier
    private String outboundConnectionStation
    private String arrivingStation
    private String returnConnectionStation
    private String returningStation
    private String airlineName
    private String outboundConnectionAirlineName

    TicketType(String carrierCode, String departingStation,String outboundConnectionCarrier, String outboundConnectionStation, String arrivingStation, String returnConnectionStation, String returningStation, String airlineName, String outboundConnectionAirlineName) {
        this.carrierCode = carrierCode
        this.departingStation = departingStation
        this.outboundConnectionCarrier = outboundConnectionCarrier
        this.outboundConnectionStation = outboundConnectionStation
        this.arrivingStation = arrivingStation
        this.returningStation = returningStation
        this.returnConnectionStation = returnConnectionStation
        this.airlineName = airlineName
        this.outboundConnectionAirlineName = outboundConnectionAirlineName
    }

    public getCarrierCode(){
        return carrierCode;
    }

    public getDepartingStation(){
        return departingStation;
    }
    public getArrivingStation(){
        return arrivingStation;
    }
    public getReturningStation(){
        return returningStation
    }
    public getAirlineName(){
        return airlineName;
    }
    public getOutboundConnectionAirlineName(){
        return outboundConnectionAirlineName;
    }
    public getOutboundConnectionStation() {
        return outboundConnectionStation;
    }
    public getReturnConnectionStation() {
        return returnConnectionStation;
    }

    public getOutboundConnectionCarrier() {
        return outboundConnectionCarrier
    }

    public getArrivalConnectionCarrier() {
        return outboundConnectionCarrier
    }
}
