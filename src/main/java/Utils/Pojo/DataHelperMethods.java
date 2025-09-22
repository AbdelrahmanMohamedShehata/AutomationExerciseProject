package Utils.Pojo;
import Utils.Data.*;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.util.List;

public class DataHelperMethods {

    List<BrowserName> browserData;
    List<loginCredentials> credentials;
    List<SearchField> searchData;
    List<Products> product;
    List<Payment> payment;
    List<URLData> UrlData;
    List<ContactUs> contact;
    List<Review> review;
    List<signupInfo> signupInfoData;
    List<Signup> signup;
    List<InvalidData> invalidData;
    JsonReader jsonReader = new JsonReader();

    public BrowserName getBrowserNames(int i){
        browserData = jsonReader.readData(".\\src\\main\\resources\\JsonData\\browserName.json",
                new TypeReference<List<BrowserName>>(){});
       return browserData.get(i);
    }

    public URLData getUrlData(int i){
        UrlData = jsonReader.readData(".\\src\\main\\resources\\JsonData\\urlData.json",
                new TypeReference<List<URLData>>(){});
        return UrlData.get(i);
    }

    public loginCredentials getLoginData(int i){
        credentials = jsonReader.readData(".\\src\\main\\resources\\JsonData\\loginData.json",
                new TypeReference<List<loginCredentials>>(){});
        return credentials.get(i);
    }

    public SearchField getSearchKeys(int i){
        searchData = jsonReader.readData(".\\src\\main\\resources\\JsonData\\searchData.json",
                new TypeReference<List<SearchField>>(){});
        return searchData.get(i);
    }

    public Products getProductData(int i){
        product = jsonReader.readData(".\\src\\main\\resources\\JsonData\\productData.json",
                new TypeReference<List<Products>>(){});
        return product.get(i);
    }

    public Payment getPaymentData(int i){
        payment = jsonReader.readData(".\\src\\main\\resources\\JsonData\\payment_Data.json",
                new TypeReference<List<Payment>>(){});
        return payment.get(i);
    }

    public ContactUs getContactusData(int i){
        contact = jsonReader.readData(".\\src\\main\\resources\\JsonData\\contactusData.json",
                new TypeReference<List<ContactUs>>(){});
        return contact.get(i);
    }

    public Review getReviewData(int i){
        review = jsonReader.readData(".\\src\\main\\resources\\JsonData\\reviewData.json",
                new TypeReference<List<Review>>(){});
        return review.get(i);
    }

    public Signup getSignupData(int i){
        signup = jsonReader.readData(".\\src\\main\\resources\\JsonData\\signupData.json",
                new TypeReference<List<Signup>>(){});
        return signup.get(i);
    }

    public List<Signup> getAllSignupData(){
        signup = jsonReader.readData(".\\src\\main\\resources\\JsonData\\signupData.json",
                new TypeReference<List<Signup>>(){});
        return signup;
    }

    public signupInfo getSignupInfoData(int i){
        signupInfoData = jsonReader.readData(".\\src\\main\\resources\\JsonData\\signupInfoData.json",
                new TypeReference<List<signupInfo>>(){});
        return signupInfoData.get(i);
    }

    public InvalidData getInvalidData(int i){
         invalidData = jsonReader.readData(".\\src\\main\\resources\\JsonData\\invalid_Data.json",
                new TypeReference<List<InvalidData>>(){});
        return invalidData.get(i);
    }

    public List<InvalidData> getAllInvalidData(){
        invalidData = jsonReader.readData(".\\src\\main\\resources\\JsonData\\invalid_Data.json",
                new TypeReference<List<InvalidData>>(){});
        return invalidData;
    }

    public String getAbsolutePathFromJson(String path){
        File file = new File(path);
        return file.getAbsolutePath();
    }
}
