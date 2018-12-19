package ie.holiday.booktimeoff;

public class UserBookingData {

    String DB_Reason, DB_ToDate, DB_FromDate;

    public void setReason(String DB_Reason)
    {
        this.DB_Reason = DB_Reason;
    }

    public String  getReason()
    {
        return this.DB_Reason;
    }

    public void setToDate(String DB_ToDate)
    {
        this.DB_ToDate = DB_ToDate;
    }

    public String  getToDate()
    {
        return this.DB_ToDate;
    }

    public void setFromDate(String DB_FromDate)
    {
        this.DB_FromDate = DB_FromDate;
    }

    public String  getFromDate()
    {
        return this.DB_FromDate;
    }


}
