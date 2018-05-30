/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg;

import java.io.FileOutputStream;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.w3c.dom.*;

/**
 * FXML Controller class
 *
 * @author cnsak
 */
public class Gacha implements Initializable {

    ArrayList<AuxiliarHeroe> contratos = new ArrayList<AuxiliarHeroe>();
    ArrayList<AccountHeroe> contratosToClone = new ArrayList<AccountHeroe>();
    ArrayList<AccountHeroe> contratados = new ArrayList<AccountHeroe>();
    ArrayList<AccountHeroe> contratadosToSave = new ArrayList<AccountHeroe>();
    Document xml;
    ArrayList<String> goldInAccount;
    ArrayList<String> id;
    ArrayList<String> name;
    ArrayList<String> lvl;
    ArrayList<String> base_atk;
    ArrayList<String> prob;
    ArrayList<String> exp;
    int gold;
    @FXML
    private ImageView iv_gold;
    @FXML
    private Label lb_gold;
    @FXML
    private ImageView iw_heroe;
    @FXML
    private Label lb_summon;
    @FXML
    private ImageView iv_back;
    
    GameLoop gameBack;
    @FXML
    private Button bBack;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TemplateXMLonlyRead readerGacha = new readGachaFileGacha();
        contratos = readerGacha.readXML();
        
        TemplateXMLonlyRead readerGachaToClone = new readGachaToCloneFileGacha();
        contratosToClone = readerGachaToClone.readXML();
        
        //loadfromXML("src/clickerg/gacha/gacha.xml", 0);
        TemplateXMLonlyRead readerAccount = new readGachaFileAccountInfo();
        contratados = readerAccount.readXML();
        gold = readerAccount.gold;
        lb_gold.setText(""+gold);
        lb_gold.setTextFill(Color.web("#FFFFFF"));
        lb_gold.setStyle("-fx-font-weight: bold");
        
        lb_summon.setTextFill(Color.web("#FFFFFF"));
        lb_summon.setStyle("-fx-font-weight: bold");
        
        
        gameBack = new GameLoop("1", iv_back, "background");
        gameBack.startGame();
        
        iv_gold.setImage(new Image("/clickerg/gacha/goldBag.gif"));
        
         ImageView iv = new ImageView();
         iv.setImage(new Image("/clickerg/gacha/back.png"));
         iv.setFitHeight(50);
         iv.setFitWidth(80);
          bBack.setStyle("-fx-background-color: transparent;");
        bBack.setGraphic(iv);
        
        bBack.sceneProperty().addListener((obs, oldScene, newScene) -> {
        Platform.runLater(() -> {
            Stage stage = (Stage) bBack.getScene().getWindow();
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
        
        
    }
    
        
    private ArrayList<String> getTextValue(Element doc, String tag) {
        ArrayList<String> value = new ArrayList<String>();
        NodeList nl= doc.getElementsByTagName(tag);
        
        for(int x = 0;x<nl.getLength();x++){
           value.add(nl.item(x).getFirstChild().getNodeValue());
        }
        return value;
    }
    
   

    @FXML
    private void summon(MouseEvent event) throws CloneNotSupportedException {
        System.out.println("Oro:"+gold);
        if(gold<500){
            System.out.println("No tienes oro suficiente ("+(gold-500)+"), vete a farmear");
            return;
        }
        double value = Math.random()*100;
        double actualSearch = 0;
        for(int x = 0; x<contratos.size();x++){
            if(actualSearch+Integer.parseInt(contratos.get(x).getProb())>value){
                System.out.println("Te toco "+contratos.get(x).toString());
                //contratados.add(contratos.get(x));
                //contratadosToSave.add(contratos.get(x));
                searchClone:
                for(AccountHeroe heroe : contratosToClone){
                    if(heroe.getId().equals(contratos.get(x).getId())){
                        contratados.add((AccountHeroe) heroe.cloneObject());
                        contratadosToSave.add((AccountHeroe) heroe.cloneObject());
                        break searchClone;
                    }
                }
                gold-=500;
                lb_gold.setText(""+gold);
                iw_heroe.setImage(new Image("/clickerg/heroes/images/id_" + contratos.get(x).getId()+".png"));
                lb_summon.setText("¡"+ contratos.get(x).getName() + " se ha unido a tu equipo!");
                return;
            }
            actualSearch+=Integer.parseInt(contratos.get(x).getProb());
        }
    }
    
    public void saveToXML(String xmlRoute) throws SAXException, IOException {
        Document dom;
        Element e = null;
        Element heroe;

        // instance of a DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.parse(xmlRoute);

            Node rootEle = dom.getFirstChild();
            
            Node HeroesData = dom.getElementsByTagName("heroes").item(0);
            
            Node goldEle = dom.getElementsByTagName("gold").item(0);
            
            goldEle.setTextContent(gold + "");
            
            int heroesNumber = dom.getElementsByTagName("heroe").getLength();
            
            for(int x = 0;x<contratadosToSave.size();x++){
                heroe = dom.createElement("heroe");
                
                //borrar esta linea solo pruebas
                if(heroesNumber == 0){
                    if(x==0){
                        e = dom.createElement("active");
                        e.appendChild(dom.createTextNode("true"));
                        heroe.appendChild(e);
                    }else{
                        e = dom.createElement("active");
                        e.appendChild(dom.createTextNode("false"));
                        heroe.appendChild(e);
                    }
                }else{
                    e = dom.createElement("active");
                    e.appendChild(dom.createTextNode("false"));
                    heroe.appendChild(e);
                }
                
                
                e = dom.createElement("base_atk");
                e.appendChild(dom.createTextNode(contratadosToSave.get(x).getBase_atk()));
                heroe.appendChild(e);
                
                e = dom.createElement("exp");
                e.appendChild(dom.createTextNode(contratadosToSave.get(x).getExp()));
                heroe.appendChild(e);

                e = dom.createElement("id");
                e.appendChild(dom.createTextNode(contratadosToSave.get(x).getId()));
                heroe.appendChild(e);
                
                e = dom.createElement("id_heroe");
                e.appendChild(dom.createTextNode(heroesNumber+""));
                heroe.appendChild(e);

                e = dom.createElement("lvl");
                e.appendChild(dom.createTextNode(contratadosToSave.get(x).getLvl()));
                heroe.appendChild(e);

                e = dom.createElement("name");
                e.appendChild(dom.createTextNode(contratadosToSave.get(x).getName()));
                heroe.appendChild(e);

                Node heroeNode = (Node)heroe;
                
                HeroesData.appendChild(heroeNode);
                
                heroesNumber++;
            }
            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                //tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                // send DOM to file
                tr.transform(new DOMSource(dom), 
                                     new StreamResult(new FileOutputStream(xmlRoute)));

            } catch (TransformerException te) {
                System.out.println(te.getMessage());
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        } catch (ParserConfigurationException pce) {
            System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
        }
    }

    
    /*public void loadfromXML(String xmlRoute, int mode) {

        // Make an  instance of the DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use the factory to take an instance of the document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // parse using the builder to get the DOM mapping of the    
            // XML file
            xml = db.parse(xmlRoute);
            
            Element doc = xml.getDocumentElement();
            
            id = getTextValue(doc, "id");

            name = getTextValue(doc, "name");

            lvl = getTextValue(doc, "lvl");

            base_atk = getTextValue(doc, "base_atk");

            if(mode==0){
                prob = getTextValue(doc, "prob");
                for(int i = 0;i<id.size();i++){
                    contratos.add(new AuxiliarHeroe(id.get(i), name.get(i), lvl.get(i), base_atk.get(i), prob.get(i), "0"));
                }
            }else{
                goldInAccount = getTextValue(doc, "gold");
                gold = Integer.parseInt(goldInAccount.get(0));
                exp = getTextValue(doc, "exp");
                for(int i = 0;i<id.size();i++){
                    contratados.add(new AuxiliarHeroe(id.get(i), name.get(i), lvl.get(i), base_atk.get(i), "not needed", exp.get(i)));
                }
            }
            
        } catch (ParserConfigurationException pce) {
            System.out.println(pce.getMessage());
        } catch (SAXException se) {
            System.out.println(se.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }
    private void savePrueba(ActionEvent event) throws SAXException, IOException {
        saveToXML("src/clickerg/main/accountInfo.xml");
    }*/

    @FXML
    private void irATown(ActionEvent event) throws IOException {
        closeMethod();
        
         Parent reserva = FXMLLoader.load(getClass().getResource("main/main.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(reserva));
        //Preguntar por cierre
        stage.setTitle("Town");
        stage.show();
    }
    
        
  public void closeMethod(){
            gameBack.setClose(true);
            TemplateXMLWriter gachaWriter = new writeGachaFileAccountInfo();
            gachaWriter.modifyXML(contratadosToSave, gold);  

    }
    
}
