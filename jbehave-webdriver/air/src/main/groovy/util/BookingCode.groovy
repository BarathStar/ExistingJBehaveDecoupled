package util

public enum BookingCode {

  BusinessSelect("K"),
  Anytime("Y"),
  WannaGetAway("L"),
  Senior("Q"),
  StandardAward("V"),
  FreedomAward("X"),
  Nonrev("V"),
  Ding("S")

  public static List<String> BOOKING_CODE_NAMES = ["Business Select", "Anytime",  "Senior", "Wanna Get Away", "Ding"]
  private String bookingClass

  private BookingCode(String bookingClass){
    this.bookingClass = bookingClass
  }
  static BookingCode from(String fareType) {
      return BookingCode.values().find {
       it.name() == fareType
      }
  }

  public String bookingClassCode(){
    return bookingClass
  }

}
