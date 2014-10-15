package util

import com.google.common.collect.ImmutableList

class RandomTicketTypeGroup {

   private static final ImmutableList<TicketType> southWestRoutes = ImmutableList.of(
           tt("WN", "DAL","WN", "", "ABQ", "", "HOU", "Southwest Airlines", "Southwest Airlines"),
           tt("WN", "DAL","WN", "", "SAT", "", "AMA", "Southwest Airlines", "Southwest Airlines"),
           tt("WN", "DAL","WN", "", "AUS", "", "ABQ", "Southwest Airlines", "Southwest Airlines"),
           tt("WN", "ABQ","WN", "", "DAL", "", "LAX", "Southwest Airlines", "Southwest Airlines"),
           tt("WN", "DAL","WN", "", "HOU", "", "ABQ", "Southwest Airlines", "Southwest Airlines")
   )

    private static final ImmutableList<TicketType> southwestCodeShareRoutes = ImmutableList.of(
            tt("WN","BOS","WN", "BWI", "FLL", "", "PDX","Southwest Airlines", "Southwest Airlines")
    )

    private static final ImmutableList<TicketType> codeShareRoutes = ImmutableList.of(
            tt("WN","FLL","FL", "BWI", "HSV", "", "LAS","Southwest Airlines", "Southwest Airlines")
    )

    private static final ImmutableList<TicketType> flWnCodeShareRoutes = ImmutableList.of(
            tt("FL","ATL","WN", "DEN", "LAS", "", "","AirTran", "Southwest Airlines")
    )

    private static final ImmutableList<TicketType> wnFlCodeShareRoutes = ImmutableList.of(
            tt("WN","ISP","FL", "BWI", "FLL", "", "ISP","Southwest Airlines", "AirTran")
    )

    private static final ImmutableList<TicketType> internationalRoutes = ImmutableList.of(
            tt("FL","ATL","FL", "", "AUA", "", "ATL","AirTran", "AirTran")
    )

    private static final ImmutableList<TicketType> airTranOnlyRoutes = ImmutableList.of(
            tt("FL","BOS","FL", "", "BWI", "", "GRR","AirTran", "AirTran"),
            tt("FL","ATL","FL", "", "AUA", "", "ATL","AirTran", "AirTran")
    )

    private static final ImmutableList<TicketType> southwestNonstopRoutes = ImmutableList.of(
            tt("WN","DAL","WN", "", "AUS", "", "HOU","Southwest Airlines", "Southwest Airlines")
    )

    private static final ImmutableList<TicketType> southwestConnectRoutes = ImmutableList.of(
            tt("WN","DAL","WN", "HOU", "MSY", "", "HOU","Southwest Airlines", "Southwest Airlines")
    )

    private static final ImmutableList<TicketType> airTranRoutes = ImmutableList.of(
            tt("FL","BOS","FL", "", "BWI", "", "GRR","AirTran", "AirTran"),
    )

    private static final ImmutableList<TicketType> southwestPlaneChangeRoutes = ImmutableList.of(
        tt("WN", "MCO", "WN", "", "LAX", "", "LGA", "Southwest Airlines", "Southwest Airlines"),
        tt("WN", "SEA", "WN", "", "FLL", "", "LGA", "Southwest Airlines", "Southwest Airlines"),
        tt("WN", "SEA", "WN", "", "HOU", "", "DEN", "Southwest Airlines", "Southwest Airlines"),
        tt("WN", "IAD", "WN", "", "PHX", "", "MCI", "Southwest Airlines", "Southwest Airlines"),
        tt("WN", "DAL", "WN", "", "PHX", "", "SEA", "Southwest Airlines", "Southwest Airlines")
    )

    private static final ImmutableList<TicketType> southwestUMRoutes = ImmutableList.of(
        tt("WN", "AUS", "WN", "", "HOU", "", "PHX", "Southwest Airlines", "Southwest Airlines"),
    )

    private static final ImmutableList<TicketType> southwestInternationalRoutes = ImmutableList.of(
        tt("WN", "HOU", "WN", "", "GDL", "", "HOU", "Southwest Airlines", "Southwest Airlines"),
    )

    private Map<String, TicketType> ticketTypes = new HashMap<String, TicketType>();

    private TicketType getRandomRoute(List<TicketType> routes) {
        return routes.get(new Random().nextInt(routes.size()))
    }

    public RandomTicketTypeGroup () {
        ticketTypes.put("Southwest", getRandomRoute(southWestRoutes))
        ticketTypes.put("SouthwestCodeshare", getRandomRoute(southwestCodeShareRoutes))
        ticketTypes.put("SouthwestNS", getRandomRoute(southwestNonstopRoutes))
        ticketTypes.put("SouthwestConnect", getRandomRoute(southwestConnectRoutes))
        ticketTypes.put("AirTran", getRandomRoute(airTranRoutes))
        ticketTypes.put("AirTranOnly", getRandomRoute(airTranOnlyRoutes))
        ticketTypes.put("CodeShare", getRandomRoute(codeShareRoutes))
        ticketTypes.put("International", getRandomRoute(internationalRoutes))
        ticketTypes.put("SouthwestPC", getRandomRoute(southwestPlaneChangeRoutes))
        ticketTypes.put("FLWNCodeShare", getRandomRoute(flWnCodeShareRoutes))
        ticketTypes.put("WNFLCodeShare", getRandomRoute(wnFlCodeShareRoutes))
        ticketTypes.put("WN_WN", getRandomRoute(southWestRoutes))
        ticketTypes.put("SouthwestUMRoute", getRandomRoute(southwestUMRoutes))
        ticketTypes.put("SwaInternationalRoute", getRandomRoute(southwestInternationalRoutes))

    }

    public TicketType getTicketType(String key) {
        return ticketTypes.get(key)

    }

    private static TicketType tt(String carrierCode, String departingStation, String outboundConnectingCarrier, String outboundConnectingStation, String arrivingStation, String returnConnectingStation, String returningStation, String airlineName, String outboundConnectionAirlineName) {
        return new TicketType(carrierCode, departingStation, outboundConnectingCarrier, outboundConnectingStation, arrivingStation, returnConnectingStation, returningStation, airlineName, outboundConnectionAirlineName)
    }


}
