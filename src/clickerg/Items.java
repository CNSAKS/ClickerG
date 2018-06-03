/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg;


import static clickerg.Heroes.selectedHeroe;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cnsak
 */
public class Items implements Initializable {

    private AuxiliarHeroe heroeToEquip;
    private String itemPosSelected;
    ArrayList<AuxiliarItem> items = new ArrayList<AuxiliarItem>();
    ArrayList<AuxiliarHeroe> heroes = new ArrayList<AuxiliarHeroe>();
    TemplateXMLWriter itemWriter = new writeItemsFileAccountInfo();
    
    @FXML
    private Label label_itemindicator;
    @FXML
    private VBox containerItems;
    @FXML
    private Button bBack;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void initData (AuxiliarHeroe heroeInfo, String itemToChange){
        heroeToEquip = heroeInfo;
        itemPosSelected = itemToChange;
        label_itemindicator.setText("Cambiando item del slot "+itemToChange);
        TemplateXMLonlyRead readerAccount = new readItemsFileAccountInfo();
        items = readerAccount.readXML();
        items.sort(new SortByAtk());
        TemplateXMLonlyRead bossAccount = new readExpFileAccountInfo();
        heroes = bossAccount.readXML();
        
        for(AuxiliarItem item : items){
            HBox temp_hbox = new HBox();
            Label temp_label = new Label();
            Label temp_label2 = new Label();
            Button temp_button = new Button();
            temp_hbox.setMinSize(584, 50);
            temp_hbox.setStyle("-fx-alignment: center ;-fx-border-color: black;");
            temp_label.setMinSize(250,20);
            temp_label.setText(item.getName()+" - ATK : x"+item.getBase_mult());
            temp_label2.setMinSize(250,20);
            if(item.getEquipado().equals("-1")){
                temp_label2.setText("No equipado");
            }else{
                search:
                for(AuxiliarHeroe heroe : heroes){
                    if(heroe.getId_heroe().equals(item.getEquipado())){
                       temp_label2.setText("Equipado en "+heroe.getName()+" de nivel: "+heroe.getLvl());
                       break search;
                    }
                }
            }

            temp_button.setId("item-"+item.getId());
            if(item.getEquipado().equals(heroeToEquip.getId_heroe())){
                temp_button.setDisable(true);
            }
            temp_button.setMinSize(50,20);
            temp_button.setText("Equipar");
            temp_button.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent event){
                    String idItem =(temp_button.getId().replaceAll("[A-z]*-", ""));
                    itemWriter.modifyXML(null, new int[]{Integer.parseInt(idItem), Integer.parseInt(heroeToEquip.getId_heroe()), Integer.parseInt(itemPosSelected)});
                    if(heroeToEquip.getItem_1().equals(idItem)){
                        heroeToEquip.setItem_1(-1+"");
                    }
                    if(heroeToEquip.getItem_2().equals(idItem)){
                        heroeToEquip.setItem_2(-1+"");
                    }
                    if(heroeToEquip.getItem_3().equals(idItem)){
                        heroeToEquip.setItem_3(-1+"");
                    }
                    
                    switch (itemPosSelected){
                        case "1": heroeToEquip.setItem_1(idItem);
                                  break;
                        case "2": heroeToEquip.setItem_2(idItem);
                                  break;
                        case "3": heroeToEquip.setItem_3(idItem);
                                  break;
                        default:  heroeToEquip.setItem_1(idItem);
                    }
                    try {
                        returnToHeroesInfo(event);
                    } catch (IOException ex) {
                        Logger.getLogger(Items.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            temp_hbox.getChildren().add(temp_label);
            temp_hbox.getChildren().add(temp_label2);
            temp_hbox.getChildren().add(temp_button);
            containerItems.getChildren().add(temp_hbox);
        }


        ImageView iv = new ImageView();
        iv.setImage(new Image("/clickerg/icons/back.png"));
        iv.setFitHeight(50);
        iv.setFitWidth(80);
        bBack.setStyle("-fx-background-color: transparent;");
        bBack.setGraphic(iv);
    }
    
    @FXML
    private void returnToHeroesInfo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("heroesInfo/heroesInfo.fxml"));
        Parent reserva = (Parent) loader.load();
        loader.<HeroesInfo>getController().initData(heroeToEquip);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(reserva));
        //Preguntar por cierre
        stage.setTitle("HeroesInfo");
        stage.show();
    }
    
}
