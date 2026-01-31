package org.example.dataFactory;

import org.example.models.*;

public class TestDataFactory {

    public static AdminData createDefaultAdmin() {
        return new AdminData(
                "Иванов",
                "Иван",
                "Иванович",
                "375297777777",
                "АВ1234567",
                "23072004");
    }

    public static UserData createDefaultUser() {
        return new UserData(
                "Иванов",
                "Иван",
                "Иванович",
                "375297777777",
                "АВ1234567",
                "г. Брест");
    }

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

    public static BirthRegistrationServiceData createBirthServiceData() {
        return new BirthRegistrationServiceData(
                "г. Брест",
                "Иванова Анна Ивановна",
                "Иванов Иван Иванович",
                "Иванов Иван Иванович",
                "Иванова Анна Ивановна");
    }

    public static DeathRegistrationServiceData createDeathServiceData() {
        return new DeathRegistrationServiceData(
                "29012026",
                "г. Брест");
    }
}