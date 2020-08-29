package day5.Fliter;

public enum Property {
    ID,COMPANY_NAME, CONTACT_NAME, CONTACT_TITLE,POSTAL_CODE, COUNTRY;

    public String toString() {
        switch(this) {
            case ID:return "ID";
            case COMPANY_NAME: return "Company Name";
            case CONTACT_NAME: return "Contact Name";
            case CONTACT_TITLE: return "Contact Title";
            case POSTAL_CODE:return "Postal Code";
            case COUNTRY: return "Country";
            default:    return "unknown";
        }
    }
}
