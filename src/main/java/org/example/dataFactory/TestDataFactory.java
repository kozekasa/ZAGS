package org.example.dataFactory;

import io.qameta.allure.Step;
import org.example.models.*;

public class TestDataFactory {

    @Step("Инициализация данных: Администратор")
    public static AdminData createAdminForUI() {
        return new AdminData(
                "Иванов",
                "Иван",
                "Иванович",
                "37529777777",
                "АВ123456",
                "23072004");
    }

    @Step("Инициализация данных: Администратор")
    public static AdminData createAdminForAPI() {
        return new AdminData(
                "Иванов",
                "Иван",
                "Иванович",
                "37529777777",
                "АВ123456",
                "2004-07-23");
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
    public static UserDataAPI createMarriageRegistrationAPIRequest() {
        return new UserDataAPI(
                "wedding", "Иванов", "Иван", "Иванович", "37529111223",
                "AB123456", "Петрова", "Анна", "Сергеевна", "1995-05-10",
                "MP765432", "Female", "2026-06-20", "Иванова", "Иванов",
                "Иван", "Иванович", "1990-01-01", "KH123456",
                "", "", "", "", "");
    }

    @Step("Инициализация данных для заявки: Регистрация рождения")
    public static UserDataAPI createBirthRegistrationAPIRequest() {
        return new UserDataAPI(
                "birth",
                "Иванов",
                "Иван",
                "Иванович",
                "37529111223",
                "AB123456",
                "Иванова",
                "Мария",
                "Ивановна",
                "2026-02-01",
                "",
                "Female",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "Минск, РБ",
                "Иванова А.С.",
                "Иванов И.И.",
                "",
                "");
    }

    @Step("Инициализация данных для заявки: Регистрация смерти")
    public static UserDataAPI createDeathRegistrationAPIRequest() {
        return new UserDataAPI(
                "death", "Иванов", "Иван", "Иванович", "37529111223",
                "AB123456", "Петрова", "Анна", "Сергеевна", "1995-05-10",
                "MP765432", "Female", "", "", "", "",
                "", "", "", "", "", "",
                "2026-01-10", "Госпиталь №1");
    }

    @Step("Подготовка данных для одобрения заявки №{appId}")
    public static RequestProcessData approveRequest(int appId, int staffId) {
        return new RequestProcessData(appId, staffId, "approved");
    }

    @Step("Подготовка данных для отклонения заявки №{appId}")
    public static RequestProcessData rejectRequest(int appId, int staffId) {
        return new RequestProcessData(appId, staffId, "rejected");
    }
}