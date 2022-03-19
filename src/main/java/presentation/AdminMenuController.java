package presentation;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.VacationpackageEntity;
import repository.DestinationRepository;
import repository.VacationPackageRepository;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class AdminMenuController {

    public TextField idTF;
    public TextField priceTF;
    public TextField extraDetailsTF;
    public TextField noOfPeopleThatCanBookTF;
    public TextField nameTF;
    public DatePicker periodStartTF;
    public DatePicker periodEndTF;

    public ListView<String> listView;

    public ChoiceBox<String> destIdCB;
    public ChoiceBox<String> deleteDestCB;
    public TextField addDestTF;

    //public Button refreshButton;

    List<VacationpackageEntity> vacationpackageEntities;
    VacationPackageRepository vacationPackageRepository = new VacationPackageRepository();
    VacationpackageEntity selectedPackage;

    @FXML
    void refreshTable()
    {
        vacationpackageEntities = vacationPackageRepository.getAllVacationPackages();
        listView.getItems().clear();
        for (VacationpackageEntity x :
                vacationpackageEntities) {
            listView.getItems().add(x.toString());
        }
        updateDestinationBox();
    }

    void updateDestinationBox()
    {
        ObservableList<String> observableList = DestinationRepository.getAllDestinationNames();
        destIdCB.setItems(observableList);
        deleteDestCB.setItems(observableList);
    }

    @FXML
    void clickOnTableRow()
    {
        int s = listView.getSelectionModel().getSelectedIndex();
        selectedPackage = vacationpackageEntities.get(s);
        idTF.setText(Integer.toString(selectedPackage.getIdvacationpackage()));

        destIdCB.getSelectionModel().select(DestinationRepository.getChoiceBoxId(selectedPackage.getDestinationId()));

        periodStartTF.setValue(selectedPackage.getPeriodstart().toLocalDate());
        periodEndTF.setValue(selectedPackage.getPeriodend().toLocalDate());

        priceTF.setText(Integer.toString(selectedPackage.getPrice()));
        extraDetailsTF.setText(selectedPackage.getExtraDetails());
        nameTF.setText(selectedPackage.getName());
        noOfPeopleThatCanBookTF.setText(Integer.toString(selectedPackage.getNoofpeoplethatcanbook()));
    }

    private int getDestinationId(ChoiceBox<String> choiceBox)
    {
        String selectedDestination = choiceBox.getSelectionModel().getSelectedItem();
        return DestinationRepository.getIdByDestination(selectedDestination);
    }

    void updateSelected()
    {
        selectedPackage.setIdvacationpackage(Integer.parseInt(idTF.getText()));
        selectedPackage.setDestinationId(getDestinationId(destIdCB));
        selectedPackage.setName(nameTF.getText());
        selectedPackage.setExtraDetails(extraDetailsTF.getText());
        selectedPackage.setNoofpeoplethatcanbook(Integer.parseInt(noOfPeopleThatCanBookTF.getText()));
        selectedPackage.setPrice(Integer.parseInt(priceTF.getText()));
        selectedPackage.setPeriodstart(java.sql.Date.valueOf(periodStartTF.getValue()));
        selectedPackage.setPeriodend(java.sql.Date.valueOf(periodStartTF.getValue()));
    }

    @FXML
    void add()
    {
        updateSelected();
        vacationPackageRepository.addVacation(selectedPackage);
        refreshTable();
    }

    @FXML
    void edit() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        updateSelected();
        vacationPackageRepository.modifyVacation(selectedPackage);
        refreshTable();
    }

    @FXML
    void deleteVacationPackage()
    {
        VacationPackageRepository.deleteVacationPackage(selectedPackage.getIdvacationpackage());
        refreshTable();
    }

    @FXML
    void deleteDestination()
    {
        DestinationRepository.deleteDestination(getDestinationId(deleteDestCB));
        updateDestinationBox();
        refreshTable();
    }

    public void addDestination(ActionEvent actionEvent) {
        DestinationRepository.addDestination(addDestTF.getText());
        updateDestinationBox();
    }
}
