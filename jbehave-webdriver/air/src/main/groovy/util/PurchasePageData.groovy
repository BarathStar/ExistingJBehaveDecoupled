package util

class PurchasePageData {
    BigDecimal luvVocherAmount
    BigDecimal totalDueAmount
    BigDecimal fullBalanceAmount
    BigDecimal luvVoucherAppliedAmount
    BigDecimal luvVoucherRemainingAmount
    BigDecimal tripTotal
    BigDecimal totalTravelFundsApplied = BigDecimal.ZERO
    BigDecimal travelFundsApplied = BigDecimal.ZERO
    BigDecimal travelFundsRemaining = BigDecimal.ZERO
    BigDecimal exchangeTicketFundsApplied = BigDecimal.ZERO
    BigDecimal exchangeTicketFundsRemaining = BigDecimal.ZERO
    BigDecimal totalDueNow = BigDecimal.ZERO
    boolean umHasCompanion = false
    BigDecimal guardianFee = 0
    BigDecimal totalPoints = 0
    BigDecimal availablePoints = 0
    String promoCertExpirationDate
}
