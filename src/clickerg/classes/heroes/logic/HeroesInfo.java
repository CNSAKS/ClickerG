/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg.classes.heroes.logic;

import clickerg.classes.others.auxiliars.AuxiliarHeroe;
import clickerg.classes.others.auxiliars.AuxiliarItem;
import clickerg.classes.others.damageCalculators.HeroeDamageCalculator;
import clickerg.classes.others.damageCalculators.HeroeDamageCalculatorBase;
import clickerg.classes.others.damageCalculators.HeroeDamageCalculatorSlot1;
import clickerg.classes.others.damageCalculators.HeroeDamageCalculatorSlot2;
import clickerg.classes.others.damageCalculators.HeroeDamageCalculatorSlot3;
import clickerg.classes.items.logic.Items;
import clickerg.classes.others.templates.TemplateXMLWriter;
import clickerg.classes.others.templates.TemplateXMLonlyRead;
import clickerg.classes.heroes.persistence.readHeroeFileAccountInfo;
import clickerg.classes.heroes.persistence.readHeroeInfoFileAccountInfo;
import clickerg.classes.heroes.persistence.writeHeroeFileAccountInfo;
import static clickerg.classes.heroes.logic.Heroes.selectedHeroe;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Dragon
 */


public class HeroesInfo implements Initializable{

    @FXML
    private Label lb_name;
    @FXML
    private Label lb_lvl;
    
    public AuxiliarHeroe heroesInfo;
    public String itemToChange;
    @FXML
    private Label lb_ataque;
    @FXML
    private Label lb_experiencia;
    @FXML
    private Button b_activar;
    @FXML
    private ImageView img_heroe;
    
    public ArrayList<AuxiliarHeroe> contratados = new ArrayList<>();
    public ArrayList<AuxiliarItem> items = new ArrayList<>();
    boolean needSave;
    @FXML
    private Label lb_heroeAc;
    @FXML
    private Button button_item1;
    @FXML
    private Button button_item2;
    @FXML
    private Button button_item3;
    @FXML
    private Button bBack;
    @FXML
    private Label label_item_1;
    @FXML
    private Label label_item_2;
    @FXML
    private Label label_item_3;
    @FXML
    private VBox vBox_back;
    @FXML
    private ImageView iv_back;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void initData (AuxiliarHeroe heroe){
        
        iv_back.setImage(new Image("/clickerg/visualsAndFiles/heroesInfo/heroeBackground1.png"));
        
        label_item_3.setTextFill(Color.web("#FFFFFF"));
        label_item_2.setTextFill(Color.web("#FFFFFF"));
        label_item_1.setTextFill(Color.web("#FFFFFF"));
        lb_heroeAc.setTextFill(Color.web("#FFFFFF"));
        lb_experiencia.setTextFill(Color.web("#FFFFFF"));
        lb_ataque.setTextFill(Color.web("#FFFFFF"));
        lb_lvl.setTextFill(Color.web("#FFFFFF"));
        lb_name.setTextFill(Color.web("#FFFFFF"));
       
        ImageView iv1 = new ImageView();
        iv1.setImage(new Image("/clickerg/visualsAndFiles/icons/active.png"));
        iv1.setFitHeight(20);
        iv1.setFitWidth(80);
        b_activar.setStyle("-fx-background-color: transparent;");
        b_activar.setGraphic(iv1);
        
        ImageView iv2 = new ImageView();
        iv2.setImage(new Image("/clickerg/visualsAndFiles/icons/item1.png"));
        iv2.setFitHeight(25);
        iv2.setFitWidth(80);
        button_item1.setStyle("-fx-background-color: transparent;");
        button_item1.setGraphic(iv2);
        
        ImageView iv3 = new ImageView();
        iv3.setImage(new Image("/clickerg/visualsAndFiles/icons/item2.png"));
        iv3.setFitHeight(25);
        iv3.setFitWidth(80);
        button_item2.setStyle("-fx-background-color: transparent;");
        button_item2.setGraphic(iv3);
        
        ImageView iv4 = new ImageView();
        iv4.setImage(new Image("/clickerg/visualsAndFiles/icons/item3.png"));
        iv4.setFitHeight(25);
        iv4.setFitWidth(80);
        button_item3.setStyle("-fx-background-color: transparent;");
        button_item3.setGraphic(iv4);
        
        
        TemplateXMLonlyRead heroeReader = new readHeroeFileAccountInfo();
        contratados = heroeReader.readXML();
        
        TemplateXMLonlyRead itemReader = new readHeroeInfoFileAccountInfo();
        items = itemReader.readXML();
        
        heroesInfo = Heroes.selectedHeroe;;
        needSave = false;
        lb_name.setText(heroesInfo.getName());
        lb_lvl.setText("Nivel : " + heroesInfo.getLvl());
        lb_experiencia.setText("Experiencia : " + heroesInfo.getExp());
        HeroeDamageCalculator HDC = new HeroeDamageCalculatorBase(heroesInfo);
        HDC = new HeroeDamageCalculatorSlot1(HDC,heroesInfo,items);
        HDC = new HeroeDamageCalculatorSlot2(HDC,heroesInfo,items);
        HDC = new HeroeDamageCalculatorSlot3(HDC,heroesInfo,items);
        lb_ataque.setText("Ataque : "+((long) HDC.calcularAtaque()));
        
        Image image = new Image("/clickerg/visualsAndFiles/heroes/images/id_" + heroesInfo.getId()+".png");
        if(heroesInfo.getActive().equals("true")){
            lb_heroeAc.setVisible(true);
        }else{
            lb_heroeAc.setVisible(false);
        }
        
        
        img_heroe.setImage(image);
        
        for(AuxiliarItem i:items){
            if(heroesInfo.getItem_1().equals(i.getId())){
                label_item_1.setText(i.getName()+" ATK: x"+i.getBase_mult());
            }
            if(heroesInfo.getItem_2().equals(i.getId())){
                label_item_2.setText(i.getName()+" ATK: x"+i.getBase_mult());
            }
            if(heroesInfo.getItem_3().equals(i.getId())){
                label_item_3.setText(i.getName()+" ATK: x"+i.getBase_mult());
            }
        }
        
        
        img_heroe.sceneProperty().addListener((obs, oldScene, newScene) -> {
        Platform.runLater(() -> {
            Stage stage = (Stage) img_heroe.getScene().getWindow();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                closeMethod();
                Platform.exit();
                System.exit(0);
            }
        });
        });
    });
        
        
         ImageView iv = new ImageView();
         iv.setImage(new Image("/clickerg/visualsAndFiles/icons/back.png"));
         iv.setFitHeight(50);
         iv.setFitWidth(80);
         bBack.setStyle("-fx-background-color: transparent;");
         bBack.setGraphic(iv);
    }
    
    @FXML
    private void irATown(ActionEvent event) throws IOException {
        
        closeMethod();
        Parent reserva = FXMLLoader.load(getClass().getResource("/clickerg/visualsAndFiles/heroes/heroes.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(reserva));
        //Preguntar por cierre
        stage.setTitle("Heroes");
        stage.show();
    }

    @FXML
    private void setActive(ActionEvent event) {
        lb_heroeAc.setVisible(true);
        needSave=true;
        for(int x = 0;x<contratados.size();x++){
             if("true".equals(contratados.get(x).getActive())){
                contratados.get(x).setActive("false");
             }     
        }
        for(int x = 0;x<contratados.size();x++){
             if(contratados.get(x).getId_heroe().equals(heroesInfo.getId_heroe())){
                contratados.get(x).setActive("true");
             }     
        }
    }
    
    
    public void closeMethod(){
        if(needSave){
            TemplateXMLWriter heroeWriter = new writeHeroeFileAccountInfo();
            heroeWriter.modifyXML(contratados, new int[]{Integer.parseInt(heroesInfo.getId_heroe())});
        }
        
    }

    @FXML
    private void addItem(ActionEvent event) throws IOException {
        closeMethod();
        itemToChange = ((Control) event.getSource()).getId();
        itemToChange = itemToChange.substring(itemToChange.length()-1, itemToChange.length());
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/clickerg/visualsAndFiles/items/items.fxml"));
        Parent reserva = (Parent) loader.load();
        loader.<Items>getController().initData(selectedHeroe,itemToChange);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(reserva));
        //Preguntar por cierre
        stage.setTitle("Items");
        stage.show();
    }
    
}