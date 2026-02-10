package org.example.dataFactory;

import io.qameta.allure.Step;
import org.example.models.*;

public class TestDataFactory {

    private static AdminData createBaseAdmin(String birthDate) {
        return new AdminData(
                "Иванов",
                "Иван",
                "Иванович",
                "37529777777",
                "АВ123456",
                birthDate);
    }

    @Step("Инициализация данных: Администратор (UI)")
    public static AdminData createAdminForUI() {
        return createBaseAdmin("23072004");
    }

    @Step("Инициализация данных: Администратор (API)")
    public static AdminData createAdminForAPI() {
        return createBaseAdmin("2004-07-23");
    }

    @Step("Инициализация данных: Пользователь")
    public static UserData createDefaultUser() {
        return new UserData(
                "Иванов",
                "Иван",
                "Иванович",
                "37529777777",
                "АВ1234567",
                "г. Брест");
    }

    @Step("Инициализация данных: Гражданин")
    public static CitizenData createDefaultCitizen() {
        return new CitizenData(
                "Иванов",
                "Иван",
                "Иванович",
                "23072004",
                "АВ1234567",
                "male",
                "г. Брест");
    }

    @Step("Инициализация данных услуги: Регистрация брака")
    public static MarriageRegistrationServiceData createMarriageServiceData() {
        return new MarriageRegistrationServiceData(
                "29012026",
                "Иванова",
                "Иванов",
                "Иван",
                "Иванович",
                "27012004",
                "AB1234567");
    }

    @Step("Инициализация данных услуги: Регистрация рождения")
    public static BirthRegistrationServiceData createBirthServiceData() {
        return new BirthRegistrationServiceData(
                "г. Брест",
                "Иванова Анна Ивановна",
                "Иванов Иван Иванович",
                "Иванов Иван Иванович",
                "Иванова Анна Ивановна");
    }

    @Step("Инициализация данных услуги: Регистрация смерти")
    public static DeathRegistrationServiceData createDeathServiceData() {
        return new DeathRegistrationServiceData(
                "29012026",
                "г. Брест");
    }

    @Step("Инициализация данных для заявки: Регистрация брака")
    public static UserDataAPI.UserDataAPIBuilder createMarriageRegistrationAPIRequest() {
        return UserDataAPI.builder()
                .mode("wedding")
                .personalLastName("Иванов")
                .personalFirstName("Иван")
                .personalMiddleName("Иванович")
                .personalPhoneNumber("37529111223")
                .personalNumberOfPassport("AB123456")
                .citizenLastName("Иванова")
                .citizenFirstName("Мария")
                .citizenMiddleName("Ивановна")
                .citizenBirthDate("2026-02-01")
                .citizenNumberOfPassport("AB123123")
                .citizenGender("Female")
                .dateOfMarriage("2026-06-20")
                .newLastName("Иванова")
                .anotherPersonLastName("Иванов")
                .anotherPersonFirstName("Иван")
                .anotherPersonMiddleName("Иванович")
                .birth_of_anotoherPerson("1990-01-01")
                .anotherPersonPassport("KH123456");
    }

    @Step("Инициализация данных для заявки: Регистрация рождения")
    public static UserDataAPI.UserDataAPIBuilder createBirthRegistrationAPIRequest() {
        return UserDataAPI.builder()
                .mode("birth")
                .personalLastName("Иванов")
                .personalFirstName("Иван")
                .personalMiddleName("Иванович")
                .personalPhoneNumber("37529111223")
                .personalNumberOfPassport("AB123456")
                .citizenLastName("Иванова")
                .citizenFirstName("Мария")
                .citizenMiddleName("Ивановна")
                .citizenBirthDate("2026-02-01")
                .citizenNumberOfPassport("AB123123")
                .citizenGender("Female")
                .birth_place("Минск, РБ")
                .birth_mother("Иванова А.С.")
                .birth_father("Иванов И.И.");
    }

    @Step("Инициализация данных для заявки: Регистрация смерти")
    public static UserDataAPI.UserDataAPIBuilder createDeathRegistrationAPIRequest() {
        return UserDataAPI.builder()
                .mode("death")
                .personalLastName("Иванов")
                .personalFirstName("Иван")
                .personalMiddleName("Иванович")
                .personalPhoneNumber("37529111223")
                .personalNumberOfPassport("AB123456")
                .citizenLastName("Иванова")
                .citizenFirstName("Мария")
                .citizenMiddleName("Ивановна")
                .citizenBirthDate("2026-02-01")
                .citizenNumberOfPassport("AB123123")
                .citizenGender("Female")
                .death_dateOfDeath("2026-01-10")
                .death_placeOfDeath("Госпиталь №1");
    }

    @Step("Подготовка данных для обработки заявки №{appId} (статус: {status})")
    public static RequestProcessData createRequestStatus(int appId, int staffId, String status) {
        return new RequestProcessData(appId, staffId, status);
    }

    @Step("Подготовка данных для одобрения заявки №{appId} (статус: {status})")
    public static RequestProcessData.RequestProcessDataBuilder createRequestWithoutApplidBuilder(int staffId, String status) {
        return RequestProcessData.builder()
                .staffid(staffId)
                .action(status);
    }


    @Step("Инициализация невалидных данных (отсутствие поля с именем) для заявки: Регистрация рождения")
    public static UserDataAPI.UserDataAPIBuilder createInvalidBirthRegistrationAPIRequest() {
        return UserDataAPI.builder()
                .mode("birth")
                .personalLastName("Иванов")
                .personalMiddleName("Иванович")
                .personalPhoneNumber("37529111223")
                .personalNumberOfPassport("AB123456")
                .citizenLastName("Иванова")
                .citizenFirstName("Мария")
                .citizenMiddleName("Ивановна")
                .citizenBirthDate("2026-02-01")
                .citizenNumberOfPassport("AB123123")
                .citizenGender("Female")
                .birth_place("Минск, РБ")
                .birth_mother("Иванова А.С.")
                .birth_father("Иванов И.И.");
    }

    @Step("Инициализация невалидных данных (отсутствие поля с именем) для заявки: Регистрация рождения")
    public static AdminData.AdminDataBuilder createAdminWithoutNameForAPI() {
        return AdminData.builder()
                .personalLastName("Иванов")
                .personalMiddleName("37529777777")
                .personalPhoneNumber("37529777777")
                .personalNumberOfPassport("АВ123456");
    }
}