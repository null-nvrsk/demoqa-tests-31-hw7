import org.junit.jupiter.api.Test;

public class RegistrationTests extends BaseTest {

    String firstName = "John";
    String lastName = "Smith";
    String userEmail = "johnsmith@gmail.com";
    String invalidEmail = "email@domain";

    String userGender = "Male";
    String userNumber = "8001234567";
    String userBirthYear = "1985";
    String userBirthMonth = "August";
    String userBirthDay = "30";

    String subject = "Maths";
    String hobby = "Reading";

    String pictureFileName = "avatar.png";

    String currentAddress = "1195 Borregas Drive";
    String state = "Uttar Pradesh";
    String city = "Lucknow";

    @Test
    void successRegistrationWithRandomTest() {
        registrationPage.openPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setEmail(testData.userEmail)
                .setGender(testData.userGender)
                .setPhone(testData.userNumber)
                .setBirthDate(testData.userBirthDay, testData.userBirthMonth, testData.userBirthYear)
                .setSubject(testData.subject)
                .setHobby(testData.hobby)
                .uploadPicture(testData.picturePath)
                .setCurrentAddress(testData.currentAddress)
                .setStateAndCity(testData.state, testData.city);

        registrationPage.submitRegistrationForm();

        registrationPage
                .verifyResultsModal()
                .verifyResult("Student Name", testData.firstName + " " + testData.lastName)
                .verifyResult("Student Email", testData.userEmail)
                .verifyResult("Gender", testData.userGender)
                .verifyResult("Mobile", testData.userNumber)
                .verifyResult("Date of Birth", testData.userBirthDay + " " + testData.userBirthMonth + ","
                        + testData.userBirthYear)
                .verifyResult("Subjects", testData.subject)
                .verifyResult("Hobbies", testData.hobby)
                .verifyResult("Picture", testData.pictureName)
                .verifyResult("Address", testData.currentAddress)
                .verifyResult("State and City", testData.state + " " + testData.city);
    }

    @Test
    void successRegistrationTest() {

        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(userEmail)
                .setGender(userGender)
                .setPhone(userNumber)
                .setBirthDate(userBirthDay, userBirthMonth, userBirthYear)
                .setSubject(subject)
                .setHobby(hobby)
                .uploadPicture(pictureFileName)
                .setCurrentAddress(currentAddress)
                .setStateAndCity(state, city);

        registrationPage.submitRegistrationForm();

        registrationPage.verifyResultsModal()
                .verifyResult("Student Name", firstName + " " + lastName)
                .verifyResult("Student Email", userEmail)
                .verifyResult("Gender", userGender)
                .verifyResult("Mobile", userNumber)
                .verifyResult("Date of Birth", userBirthDay + " " + userBirthMonth + "," + userBirthYear)
                .verifyResult("Subjects", subject)
                .verifyResult("Hobbies", hobby)
                .verifyResult("Picture", pictureFileName)
                .verifyResult("Address", currentAddress)
                .verifyResult("State and City", state + " " + city);
    }

    @Test
    void minimalRequiredRegistrationTest() {

        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(userGender)
                .setPhone(userNumber);

        registrationPage.submitRegistrationForm();

        registrationPage.verifyResultsModal()
                .verifyResult("Student Name", firstName + " " + lastName)
                .verifyResult("Gender", userGender)
                .verifyResult("Mobile", userNumber);
    }

    @Test
    void invalidEmailRegistrationTest() {

        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(invalidEmail)
                .setGender(userGender)
                .setPhone(userNumber);

        registrationPage.submitRegistrationForm();

        registrationPage.verifyEmailErrorNotification();
    }
}