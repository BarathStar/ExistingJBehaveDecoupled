package com.swacorp.dotcom.webscenarios.air

import domain.GiftCard
import util.StringHelper

public abstract class Data {

    private List<GiftCard> giftCards = [
        new GiftCard("6000010000041190","2402"),
        new GiftCard("6000010000041281","5352"),
        new GiftCard("6000010002070544","0523"),
        new GiftCard("6000010000042602","4867")
    ]

    private List<GiftCard> luvVouchers = [
        new GiftCard("9100000013769567","3200"),
        new GiftCard("9100000013768627","7900"),
        new GiftCard("9100000013769575","9822"),
        new GiftCard("9100000013769344","9354")
    ]

    CreditCardData creditCardData
    String swaBizTravelAccountCompanyId

    String getFirstName() {
        return "Leapfrog"
    }

    String getLastName() {
        return "Suu"
    }

    String getLastNameForHotel() {
        return getLastName() + StringHelper.getRandomString(5)
    }

    String getSwaBizTravellerFirstName() {
        return "southwest"
    }

    String getSwaBizTravellerLastName() {
        return "dotcom"
    }

    String getPreferredName() {
        return "My name"
    }

    String getswaBizTravelAccountCompanyId() {
        return swaBizTravelAccountCompanyId ?: 99587574
    }

    String getswaBizTravelAccountNumber() {
        return "500306660"
    }

    String getswaBizTravelAccountPassword() {
        return "test123"
    }

    abstract APlusUser getAPlusUser()

    abstract RRUser getUser(String user)

    abstract Map<String, RRUser> getRrUserMap()

    CreditCard getRandomCreditCard() {
        return CreditCardData.selectCard(CreditCardData.randomCardList)
    }

    CreditCard getRandomCreditCardOnlyVisa() {
        return CreditCardData.selectCard(CreditCardData.randomCardListOnlyVisa)
    }

    CreditCard getUnacceptableCreditCard() {
        return CreditCardData.unacceptableCreditCard;
    }

    CreditCard getStandardCreditCard(int number = 0) {
        return CreditCardData.standardCreditCards.get(number)
    }

    private Set<Route> routes = new HashSet<Route>();
    {
        routes.add(new Route("DAL", "HOU"))
    }

    Route getRoute() {
        synchronized (routes) {
            int routeToSelect = new Random().nextInt(routes.size())
            return routes.toArray()[routeToSelect]
        }
    }

    public CreditCard getDinersCreditCard() {
        return CreditCardData.getDinerClubCard
    }

    public List<GiftCard> getGiftCards(size) {
        return giftCards.subList(0, size)
    }

    public List<GiftCard> getLuvVouchers(size) {
        return luvVouchers.subList(0, size)
    }

    public CreditCard getUATPCreditCard() {
        return CreditCardData.getUATPCard
    }
}
