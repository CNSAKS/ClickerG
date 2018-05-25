/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clickerg;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author CNSAKS
 */
public class Exp implements Initializable {

    @FXML
    private Label lb_expCount;
    private int expPerClick;
    @FXML
    private Label label_expPerClick;
    @FXML
    private VBox vBox_exp;
    @FXML
    private Pane pn_ExpRoom;
    
    ArrayList<AuxiliarHeroe> contratados = new ArrayList<AuxiliarHeroe>();
    Document xml;
    ArrayList<String> id;
    ArrayList<String> name;
    ArrayList<String> id_heroe;
    ArrayList<String> lvl;
    ArrayList<String> base_atk;
    ArrayList<String> prob;
    ArrayList<String> active;
    ArrayList<String> expList;
    
    int baseLvlExp = 100;
    int nextLvlExp = baseLvlExp;
    int heroeLvl;
    int heroExperience;
    String idActual;
    @FXML
    private ProgressBar exp_bar;
    @FXML
    private Label currentexp;
    @FXML
    private ImageView imageHeroe;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        TemplateXMLonlyRead AccountInfoReader = new readBossFileAccountInfo();
        contratados = AccountInfoReader.readXML();
        expPerClick = AccountInfoReader.bossLvl;
        
        label_expPerClick.setText(expPerClick + "");
       
         loadfromXML("src/clickerg/main/accountInfo.xml", 1);
         for(int x = 0;x<contratados.size();x++){
             if("true".equals(contratados.get(x).getActive())){
                heroExperience = Integer.parseInt(contratados.get(x).getExp());
                idActual = contratados.get(x).getId();
                heroeLvl = Integer.parseInt(contratados.get(x).getLvl());
             }
        }
        lb_expCount.setText(heroExperience + "");
        nextLvlExp =  (int)( baseLvlExp *  Math.pow(1.16, heroeLvl-1));
        exp_bar.setProgress(0);
        exp_bar.setProgress((exp_bar.getProgress()+ heroExperience) / nextLvlExp );
        currentexp.setText((int) heroExperience +" / "+(int) nextLvlExp);
       
        
        
        
      //  imageHeroe.sceneProperty().addListener((obs, oldScene, newScene) -> {
       // Platform.runLater(() -> {
          //  Stage stage = (Stage) imageHeroe.getScene().getWindow();
          //  stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
          //  @Override
           // public void handle(WindowEvent t) {Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
             //   closeMethod();
              //  Platform.exit();
              //  System.exit(0);
     //       }
    //    });
      //  });
    // });
        
    }    

    @FXML
    private void moreExpPC(ActionEvent event) {
         expPerClick++;
         label_expPerClick.setText(expPerClick+"");
    }
    
    @FXML
    private void expIncrease(MouseEvent event) {
        exp_bar.setProgress((exp_bar.getProgress() *  nextLvlExp + expPerClick) / nextLvlExp );
        heroExperience += expPerClick;
        lb_expCount.setText(heroExperience + "");
        if(exp_bar.getProgress() * nextLvlExp >= nextLvlExp ){
            heroExperience = 0;
            exp_bar.setProgress(0);        
            lb_expCount.setText("0");
            heroeLvl++;
            nextLvlExp  = (int) (baseLvlExp * Math.pow(1.16, heroeLvl-1));
            nextLvlExp = (int) Math.floor(nextLvlExp);
            
        }
        currentexp.setText(lb_expCount.getText() + " / " + nextLvlExp);
        
    } 

    @FXML
    private void backTown(ActionEvent event) throws IOException {
        Parent reserva = FXMLLoader.load(getClass().getResource("main/main.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(reserva));
        stage.setTitle("Town");
        stage.show();
    }
    
    
    public void loadfromXML(String xmlRoute, int mode) {
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
            
            id_heroe = getTextValue(doc, "id_heroe");

            lvl = getTextValue(doc, "lvl");

            base_atk = getTextValue(doc, "base_atk");
            
            active = getTextValue(doc, "active");
            
            expList = getTextValue(doc, "exp");
            
            for(int i = 0;i<id.size();i++){
                contratados.add(new AuxiliarHeroe(id.get(i), name.get(i), lvl.get(i), base_atk.get(i), "0", active.get(i), expList.get(i), id_heroe.get(i)));
            }
            
        } catch (ParserConfigurationException pce) {
            System.out.println(pce.getMessage());
        } catch (SAXException se) {
            System.out.println(se.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
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
    private void pruebaActu(ActionEvent event){
        try{
        String filepath = "src/clickerg/main/accountInfo.xml";
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(filepath);
        
      
        
        NodeList heroesData = doc.getElementsByTagName("heroe");
        
        for(int i = 0; i<heroesData.getLength(); i++ ){
        
            Node n = heroesData.item(i);
            Element eleL = (Element) n;
            System.out.print(eleL.toString());
            Element eleX = (Element) eleL.getElementsByTagName("id").item(0);
            if(eleX.getFirstChild().getNodeValue().equals(idActual)){
                Node expData =eleL.getElementsByTagName("exp").item(0);
                
                expData.setTextContent(heroExperience + "");
                
                Node lvlData =eleL.getElementsByTagName("lvl").item(0);
                lvlData.setTextContent(heroeLvl + "");
            }
            
            
        
        }
        
        
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filepath));
        transformer.transform(source, result);
        
        }catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        // TODO Auto-generated catch block
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
   // public void closeMethod(){
       // gameHeroe.setClose(true);
     //     TemplateXMLWriter heroeWriter = new writeHeroeFileAccountInfo();
       //   heroeWriter.modifyXML(contratados, 0);         
   // }
    
}