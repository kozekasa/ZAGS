package org.example.db;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.example.ApiPreconditions;
import org.example.baseTests.BaseDbTest;
import org.example.dataFactory.TestDataFactory;
import org.example.models.UserDataAPI;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Epic("DB")
@Feature("Изменение статуса заявки")
public class RequestProcessDatabaseTest extends BaseDbTest {

    private int applid;
    private int staffId;
    private String currentKind;

    @Test
    @Story("Изменение статуса на approved")
    @DisplayName("Проверка изменения статуса заявки на approved в БД")
    public void testApproveApplicationStatusInDb() {
        this.currentKind = "marriage";

        UserDataAPI userRequest = TestDataFactory.createMarriageRegistrationAPIRequest().build();
        String targetStatus = "approved";

        int[] ids = ApiPreconditions.createAndPrepareApplication(userRequest, targetStatus);
        this.applid = ids[0];
        this.staffId = ids[1];

        String actualStatus = dbSteps.getApplicationStatus(applid);
        assertThat(actualStatus)
                .as("Статус заявки в БД не соответствует ожидаемому")
                .isEqualToIgnoringCase(targetStatus);
    }


    @AfterEach
    @Step("Полная очистка данных (заявка и админ)")
    public void cleanUpAll() throws SQLException {
        if (applid != 0) {
            dbSteps.cleanUpApplicationData(applid, currentKind);
        }
        if (staffId != 0) {
            dbSteps.deleteStaff(staffId);
        }
    }
}