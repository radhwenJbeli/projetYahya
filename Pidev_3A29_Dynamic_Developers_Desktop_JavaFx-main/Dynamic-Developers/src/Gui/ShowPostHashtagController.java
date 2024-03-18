/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;


import Entity.Post;
import Entity.PostLike;
import Service.ServicePost;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import static Gui.ShowPostController.hash;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import utils.SessionManager;


/**
 * FXML Controller class
 *
 * @author Oumayma
 */
public class ShowPostHashtagController implements Initializable {

    @FXML
    private Circle cir1;
    @FXML
    private Button tfquoideneuf;
    @FXML
    private VBox tfpostlist;
    private String h ;

    public String getH() {
        return h;
    }

    public String setH(String hash) {
        h=hash;
        System.out.println(getH());
        return hash;
    }

    
    
    public void initData(String h){
        ServicePost service=new ServicePost();
        List<Post> list = new ArrayList<Post>();
        list=service.recupererhashtag(h);
       // Collections.sort(list,Collections.reverseOrder());
        System.out.println(h);
      
        for(Post e : list)
        {  
           VBox du = new VBox();
           Label date= new Label(diffdate(e.getDateP()));
             
            Label username= new Label("TRAVEL ME");
           if (e.getVisibilite().equals("public"))
              username.setText(service.OneUser(e.getIdc()).getUserName());   
           else if(e.getVisibilite().equals("anonymous"))         
             username.setText("TRAVEL ME USER");
           else
             username.setText("TRAVEL ME");  
           
           du.getChildren().add(username);
           du.getChildren().add(date);
           du.setTranslateX(15);
           du.setTranslateY(15);
           
           HBox imgu = new HBox();
          
           Circle cir = new Circle();
           cir.setRadius(35);
           Image admin = new Image("/image/logo.png");
           Image user = new Image("/image/profile-pic.png");
           Image anonymous = new Image("/image/user-secret.png");
           
           if (e.getVisibilite().equals("public"))
            cir.setFill(new ImagePattern(user));   
           else if(e.getVisibilite().equals("anonymous"))          
             cir.setFill(new ImagePattern(anonymous));
           else
              cir.setFill(new ImagePattern(admin)); 
       
           cir.setTranslateX(5);
           cir.setTranslateY(5);
           
           FontAwesomeIconView modif = new FontAwesomeIconView(null);
           modif.setFill(Color.GREEN);
          // modif.setGlyphSize(25)
;
           modif.setCursor(Cursor.HAND);
           modif.setTranslateY(10);
           modif.setTranslateX(500);
           FontAwesomeIconView supp = new FontAwesomeIconView(null);
           supp.setFill(Color.RED);
          // supp.setGlyphSize(25);
           supp.setCursor(Cursor.HAND);
           supp.setTranslateX(520);
           supp.setTranslateY(10);
           
           supp.setOnMouseClicked(a->{
               service.Supprimer(e.getId());
               Parent root;
               try {
                   root = FXMLLoader.load(getClass().getResource("ShowPost.fxml"));
                    Scene scene = new Scene(root);
                   Stage stage = (Stage) ((Node) a.getSource()).getScene().getWindow();
                   stage.setScene(scene);
                   stage.show(); 
               } catch (IOException ex) {
                   Logger.getLogger(ShowPostController.class.getName()).log(Level.SEVERE, null, ex);
               }
           });
           
            modif.setOnMouseClicked(a->{
              
             Parent root;
               try {
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdatePost.fxml"));
                   root = (Parent) loader.load();
                   UpdatePostController controller = loader.getController();
             
                    controller.initData(e);
                   
                   System.out.println(loader.getController().toString());
                  
                   Stage stage=(Stage) ((Node) a.getSource()).getScene().getWindow();
                   stage.setScene(new Scene(root));
                   stage.show();
                   
               } catch (IOException ex) {
                   Logger.getLogger(ShowPostController.class.getName()).log(Level.SEVERE, null, ex);
               }
              
           });
           
        
           imgu.getChildren().add(cir);
           imgu.getChildren().add(du);
           
            if(service.UserPost(e.getId(),SessionManager.getId())){
             imgu.getChildren().add(modif);
             imgu.getChildren().add(supp);  
           }
           
          // VBox p = new VBox();
         
            //System.out.println("/image/"+e.getImageP());
           
           
           Label desc= new Label(e.getDescriptionP());
           desc.setTranslateX(10);
           desc.setTranslateY(20);
           Hyperlink hashtag = new Hyperlink();
           hashtag.setText("#"+e.getHashtagP());
           //Label hashtag= new Label("#"+h.getText());
           hashtag.setTranslateX(10);
           hashtag.setTranslateY(20);
           hash=hashtag.getText().substring(1);
        
           
           HBox lc = new HBox();
           
           FontAwesomeIconView like = new FontAwesomeIconView(null);
          // like.setGlyphSize(25);
           like.setCursor(Cursor.HAND);
                 if(service.islikedbyuser(e.getId(),SessionManager.getId()).isEmpty()){
                      //service.ajouterlike(e.getId(), 1);
                      like.setGlyphName("HEART_ALT");
                      //like.setGlyphSize(25);
                      like.setCursor(Cursor.HAND);
                      
                   }else{
                      //service.Supprimerlike(e.getId(),1);
                      like.setGlyphName("HEART");
                      //like.setGlyphSize(25);
                      like.setCursor(Cursor.HAND);
                     
                   }
           
           Label nblike= new Label("15");
           nblike.setPrefSize(40,25);
           nblike.setTranslateX(10);
           nblike.setTranslateY(-2);
            int l=service.likes(e.getId()).size();
           // e.setLikes(l);
                     if (l==0){
                        nblike.setText("0");
                     }else{
                       nblike.setText(String.valueOf(l)); 
                      // nblike.setText(String.valueOf(e.getLikes()));
                     }
           
           like.setOnMouseClicked(new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent lk) {
                   List<PostLike> test =service.likes(e.getId());
                   if(service.islikedbyuser(e.getId(),SessionManager.getId()).size()==0){
                      service.ajouterlike(e.getId(), SessionManager.getId());
                      like.setGlyphName("HEART");
                      //like.setGlyphSize(25);
                      like.setCursor(Cursor.HAND);
                      int li=service.likes(e.getId()).size();
                      e.setLikes(li);
                      nblike.setText(String.valueOf(li)); 
                   }else{
                      service.Supprimerlike(e.getId(),SessionManager.getId());
                      like.setGlyphName("HEART_ALT");
                      //like.setGlyphSize(25);
                      like.setCursor(Cursor.HAND);
                      int li=service.likes(e.getId()).size();
                      nblike.setText(String.valueOf(li)) ;
                   }
                    
               }
           });
           FontAwesomeIconView Comment = new FontAwesomeIconView(null);
           //Comment.setGlyphSize(25);
           Comment.setCursor(Cursor.HAND);
           Comment.setTranslateY(-2);
           
           Label nbcom = new Label("15");
           nbcom.setPrefSize(40,25);
           nbcom.setTranslateX(10);
           nbcom.setTranslateY(-2);
           
           int c=service.comments(e.getId());
           
                     if (c==0){
                        nbcom.setText("0");
                     }else{
                       nbcom.setText(String.valueOf(c));  
                     }
           lc.getChildren().add(like);
           lc.getChildren().add(nblike);
           lc.getChildren().add(Comment);
           lc.getChildren().add(nbcom);
           lc.setTranslateY(40);
           lc.setTranslateX(5);
           Comment.setOnMouseClicked(commentpage->{
              
             Parent root;
               try {
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML1post.fxml"));
                   root = (Parent) loader.load();
                  FXML1postController controller = loader.getController();
             
                    controller.initData(e);
                   
                   System.out.println(loader.getController().toString());
                  
                   Stage stage=(Stage) ((Node) commentpage.getSource()).getScene().getWindow();
                   stage.setScene(new Scene(root));
                   stage.show();
                   
               } catch (IOException ex) {
                   Logger.getLogger(ShowPostController.class.getName()).log(Level.SEVERE, null, ex);
               }
              
           });
          // p.getChildren().add(lc);
            VBox post= new VBox();
            post.getChildren().add(imgu);
            post.getChildren().add(desc);
            post.getChildren().add(hashtag);
           if (!(e.getImageP().equals("null"))){
           Image imagep = new Image("/image/"+e.getImageP());
           ImageView iv2 = new ImageView();
            iv2.setImage(imagep);
            iv2.setFitHeight(500);
            iv2.setFitWidth(800);
           
           iv2.setTranslateX(10);
           iv2.setTranslateY(30); 
              post.getChildren().add(iv2);}
           
            post.getChildren().add(lc);
            post.setTranslateY(20);
            tfpostlist.getChildren().add(post);
            tfpostlist.setSpacing(45);
            
           //tfpostlist.getChildren().add(p);
       
        }
               
    }
    
    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
       
         
        Image imageu = new Image("/image/profile-pic.png");
        cir1.setFill(new ImagePattern(imageu)); 
               
        
    }    

    @FXML
    private void redirect(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("AddPost.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }
    
     public String diffdate (String d) {
        
       SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
       LocalDateTime now = LocalDateTime.now();  
       String dc= dtf.format(now);
      // System.out.println(dtf.format(now));
       String date = null;
        try {
                        Date d1 = sdf.parse(d);
			Date d2 = sdf.parse(dc);

			// Calucalte time difference
			// in milliseconds
			long difference_In_Time
				= d2.getTime() - d1.getTime();

			// Calucalte time difference in
			// seconds, minutes, hours, years,
			// and days
			long difference_In_Seconds
				= (difference_In_Time
				/ 1000)
				% 60;

			long difference_In_Minutes
				= (difference_In_Time
				/ (1000 * 60))
				% 60;

			long difference_In_Hours
				= (difference_In_Time
				/ (1000 * 60 * 60))
				% 24;

			long difference_In_Years
				= (difference_In_Time
				/ (1000l * 60 * 60 * 24 * 365));

			long difference_In_Days
				= (difference_In_Time
				/ (1000 * 60 * 60 * 24))
				% 365;
                        
                        if (difference_In_Years != 0)
                           date= String.valueOf("il ya "+difference_In_Years+" années");
                        else if (difference_In_Days != 0)
                            date= String.valueOf("il ya "+difference_In_Days+" jours");    
		        else if (difference_In_Hours != 0)
                            date= String.valueOf("il ya "+difference_In_Hours+" heures");
                        else if (difference_In_Minutes != 0)
                            date= String.valueOf("il ya "+difference_In_Minutes+" minutes");
                        else if (difference_In_Seconds != 0)
                            date= String.valueOf("il ya "+difference_In_Seconds+" secondes");
                        else 
                            date="Now";
                        
                        
      } catch (ParseException ex) {
            Logger.getLogger(ShowPostController.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		
        return date ;
    }

    @FXML
    private void retour(MouseEvent event) throws IOException {
        
          Parent root = FXMLLoader.load(getClass().getResource("ShowPost.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }
     
   
}

    

