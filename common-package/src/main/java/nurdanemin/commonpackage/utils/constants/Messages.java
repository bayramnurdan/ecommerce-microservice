package nurdanemin.commonpackage.utils.constants;
public class Messages {
public static class Address {
    public static final String NotExists = "ADDRESS_DOES_NOT_EXIST";
    public static final String Exists = "ADDRESS_ALREADY_EXISTS";
}

public static class Brand {
    public static final String NotExists = "BRAND_DOES_NOT_EXIST";
    public static final String Exists = "BRAND_ALREADY_EXISTS";
}

public static class Category {
    public static final String NotExists = "CATEGORY_DOES_NOT_EXIST";
    public static final String Exists = "CATEGORY_ALREADY_EXISTS";
}
public static class Payment {
    public static final String NotEnough = "BALANCE_IS_NOT_ENOUGH";
    public static final String NotExists = "PAYMENT_NOT_EXIST";
}

public static class Product {
    public static final String NotExists = "PRODUCT_DOES_NOT_EXIST";
    public static final String ExistsForBrand = "PRODUCT_ALREADY_EXISTS_FOR_BRAND";
}
    public static class User {
        public static final String NotExists = "USER_NOT_EXIST";
        public static final String Exists = "USER_ALREADY_EXISTS";
        public static  final String EmailIsUsedBefore = "EMAIL_ALREADY_USED_BEFORE";
    }
}