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
public class GachaToTest implements Initializable {

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
    public int gold;
    @FXML
    public ImageView iv_gold;
    @FXML
    public Label lb_gold;
    @FXML
    public ImageView iw_heroe;
    @FXML
    public Label lb_summon;
    @FXML
    public ImageView iv_back;
    
    public GameLoop gameBack;
    @FXML
    public Button bBack;
    @FXML
    public Label lb_noGold;
    
    public LabelTextVolatile noGold;
    
    public LabelTextVolatile summonHero;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        readFromXML();
        
        initializeTextAndStyles();
        
        initializeStaticImages();
        
        initializeAnimations();
        
        initializeOnClose();

    }
    
    public void readFromXML(){
    
        TemplateXMLonlyRead readerGacha = new readGachaFileGacha();
        contratos = readerGacha.readXML();
        
        TemplateXMLonlyRead readerGachaToClone = new readGachaToCloneFileGacha();
        contratosToClone = readerGachaToClone.readXML();
        
        TemplateXMLonlyRead readerAccount = new readGachaFileAccountInfo();
        contratados = readerAccount.readXML();
    
        gold = readerAccount.gold;
    }
    
    public void initializeTextAndStyles(){
    
        lb_gold.setText(""+getGold());
        lb_gold.setTextFill(Color.web("#FFFFFF"));
        lb_gold.setStyle("-fx-font-weight: bold");

        lb_noGold.setTextFill(Color.web("#FFFFFF"));
        lb_noGold.setStyle("-fx-font-weight: bold");

        lb_summon.setTextFill(Color.web("#FFFFFF"));
        lb_summon.setStyle("-fx-font-weight: bold");
    
    }
    
    public int getGold(){
    
        return gold;
    }
    
    public void setGold(int goldAmmount){
    
        this.gold = goldAmmount;
    }
    public void initializeAnimations(){
    
        gameBack = new GameLoop("1", iv_back, "background");
        gameBack.startGame();
    
    
    }
    public void initializeStaticImages(){
    
        iv_gold.setImage(new Image("/clickerg/icons/goldBag.gif"));
        
        ImageView iv = new ImageView();
        iv.setImage(new Image("/clickerg/icons/back.png"));
        iv.setFitHeight(50);
        iv.setFitWidth(80);
        
        bBack.setStyle("-fx-background-color: transparent;");
        bBack.setGraphic(iv);
    
    }
    public void initializeOnClose(){
    
        bBack.sceneProperty().addListener((obs, oldScene, newScene) -> {
        Platform.runLater(() -> {
            Stage stage = (Stage) bBack.getScene().getWindow();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                closeMethod();
                Platform.exit();
                System.exit(0);
                }       
            });
        });
    });
    
    }
 
    @FXML
    public void obtainRandomHero(MouseEvent event) throws CloneNotSupportedException   {
        if(checkGold()){
            randomHeroPicker();
        }
    }
    
    public boolean checkGold(){
    
        if(getGold()<500){
            
            //showVolatileLabelNoGold();
            return false;
            
        }
        
        return true;
    
    }
    
    public void randomHeroPicker() throws CloneNotSupportedException{
    
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
                        cloneHero(heroe);
                        break searchClone;
                    }
                }
                actualizeGold();
             //   showSummon(x);
                
                return;
            }
            actualSearch+=Integer.parseInt(contratos.get(x).getProb());
        }
    }
    
    public void showSummon(int x){
    
        iw_heroe.setImage(new Image("/clickerg/heroes/images/id_" + contratos.get(x).getId()+".png"));
        showLabelVolatileObtainedHero(x);
    
    }
    public void actualizeGold(){
    
        setGold(getGold()-500);
       // lb_gold.setText(""+getGold());
    }
    public void cloneHero(AccountHeroe heroe)throws CloneNotSupportedException{

        contratadosToSave.add((AccountHeroe) heroe.cloneObject());
    }
    
    
    public void showVolatileLabelNoGold(){
       
        noGold = new LabelTextVolatile(2500, lb_summon, "¡No tienes suficiente oro! Te falta " + (500-getGold()));
        noGold.startTime();
    }
    
    public void showLabelVolatileObtainedHero(int x){
        
        summonHero = new LabelTextVolatile(2500, lb_summon, "¡"+ contratos.get(x).getName() + " se ha unido a tu equipo!");
        summonHero.startTime();
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
    private void backToPreviousScene(ActionEvent event) throws IOException {
        closeMethod();
        
         Parent reserva = FXMLLoader.load(getClass().getResource("main/main.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(reserva));
        //Preguntar por cierre
        stage.setTitle("Town");
        stage.show();
    }
    
        
    private void closeMethod(){
        closeGameLoops();
        saveData();
             

    }
  
    public void closeGameLoops(){

        gameBack.setClose(true);
    }
    
    private void saveData(){
    
        TemplateXMLWriter gachaWriter = new writeGachaFileAccountInfo();
        gachaWriter.modifyXML(contratadosToSave, new int[]{getGold()});  
    }
    
    //compare para comprobacion de la clonacion
    
    public boolean compareHeroe(AccountHeroe a, AccountHeroe b){
        return a.getActive().equals(b.getActive()) &&
               a.getBase_atk().equals(b.getBase_atk()) &&
               a.getExp().equals(b.getExp()) &&
               a.getId().equals(b.getId()) &&
               a.getId_heroe().equals(b.getId_heroe()) &&
               a.getLvl().equals(b.getLvl()) &&
               a.getName().equals(b.getName());
                
    }
}
